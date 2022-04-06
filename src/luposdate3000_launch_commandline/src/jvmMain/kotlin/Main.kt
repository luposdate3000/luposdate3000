/*
 * This file is part of the Luposdate3000 distribution (https://github.com/luposdate3000/luposdate3000).
 * Copyright (c) 2020-2021, Institute of Information Systems (Benjamin Warnke and contributors of LUPOSDATE3000), University of Luebeck
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, version 3.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
import lupos.endpoint.LuposdateEndpoint
import lupos.result_format.EQueryResultToStreamExt
import lupos.shared.inline.File
import lupos.shared.inline.MyPrintWriter
import lupos.shared.myPrintStackTrace

internal class MyCommands(
    internal val params: Array<String>,
    internal val action: (Array<String>) -> Unit,
)

public fun main() {
    var done = false
    var outputFile: String? = null
    val instance = LuposdateEndpoint.initialize()
    val commands = mutableMapOf<String, MyCommands>()
    commands["help"] = MyCommands(
        params = arrayOf(),
        action = { _ ->
            println("available commands:")
            for ((name, command) in commands) {
                println("$name ${command.params.joinToString(" ")}")
            }
        }
    )
    commands["quit"] = MyCommands(
        params = arrayOf(),
        action = { _ ->
            done = true
        }
    )
    commands["loadTurtle"] = MyCommands(
        params = arrayOf("<TurtleFileName>"),
        action = { args ->
            LuposdateEndpoint.importTripleFile(instance, args[1])
        }
    )
    commands["output"] = MyCommands(
        params = arrayOf("<OutputFileName?>"),
        action = { args ->
            if (args.size > 1) {
                outputFile = args[1]
            } else {
                outputFile = null
            }
        }
    )
    commands["eval"] = MyCommands(
        params = arrayOf("<QueryFileName>"),
        action = { args ->
            val node = LuposdateEndpoint.evaluateSparqlToOperatorgraphA(instance, File(args[1]).readAsString())
            println(node)
            if (outputFile == null) {
                val output = MyPrintWriter(true)
                LuposdateEndpoint.evaluateOperatorgraphToResultB(instance, node, output)
                println(output.toString())
            } else {
                File(outputFile!!).withOutputStream { output ->
                    LuposdateEndpoint.evaluateOperatorgraphToResultB(instance, node, output)
                }
            }
        }
    )
    commands["evalWith"] = MyCommands(
        params = arrayOf("<QueryFileName>", "<" + EQueryResultToStreamExt.names.joinToString("|") + ">"),
        action = { args ->
            val node = LuposdateEndpoint.evaluateSparqlToOperatorgraphA(instance, File(args[1]).readAsString())
            println(node)
            if (outputFile == null) {
                val output = MyPrintWriter(true)
                LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, node, output, EQueryResultToStreamExt.names.indexOf(args[2]))
                println(output.toString())
            } else {
                File(outputFile!!).withOutputStream { output ->
                    LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, node, output, EQueryResultToStreamExt.names.indexOf(args[2]))
                }
            }
        }
    )
    while (!done) {
        val line = readLine()
        if (line == null) {
            break
        }
        if (line != "") {
            val args = line.split(" ").toTypedArray()
            val command = commands[args[0]]
            if (command == null) {
                commands["help"]!!.action(args)
            } else {
                try {
                    command.action(args)
                } catch (e: Throwable) {
                    e.myPrintStackTrace(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_launch_commandline/src/jvmMain/kotlin/Main.kt:109"/*SOURCE_FILE_END*/ )
                }
            }
            println()
        }
    }
}

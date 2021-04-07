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
package lupos.s00misc

import lupos.dictionary.DictionaryHelper
import lupos.endpoint.LuposdateEndpoint
import lupos.s03resultRepresentation.*
import lupos.s04arithmetikOperators.generated.*
import lupos.s04arithmetikOperators.multiinput.*
import lupos.s04arithmetikOperators.noinput.AOPConstant
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.OPBaseCompound
import lupos.s09physicalOperators.POPBase
import lupos.s09physicalOperators.multiinput.POPJoinMerge
import lupos.s09physicalOperators.multiinput.POPUnion
import lupos.s09physicalOperators.singleinput.POPBind
import lupos.s09physicalOperators.singleinput.POPDebug
import lupos.s09physicalOperators.singleinput.POPFilter
import lupos.s09physicalOperators.singleinput.POPProjection
import lupos.s05tripleStore.POPTripleStoreIterator


public fun generateSourceCode(className: String,
                              packageName: String,
                              variableName: String,
                              variableValue: String,
                              folderName: String,
                              fileName: String
) {
    println("$className $packageName $variableName $variableValue into $fileName")
    java.io.File(folderName).mkdirs()
    // Root of the operatorgraph
    val preparedStatement = LuposdateEndpoint.evaluateSparqlToOperatorgraphA(variableValue)
    println("--------------GRAPH----------------------")
    println(preparedStatement)
    println("--------------GRAPH----------------------")
    // Buffer to store the separated operators
    val buffer = MyPrintWriter(true)
    // Buffer to store the generated operator classes (Bind, Filter, JoinMerge)
    val classes = MyPrintWriter(true)
    // Imports that will be used in the generated file
    val imports = mutableSetOf<String>()
    // Necessary imports for the generated file
    val commonImports = arrayOf(
        "lupos.s04logicalOperators.Query",
        "lupos.s04logicalOperators.IQuery",
        "lupos.s00misc.MyPrintWriter",
        "lupos.endpoint.LuposdateEndpoint",
        "com.ionspin.kotlin.bignum.integer.BigInteger",
        "com.ionspin.kotlin.bignum.decimal.BigDecimal",
        "lupos.s03resultRepresentation.compareTo",
        "lupos.s03resultRepresentation.plus",
        "lupos.s03resultRepresentation.minus",
        "lupos.s03resultRepresentation.times",
        "lupos.s03resultRepresentation.div",
        "lupos.s04logicalOperators.IOPBase",
        "lupos.s09physicalOperators.POPBase",
        "lupos.s00misc.EOperatorIDExt",
        "lupos.s00misc.ESortPriorityExt",
        "lupos.s00misc.SanityCheck",
        "lupos.s00misc.SanityCheck",
        "lupos.s00misc.XMLElement",
        "lupos.s00misc.Partition",
        "lupos.s04logicalOperators.iterator.ColumnIterator",
        "lupos.s04logicalOperators.iterator.IteratorBundle",
        "lupos.s04logicalOperators.iterator.ColumnIteratorQueue",
        "lupos.s04logicalOperators.iterator.ColumnIteratorQueueExt",
        "lupos.s04arithmetikOperators.generated.AOPAnd",
        "lupos.s03resultRepresentation.ValueIri",
        "lupos.dictionary.DictionaryHelper",
        "lupos.s00misc.ByteArrayWrapper"
    )
    commonImports.forEach { imports.add(it) } // Add the common imports
    val list = mutableListOf<Long>()
    // The containers to store the generated operator classes
    val containers = mutableListOf<ClazzContainer>()
    // Generate the operators from the operatorgraph so they are available independently in the generated file
    writeOperatorGraph(preparedStatement as OPBase, buffer, imports, classes, list, containers)
    // Print the generated source code
    java.io.File(fileName).printWriter().use { outFile ->
        outFile.println("package $packageName") // Package
        imports.forEach { outFile.println("import $it") } // Create all the imports
        outFile.println()
        // This is the function that can be called to retrieve the result
        outFile.println("public fun $className.${variableName}_evaluate(): String {")
        outFile.println()
        outFile.println("    val query = Query()") // New empty query object
        outFile.println("    val graph = tripleStoreManager.getGraph(\"\")")
        // Writing the operators to the generated file
        outFile.print(buffer.toString())
        outFile.println("    val buf = MyPrintWriter(true)")
        // Evaluate the operatorgraph with the operators from the generated files and store it in buf
        outFile.println("    LuposdateEndpoint.evaluateOperatorgraphToResult(operator${preparedStatement.getUUID()}, buf)")
        outFile.println("    return buf.toString()") // Return result as String
        outFile.println("}")
        outFile.println(classes.toString())
        for (container in containers){
            outFile.println(container.header.toString())
            outFile.println(container.iteratorHeader.toString())
            for(s in container.iteratorClassVariables) {
                outFile.println(s)
            }
            outFile.print(container.iteratorNextHeader.toString())
            for(s in container.iteratorNextVariables) {
                outFile.println(s)
            }
            outFile.println(container.iteratorNextBody.toString())
            outFile.println(container.iteratorNextBodyResult.toString())
            outFile.println(container.iteratorNextBodyEnd.toString())
            outFile.println(container.iteratorNextFooter.toString())
            outFile.println(container.iteratorCloseHeader.toString())
            outFile.println(container.iteratorCloseBody.toString())
            outFile.println(container.iteratorCloseFooter.toString())
            outFile.println(container.iteratorFooter.toString())
            outFile.println(container.footer.toString())
        }
    }
}



// Copies the Operators from the operatorgraph and replaces some with a generated Class specialized for that one query
private fun writeOperatorGraph(
    operatorGraph: OPBase,
    buffer: MyPrintWriter,
    imports: MutableSet<String>,
    classes: MyPrintWriter,
    knownChildren: MutableList<Long>,
    containers: MutableList<ClazzContainer>,
) {
    val tmpBuf = ByteArrayWrapper()
    val projectedVariables =
        "listOf(${(operatorGraph as? POPBase)?.projectedVariables?.map { "\"$it\"" }?.joinToString()})"
    // Calls this function recursively on all children
    for (child in operatorGraph.children) {
        if (!knownChildren.contains((child as OPBase).getUUID())) {
            knownChildren.add(child.getUUID())
            writeOperatorGraph(child, buffer, imports, classes, knownChildren, containers)
        }
    }
    // Check on what type of operator this function was called
    when (operatorGraph) {
        is POPJoinMerge -> {
            // Merge Joins will be implemented within the generated file, specialized for the annotated query
            generatePOPJoinMerge(operatorGraph, projectedVariables, buffer, imports, classes)
            // Original implementation
            /*buffer.println("    val operator${operatorGraph.uuid} = POPJoinMerge(query, $projectedVariables," +
                "operator${operatorGraph.children[0].getUUID()}," +
                "operator${operatorGraph.children[1].getUUID()}, false)"
            )
            imports.add("lupos.s09physicalOperators.multiinput.POPJoinMerge")*/
        }
        is POPFilter -> {
            // Filters will be implemented within the generated file, specialized for the annotated query
            generatePOPFilter(operatorGraph, projectedVariables, buffer, imports, containers)
            // Original implementation
            /*buffer.println("    val operator${operatorGraph.uuid} = POPFilter(query, $projectedVariables," +
                "operator${operatorGraph.children[1].getUUID()}," +
                "operator${operatorGraph.children[0].getUUID()})"
                )
                imports.add("lupos.s09physicalOperators.singleinput.POPFilter")
            }*/
        }
        is AOPVariable -> {
            buffer.println(
                "    val operator${operatorGraph.uuid} = AOPVariable(query," +
                    " \"${operatorGraph.name}\")"
            )
            imports.add("lupos.s04arithmetikOperators.noinput.AOPVariable")
        }
        is AOPConstant -> {
            operatorGraph.getQuery().getDictionary().getValue(tmpBuf, operatorGraph.getValue())
            val value = DictionaryHelper.byteArrayToValueDefinition(tmpBuf)
            buffer.println(
                "    val operator${operatorGraph.uuid} = AOPConstant(query," +
                    "ValueDefinition(\"${value.valueToString()?.replace("\"", "\\\"")}\"))"
            )
            imports.add("lupos.s04arithmetikOperators.noinput.AOPConstant")
            imports.add("lupos.s03resultRepresentation.ValueDefinition")
        }

        is POPTripleStoreIterator -> {
            buffer.println("    val operator${operatorGraph.uuid} = graph.getIterator(query, " +
                "arrayOf(operator${operatorGraph.children[0].getUUID()}," +
                "operator${operatorGraph.children[1].getUUID()}," +
                "operator${operatorGraph.children[2].getUUID()})," +
                "EIndexPatternExt.${EIndexPatternExt.names[operatorGraph.getIndexPattern()]})"
            )
            imports.add("lupos.s05tripleStore.tripleStoreManager")
            imports.add("lupos.s00misc.EIndexPatternExt")
            imports.add("lupos.s00misc.Partition")
        }
        is POPDebug -> {
            buffer.println(
                "    val operator${operatorGraph.uuid} = operator${operatorGraph.children[0].getUUID()}"
            )
        }
        is AOPGT -> {
            buffer.println(
                "    val operator${operatorGraph.uuid} = AOPGT(query," +
                    "operator${operatorGraph.children[0].getUUID()}," +
                    "operator${operatorGraph.children[1].getUUID()})"
            )
            imports.add("lupos.s04arithmetikOperators.multiinput.AOPGT")
        }
        is AOPLT -> {
            buffer.println(
                "    val operator${operatorGraph.uuid} = AOPLT(query," +
                    "operator${operatorGraph.children[0].getUUID()}," +
                    "operator${operatorGraph.children[1].getUUID()})"
            )
            imports.add("lupos.s04arithmetikOperators.multiinput.AOPLT")
        }
        is POPProjection -> {
            buffer.println(
                "    val operator${operatorGraph.uuid} = POPProjection(query," +
                    " $projectedVariables," +
                    "operator${operatorGraph.children[0].getUUID()})"
            )
            imports.add("lupos.s09physicalOperators.singleinput.POPProjection")
        }
        is OPBaseCompound -> {
            val proVars = "arrayOf(${operatorGraph.children.map { "operator" + it.getUUID() }.joinToString()})"
            val proVarsOrder = "listOf(${
                operatorGraph.columnProjectionOrder.map {
                    "listOf(${
                        it.map { it2 -> "\"$it2\"" }.joinToString()
                    })"
                }.joinToString()
            })"
            buffer.println(
                "    val operator${operatorGraph.uuid} = OPBaseCompound(query," +
                    "$proVars," +
                    "$proVarsOrder)"
            )
            imports.add("lupos.s04logicalOperators.OPBaseCompound")
        }
        is AOPAnd -> {
            buffer.println(
                "    val operator${operatorGraph.uuid} = AOPAnd(query," +
                    " operator${operatorGraph.children[0].getUUID()}," +
                    " operator${operatorGraph.children[1].getUUID()})"
            )
            imports.add("lupos.s04arithmetikOperators.multiinput.AOPAnd")
        }
        is POPUnion -> {
            buffer.println(
                "    val operator${operatorGraph.uuid} = POPUnion(query," +
                    " $projectedVariables," +
                    " operator${operatorGraph.children[0].getUUID()}," +
                    " operator${operatorGraph.children[1].getUUID()})"
            )
            imports.add("lupos.s09physicalOperators.multiinput.POPUnion")
        }
        is AOPEQ -> {
            buffer.println(
                "    val operator${operatorGraph.uuid} = AOPEQ(query," +
                    "operator${operatorGraph.children[0].getUUID()}," +
                    "operator${operatorGraph.children[1].getUUID()})"
            )
            imports.add("lupos.s04arithmetikOperators.multiinput.AOPEQ")
        }
        is AOPGEQ -> {
            buffer.println(
                "    val operator${operatorGraph.uuid} = AOPGEQ(query," +
                    "operator${operatorGraph.children[0].getUUID()}," +
                    "operator${operatorGraph.children[1].getUUID()})"
            )
            imports.add("lupos.s04arithmetikOperators.multiinput.AOPGEQ")
        }
        is AOPLEQ -> {
            buffer.println(
                "    val operator${operatorGraph.uuid} = AOPLEQ(query," +
                    "operator${operatorGraph.children[0].getUUID()}," +
                    "operator${operatorGraph.children[1].getUUID()})"
            )
            imports.add("lupos.s04arithmetikOperators.multiinput.AOPLEQ")
        }
        is AOPNEQ -> {
            buffer.println(
                "    val operator${operatorGraph.uuid} = AOPNEQ(query," +
                    "operator${operatorGraph.children[0].getUUID()}," +
                    "operator${operatorGraph.children[1].getUUID()})"
            )
            imports.add("lupos.s04arithmetikOperators.multiinput.AOPNEQ")
        }
        is AOPAddition -> {
            buffer.println(
                "    val operator${operatorGraph.uuid} = AOPAddition(query," +
                    "operator${operatorGraph.children[0].getUUID()}," +
                    "operator${operatorGraph.children[1].getUUID()})"
            )
            imports.add("lupos.s04arithmetikOperators.generated.AOPAddition")
        }
        is AOPSubtraction -> {
            buffer.println(
                "    val operator${operatorGraph.uuid} = AOPSubtraction(query," +
                    "operator${operatorGraph.children[0].getUUID()}," +
                    "operator${operatorGraph.children[1].getUUID()})"
            )
            imports.add("lupos.s04arithmetikOperators.generated.AOPSubtraction")
        }
        is AOPMultiplication -> {
            buffer.println(
                "    val operator${operatorGraph.uuid} = AOPMultiplication(query," +
                    "operator${operatorGraph.children[0].getUUID()}," +
                    "operator${operatorGraph.children[1].getUUID()})"
            )
            imports.add("lupos.s04arithmetikOperators.generated.AOPMultiplication")
        }
        is AOPDivision -> {
            buffer.println(
                "    val operator${operatorGraph.uuid} = AOPDivision(query," +
                    "operator${operatorGraph.children[0].getUUID()}," +
                    "operator${operatorGraph.children[1].getUUID()})"
            )
            imports.add("lupos.s04arithmetikOperators.generated.AOPDivision")
        }
        is POPBind -> {
            generatePOPBind(operatorGraph, projectedVariables, buffer, imports, containers)
        }
        else -> {
            throw Exception(operatorGraph.classname)
        }
    }
}

// Creates variables and the comparisons to filter, the root variable contains the filters result
internal fun writeFilter(child: IOPBase, classes: MyPrintWriter?, operatorGraph: OPBase, variables: MutableSet<String>?) {
    val tmpBuf = ByteArrayWrapper()

    for (c in child.getChildren()) {
        writeFilter(c, classes, operatorGraph, variables)
    }
    // On
    if (classes != null) {
        when (child) {
            is AOPAnd -> {
                classes.println("                    val child${child.uuid}: Boolean = child${child.children[0].getUUID()} && child${child.children[1].getUUID()}")
            }
            is AOPLT -> {
                classes.println("                    val child${child.uuid}: Boolean = child${child.children[0].getUUID()} < child${child.children[1].getUUID()}")
            }
            is AOPGT -> {
                classes.println("                    val child${child.uuid}: Boolean = child${child.children[0].getUUID()} > child${child.children[1].getUUID()}")
            }
            is AOPEQ -> {
                classes.println("                    val child${child.uuid}: Boolean = child${child.children[0].getUUID()} == child${child.children[1].getUUID()}")
            }
            is AOPLEQ -> {
                classes.println("                    val child${child.uuid}: Boolean = child${child.children[0].getUUID()} <= child${child.children[1].getUUID()}")
            }
            is AOPGEQ -> {
                classes.println("                    val child${child.uuid}: Boolean = child${child.children[0].getUUID()} >= child${child.children[1].getUUID()}")
            }
            is AOPNEQ -> {
                classes.println("                    val child${child.uuid}: Boolean = child${child.children[0].getUUID()} != child${child.children[1].getUUID()}")
            }
            is AOPAddition -> {
                classes.println("                    val child${child.uuid} = child${child.children[0].getUUID()} + child${child.children[1].getUUID()}")
            }
            is AOPSubtraction -> {
                classes.println("                    val child${child.uuid} = child${child.children[0].getUUID()} - child${child.children[1].getUUID()}")
            }
            is AOPMultiplication -> {
                classes.println("                    val child${child.uuid} = child${child.children[0].getUUID()} * child${child.children[1].getUUID()}")
            }
            is AOPDivision -> {
                classes.println("                    val child${child.uuid} = child${child.children[0].getUUID()} / child${child.children[1].getUUID()}")
            }
            is AOPVariable -> {
                // Muss in einen Datentyp gecastet werden, um Operationen wie ?pages+5 < 50 im Filter durchführen zu können
                // Hier klappt .toInt() am Ende ranhängen, sollte aber dynamisch erkannt werden; Anhängig von der Konstanten zuvor machen?
                classes.println(
                    "                   val tmp = ByteArrayWrapper()")
                classes.println(
                    "                   query.getDictionary().getValue(tmp, row${child.name})"
                )
                classes.println(
                    "                    val child${child.uuid} = DictionaryHelper.byteArrayToValueDefinition(tmp)"
                )
            }
            is AOPConstant -> {
            }
            else -> {
                throw Exception(child.getClassname())
            }
        }
    } else if(variables != null){
        if (child is AOPConstant) {
            child.getQuery().getDictionary().getValue(tmpBuf, child.getValue())
            when (val value = DictionaryHelper.byteArrayToValueDefinition(tmpBuf)) {
                is ValueBoolean -> {
                    variables.add("        val child${child.uuid} = ${value.value}")
                }
                is ValueInteger -> {
                    variables.add("        val child${child.uuid} = BigInteger.fromInt(${value.value})")
                }
                is ValueDecimal -> {
                    variables.add("        val child${child.uuid} = MyBigDecimal.fromBigDecimal(${value.value})")
                }
                is ValueStringBase -> {
                    // Erkennt noch nicht, dass es ein String ist?
                    // Abfragen wie equals/!equals werden noch als == und != übersetzt
                    variables.add(
                        "        val child${child.uuid} = \"${
                            value.valueToString()!!.replace("\"", "")
                        }\""
                    )
                }
                is ValueIri -> {
                    throw Exception("to do")
                }
                else -> {
                    throw Exception(value.toString())
                }
            }
        }
    }
}

// Containers to store the different parts of the generated operator
internal class ClazzContainer(val name: String, val uuid: Long){
    val header = MyPrintWriter(true)
    val iteratorHeader = MyPrintWriter(true)
    val iteratorClassVariables = mutableSetOf<String>()
    val iteratorNextHeader = MyPrintWriter(true)
    val iteratorNextBody = MyPrintWriter(true)
    val iteratorNextVariables = mutableSetOf<String>()
    val iteratorNextFooter = MyPrintWriter(true)
    val iteratorCloseHeader = MyPrintWriter(true)
    val iteratorCloseBody = MyPrintWriter(true)
    val iteratorCloseFooter = MyPrintWriter(true)
    val iteratorFooter = MyPrintWriter(true)
    val footer = MyPrintWriter(true)
    val iteratorNextBodyResult = MyPrintWriter(true)
    val iteratorNextBodyEnd = MyPrintWriter(true)
}

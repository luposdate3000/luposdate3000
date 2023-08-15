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
package lupos.code_generator_shared
import lupos.endpoint.LuposdateEndpoint
import lupos.operator.arithmetik.generated.AOPAddition
import lupos.operator.arithmetik.generated.AOPAnd
import lupos.operator.arithmetik.generated.AOPBuildInCallSTR
import lupos.operator.arithmetik.generated.AOPBuildInCallUCASE
import lupos.operator.arithmetik.generated.AOPDivision
import lupos.operator.arithmetik.generated.AOPMultiplication
import lupos.operator.arithmetik.generated.AOPSubtraction
import lupos.operator.arithmetik.multiinput.AOPEQ
import lupos.operator.arithmetik.multiinput.AOPGEQ
import lupos.operator.arithmetik.multiinput.AOPGT
import lupos.operator.arithmetik.multiinput.AOPLEQ
import lupos.operator.arithmetik.multiinput.AOPLT
import lupos.operator.arithmetik.multiinput.AOPNEQ
import lupos.operator.arithmetik.noinput.AOPConstant
import lupos.operator.arithmetik.noinput.AOPVariable
import lupos.operator.base.OPBase
import lupos.operator.base.OPBaseCompound
import lupos.operator.physical.POPBase
import lupos.operator.physical.multiinput.POPJoinMerge
import lupos.operator.physical.multiinput.POPUnion
import lupos.operator.physical.singleinput.POPBind
import lupos.operator.physical.singleinput.POPDebug
import lupos.operator.physical.singleinput.POPFilter
import lupos.operator.physical.singleinput.POPProjection
import lupos.operator.physical.singleinput.POPVisualisation
import lupos.shared.*
import lupos.shared.dynamicArray.ByteArrayWrapper
import lupos.shared.inline.DictionaryHelper
import lupos.shared.inline.MyPrintWriter
import lupos.shared.myPrintStackTrace
import lupos.shared.operator.IOPBase
import lupos.triple_store_manager.POPTripleStoreIterator
import java.io.OutputStream
import java.io.PrintWriter
import kotlin.jvm.JvmField

private const val passThroughGenericImplementation = false

public object CodeGeneration {
    public fun generateSourceCode(
        out: OutputStream,
        className: String,
        packageName: String,
        variableName: String,
        variableValue: String,
    ) {
        val instance = LuposdateEndpoint.initialize()
        try {
            // Root of the operatorgraph
            val preparedStatement = LuposdateEndpoint.evaluateSparqlToOperatorgraphA(instance, variableValue)
            // Buffer to store the separated operators
            val operatorsBuffer = MyPrintWriter(true)
            // Imports that will be used in the generated file
            val imports = mutableSetOf(
                "lupos.operator.base.Query",
                "lupos.shared.IQuery",
                "lupos.endpoint.LuposdateEndpoint",
                "com.ionspin.kotlin.bignum.integer.BigInteger",
                "com.ionspin.kotlin.bignum.decimal.BigDecimal",
                "lupos.shared.compareTo",
                "lupos.shared.plus",
                "lupos.shared.minus",
                "lupos.shared.times",
                "lupos.shared.div",
                "lupos.shared.DictionaryValueHelper",
                "lupos.shared.DictionaryValueType",
                "lupos.shared.DictionaryValueTypeArray",
                "lupos.shared.Luposdate3000Instance",
                "lupos.shared.operator.IOPBase",
                "lupos.operator.physical.POPBase",
                "lupos.shared.EOperatorIDExt",
                "lupos.shared.ESortPriorityExt",
                "kotlin.jvm.JvmField",
                "lupos.shared.SanityCheck",
                "lupos.shared.SanityCheck",
                "lupos.shared.XMLElement",
                "lupos.shared.Partition",
                "lupos.shared.operator.iterator.ColumnIterator",
                "lupos.shared.operator.iterator.IteratorBundle",
                "lupos.shared.operator.iterator.ColumnIteratorQueue",
                "lupos.operator.arithmetik.generated.AOPAnd",
                "lupos.shared.ValueIri",
                "lupos" + ".shared.inline.MyPrintWriter",
                "lupos" + ".shared.inline.ColumnIteratorQueueExt",
                "lupos" + ".shared.inline.DictionaryHelper",
                "lupos.shared.dictionary.DictionaryExt",
                "lupos.shared.dynamicArray.ByteArrayWrapper",
            )
            // This list will contain all the written operators
            val createdOperators = mutableListOf<Long>()
            // The containers to store the generated operator classes
            val classContainers = mutableListOf<ClazzContainer>()
            // Generate the operators from the operatorgraph so they are available independently in the generated file
            writeOperatorGraph(preparedStatement as OPBase, operatorsBuffer, imports, createdOperators, classContainers)
            // Print the generated source code
            val outFile = PrintWriter(out)
            outFile.println("package $packageName") // Package
            outFile.println()
            imports.forEach { outFile.println("import $it") } // Create all the imports
            outFile.println()
            // This is the function that can be called to retrieve the result
            outFile.println("public fun $className.${variableName}_evaluate(instance:Luposdate3000Instance):IOPBase {")
            // New empty query object
            outFile.println("    val query = Query(instance)")
            // This will be used to get the TripleStoreIterator
            outFile.println("    val graph = instance.tripleStoreManager!!.getGraph(\"\")") //
            // Writing the operators to the generated file
            outFile.print(operatorsBuffer.toString())
            // Evaluate the operatorgraph with the operators from the generated files and store it in buf
            outFile.println("    return operator${preparedStatement.getUUID()}")
            outFile.println("}")
            outFile.println()
            // This will print the generated operator classes
            for (container in classContainers) {
                outFile.print(container.header.toString())
                outFile.print(container.iteratorHeader.toString())
                for (s in container.iteratorClassVariables) {
                    outFile.println("@JvmField internal $s")
                }
                outFile.print(container.iteratorNextHeader.toString())
                for (s in container.iteratorNextVariables) {
                    outFile.println(s)
                }
                outFile.print(container.iteratorNextBody.toString())
                outFile.print(container.iteratorNextBodyResult.toString())
                outFile.print(container.iteratorNextBodyEnd.toString())
                outFile.print(container.iteratorNextFooter.toString())
                outFile.print(container.iteratorBody.toString())
                outFile.print(container.iteratorCloseHeader.toString())
                outFile.print(container.iteratorCloseBody.toString())
                outFile.print(container.iteratorCloseFooter.toString())
                outFile.print(container.iteratorFooter.toString())
                outFile.print(container.footer.toString())
            }
            outFile.close()
        } catch (e: Throwable) {
            e.myPrintStackTrace(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_code_generator_shared/src/jvmMain/kotlin/lupos/code_generator_shared/CodeGeneration.kt:154"/*SOURCE_FILE_END*/)
            val outFile = PrintWriter(out)
            outFile.println("package $packageName")
            outFile.println("import lupos.shared.operator.IOPBase")
            outFile.println("public fun $className.${variableName}_evaluate(instance:Luposdate3000Instance):IOPBase {")
            val txt = (
                "    println(\"${e.stackTraceToString().replace("\"", "\\\"")}\")"
                    .replace("\n", "\")\n    println(\"")
                    .replace("\r", "\")\n    println(\"") +
                    "\n"
                )
                .replace("    println(\"\")\n", "")
                .replace("\$", "\\\$")
            outFile.print(txt)
            outFile.println("    throw Exception(\"\")")
            outFile.println("}")
            outFile.close()
        }
        LuposdateEndpoint.close(instance)
    }
}

// Copies the Operators from the operatorgraph and replaces some with a generated classes specialized for that one query
private fun writeOperatorGraph(
    operator: OPBase,
    operatorsBuffer: MyPrintWriter,
    imports: MutableSet<String>,
    knownChildren: MutableList<Long>,
    classContainers: MutableList<ClazzContainer>,
) {
    val tmpBuf = ByteArrayWrapper()
    // Create a list of the projected variables, needed for the constructors of most operators
    val projectedVariables = "listOf(${(operator as? POPBase)?.projectedVariables?.joinToString { "\"$it\"" }})"
    // Calls this function recursively on all children
    //  and skips on already used one (not sure if that is still a problem?)
    for (child in operator.children) {
        if (!knownChildren.contains((child as OPBase).getUUID())) {
            knownChildren.add(child.getUUID())
            writeOperatorGraph(child, operatorsBuffer, imports, knownChildren, classContainers)
        }
    }
    // Check on what type of operator this function was called
    //  we have to create all operators so we can rebuild the operatorgraph
    //  later and replace some of them with our own specialized implementation
    when (operator) {
        is POPJoinMerge -> {
            if (passThroughGenericImplementation) {
                // Original implementation
                operatorsBuffer.println(
                    "    val operator${operator.uuid} = POPJoinMerge(query, $projectedVariables," +
                        "operator${operator.children[0].getUUID()}," +
                        "operator${operator.children[1].getUUID()}, false)",
                )
                imports.add("lupos.operator.physical.multiinput.POPJoinMerge")
            } else {
                // Merge Joins will be implemented within the generated file, specialized for the annotated query
                generatePOPJoinMerge(operator, projectedVariables, operatorsBuffer, imports, classContainers)
            }
        }
        is POPFilter -> {
            if (passThroughGenericImplementation) {
                // Original implementation
                operatorsBuffer.println(
                    "    val operator${operator.uuid} = POPFilter(query, $projectedVariables," +
                        "operator${operator.children[1].getUUID()}," +
                        "operator${operator.children[0].getUUID()})",
                )
                imports.add("lupos.operator.physical.singleinput.POPFilter")
            } else {
                // Filters will be implemented within the generated file, specialized for the annotated query
                generatePOPFilter(operator, projectedVariables, operatorsBuffer, imports, classContainers)
            }
        }
        is AOPVariable -> {
            // Creating a new operator with the AOPVariable constructor
            operatorsBuffer.println(
                "    val operator${operator.uuid} = AOPVariable(query," +
                    " \"${operator.name}\")",
            )
            imports.add("lupos.operator.arithmetik.noinput.AOPVariable")
        }
        is AOPConstant -> {
            // Creating a new operator with the AOPConstant constructor
            operator.getQuery().getDictionary().getValue(tmpBuf, operator.getValue())
            val value = DictionaryHelper.byteArrayToSparql(tmpBuf).replace("\"", "\\\"")
            operatorsBuffer.println("    val bbbbuffer=ByteArrayWrapper()")
            operatorsBuffer.println("    DictionaryHelper.sparqlToByteArray(bbbbuffer,\"$value\")")
            operatorsBuffer.println("    val operator${operator.uuid} = AOPConstant(query,bbbbuffer)")
            imports.add("lupos.operator.arithmetik.noinput.AOPConstant")
            imports.add("lupos.shared.ValueDefinition")
        }
        is POPTripleStoreIterator -> {
            // Creating a new operator with the POPTripleStoreIterator constructor
            operatorsBuffer.println(
                "    val operator${operator.uuid} = graph.getIterator(query, " +
                    "arrayOf(operator${operator.children[0].getUUID()}," +
                    "operator${operator.children[1].getUUID()}," +
                    "operator${operator.children[2].getUUID()})," +
                    "EIndexPatternExt.${EIndexPatternExt.names[operator.getIndexPattern()]})",
            )
            imports.add("lupos.shared.EIndexPatternExt")
            imports.add("lupos.shared.Partition")
        }
        is POPDebug -> {
            // Creating a new operator with the POPDebug constructor
            operatorsBuffer.println(
                "    val operator${operator.uuid} = operator${operator.children[0].getUUID()}",
            )
        }
        is POPVisualisation -> {
            // Creating a new operator with the POPVisualisation constructor
            operatorsBuffer.println(
                "    val operator${operator.uuid} = operator${operator.children[0].getUUID()}",
            )
        }
        is AOPGT -> {
            // Creating a new operator with the AOPGT constructor
            operatorsBuffer.println(
                "    val operator${operator.uuid} = AOPGT(query," +
                    "operator${operator.children[0].getUUID()}," +
                    "operator${operator.children[1].getUUID()})",
            )
            imports.add("lupos.operator.arithmetik.multiinput.AOPGT")
        }
        is AOPLT -> {
            // Creating a new operator with the AOPLT constructor
            operatorsBuffer.println(
                "    val operator${operator.uuid} = AOPLT(query," +
                    "operator${operator.children[0].getUUID()}," +
                    "operator${operator.children[1].getUUID()})",
            )
            imports.add("lupos.operator.arithmetik.multiinput.AOPLT")
        }
        is POPProjection -> {
            // Creating a new operator with the POPProjection constructor
            operatorsBuffer.println(
                "    val operator${operator.uuid} = POPProjection(query," +
                    " $projectedVariables," +
                    "operator${operator.children[0].getUUID()})",
            )
            imports.add("lupos.operator.physical.singleinput.POPProjection")
        }
        // Creating a new operator with the OPBaseCompound constructor
        is OPBaseCompound -> {
            val proVars = "arrayOf(${operator.children.joinToString { "operator" + it.getUUID() }})"
            val proVarsOrder = "listOf(${
                operator.columnProjectionOrder.joinToString {
                    "listOf(${
                        it.joinToString { it2 -> "\"$it2\"" }
                    })"
                }
            })"
            operatorsBuffer.println(
                "    val operator${operator.uuid} = OPBaseCompound(query," +
                    "$proVars," +
                    "$proVarsOrder)",
            )
            imports.add("lupos.operator.base.OPBaseCompound")
        }
        // Creating a new operator with the AOPAnd constructor
        is AOPAnd -> {
            operatorsBuffer.println(
                "    val operator${operator.uuid} = AOPAnd(query," +
                    " operator${operator.children[0].getUUID()}," +
                    " operator${operator.children[1].getUUID()})",
            )
            imports.add("lupos.operator.arithmetik.multiinput.AOPAnd")
        }
        // Creating a new operator with the POPUnion constructor
        is POPUnion -> {
            operatorsBuffer.println(
                "    val operator${operator.uuid} = POPUnion(query," +
                    " $projectedVariables," +
                    " operator${operator.children[0].getUUID()}," +
                    " operator${operator.children[1].getUUID()})",
            )
            imports.add("lupos.operator.physical.multiinput.POPUnion")
        }
        // Creating a new operator with the AOPEQ constructor
        is AOPEQ -> {
            operatorsBuffer.println(
                "    val operator${operator.uuid} = AOPEQ(query," +
                    "operator${operator.children[0].getUUID()}," +
                    "operator${operator.children[1].getUUID()})",
            )
            imports.add("lupos.operator.arithmetik.multiinput.AOPEQ")
        }
        // Creating a new operator with the AOPGEQ constructor
        is AOPGEQ -> {
            operatorsBuffer.println(
                "    val operator${operator.uuid} = AOPGEQ(query," +
                    "operator${operator.children[0].getUUID()}," +
                    "operator${operator.children[1].getUUID()})",
            )
            imports.add("lupos.operator.arithmetik.multiinput.AOPGEQ")
        }
        // Creating a new operator with the AOPLEQ constructor
        is AOPLEQ -> {
            operatorsBuffer.println(
                "    val operator${operator.uuid} = AOPLEQ(query," +
                    "operator${operator.children[0].getUUID()}," +
                    "operator${operator.children[1].getUUID()})",
            )
            imports.add("lupos.operator.arithmetik.multiinput.AOPLEQ")
        }
        // Creating a new operator with the AOPNEQ constructor
        is AOPNEQ -> {
            operatorsBuffer.println(
                "    val operator${operator.uuid} = AOPNEQ(query," +
                    "operator${operator.children[0].getUUID()}," +
                    "operator${operator.children[1].getUUID()})",
            )
            imports.add("lupos.operator.arithmetik.multiinput.AOPNEQ")
        }
        // Creating a new operator with the AOPAddition constructor
        is AOPAddition -> {
            operatorsBuffer.println(
                "    val operator${operator.uuid} = AOPAddition(query," +
                    "operator${operator.children[0].getUUID()}," +
                    "operator${operator.children[1].getUUID()})",
            )
            imports.add("lupos.operator.arithmetik.generated.AOPAddition")
        }
        // Creating a new operator with the AOPSubtraction constructor
        is AOPSubtraction -> {
            operatorsBuffer.println(
                "    val operator${operator.uuid} = AOPSubtraction(query," +
                    "operator${operator.children[0].getUUID()}," +
                    "operator${operator.children[1].getUUID()})",
            )
            imports.add("lupos.operator.arithmetik.generated.AOPSubtraction")
        }
        // Creating a new operator with the AOPMultiplication constructor
        is AOPMultiplication -> {
            operatorsBuffer.println(
                "    val operator${operator.uuid} = AOPMultiplication(query," +
                    "operator${operator.children[0].getUUID()}," +
                    "operator${operator.children[1].getUUID()})",
            )
            imports.add("lupos.operator.arithmetik.generated.AOPMultiplication")
        }
        // Creating a new operator with the AOPDivision constructor
        is AOPDivision -> {
            operatorsBuffer.println(
                "    val operator${operator.uuid} = AOPDivision(query," +
                    "operator${operator.children[0].getUUID()}," +
                    "operator${operator.children[1].getUUID()})",
            )
            imports.add("lupos.operator.arithmetik.generated.AOPDivision")
        }
        is POPBind -> {
            // POPBind will be implemented within the generated file, specialized for the annotated query
            generatePOPBind(operator, projectedVariables, operatorsBuffer, imports, classContainers)
        }
        is AOPBuildInCallUCASE -> {
            operatorsBuffer.println(
                "    val operator${operator.uuid} = AOPBuildInCallUCASE(query," +
                    "operator${operator.children[0].getUUID()})",
            )
            imports.add("lupos.operator.arithmetik.generated.AOPBuildInCallUCASE")
        }
        is AOPBuildInCallSTR -> {
            operatorsBuffer.println(
                "    val operator${operator.uuid} = AOPBuildInCallSTR(query," +
                    "operator${operator.children[0].getUUID()})",
            )
            imports.add("lupos.operator.arithmetik.generated.AOPBuildInCallSTR")
        }
        else -> {
            // Oops, seems like we forgot an operator
            throw Exception(operator.classname)
        }
    }
}

internal fun convert(child: IOPBase): CodeGenClassHolder {
    println("CALLED CONVERT")
    val children = Array(child.getChildren().size) {
        convert(child.getChildren()[it])
    }
    return when (child) {
        is AOPVariable -> {
            CodeGenClassHolder("AOPVariable", children, child.getName())
        }
        is AOPConstant -> {
            val dict = child.getQuery().getDictionary()
            val tmpBuf = ByteArrayWrapper()
            dict.getValue(tmpBuf, child.getValue())
            CodeGenClassHolder("AOPConstant", children, "", tmpBuf)
        }
        else -> {
            println("CE" + child.getClassname())
            CodeGenClassHolder(child.getClassname(), children)
        }
    }
}

internal fun writeMethod(
    child: IOPBase,
    classes: MyPrintWriter,
    variables: MutableSet<String>,
    imports: MutableSet<String>,
) {
    val conversion = CodeGenClassHolder("ToDictionaryID", arrayOf(CodeGenClassHolder("ToByteArrayWrapper", arrayOf(convert(child)))))

    val builder = StringBuilder()

    val target = StringBuilder()
    val variables = mutableMapOf<String, String>()
    val variableBuilder = StringBuilder()
    generateMethod(conversion, "", arrayOf(""), "rowx", "", imports, target, mutableSetOf(""), mutableListOf(), false, builder, variables, false)
    val buildString = builder.toString()

    for ((k, v) in variables) {
        if (buildString.contains(k) && v != "") {
            variableBuilder.appendLine("var $k : $v = ${getDefaultValue(v)}")
        }
    }
    classes.println(variableBuilder.toString())
    // println(target.toString());
    println(builder.toString())
    classes.println(builder.toString())
}

// Creates variables and the comparisons to filter, the root variable contains the filters result
internal fun writeFilter(child: IOPBase, classes: MyPrintWriter?, operatorGraph: OPBase, variables: MutableSet<String>?) {
    val tmpBuf = ByteArrayWrapper()

    // Call recursively for all children
    for (c in child.getChildren()) {
        writeFilter(c, classes, operatorGraph, variables)
    }
    // Classes != null means we want to create comparisons or do
    if (classes != null) {
        when (child) {
            is AOPAnd -> {
                classes.println("                        val child${child.uuid}: Boolean = child${child.children[0].getUUID()} && child${child.children[1].getUUID()}")
            }
            is AOPLT -> {
                classes.println("                        val child${child.uuid}: Boolean = child${child.children[0].getUUID()} < child${child.children[1].getUUID()}")
            }
            is AOPGT -> {
                classes.println("                        val child${child.uuid}: Boolean = child${child.children[0].getUUID()} > child${child.children[1].getUUID()}")
            }
            is AOPEQ -> {
                classes.println("                        val child${child.uuid}: Boolean = child${child.children[0].getUUID()} == child${child.children[1].getUUID()}")
            }
            is AOPLEQ -> {
                classes.println("                        val child${child.uuid}: Boolean = child${child.children[0].getUUID()} <= child${child.children[1].getUUID()}")
            }
            is AOPGEQ -> {
                classes.println("                        val child${child.uuid}: Boolean = child${child.children[0].getUUID()} >= child${child.children[1].getUUID()}")
            }
            is AOPNEQ -> {
                classes.println("                        val child${child.uuid}: Boolean = child${child.children[0].getUUID()} != child${child.children[1].getUUID()}")
            }
            is AOPAddition -> {
                classes.println("                        val child${child.uuid} = child${child.children[0].getUUID()} + child${child.children[1].getUUID()}")
            }
            is AOPSubtraction -> {
                classes.println("                        val child${child.uuid} = child${child.children[0].getUUID()} - child${child.children[1].getUUID()}")
            }
            is AOPMultiplication -> {
                classes.println("$                        val child${child.uuid} = child${child.children[0].getUUID()} * child${child.children[1].getUUID()}")
            }
            is AOPDivision -> {
                classes.println("                        val child${child.uuid} = child${child.children[0].getUUID()} / child${child.children[1].getUUID()}")
            }
            is AOPBuildInCallUCASE -> {
                classes.println("                        val child${child.uuid} = child${child.children[0].getUUID()}.toUpperCase()")
            }
            is AOPBuildInCallSTR -> {
                classes.println("                        val child${child.uuid} = child${child.children[0].getUUID()}.toString()")
            }
            is AOPVariable -> {
                // Muss in einen Datentyp gecastet werden, um Operationen wie ?pages+5 < 50 im Filter durchführen zu können
                // Hier klappt .toInt() am Ende ranhängen, sollte aber dynamisch erkannt werden; Anhängig von der Konstanten zuvor machen?
                classes.println("                        query.getDictionary().getValue(buffer, row${child.name})")
                classes.println("                        val child${child.uuid} = DictionaryHelper.byteArrayToValueDefinition(buffer)")
            }
            is AOPConstant -> {
            }
            else -> {
                throw Exception(child.getClassname())
            }
        }
    }
    // Variables != null means we want to create variables for comparison
}

// Containers to store the different parts of the generated operator
internal class ClazzContainer(@JvmField internal val name: String, @JvmField internal val uuid: Long) {
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
    val iteratorBody = MyPrintWriter(true)
}

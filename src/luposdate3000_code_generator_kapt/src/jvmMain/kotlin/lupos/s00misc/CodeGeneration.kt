package lupos.s00misc

// import com.squareup.kotlinpoet.*
import kotlin.reflect.typeOf
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
    // Buffer to store the generated code
    val buffer = MyPrintWriter(true)
    val classes = MyPrintWriter(true)
    // General imports
    val commonImports = arrayOf(
        "lupos.s04logicalOperators.Query",
        "lupos.s00misc.MyPrintWriter",
        "lupos.s16network.LuposdateEndpoint",
        "lupos.s00misc.MyBigInteger",
        "lupos.s00misc.MyBigDecimal",
        "lupos.s03resultRepresentation.compareTo",
        "lupos.s03resultRepresentation.plus",
        "lupos.s03resultRepresentation.minus",
        "lupos.s03resultRepresentation.times",
        "lupos.s03resultRepresentation.div"
    )
    // Imports that will be used in the generated file
    val imports = mutableSetOf<String>()
    commonImports.forEach { imports.add(it) } // Add the common imports
    val list = mutableListOf<Long>()
    val containers = mutableListOf<ClazzContainer>()
    // Generate the Operators that will be evaluated in the generated code
    writeOperatorGraph(preparedStatement as OPBase, buffer, imports, classes, list, containers)
    // Print the generated source code
    java.io.File(fileName).printWriter().use { out ->
        out.println("package $packageName")
        imports.forEach { out.println("import $it") }
        out.println()
        out.println("public fun $className.${variableName}_evaluate() {")
        out.println()
        out.println("    val query = Query()")
        out.print(buffer.toString()) // Printing the code from the Buffer
        out.println("    val buf = MyPrintWriter(true)")
        out.println("    LuposdateEndpoint.evaluateOperatorgraphToResult(operator${preparedStatement.getUUID()}, buf)")
        out.println("    println(\"-------------PRINTING RESULT FROM GENERATED CODE-------------\")")
        out.println("    println(buf)")
        out.println("}")
        out.println(classes.toString())
        for (container in containers){
            out.println(container.header.toString())
            out.println(container.iteratorHeader.toString())
            for(s in container.iteratorClassVariables) {
                out.println(s)
            }
            out.print(container.iteratorNextHeader.toString())
            for(s in container.iteratorNextVariablen) {
                out.println(s)
            }
            out.println(container.iteratorNextBody.toString())
            out.println(container.iteratorNextBodyResult.toString())
            out.println(container.iteratorNextBodyEnd.toString())
            out.println(container.iteratorNextFooter.toString())
            out.println(container.iteratorCloseHeader.toString())
            out.println(container.iteratorCloseBody.toString())
            out.println(container.iteratorCloseFooter.toString())
            out.println(container.iteratorFooter.toString())
            out.println(container.footer.toString())
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
    for (child in operatorGraph.children) {
        if (knownChildren.contains((child as OPBase).getUUID())) {
            println("COLLISION")
        } else {
            knownChildren.add(child.getUUID())
            writeOperatorGraph(child, buffer, imports, classes, knownChildren, containers)
        }
    }
    when (operatorGraph) {
        is POPJoinMerge -> {
            if(true) {
                generatePOPJoinMerge(operatorGraph, projectedVariables, buffer, imports, classes)
            }
            else {
                buffer.println("    val operator${operatorGraph.uuid} = POPJoinMerge(query, $projectedVariables," +
                    "operator${operatorGraph.children[0].getUUID()}," +
                    "operator${operatorGraph.children[1].getUUID()}, false)"
                )
                imports.add("lupos.s09physicalOperators.multiinput.POPJoinMerge")
            }
        }
        is POPFilter -> {
            generatePOPFilter(operatorGraph, projectedVariables, buffer, imports, containers)

        }
        is AOPVariable -> {
            buffer.println(
                "    val operator${operatorGraph.uuid} = AOPVariable(query," +
                    " \"${operatorGraph.name}\")"
            )
            imports.add("lupos.s04arithmetikOperators.noinput.AOPVariable")
        }
        is AOPConstant -> {
            //operatorGraph.getQuery().getDictionary().getValue(tmpBuf, operatorGraph.getValue())
            val valueDef =
                operatorGraph.getQuery().getDictionary().getValue(tmpBuf, operatorGraph.getValue()).toString()
                    ?.replace("\"", "\\\"")
            buffer.println(
                "    val operator${operatorGraph.uuid} = AOPConstant(query," +
                    " ValueDefinition(\"$valueDef\"))"
            )
            imports.add("lupos.s04arithmetikOperators.noinput.AOPConstant")
            imports.add("lupos.s03resultRepresentation.ValueDefinition")
        }
        is POPTripleStoreIterator -> {
            buffer.println(
                "    val operator${operatorGraph.uuid} = TripleStoreIteratorGlobal(query," +
                    "$projectedVariables,\"${operatorGraph.graphName}\"," +
                    "arrayOf(operator${operatorGraph.children[0].getUUID()}," +
                    "operator${operatorGraph.children[1].getUUID()}," +
                    "operator${operatorGraph.children[2].getUUID()})," +
                    " EIndexPatternExt.${EIndexPatternExt.names[operatorGraph.idx]}," +
                    "Partition())"
            )
            imports.add("lupos.s15tripleStoreDistributed.TripleStoreIteratorGlobal")
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
            imports.add("lupos.s04arithmetikOperators.multiinput.AOPAddition")
        }
        is AOPSubtraction -> {
            buffer.println(
                "    val operator${operatorGraph.uuid} = AOPSubtraction(query," +
                    "operator${operatorGraph.children[0].getUUID()}," +
                    "operator${operatorGraph.children[1].getUUID()})"
            )
            imports.add("lupos.s04arithmetikOperators.multiinput.AOPSubtraction")
        }
        is AOPMultiplication -> {
            buffer.println(
                "    val operator${operatorGraph.uuid} = AOPMultiplication(query," +
                    "operator${operatorGraph.children[0].getUUID()}," +
                    "operator${operatorGraph.children[1].getUUID()})"
            )
            imports.add("lupos.s04arithmetikOperators.multiinput.AOPMultiplication")
        }
        is AOPDivision -> {
            buffer.println(
                "    val operator${operatorGraph.uuid} = AOPDivision(query," +
                    "operator${operatorGraph.children[0].getUUID()}," +
                    "operator${operatorGraph.children[1].getUUID()})"
            )
            imports.add("lupos.s04arithmetikOperators.multiinput.AOPDivision")
        }
        is POPBind -> {
            generatePOPBind(operatorGraph, projectedVariables, buffer, imports, containers)
        }
        else -> {
            throw Exception(operatorGraph.classname)
        }
    }
}

// Creates variables for the comparisons in the filter, the root variable contains the filters result
// writeFilter(operatorGraph.children[1],  clazz.iteratorNextBody, operatorGraph, false)
internal fun writeFilter(child: IOPBase, classes: MyPrintWriter?, operatorGraph: OPBase, variablen: MutableSet<String>?) {
    val tmpBuf = ByteArrayWrapper()
    for (c in child.getChildren()) {
        writeFilter(c, classes, operatorGraph, variablen)
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
                    "                    val child${child.uuid} = query.getDictionary().getValue(row${child.name})"
                )
            }
            is AOPConstant -> {
            }
            else -> {
                throw Exception(child.getClassname())
            }
        }
    } else if(variablen != null){
        if (child is AOPConstant) {
            child.getQuery().getDictionary().getValue(tmpBuf, child.getValue())
            when (val value = ValueDefinition(tmpBuf.toString())) {
                is ValueBoolean -> {
                    variablen.add("        val child${child.uuid} = ${value.value}")
                }
                is ValueInteger -> {
                    variablen.add("        val child${child.uuid} = MyBigInteger(\"${value.value}\")")
                }
                is ValueDecimal -> {
                    variablen.add("        val child${child.uuid} = MyBigDecimal(\"${value.value}\")")
                }
                is ValueStringBase -> {
                    // Erkennt noch nicht, dass es ein String ist?
                    // Abfragen wie equals/!equals werden noch als == und != übersetzt
                    variablen.add(
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

internal class ClazzContainer(val name: String, val uuid: Long){
    val header = MyPrintWriter(true)
    val iteratorHeader = MyPrintWriter(true)
    val iteratorClassVariables = mutableSetOf<String>()
    val iteratorNextHeader = MyPrintWriter(true)
    val iteratorNextBody = MyPrintWriter(true)
    val iteratorNextVariablen = mutableSetOf<String>()
    val iteratorNextFooter = MyPrintWriter(true)
    val iteratorCloseHeader = MyPrintWriter(true)
    val iteratorCloseBody = MyPrintWriter(true)
    val iteratorCloseFooter = MyPrintWriter(true)
    val iteratorFooter = MyPrintWriter(true)
    val footer = MyPrintWriter(true)
    val iteratorNextBodyResult = MyPrintWriter(true)
    val iteratorNextBodyEnd = MyPrintWriter(true)
}

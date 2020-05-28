package lupos.s00misc
import lupos.s00misc.Coverage
object OperatorGraphToLatex {
    class StackElement(val name: String) {
        var projectionHelper = ""
        val children = mutableListOf<StackElement>()
        override fun toString(): String {
Coverage.funStart(1167)
            var res = StringBuilder()
Coverage.statementStart(1168)
            res.append("[")
Coverage.statementStart(1169)
            res.append(name)
Coverage.statementStart(1170)
            if (name == "Projection") {
Coverage.ifStart(1171)
                res.append("(\\textit{${projectionHelper}})")
Coverage.statementStart(1172)
            }
Coverage.statementStart(1173)
            if (children.size > 0) {
Coverage.ifStart(1174)
                if (children.size > 1) {
Coverage.ifStart(1175)
                    res.append("[")
Coverage.statementStart(1176)
                }
Coverage.statementStart(1177)
                for (c in children) {
Coverage.forLoopStart(1178)
                    res.append(c.toString())
Coverage.statementStart(1179)
                }
Coverage.statementStart(1180)
                if (children.size > 1) {
Coverage.ifStart(1181)
                    res.append("]")
Coverage.statementStart(1182)
                }
Coverage.statementStart(1183)
            }
Coverage.statementStart(1184)
            res.append("]")
Coverage.statementStart(1185)
            return res.toString()
        }
    }
    operator fun invoke(inputString: String, caption: String? = null): String {
Coverage.funStart(1186)
        val output = StringBuilder()
Coverage.statementStart(1187)
        output.append("\\documentclass[tikz,border=10pt]{standalone}\n")
Coverage.statementStart(1188)
        output.append("\\usepackage{forest}\n")
Coverage.statementStart(1189)
        output.append("\\begin{document}\n")
Coverage.statementStart(1190)
        output.append("\\begin{forest}\n")
Coverage.statementStart(1191)
        if (caption != null) {
Coverage.ifStart(1192)
            output.append("[$caption")
Coverage.statementStart(1193)
        }
Coverage.statementStart(1194)
        val stack = mutableListOf<StackElement>()
Coverage.statementStart(1195)
        for (element2 in inputString.split("<")) {
Coverage.forLoopStart(1196)
            val element = element2.replace("TripleStoreIteratorGlobal", "LOPTriple")
Coverage.statementStart(1197)
            when {
                element == "children>" || element == "/children>" || element == "LocalVariables>" || element == "/LocalVariables>" || element == "LocalVariables/>" || element == "variables>" || element == "/variables>" -> {
Coverage.whenCaseStart(1199)
                }
                element.startsWith("/LOP") || element.startsWith("/AOP") || element.startsWith("/POP") -> {
Coverage.whenCaseStart(1200)
                    if (stack.size == 1) {
Coverage.ifStart(1201)
                        output.append(stack[0].toString().replace("_", "\\_"))
Coverage.statementStart(1202)
                    }
Coverage.statementStart(1203)
                    if (stack.size > 1) {
Coverage.ifStart(1204)
                        stack[1].children.add(stack[0])
Coverage.statementStart(1205)
                    }
Coverage.statementStart(1206)
                    stack.removeAt(0)
Coverage.statementStart(1207)
                }
                element.startsWith("AOPVariable") -> {
Coverage.whenCaseStart(1208)
                    val idx = element.indexOf("name=\"") + 6
Coverage.statementStart(1209)
                    stack[0].children.add(StackElement("Variable(${element.substring(idx, element.length - 3)})"))
Coverage.statementStart(1210)
                }
                element.startsWith("Value") -> {
Coverage.whenCaseStart(1211)
                    if (stack.size > 0) {
Coverage.ifStart(1212)
                        stack[0].children.add(StackElement("Value"))
Coverage.statementStart(1213)
                    }
Coverage.statementStart(1214)
                }
                element.startsWith("LocalVariable") -> {
Coverage.whenCaseStart(1215)
                    if (stack.size > 0) {
Coverage.ifStart(1216)
                        if (stack[0].projectionHelper != "") {
Coverage.ifStart(1217)
                            stack[0].projectionHelper += ","
Coverage.statementStart(1218)
                        }
Coverage.statementStart(1219)
                        stack[0].projectionHelper += element.substring(20, element.length - 3)
Coverage.statementStart(1220)
                    }
Coverage.statementStart(1221)
                }
                element.startsWith("variable") -> {
Coverage.whenCaseStart(1222)
                    if (element != "variables/>") {
Coverage.ifStart(1223)
                        if (stack.size > 0) {
Coverage.ifStart(1224)
                            if (stack[0].projectionHelper != "") {
Coverage.ifStart(1225)
                                stack[0].projectionHelper += ","
Coverage.statementStart(1226)
                            }
Coverage.statementStart(1227)
                            stack[0].projectionHelper += element.substring(15, element.length - 3)
Coverage.statementStart(1228)
                        }
Coverage.statementStart(1229)
                    }
Coverage.statementStart(1230)
                }
                element.startsWith("LOP") || element.startsWith("AOP") || element.startsWith("POP") -> {
Coverage.whenCaseStart(1231)
                    stack.add(0, StackElement(element.substring(3, element.indexOf(" "))))
Coverage.statementStart(1232)
                }
                else -> {
Coverage.whenCaseStart(1233)
                }
            }
Coverage.statementStart(1234)
        }
Coverage.statementStart(1235)
        if (caption != null) {
Coverage.ifStart(1236)
            output.append("]")
Coverage.statementStart(1237)
        }
Coverage.statementStart(1238)
        output.append("\n")
Coverage.statementStart(1239)
        output.append("\\end{forest}\n")
Coverage.statementStart(1240)
        output.append("\\end{document}\n")
Coverage.statementStart(1241)
        return output.toString()
    }
}

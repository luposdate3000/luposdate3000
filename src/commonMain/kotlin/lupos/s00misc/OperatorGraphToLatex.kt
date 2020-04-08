package lupos.s00misc

object OperatorGraphToLatex {
    class StackElement(val name: String) {
        var projectionHelper = ""
        val children = mutableListOf<StackElement>()
        override fun toString(): String {
            var res = StringBuilder()
            res.append("[")
            res.append(name)
            if (name == "Projection") {
                res.append("(\\textit{${projectionHelper}})")
            }
            if (children.size > 0) {
                if (children.size > 1)
                    res.append("[")
                for (c in children) {
                    res.append(c.toString())
                }
                if (children.size > 1)
                    res.append("]")
            }
            res.append("]")
            return res.toString()
        }
    }

    operator fun invoke(inputString: String) :String{
        val output = StringBuilder()
        output.append("\\documentclass[tikz,border=10pt]{standalone}\n")
        output.append("\\usepackage{forest}\n")
        output.append("\\begin{document}\n")
        output.append("\\begin{forest}\n")
        val stack = mutableListOf<StackElement>()
        for (element2 in inputString.split("<")) {
            val element = element2.replace("TripleStoreIteratorGlobal", "LOPTriple")
            when {
                element == "children>" || element == "/children>" || element == "LocalVariables>" || element == "/LocalVariables>" || element == "LocalVariables/>" || element == "variables>" || element == "/variables>" -> {
                }
                element.startsWith("/LOP") || element.startsWith("/AOP") || element.startsWith("/POP") -> {
                    if (stack.size == 1) {
                        output.append(stack[0].toString().replace("_", "\\_"))
                    }
                    if (stack.size > 1) {
                        stack[1].children.add(stack[0])
                    }
                    stack.removeAt(0)
                }
                element.startsWith("AOPVariable") -> {
                    val idx = element.indexOf("name=\"") + 6
                    stack[0].children.add(StackElement("Variable(${element.substring(idx, element.length - 3)})"))
                }
                element.startsWith("Value") -> {
                    if (stack.size > 0) {
                        stack[0].children.add(StackElement("Value"))
                    }
                }
                element.startsWith("LocalVariable") -> {
                    if (stack.size > 0) {
                        if (stack[0].projectionHelper != "") {
                            stack[0].projectionHelper += ","
                        }
                        stack[0].projectionHelper += element.substring(20, element.length - 3)
                    }
                }
                element.startsWith("variable") -> {
                    if (stack.size > 0) {
                        if (stack[0].projectionHelper != "") {
                            stack[0].projectionHelper += ","
                        }
                        stack[0].projectionHelper += element.substring(15, element.length - 3)
                    }
                }
                element.startsWith("LOP") || element.startsWith("AOP") || element.startsWith("POP") -> {
                    stack.add(0, StackElement(element.substring(3, element.indexOf(" "))))
                }
                else -> {}
            }
        }
        output.append("\n")
        output.append("\\end{forest}\n")
        output.append("\\end{document}\n")
        return output.toString()
    }
}

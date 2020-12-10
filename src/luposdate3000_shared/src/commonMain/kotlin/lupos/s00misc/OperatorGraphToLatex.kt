package lupos.s00misc

import kotlin.jvm.JvmField

object OperatorGraphToLatex {
    class StackElement(@JvmField val name: String) {
        @JvmField
        var projectionHelper: String = ""

        @JvmField
        var partitionHelper: String = ""

        @JvmField
        val children: MutableList<StackElement> = mutableListOf()
        private fun getChildParallelism(): Int {
            var res = 0
            if (children.size > 0) {
                res = children[0].getChildParallelism()
            }
            if (name.startsWith("SplitPartition")) {
                res++
            } else if (name.startsWith("MergePartition")) {
                res--
            }
            return res
        }

        private fun getParallelism(): Int {
            var res = getChildParallelism()
            if (name.startsWith("MergePartition")) {
                res++
            }
            return res
        }

        private fun isChangingParallelism(): Boolean {
            return name.startsWith("SplitPartition") || name.startsWith("MergePartition")
        }

        override fun toString(): String {
            val parallelism = getParallelism()
            val res = StringBuilder()
            res.append("[")
            when {
                isChangingParallelism() -> {
                    res.append(coloredText("red", name))
                }
                parallelism > 0 -> {
                    res.append(coloredText("blue", name))
                }
                else -> {
                    res.append(name)
                }
            }
            if (name == "Projection") {
                res.append("(\\textit{$projectionHelper})")
            } else if (name.startsWith("SplitPartition") || name.startsWith("MergePartition")) {
                res.append("(\\textit{$partitionHelper})")
            }
            if (children.size > 0) {
                if (children.size > 1) {
                    res.append("[")
                }
                for (c in children) {
                    res.append(c.toString())
                }
                if (children.size > 1) {
                    res.append("]")
                }
            }
            res.append("]")
            return res.toString()
        }
    }

    fun coloredText(color: String, str: String): String = "\\textcolor{$color}{$str}"
    operator fun invoke(inputString: String, caption: String? = null): String {
        val output = StringBuilder()
        output.append("\\documentclass[tikz,border=10pt]{standalone}\n")
        output.append("\\usepackage{forest}\n")
        output.append("\\usepackage{xcolor}\n")
        output.append("\\begin{document}\n")
        output.append("\\begin{forest}\n")
        if (caption != null) {
            output.append("[$caption")
        }
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
                    if (element != "variables/>") {
                        if (stack.size > 0) {
                            if (stack[0].projectionHelper != "") {
                                stack[0].projectionHelper += ","
                            }
                            stack[0].projectionHelper += element.substring(15, element.length - 3)
                        }
                    }
                }
                element.startsWith("POPSplitPartition") || element.startsWith("POPMergePartition") -> {
                    stack.add(0, StackElement(element.substring(3, element.indexOf(" "))))
                    val t = "partitionVariable"
                    val i = element.indexOf(t) + t.length + 2
                    val j = element.indexOf("\"", i)
                    stack[0].partitionHelper = element.substring(i, j)
                }
                element.startsWith("LOP") || element.startsWith("AOP") || element.startsWith("POP") -> {
                    stack.add(0, StackElement(element.substring(3, element.indexOf(" "))))
                }
                else -> {
                }
            }
        }
        if (caption != null) {
            output.append("]")
        }
        output.append("\n")
        output.append("\\end{forest}\n")
        output.append("\\end{document}\n")
        return output.toString()
    }
}

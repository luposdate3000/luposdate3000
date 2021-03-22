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

public object OperatorGraphToLatex {
    public operator fun invoke(inputString: String, caption: String? = null): String {
        val output = StringBuilder()
        output.append("\\documentclass[tikz,border=10pt]{standalone}\n")
        output.append("\\usepackage{forest}\n")
        output.append("\\usepackage{xcolor}\n")
        output.append("\\begin{document}\n")
        output.append("\\begin{forest}\n")
        if (caption != null) {
            output.append("[$caption")
        }
        val stack = mutableListOf<OperatorGraphToLatex_StackElement>()
        for (element2 in inputString.split("<")) {
            val element = element2.replace("POPTripleStoreIterator", "LOPTriple")
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
                    stack[0].children.add(OperatorGraphToLatex_StackElement("Variable(${element.substring(idx, element.length - 3)})"))
                }
                element.startsWith("Value") -> {
                    if (stack.size > 0) {
                        stack[0].children.add(OperatorGraphToLatex_StackElement("Value"))
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
                    stack.add(0, OperatorGraphToLatex_StackElement(element.substring(3, element.indexOf(" "))))
                    val t = "partitionVariable"
                    val i = element.indexOf(t) + t.length + 2
                    val j = element.indexOf("\"", i)
                    stack[0].partitionHelper = element.substring(i, j)
                }
                element.startsWith("LOP") || element.startsWith("AOP") || element.startsWith("POP") -> {
                    stack.add(0, OperatorGraphToLatex_StackElement(element.substring(3, element.indexOf(" "))))
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

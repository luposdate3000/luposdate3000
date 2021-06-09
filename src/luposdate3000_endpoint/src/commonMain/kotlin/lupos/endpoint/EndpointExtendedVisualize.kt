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
package lupos.endpoint

import lupos.operator.arithmetik.noinput.AOPConstant
import lupos.operator.arithmetik.noinput.AOPVariable
import lupos.operator.base.Query
import lupos.operator.physical.singleinput.POPVisualisation
import lupos.optimizer.ast.OperatorGraphVisitor
import lupos.optimizer.logical.LogicalOptimizer
import lupos.optimizer.physical.PhysicalOptimizer
import lupos.optimizer.physical.PhysicalOptimizerVisualisation
import lupos.parser.LexerCharIterator
import lupos.parser.LookAheadTokenIterator
import lupos.parser.sparql1_1.ASTNode
import lupos.parser.sparql1_1.SPARQLParser
import lupos.parser.sparql1_1.TokenIteratorSPARQLParser
import lupos.shared.IVisualisation
import lupos.shared.Luposdate3000Instance
import lupos.shared.operator.IOPBase
import lupos.shared_inline.MyPrintWriter
import kotlin.js.JsName

public class EndpointExtendedVisualize(input: String, internal val instance: Luposdate3000Instance) : IVisualisation {
    private var resultLog: Array<String>
    private var resultPhys: Array<String>
    private var result: String
    private var animationData: MutableList<String> = mutableListOf()

    init {
        val query: String = input
        val q: Query = Query(instance)
        val lcit: LexerCharIterator = LexerCharIterator(query)
        val tit: TokenIteratorSPARQLParser = TokenIteratorSPARQLParser(lcit)
        val ltit: LookAheadTokenIterator = LookAheadTokenIterator(tit, 3)
        val parser: SPARQLParser = SPARQLParser(ltit)
        val astNode: ASTNode = parser.expr()
        val lopNode: IOPBase = astNode.visit(OperatorGraphVisitor(q)) // Log Operatorgraph
        val logSteps: MutableList<IOPBase> = mutableListOf()
        val optLog: IOPBase = LogicalOptimizer(q).optimizeCall(lopNode, {}, { logSteps.add(it.cloneOP()) })
        val resultLogTmp = mutableListOf<String>()
        for (i in logSteps) {
            traverseNetwork(i, mutableMapOf())
            resultLogTmp.add(getJsonData(i))
        }
        resultLog = resultLogTmp.toTypedArray()
        val popOptimizer: PhysicalOptimizer = PhysicalOptimizer(q)
        val physSteps: MutableList<IOPBase> = mutableListOf()
        val tmp: IOPBase =
            popOptimizer.optimizeCall(optLog, {}, { physSteps.add(it.cloneOP()) }) // Physical Operatorgraph
        val optPhys: IOPBase = PhysicalOptimizerVisualisation(q).optimizeCall(tmp)
        val resultPhysTmp = mutableListOf<String>()
        for (i in physSteps) {
            traverseNetwork(i, mutableMapOf())
            resultPhysTmp.add(getJsonData(i))
        }
        traverseNetwork(optPhys, mutableMapOf())
        resultPhysTmp.add(getJsonData(optPhys))
        resultPhys = resultPhysTmp.toTypedArray()

        val buf = MyPrintWriter(true)
        recursive(optPhys)
        LuposdateEndpoint.evaluateOperatorgraphToResult(instance, optPhys, buf)
        result = buf.toString()
    }

    private fun recursive(node: IOPBase) {
        for (i in node.getChildren()) {
            recursive(i)
        }
        if (node is POPVisualisation) {
            node.visualTest = this
        }
    }

    @JsName("getDataSteps")
    public fun getDataSteps(): Array<String> {
        return animationData.toTypedArray()
    }

    @JsName("getOptimizedStepsPhysical")
    public fun getOptimizedStepsPhysical(): Array<String> {
        return resultPhys
    }

    @JsName("getOptimizedStepsLogical")
    public fun getOptimizedStepsLogical(): Array<String> {
        return resultLog
    }

    @JsName("getResult")
    public fun getResult(): String {
        return result
    }

    // Input: (Sub)-Tree of the Query
// Output: Node and Edge Information as String for each node of the tree
//
// Receives a node as IOPBase and calls sub methods to determine the Node and
// Edge data as strings which are needed for the visualization framework.
    private fun getJsonData(baum: IOPBase): String {
        val x = baum
        // Calling traverseNetwork method to change the UUIDs via DFS.
        var map = mutableMapOf<IOPBase, Int>() // define empty map
        map = traverseNetwork(x, map)

        // Sub-String creation for all Node Data
        var node = "["
        node += createNodeJson(x, map)
        node = node.substring(0, node.length - 1)
        node += "]"

        // Sub-String creation for all Edge Data
        var edge = "["
        for (i in x.getChildren()) {
            edge += createEdgeJson(i, map[x], map)
        }

        edge = edge.substring(0, edge.length - 1)
        edge += "]"

        // "SPLITHERE" will be the marker to split the node data from the edge data
        // in the visualization code
        return node + "SPLITHERE" + edge
    }

    // Input: (Sub)-Tree as IOPBase, MutableMap
// Output: New MutableMap
//
// Method checks if an node is already included
// -> yes: cloning operator to avoid ID duplicate in the visualization and
// -> no: adding node to the hashmap and reassigning the UUID based on the size of the
//          hashmap, which results in an DFS like reassignment of the UUIDs.
    private fun traverseNetwork(teilbaum: IOPBase, mutableMap: MutableMap<IOPBase, Int>): MutableMap<IOPBase, Int> {
        var hashMap = mutableMap
        val x = teilbaum
        // Node already included (node is multiple times within the tree)
        if (mutableMap.contains(x)) {
            val tmp = x.cloneOP()
            mutableMap[tmp] = mutableMap.size + 1
            mutableMap[tmp]?.let { tmp.setVisualUUID(it.toLong()) }
            if (tmp.getChildren().isNotEmpty()) {
                for (i in tmp.getChildren()) {
                    hashMap = traverseNetwork(i, mutableMap)
                }
            }
            // Node not included
        } else {
            mutableMap[x] = mutableMap.size + 1
            mutableMap[x]?.let { x.setVisualUUID(it.toLong()) }
            if (x.getChildren().isNotEmpty()) {
                for (i in x.getChildren()) {
                    hashMap = traverseNetwork(i, mutableMap)
                }
            }
        }
        return hashMap
    }

    // Input: (Sub)-Tree as IOPBase, UUID from the node that is connection to this node, hashmap
// Output: Edge information as string needed for visualization framework
//
// Outputs a string that creates an Edge in the visualization framework
    private fun createEdgeJson(teilbaum: IOPBase, uuid: Int?, mutableMap: MutableMap<IOPBase, Int>): String {
        val x = teilbaum
        val map = mutableMap
        val toId = uuid // UUID of the node that is connected to this node (x)
        var result = String()
        result += "{\"from\": ${map[x]}, \"to\": $toId,\"width\":1},"
        if (x.getChildren().isNotEmpty()) {
            for (i in x.getChildren()) {
                result += createEdgeJson(i, map[x], map)
            }
        }
        return result
    }

    // Input: (Sub)-Tree as IOPBase,  hashmap
// Output: Node information as string needed for visualization framework
//
// Outputs a string that creates a Node in the visualization framework
    private fun createNodeJson(teilbaum: IOPBase, mutableMap: MutableMap<IOPBase, Int>): String {
        val x = teilbaum
        val map = mutableMap
        var result = String()
        var label = String()
        try {
            // AOPVariable and AOPConstant have a different structure as IOPBase
            // -> Different variables are containing the needed information for visualization
            when (x) {
                is AOPVariable -> {
                    label = "\"label\": \"${x.getClassname()} ${x.getUUID()}\\n${
                    "?" + x.getName().replace("\n", "").replace("\"", "\\\"")
                    }\""
                }
                is AOPConstant -> {
                    label = "\"label\": \"${x.getClassname()} ${x.getUUID()}\\n${
                    x.toSparql().replace("\n", "").replace("\"", "\\\"")
                    }\""
                    // In general: All IOPBase nodes
                }
                else -> {
                    label = "\"label\": \"${x.getClassname()} ${x.getUUID()}\\n${
                    x.getProvidedVariableNames().toString().replace("\n", "").replace("\"", "\\\"")
                    }\""
                }
            }
            // In case getProvidedVariableNames is not defined for a given operator.
        } catch (e: Exception) {
            label = "\"label\": \"${x.getClassname()} ${x.getUUID()}\""
        }
        result += "{\"id\": ${map[x]}, $label},"
        if (x.getChildren().isNotEmpty()) {
            for (i in x.getChildren()) {
                result += createNodeJson(i, map)
            }
        }
        return result
    }

    override fun sendData(string: String) {
        animationData.add(string)
    }
}

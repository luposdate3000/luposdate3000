package lupos.endpoint_launcher

import lupos.operator.arithmetik.noinput.AOPConstant
import lupos.operator.arithmetik.noinput.AOPVariable
import lupos.operator.base.Query
import lupos.optimizer.ast.OperatorGraphVisitor
import lupos.optimizer.logical.LogicalOptimizer
import lupos.optimizer.physical.PhysicalOptimizer
import lupos.optimizer.physical.PhysicalOptimizerVisualisation
import lupos.parser.LexerCharIterator
import lupos.parser.LookAheadTokenIterator
import lupos.parser.sparql1_1.SPARQLParser
import lupos.parser.sparql1_1.TokenIteratorSPARQLParser
import lupos.shared.operator.IOPBase
import kotlin.js.JsName

public class EndpointExtendedVisualize {

    // Optimising the physical operator graph and is returning each step of the process for visualisation
    @JsName("getOptimizedStepsPhysical")
    public fun getOptimizedStepsPhysical(query: String): Array<String> {
        val q = Query()
        val lcit = LexerCharIterator(query)
        val tit = TokenIteratorSPARQLParser(lcit)
        val ltit = LookAheadTokenIterator(tit, 3)
        val parser = SPARQLParser(ltit)
        val astNode = parser.expr()
        val lopNode = astNode.visit(OperatorGraphVisitor(q)) // Log Operatorgraph
        val lopNode2 = LogicalOptimizer(q).optimizeCall(lopNode) // Log Operatorgraph Optimized
        val popOptimizer = PhysicalOptimizer(q)
        var tmp = mutableListOf<IOPBase>()
        val tmp3 = popOptimizer.optimizeCall(lopNode2, {}, { tmp.add(it.cloneOP()) }) // Physical Operatorgraph
        val tmp2 = PhysicalOptimizerVisualisation(q).optimizeCall(tmp3)
        var result = mutableListOf<String>()
        // Change the UUIDs in each step beginning from 1 via DFS.
        for (i in tmp) {
            traverseNetwork(i, mutableMapOf<IOPBase, Int>())
            result.add(getJsonData(i))
        }
        return result.toTypedArray()
    }

    // Optimising the logical operator graph and is returning each step of the process for visualisation
    @JsName("getOptimizedStepsLogical")
    public fun getOptimizedStepsLogical(query: String): Array<String> {
        val q = Query()
        val lcit = LexerCharIterator(query)
        val tit = TokenIteratorSPARQLParser(lcit)
        val ltit = LookAheadTokenIterator(tit, 3)
        val parser = SPARQLParser(ltit)
        val astNode = parser.expr()
        val lopNode = astNode.visit(OperatorGraphVisitor(q)) // Log Operatorgraph
        var tmp = mutableListOf<IOPBase>()
        var tmp2 = LogicalOptimizer(q).optimizeCall(lopNode, {}, { tmp.add(it.cloneOP()) }) // Log Operatorgraph Optimized
        var result = mutableListOf<String>()
        // Change the UUIDs in each step beginning from 1 via DFS.
        for (i in tmp) {
            traverseNetwork(i, mutableMapOf<IOPBase, Int>())
            result.add(getJsonData(i))
        }
        return result.toTypedArray()
    }

    // Input: (Sub)-Tree of the Query
    // Output: Node and Edge Information as String for each node of the tree
    //
    // Receives a node as IOPBase and calls sub methods to determine the Node and
    // Edge data as strings which are needed for the visualization framework.
    private fun getJsonData(baum: IOPBase): String {
        var x = baum
        // Calling traverseNetwork method to change the UUIDs via DFS.
        var map = mutableMapOf<IOPBase, Int>() // define empty map
        map = traverseNetwork(x, map)

        // Sub-String creation for all Node Data
        var node = "["
        node += createNodeJson(x, map)
        node = node.substring(0, node.length - 1).toString()
        node += "]"

        // Sub-String creation for all Edge Data
        var edge = "["
        for (i in x.getChildren()) {
            edge += createEdgeJson(i, map[x], map)
        }

        edge = edge.substring(0, edge.length - 1).toString()
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
    public fun traverseNetwork(teilbaum: IOPBase, mutableMap: MutableMap<IOPBase, Int>): MutableMap<IOPBase, Int> {
        var hashMap = mutableMap
        var x = teilbaum
        // Node already included (node is multiple times within the tree)
        if (mutableMap.contains(x)) {
            var tmp = x.cloneOP()
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
        var x = teilbaum
        var map = mutableMap
        var toId = uuid // UUID of the node that is connected to this node (x)
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
        var x = teilbaum
        var map = mutableMap
        var result = String()
        var label = String()
        try {
            // AOPVariable and AOPConstant have a different structure as IOPBase
            // -> Different variables are containing the needed information for visualization
            if (x is AOPVariable) {
                label = "\"label\": \"${x.getClassname()} ${x.getUUID()}\\n${
                "?" + x.getName().replace("\n", "").replace("\"", "\\\"")
                }\""
            } else if (x is AOPConstant) {
                label = "\"label\": \"${x.getClassname()} ${x.getUUID()}\\n${
                x.toSparql().replace("\n", "").replace("\"", "\\\"")
                }\""
                // In general: All IOPBase nodes
            } else {
                label = "\"label\": \"${x.getClassname()} ${x.getUUID()}\\n${
                x.getProvidedVariableNames().toString().replace("\n", "").replace("\"", "\\\"")
                }\""
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
}

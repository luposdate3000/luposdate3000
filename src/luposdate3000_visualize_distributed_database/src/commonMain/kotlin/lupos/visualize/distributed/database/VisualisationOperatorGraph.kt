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
package lupos.visualize.distributed.database

import lupos.shared.XMLElement

public class VisualisationOperatorGraph {
    internal companion object {
        internal const val distanceX = 20.0
        internal const val distanceY = 20.0
        internal const val radius = 8.0
        internal const val layerNode = 1
        internal const val layerNodeText = 2
        internal const val layerConnection = 0
        internal const val treshhold = 0.001
    }
    public val nodes: MutableList<MutableList<VisualisationOperatorGraphNode>> = mutableListOf<MutableList<VisualisationOperatorGraphNode>>()
    public var minX: Double = 0.0
    public var minY: Double = 0.0
    public var maxX: Double = 0.0
    public var maxY: Double = 0.0
    public fun prepareOperatorGraph(op: XMLElement) {
        operatorGraphToNodes(op, 0, nodes)
        for (i in 0 until nodes.size) {
            for (j in 0 until nodes[i].size) {
                val n = nodes[i][j]
                n.x = j * distanceX
                n.y = i * distanceY
                if (maxY <n.y) {
                    maxY = n.y
                }
                if (maxX <n.x) {
                    maxX = n.x
                }
            }
        }
        var flag = true
        while (flag) {
            flag = false
            for (i in 0 until nodes.size) {
                for (j in 1 until nodes[i].size) {
                    val n = nodes[i][j]
                    val nl = nodes[i][j - 1]
                    if (nl.x + distanceY> n.x + treshhold) {
                        n.x = nl.x + distanceY
                        if (maxX <n.x) {
                            maxX = n.x
                        }
                        flag = true
                    }
                }
            }
            var i = nodes.size
            while (i > 0) {
                i--
                for (n in nodes[i]) {
                    flag = flag || assignCoordinates(n, nodes)
                }
            }
        }
    }
    public fun toImage(): ImageHelper {
        val res = ImageHelper()
        res.createClass(
            "operator-node",
            mapOf(
                "fill" to "#FFFFFF",
                "stroke" to "#000000",
            )
        )
        res.createClass(
            "operator-connection",
            mapOf(
                "stroke" to "#000000",
            )
        )
        res.setZeroSize()
        for (nn in nodes) {
            for (n in nn) {
                res.addCircle(layerNode, n.x, n.y, radius, mutableListOf("operator-node"))
                val key = if (n.key.size> 0) {
                    n.key.toString()
                } else {
                    ""
                }
                res.addText(layerNodeText, n.x, n.y, n.tag + key, mutableListOf())
                for (a in n.above) {
                    res.addLine(layerConnection, a.x, a.y, n.x, n.y, mutableListOf("operator-connection"))
                }
            }
        }
        return res
    }
    public fun operatorGraphToImage(op: XMLElement): ImageHelper {
        prepareOperatorGraph(op)
        return toImage()
    }

    private fun assignCoordinates(n: VisualisationOperatorGraphNode, nodes: MutableList<MutableList<VisualisationOperatorGraphNode>>): Boolean {
        var flag = false
        val a = n.above.size> 0 && n.above.map { it.below }.flatten().toSet().size == 1
        val b = n.below.size> 0 && n.below.map { it.above }.flatten().toSet().size == 1
        if (a && (!b || n.above.size >= n.below.size)) {
            var xl = n.above.first().x
            var xr = n.above.last().x
            var x = xl + (xr - xl) * 0.5
            if (x> n.x + treshhold) {
                n.x = x
                if (maxX <n.x) {
                    maxX = n.x
                }
                flag = true
            }
        }
        if (b && (!a || n.below.size> n.above.size)) {
            var xl = n.below.first().x
            var xr = n.below.last().x
            var x = xl + (xr - xl) * 0.5
            if (x> n.x + treshhold) {
                n.x = x
                if (maxX <n.x) {
                    maxX = n.x
                }
                flag = true
            }
        }
        return flag
    }

    private fun filterChilds(childs: List<XMLElement>): List<XMLElement> {
        val res = mutableListOf<XMLElement>()
        for (c in childs) {
            when (c.tag) {
                "columnProjectionOrders", "projectedVariables", "bindings", "columnProjectionOrderElement", "by", "sparam", "pparam", "oparam", "partitionDistributionProvideKey", "partitionDistributionReceiveKey", "idx" -> {}
                "children", "POPDebug" -> {
                    res.addAll(filterChilds(c.childs))
                }
                else -> {
                    res.add(c)
                }
            }
        }
        return res
    }

    private fun operatorGraphToNodes(op: XMLElement, layer: Int, nodes: MutableList<MutableList<VisualisationOperatorGraphNode>>): VisualisationOperatorGraphNode {
        val below = mutableListOf<VisualisationOperatorGraphNode>()
        val above = mutableListOf<VisualisationOperatorGraphNode>()
        val key: List<String>
        if (op.tag.contains("DistributedSend")) {
            key = op.childs.filter { it.tag == "partitionDistributionProvideKey" }.map { it.attributes["key"]!! }
        } else if (op.tag.contains("DistributedReceive")) {
            key = op.childs.filter { it.tag == "partitionDistributionReceiveKey" }.map { it.attributes["key"]!! }
        } else {
            key = listOf()
        }
        val node = VisualisationOperatorGraphNode(layer, below, above, op.tag, key)
        node.y = layer * distanceY
        while (nodes.size <= layer) {
            nodes.add(mutableListOf())
        }
        nodes[layer].add(node)
        for (c in filterChilds(op.childs)) {
            val cc = operatorGraphToNodes(c, layer + 1, nodes)
            above.add(cc)
            cc.below.add(node)
        }
        return node
    }
}

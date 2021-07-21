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

import lupos.shared.SanityCheck
import lupos.shared.XMLElement
import kotlin.math.sqrt

public class VisualisationOperatorGraph {
    internal companion object {
        internal var idcounter = 0
        internal const val distanceX = 40.0
        internal const val distanceY = 40.0
        internal const val radius = 16.0
        internal const val layerConnection = 1
        internal const val layerNode = 2
        internal const val layerNodeText = 3
        internal const val treshhold = 0.001
        internal const val maxMove = 5.0
        internal const val minMove = 0.1
    }
    internal val id = idcounter++
    public val nodes: MutableList<MutableList<VisualisationOperatorGraphNode>> = mutableListOf<MutableList<VisualisationOperatorGraphNode>>()
    public var minX: Double = -radius
    public var minY: Double = -radius
    public var maxX: Double = +radius
    public var maxY: Double = +radius
    public var offsetX: Double = 0.0
    public var offsetY: Double = 0.0
    public var anchorX: Double = 0.0
    public var anchorY: Double = 0.0
    public fun getRadius(): Double {
        return sqrt((maxX - minX) * (maxX - minX) + (maxY - minY) * (maxY - minY))
    }
    public fun prepareOperatorGraph(op: XMLElement) {
        operatorGraphToNodes(op, 0, nodes, listOf())
        for (i in 0 until nodes.size) {
            for (j in 0 until nodes[i].size) {
                val n = nodes[i][j]
                n.x = j * distanceX
                n.y = i * distanceY
                if (maxY <n.y + radius) {
                    maxY = n.y + radius
                }
                if (maxX <n.x + radius) {
                    maxX = n.x + radius
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
                        if (maxX <n.x + radius) {
                            maxX = n.x + radius
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
    public fun toImage(image: ImageHelper, layerOffset: Int) {
        image.createClass(
            "operator-node",
            mapOf(
                "fill" to "#FFFFFF",
                "stroke" to "#000000",
            )
        )
        image.createClass(
            "operator-connection",
            mapOf(
                "stroke" to "#000000",
            )
        )
        image.createClass(
            "fill-yellow",
            mapOf(
                "fill" to "#FFFF00",
            )
        )
        val myOffsetX = offsetX - (maxX - minX) * 0.5
        val myOffsetY = offsetY - (maxY - minY) * 0.5
        image.addRect(layerOffset, minX + myOffsetX, minY + myOffsetY, maxX + myOffsetX, maxY + myOffsetY, mutableListOf("fill-yellow"))
        for (nn in nodes) {
            for (n in nn) {
                image.addCircle(layerOffset + layerNode, n.x + myOffsetX, n.y + myOffsetY, radius, mutableListOf("operator-node"))
                val key2 = if (n.key.size> 0) {
                    n.key.toString()
                } else {
                    ""
                }
                val label = when (n.op.tag) {
                    "POPProjection" -> "\u03C0"
                    "POPJoinMerge", "POPJoinMergeSingleColumn" -> "\u2A1D"
                    "POPGroup" -> "G"
                    "POPDistributedSendSingle" -> "\u2191" + key2
                    "POPDistributedReceiveSingle" -> "\u2193" + key2
                    "POPTripleStoreIterator" -> {
                        SanityCheck.check(
                            { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_visualize_distributed_database/src/commonMain/kotlin/lupos/visualize/distributed/database/VisualisationOperatorGraph.kt:127"/*SOURCE_FILE_END*/ },
                            { n.parentKeys.size <= 1 }
                        )
                        if (n.parentKeys.size == 1) {
                            val pkey = n.parentKeys.first()
                            val parr = pkey.split("=")
                            SanityCheck.check(
                                { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_visualize_distributed_database/src/commonMain/kotlin/lupos/visualize/distributed/database/VisualisationOperatorGraph.kt:134"/*SOURCE_FILE_END*/ },
                                { parr.size == 2 }
                            )
                            val idxName = parr[1]
                            val desc = n.op["idx"]!!["TripleStoreIndexDescription"]!!
                            val patternUsed = desc.attributes["pattern"]!!
                            val hostUsed = desc.attributes["hostname$idxName"]!!
                            val keyUsed = desc.attributes["key$idxName"]!!
                            "!$patternUsed@$hostUsed:$keyUsed!"
                        } else {
                            "!!"
                        }
                    }
                    else -> n.op.tag
                }
                image.addText(layerOffset + layerNodeText, n.x + myOffsetX, n.y + myOffsetY, label, mutableListOf())
                for (a in n.above) {
                    image.addLine(layerOffset + layerConnection, a.x + myOffsetX, a.y + myOffsetY, n.x + myOffsetX, n.y + myOffsetY, mutableListOf("operator-connection"))
                }
            }
        }
    }
    public fun operatorGraphToImage(op: XMLElement): ImageHelper {
        prepareOperatorGraph(op)
        val res = ImageHelper()
        res.setZeroSize()
        toImage(res, 0)
        return res
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
                if (maxX <n.x + radius) {
                    maxX = n.x + radius
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
                if (maxX <n.x + radius) {
                    maxX = n.x + radius
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
                "columnProjectionOrders", "projectedVariables", "bindings", "columnProjectionOrderElement", "by", "sparam", "pparam", "oparam", "partitionDistributionProvideKey", "partitionDistributionReceiveKey" -> {}
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

    private fun operatorGraphToNodes(op: XMLElement, layer: Int, nodes: MutableList<MutableList<VisualisationOperatorGraphNode>>, parentKeys: List<String>): VisualisationOperatorGraphNode {
        val below = mutableListOf<VisualisationOperatorGraphNode>()
        val above = mutableListOf<VisualisationOperatorGraphNode>()
        val key =
            if (op.tag.contains("DistributedSend")) {
                op.childs.filter { it.tag == "partitionDistributionProvideKey" }.map { it.attributes["key"]!! }
            } else if (op.tag.contains("DistributedReceive")) {
                op.childs.filter { it.tag == "partitionDistributionReceiveKey" }.map { it.attributes["key"]!! }
            } else {
                listOf()
            }
        val node = VisualisationOperatorGraphNode(layer, below, above, op, key, parentKeys)
        node.y = layer * distanceY
        while (nodes.size <= layer) {
            nodes.add(mutableListOf())
        }
        nodes[layer].add(node)
        if (!op.tag.contains("POPTripleStoreIterator")) {
            for (c in filterChilds(op.childs)) {
                val cc = operatorGraphToNodes(c, layer + 1, nodes, parentKeys + key)
                above.add(cc)
                cc.below.add(node)
            }
        }
        return node
    }
}

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
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt

public class VisualisationOperatorGraph {
    internal companion object {
        internal var idcounter = 0
        internal const val visuallySplitReceiveMulti = false
        internal const val distanceX = 120.0
        internal const val distanceY = 40.0
        internal const val layerConnection = 1
        internal const val layerNodeText = 3
        internal const val layerOPConnection = 4
        internal const val treshhold = 0.001
        internal const val maxMove = 5.0
        internal const val minMove = 0.1
    }
    internal val id = idcounter++
    public val mapOfReceivers: MutableMap<String, Pair<Double, Double>> = mutableMapOf<String, Pair<Double, Double>>()
    public val mapOfSenders: MutableMap<String, Pair<Double, Double>> = mutableMapOf<String, Pair<Double, Double>>()
    public val nodes: MutableList<MutableList<VisualisationOperatorGraphNode>> = mutableListOf<MutableList<VisualisationOperatorGraphNode>>()
    public var minX: Double = -999999.0
    public var minY: Double = -999999.0
    public var maxX: Double = -999999.0
    public var maxY: Double = -999999.0
    public var offsetX: Double = 0.0
    public var offsetY: Double = 0.0
    public var anchorX: Double = 0.0
    public var anchorY: Double = 0.0
    private fun checkMinMax(x: Double, y: Double) {
        if (maxY <y + 0.5 * distanceY || maxY <-999990.0) {
            maxY = y + 0.5 * distanceY
        }
        if (maxX <x + 0.5 * distanceX || maxX <-999990.0) {
            maxX = x + 0.5 * distanceX
        }
        if (minY > y - 0.5 * distanceY || minY <-999990.0) {
            minY = y - 0.5 * distanceY
        }
        if (minX > x - 0.5 * distanceX || minX <-999990.0) {
            minX = x - 0.5 * distanceX
        }
    }
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
                checkMinMax(n.x, n.y)
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
                        checkMinMax(n.x, n.y)
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
    public fun myOffsetX(): Double = offsetX - (minX + maxX) * 0.5
    public fun myOffsetY(): Double = offsetY - (minY + maxY) * 0.5
    public fun toImage(
        image: ImageHelper,
        layerOffset: Int,
        mapOfSenders2: MutableMap<String, Pair<Double, Double>>?,
        mapOfReceivers2: MutableMap<String, Pair<Double, Double>>?,
    ) {
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
        val myOffsetX2 = myOffsetX()
        val myOffsetY2 = myOffsetY()
        image.addRect(layerOffset, minX + myOffsetX2, minY + myOffsetY2, maxX + myOffsetX2, maxY + myOffsetY2, mutableListOf("fill-yellow"))
        for (nn in nodes) {
            for (n in nn) {
                val key2 = if (n.key.size == 1) {
                    n.key.first()
                } else if (n.key.size> 1) {
                    val kk: Array<String> = n.key.toTypedArray()
                    val a = kk[0].split(":").map { it.split("=") }
                    val b = kk[1].split(":").map { it.split("=") }
                    var res = a[0][0]
                    for (i in 1 until a.size) {
                        res += ":" + a[i][0] + "="
                        if (a[i][1] != b[i][1]) {
                            res += "*"
                        } else {
                            res += a[i][1]
                        }
                    }
                    println("$a $b $res")
                    res
                } else {
                    ""
                }
                val label = when (n.op.tag) {
                    "POPProjection" -> "\u03C0"
                    "POPJoinMerge", "POPJoinMergeSingleColumn" -> "\u2A1D"
                    "POPGroup" -> "G"
                    "POPUnion" -> "\u222A"
                    "POPDistributedSendSingle", "POPDistributedSendMulti" -> {
                        for (k in n.key) {
                            mapOfSenders[k] = n.x to n.y
                        }
                        "\u2191" + key2
                    }
                    "POPDistributedReceiveSingle", "POPDistributedReceiveMulti" -> {
                        for (k in n.key) {
                            mapOfReceivers[k] = n.x to n.y
                        }
                        "\u2193" + key2
                    }
                    "POPTripleStoreIterator" -> {
                        SanityCheck.check(
                            { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_visualize_distributed_database/src/commonMain/kotlin/lupos/visualize/distributed/database/VisualisationOperatorGraph.kt:171"/*SOURCE_FILE_END*/ },
                            { n.parentKeys.size <= 1 }
                        )
                        if (n.parentKeys.size == 1) {
                            val pkey = n.parentKeys.first()
                            val parr = pkey.split("=")
                            SanityCheck.check(
                                { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_visualize_distributed_database/src/commonMain/kotlin/lupos/visualize/distributed/database/VisualisationOperatorGraph.kt:178"/*SOURCE_FILE_END*/ },
                                { parr.size == 2 }
                            )
                            val idxName = parr[1]
                            val desc = n.op["idx"]!!["TripleStoreIndexDescription"]!!
                            val patternUsed = desc.attributes["pattern"]!!
                            val hostUsed = desc.attributes["hostname$idxName"]!!
                            val keyUsed = desc.attributes["key$idxName"]!!
                            "S[$patternUsed@$hostUsed:$keyUsed]"
                        } else {
                            "S"
                        }
                    }
                    else -> {
                        if (n.op.tag.contains("Send") || n.op.tag.contains("Receive")) {
                            TODO(n.op.tag)
                        }
                        n.op.tag
                    }
                }
                image.addText(layerOffset + layerNodeText, n.x + myOffsetX2, n.y + myOffsetY2, label, mutableListOf())
                for (a in n.above) {
                    val x1 = a.x + myOffsetX2
                    val y1 = a.y + myOffsetY2
                    val x2 = n.x + myOffsetX2
                    val y2 = n.y + myOffsetY2
                    val w = (x2 - x1)
                    val h = (y2 - y1)
                    val l1 = sqrt(w * w + h * h)
                    val x0 = (x1 + x2) / 2.0
                    val y0 = (y1 + y2) / 2.0
                    val a = atan2(h, w)
                    val radius = sqrt(cos(a) * 0.5 * distanceX * cos(a) * 0.5 * distanceX + sin(a) * 0.5 * distanceY * sin(a) * 0.5 * distanceY)
                    var l2 = l1 - radius
                    if (l2 <0.0) {
                        l2 = 0.0
                    }
                    val w0 = w / l1 * l2 * 0.5
                    val h0 = h / l1 * l2 * 0.5
                    image.addLine(layerOffset + layerConnection, x0 - w0, y0 - h0, x0 + w0, y0 + h0, mutableListOf("operator-connection"))
                }
            }
        }
        if (mapOfSenders2 != null && mapOfReceivers2 != null) {
            if (mapOfSenders2.size != mapOfReceivers2.size || !mapOfSenders2.keys.toSet().containsAll(mapOfReceivers2.keys.toSet())) {
                println("e: FAIL visualisation Problem :: ${ mapOfSenders2.size} ${mapOfReceivers2.size} ${mapOfSenders2.keys} ${mapOfReceivers2.keys}")
            }
            for ((k, s) in mapOfSenders2) {
                val d = mapOfReceivers2[k]
                if (d != null) {
                    image.addLine(layerOffset + layerOPConnection, s.first, s.second, d.first, d.second, mutableListOf("operator-connection"))
                }
            }
        }
    }
    public fun operatorGraphToImage(op: XMLElement): ImageHelper {
        prepareOperatorGraph(op)
        val res = ImageHelper()
        res.setZeroSize()
        toImage(res, 0, null, null)
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
                checkMinMax(n.x, n.y)
                flag = true
            }
        }
        if (b && (!a || n.below.size> n.above.size)) {
            var xl = n.below.first().x
            var xr = n.below.last().x
            var x = xl + (xr - xl) * 0.5
            if (x> n.x + treshhold) {
                n.x = x
                checkMinMax(n.x, n.y)
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
        val key = (op.childs.filter { it.tag == "partitionDistributionProvideKey" }.map { it.attributes["key"]!! } + op.childs.filter { it.tag == "partitionDistributionReceiveKey" }.map { it.attributes["key"]!! }).toSet().toList()
        if (op.tag == "POPDistributedReceiveMulti" && visuallySplitReceiveMulti) {
            val node = VisualisationOperatorGraphNode(layer, below, above, XMLElement("POPUnion"), listOf(), parentKeys)
            node.y = layer * distanceY
            while (nodes.size <= layer + 1) {
                nodes.add(mutableListOf())
            }
            nodes[layer].add(node)
            for (k in key) {
                val below2 = mutableListOf<VisualisationOperatorGraphNode>()
                val above2 = mutableListOf<VisualisationOperatorGraphNode>()
                val node2 = VisualisationOperatorGraphNode(layer + 1, below2, above2, XMLElement("POPDistributedReceiveSingle"), listOf(k), parentKeys)
                above.add(node2)
                below2.add(node)
                nodes[layer + 1].add(node2)
            }
            return node
        } else {
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
}

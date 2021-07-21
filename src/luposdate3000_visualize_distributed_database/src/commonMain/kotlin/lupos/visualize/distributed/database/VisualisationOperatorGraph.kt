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
        internal var idcounter = 0
        internal const val distanceX = 80.0
        internal const val distanceY = 80.0
        internal const val radius = 30.0
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
    public fun verifyNoCollision(other: VisualisationOperatorGraph): Boolean {
        val myW = (maxX - minX) * 0.5
        val myH = (maxY - minY) * 0.5
        val otherW = (other.maxX - other.minX) * 0.5
        val otherH = (other.maxY - other.minY) * 0.5

        val myX1 = offsetX - myW
        val myY1 = offsetY - myH
        val otherX1 = other.offsetX - otherW
        val otherY1 = other.offsetY - otherH
        val myX2 = offsetX + myW
        val myY2 = offsetY + myH
        val otherX2 = other.offsetX + otherW
        val otherY2 = other.offsetY + otherH

        val d = doubleArrayOf(
            myX2 - otherX1 + distanceX,
            myY2 - otherY1 + distanceY,
            otherX2 - myX1 + distanceX,
            otherY2 - myY1 + distanceY,
        )
        for (v in d) {
            if (v <0.0) {
                return false
            }
        }
        var minI = 0
        for (i in 0 until d.size) {
            if (d[i] <d[minI]) {
                minI = i
            }
            d[i] = d[i] * 0.5
            if (d[i]> maxMove) {
                d[i] = maxMove
            }
        }
        println("dist ${d[minI]}")
        when (minI) {
            0 -> {
                offsetX -= d[minI]
                other.offsetX += d[minI]
            }
            1 -> {
                offsetY -= d[minI]
                other.offsetY += d[minI]
            }
            2 -> {
                offsetX += d[minI]
                other.offsetX -= d[minI]
            }
            3 -> {
                offsetY += d[minI]
                other.offsetY -= d[minI]
            }
        }
        return d[minI]> minMove
    }
    public fun prepareOperatorGraph(op: XMLElement) {
        operatorGraphToNodes(op, 0, nodes)
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
                val key = if (n.key.size> 0) {
                    n.key.toString()
                } else {
                    ""
                }
                image.addText(layerOffset + layerNodeText, n.x + myOffsetX, n.y + myOffsetY, n.tag + key, mutableListOf())
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

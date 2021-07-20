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

public object VisualisationOperatorGraph {

    private const val distanceX = 20.0
    private const val distanceY = 20.0
    private const val radius = 20.0
    private const val layerNode = 1
    private const val layerConnection = 0
    public fun operatorGraphToImage(op: XMLElement): ImageHelper {
        val res = ImageHelper()
        res.setZeroSize()
        val nodes = mutableListOf<MutableList<VisualisationOperatorGraphNode>>()
        operatorGraphToNodes(op, 0, nodes)
        var n = nodes[0][0]
        n.x = 0.0
        while (n.above.size > 0) {
            n = n.above[0]
            n.x = 0.0
        }
        var flag = true
        while (flag) {
            flag = false
            var i = nodes.size
            while (i > 0) {
                i--
                for (n in nodes[i]) {
                    flag = flag || assignCoordinates(n, nodes)
                }
            }
        }
        for (nn in nodes) {
            for (n in nn) {
                res.addCircle(layerNode, n.x, n.y, radius, mutableListOf(""))
                for (a in n.above) {
                    res.addLine(layerConnection, a.x, a.y, n.x, n.y, mutableListOf(""))
                }
            }
        }
        return res
    }

    private fun assignCoordinates(n: VisualisationOperatorGraphNode, nodes: MutableList<MutableList<VisualisationOperatorGraphNode>>): Boolean {
        var flag = false
        if (n.above.size > 0) {
            var l = n.above.first().x
            var r = n.above.last().x
            if (l >= 0) {
                for (a in n.above) {
                    flag = flag || a.x != l
                    a.x = l
                    l += distanceX
                }
            }
            if (r != l) {
                l = n.above.first().x
                n.x = l + (r - l) * 0.5
                for (b in n.below) {
                    b.x = -99999999.0
                }
                for (nni in 0 until nodes[n.layer + 1].size) {
                    val nn = nodes[n.layer + 1][nni]
                    if (nn == n.above.last()) {
                        for (i in nni + 1 until nodes[n.layer + 1].size) {
                            val nn2 = nodes[n.layer + 1][i]
                            if (nn2.x >= 0 && nn2.x <r + distanceX) {
                                nn2.x = r + distanceX
                                r += distanceX
                                for (a in nn2.above) {
                                    a.x = -99999999.0
                                }
                                for (b in nn2.below) {
                                    b.x = -99999999.0
                                }
                            }
                        }
                        break
                    }
                }
            }
        }
        return flag
    }

    private fun operatorGraphToNodes(op: XMLElement, layer: Int, nodes: MutableList<MutableList<VisualisationOperatorGraphNode>>): VisualisationOperatorGraphNode {
        val below = mutableListOf<VisualisationOperatorGraphNode>()
        val above = mutableListOf<VisualisationOperatorGraphNode>()
        val node = VisualisationOperatorGraphNode(layer, below, above)
        node.y = layer * distanceY
        while (nodes.size <= layer) {
            nodes.add(mutableListOf())
        }
        nodes[layer].add(node)
        for (c in op.childs) {
            val cc = operatorGraphToNodes(c, layer + 1, nodes)
            above.add(cc)
            cc.below.add(node)
        }
        return node
    }

    internal class VisualisationOperatorGraphNode(val layer: Int, val below: MutableList<VisualisationOperatorGraphNode>, val above: MutableList<VisualisationOperatorGraphNode>) {
        var x: Double = -99999999.0
        var y: Double = -99999999.0
    }
}

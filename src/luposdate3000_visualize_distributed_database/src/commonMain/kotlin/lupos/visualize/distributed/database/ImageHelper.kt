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

import kotlin.math.abs
import kotlin.math.sqrt

public class ImageHelper {
    private val layers = mutableListOf(mutableSetOf<String>())
    private val classes = mutableMapOf<String, MutableMap<String, String>>()

    internal var margin: Double = 50.0
    internal var width: Double = 8000.0
    internal var height: Double = 8000.0
    internal var minX: Double = margin
    internal var maxX: Double = width - margin
    internal var minY: Double = margin
    internal var maxY: Double = height - margin

    public fun setZeroSize() {
        minX = -99999999.0
        minY = -99999999.0
        maxX = -99999999.0
        maxY = -99999999.0
    }

    private fun adjustBordersToPoint(x: Double, y: Double) {
        if (minX > x || minX == -99999999.0) {
            minX = x
        }
        if (minY > y || minY == -99999999.0) {
            minY = y
        }
        if (maxX < x || maxX == -99999999.0) {
            maxX = x
        }
        if (maxY < y || maxY == -99999999.0) {
            maxY = y
        }
    }

    public fun deepCopy(): ImageHelper {
        val res = ImageHelper()
        var i = 0
        for (layer in layers) {
            res.layers.add(mutableSetOf())
            res.layers[i].clear()
            res.layers[i].addAll(layer)
            i++
        }
        res.classes.clear()
        for ((k, v) in classes) {
            val m = mutableMapOf<String, String>()
            res.classes[k] = m
            m.putAll(v)
        }
        return res
    }

    public fun createClass(name: String, attributes: Map<String, String>) {
        var m = classes[name]
        if (m == null) {
            m = mutableMapOf()
            classes[name] = m
        }
        m.putAll(attributes)
    }

    private fun checkLayer(layer: Int) {
        while (layers.size <= layer) {
            layers.add(mutableSetOf())
        }
    }

    private fun classString(classes: List<String>): String {
        return if (classes.isEmpty()) {
            ""
        } else {
            " class=\"${classes.joinToString(" ")}\""
        }
    }

    public fun addText(layer: Int, x: Double, y: Double, text: String, classes: List<String>) {
        checkLayer(layer)
        adjustBordersToPoint(x, y)
        layers[layer].add("    <text x=\"$x\" y=\"$y\" writing-mode=\"lr\" glyph-orientation-horizontal=\"90\" text-anchor=\"middle\" alignment-baseline=\"middle\" dominant-baseline=\"central\"${classString(classes)} >$text</text>")
    }

    public fun addCircle(layer: Int, cx: Double, cy: Double, r: Double, classes: List<String>) {
        checkLayer(layer)
        adjustBordersToPoint(cx - r, cy - r)
        adjustBordersToPoint(cx + r, cy + r)
        layers[layer].add("    <circle cx=\"$cx\" cy=\"$cy\" r=\"$r\"${classString(classes)} />")
    }

    public fun addRect(layer: Int, x1: Double, y1: Double, x2: Double, y2: Double, classes: List<String>) {
        checkLayer(layer)
        adjustBordersToPoint(x1, y1)
        adjustBordersToPoint(x2, y2)
        layers[layer].add("    <rect x=\"$x1\" y=\"$y1\" width=\"${x2 - x1}\" height=\"${y2 - y1}\"${classString(classes)} />")
    }

    public fun addLine(layer: Int, x1: Double, y1: Double, x2: Double, y2: Double, classes: List<String>) {
        checkLayer(layer)
        adjustBordersToPoint(x1, y1)
        adjustBordersToPoint(x2, y2)
        layers[layer].add("    <line x1=\"$x1\" y1=\"$y1\" x2=\"$x2\" y2=\"$y2\"${classString(classes)} />")
    }

    @Suppress("NOTHING_TO_INLINE")
    private fun getLength(p: Pair<Double, Double>): Double {
        return sqrt(p.first * p.first + p.second * p.second)
    }

    @Suppress("NOTHING_TO_INLINE")
    private fun setLength(p: Pair<Double, Double>, l: Double): Pair<Double, Double> {
        val f = l / getLength(p)
        return (p.first * f) to (p.second * f)
    }

    @Suppress("NOTHING_TO_INLINE")
    private fun getDirection(p1: Pair<Double, Double>, p2: Pair<Double, Double>): Pair<Double, Double> {
        return (p2.first - p1.first) to (p2.second - p1.second)
    }

    private fun rotate90Degree(p: Pair<Double, Double>): Pair<Double, Double> {
        return p.second to (-p.first)
    }

    @Suppress("NOTHING_TO_INLINE")
    private fun shortenPath(p: Pair<Double, Double>, a: Pair<Double, Double>, b: Pair<Double, Double>, dir: Pair<Double, Double>, len: Double): Pair<Double, Double> {
        val mov = rotate90Degree(setLength(dir, len))
        val p1 = p.first + mov.first to p.second + mov.second
        val p2 = p.first - mov.first to p.second - mov.second
        val l1 = getLength(getDirection(a, p1)) + getLength(getDirection(b, p1))
        val l2 = getLength(getDirection(a, p2)) + getLength(getDirection(b, p2))
        return if (abs(l2 - l1) < 20.0) { // sourth left first, if it does not matter
            if (getLength(getDirection(p1, 0.0 to 0.0)) < getLength(getDirection(p2, 0.0 to 0.0))) {
                p1
            } else {
                p2
            }
        } else if (l1 < l2) {
            p1
        } else {
            p2
        }
    }

    private inner class LocalPoint(val p: Pair<Double, Double>) {
        override fun equals(other: Any?): Boolean {
            return other is LocalPoint && getLength(getDirection(p, other.p)) < 1.0
        }

        override fun hashCode(): Int {
            return getLength(p).toInt()
        }
    }

    private var byPassMap = mutableMapOf<LocalPoint, Int>()
    public fun addPath(layer: Int, points: List<Pair<Double, Double>>, classes: List<String>, pointRadius: Double, minDistToOtherPath: Double) {
        if (points.size > 0) {
            checkLayer(layer)
            for ((first, second) in points) {
                adjustBordersToPoint(first - pointRadius - minDistToOtherPath, second - pointRadius - minDistToOtherPath)
                adjustBordersToPoint(first + pointRadius + minDistToOtherPath, second + pointRadius + minDistToOtherPath)
            }
            val directions = mutableListOf<Pair<Double, Double>>()
            val correctedPoints = mutableListOf<Pair<Double, Double>>()
            if (points.size == 1) {
                correctedPoints.add(points[0])
                correctedPoints.add(points[0])
                directions.add(points[0].first + 10 to points[0].second + 10)
                directions.add(points[0].first - 10 to points[0].second + 10)
            } else {
                for (i in 0 until points.size) {
                    val im = if (i > 0) i - 1 else i // i-1
                    val ip = if (i < points.size - 1) i + 1 else i // i+1
                    val dir = getDirection(points[im], points[ip])
                    directions.add(dir)
                    if (i == 0 || i == points.size - 1) {
                        correctedPoints.add(points[i])
                    } else {
                        val p = LocalPoint(points[i])
                        var c = byPassMap[p]
                        if (c == null) {
                            c = 1
                        } else {
                            c++
                        }
                        byPassMap[p] = c
                        correctedPoints.add(shortenPath(points[i], points[im], points[ip], dir, pointRadius + c * minDistToOtherPath))
                    }
                }
            }
            val (x, y) = correctedPoints.first()
            var s = "    <path d=\"M $x,$y"
            for (i in 0 until correctedPoints.size - 1) {
                val (x1, y1) = correctedPoints[i]
                val (x2, y2) = correctedPoints[i + 1]
                var len = getLength(getDirection(correctedPoints[i], correctedPoints[i + 1])) / 5.0 // 1/5 der strecke
                if (len < pointRadius * 2) { // minimum doppelter punkt-radius
                    len = pointRadius * 2
                }
                val (dx1, dy1) = setLength(directions[i], len)
                val (dx2, dy2) = setLength(directions[i + 1], len)
                val cx1 = x1 + dx1
                val cy1 = y1 + dy1
                val cx2 = x2 - dx2
                val cy2 = y2 - dy2
                s += " C $cx1,$cy1 $cx2,$cy2 $x2,$y2"
            }
            s += "\"${classString(classes)} />"
            layers[layer].add(s)
        }
    }

    override fun toString(): String {
        val buffer = StringBuilder()
        val x = minX - margin
        val y = minY - margin
        val w = maxX - minX + margin * 2
        val h = maxY - minY + margin * 2
        buffer.appendLine("<svg version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\" viewBox=\"$x $y $w ${h}\" >")
        buffer.appendLine("    <defs>")
        buffer.appendLine("        <marker id=\"arrowhead\" orient=\"auto\" markerWidth=\"2\" markerHeight=\"4\" refX=\"0.1\" refY=\"2\">")
        buffer.appendLine("            <path d=\"M0,0 V4 L2,2 Z\" fill=\"#000000\" />")
        buffer.appendLine("        </marker>")
        buffer.appendLine("    </defs>")
        buffer.appendLine("    <style type=\"text/css\">")
        for ((name, attrs) in classes) {
            buffer.appendLine("        .$name{")
            for ((k, v) in attrs) {
                buffer.appendLine("            $k:$v;")
            }
            buffer.appendLine("        }")
        }
        buffer.appendLine("    </style>")
        buffer.appendLine("    <rect x=\"$x\" y=\"$y\" width=\"$w\" height=\"$h\" fill=\"#FFFFFF\" />")
        for (layer in layers) {
            for (line in layer) {
                buffer.appendLine(line)
            }
        }

        buffer.appendLine("</svg>")
        return buffer.toString()
    }
}

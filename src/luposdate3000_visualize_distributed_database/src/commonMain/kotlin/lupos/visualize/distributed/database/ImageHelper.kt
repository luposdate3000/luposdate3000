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

public class ImageHelper {
    private val layers = mutableListOf(mutableSetOf<String>())
    private val classes = mutableMapOf<String, MutableMap<String, String>>()

    internal val margin = 50
    internal val width = 3000
    internal val height = 3000
    internal val minX: Int = margin
    internal val maxX: Int = width - margin
    internal val minY: Int = margin
    internal val maxY: Int = height - margin

    public fun deepCopy(): ImageHelper {
        val res = ImageHelper()
        var i = 0
        for (layer in layers) {
            res.layers.add(mutableSetOf<String>())
            res.layers[i].clear()
            res.layers[i].addAll(layer)
            i++
        }
        res.classes.clear()
        for ((k, v)in classes) {
            val m = mutableMapOf<String, String>()
            res.classes[k] = m
            m.putAll(v)
        }
        return res
    }

    public fun createClass(name: String, attributes: Map<String, String>) {
        var m = classes[name]
        if (m == null) {
            m = mutableMapOf<String, String>()
            classes[name] = m
        }
        m.putAll(attributes)
    }

    private fun checkLayer(layer: Int) {
        while (layers.size <= layer) {
            layers.add(mutableSetOf<String>())
        }
    }
    private fun classString(classes: List<String>): String {
        if (classes.size == 0) {
            return ""
        } else {
            return " class=\"${classes.joinToString(" ")}\""
        }
    }
    public fun addCircle(layer: Int, cx: Double, cy: Double, r: Double, classes: List<String>) {
        checkLayer(layer)
        layers[layer].add("    <circle cx=\"$cx\" cy=\"$cy\" r=\"$r\"${classString(classes)} />")
    }
    public fun addLine(layer: Int, x1: Double, y1: Double, x2: Double, y2: Double, classes: List<String>) {
        checkLayer(layer)
        layers[layer].add("    <line x1=\"$x1\" y1=\"$y1\" x2=\"$x2\" y2=\"$y2\"${classString(classes)} />")
    }

    public override fun toString(): String {
        val buffer = StringBuilder()
        buffer.appendLine("<svg version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\" viewBox=\"0 0 $width $height\" >")
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
        buffer.appendLine("    <rect width=\"100%\" height=\"100%\" fill=\"#FFFFFF\"/>")
        for (layer in layers) {
            for (line in layer) {
                buffer.appendLine(line)
            }
        }

        buffer.appendLine("</svg>")
        return buffer.toString()
    }
}

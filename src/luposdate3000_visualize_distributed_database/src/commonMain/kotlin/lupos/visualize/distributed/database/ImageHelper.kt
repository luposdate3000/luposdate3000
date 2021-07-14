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

public class ImageHelper{
private val layers=mutableListOf(mutableSetOf<String>())
private val classes=mutableMapOf<String,MutableMap<String,String>>()

public fun createClass(name:String,attributes:Map<String,String>){
var m=classes[name]
if(m==null){
m=mutableMapOf<String,String>()
classes[name]=m
}
m.putAll(attributes)
}

public fun addCircle(layer:Int,cx:Double,cy:Double,r:Double,classes:List<String>){
while(layers.size<layer){
layers.add(mutableSetOf<String>())
}
layers[layer].add("<circle cx=\"$cx\" cy=\"$cy\" r=\"$r\" class=\"${classes.joinToString(" ")}\" />")
}

override public fun toString():String{
val buffer=StringBuilder()
buffer.appendLine("<svg version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\">")

buffer.appendLine("    <style type=\"text/css\">")
for((name,attrs) in classes){
buffer.appendLine("        .$name{")
for((k,v) in attrs){
buffer.appendLine("            $k:$v;")
}
buffer.appendLine("        }")
}
buffer.appendLine("    </style>")


for(layer in layers){
for(line in layer){
buffer.appendLine(line)
}
}

buffer.appendLine("</svg>")
return buffer.toString()
}
}

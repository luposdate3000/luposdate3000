#!/usr/bin/env kotlin
import java.io.File
//args[0] enum name
//args[1] package name
//args[2] file base name

val mapping=mutableListOf<String>()
File(args[2]+".kt").printWriter().use { out ->
out.println("package ${args[1]}")
out.println("import kotlin.jvm.JvmField")
out.println("import lupos.s00misc.UnreachableException")
out.println("public typealias ${args[0]} = Int")
out.println("public object ${args[0]}Ext {")
var id=0
File(args[2]+".txt").forEachLine { 
out.println("    public const val $it : ${args[0]} = $id")
mapping.add(it)
id++
}
out.println("    public fun toName(id:Int):String{")
out.println("        return when(id) {")
for(i in 0 until id){
out.println("            $i -> \"${mapping[i]}\"")
}
out.println("            else -> throw UnreachableException()")
out.println("        }")
out.println("    }")
out.println("}")
}

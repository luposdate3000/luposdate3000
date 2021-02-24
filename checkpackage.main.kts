#!/usr/bin/env kotlin
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
import java.io.File

for (it in File("src").walk()) {
    val name = it.toString()
    try {
        if (name.endsWith(".kt") && name.contains("/kotlin/")) {
            var pkg = ""
            it.forEachLine { line ->
                if (line.startsWith("package ")) {
                    pkg = line.substring("package ".length)
                }
            }
            var folder = name.substring(name.indexOf("/kotlin/"), name.lastIndexOf("/"))
            if (!folder.endsWith("/")) {
                folder = folder + "/"
            }
            var desiredfolder = "/kotlin/" + pkg.replace(".", "/")
            if (!desiredfolder.endsWith("/")) {
                desiredfolder = desiredfolder + "/"
            }
            if (folder != desiredfolder) {
                val newname = name.replace(folder, desiredfolder)
                val newfolder = newname.substring(0, newname.lastIndexOf("/"))
                println("mkdir -p $newfolder")
                println("mv $name $newname")
            }
        }
    } catch (e: Throwable) {
        println("$name")
        e.printStackTrace()
    }
}

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
package launcher

import java.io.File
import java.nio.file.Files
import java.nio.file.Paths

val regexEnableSuspend = "/\\*suspend\\*/ ".toRegex()
val regexDisableSuspend = "(^|[^a-zA-Z])suspend ".toRegex()
public fun applySuspendEnable() {
    Files.walk(Paths.get("src.generated")).forEach { it ->
        val tmp = it.toString()
        if (tmp.endsWith(".kt")) {
            applySuspend(tmp, SuspendMode.Enable)
        }
    }
}

public fun applySuspendDisable() {
    Files.walk(Paths.get("src.generated")).forEach { it ->
        val tmp = it.toString()
        if (tmp.endsWith(".kt")) {
            applySuspend(tmp, SuspendMode.Disable)
        }
    }
}

public fun applySuspend(f: String, suspendMode: SuspendMode) {
    val fileSource = File(f)
    val fileTarget = File(f + ".tmp")
    fileTarget.printWriter().use { out ->
        fileSource.bufferedReader().readLines().forEach {
            when (suspendMode) {
                SuspendMode.Enable -> {
                    out.println(
                        it //
                            .replace(regexDisableSuspend, "\$1suspend ") //
                            .replace(regexEnableSuspend, "suspend "),
                    )
                }
                SuspendMode.Disable -> {
                    out.println(
                        it //
                            .replace(regexDisableSuspend, "\$1") //
                            .replace(regexEnableSuspend, ""),
                    )
                }
            }
        }
    }
    fileSource.delete()
    fileTarget.copyTo(fileSource)
    fileTarget.delete()
}

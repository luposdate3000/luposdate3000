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

val regexEnableNoInline = "/\\*noinline\\*/".toRegex()
val regexEnableInline = "/\\*inline\\*/".toRegex()
val regexEnableCrossInline = "/\\*crossinline\\*/".toRegex()
val regexDisableNoInline = "(^|[^a-zA-Z])noinline ".toRegex()
val regexDisableInline = "(^|[^a-zA-Z])inline ".toRegex()
val regexDisableCrossInline = "(^|[^a-zA-Z])crossinline ".toRegex()
public fun applyInlineEnable() {
    Files.walk(Paths.get("src.generated")).forEach { it ->
        val tmp = it.toString()
        if (tmp.endsWith(".kt")) {
            applyInline(tmp, InlineMode.Enable)
        }
    }
}

public fun applyInlineDisable() {
    Files.walk(Paths.get("src.generated")).forEach { it ->
        val tmp = it.toString()
        if (tmp.endsWith(".kt")) {
            applyInline(tmp, InlineMode.Disable)
        }
    }
}

public fun applyInline(f: String, inlineMode: InlineMode) {
    val fileSource = File(f)
    val fileTarget = File(f + ".tmp")
    fileTarget.printWriter().use { out ->
        fileSource.bufferedReader().readLines().forEach {
            when (inlineMode) {
                InlineMode.Enable -> {
                    out.println(
                        it //
                            .replace(regexDisableNoInline, "\$1noinline ") //
                            .replace(regexDisableInline, "\$1inline ") //
                            .replace(regexDisableCrossInline, "\$1crossinline ") //
                            .replace(regexEnableNoInline, "noinline ") //
                            .replace(regexEnableInline, "inline ") //
                            .replace(regexEnableCrossInline, "crossinline "),
                    )
                }
                InlineMode.Disable -> {
                    out.println(
                        it //
                            .replace(regexDisableNoInline, "\$1") //
                            .replace(regexDisableInline, "\$1") //
                            .replace(regexDisableCrossInline, "\$1") //
                            .replace(regexEnableNoInline, "") //
                            .replace(regexEnableInline, "") //
                            .replace(regexEnableCrossInline, ""),
                    )
                }
            }
        }
    }
    fileSource.delete()
    fileTarget.copyTo(fileSource)
    fileTarget.delete()
}

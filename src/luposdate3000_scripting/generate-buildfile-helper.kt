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

enum class ExecMode { RUN, HELP, GENERATE_PARSER, GENERATE_LAUNCHER, GENERATE_ENUMS, SETUP_GRADLE, UNKNOWN, SETUP_SPACLIENT }
enum class ParamClassMode { VALUES, NO_VALUE, FREE_VALUE }
enum class ReleaseMode { Enable, Disable }
enum class DryMode { Enable, Disable }
enum class TargetMode2 { JVM, JS, Native, All, JVM_JS }
enum class IntellijMode { Enable, Disable }
enum class SuspendMode { Enable, Disable }
enum class InlineMode { Enable, Disable }
enum class EDictionaryValueMode { Int, Long }

fun targetModeCompatible(base: TargetMode2, check: TargetMode2): Boolean {
    when (check) {
        TargetMode2.JVM -> return base == TargetMode2.JVM || base == TargetMode2.JVM_JS || base == TargetMode2.All
        TargetMode2.JS -> return base == TargetMode2.JS || base == TargetMode2.JVM_JS || base == TargetMode2.All
        TargetMode2.Native -> return base == TargetMode2.Native || base == TargetMode2.All
        else -> throw Exception("")
    }
}

fun fixPathNames(s: String): String {
    var res = s.trim()
    var back = ""
    while (back != res) {
        back = res
        res = res.replace("\\", "/").replace("/./", "/").replace("//", "/")
    }
    return res
}

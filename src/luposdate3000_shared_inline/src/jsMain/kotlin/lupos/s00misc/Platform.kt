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

package lupos.modulename
import lupos.s00misc.EOperatingSystemExt
internal actual object _Platform {
    val operatingSystem = EOperatingSystemExt.JS
    @Suppress("NOTHING_TO_INLINE") internal actual inline fun getOperatingSystem() = operatingSystem
    @Suppress("NOTHING_TO_INLINE") internal actual inline fun getUserHome(): String = throw Exception("not available on this platform")
    @Suppress("NOTHING_TO_INLINE") internal actual inline fun getPathSeparator(): String = throw Exception("not available on this platform")
    @Suppress("NOTHING_TO_INLINE") internal actual inline fun findNamedFileInDirectory(dir: String, name: String): List<String> = throw Exception("not available on this platform")
    @Suppress("NOTHING_TO_INLINE") internal actual inline fun getNullFileName(): String = throw Exception("not available on this platform")
    @Suppress("NOTHING_TO_INLINE") internal actual inline fun getEnv(key: String, default: String?): String? {
        return default
    }
    @Suppress("NOTHING_TO_INLINE") internal actual inline fun getBenchmarkHome(): String {
        return getEnv("LUPOS_BENCHMARK_HOME", "${getPathSeparator()}mnt")!!
    }
    @Suppress("NOTHING_TO_INLINE") internal actual inline fun getGradleCache(): String {
        return getEnv("LUPOS_GRADLE_CACHE", "${getUserHome()}${getPathSeparator()}.gradle${getPathSeparator()}caches${getPathSeparator()}")!!
    }
    @Suppress("NOTHING_TO_INLINE") internal actual inline fun getMavenCache(): String {
        return getEnv("LUPOS_MAVEN_CACHE", "${getUserHome()}${getPathSeparator()}.m2${getPathSeparator()}repository${getPathSeparator()}")!!
    }
    @Suppress("NOTHING_TO_INLINE") internal actual inline fun getAvailableRam(): Int {
        return getEnv("LUPOS_RAM", "60")!!.toInt()
    }
}

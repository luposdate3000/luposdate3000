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

import kotlinx.cinterop.toKString
import lupos.s00misc.EOperatingSystemExt
import platform.posix.getenv

internal actual object _Platform {
    val operatingSystem = EOperatingSystemExt.UNKNOWN

    @Suppress("NOTHING_TO_INLINE")
    internal actual inline fun getHostName(): String = throw Exception("not available on this platform")
    internal actual inline fun getOperatingSystem() = operatingSystem
    internal actual inline fun getUserHome(): String = throw Exception("not available on this platform")
    internal actual inline fun getPathSeparator(): String = throw Exception("not available on this platform")
    internal actual inline fun findNamedFileInDirectory(dir: String, name: String): List<String> = throw Exception("not available on this platform")
    internal actual inline fun getNullFileName(): String = "/dev/null"
    internal actual inline fun getEnv(key: String, default: String?): String? {
        val tmp = getenv(key)?.toKString()
        if (tmp != null) {
            return tmp
        }
        return default
    }

    internal actual inline fun getBenchmarkHome(): String {
        return getEnv("LUPOS_BENCHMARK_HOME", "${getPathSeparator()}mnt")!!
    }

    internal actual inline fun getGradleCache(): String {
        return getEnv("LUPOS_GRADLE_CACHE", "${getUserHome()}${getPathSeparator()}.gradle${getPathSeparator()}caches${getPathSeparator()}")!!
    }

    internal actual inline fun getMavenCache(): String {
        return getEnv("LUPOS_MAVEN_CACHE", "${getUserHome()}${getPathSeparator()}.m2${getPathSeparator()}repository${getPathSeparator()}")!!
    }

    internal actual inline fun getAvailableRam(): Int {
        return getEnv("LUPOS_RAM", "60")!!.toInt()
    }

    internal actual inline fun setShutdownHock(crossinline action: () -> Unit) {
        println("registering shutdown hook not implemented")
    }
}

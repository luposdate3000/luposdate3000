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
package lupos.shared.inline

import kotlinx.cinterop.toKString
import lupos.shared.EOperatingSystemExt
import platform.posix.getenv

public actual object Platform {
    val operatingSystem = EOperatingSystemExt.UNKNOWN

    @Suppress("NOTHING_TO_INLINE")
    public actual inline fun getHostName(): String = TODO("Platform")
    public actual inline fun getOperatingSystem() = operatingSystem
    public actual inline fun getUserHome(): String = TODO("Platform")
    public actual inline fun getPathSeparator(): String = TODO("Platform")
    public actual inline fun findNamedFileInDirectory(dir: String, name: String): List<String> = TODO("Platform")
    public actual inline fun getNullFileName(): String = "/dev/null"
    public actual inline fun getEnv(key: String, default: String?): String? {
        val tmp = getenv(key)?.toKString()
        if (tmp != null) {
            return tmp
        }
        return default
    }

    public actual inline fun getGradleCache(): String {
        return getEnv("LUPOS_GRADLE_CACHE", "${getUserHome()}${getPathSeparator()}.gradle${getPathSeparator()}caches${getPathSeparator()}")!!
    }

    public actual inline fun getMavenCache(): String {
        return getEnv("LUPOS_MAVEN_CACHE", "${getUserHome()}${getPathSeparator()}.m2${getPathSeparator()}repository${getPathSeparator()}")!!
    }

    public actual inline fun getAvailableRam(): Int {
        return getEnv("LUPOS_RAM", "4")!!.toInt()
    }

    public actual inline fun setShutdownHock(crossinline action: () -> Unit) {
    }
}

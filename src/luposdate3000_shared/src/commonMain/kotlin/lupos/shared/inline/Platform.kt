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

import lupos.shared.EOperatingSystem

internal expect object Platform {
    internal inline fun setShutdownHock(crossinline action: () -> Unit)
    internal inline fun getEnv(key: String, default: String? = null): String?
    internal inline fun getHostName(): String
    internal inline fun getUserHome(): String
    internal inline fun getPathSeparator(): String
    internal inline fun findNamedFileInDirectory(dir: String, name: String): List<String>
    internal inline fun getOperatingSystem(): EOperatingSystem
    internal inline fun getNullFileName(): String
    internal inline fun getGradleCache(): String
    internal inline fun getMavenCache(): String
    internal inline fun getAvailableRam(): Int
}

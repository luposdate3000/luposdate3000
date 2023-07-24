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

public expect object Platform {
    public inline fun setShutdownHock(crossinline action: () -> Unit)
    public inline fun getEnv(key: String, default: String? = null): String?
    public inline fun getHostName(): String
    public inline fun getUserHome(): String
    public inline fun getPathSeparator(): String
    public inline fun findNamedFileInDirectory(dir: String, name: String): List<String>
    public inline fun getOperatingSystem(): EOperatingSystem
    public inline fun getNullFileName(): String
    public inline fun getGradleCache(): String
    public inline fun getMavenCache(): String
    public inline fun getAvailableRam(): Int
}

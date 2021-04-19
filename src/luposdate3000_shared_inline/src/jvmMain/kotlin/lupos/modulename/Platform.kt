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
package lupos.shared_inline

import lupos.s00misc.EOperatingSystemExt
import java.io.BufferedReader
import java.io.File
import java.io.InputStreamReader
import kotlin.jvm.JvmField

internal actual object Platform {
    @JvmField
    val userHome: String = System.getProperty("user.home")
    val operatingSystem = if (System.getProperty("os.name").contains("Win")) EOperatingSystemExt.Windows else EOperatingSystemExt.Linux

    @JvmField
    val pathSepatator = if (operatingSystem == EOperatingSystemExt.Windows) "\\\\" else "/"

    @JvmField
    val nullFileName = if (operatingSystem == EOperatingSystemExt.Windows) "NUL" else "/dev/null"

    @Suppress("NOTHING_TO_INLINE")
    internal actual inline fun getHostName(): String {
        return BufferedReader(InputStreamReader(Runtime.getRuntime().exec("hostname").getInputStream())).readLine()
    }

    @Suppress("NOTHING_TO_INLINE")
    internal actual inline fun getOperatingSystem() = operatingSystem

    @Suppress("NOTHING_TO_INLINE")
    internal actual inline fun getUserHome() = userHome

    @Suppress("NOTHING_TO_INLINE")
    internal actual inline fun getPathSeparator() = pathSepatator

    @Suppress("NOTHING_TO_INLINE")
    internal actual inline fun findNamedFileInDirectory(dir: String, name: String): List<String> {
        val res = mutableListOf<String>()
        for (f in File(dir).walk()) {
            if (f.isFile()) {
                if (f.getName() == name) {
                    res.add(f.toString())
                }
            }
        }
        return res
    }

    @Suppress("NOTHING_TO_INLINE")
    internal actual inline fun getNullFileName(): String = nullFileName

    @Suppress("NOTHING_TO_INLINE")
    internal actual inline fun getEnv(key: String, default: String?): String? {
        return System.getenv(key) ?: default
    }

    @Suppress("NOTHING_TO_INLINE")
    internal actual inline fun getBenchmarkHome(): String {
        return getEnv("LUPOS_BENCHMARK_HOME", "${getPathSeparator()}mnt")!!
    }

    @Suppress("NOTHING_TO_INLINE")
    internal actual inline fun getGradleCache(): String {
        return getEnv("LUPOS_GRADLE_CACHE", "${getUserHome()}${getPathSeparator()}.gradle${getPathSeparator()}caches${getPathSeparator()}")!!
    }

    @Suppress("NOTHING_TO_INLINE")
    internal actual inline fun getMavenCache(): String {
        return getEnv("LUPOS_MAVEN_CACHE", "${getUserHome()}${getPathSeparator()}.m2${getPathSeparator()}repository${getPathSeparator()}")!!
    }

    @Suppress("NOTHING_TO_INLINE")
    internal actual inline fun getAvailableRam(): Int {
        return getEnv("LUPOS_RAM", "50")!!.toInt()
    }

    private var shutdownhock: () -> Unit = {}

    init {
        println("init shutdown hook")
        Runtime.getRuntime().addShutdownHook(object : Thread() {
            override fun run() {
                println("calling shutdown-hock")
                shutdownhock()
                println("called shutdown-hock")
            }
        })
    }

    internal actual inline fun setShutdownHock(crossinline action: () -> Unit) {
        println("registering shutdown hook")
        shutdownhock = {
            action()
        }
    }
}

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
package lupos.shared

import lupos.shared_inline.Platform
import kotlin.jvm.JvmField

@JvmField
public val LUPOS_BUFFER_SIZE: Int = Platform.getEnv("LUPOS_BUFFER_SIZE", "134217728")!!.toInt() // set this to at most 5% of your available RAM

@JvmField
public val LUPOS_REAL_WORLD_DATA_ROOT: String = Platform.getEnv("LUPOS_REAL_WORLD_DATA_ROOT", "/mnt/luposdate-testdata/")!! // set this to a huge storage device, to store your benchmark data

@JvmField
public val LUPOS_HOME: String = Platform.getEnv("LUPOS_HOME", "/tmp/luposdate3000/")!! // the root path, where the database stores its data

@JvmField
public val BUFFER_HOME: String = LUPOS_HOME + "/" + Platform.getEnv("LUPOS_PROCESS_ID", "0")!! + "/" // the root path, where the buffermanager stores its data

@JvmField
public val LUPOS_VISUALIZE: Boolean = Platform.getEnv("LUPOS_VISUALIZE", "false")!!.toBoolean() // if iterator data should be traced for the visualization

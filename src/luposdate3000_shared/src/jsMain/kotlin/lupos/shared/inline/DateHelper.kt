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

public actual class DateHelper {
    public actual constructor() {}

    @Suppress("NOTHING_TO_INLINE")
    public actual inline fun year(): Int = js("new Date()").getFullYear()

    @Suppress("NOTHING_TO_INLINE")
    public actual inline fun month(): Int = js("new Date()").getMonth()

    @Suppress("NOTHING_TO_INLINE")
    public actual inline fun day(): Int = js("new Date()").getDay()

    @Suppress("NOTHING_TO_INLINE")
    public actual inline fun hours(): Int = js("new Date()").getHours()

    @Suppress("NOTHING_TO_INLINE")
    public actual inline fun minutes(): Int = js("new Date()").getMinutes()

    @Suppress("NOTHING_TO_INLINE")
    public actual inline fun seconds(): Int = js("new Date()").getSeconds()
}

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

import lupos.s00misc.NotImplementedException

internal actual class _DateHelper {
    actual constructor() {}

    internal actual inline fun year(): Int = throw NotImplementedException("DateHelper", "year not implemented")
    internal actual inline fun month(): Int = throw NotImplementedException("DateHelper", "month not implemented")
    internal actual inline fun day(): Int = throw NotImplementedException("DateHelper", "day not implemented")
    internal actual inline fun hours(): Int = throw NotImplementedException("DateHelper", "hours not implemented")
    internal actual inline fun minutes(): Int = throw NotImplementedException("DateHelper", "minutes not implemented")
    internal actual inline fun seconds(): Int = throw NotImplementedException("DateHelper", "seconds not implemented")
}

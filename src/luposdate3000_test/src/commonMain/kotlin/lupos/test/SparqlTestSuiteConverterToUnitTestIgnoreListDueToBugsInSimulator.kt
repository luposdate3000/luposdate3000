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
package lupos.test
internal object SparqlTestSuiteConverterToUnitTestIgnoreListDueToBugsInSimulator {
    internal val ignoreListDueToBugsInSimulator = mapOf(
        "HOURS" to "distributed dictionary access",
        "MD5overUnicodedata" to "distributed dictionary access",
        "bind11BINDscopingVariableinfilterinscope" to "distributed dictionary access",
        "ADD1" to "query not distributed",
        "INSERT01" to "query not distributed",
        "MIN" to "distributed dictionary access",
        "MONTH" to "distributed dictionary access",
        "MOVE6" to "query not distributed",
        "STRLEN" to "distributed dictionary access",
        "ADD3" to "query not distributed",
        "FLOOR" to "distributed dictionary access",
        "INSERT03" to "query not distributed",
        "COUNT8b" to "distributed dictionary access",
        "ADD6" to "query not distributed",
        "MINUTES" to "distributed dictionary access",
    )
}

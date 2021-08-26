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
internal object SparqlTestSuiteConverterToUnitTestIgnoreListDueToTooSlow {
    internal val ignoreListDueToTooSlow = mapOf<String, String>( //
        "CLEARSILENTDEFAULT" to "too slow",
        "DELETEINSERT3b" to "too slow",
        "DELETEINSERT6" to "too slow",
        "jsonres02JSONResultFormat" to "too slow",
        "resourcesbsbmexplorequery82553sparql2553" to "too slow",
        "resourcessp2bq12b3sparql1294" to "too slow",
        "syntaxoneof01rq" to "too slow",
        "syntaxupdate13ru" to "too slow",
        "syntaxupdate14ru" to "too slow",
        "syntaxupdate35ru" to "too slow",
        "syntaxupdate37ru" to "too slow",
        "syntaxupdate40ru" to "too slow",
    )
}

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
    internal val ignoreList= mapOf<String, String>( //
        "bind03BIND" to "too slow",
        "INSERT01" to "too slow",
        "RAND" to "too slow",
        "resourcesmyqueriesoptional35sparql5" to "too slow",
        "resourcesmyqueriesoptional40sparql5" to "too slow",
        "resourcesmyqueriesoptional7sparql4" to "too slow",
        "resourcessp2bq11sparql21" to "too slow",
        "resourcessp2bq12csparql973" to "too slow",
        "resourcessp2bq5bsparql21" to "too slow",
        "resourcessp2bq66sparql32978" to "too slow",
        "ROUND" to "too slow",
    )
}

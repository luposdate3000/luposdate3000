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
internal object SparqlTestSuiteConverterToUnitTestIgnoreListDueToBugs {
    internal val ignoreListDueToBugs = mapOf<String, String>( //
        "ADD5" to "errors",
        "COUNT10" to "errors",
        "DELETEINSERT1c" to "errors",
        "DELETEINSERT9" to "errors",
        "Existswithoneconstant" to "errors",
        "jsonres04JSONResultFormat" to "unknown error",
        "Nestednegativeexistsinpositiveexists" to "errors",
        "NOTIN1" to "unknown error",
        "papersparqldlQ1rdfs" to "errors",
        "PositiveEXISTS1" to "errors",
        "resourcesmyqueriesoptional50sparql5" to "unknown error",
        "resourcesmyqueriesx1sparql5" to "unknown error",
        "resourcessp2bq12csparql32978" to "unknown error",
        "resourcessp2bq1sparql21" to "unknown error",
        "resourcessp2bq42sparql32978" to "unknown error",
        "resourcessp2bq92sparql21" to "errors",
        "SimpleDELETEDATA3" to "errors",
        "sparqldl12rqrangetest" to "errors",
        "UUIDpatternmatch" to "unknown error",
    )
}

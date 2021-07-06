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
internal object SparqlTestSuiteConverterToUnitTestIgnoreListDueToBugsResolveLater {
    internal val ignoreListDueToBugsResolveLater = mapOf(
        "csv03CSVResultFormat" to "Bug in CSV-Parser",
        "csv01CSVResultFormat" to "Bug in CSV-Parser",
        "cvs02CSVResultFormat" to "Bug in CSV-Parser",
        "SHA256onUnicodedata" to "Bug in SHA256-Function",
        "constructwhere04CONSTRUCTWHERE" to "Bug in SparqlTestSuiteConverterToUnitTest",
        "syntaxconstructwhere02rq" to "Bug in SparqlTestSuiteConverterToUnitTest",
        "bind01BINDfixeddataforOWLDL" to "Bug in OWL-Inference",
        "bind02BINDfixeddataforOWLDL" to "Bug in OWL-Inference",
        "bind03BINDfixeddataforOWLDL" to "Bug in OWL-Inference",
        "bind04BINDfixeddataforOWLDL" to "Bug in OWL-Inference",
        "bind05BINDfixeddataforOWLDL" to "Bug in OWL-Inference",
        "bind06BINDfixeddataforOWLDL" to "Bug in OWL-Inference",
        "bind07BINDfixeddataforOWLDL" to "Bug in OWL-Inference",
        "bind08BINDfixeddataforOWLDL" to "Bug in OWL-Inference",
        "synbad01rq" to "Bug in Error-detection during Query-Parsing",
        "synbad02rq" to "Bug in Error-detection during Query-Parsing",
        "synbad03rq" to "Bug in Error-detection during Query-Parsing",
        "synbadpname06" to "Bug in Error-detection during Query-Parsing",
        "syntaxBINDscope6rq" to "Bug in Error-detection during Query-Parsing",
        "syntaxBINDscope7rq" to "Bug in Error-detection during Query-Parsing",
        "syntaxBINDscope8rq" to "Bug in Error-detection during Query-Parsing",
        "syntaxSELECTscope2" to "Bug in Error-detection during Query-Parsing",
        "syntaxupdate32ru" to "Bug in Error-detection during Query-Parsing",
        "syntaxupdate33ru" to "Bug in Error-detection during Query-Parsing",
        "syntaxupdate34ru" to "Bug in Error-detection during Query-Parsing",
        "syntaxupdate36ru" to "Bug in Error-detection during Query-Parsing",
        "syntaxupdate54ru" to "Bug in Error-detection during Query-Parsing",
        "syntaxupdatebad03ru" to "Bug in Error-detection during Query-Parsing",
        "syntaxupdatebad04ru" to "Bug in Error-detection during Query-Parsing",
        "syntaxupdatebad08ru" to "Bug in Error-detection during Query-Parsing",
        "syntaxupdatebad09ru" to "Bug in Error-detection during Query-Parsing",
        "syntaxupdatebad10ru" to "Bug in Error-detection during Query-Parsing",
        "syntaxupdatebad11ru" to "Bug in Error-detection during Query-Parsing",
        "syntaxupdatebad12ru" to "Bug in Error-detection during Query-Parsing",
    )
}

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
        "DROPALL" to "distributed dictionary access",
        "IF" to "distributed dictionary access",
        "SUMwithGROUPBY" to "distributed dictionary access",
        "COUNT1" to "distributed dictionary access",
        "ADD8" to "distributed dictionary access",
        "ABS" to "distributed dictionary access",
        "DROPDEFAULT" to "distributed dictionary access",
        "bind07BIND" to "distributed dictionary access",
        "Group3" to "distributed dictionary access",
        "aggemptygroup" to "distributed dictionary access",
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
        "ADDSILENT" to "query not distributed",
        "COPY1" to "query not distributed",
        "SHA1" to "distributed dictionary access",
        "ADD7" to "query not distributed",
        "Expressionhasundefinedvariable" to "distributed dictionary access",
        "MOVE3" to "query not distributed",
        "SECONDS" to "distributed dictionary access",
        "ADD2" to "query not distributed",
        "CEIL" to "distributed dictionary access",
        "COPY3" to "query not distributed",
        "Expressionisequality" to "distributed dictionary access",
        "SHA256" to "distributed dictionary access",
        "SimpleDELETE3" to "query not distributed",
        "ADD4" to "query not distributed",
        "COPY6" to "query not distributed",
        "INSERT04" to "query not distributed",
        "ROUND" to "distributed dictionary access",
        "INSERTUSING01" to "query not distributed",
        "MOVE1" to "query not distributed",
        "Reuseaprojectexpressionvariableinorderby" to "distributed dictionary access",
        "DAY" to "distributed dictionary access",
        "LCASE" to "distributed dictionary access",
        "UCASE" to "distributed dictionary access",
        "YEAR" to "distributed dictionary access",
        "SAMPLE" to "distributed dictionary access",
        "COPYSILENT" to "query not distributed",
        "SimpleDELETEWHERE3" to "query not distributed",
        "Group1" to "distributed dictionary access",
        "STRDTSTR" to "distributed dictionary access",
        "constructwhere02CONSTRUCTWHERE" to "query not distributed",
        "MD5" to "distributed dictionary access",
        "MOVESILENT" to "query not distributed",
        "STRLANGSTR" to "distributed dictionary access",
        "bind10BINDscopingVariableinfilternotinscope" to "distributed dictionary access",
    )
}

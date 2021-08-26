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
internal object SparqlTestSuiteConverterToUnitTestIgnoreListDueToNotImplemented {
    internal val ignoreList = mapOf<String, String>( //
        "ENCODEFORURI" to "not implemented",
        "GROUPCONCAT1" to "not implemented",
        "GROUPCONCAT2" to "not implemented",
        "GROUPCONCATwithSEPARATOR" to "not implemented",
        "pp01Simplepath" to "not implemented",
        "pp02Starpath" to "not implemented",
        "pp03Simplepathwithloop" to "not implemented",
        "pp06Pathwithtwographs" to "not implemented",
        "pp07Pathwithonegraph" to "not implemented",
        "pp08Reversepath" to "not implemented",
        "pp09Reversesequencepath" to "not implemented",
        "pp10Pathwithnegation" to "not implemented",
        "pp11Simplepathandtwopathstosametargetnode" to "not implemented",
        "pp12Variablelengthpathandtwopathstosametargetnode" to "not implemented",
        "pp14Starpathoverfoafknows" to "not implemented",
        "pp16Duplicatepathsandcyclesthroughfoafknows" to "not implemented",
        "pp21Diamondp" to "not implemented",
        "pp23Diamondwithtailp" to "not implemented",
        "pp25Diamondwithloopp" to "not implemented",
        "pp28aDiamondwithlooppp" to "not implemented",
        "pp30Operatorprecedence1" to "not implemented",
        "pp31Operatorprecedence2" to "not implemented",
        "pp32Operatorprecedence3" to "not implemented",
        "pp33Operatorprecedence4" to "not implemented",
        "pp34NamedGraph1" to "not implemented",
        "pp35NamedGraph2" to "not implemented",
        "pp36Arbitrarypathwithboundendpoints" to "not implemented",
        "pp37Nested" to "not implemented",
        "RAND" to "not implemented",
        "REPLACE" to "not implemented",
        "REPLACEwithcapturedsubstring" to "not implemented",
        "REPLACEwithoverlappingpattern" to "not implemented",
        "SHA512onUnicodedata" to "not implemented",
        "SHA512" to "not implemented",
        "sq01Subquerywithingraphpattern" to "not implemented",
        "sq02Subquerywithingraphpatterngraphvariableisbound" to "not implemented",
        "sq03Subquerywithingraphpatterngraphvariableisnotbound" to "not implemented",
        "sq04Subquerywithingraphpatterndefaultgraphdoesnotapply" to "not implemented",
        "sq05Subquerywithingraphpatternfromnamedapplies" to "not implemented",
        "sq06Subquerywithgraphpatternfromnamedapplies" to "not implemented",
        "sq07Subquerywithfrom" to "not implemented",
        "sq08Subquerywithaggregate" to "not implemented",
        "sq09NestedSubqueries" to "not implemented",
        "sq10Subquerywithexists" to "not implemented",
        "SUBSTR2argument" to "not implemented",
        "SUBSTR3argument" to "not implemented",
        "synppincollection" to "not implemented",
        "syntaxaggregate13rq" to "not implemented",
        "syntaxaggregate14rq" to "not implemented",
        "syntaxaggregate15rq" to "not implemented",
        "syntaxpropertyPaths01rq" to "not implemented",
        "syntaxselectexpr04rq" to "not implemented",
    )
}

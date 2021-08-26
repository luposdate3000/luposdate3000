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
    internal val ignoreListDueToBugsInSimulator = mapOf<String, String>( //
        "BNODEstr" to "errors in simulator",
        "Calculatepropersubset" to "errors in simulator",
        "CalculatewhichsetsaresubsetsofothersincludeAsubsetOfA" to "errors in simulator",
        "Calculatewhichsetshavethesameelements" to "errors in simulator",
        "CLEARALL" to "errors in simulator",
        "CONCAT2" to "errors in simulator",
        "constructwhere03CONSTRUCTWHERE" to "errors in simulator",
        "COUNT4" to "errors in simulator",
        "DELETEINSERT4b" to "errors in simulator",
        "DELETEINSERT6b" to "errors in simulator",
        "Expressionraiseanerror" to "errors in simulator",
        "filteredsubclassquerywithhasChildsomeThingrestriction" to "errors in simulator",
        "GraphspecificDELETE1" to "errors in simulator",
        "IFerrorpropogation" to "errors in simulator",
        "InlineVALUESgraphpattern" to "errors in simulator",
        "MAXwithGROUPBY" to "errors in simulator",
        "MedicaltemporalproximitybyexclusionNOTEXISTS" to "errors in simulator",
        "MINwithGROUPBY" to "errors in simulator",
        "papersparqldlQ2" to "errors in simulator",
        "papersparqldlQ3" to "errors in simulator",
        "papersparqldlQ4" to "errors in simulator",
        "parentquerywithhasChildsomeFemalerestriction" to "errors in simulator",
        "Plainliteralswithlanguagetagarenotthesameasthesameliteralwithout" to "errors in simulator",
        "PostqueryVALUESwith2objvars1row" to "errors in simulator",
        "PostqueryVALUESwithsubjvar1row" to "errors in simulator",
        "RDFSinferencetestcombiningsubPropertyOfanddomain" to "errors in simulator",
        "RDFSinferencetestdomain" to "errors in simulator",
        "RDFSinferencetestrange" to "errors in simulator",
        "RDFSinferencetestsubClassOfluposDuplicate1" to "errors in simulator",
        "RDFSinferencetestsubPropertyandinstances" to "errors in simulator",
        "resourcesbtc029sparql1198" to "errors in simulator",
        "resourcesbtc030sparql867" to "errors in simulator",
        "resourcesmyqueriesoptional16sparql4" to "errors in simulator",
        "resourcesmyqueriesoptional16sparql5" to "errors in simulator",
        "resourcesmyqueriesoptional21sparql4" to "errors in simulator",
        "resourcesmyqueriesoptional23sparql4" to "errors in simulator",
        "resourcesmyqueriesoptional28sparql4" to "errors in simulator",
        "resourcesmyqueriesoptional37sparql5" to "errors in simulator",
        "resourcesmyqueriesoptional39sparql4" to "errors in simulator",
        "resourcesmyqueriesoptional47sparql4" to "errors in simulator",
        "resourcesmyqueriesoptional52sparql4" to "errors in simulator",
        "resourcesmyqueriesoptional56sparql4" to "errors in simulator",
        "resourcesmyqueriesoptional56sparql5" to "errors in simulator",
        "resourcesmyqueriesoptional5sparql4" to "errors in simulator",
        "resourcesmyqueriesoptional6sparql5" to "errors in simulator",
        "resourcessp2bq10sparql973" to "errors in simulator",
        "resourcessp2bq12asparql1294" to "errors in simulator",
        "resourcessp2bq12b1sparql1640" to "errors in simulator",
        "resourcessp2bq12b3sparql700" to "errors in simulator",
        "resourcessp2bq12b4sparql247" to "errors in simulator",
        "resourcessp2bq12bsparql21" to "errors in simulator",
        "resourcessp2bq12csparql973" to "errors in simulator",
        "resourcessp2bq1sparql700" to "errors in simulator",
        "resourcessp2bq2sparql1294" to "errors in simulator",
        "resourcessp2bq3asparql700" to "errors in simulator",
        "resourcessp2bq5bsparql1294" to "errors in simulator",
        "resourcessp2bq62sparql1294" to "errors in simulator",
        "resourcessp2bq66sparql21" to "errors in simulator",
        "resourcessp2bq67sparql21" to "errors in simulator",
        "resourcessp2bq68sparql973" to "errors in simulator",
        "resourcessp2bq71sparql247" to "errors in simulator",
        "resourcessp2bq7sparql700" to "errors in simulator",
        "resourcessp2bq91sparql21" to "errors in simulator",
        "resourcessp2bq9sparql1640" to "errors in simulator",
        "Reuseaprojectexpressionvariableinselect" to "errors in simulator",
        "SHA256" to "errors in simulator",
        "simple5" to "errors in simulator",
        "simple7" to "errors in simulator",
        "SimpleDELETE1" to "errors in simulator",
        "SimpleDELETEWHERE1" to "errors in simulator",
        "simpletriplepatternmatch" to "errors in simulator",
        "sparqldl01rqtriplepattern" to "errors in simulator",
        "sparqldl13rqsameAs" to "errors in simulator",
        "sq14limitbyresource" to "errors in simulator",
        "STRBEFOREdatatyping" to "errors in simulator",
        "STRDTSTR" to "errors in simulator",
        "STRDTTypeErrors" to "errors in simulator",
        "STRLANGTypeErrors" to "errors in simulator",
        "SubtractionwithMINUSfromafullyboundminuend" to "errors in simulator",
        "TZ" to "errors in simulator",
    )
}

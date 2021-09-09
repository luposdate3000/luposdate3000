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
package lupos.launch.display_bibtex
import lupos.endpoint.LuposdateEndpoint
import lupos.shared.DictionaryValueHelper
import lupos.shared.Parallel
import lupos.shared.Partition
import lupos.shared.dynamicArray.ByteArrayWrapper
import lupos.shared.inline.DictionaryHelper

@OptIn(ExperimentalStdlibApi::class, kotlin.time.ExperimentalTime::class)
internal fun mainFunc(inputFileName: String): Unit = Parallel.runBlocking {
    val instance = LuposdateEndpoint.initialize()
    LuposdateEndpoint.importTripleFile(instance, inputFileName)
    var node = LuposdateEndpoint.evaluateSparqlToOperatorgraphB(instance, "SELECT ?s ?p ?o WHERE { ?s a <bibtex_entry> . ?s ?p ?o . }", false)
    node = node.getChildren()[0]
    val query = node.getQuery()
    val child = node.evaluateRoot(Partition())
    val values = mutableMapOf<String, MutableMap<String, String>>()
    val colS = child.columns["s"]!!
    val colP = child.columns["p"]!!
    val colO = child.columns["o"]!!
    val buffer = ByteArrayWrapper()
    while (true) {
        val s = colS.next()
        val p = colP.next()
        val o = colO.next()
        if (s != DictionaryValueHelper.nullValue) {
            query.getDictionary().getValue(buffer, s)
            val vs = DictionaryHelper.byteArrayToSparql(buffer)
            query.getDictionary().getValue(buffer, p)
            val vp = DictionaryHelper.byteArrayToSparql(buffer)
            query.getDictionary().getValue(buffer, o)
            val vo = DictionaryHelper.byteArrayToSparql(buffer)
            var vals = values[vs]
            if (vals == null) {
                vals = mutableMapOf()
                values[vs] = vals
            }
            vals[vp] = vo
        } else {
            break
        }
    }
    println("bibtex :: ")
    for ((s, vals) in values) {
        vals.remove("<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>")
        val type = extractIri(vals["<bibtex_type>"])!!
        val label = extractIri(s)
        println("@$type{$label,")
        vals.remove("<bibtex_type>")
        for (
            (first, second) in arrayOf(
                "title" to "<bibtex_title>",
                "author" to "<bibtex_author_list>",
                "journal" to "<bibtex_journal>",
                "publisher" to "<bibtex_publisher>",
                "volume" to "<bibtex_volume>",
                "number" to "<bibtex_number>",
                "year" to "<bibtex_year>",
                "url" to "<bibtex_url>",
                "address" to "<bibtex_address>",
                "booktitle" to "<bibtex_booktitle>",
                "data" to "<bibtex_data>",
                "day" to "<bibtex_day>",
                "doi" to "<bibtex_doi>",
                "editor" to "<bibtex_editor>",
                "howpublished" to "<bibtex_howpublished>",
                "iddue_data" to "<bibtex_iddue_data>",
                "isbn" to "<bibtex_isbn>",
                "issn" to "<bibtex_issn>",
                "issue_data" to "<bibtex_issue_data>",
                "keywords" to "<bibtex_keywords>",
                "location" to "<bibtex_location>",
                "month" to "<bibtex_month>",
                "note" to "<bibtex_note>",
                "numpages" to "<bibtex_numpages>",
                "organization" to "<bibtex_organization>",
                "series" to "<bibtex_series>",
                "urn" to "<bibtex_urn>",
            )
        ) {
            val item = extractString(vals[second])
            if (item != null) {
                println("  $first={$item},")
                vals.remove(second)
            }
        }
        val pages_from = extractString(vals["<bibtex_pages_from>"])
        if (pages_from != null) {
            val pages_to = extractString(vals["<bibtex_pages_to>"]!!)
            vals.remove("<bibtex_pages_from>")
            vals.remove("<bibtex_pages_to>")
            println("  pages={$pages_from--$pages_to},")
        }
        for ((p, o) in vals) {
            println("#### $s -> $p -> $o")
        }
        println("}")
    }
}

internal fun extractString(s: String?): String? {
    if (s == null) {
        return s
    }
    if (s.startsWith('"') && s.endsWith('"')) {
        return s.substring(1, s.length - 1)
    }
    throw Exception("not a String '$s'")
}

internal fun extractIri(s: String?): String? {
    if (s == null) {
        return s
    }
    if (s.startsWith('<') && s.endsWith('>')) {
        return s.substring(1, s.length - 1)
    }
    throw Exception("not a Iri '$s'")
}

internal fun extractInteger(s: String?): String? {
    if (s == null) {
        return s
    }
    if (s.startsWith('"') && s.endsWith("\"^^<http://www.w3.org/2001/XMLSchema#integer>")) {
        return s.substring(1, s.length - "\"^^<http://www.w3.org/2001/XMLSchema#integer>".length)
    }
    throw Exception("not a Integer '$s'")
}

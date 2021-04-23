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
package lupos.parser.rdf

import lupos.shared.UUID_Counter
import kotlin.jvm.JvmField

public object Dictionary {
    @JvmField
    internal val RDFTerm_to_ID = mutableMapOf<String, Long>()

    @JvmField
    internal val ID_to_RDFTerm = mutableMapOf<Long, RDFTerm>()

    private fun addRDFTerm(term: RDFTerm): Long {
        var result = this.RDFTerm_to_ID[term.toN3String()]
        if (result != null) {
            return result
        }
        result = UUID_Counter.getNextUUID()
        this.RDFTerm_to_ID[term.toN3String()] = result
        this.ID_to_RDFTerm[result] = term
        return result
    }

    public fun IRI(iri: String): Long = this.RDFTerm_to_ID["<$iri>"]
        ?: addRDFTerm(lupos.parser.rdf.IRI(iri))

    public fun BlankNode(local_name: String): Long = this.RDFTerm_to_ID["_:$local_name"]
        ?: addRDFTerm(lupos.parser.rdf.BlankNode(local_name))

    public fun BlankNode(): Long = addRDFTerm(lupos.parser.rdf.BlankNode())
    public fun SimpleLiteral(content: String, delimiter: String = "\""): Long = this.RDFTerm_to_ID[delimiter + content + delimiter]
        ?: addRDFTerm(lupos.parser.rdf.SimpleLiteral(content, delimiter))

    public fun LanguageTaggedLiteral(content: String, delimiter: String = "\"", language: String): Long = this.RDFTerm_to_ID["$delimiter$content$delimiter@$language"]
        ?: addRDFTerm(lupos.parser.rdf.LanguageTaggedLiteral(content, delimiter, language))

    public fun TypedLiteral(content: String, delimiter: String = "\"", type: String): Long = this.RDFTerm_to_ID["$delimiter$content$delimiter^^<$type>"]
        ?: addRDFTerm(lupos.parser.rdf.TypedLiteral(content, delimiter, type))

    public operator fun get(id: Long): RDFTerm? {
        return this.ID_to_RDFTerm[id]
    }
}

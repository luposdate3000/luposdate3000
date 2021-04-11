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
package lupos.s02buildSyntaxTree.rdf

import kotlin.jvm.JvmField
import lupos.shared.UUID_Counter

public abstract class RDFTerm {
    public abstract fun toN3String(): String
}

public abstract class RDFResource : RDFTerm()
public class IRI(@JvmField public val iri: String) : RDFResource() {
    public override fun toN3String(): String = "<$iri>"
}

public class BlankNode(@JvmField public val local_name: String) : RDFResource() {
    public constructor() : this("_" + UUID_Counter.getNextUUID())

    public override fun toN3String(): String = "_:$local_name"

}

public abstract class Literal(@JvmField public val content: String, @JvmField public val delimiter: String) : RDFTerm() {
    public override fun toN3String(): String = delimiter + content + delimiter
}

public class SimpleLiteral(content: String, delimiter: String) : Literal(content, delimiter)
public class LanguageTaggedLiteral(content: String, delimiter: String, @JvmField public val language: String) : Literal(content, delimiter) {
    public override fun toN3String(): String = super.toN3String() + "@" + language
}

public class TypedLiteral(content: String, delimiter: String, @JvmField public val type: String) : Literal(content, delimiter) {
    public override fun toN3String(): String = super.toN3String() + "^^<" + type + ">"
}

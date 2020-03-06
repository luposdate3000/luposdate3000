package lupos.s02buildSyntaxTree.rdf

import kotlin.jvm.JvmField
import lupos.s00misc.ThreadSafeUuid


abstract class RDFTerm {
    abstract fun toN3String(): String
}

abstract class RDFResource : RDFTerm()
class IRI(@JvmField val iri: String) : RDFResource() {
    override fun toN3String(): String = "<" + iri + ">"
}

class BlankNode(@JvmField val local_name: String) : RDFResource() {
    constructor() : this(NewNameCreator.createNewName())

    override fun toN3String(): String = "_:" + local_name

    companion object NewNameCreator { // just for creating internal new names in case of [] in RDF documents...
        private val counter = ThreadSafeUuid()
        fun createNewName(): String {
            return "_" + counter.next() // local names for blank nodes in RDF documents cannot start with "_". Hence we start internally given names with "_"!
        }
    }
}

abstract class Literal(@JvmField val content: String, @JvmField val delimiter: String) : RDFTerm() {
    override fun toN3String(): String = delimiter + content + delimiter
}

class SimpleLiteral(content: String, delimiter: String) : Literal(content, delimiter) {
    constructor(content: String) : this(content, "\"")
}

class LanguageTaggedLiteral(content: String, delimiter: String, @JvmField val language: String) : Literal(content, delimiter) {
    constructor(content: String, language: String) : this(content, "\"", language)

    override fun toN3String(): String = super.toN3String() + "@" + language
}

class TypedLiteral(content: String, delimiter: String, @JvmField val type: String) : Literal(content, delimiter) {
    constructor(content: String, type: String) : this(content, "\"", type)

    override fun toN3String(): String = super.toN3String() + "^^<" + type + ">"
}

class Triple(@JvmField val s: RDFResource, @JvmField val p: IRI, @JvmField val o: RDFTerm) {
    fun toN3String(): String = s.toN3String() + " " + p.toN3String() + " " + o.toN3String() + "."
}

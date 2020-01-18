package lupos.s1buildSyntaxTree.rdf


abstract open class RDFTerm() {
    abstract fun toN3String(): String;
}

abstract open class RDFResource() : RDFTerm()
class IRI(val iri: String) : RDFResource() {
    override fun toN3String(): String = "<" + iri + ">"
}

class BlankNode(val local_name: String) : RDFResource() {
    constructor() : this(NewNameCreator.createNewName());
    override fun toN3String(): String = "_:" + local_name

    companion object NewNameCreator { // just for creating internal new names in case of [] in RDF documents...
        var counter = 0;
        fun createNewName(): String {
            val result = "_" + counter; // local names for blank nodes in RDF documents cannot start with "_". Hence we start internally given names with "_"!
            counter++;
            return result;
        }
    }
}

abstract open class Literal(val content: String, val delimiter: String) : RDFTerm() {
    override fun toN3String(): String = delimiter + content + delimiter
}

class SimpleLiteral(content: String, delimiter: String) : Literal(content, delimiter) {
    constructor(content: String) : this(content, "\"")
}

class LanguageTaggedLiteral(content: String, delimiter: String, val language: String) : Literal(content, delimiter) {
    constructor(content: String, language: String) : this(content, "\"", language)

    override fun toN3String(): String = super.toN3String() + "@" + language
}

class TypedLiteral(content: String, delimiter: String, val type: String) : Literal(content, delimiter) {
    constructor(content: String, type: String) : this(content, "\"", type)

    override fun toN3String(): String = super.toN3String() + "^^<" + type + ">"
}

class Triple(val s: RDFResource, val p: IRI, val o: RDFTerm) {
    fun toN3String(): String = s.toN3String() + " " + p.toN3String() + " " + o.toN3String() + "."
}

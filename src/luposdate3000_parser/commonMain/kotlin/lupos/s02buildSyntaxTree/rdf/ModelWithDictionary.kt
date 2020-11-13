package lupos.s02buildSyntaxTree.rdf

import kotlin.jvm.JvmField

object Dictionary {
    private var max_id = 0L
    private val RDFTerm_to_ID = mutableMapOf<String, Long>()
    private val ID_to_RDFTerm = mutableMapOf<Long, RDFTerm>()
    private fun addRDFTerm(term: RDFTerm): Long {
        val result = max_id++
        this.RDFTerm_to_ID[term.toN3String()] = result
        this.ID_to_RDFTerm[result] = term
        return result
    }

    fun IRI(iri: String): Long = this.RDFTerm_to_ID["<$iri>"]
            ?: addRDFTerm(lupos.s02buildSyntaxTree.rdf.IRI(iri))

    fun BlankNode(local_name: String): Long = this.RDFTerm_to_ID["_:$local_name"]
            ?: addRDFTerm(lupos.s02buildSyntaxTree.rdf.BlankNode(local_name))

    fun BlankNode(): Long = addRDFTerm(lupos.s02buildSyntaxTree.rdf.BlankNode())
    fun SimpleLiteral(content: String, delimiter: String = "\""): Long = this.RDFTerm_to_ID[delimiter + content + delimiter]
            ?: addRDFTerm(lupos.s02buildSyntaxTree.rdf.SimpleLiteral(content, delimiter))

    fun LanguageTaggedLiteral(content: String, delimiter: String = "\"", language: String): Long = this.RDFTerm_to_ID["$delimiter$content$delimiter@$language"]
            ?: addRDFTerm(lupos.s02buildSyntaxTree.rdf.LanguageTaggedLiteral(content, delimiter, language))

    fun TypedLiteral(content: String, delimiter: String = "\"", type: String): Long = this.RDFTerm_to_ID["$delimiter$content$delimiter^^<$type>"]
            ?: addRDFTerm(lupos.s02buildSyntaxTree.rdf.TypedLiteral(content, delimiter, type))

    operator fun get(id: Long): RDFTerm? {
        return this.ID_to_RDFTerm[id]
    }
}

class ID_Triple(@JvmField val s: Long, @JvmField val p: Long, @JvmField val o: Long) {
    fun toN3String(): String = Dictionary[s]?.toN3String() + " " + Dictionary[p]?.toN3String() + " " + Dictionary[o]?.toN3String() + "."
}

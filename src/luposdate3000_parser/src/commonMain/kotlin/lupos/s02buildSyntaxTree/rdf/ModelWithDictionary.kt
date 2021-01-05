package lupos.s02buildSyntaxTree.rdf
import kotlin.jvm.JvmField
public object Dictionary {
    private var max_id = 0L
    private val RDFTerm_to_ID = mutableMapOf<String, Long>()
    private val ID_to_RDFTerm = mutableMapOf<Long, RDFTerm>()
    private fun addRDFTerm(term: RDFTerm): Long {
        val result = max_id++
        this.RDFTerm_to_ID[term.toN3String()] = result
        this.ID_to_RDFTerm[result] = term
        return result
    }
    public fun IRI(iri: String): Long = this.RDFTerm_to_ID["<$iri>"]
        ?: addRDFTerm(lupos.s02buildSyntaxTree.rdf.IRI(iri))
    public fun BlankNode(local_name: String): Long = this.RDFTerm_to_ID["_:$local_name"]
        ?: addRDFTerm(lupos.s02buildSyntaxTree.rdf.BlankNode(local_name))
    public fun BlankNode(): Long = addRDFTerm(lupos.s02buildSyntaxTree.rdf.BlankNode())
    public fun SimpleLiteral(content: String, delimiter: String = "\""): Long = this.RDFTerm_to_ID[delimiter + content + delimiter]
        ?: addRDFTerm(lupos.s02buildSyntaxTree.rdf.SimpleLiteral(content, delimiter))
    public fun LanguageTaggedLiteral(content: String, delimiter: String = "\"", language: String): Long = this.RDFTerm_to_ID["$delimiter$content$delimiter@$language"]
        ?: addRDFTerm(lupos.s02buildSyntaxTree.rdf.LanguageTaggedLiteral(content, delimiter, language))
    public fun TypedLiteral(content: String, delimiter: String = "\"", type: String): Long = this.RDFTerm_to_ID["$delimiter$content$delimiter^^<$type>"]
        ?: addRDFTerm(lupos.s02buildSyntaxTree.rdf.TypedLiteral(content, delimiter, type))
    public operator fun get(id: Long): RDFTerm? {
        return this.ID_to_RDFTerm[id]
    }
}
public class ID_Triple(@JvmField public val s: Long, @JvmField public val p: Long, @JvmField public val o: Long)

package lupos.s02buildSyntaxTree.rdf
import kotlin.jvm.JvmField
public abstract class RDFTerm {
    public abstract fun toN3String(): String
}
public abstract class RDFResource : RDFTerm()
public class IRI(@JvmField public val iri: String) : RDFResource() {
    public override fun toN3String(): String = "<$iri>"
}
public class BlankNode(@JvmField public val local_name: String) : RDFResource() {
    public constructor() : this(createNewName())
    public override fun toN3String(): String = "_:$local_name"
    public companion object NewNameCreator { // just for creating internal new names in case of [] in RDF documents...
        private var counter = 0L
        public fun createNewName(): String {
            return "_" + counter++ // local names for blank nodes in RDF documents cannot start with "_". Hence we start internally given names with "_"!
        }
    }
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

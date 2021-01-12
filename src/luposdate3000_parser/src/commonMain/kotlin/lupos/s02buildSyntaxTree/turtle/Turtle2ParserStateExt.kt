package lupos.s02buildSyntaxTree.turtle
import kotlin.jvm.JvmField
internal object Turtle2ParserStateExt {
    internal const val EOF: Turtle2ParserState = 0
    internal const val OBJECT: Turtle2ParserState = 1
    internal const val PREDICATE: Turtle2ParserState = 2
    internal const val STATEMENT: Turtle2ParserState = 3
    internal const val TRIPLE_END: Turtle2ParserState = 4
    internal const val TRIPLE_END_OR_OBJECT_IRI: Turtle2ParserState = 5
    internal const val TRIPLE_END_OR_OBJECT_STRING: Turtle2ParserState = 6
    @JvmField internal val values: IntArray = IntArray(7) { it }
    @JvmField internal val names: Array<String> = arrayOf(
        "EOF",
        "OBJECT",
        "PREDICATE",
        "STATEMENT",
        "TRIPLE_END",
        "TRIPLE_END_OR_OBJECT_IRI",
        "TRIPLE_END_OR_OBJECT_STRING",
    )
}

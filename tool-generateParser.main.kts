#!/usr/bin/env kotlin
@file:Import("src/luposdate3000_shared/src/commonMain/kotlin/lupos/s00misc/EOperatingSystem.kt")
@file:Import("src/luposdate3000_shared/src/commonMain/kotlin/lupos/s00misc/EOperatingSystemExt.kt")
@file:Import("src/luposdate3000_shared_inline/src/commonMain/kotlin/lupos/s00misc/Platform.kt")
@file:Import("src/luposdate3000_shared_inline/src/commonMain/kotlin/lupos/s00misc/PlatformAlias.kt")
@file:Import("src/luposdate3000_shared_inline/src/jvmMain/kotlin/lupos/s00misc/Platform.kt")
@file:CompilerOptions("-Xmulti-platform")
import lupos.s00misc.Platform
import java.io.File
import java.lang.ProcessBuilder.Redirect
val outFile = File("src${Platform.getPathSeparator()}luposdate3000_parser${Platform.getPathSeparator()}src${Platform.getPathSeparator()}commonMain${Platform.getPathSeparator()}kotlin${Platform.getPathSeparator()}lupos${Platform.getPathSeparator()}s02buildSyntaxTree${Platform.getPathSeparator()}turtle${Platform.getPathSeparator()}Turtle2ParserGenerated.kt")
outFile.printWriter().use { out ->
    out.println("package lupos.s02buildSyntaxTree.turtle")
    out.println("import lupos.s00misc.IMyInputStream")
    out.println("import lupos.s00misc.Luposdate3000Exception")
    out.println("import kotlin.jvm.JvmField")
    out.println("internal open class ParserException(msg: String) : Luposdate3000Exception(\"ParserContext\", msg)")
    out.println("internal class ParserExceptionEOF : ParserException(\"EOF\")")
    out.println("internal class ParserExceptionUnexpectedChar(context: ParserContext) : ParserException(\"unexpected char 0x\${context.c.toString(16)} at \${context.line}:\${context.column}\")")
}
val scriptFile = "src/luposdate3000_scripting/parsergenerator.main.kts"
val generatingArgs = arrayOf(
    listOf(scriptFile, "PARSER_CONTEXT"),
    listOf(scriptFile, "parse_dot", "DOT"),
    listOf(scriptFile, "parse_ws", "SKIP_WS"),
    listOf(scriptFile, "parse_ws_forced", "SKIP_WS_FORCED"),
    listOf(scriptFile, "parse_statement", "BASE", "PREFIX", "BASE2", "PREFIX2", "IRIREF", "PNAME_NS", "BLANK_NODE_LABEL"),
    listOf(scriptFile, "parse_base", "IRIREF"),
    listOf(scriptFile, "parse_prefix", "PNAME_NS"),
    listOf(scriptFile, "parse_prefix2", "IRIREF"),
    listOf(scriptFile, "parse_predicate", "VERB1", "IRIREF", "PNAME_NS"),
    listOf(scriptFile, "parse_obj", "IRIREF", "PNAME_NS", "BLANK_NODE_LABEL", "STRING_LITERAL_QUOTE", "STRING_LITERAL_SINGLE_QUOTE", "STRING_LITERAL_LONG_SINGLE_QUOTE", "STRING_LITERAL_LONG_QUOTE", "INTEGER", "DECIMAL", "DOUBLE", "BOOLEAN"),
    listOf(scriptFile, "parse_triple_end", "PREDICATE_LIST1", "OBJECT_LIST1", "DOT"),
    listOf(scriptFile, "parse_triple_end_or_object_iri", "PN_LOCAL", "PREDICATE_LIST1", "OBJECT_LIST1", "DOT", "SKIP_WS_FORCED"),
    listOf(scriptFile, "parse_triple_end_or_object_string", "LANGTAG", "IRI1", "PREDICATE_LIST1", "OBJECT_LIST1", "DOT", "SKIP_WS_FORCED"),
    listOf(scriptFile, "parse_triple_end_or_object_string_typed", "IRIREF", "PNAME_NS"),
    listOf(scriptFile, "parse_triple_end_or_object_string_typed_iri", "PN_LOCAL", "PREDICATE_LIST1", "OBJECT_LIST1", "DOT", "SKIP_WS_FORCED"),
    listOf(scriptFile, "parse_subject_iri_or_ws", "PN_LOCAL", "SKIP_WS_FORCED"),
    listOf(scriptFile, "parse_predicate_iri_or_ws", "PN_LOCAL", "SKIP_WS_FORCED"),
)
for (args in generatingArgs) {
    ProcessBuilder(args)
        .redirectOutput(Redirect.appendTo(outFile))
        .redirectError(Redirect.INHERIT)
        .start()
        .waitFor()
}

#!/bin/bash
echo "package lupos.s02buildSyntaxTree.turtle"
echo "import kotlin.jvm.JvmField"
echo "import lupos.s00misc.Luposdate3000Exception"
echo "import lupos.s00misc.IMyInputStream"
echo "open class ParserException(msg:String):Luposdate3000Exception(\"ParserContext\",msg)"
echo "internal class ParserExceptionEOF():ParserException(\"EOF\")"
echo "internal class ParserExceptionUnexpectedChar(context:ParserContext):ParserException(\"unexpected char 0x\${context.c.toString(16)} at \${context.line}:\${context.column}\")"

./parsergenerator.kts PARSER_CONTEXT
./parsergenerator.kts parse_dot                                   DOT
./parsergenerator.kts parse_ws                                    SKIP_WS
./parsergenerator.kts parse_ws_forced                             SKIP_WS_FORCED
./parsergenerator.kts parse_statement                             BASE PREFIX BASE2 PREFIX2 IRIREF PNAME_NS BLANK_NODE_LABEL
./parsergenerator.kts parse_base                                  IRIREF
./parsergenerator.kts parse_prefix                                PNAME_NS
./parsergenerator.kts parse_prefix2                               IRIREF
./parsergenerator.kts parse_predicate                             VERB1 IRIREF PNAME_NS
./parsergenerator.kts parse_obj                                   IRIREF PNAME_NS BLANK_NODE_LABEL STRING_LITERAL_QUOTE STRING_LITERAL_SINGLE_QUOTE STRING_LITERAL_LONG_SINGLE_QUOTE STRING_LITERAL_LONG_QUOTE INTEGER DECIMAL DOUBLE BOOLEAN
./parsergenerator.kts parse_triple_end                            PREDICATE_LIST1 OBJECT_LIST1 DOT
./parsergenerator.kts parse_triple_end_or_object_iri              PN_LOCAL PREDICATE_LIST1 OBJECT_LIST1 DOT SKIP_WS_FORCED
./parsergenerator.kts parse_triple_end_or_object_string           LANGTAG IRI1 PREDICATE_LIST1 OBJECT_LIST1 DOT SKIP_WS_FORCED
./parsergenerator.kts parse_triple_end_or_object_string_typed     IRIREF PNAME_NS
./parsergenerator.kts parse_triple_end_or_object_string_typed_iri PN_LOCAL PREDICATE_LIST1 OBJECT_LIST1 DOT SKIP_WS_FORCED
./parsergenerator.kts parse_subject_iri_or_ws                     PN_LOCAL SKIP_WS_FORCED
./parsergenerator.kts parse_predicate_iri_or_ws                   PN_LOCAL SKIP_WS_FORCED

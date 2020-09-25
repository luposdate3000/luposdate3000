#!/bin/bash
echo "package lupos.s02buildSyntaxTree.turtle"
echo "import kotlin.jvm.JvmField"
/src/parser/parsergenerator.kts PARSER_CONTEXT
/src/parser/parsergenerator.kts parse_statement                          BASE PREFIX BASE2 PREFIX2 IRIREF PNAME_NS BLANK_NODE_LABEL
/src/parser/parsergenerator.kts parse_base                               IRIREF
/src/parser/parsergenerator.kts parse_prefix                             PNAME_NS
/src/parser/parsergenerator.kts parse_prefix2                            IRIREF
/src/parser/parsergenerator.kts parse_predicate_or_subject               PN_LOCAL VERB1 IRIREF PNAME_NS
/src/parser/parsergenerator.kts parse_predicate                          VERB1 IRIREF PNAME_NS
/src/parser/parsergenerator.kts parse_obj                                IRIREF PNAME_NS BLANK_NODE_LABEL STRING_LITERAL_QUOTE STRING_LITERAL_SINGLE_QUOTE STRING_LITERAL_LONG_SINGLE_QUOTE STRING_LITERAL_LONG_QUOTE INTEGER DECIMAL DOUBLE BOOLEAN
/src/parser/parsergenerator.kts parse_obj_or_predicate                   PN_LOCAL IRIREF PNAME_NS BLANK_NODE_LABEL STRING_LITERAL_QUOTE STRING_LITERAL_SINGLE_QUOTE STRING_LITERAL_LONG_SINGLE_QUOTE STRING_LITERAL_LONG_QUOTE INTEGER DECIMAL DOUBLE BOOLEAN
/src/parser/parsergenerator.kts parse_triple_end                         PREDICATE_LIST1 OBJECT_LIST1 DOT
/src/parser/parsergenerator.kts parse_triple_end_or_object_iri           PN_LOCAL PREDICATE_LIST1 OBJECT_LIST1 DOT
/src/parser/parsergenerator.kts parse_triple_end_or_obj_string           LANGTAG IRI1 PREDICATE_LIST1 OBJECT_LIST1 DOT
/src/parser/parsergenerator.kts parse_triple_end_or_obj_string_typed     IRIREF PNAME_NS
/src/parser/parsergenerator.kts parse_triple_end_or_obj_string_typed_iri PN_LOCAL PREDICATE_LIST1 OBJECT_LIST1 DOT

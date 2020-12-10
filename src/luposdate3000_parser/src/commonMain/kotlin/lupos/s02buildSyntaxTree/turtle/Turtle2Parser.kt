package lupos.s02buildSyntaxTree.turtle

import lupos.s00misc.ETripleComponentType
import lupos.s00misc.IMyInputStream
import kotlin.jvm.JvmField

internal enum class Turtle2ParserState {
    EOF,
    STATEMENT,
    PREDICATE,
    OBJECT,
    TRIPLE_END,
    TRIPLE_END_OR_OBJECT_IRI,
    TRIPLE_END_OR_OBJECT_STRING,
}

abstract class Turtle2Parser(input: IMyInputStream) {
    @JvmField
    internal val context = ParserContext(input)

    @JvmField
    internal val prefixMap = mutableMapOf<String, String>()

    @JvmField
    internal val triple = Array(3) { "" }

    @JvmField
    internal val tripleType = Array(3) { ETripleComponentType.IRI }

    @JvmField
    internal var state = Turtle2ParserState.STATEMENT
    abstract fun onTriple(triple: Array<String>, tripleType: Array<ETripleComponentType>)
    fun turtleDoc() {
        var iter = 0
        while (true) {
            iter++
            when (state) {
                //println("state :: $state at (${context.line}:${context.column})")
                Turtle2ParserState.EOF -> {
                    break
                }
                Turtle2ParserState.STATEMENT -> {
                    statement()
                }
                Turtle2ParserState.PREDICATE -> {
                    predicate()
                }
                Turtle2ParserState.OBJECT -> {
                    obj()
                }
                Turtle2ParserState.TRIPLE_END -> {
                    triple_end()
                }
                Turtle2ParserState.TRIPLE_END_OR_OBJECT_IRI -> {
                    triple_end_or_object_iri()
                }
                Turtle2ParserState.TRIPLE_END_OR_OBJECT_STRING -> {
                    triple_end_or_object_string()
                }
            }
        }
    }

    private fun statement_helper_1() {
        parse_base(context,
                onIRIREF = {
                    //println("onIRIREF(${context.getValue()})")
                    val s = context.getValue()
                    prefixMap[":"] = s.substring(1, s.length - 1)
                })
    }

    private fun statement_helper_2() {
        parse_prefix(context,
                onPNAME_NS = {
                    //println("onPNAME_NS(${context.getValue()})")
                    val prefix = context.getValue()
                    parse_ws_forced(context) {}
                    parse_prefix2(context, onIRIREF = {
                        //println("onIRIREF(${context.getValue()})")
                        val s = context.getValue()
                        prefixMap[prefix] = s.substring(1, s.length - 1)
                    })
                })
    }

    private fun statement_helper_3() {
        parse_subject_iri_or_ws(context,
                onPN_LOCAL = {
                    //println("onPN_LOCAL(${context.getValue()})")
                    triple[0] = "<" + prefixMap[triple[0]]!! + context.getValue() + ">"
                    tripleType[0] = ETripleComponentType.IRI
                    parse_ws_forced(context) {}
                },
                onSKIP_WS_FORCED = {
                    //println("onSKIP_WS_FORCED(${context.getValue()})")
                    triple[0] = "<" + prefixMap[triple[0]]!! + ">"
                    tripleType[0] = ETripleComponentType.IRI
                })
    }

    private fun statement() {
        parse_ws(context) {}
        if (context.c == ParserContext.EOF) {
            state = Turtle2ParserState.EOF
            return
        }
        parse_statement(context,
                onBASE = {
                    //println("onBASE(${context.getValue()})")
                    parse_ws_forced(context) {}
                    statement_helper_1()
                    state = Turtle2ParserState.STATEMENT
                },
                onPREFIX = {
                    //println("onPREFIX(${context.getValue()})")
                    parse_ws_forced(context) {}
                    statement_helper_2()
                    state = Turtle2ParserState.STATEMENT
                },
                onBASE2 = {
                    //println("onBASE2(${context.getValue()})")
                    parse_ws_forced(context) {}
                    statement_helper_1()
                    parse_ws(context) {}
                    parse_dot(context) {}
                    state = Turtle2ParserState.STATEMENT
                },
                onPREFIX2 = {
                    //println("onPREFIX2(${context.getValue()})")
                    parse_ws_forced(context) {}
                    statement_helper_2()
                    parse_ws(context) {}
                    parse_dot(context) {}
                    state = Turtle2ParserState.STATEMENT
                },
                onIRIREF = {
                    //println("onIRIREF(${context.getValue()})")
                    triple[0] = context.getValue()
                    tripleType[0] = ETripleComponentType.IRI
                    parse_ws_forced(context) {}
                    state = Turtle2ParserState.PREDICATE
                },
                onPNAME_NS = {
                    //println("onPNAME_NS(${context.getValue()})")
                    triple[0] = context.getValue()
                    tripleType[0] = ETripleComponentType.IRI
                    statement_helper_3()
                    state = Turtle2ParserState.PREDICATE
                },
                onBLANK_NODE_LABEL = {
                    //println("onBLANK_NODE_LABEL(${context.getValue()})")
                    triple[0] = context.getValue()
                    tripleType[0] = ETripleComponentType.BLANK_NODE
                    parse_ws_forced(context) {}
                    state = Turtle2ParserState.PREDICATE
                })
    }

    private fun predicate_helper_1() {
        parse_predicate_iri_or_ws(context,
                onPN_LOCAL = {
                    //println("onPN_LOCAL(${context.getValue()})")
                    triple[1] = "<" + prefixMap[triple[1]]!! + context.getValue() + ">"
                    tripleType[1] = ETripleComponentType.IRI
                    parse_ws_forced(context) {}
                },
                onSKIP_WS_FORCED = {
                    //println("onSKIP_WS_FORCED(${context.getValue()})")
                    triple[1] = "<" + prefixMap[triple[1]]!! + ">"
                    tripleType[1] = ETripleComponentType.IRI
                })
    }

    private fun predicate() {
        parse_predicate(context,
                onVERB1 = {
                    //println("onVERB1(${context.getValue()})")
                    triple[1] = "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>"
                    tripleType[1] = ETripleComponentType.IRI
                    parse_ws_forced(context) {}
                },
                onIRIREF = {
                    //println("onIRIREF(${context.getValue()})")
                    triple[1] = context.getValue()
                    tripleType[1] = ETripleComponentType.IRI
                    parse_ws_forced(context) {}
                },
                onPNAME_NS = {
                    //println("onPNAME_NS(${context.getValue()})")
                    triple[1] = context.getValue()
                    tripleType[1] = ETripleComponentType.IRI
                    predicate_helper_1()
                })
        state = Turtle2ParserState.OBJECT
    }

    private fun obj() {
        parse_obj(context,
                onIRIREF = {
                    //println("onIRIREF(${context.getValue()})")
                    triple[2] = context.getValue()
                    tripleType[2] = ETripleComponentType.IRI
                    parse_ws(context) {}
                    state = Turtle2ParserState.TRIPLE_END
                },
                onPNAME_NS = {
                    //println("onPNAME_NS(${context.getValue()})")
                    triple[2] = context.getValue()
                    tripleType[2] = ETripleComponentType.IRI
                    state = Turtle2ParserState.TRIPLE_END_OR_OBJECT_IRI
                },
                onBLANK_NODE_LABEL = {
                    //println("onBLANK_NODE_LABEL(${context.getValue()})")
                    val v = context.getValue()
                    tripleType[2] = ETripleComponentType.BLANK_NODE
                    if (v.endsWith(".")) {
//TODO fix the underlying bug in the parser
                        triple[2] = v.substring(0, v.length - 1)
                        onTriple(triple, tripleType)
                        state = Turtle2ParserState.STATEMENT
                    } else {
                        triple[2] = v
                        parse_ws(context) {}
                        state = Turtle2ParserState.TRIPLE_END
                    }
                },
                onSTRING_LITERAL_QUOTE = {
                    //println("onSTRING_LITERAL_QUOTE(${context.getValue()})")
                    triple[2] = context.getValue()
                    tripleType[2] = ETripleComponentType.STRING
                    state = Turtle2ParserState.TRIPLE_END_OR_OBJECT_STRING
                },
                onSTRING_LITERAL_SINGLE_QUOTE = {
                    //println("onSTRING_LITERAL_SINGLE_QUOTE(${context.getValue()})")
                    triple[2] = context.getValue()
                    tripleType[2] = ETripleComponentType.STRING
                    state = Turtle2ParserState.TRIPLE_END_OR_OBJECT_STRING
                },
                onSTRING_LITERAL_LONG_SINGLE_QUOTE = {
                    //println("onSTRING_LITERAL_LONG_SINGLE_QUOTE(${context.getValue()})")
                    triple[2] = context.getValue()
                    tripleType[2] = ETripleComponentType.STRING
                    state = Turtle2ParserState.TRIPLE_END_OR_OBJECT_STRING
                },
                onSTRING_LITERAL_LONG_QUOTE = {
                    //println("onSTRING_LITERAL_LONG_QUOTE(${context.getValue()})")
                    triple[2] = context.getValue()
                    tripleType[2] = ETripleComponentType.STRING
                    state = Turtle2ParserState.TRIPLE_END_OR_OBJECT_STRING
                },
                onINTEGER = {
                    //println("onINTEGER(${context.getValue()})")
                    triple[2] = context.getValue()
                    tripleType[2] = ETripleComponentType.INTEGER
                    parse_ws(context) {}
                    state = Turtle2ParserState.TRIPLE_END
                },
                onDECIMAL = {
                    //println("onDECIMAL(${context.getValue()})")
                    triple[2] = context.getValue()
                    tripleType[2] = ETripleComponentType.DECIMAL
                    parse_ws(context) {}
                    state = Turtle2ParserState.TRIPLE_END
                },
                onDOUBLE = {
                    //println("onDOUBLE(${context.getValue()})")
                    triple[2] = context.getValue()
                    tripleType[2] = ETripleComponentType.DOUBLE
                    parse_ws(context) {}
                    state = Turtle2ParserState.TRIPLE_END
                },
                onBOOLEAN = {
                    //println("onBOOLEAN(${context.getValue()})")
                    triple[2] = context.getValue()
                    tripleType[2] = ETripleComponentType.BOOLEAN
                    parse_ws(context) {}
                    state = Turtle2ParserState.TRIPLE_END
                })
    }

    private fun triple_end() {
        parse_triple_end(context,
                onPREDICATE_LIST1 = {
                    //println("onPREDICATE_LIST1(${context.getValue()})")
                    onTriple(triple, tripleType)
                    parse_ws(context) {}
                    state = Turtle2ParserState.PREDICATE
                },
                onOBJECT_LIST1 = {
                    //println("onOBJECT_LIST1(${context.getValue()})")
                    onTriple(triple, tripleType)
                    parse_ws(context) {}
                    state = Turtle2ParserState.OBJECT
                },
                onDOT = {
                    //println("onDOT(${context.getValue()})")
                    onTriple(triple, tripleType)
                    state = Turtle2ParserState.STATEMENT
                })
    }

    private fun triple_end_or_object_iri() {
        parse_triple_end_or_object_iri(context,
                onPN_LOCAL = {
                    //println("onPN_LOCAL(${context.getValue()})")
                    val v = context.getValue()
                    tripleType[2] = ETripleComponentType.IRI
                    if (v.endsWith(".")) {
//TODO fix the underlying bug in the parser
                        triple[2] = "<" + prefixMap[triple[2]]!! + v.substring(0, v.length - 1) + ">"
                        onTriple(triple, tripleType)
                        state = Turtle2ParserState.STATEMENT
                    } else {
                        triple[2] = "<" + prefixMap[triple[2]]!! + v + ">"
                        parse_ws(context) {}
                        state = Turtle2ParserState.TRIPLE_END
                    }
                },
                onSKIP_WS_FORCED = {
                    //println("onSKIP_WS_FORCED(${context.getValue()})")
                    triple[2] = "<" + prefixMap[triple[2]]!! + ">"
                    tripleType[2] = ETripleComponentType.IRI
                    state = Turtle2ParserState.TRIPLE_END
                },
                onPREDICATE_LIST1 = {
                    //println("onPREDICATE_LIST1(${context.getValue()})")
                    triple[2] = "<" + prefixMap[triple[2]]!! + ">"
                    tripleType[2] = ETripleComponentType.IRI
                    onTriple(triple, tripleType)
                    state = Turtle2ParserState.PREDICATE
                },
                onOBJECT_LIST1 = {
                    //println("onOBJECT_LIST1(${context.getValue()})")
                    triple[2] = "<" + prefixMap[triple[2]]!! + ">"
                    tripleType[2] = ETripleComponentType.IRI
                    onTriple(triple, tripleType)
                    state = Turtle2ParserState.OBJECT
                },
                onDOT = {
                    //println("onDOT(${context.getValue()})")
                    triple[2] = "<" + prefixMap[triple[2]]!! + ">"
                    tripleType[2] = ETripleComponentType.IRI
                    onTriple(triple, tripleType)
                    state = Turtle2ParserState.STATEMENT
                })
    }

    private fun triple_end_or_object_string_helper_2() {
        val prefix = context.getValue()
        parse_triple_end_or_object_string_typed_iri(context,
                onPN_LOCAL = {
                    //println("onPN_LOCAL(${context.getValue()})")
                    val v = context.getValue()
                    tripleType[2] = ETripleComponentType.STRING_TYPED
                    if (v.endsWith(".")) {
//TODO fix the underlying bug in the parser
                        triple[2] += "<" + prefixMap[prefix]!! + v.substring(0, v.length - 1) + ">"
                        onTriple(triple, tripleType)
                        state = Turtle2ParserState.STATEMENT
                    } else {
                        triple[2] += "<" + prefixMap[prefix]!! + v + ">"
                        parse_ws(context) {}
                        state = Turtle2ParserState.TRIPLE_END
                    }
                },
                onSKIP_WS_FORCED = {
                    //println("onSKIP_WS_FORCED(${context.getValue()})")
                    triple[2] += "<" + prefixMap[prefix]!! + ">"
                    tripleType[2] = ETripleComponentType.STRING_TYPED
                    state = Turtle2ParserState.TRIPLE_END
                },
                onPREDICATE_LIST1 = {
                    //println("onPREDICATE_LIST1(${context.getValue()})")
                    triple[2] += "<" + prefixMap[prefix]!! + ">"
                    tripleType[2] = ETripleComponentType.STRING_TYPED
                    onTriple(triple, tripleType)
                    state = Turtle2ParserState.PREDICATE
                },
                onOBJECT_LIST1 = {
                    //println("onOBJECT_LIST1(${context.getValue()})")
                    triple[2] += "<" + prefixMap[prefix]!! + ">"
                    tripleType[2] = ETripleComponentType.STRING_TYPED
                    onTriple(triple, tripleType)
                    state = Turtle2ParserState.OBJECT
                },
                onDOT = {
                    //println("onDOT(${context.getValue()})")
                    triple[2] += "<" + prefixMap[prefix]!! + ">"
                    tripleType[2] = ETripleComponentType.STRING_TYPED
                    onTriple(triple, tripleType)
                    state = Turtle2ParserState.STATEMENT
                })
    }

    private fun triple_end_or_object_string_helper_1() {
        parse_triple_end_or_object_string_typed(context,
                onIRIREF = {
                    //println("onIRIREF(${context.getValue()})")
                    triple[2] += context.getValue()
                    tripleType[2] = ETripleComponentType.STRING_TYPED
                    parse_ws(context) {}
                    state = Turtle2ParserState.TRIPLE_END
                },
                onPNAME_NS = {
                    //println("onPNAME_NS(${context.getValue()})")
                    triple_end_or_object_string_helper_2()
                })
    }

    private fun triple_end_or_object_string() {
        parse_triple_end_or_object_string(context,
                onLANGTAG = {
                    //println("onLANGTAG(${context.getValue()})")
                    triple[2] += context.getValue()
                    tripleType[2] = ETripleComponentType.STRING_LANG
                    parse_ws(context) {}
                    state = Turtle2ParserState.TRIPLE_END
                },
                onIRI1 = {
                    //println("onIRI1(${context.getValue()})")
                    triple[2] += context.getValue()
                    tripleType[2] = ETripleComponentType.STRING_TYPED
                    triple_end_or_object_string_helper_1()
                },
                onPREDICATE_LIST1 = {
                    //println("onPREDICATE_LIST1(${context.getValue()})")
                    onTriple(triple, tripleType)
                    state = Turtle2ParserState.PREDICATE
                },
                onOBJECT_LIST1 = {
                    //println("onOBJECT_LIST1(${context.getValue()})")
                    onTriple(triple, tripleType)
                    state = Turtle2ParserState.OBJECT
                },
                onDOT = {
                    //println("onDOT(${context.getValue()})")
                    onTriple(triple, tripleType)
                    state = Turtle2ParserState.STATEMENT
                },
                onSKIP_WS_FORCED = {
                    state = Turtle2ParserState.TRIPLE_END
                })
    }
}

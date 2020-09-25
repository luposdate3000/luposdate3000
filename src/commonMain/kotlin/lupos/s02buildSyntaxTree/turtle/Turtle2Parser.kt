package lupos.s02buildSyntaxTree.turtle

import kotlin.jvm.JvmField

enum class Turtle2ParserState {
    STATEMENT,
    PREDICATE,
    OBJECT,
    TRIPLE_END,
    TRIPLE_END_OR_OBJECT_IRI,
    TRIPLE_END_OR_OBJECT_STRING,
}

abstract class Turtle2Parser(input: CharIterator) {
    @JvmField
    val context = ParserContext(input)

    @JvmField
    val prefixMap = mutableMapOf<String, String>()

    @JvmField
    val triple = Array(3) { "" }

    @JvmField
    var state = Turtle2ParserState.STATEMENT
    abstract fun onTriple(triple: Array<String>)
    fun turtleDoc() {
        while (true) {
            when (state) {
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
                else -> {
                    throw Exception("unknown $state")
                }
            }
        }
    }

    fun statement() {
        parse_ws(context, {})
        parse_statement(context,
                onBASE = {
                    parse_ws_forced(context, {})
                    parse_base(context,
                            onIRIREF = {
                                prefixMap[""] = context.getValue()
                            })
                    state = Turtle2ParserState.STATEMENT
                },
                onPREFIX = {
                    parse_ws_forced(context, {})
                    parse_prefix(context,
                            onPNAME_NS = {
                                val prefix = context.getValue()
                                parse_ws_forced(context, {})
                                parse_prefix2(context, onIRIREF = {
                                    prefixMap[prefix] = context.getValue()
                                })
                            })
                    state = Turtle2ParserState.STATEMENT
                },
                onBASE2 = {
                    parse_ws_forced(context, {})
                    parse_base(context,
                            onIRIREF = {
                                prefixMap[""] = context.getValue()
                            })
                    parse_ws(context, {})
                    parseDOT(context, {})
                    state = Turtle2ParserState.STATEMENT
                },
                onPREFIX2 = {
                    parse_ws_forced(context, {})
                    parse_prefix(context,
                            onPNAME_NS = {
                                val prefix = context.getValue()
                                parse_ws_forced(context, {})
                                parse_prefix2(context, onIRIREF = {
                                    prefixMap[prefix] = context.getValue()
                                })
                            })
                    parse_ws(context, {})
                    parseDOT(context, {})
                    state = Turtle2ParserState.STATEMENT
                },
                onIRIREF = {
                    triple[0] = context.getValue()
                    parse_subject_iri_or_ws(context,
                            onPN_LOCAL = {
                                triple[0] += context.getValue()
                                parse_ws_forced(context, {})
                            },
                            onSKIP_WS_FORCED = {
                            })
                                state = Turtle2ParserState.PREDICATE
                },
                onPNAME_NS = {
                    triple[0] = context.getValue()
                    state = Turtle2ParserState.PREDICATE
                },
                onBLANK_NODE_LABEL = {
                    triple[0] = context.getValue()
                    state = Turtle2ParserState.PREDICATE
                })
    }

    fun predicate() {
        parse_predicate(context,
                onVERB1 = {
                    triple[1] = "rdf:type"
                    parse_ws_forced(context, {})
                },
                onIRIREF = {
                    triple[1] = context.getValue()
                    parse_ws_forced(context, {})
                },
                onPNAME_NS = {
                    triple[1] = context.getValue()
                    parse_predicate_iri_or_ws(context,
                            onPN_LOCAL = {
                                triple[1] += context.getValue()
                                parse_ws_forced(context, {})
                            },
                            onSKIP_WS_FORCED = {
                            })
                }        )
state = Turtle2ParserState.OBJECT
    }

    fun obj() {
        parse_obj(context,
                onIRIREF = {
                    triple[2] = context.getValue()
                    parse_ws(context, {})
                    state = Turtle2ParserState.TRIPLE_END
                },
                onPNAME_NS = {
                    triple[2] = context.getValue()
                    state = Turtle2ParserState.TRIPLE_END_OR_OBJECT_IRI
                },
                onBLANK_NODE_LABEL = {
                    triple[2] = context.getValue()
                    parse_ws(context, {})
                    state = Turtle2ParserState.TRIPLE_END
                },
                onSTRING_LITERAL_QUOTE = {
                    triple[2] = context.getValue()
                    state = Turtle2ParserState.TRIPLE_END_OR_OBJECT_STRING
                },
                onSTRING_LITERAL_SINGLE_QUOTE = {
                    triple[2] = context.getValue()
                    state = Turtle2ParserState.TRIPLE_END_OR_OBJECT_STRING
                },
                onSTRING_LITERAL_LONG_SINGLE_QUOTE = {
                    triple[2] = context.getValue()
                    state = Turtle2ParserState.TRIPLE_END_OR_OBJECT_STRING
                },
                onSTRING_LITERAL_LONG_QUOTE = {
                    triple[2] = context.getValue()
                    state = Turtle2ParserState.TRIPLE_END_OR_OBJECT_STRING
                },
                onINTEGER = {
                    triple[2] = context.getValue()
                    parse_ws(context, {})
                    state = Turtle2ParserState.TRIPLE_END
                },
                onDECIMAL = {
                    triple[2] = context.getValue()
                    parse_ws(context, {})
                    state = Turtle2ParserState.TRIPLE_END
                },
                onDOUBLE = {
                    triple[2] = context.getValue()
                    parse_ws(context, {})
                    state = Turtle2ParserState.TRIPLE_END
                },
                onBOOLEAN = {
                    triple[2] = context.getValue()
                    parse_ws(context, {})
                    state = Turtle2ParserState.TRIPLE_END
                })
    }

    fun triple_end() {
        parse_triple_end(context,
                onPREDICATE_LIST1 = {
                    onTriple(triple)
                    parse_ws(context, {})
                    state = Turtle2ParserState.PREDICATE
                },
                onOBJECT_LIST1 = {
                    onTriple(triple)
                    parse_ws(context, {})
                    state = Turtle2ParserState.OBJECT
                },
                onDOT = {
                    onTriple(triple)
                    state = Turtle2ParserState.STATEMENT
                })
    }

    fun triple_end_or_object_iri() {
        parse_triple_end_or_object_iri(context,
                onPN_LOCAL = {
                    triple[2] += context.getValue()
                    parse_ws_forced(context, {})
                    state = Turtle2ParserState.OBJECT
                },
                onSKIP_WS_FORCED = {
                    state = Turtle2ParserState.TRIPLE_END
                },
                onPREDICATE_LIST1 = {
                    onTriple(triple)
                    state = Turtle2ParserState.PREDICATE
                },
                onOBJECT_LIST1 = {
                    onTriple(triple)
                    state = Turtle2ParserState.OBJECT
                },
                onDOT = {
                    onTriple(triple)
                    state = Turtle2ParserState.STATEMENT
                })
    }

    fun triple_end_or_obj_string() {
        parse_triple_end_or_obj_string(context,
                onLANGTAG = {
                    triple[2] += context.getValue()
                    parse_ws(context, {})
                    state = Turtle2ParserState.TRIPLE_END
                },
                onIRI1 = {
                    triple[2] += context.getValue()
                    parse_triple_end_or_obj_string_typed(context,
                            onIRIREF = {
                                triple[2] += context.getValue()
                                parse_ws(context, {})
                                state = Turtle2ParserState.TRIPLE_END
                            },
                            onPNAME_NS = {
                                triple[2] += context.getValue()
                                parse_triple_end_or_obj_string_typed_iri(context,
                                        onPN_LOCAL = {
                                            triple[2] += context.getValue()
 parse_ws(context, {})
                                            state = Turtle2ParserState.TRIPLE_END
                                        },
                                        onSKIP_WS_FORCED = {
                                            state = Turtle2ParserState.TRIPLE_END
                                        }
                                                onPREDICATE_LIST1 = {
                                            onTriple(triple)
                                            state = Turtle2ParserState.PREDICATE
                                        },
                                        onOBJECT_LIST1 = {
                                            onTriple(triple)
                                            state = Turtle2ParserState.OBJECT
                                        },
                                        onDOT = {
                                            onTriple(triple)
                                            state = Turtle2ParserState.STATEMENT
                                        })
                            })
                },
                onPREDICATE_LIST1 = {
                    onTriple(triple)
                    state = Turtle2ParserState.PREDICATE
                },
                onOBJECT_LIST1 = {
                    onTriple(triple)
                    state = Turtle2ParserState.OBJECT
                },
                onDOT = {
                    onTriple(triple)
                    state = Turtle2ParserState.STATEMENT
                }, onSKIP_WS_FORCED = {
	            state = Turtle2ParserState.TRIPLE_END
                })
    }
}

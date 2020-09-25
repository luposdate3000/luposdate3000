package lupos.s02buildSyntaxTree.turtle

enum class Turtle2ParserState {
    STATEMENT,
    BASE,
    PREFIX,
    PREDICATE,
    PREDICATE_OR_SUBJECT,
    OBJECT,
    OBJECT_OR_PREDICATE,
    TRIPLE_END,
    TRIPLE_END_OR_OBJECT_IRI,
    TRIPLE_END_OR_OBJECT_STRING,
}

abstract class Turtle2Parser(input: CharIterator) {
@JVMField    val context = ParserContext(input)
@JVMField    val prefixMap = mutableMapOf<String, String>()
@JVMField    val triple = Array(3) { "" }
@JVMField    var state = Turtle2ParserState.STATEMENT
    abstract fun onTriple(triple: Array<String>)
    fun turtleDoc() {
        while (true) {
            when (state) {
                Turtle2ParserState.STATEMENT -> {
                    statement()
                }
                Turtle2ParserState.BASE -> {
                    base()
                }
                Turtle2ParserState.PREFIX -> {
                    prefix()
                }
                Turtle2ParserState.PREDICATE -> {
                    predicate()
                }
                Turtle2ParserState.PREDICATE_OR_SUBJECT -> {
                    predicate_or_subject()
                }
                Turtle2ParserState.OBJECT -> {
                    obj()
                }
                Turtle2ParserState.OBJECT_OR_PREDICATE -> {
                    obj_or_predicate()
                }
                Turtle2ParserState.TRIPLE_END -> {
                    tripleEnd()
                }
                Turtle2ParserState.TRIPLE_END_OR_OBJECT_IRI -> {
                    tripleEndOrObjectIri()
                }
                Turtle2ParserState.TRIPLE_END_OR_OBJECT_STRING -> {
                    tripleEndOrObjectString()
                }
                else {
                    throw Exception("unknown $state")
                }
            }
        }
    }

    fun statement() {
        parseStatement(context,
                onBASE = {
                    base()
                },
                onPREFIX = {
                    prefix()
                },
                onBASE2 = {
                    base()
                    parseDot(context, {})
                },
                onPREFIX2 = {
                    prefix()
                    parseDot(context, {})
                },
                onIRIREF = {
                    triple[0] = context.getValue()
                    state = Turtle2ParserState.PREDICATE_OR_SUBJECT
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

    fun base() {
        parseIRIREF(context,
                onIRIREF = {
                    prefixMap[""] = context.getValue()
                })
    }

    fun prefix() {
        parsePNAME_NS(context,
                onPNAME_NS = {
                    val prefix = context.getValue()
                    parseIRIREF(context, onIRIREF = {
                        prefixMap[prefix] = context.getValue()
                    })
                })
    }

    fun predicate_or_subject() {
        parse_predicate_or_subject(context,
                onPN_LOCAL = {
                    triple[0] += context.getValue()
                    state = Turtle2ParserState.PREDICATE
                },
                onVERB1 = {
                    triple[1] = "rdf:type"
                    state = Turtle2ParserState.OBJECT
                },
                onIRIREF = {
                    triple[1] = context.getValue()
                    state = Turtle2ParserState.OBJECT
                },
                onPNAME_NS = {
                    triple[1] = context.getValue()
                    state = Turtle2ParserState.OBJECT_OR_PREDICATE
                })
    }

    fun predicate() {
        parse_predicate(context,
                onVERB1 = {
                    triple[1] = "rdf:type"
                    state = Turtle2ParserState.OBJECT
                },
                onIRIREF = {
                    triple[1] = context.getValue()
                    state = Turtle2ParserState.OBJECT
                },
                onPNAME_NS = {
                    triple[1] = context.getValue()
                    state = Turtle2ParserState.OBJECT_OR_PREDICATE
                }
        )
    }

    fun obj() {
        parse_object(context,
                onIRIREF = {
                    triple[2] = context.getValue()
                    state = Turtle2ParserState.TRIPLE_END
                },
                onPNAME_NS = {
                    triple[2] = context.getValue()
                    state = Turtle2ParserState.TRIPLE_END_OR_OBJECT_IRI
                },
                onBLANK_NODE_LABEL = {
                    triple[2] = context.getValue()
                    state = Turtle2ParserState.TRIPLE_END
                },
                onSTRING_LITERAL_QUOTE = {
                    triple[2] = context.getValue()
                    state = TRIPLE_END_OR_OBJECT_STRING
                },
                onSTRING_LITERAL_SINGLE_QUOTE = {
                    triple[2] = context.getValue()
                    state = TRIPLE_END_OR_OBJECT_STRING
                },
                onSTRING_LITERAL_LONG_SINGLE_QUOTE = {
                    triple[2] = context.getValue()
                    state = TRIPLE_END_OR_OBJECT_STRING
                },
                onSTRING_LITERAL_LONG_QUOTE = {
                    triple[2] = context.getValue()
                    state = TRIPLE_END_OR_OBJECT_STRING
                },
                onINTEGER = {
                    triple[2] = context.getValue()
                    state = Turtle2ParserState.TRIPLE_END
                },
                onDECIMAL = {
                    triple[2] = context.getValue()
                    state = Turtle2ParserState.TRIPLE_END
                },
                onDOUBLE = {
                    triple[2] = context.getValue()
                    state = Turtle2ParserState.TRIPLE_END
                },
                onBOOLEAN = {
                    triple[2] = context.getValue()
                    state = Turtle2ParserState.TRIPLE_END
                })
    }

    fun obj_or_predicate() {
        parse_object_or_predicate(context,
                onPN_LOCAL = {
                    triple[1] += context.getValue()
                    state = Turtle2ParserState.OBJECT
                },
                onIRIREF = {
                    triple[2] = context.getValue()
                    state = Turtle2ParserState.TRIPLE_END
                }
                        onPNAME_NS ={
                    triple[2] = context.getValue()
                    state = Turtle2ParserState.TRIPLE_END_OR_OBJECT_IRI
                }
                        onBLANK_NODE_LABEL ={
                    triple[2] = context.getValue()
                    state = Turtle2ParserState.TRIPLE_END
                }
                        onSTRING_LITERAL_QUOTE ={
                    triple[2] = context.getValue()
                    state = TRIPLE_END_OR_OBJECT_STRING
                }
                        onSTRING_LITERAL_SINGLE_QUOTE ={
                    triple[2] = context.getValue()
                    state = TRIPLE_END_OR_OBJECT_STRING
                }
                        onSTRING_LITERAL_LONG_SINGLE_QUOTE ={
                    triple[2] = context.getValue()
                    state = TRIPLE_END_OR_OBJECT_STRING
                }
                        onSTRING_LITERAL_LONG_QUOTE ={
                    triple[2] = context.getValue()
                    state = TRIPLE_END_OR_OBJECT_STRING
                }
                        onINTEGER ={
                    triple[2] = context.getValue()
                    state = Turtle2ParserState.TRIPLE_END
                }
                        onDECIMAL ={
                    triple[2] = context.getValue()
                    state = Turtle2ParserState.TRIPLE_END
                }
                        onDOUBLE ={
                    triple[2] = context.getValue()
                    state = Turtle2ParserState.TRIPLE_END
                }
                        onBOOLEAN ={
                    triple[2] = context.getValue()
                    state = Turtle2ParserState.TRIPLE_END
                }

    }

    fun tripleEnd() {
        parseTripleEnd(context,
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
                }
        )
    }

    fun tripleEndOrObjectIri() {
        parseTripleEndOrObjectIri(context,
                onPN_LOCAL = {
                    triple[2] += context.getValue()
                    state = Turtle2ParserState.OBJECT
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
                }
        )
    }

    fun tripleEndOrObjectString() {
        parseTripleEndOrObjectString(context,
                onLANGTAG = {
                    triple[2] += context.getValue()
                    state = Turtle2ParserState.TRIPLE_END
                },
                onIRI1 = {
                    triple[2] += context.getValue()
                    parseTripleEndOrObjectStringTyped(context:ParserContext,
                            onIRIREF = {
                                triple[2] += context.getValue()
                                state = Turtle2ParserState.TRIPLE_END
                            },
                            onPNAME_NS = {
                                triple[2] += context.getValue()
                                parseTripleEndOrObjectIri(context,
                                        onPN_LOCAL = {
                                            triple[2] += context.getValue()
                                            state = Turtle2ParserState.OBJECT
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
                                        }                                )
                            }
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


    class ParserContext(val input: CharIterator) {
        @JVMField
        var c: Char = ' '

        @JVMField
        var buffer = StringBuilder()
        fun next() {
            c = input.next()
        }

        fun hasNext(): Boolean {
            return input.hasNext()
        }

        fun getValue(): String {
            return buffer.toString()
        }

        init {
            context.next()
        }
    }

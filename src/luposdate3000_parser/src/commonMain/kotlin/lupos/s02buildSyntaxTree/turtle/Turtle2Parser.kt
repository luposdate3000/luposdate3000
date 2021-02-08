/*
 * This file is part of the Luposdate3000 distribution (https://github.com/luposdate3000/luposdate3000).
 * Copyright (c) 2020-2021, Institute of Information Systems (Benjamin Warnke and contributors of LUPOSDATE3000), University of Luebeck
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, version 3.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package lupos.s02buildSyntaxTree.turtle

import lupos.s00misc.ETripleComponentType
import lupos.s00misc.ETripleComponentTypeExt
import lupos.s00misc.IMyInputStream
import kotlin.jvm.JvmField

public abstract class Turtle2Parser(input: IMyInputStream) {
    @JvmField
    internal val context = ParserContext(input)

    @JvmField
    internal val prefixMap = mutableMapOf<String, String>()

    @JvmField
    internal val triple = Array(3) { "" }

    @JvmField
    internal val tripleType = Array(3) { ETripleComponentTypeExt.IRI }

    @JvmField
    internal var state = Turtle2ParserStateExt.STATEMENT
    public abstract fun onTriple(triple: Array<String>, tripleType: Array<ETripleComponentType>)
    public fun turtleDoc() {
        var iter = 0
        loop@ while (true) {
            iter++
            when (state) {
                Turtle2ParserStateExt.EOF -> {
                    break@loop
                }
                Turtle2ParserStateExt.STATEMENT -> {
                    statement()
                }
                Turtle2ParserStateExt.PREDICATE -> {
                    predicate()
                }
                Turtle2ParserStateExt.OBJECT -> {
                    obj()
                }
                Turtle2ParserStateExt.TRIPLE_END -> {
                    triple_end()
                }
                Turtle2ParserStateExt.TRIPLE_END_OR_OBJECT_IRI -> {
                    triple_end_or_object_iri()
                }
                Turtle2ParserStateExt.TRIPLE_END_OR_OBJECT_STRING -> {
                    triple_end_or_object_string()
                }
            }
        }
    }

    private fun statement_helper_1() {
        parse_base(
            context,
            onIRIREF = {
                val s = context.getValue()
                prefixMap[":"] = s.substring(1, s.length - 1)
            }
        )
    }

    private fun statement_helper_2() {
        parse_prefix(
            context,
            onPNAME_NS = {
                val prefix = context.getValue()
                parse_ws_forced(context) {}
                parse_prefix2(
                    context,
                    onIRIREF = {
                        val s = context.getValue()
                        prefixMap[prefix] = s.substring(1, s.length - 1)
                    }
                )
            }
        )
    }

    private fun statement_helper_3() {
        parse_subject_iri_or_ws(
            context,
            onPN_LOCAL = {
                triple[0] = "<" + prefixMap[triple[0]]!! + context.getValue() + ">"
                tripleType[0] = ETripleComponentTypeExt.IRI
                parse_ws_forced(context) {}
            },
            onSKIP_WS_FORCED = {
                triple[0] = "<" + prefixMap[triple[0]]!! + ">"
                tripleType[0] = ETripleComponentTypeExt.IRI
            }
        )
    }

    private fun statement() {
        parse_ws(context) {}
        if (context.c == ParserContext.EOF) {
            state = Turtle2ParserStateExt.EOF
            return
        }
        parse_statement(
            context,
            onBASE = {
                parse_ws_forced(context) {}
                statement_helper_1()
                state = Turtle2ParserStateExt.STATEMENT
            },
            onPREFIX = {
                parse_ws_forced(context) {}
                statement_helper_2()
                state = Turtle2ParserStateExt.STATEMENT
            },
            onBASE2 = {
                parse_ws_forced(context) {}
                statement_helper_1()
                parse_ws(context) {}
                parse_dot(context) {}
                state = Turtle2ParserStateExt.STATEMENT
            },
            onPREFIX2 = {
                parse_ws_forced(context) {}
                statement_helper_2()
                parse_ws(context) {}
                parse_dot(context) {}
                state = Turtle2ParserStateExt.STATEMENT
            },
            onIRIREF = {
                triple[0] = context.getValue()
                tripleType[0] = ETripleComponentTypeExt.IRI
                parse_ws_forced(context) {}
                state = Turtle2ParserStateExt.PREDICATE
            },
            onPNAME_NS = {
                triple[0] = context.getValue()
                tripleType[0] = ETripleComponentTypeExt.IRI
                statement_helper_3()
                state = Turtle2ParserStateExt.PREDICATE
            },
            onBLANK_NODE_LABEL = {
                triple[0] = context.getValue()
                tripleType[0] = ETripleComponentTypeExt.BLANK_NODE
                parse_ws_forced(context) {}
                state = Turtle2ParserStateExt.PREDICATE
            }
        )
    }

    private fun predicate_helper_1() {
        parse_predicate_iri_or_ws(
            context,
            onPN_LOCAL = {
                triple[1] = "<" + prefixMap[triple[1]]!! + context.getValue() + ">"
                tripleType[1] = ETripleComponentTypeExt.IRI
                parse_ws_forced(context) {}
            },
            onSKIP_WS_FORCED = {
                triple[1] = "<" + prefixMap[triple[1]]!! + ">"
                tripleType[1] = ETripleComponentTypeExt.IRI
            }
        )
    }

    private fun predicate() {
        parse_predicate(
            context,
            onVERB1 = {
                triple[1] = "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>"
                tripleType[1] = ETripleComponentTypeExt.IRI
                parse_ws_forced(context) {}
            },
            onIRIREF = {
                triple[1] = context.getValue()
                tripleType[1] = ETripleComponentTypeExt.IRI
                parse_ws_forced(context) {}
            },
            onPNAME_NS = {
                triple[1] = context.getValue()
                tripleType[1] = ETripleComponentTypeExt.IRI
                predicate_helper_1()
            }
        )
        state = Turtle2ParserStateExt.OBJECT
    }

    private fun obj() {
        parse_obj(
            context,
            onIRIREF = {
                triple[2] = context.getValue()
                tripleType[2] = ETripleComponentTypeExt.IRI
                parse_ws(context) {}
                state = Turtle2ParserStateExt.TRIPLE_END
            },
            onPNAME_NS = {
                triple[2] = context.getValue()
                tripleType[2] = ETripleComponentTypeExt.IRI
                state = Turtle2ParserStateExt.TRIPLE_END_OR_OBJECT_IRI
            },
            onBLANK_NODE_LABEL = {
                val v = context.getValue()
                tripleType[2] = ETripleComponentTypeExt.BLANK_NODE
                if (v.endsWith(".")) {
// TODO fix the underlying bug in the parser
                    triple[2] = v.substring(0, v.length - 1)
                    onTriple(triple, tripleType)
                    state = Turtle2ParserStateExt.STATEMENT
                } else {
                    triple[2] = v
                    parse_ws(context) {}
                    state = Turtle2ParserStateExt.TRIPLE_END
                }
            },
            onSTRING_LITERAL_QUOTE = {
                triple[2] = context.getValue()
                tripleType[2] = ETripleComponentTypeExt.STRING
                state = Turtle2ParserStateExt.TRIPLE_END_OR_OBJECT_STRING
            },
            onSTRING_LITERAL_SINGLE_QUOTE = {
                triple[2] = context.getValue()
                tripleType[2] = ETripleComponentTypeExt.STRING
                state = Turtle2ParserStateExt.TRIPLE_END_OR_OBJECT_STRING
            },
            onSTRING_LITERAL_LONG_SINGLE_QUOTE = {
                triple[2] = context.getValue()
                tripleType[2] = ETripleComponentTypeExt.STRING
                state = Turtle2ParserStateExt.TRIPLE_END_OR_OBJECT_STRING
            },
            onSTRING_LITERAL_LONG_QUOTE = {
                triple[2] = context.getValue()
                tripleType[2] = ETripleComponentTypeExt.STRING
                state = Turtle2ParserStateExt.TRIPLE_END_OR_OBJECT_STRING
            },
            onINTEGER = {
                triple[2] = context.getValue()
                tripleType[2] = ETripleComponentTypeExt.INTEGER
                parse_ws(context) {}
                state = Turtle2ParserStateExt.TRIPLE_END
            },
            onDECIMAL = {
                triple[2] = context.getValue()
                tripleType[2] = ETripleComponentTypeExt.DECIMAL
                parse_ws(context) {}
                state = Turtle2ParserStateExt.TRIPLE_END
            },
            onDOUBLE = {
                triple[2] = context.getValue()
                tripleType[2] = ETripleComponentTypeExt.DOUBLE
                parse_ws(context) {}
                state = Turtle2ParserStateExt.TRIPLE_END
            },
            onBOOLEAN = {
                triple[2] = context.getValue()
                tripleType[2] = ETripleComponentTypeExt.BOOLEAN
                parse_ws(context) {}
                state = Turtle2ParserStateExt.TRIPLE_END
            }
        )
    }

    private fun triple_end() {
        parse_triple_end(
            context,
            onPREDICATE_LIST1 = {
                onTriple(triple, tripleType)
                parse_ws(context) {}
                state = Turtle2ParserStateExt.PREDICATE
            },
            onOBJECT_LIST1 = {
                onTriple(triple, tripleType)
                parse_ws(context) {}
                state = Turtle2ParserStateExt.OBJECT
            },
            onDOT = {
                onTriple(triple, tripleType)
                state = Turtle2ParserStateExt.STATEMENT
            }
        )
    }

    private fun triple_end_or_object_iri() {
        parse_triple_end_or_object_iri(
            context,
            onPN_LOCAL = {
                val v = context.getValue()
                tripleType[2] = ETripleComponentTypeExt.IRI
                if (v.endsWith(".")) {
// TODO fix the underlying bug in the parser
                    triple[2] = "<" + prefixMap[triple[2]]!! + v.substring(0, v.length - 1) + ">"
                    onTriple(triple, tripleType)
                    state = Turtle2ParserStateExt.STATEMENT
                } else {
                    triple[2] = "<" + prefixMap[triple[2]]!! + v + ">"
                    parse_ws(context) {}
                    state = Turtle2ParserStateExt.TRIPLE_END
                }
            },
            onSKIP_WS_FORCED = {
                triple[2] = "<" + prefixMap[triple[2]]!! + ">"
                tripleType[2] = ETripleComponentTypeExt.IRI
                state = Turtle2ParserStateExt.TRIPLE_END
            },
            onPREDICATE_LIST1 = {
                triple[2] = "<" + prefixMap[triple[2]]!! + ">"
                tripleType[2] = ETripleComponentTypeExt.IRI
                onTriple(triple, tripleType)
                state = Turtle2ParserStateExt.PREDICATE
            },
            onOBJECT_LIST1 = {
                triple[2] = "<" + prefixMap[triple[2]]!! + ">"
                tripleType[2] = ETripleComponentTypeExt.IRI
                onTriple(triple, tripleType)
                state = Turtle2ParserStateExt.OBJECT
            },
            onDOT = {
                triple[2] = "<" + prefixMap[triple[2]]!! + ">"
                tripleType[2] = ETripleComponentTypeExt.IRI
                onTriple(triple, tripleType)
                state = Turtle2ParserStateExt.STATEMENT
            }
        )
    }

    private fun triple_end_or_object_string_helper_2() {
        val prefix = context.getValue()
        parse_triple_end_or_object_string_typed_iri(
            context,
            onPN_LOCAL = {
                val v = context.getValue()
                tripleType[2] = ETripleComponentTypeExt.STRING_TYPED
                if (v.endsWith(".")) {
// TODO fix the underlying bug in the parser
                    triple[2] += "<" + prefixMap[prefix]!! + v.substring(0, v.length - 1) + ">"
                    onTriple(triple, tripleType)
                    state = Turtle2ParserStateExt.STATEMENT
                } else {
                    triple[2] += "<" + prefixMap[prefix]!! + v + ">"
                    parse_ws(context) {}
                    state = Turtle2ParserStateExt.TRIPLE_END
                }
            },
            onSKIP_WS_FORCED = {
                triple[2] += "<" + prefixMap[prefix]!! + ">"
                tripleType[2] = ETripleComponentTypeExt.STRING_TYPED
                state = Turtle2ParserStateExt.TRIPLE_END
            },
            onPREDICATE_LIST1 = {
                triple[2] += "<" + prefixMap[prefix]!! + ">"
                tripleType[2] = ETripleComponentTypeExt.STRING_TYPED
                onTriple(triple, tripleType)
                state = Turtle2ParserStateExt.PREDICATE
            },
            onOBJECT_LIST1 = {
                triple[2] += "<" + prefixMap[prefix]!! + ">"
                tripleType[2] = ETripleComponentTypeExt.STRING_TYPED
                onTriple(triple, tripleType)
                state = Turtle2ParserStateExt.OBJECT
            },
            onDOT = {
                triple[2] += "<" + prefixMap[prefix]!! + ">"
                tripleType[2] = ETripleComponentTypeExt.STRING_TYPED
                onTriple(triple, tripleType)
                state = Turtle2ParserStateExt.STATEMENT
            }
        )
    }

    private fun triple_end_or_object_string_helper_1() {
        parse_triple_end_or_object_string_typed(
            context,
            onIRIREF = {
                triple[2] += context.getValue()
                tripleType[2] = ETripleComponentTypeExt.STRING_TYPED
                parse_ws(context) {}
                state = Turtle2ParserStateExt.TRIPLE_END
            },
            onPNAME_NS = {
                triple_end_or_object_string_helper_2()
            }
        )
    }

    private fun triple_end_or_object_string() {
        parse_triple_end_or_object_string(
            context,
            onLANGTAG = {
                triple[2] += context.getValue()
                tripleType[2] = ETripleComponentTypeExt.STRING_LANG
                parse_ws(context) {}
                state = Turtle2ParserStateExt.TRIPLE_END
            },
            onIRI1 = {
                triple[2] += context.getValue()
                tripleType[2] = ETripleComponentTypeExt.STRING_TYPED
                triple_end_or_object_string_helper_1()
            },
            onPREDICATE_LIST1 = {
                onTriple(triple, tripleType)
                state = Turtle2ParserStateExt.PREDICATE
            },
            onOBJECT_LIST1 = {
                onTriple(triple, tripleType)
                state = Turtle2ParserStateExt.OBJECT
            },
            onDOT = {
                onTriple(triple, tripleType)
                state = Turtle2ParserStateExt.STATEMENT
            },
            onSKIP_WS_FORCED = {
                state = Turtle2ParserStateExt.TRIPLE_END
            }
        )
    }
}

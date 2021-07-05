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
package lupos.parser.turtle

import lupos.shared.IMyInputStream
import lupos.shared.dynamicArray.ByteArrayWrapper
import lupos.shared.inline.DictionaryHelper
import lupos.shared.inline.MyStringExt
import kotlin.jvm.JvmField

public abstract class Turtle2Parser(input: IMyInputStream) {
    @JvmField
    internal val context = ParserContext(input)

    @JvmField
    internal val prefixMap = mutableMapOf<String, String>(":" to "")

    @JvmField
    public val triple: Array<ByteArrayWrapper> = Array(3) { ByteArrayWrapper() }

    @JvmField
    internal var state = Turtle2ParserStateExt.STATEMENT
    public abstract fun onTriple()
    public fun parse() {
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
            }
        }
    }

    private fun statement_helper_base() {
        parse_base(
            context,
            onIRIREF = {
                val s = context.getValue()
                prefixMap[":"] = s.substring(1, s.length - 1)
            }
        )
    }

    private fun statement_helper_prefix() {
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

    private fun statement_helper_3(prefix: String) {
        parse_subject_iri_or_ws(
            context,
            onPN_LOCAL = {
                DictionaryHelper.iriToByteArray(triple[0], MyStringExt.replaceEscapes(prefixMap[prefix]!! + context.getValue(), false))
                parse_ws_forced(context) {}
            },
            onSKIP_WS_FORCED = {
                DictionaryHelper.iriToByteArray(triple[0], MyStringExt.replaceEscapes(prefixMap[prefix]!!, false))
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
                statement_helper_base()
                state = Turtle2ParserStateExt.STATEMENT
            },
            onPREFIX = {
                parse_ws_forced(context) {}
                statement_helper_prefix()
                state = Turtle2ParserStateExt.STATEMENT
            },
            onBASEA = {
                parse_ws_forced(context) {}
                statement_helper_base()
                parse_ws(context) {}
                parse_dot(context) {}
                state = Turtle2ParserStateExt.STATEMENT
            },
            onPREFIXA = {
                parse_ws_forced(context) {}
                statement_helper_prefix()
                parse_ws(context) {}
                parse_dot(context) {}
                state = Turtle2ParserStateExt.STATEMENT
            },
            onIRIREF = {
                val value = context.getValue()
                DictionaryHelper.iriToByteArray(triple[0], MyStringExt.replaceEscapes(prefixMap[":"]!! + value.substring(1, value.length - 1), false))
                parse_ws_forced(context) {}
                state = Turtle2ParserStateExt.PREDICATE
            },
            onPNAME_NS = {
                statement_helper_3(context.getValue())
                state = Turtle2ParserStateExt.PREDICATE
            },
            onBLANK_NODE_LABEL = {
                DictionaryHelper.bnodeToByteArray(triple[0], context.getValue())
                parse_ws_forced(context) {}
                state = Turtle2ParserStateExt.PREDICATE
            }
        )
    }

    private fun predicate_helper_1(prefix: String) {
        parse_predicate_iri_or_ws(
            context,
            onPN_LOCAL = {
                DictionaryHelper.iriToByteArray(triple[1], MyStringExt.replaceEscapes(prefixMap[prefix]!! + context.getValue(), false))
                parse_ws_forced(context) {}
            },
            onSKIP_WS_FORCED = {
                DictionaryHelper.iriToByteArray(triple[1], MyStringExt.replaceEscapes(prefixMap[prefix]!!, false))
            }
        )
    }

    private fun predicate() {
        parse_predicate(
            context,
            onVERBA = {
                DictionaryHelper.iriToByteArray(triple[1], "http://www.w3.org/1999/02/22-rdf-syntax-ns#type")
                parse_ws_forced(context) {}
            },
            onIRIREF = {
                val value = context.getValue()
                DictionaryHelper.iriToByteArray(triple[1], MyStringExt.replaceEscapes(prefixMap[":"]!! + value.substring(1, value.length - 1), false))
                parse_ws_forced(context) {}
            },
            onPNAME_NS = {
                predicate_helper_1(context.getValue())
            }
        )
        state = Turtle2ParserStateExt.OBJECT
    }

    private fun obj() {
        parse_obj(
            context,
            onIRIREF = {
                val value = context.getValue()
                DictionaryHelper.iriToByteArray(triple[2], MyStringExt.replaceEscapes(prefixMap[":"]!! + value.substring(1, value.length - 1), false))
                parse_ws(context) {}
                state = Turtle2ParserStateExt.TRIPLE_END
            },
            onPNAME_NS = {
                triple_end_or_object_iri(context.getValue())
            },
            onBLANK_NODE_LABEL = {
                val v = context.getValue()
                if (v.endsWith(".")) {
// TODO fix the underlying bug in the scanner
                    DictionaryHelper.bnodeToByteArray(triple[2], v.substring(0, v.length - 1))
                    onTriple()
                    state = Turtle2ParserStateExt.STATEMENT
                } else {
                    DictionaryHelper.bnodeToByteArray(triple[2], v)
                    parse_ws(context) {}
                    state = Turtle2ParserStateExt.TRIPLE_END
                }
            },
            onSTRING_LITERAL_QUOTE = {
                val s = context.getValue()
                triple_end_or_object_string(s.substring(1, s.length - 1))
            },
            onSTRING_LITERAL_SINGLE_QUOTE = {
                val s = context.getValue()
                triple_end_or_object_string(s.substring(1, s.length - 1))
            },
            onSTRING_LITERAL_LONG_SINGLE_QUOTE = {
                val s = context.getValue()
                triple_end_or_object_string(s.substring(3, s.length - 3))
            },
            onSTRING_LITERAL_LONG_QUOTE = {
                val s = context.getValue()
                triple_end_or_object_string(s.substring(3, s.length - 3))
            },
            onINTEGER = {
                DictionaryHelper.integerToByteArray(triple[2], context.getValue())
                parse_ws(context) {}
                state = Turtle2ParserStateExt.TRIPLE_END
            },
            onDECIMAL = {
                DictionaryHelper.decimalToByteArray(triple[2], context.getValue())
                parse_ws(context) {}
                state = Turtle2ParserStateExt.TRIPLE_END
            },
            onDOUBLE = {
                DictionaryHelper.doubleToByteArray(triple[2], context.getValue().toDouble())
                parse_ws(context) {}
                state = Turtle2ParserStateExt.TRIPLE_END
            },
            onBOOLEAN = {
                DictionaryHelper.booleanToByteArray(triple[2], context.getValue().lowercase() == "true")
                parse_ws(context) {}
                state = Turtle2ParserStateExt.TRIPLE_END
            }
        )
    }

    private fun triple_end() {
        parse_triple_end(
            context,
            onPREDICATE_LISTA = {
                onTriple()
                parse_ws(context) {}
                state = Turtle2ParserStateExt.PREDICATE
            },
            onOBJECT_LISTA = {
                onTriple()
                parse_ws(context) {}
                state = Turtle2ParserStateExt.OBJECT
            },
            onDOT = {
                onTriple()
                state = Turtle2ParserStateExt.STATEMENT
            }
        )
    }

    private fun triple_end_or_object_iri(arg: String) {
        parse_triple_end_or_object_iri(
            context,
            onPN_LOCAL = {
                val v = context.getValue()
                if (v.endsWith(".")) {
                    // TODO fix the underlying bug in the parser
                    DictionaryHelper.iriToByteArray(triple[2], MyStringExt.replaceEscapes(prefixMap[arg]!! + v.substring(0, v.length - 1), false))
                    onTriple()
                    state = Turtle2ParserStateExt.STATEMENT
                } else {
                    DictionaryHelper.iriToByteArray(triple[2], MyStringExt.replaceEscapes(prefixMap[arg]!! + v, false))
                    parse_ws(context) {}
                    state = Turtle2ParserStateExt.TRIPLE_END
                }
            },
            onSKIP_WS_FORCED = {
                DictionaryHelper.iriToByteArray(triple[2], MyStringExt.replaceEscapes(prefixMap[arg]!!, false))
                state = Turtle2ParserStateExt.TRIPLE_END
            },
            onPREDICATE_LISTA = {
                DictionaryHelper.iriToByteArray(triple[2], MyStringExt.replaceEscapes(prefixMap[arg]!!, false))
                onTriple()
                state = Turtle2ParserStateExt.PREDICATE
            },
            onOBJECT_LISTA = {
                DictionaryHelper.iriToByteArray(triple[2], MyStringExt.replaceEscapes(prefixMap[arg]!!, false))
                onTriple()
                state = Turtle2ParserStateExt.OBJECT
            },
            onDOT = {
                DictionaryHelper.iriToByteArray(triple[2], MyStringExt.replaceEscapes(prefixMap[arg]!!, false))
                onTriple()
                state = Turtle2ParserStateExt.STATEMENT
            }
        )
    }

    private fun triple_end_or_object_string_helper_2(arg: String, prefix: String) {
        parse_triple_end_or_object_string_typed_iri(
            context,
            onPN_LOCAL = {
                val action = {
                    val v = context.getValue()
                    if (v.endsWith(".")) {
// TODO fix the underlying bug in the parser
                        DictionaryHelper.typedToByteArray(triple[2], replaceEscapes(arg, false), replaceEscapes(prefixMap[prefix]!! + v.substring(0, v.length - 1), false))
                        onTriple()
                        state = Turtle2ParserStateExt.STATEMENT
                    } else {
                        DictionaryHelper.typedToByteArray(triple[2], replaceEscapes(arg, false), replaceEscapes(prefixMap[prefix]!! + v, false))
                        parse_ws(context) {}
                        state = Turtle2ParserStateExt.TRIPLE_END
                    }
                }
                action()
            },
            onSKIP_WS_FORCED = {
                DictionaryHelper.typedToByteArray(triple[2], replaceEscapes(arg, false), replaceEscapes(prefixMap[prefix]!!, false))
                state = Turtle2ParserStateExt.TRIPLE_END
            },
            onPREDICATE_LISTA = {
                DictionaryHelper.typedToByteArray(triple[2], replaceEscapes(arg, false), replaceEscapes(prefixMap[prefix]!!, false))
                onTriple()
                state = Turtle2ParserStateExt.PREDICATE
            },
            onOBJECT_LISTA = {
                DictionaryHelper.typedToByteArray(triple[2], replaceEscapes(arg, false), replaceEscapes(prefixMap[prefix]!!, false))
                onTriple()
                state = Turtle2ParserStateExt.OBJECT
            },
            onDOT = {
                DictionaryHelper.typedToByteArray(triple[2], replaceEscapes(arg, false), replaceEscapes(prefixMap[prefix]!!, false))
                onTriple()
                state = Turtle2ParserStateExt.STATEMENT
            }
        )
    }

    private fun replaceEscapes(s: String, strictMode: Boolean): String {
        return MyStringExt.replaceEscapes(s, strictMode)
    }

    private fun triple_end_or_object_string_helper_1(arg: String) {
        parse_triple_end_or_object_string_typed(
            context,
            onIRIREF = {
                DictionaryHelper.typedToByteArray(triple[2], MyStringExt.replaceEscapes(arg, false), MyStringExt.replaceEscapes(context.getValue(), false))
                parse_ws(context) {}
                state = Turtle2ParserStateExt.TRIPLE_END
            },
            onPNAME_NS = {
                val prefix = context.getValue()
                triple_end_or_object_string_helper_2(arg, prefix)
            }
        )
    }

    private fun triple_end_or_object_string(arg: String) {
        parse_triple_end_or_object_string(
            context,
            onLANGTAG = {
                DictionaryHelper.langToByteArray(triple[2], MyStringExt.replaceEscapes(arg, false), MyStringExt.replaceEscapes(context.getValue().substring(1), false))
                parse_ws(context) {}
                state = Turtle2ParserStateExt.TRIPLE_END
            },
            onIRIA = {
                triple_end_or_object_string_helper_1(arg)
            },
            onPREDICATE_LISTA = {
                DictionaryHelper.stringToByteArray(triple[2], MyStringExt.replaceEscapes(arg, false))
                onTriple()
                state = Turtle2ParserStateExt.PREDICATE
            },
            onOBJECT_LISTA = {
                DictionaryHelper.stringToByteArray(triple[2], MyStringExt.replaceEscapes(arg, false))
                onTriple()
                state = Turtle2ParserStateExt.OBJECT
            },
            onDOT = {
                DictionaryHelper.stringToByteArray(triple[2], MyStringExt.replaceEscapes(arg, false))
                onTriple()
                state = Turtle2ParserStateExt.STATEMENT
            },
            onSKIP_WS_FORCED = {
                DictionaryHelper.stringToByteArray(triple[2], MyStringExt.replaceEscapes(arg, false))
                state = Turtle2ParserStateExt.TRIPLE_END
            }
        )
    }
}

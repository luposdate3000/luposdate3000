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
package lupos.s02buildSyntaxTree.nQuads

import lupos.dictionary.DictionaryHelper
import lupos.s00misc.ByteArrayWrapper
import lupos.s00misc.IMyInputStream
import kotlin.jvm.JvmField

public abstract class NQuads2Parser(input: IMyInputStream) {
    @JvmField
    internal val context = ParserContext(input)

    @JvmField
    public val quad: Array<ByteArrayWrapper> = Array(4) { ByteArrayWrapper() }

    public abstract fun onQuad()
    public fun parse() {
        loop@ while (true) {
            parse_ws(context) {}
            if (context.c == ParserContext.EOF) {
                break@loop
            }
            parse_subject(
                context,
                onIRIREF = {
                    val value = context.getValue()
                    DictionaryHelper.iriToByteArray(quad[0], value.substring(1, value.length - 1))
                },
                onBLANK_NODE_LABEL = {
                    DictionaryHelper.bnodeToByteArray(quad[0], context.getValue())
                },
            )
            parse_ws_forced(context) {}
            parse_predicate(
                context,
                onIRIREF = {
                    val value = context.getValue()
                    DictionaryHelper.iriToByteArray(quad[1], value.substring(1, value.length - 1))
                },
            )
            parse_ws_forced(context) {}
            parse_object(
                context,
                onIRIREF = {
                    val value = context.getValue()
                    DictionaryHelper.iriToByteArray(quad[2], value.substring(1, value.length - 1))
                    parse_ws_forced(context) {}
                },
                onBLANK_NODE_LABEL = {
                    DictionaryHelper.bnodeToByteArray(quad[2], context.getValue())
                    parse_ws_forced(context) {}
                },
                onSTRING_LITERAL_QUOTE = {
                    val s = context.getValue()
                    parse_object_string(
                        context,
                        onIRI1 = {
                            parse_object_typed(
                                context,
                                onIRIREF = {
                                    DictionaryHelper.typedToByteArray(quad[2], s, context.getValue())
                                    parse_ws_forced(context) {}
                                }
                            )
                        },
                        onLANGTAG = {
                            DictionaryHelper.langToByteArray(quad[2], s, context.getValue().substring(1))
                            parse_ws_forced(context) {}
                        },
                        onSKIP_WS = {
                            DictionaryHelper.stringToByteArray(quad[2], s)
                        },
                    )
                }
            )
            parse_graph(
                context,
                onIRIREF = {
                    val value = context.getValue()
                    DictionaryHelper.iriToByteArray(quad[3], value.substring(1, value.length - 1))
                },
                onBLANK_NODE_LABEL = {
                    DictionaryHelper.bnodeToByteArray(quad[3], context.getValue())
                },
                onSKIP_WS = {
                    DictionaryHelper.iriToByteArray(quad[3], "")
                },
            )
            parse_ws(context) {}
            parse_dot(context) {}
            onQuad()
        }
    }
}

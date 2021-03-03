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

import lupos.s00misc.ETripleComponentType
import lupos.s00misc.ETripleComponentTypeExt
import lupos.s00misc.IMyInputStream
import kotlin.jvm.JvmField

public abstract class NQuads2Parser(input: IMyInputStream) {
    @JvmField
    internal val context = ParserContext(input)

    @JvmField
    internal val quad = Array(4) { "" }

    @JvmField
    internal val quadType = Array(4) { ETripleComponentTypeExt.IRI }

    public abstract fun onQuad(quad: Array<String>, quadType: Array<ETripleComponentType>)
    public fun parse() {
        loop@ while (true) {
            parse_ws(context) {}
            if (context.c == ParserContext.EOF) {
                break@loop
            }
            parse_subject(
                context,
                onIRIREF = {
                    quad[0] = context.getValue()
                    quadType[0] = ETripleComponentTypeExt.IRI
                },
                onBLANK_NODE_LABEL = {
                    quad[0] = context.getValue()
                    quadType[0] = ETripleComponentTypeExt.BLANK_NODE
                },
            )
            parse_ws_forced(context) {}
            parse_predicate(
                context,
                onIRIREF = {
                    quad[1] = context.getValue()
                    quadType[1] = ETripleComponentTypeExt.IRI
                },
            )
            parse_ws_forced(context) {}
            parse_object(
                context,
                onIRIREF = {
                    quad[2] = context.getValue()
                    quadType[2] = ETripleComponentTypeExt.IRI
                    parse_ws_forced(context) {}
                },
                onBLANK_NODE_LABEL = {
                    quad[2] = context.getValue()
                    quadType[2] = ETripleComponentTypeExt.BLANK_NODE
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
                                    quad[2] = s + "^^" + context.getValue()
                                    quadType[2] = ETripleComponentTypeExt.STRING_TYPED
                                    parse_ws_forced(context) {}
                                }
                            )
                        },
                        onLANGTAG = {
                            quad[2] = s + context.getValue()
                            quadType[2] = ETripleComponentTypeExt.STRING
                            parse_ws_forced(context) {}
                        },
                        onSKIP_WS = {
                            quad[2] = s
                            quadType[2] = ETripleComponentTypeExt.STRING
                        },
                    )
                }
            )
            parse_graph(
                context,
                onIRIREF = {
                    quad[3] = context.getValue()
                    quadType[3] = ETripleComponentTypeExt.IRI
                },
                onBLANK_NODE_LABEL = {
                    quad[3] = context.getValue()
                    quadType[3] = ETripleComponentTypeExt.BLANK_NODE
                },
                onSKIP_WS = {
                    quad[3] = "<>"
                    quadType[3] = ETripleComponentTypeExt.IRI
                },
            )
            parse_ws(context) {}
            parse_dot(context) {}
            onQuad(quad, quadType)
        }
    }
}

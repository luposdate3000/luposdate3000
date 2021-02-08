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
package lupos.s00misc.xmlParser
import lupos.s00misc.IMyInputStream
import lupos.s00misc.SanityCheck
import lupos.s00misc.XMLElement
public object XMLParser {
    internal fun parse(context: ParserContext, stack: MutableList<XMLElement>): Boolean {
        var res = false
        parse_element_tag_or_immediate_close_char(
            context,
            onELEMENT_END_PART = {
                parse_element_tag(
                    context,
                    onTAG = {
                        SanityCheck {
                            val tag = context.getValue()
                            SanityCheck.check { stack[stack.size - 1].tag == tag }
                        }
                        stack.removeAt(stack.size - 1)
                        parse_ws(context, {})
                        parse_element_close(context, {})
                        parse_ws(context, {})
                        if (context.c != ParserContext.EOF) {
                            parse_element_start(context, {})
                            res = true
                        }
                    }
                )
            },
            onTAG = {
                val element = XMLElement(context.getValue())
                if (element != null) {
                    var loop = true
                    while (loop) {
                        parse_ws(context, {})
                        parse_attribute_or_close_tag(
                            context,
                            onATTRIBUTE_NAME = {
                                var attributeName = context.getValue()
                                parse_attribute_assinment(context, {})
                                parse_attribute_value(
                                    context,
                                    onATTRIBUTE_VALUE = {
                                        val attributeValue = context.getValue()
                                        element.addAttribute(attributeName, attributeValue.substring(1, attributeValue.length - 1))
                                    }
                                )
                            },
                            onELEMENT_CLOSE_IMMEDIATELY = {
                                loop = false
                                stack[stack.size - 1].addContent(element)
                                parse_ws(context, {})
                                if (context.c != ParserContext.EOF) {
                                    parse_element_start(context, {})
                                    res = true
                                }
                            },
                            onELEMENT_CLOSE_LATER = {
                                loop = false
                                stack[stack.size - 1].addContent(element)
                                stack.add(element)
                                var content = ""
                                parse_ws(context, { content = context.getValue() })
                                parse_content_or_child(
                                    context,
                                    onELEMENT_CONTENT = {
                                        content += context.getValue()
                                        element.addContent(content)
                                        parse_element_start(context, {})
                                        res = true
                                    },
                                    onELEMENT_START = {
                                        res = true
                                    }
                                )
                            }
                        )
                    }
                }
            }
        )
        return res
    }
    public operator fun invoke(input: IMyInputStream): XMLElement {
        val context = ParserContext(input)
        var stack = mutableListOf(XMLElement("root"))
        parse_ws(context, {})
        parse_element_start(context, {})
        var loop = true
        while (loop) {
            loop = parse(context, stack)
        }
        val c = stack[0].childs
        return c[c.size - 1]
    }
}

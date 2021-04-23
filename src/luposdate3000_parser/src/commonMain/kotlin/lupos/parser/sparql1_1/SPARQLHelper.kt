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
package lupos.parser.sparql1_1

import kotlin.jvm.JvmField

public abstract class ASTNode(@JvmField public val children: Array<ASTNode>) {
    public override fun toString(): String {
        return toString("")
    }

    public open fun toString(indentation: String): String {
        var result = indentation + nodeToString() + "\r\n"
        result += toString(this.children, "$indentation  ")
        return result
    }

    public fun toString(nodes: Array<out ASTNode>, indentation: String): String {
        var result = ""
        for (element in nodes) {
            result += element.toString(indentation)
        }
        return result
    }

    public open fun nodeToString(): String {
        return "classname"
    }

    @Suppress("NOTHING_TO_INLINE")
    protected inline fun propertyToString(indentation2: String, indentation3: String, propertyname: String, property: Array<out ASTNode>): String {
        var result = ""
        if (property.isNotEmpty()) {
            result += "$indentation2$propertyname:\r\n"
            result += toString(property, indentation3)
        }
        return result
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun <T> getChildrensValues(visitor: Visitor<T>): List<T> {
        return List(children.size) { children[it].visit(visitor) }
    }

    public open fun <T> visit(visitor: Visitor<T>): T {
        return visitor.visit(this, this.getChildrensValues(visitor))
    }
}

public abstract class ASTUnaryOperation(child: ASTNode) : ASTNode(arrayOf(child))
public abstract class ASTUnaryOperationFixedName(child: ASTNode, @JvmField public val name: String) : ASTNode(arrayOf(child)) {
    override fun nodeToString(): String {
        return name
    }
}

public abstract class ASTBinaryOperationFixedName(left: ASTNode, right: ASTNode, @JvmField public val name: String) : ASTNode(arrayOf(left, right)) {
    override fun nodeToString(): String {
        return name
    }
}

public abstract class ASTLeafNode : ASTNode(arrayOf())

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
package lupos.operator.arithmetik.noinput

import lupos.operator.arithmetik.AOPBase
import lupos.shared.EOperatorIDExt
import lupos.shared.IQuery
import lupos.shared.SanityCheck
import lupos.shared.ValueDefinition
import lupos.shared.XMLElement
import lupos.shared.dictionary.DictionaryExt
import lupos.shared.dynamicArray.ByteArrayWrapper
import lupos.shared.operator.IOPBase
import lupos.shared.operator.iterator.ColumnIteratorQueue
import lupos.shared.operator.iterator.IteratorBundle
import lupos.shared.operator.noinput.IAOPVariable
import lupos.shared_inline.DictionaryHelper
import kotlin.jvm.JvmField

public class AOPVariable public constructor(query: IQuery, @JvmField public var name: String) : AOPBase(query, EOperatorIDExt.AOPVariableID, "AOPVariable", arrayOf()), IAOPVariable {
    override fun getName(): String = name
    override fun toSparql(): String = "?$name".replace("#", "LuposVariable")
    override fun syntaxVerifyAllVariableExists(additionalProvided: List<String>, autocorrect: Boolean) {}
    override fun getRequiredVariableNames(): List<String> = listOf(name)
    override /*suspend*/ fun toXMLElement(partial: Boolean): XMLElement = super.toXMLElement(partial).addAttribute("name", name)
    override fun cloneOP(): IOPBase = AOPVariable(query, this.getName())
    override fun equals(other: Any?): Boolean = other is AOPVariable && name == other.name
    override fun evaluate(row: IteratorBundle): () -> ValueDefinition {
        val buffer = ByteArrayWrapper()
        val tmp = row.columns[name]
        return if (tmp == null) {
            {
                DictionaryExt.undefValue2
            }
        } else {
            SanityCheck.check { tmp is ColumnIteratorQueue }
            val column = tmp as ColumnIteratorQueue
            {
                query.getDictionary().getValue(buffer, column.tmp)
                DictionaryHelper.byteArrayToValueDefinition(buffer)
            }
        }
    }

    override fun evaluateID(row: IteratorBundle): () -> Int {
        val tmp = row.columns[name]
        return if (tmp == null) {
            {
                DictionaryExt.undefValue
            }
        } else {
            SanityCheck.check { tmp is ColumnIteratorQueue }
            val column = tmp as ColumnIteratorQueue
            {
                column.tmp
            }
        }
    }

    public override fun replaceVariableWithAnother(name: String, name2: String, parent: IOPBase, parentIdx: Int): IOPBase {
        SanityCheck.check { parent.getChildren()[parentIdx] == this }
        if (this.name == name) {
            return AOPVariable(query, name2)
        }
        for (i in this.getChildren().indices) {
            this.getChildren()[i] = this.getChildren()[i].replaceVariableWithAnother(name, name2, this, i)
        }
        return this
    }

    public override fun replaceVariableWithConstant(name: String, value: Int): IOPBase {
        if (this.name == name) {
            return AOPConstant(query, value)
        }
        for (i in this.getChildren().indices) {
            this.getChildren()[i] = this.getChildren()[i].replaceVariableWithConstant(name, value)
        }
        return this
    }

    public override fun replaceVariableWithUndef(name: String, existsClauses: Boolean): IOPBase {
        if (this.name == name) {
            return AOPConstant(query, DictionaryExt.undefValue2)
        }
        for (i in this.getChildren().indices) {
            this.getChildren()[i] = this.getChildren()[i].replaceVariableWithUndef(name, existsClauses)
        }
        return this
    }
}

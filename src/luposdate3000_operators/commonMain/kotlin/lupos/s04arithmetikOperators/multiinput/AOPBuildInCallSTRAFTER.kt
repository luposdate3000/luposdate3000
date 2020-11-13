package lupos.s04arithmetikOperators.multiinput

import lupos.s00misc.EOperatorID
import lupos.s03resultRepresentation.*
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.IQuery
import lupos.s04logicalOperators.iterator.IteratorBundle

class AOPBuildInCallSTRAFTER(query: IQuery, child: AOPBase, childB: AOPBase) : AOPBase(query, EOperatorID.AOPBuildInCallSTRAFTERID, "AOPBuildInCallSTRAFTER", arrayOf(child, childB)) {
    override fun toSparql(): String = "STRAFTER(" + children[0].toSparql() + ", " + children[1].toSparql() + ")"
    override fun equals(other: Any?): Boolean = other is AOPBuildInCallSTRAFTER && children[0] == other.children[0] && children[1] == other.children[1]
    override fun evaluate(row: IteratorBundle): () -> ValueDefinition {
        val childA = (children[0] as AOPBase).evaluate(row)
        val childB = (children[1] as AOPBase).evaluate(row)
        return {
            var res: ValueDefinition = ValueError()
            val a = childA()
            val b = childB()
            var filter: String? = null
            if (b is ValueSimpleLiteral) {
                filter = b.content
            } else if (b is ValueTypedLiteral) {
                filter = b.content
            } else if (b is ValueLanguageTaggedLiteral) {
                if (a is ValueLanguageTaggedLiteral) {
                    if (a.language == b.language) {
                        filter = b.content
                    }
                }
            }
            if (filter != null) {
                when (a) {
                    is ValueSimpleLiteral -> {
                        val idx = a.content.indexOf(filter)
                        res = if (idx < 0) {
                            ValueSimpleLiteral(a.delimiter, "")
                        } else {
                            ValueSimpleLiteral(a.delimiter, a.content.substring(idx + filter.length, a.content.length))
                        }
                    }
                    is ValueLanguageTaggedLiteral -> {
                        val idx = a.content.indexOf(filter)
                        res = if (idx < 0) {
                            ValueSimpleLiteral(a.delimiter, "")
                        } else {
                            ValueLanguageTaggedLiteral(a.delimiter, a.content.substring(idx + filter.length, a.content.length), a.language)
                        }
                    }
                    is ValueTypedLiteral -> {
                        val idx = a.content.indexOf(filter)
                        res = if (idx < 0) {
                            ValueSimpleLiteral(a.delimiter, "")
                        } else {
                            ValueSimpleLiteral(a.delimiter, a.content.substring(idx + filter.length, a.content.length))
                        }
                    }
                }
            }
            /*return*/res
        }
    }

    override fun cloneOP(): IOPBase = AOPBuildInCallSTRAFTER(query, children[0].cloneOP() as AOPBase, children[1].cloneOP() as AOPBase)
}

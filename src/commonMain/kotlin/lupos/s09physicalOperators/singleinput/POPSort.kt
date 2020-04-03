package lupos.s09physicalOperators.singleinput

import kotlin.jvm.JvmField
import kotlinx.coroutines.channels.Channel
import lupos.s00misc.CoroutinesHelper
import lupos.s00misc.Coverage
import lupos.s00misc.EOperatorID
import lupos.s00misc.SanityCheck
import lupos.s00misc.XMLElement
import lupos.s01io.*
import lupos.s03resultRepresentation.*
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.Variable
import lupos.s04arithmetikOperators.noinput.*
import lupos.s04logicalOperators.iterator.*
import lupos.s04logicalOperators.noinput.OPNothing
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s09physicalOperators.POPBase

class POPSort(query: Query, @JvmField val sortBy: Array<AOPVariable>, @JvmField val sortOrder: Boolean, child: OPBase) : POPBase(query, EOperatorID.POPSortID, "POPSort", arrayOf(child)) {
    override fun equals(other: Any?): Boolean = other is POPSort && sortBy == other.sortBy && sortOrder == other.sortOrder && children[0] == other.children[0]
    override fun cloneOP() = POPSort(query, sortBy, sortOrder, children[0].cloneOP())
    override fun getRequiredVariableNames(): List<String> = sortBy.map { it.name }
    override fun toSparql(): String {
        val variables = Array(sortBy.size) { sortBy[it].name }
        var child: OPBase = this
        for (i in 0 until variables.size) {
            child = child.children[0]
        }
        SanityCheck.check({ child !is POPSort })
        val sparql = child.toSparql()
        var res: String
        if (sparql.startsWith("{SELECT ")) {
            res = sparql.substring(0, sparql.length - 1)
        } else {
            res = "{SELECT *{" + sparql + "}"
        }
        res += " ORDER BY "
        if (sortOrder) {
            res += "ASC("
        } else {
            res += "DESC("
        }
        for (v in variables) {
            res += AOPVariable(query, v).toSparql() + " "
        }
        res += ")"
        res += "}"
        return res
    }

    override fun toXMLElement(): XMLElement {
        val res = XMLElement("POPSort")
        res.addAttribute("uuid", "" + uuid)
        val sortByXML = XMLElement("by")
        res.addContent(sortByXML)
        for (v in sortBy) {
            sortByXML.addContent(XMLElement("variable").addAttribute("name", v.name))
        }
        if (sortOrder) {
            res.addAttribute("order", "ASC")
        } else {
            res.addAttribute("order", "DESC")
        }
        res.addContent(childrenToXML())
        return res
    }

    override suspend fun evaluate(): ColumnIteratorRow {
        val variablestmp = getProvidedVariableNames()
        if (variablestmp.size == 0) {
            return children[0].evaluate()
        } else {
            var comparator: Comparator<Value>
            val variables = Array(variablestmp.size) { variablestmp[it] }
            if (sortOrder) {
                comparator = ValueComparatorASC(query)
            } else {
                comparator = ValueComparatorDESC(query)
            }
            val fastcomparator = ValueComparatorFast()
            for (v in sortBy.size - 1 downTo 0) {
                val highestPriority = sortBy[v].name
                var index = 0
                while (variables[index] != highestPriority) {
                    index++
                }
                for (i in index downTo 1) {
                    variables[i] = variables[i - 1]
                }
                variables[0] = highestPriority
            }
//variables string array is now sorted by column sort-priority
            val comparators = Array(variables.size) {
                var res: Comparator<Value>
                if (it < sortBy.size) {
                    res = comparator
                } else {
                    res = fastcomparator
                }
/*return*/res
            }
            val outMap = mutableMapOf<String, ColumnIterator>()
            val child = children[0].evaluate()
            val iterators = Array(variables.size) { child.columns[variables[it]]!! }
//TODO leaves should contain more than a single row of data
            val targetIterators = Array(variables.size) { mutableListOf<ColumnIterator?>() }
//the array holds intances for_ every variable
//the list index sqared is the depth of the merge sort. That means the index squared is the number of "leaves" in the mergesort-tree.
//the intention is to have a balanced merge-sort tree
            collectData@ while (true) {
                var processDone = false
                for (variableIndex in 0 until variables.size) {
//insert new single page
                    var next = iterators[variableIndex].next()
                    if (next == null) {
                        require(variableIndex == 0)
                        break@collectData
                    }
                    val iter = ColumnIteratorRepeatValue(1, next)
                    if (targetIterators[variableIndex].size == 0) {
//previously there was no node with this amount of leaves
                        targetIterators[variableIndex].add(iter)
                        processDone = true
                    } else if (targetIterators[variableIndex][0] == null) {
//there was a node with this amount of leaves, but it already is included in a larger tree
                        targetIterators[variableIndex][0] = iter
                        processDone = true
                    } else {
//merge with another node with the same amount of leaves
                        if (variableIndex > 0) {
                            targetIterators[variableIndex][0] = ColumnIteratorMergeSort(targetIterators[variableIndex][0]!!, iter, comparators[variableIndex], targetIterators[variableIndex - 1][0]!! as ColumnIteratorMergeSort, null)
                            (targetIterators[variableIndex - 1][0]!! as ColumnIteratorMergeSort).lowerPriority = targetIterators[variableIndex][0]!! as ColumnIteratorMergeSort
                        } else {
                            targetIterators[variableIndex][0] = ColumnIteratorMergeSort(targetIterators[variableIndex][0]!!, iter, comparators[variableIndex], null, null)
                        }
                    }
                }
                if (!processDone) {
                    processMergeTree(variables.size, targetIterators, 1, comparators)
                }
            }
            //collect all partial merge-sort-trees to construct an as much as possible ballanced one
            var index = 1
            for (variableIndex in 0 until variables.size) {
                targetIterators[variableIndex].add(null)
            }
            var limit = targetIterators[0].size
            while (index < limit && targetIterators[0][index] == null) {
                index++
            }
            while (index < limit) {
                if (targetIterators[0][index] == null) {
                    for (variableIndex in 0 until variables.size) {
                        targetIterators[variableIndex][index] = targetIterators[variableIndex][index - 1]
                    }
                } else {
                    processMergeTree(variables.size, targetIterators, index, comparators)
                }
                index++
            }
            if (limit == 1) {
                for (variableIndex in 0 until variables.size) {
                    outMap[variables[variableIndex]] = ColumnIteratorDebug(uuid, ColumnIterator())
                }
            } else {
                for (variableIndex in 0 until variables.size) {
                    outMap[variables[variableIndex]] = ColumnIteratorDebug(uuid, targetIterators[variableIndex][limit - 1]!!)
                }
            }
            return ColumnIteratorRow(outMap)
        }
    }

    fun processMergeTree(variablesSize: Int, targetIterators: Array<MutableList<ColumnIterator?>>, index: Int, comparators: Array<Comparator<Value>>) {
        var targetIndex = index
        require(targetIndex > 0)
        var processDone = false
        while (!processDone) {
            for (variableIndex in 0 until variablesSize) {
                val iter = targetIterators[variableIndex][targetIndex - 1]
                require(iter != null)
                if (targetIterators[variableIndex].size == targetIndex) {
//the first merge-node with this amount of leaves
                    targetIterators[variableIndex].add(iter)
                    processDone = true
                } else if (targetIterators[variableIndex][targetIndex] == null) {
//there previously was a node with this amount of leaves, but it is already merged somewhere
                    targetIterators[variableIndex][targetIndex] = iter
                    processDone = true
                } else {
//merge with another node of same size - and continue to merge the result with other larger merge-trees
                    if (variableIndex > 0) {
                        targetIterators[variableIndex][targetIndex] = ColumnIteratorMergeSort(targetIterators[variableIndex][targetIndex]!!, iter, comparators[variableIndex], targetIterators[variableIndex - 1][targetIndex]!! as ColumnIteratorMergeSort, null)
                        (targetIterators[variableIndex - 1][targetIndex]!! as ColumnIteratorMergeSort).lowerPriority = targetIterators[variableIndex][targetIndex]!! as ColumnIteratorMergeSort
                    } else {
                        targetIterators[variableIndex][targetIndex] = ColumnIteratorMergeSort(targetIterators[variableIndex][targetIndex]!!, iter, comparators[variableIndex], null, null)
                    }
                }
                targetIterators[variableIndex][targetIndex - 1] = null
            }
            targetIndex++
        }
    }
}

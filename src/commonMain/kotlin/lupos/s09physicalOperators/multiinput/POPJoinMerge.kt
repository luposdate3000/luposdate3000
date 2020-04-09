package lupos.s09physicalOperators.multiinput
import kotlin.jvm.JvmField
import kotlinx.coroutines.channels.Channel
import lupos.s00misc.*
import lupos.s00misc.CoroutinesHelper
import lupos.s00misc.Coverage
import lupos.s00misc.EOperatorID
import lupos.s00misc.XMLElement
import lupos.s01io.*
import lupos.s03resultRepresentation.*
import lupos.s03resultRepresentation.Variable
import lupos.s04logicalOperators.*
import lupos.s04logicalOperators.iterator.*
import lupos.s09physicalOperators.POPBase
class POPJoinMerge(query: Query, projectedVariables: List<String>, childA: OPBase, childB: OPBase, @JvmField val optional: Boolean) : POPBase(query, projectedVariables, EOperatorID.POPJoinMergeID, "POPJoinMerge", arrayOf(childA, childB), ESortPriority.JOIN) {
    override fun toSparql(): String {
        if (optional) {
            return "OPTIONAL{" + children[0].toSparql() + children[1].toSparql() + "}"
        }
        return children[0].toSparql() + children[1].toSparql()
    }
    override fun equals(other: Any?): Boolean {
        if (other !is POPJoinMerge) {
            return false
        }
        if (optional != other.optional) {
            return false
        }
        for (i in children.indices) {
            if (!children[i].equals(other.children[i])) {
                return false
            }
        }
        return true
    }
    override suspend fun evaluate(): ColumnIteratorRow {
        require(!optional, { "POPJoinMerge optional" })
//setup columns
        val child = Array(2) { children[it].evaluate() }
        val columnsINO = Array(2) { mutableListOf<ColumnIterator>() }
        val columnsINJ = Array(2) { mutableListOf<ColumnIterator>() }
        val columnsOUT = Array(2) { mutableListOf<ColumnIteratorChildIterator>() }
        val columnsOUTJ = mutableListOf<ColumnIteratorChildIterator>()
        val outIterators = mutableListOf<ColumnIteratorChildIterator>()
        val outMap = mutableMapOf<String, ColumnIterator>()
        val tmp = mutableListOf<String>()
        tmp.addAll(children[1].getProvidedVariableNames())
        var t: ColumnIteratorChildIterator
        for (name in children[0].getProvidedVariableNames()) {
            if (tmp.contains(name)) {
                if (projectedVariables.contains(name)) {
                    t = ColumnIteratorChildIterator()
                    outMap[name] = ColumnIteratorDebug(uuid, name, t)
                    outIterators.add(t)
                    columnsOUTJ.add(t)
                    for (i in 0 until 2) {
                        columnsINJ[i].add(0, child[i].columns[name]!!)
                    }
                } else {
                    for (i in 0 until 2) {
                        columnsINJ[i].add(child[i].columns[name]!!)
                    }
                }
                tmp.remove(name)
            } else {
                t = ColumnIteratorChildIterator()
                outIterators.add(t)
                outMap[name] = ColumnIteratorDebug(uuid, name, t)
                columnsOUT[0].add(t)
                columnsINO[0].add(child[0].columns[name]!!)
            }
        }
        for (name in tmp) {
            t = ColumnIteratorChildIterator()
            outIterators.add(t)
            outMap[name] = ColumnIteratorDebug(uuid, name, t)
            columnsOUT[1].add(t)
            columnsINO[1].add(child[1].columns[name]!!)
        }
        require(columnsINJ[0].size > 0)
        require(columnsINJ[0].size == columnsINJ[1].size)
var emptyColumnsWithJoin = columnsOUT[0].size == 0 && columnsOUT[1].size == 0 && columnsOUTJ.size == 0
if(emptyColumnsWithJoin){
println("POPJoinMerge emptyColumnsWithJoin")
t = ColumnIteratorChildIterator()
outIterators.add(t)
columnsOUTJ.add(t)
}
        val key = Array(2) { i -> Array(columnsINJ[i].size) { columnsINJ[i][it].next() } }
println("${key.map{it.map{it}}}")
        val keyCopy = Array(columnsINJ[0].size) { key[0][it] }
        var done = findNextKey(key, columnsINJ, columnsINO)
println("${key.map{it.map{it}}}")
if(emptyColumnsWithJoin)
println("POPJoinMerge $done")
        if (!done) {
            for (iterator in outIterators) {
if(emptyColumnsWithJoin)
println("POPJoinMerge init")
                iterator.onNoMoreElements = {
if(emptyColumnsWithJoin)
println("POPJoinMerge onnomore")
                    for (i in 0 until columnsINJ[0].size) {
                        keyCopy[i] = key[0][i]
                    }
                    val data = Array(2) { Array(columnsINO.size) { mutableListOf<Value>() } }
                    val countA = sameElements(key[0], keyCopy, columnsINJ[0], columnsINO[0], data[0])
                    val countB = sameElements(key[1], keyCopy, columnsINJ[1], columnsINO[1], data[1])
                    findNextKey(key, columnsINJ, columnsINO)
                    if (key[0][0] == null || key[1][0] == null) {
                        for (iterator2 in outIterators) {
                            iterator2.onNoMoreElements = iterator2::_onNoMoreElements
                        }
                    }
                    POPJoin.crossProduct(data, keyCopy, columnsOUT, columnsOUTJ, countA, countB)
                }
            }
        }
        val res= ColumnIteratorRow(outMap)
if(emptyColumnsWithJoin){
 res.hasNext = {
println("POPJoinMerge hasNext??")
                    /*return*/columnsOUTJ[0].next() != null
                }
}
return res
    }
    inline suspend fun sameElements(key: Array<Value?>, keyCopy: Array<Value?>, columnsINJ: MutableList<ColumnIterator>, columnsINO: MutableList<ColumnIterator>, data: Array<MutableList<Value>>): Int {
        var count = 0
require(keyCopy[0]!=null)
        loop@ while (true) {
            count++
            for (i in 0 until columnsINO.size) {
                data[i].add(columnsINO[i].next()!!)
            }
            for (i in 0 until columnsINJ.size) {
                key[i] = columnsINJ[i].next()
                require(key[i] != ResultSetDictionary.undefValue)
            }
            for (i in 0 until columnsINJ.size) {
                if (key[i] != keyCopy[i]) {
                    break@loop
                }
            }
        }
        return count
    }
    inline suspend fun findNextKey(key: Array<Array<Value?>>, columnsINJ: Array<MutableList<ColumnIterator>>, columnsINO: Array<MutableList<ColumnIterator>>): Boolean {
        var done = true
        if (key[0][0] != null && key[1][0] != null) {
done=false
            loop@ while (true) {
                for (i in 0 until columnsINJ[0].size) {
                    val a = key[0][i]!!
                    val b = key[1][i]!!
println("$i $a $b")
                    if (a < b) {
                        for (j in 0 until columnsINO[0].size) {
                            columnsINO[0][j].next()
                        }
                        for (j in 0 until columnsINJ[0].size) {
                            key[0][j] = columnsINJ[0][j].next()
                            require(key[0][j] != ResultSetDictionary.undefValue)
                            done = key[0][j] == null
                            if (done) {
                                require(j == 0)
                                break@loop
                            }
                        }
                        continue@loop
                    } else if (a > b) {
                        for (j in 0 until columnsINO[1].size) {
                            columnsINO[1][j].next()
                        }
                        for (j in 0 until columnsINJ[1].size) {
                            key[1][j] = columnsINJ[1][j].next()
                            require(key[1][j] != ResultSetDictionary.undefValue)
                            done = key[1][j] == null
                            if (done) {
                                require(j == 0)
                                break@loop
                            }
                        }
                        continue@loop
                    }
                }
                break@loop
            }
        }
        return done
    }
    override fun toXMLElement() = super.toXMLElement().addAttribute("optional", "" + optional)
    override fun cloneOP() = POPJoinMerge(query, projectedVariables, children[0].cloneOP(), children[1].cloneOP(), optional)
}

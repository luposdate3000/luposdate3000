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
package lupos.triple_store_manager
import lupos.operator.arithmetik.noinput.AOPVariable
import lupos.operator.physical.singleinput.POPSort
import lupos.shared.BugException
import lupos.shared.DictionaryValueType
import lupos.shared.DictionaryValueTypeArray
import lupos.shared.EIndexPattern
import lupos.shared.EIndexPatternExt
import lupos.shared.EIndexPatternHelper
import lupos.shared.EModifyType
import lupos.shared.IQuery
import lupos.shared.ITripleStoreDescription
import lupos.shared.ITripleStoreDescriptionModifyCache
import lupos.shared.ITripleStoreIndexDescription
import lupos.shared.LuposHostname
import lupos.shared.LuposStoreKey
import lupos.shared.Luposdate3000Instance
import lupos.shared.NoValidIndexFoundException
import lupos.shared.SanityCheck
import lupos.shared.UnknownTripleStoreTypeException
import lupos.shared.myPrintStackTrace
import lupos.shared.operator.IAOPBase
import lupos.shared.operator.IOPBase
import lupos.shared.operator.noinput.IAOPConstant
import lupos.shared.operator.noinput.IAOPVariable
import kotlin.jvm.JvmField

public class TripleStoreDescription(
    @JvmField internal val graph: String,
    @JvmField internal val indices: Array<TripleStoreIndexDescription>,
    @JvmField internal val instance: Luposdate3000Instance,
) : ITripleStoreDescription {
    override fun getIndices(): List<ITripleStoreIndexDescription> = indices.map { it as ITripleStoreIndexDescription }
    override fun toMetaString(): String {
        val res = StringBuilder()
        res.append(graph)
        res.append("|")
        for (idx in indices) {
            when (idx) {
                is TripleStoreIndexDescriptionPartitionedByID -> {
                    res.append("PartitionedByID;${EIndexPatternExt.names[idx.idx_set[0]]};${idx.partitionCount};${idx.partitionColumn}")
                    for (i in 0 until idx.partitionCount) {
                        res.append(";${idx.hostnames[i]};${idx.keys[i]}")
                    }
                    res.append("|")
                }
                is TripleStoreIndexDescriptionPartitionedByKey -> {
                    res.append("PartitionedByKey;${EIndexPatternExt.names[idx.idx_set[0]]};${idx.partitionCount}")
                    for (i in 0 until idx.partitionCount) {
                        res.append(";${idx.hostnames[i]};${idx.keys[i]}")
                    }
                    res.append("|")
                }
                is TripleStoreIndexDescriptionSimple -> {
                    res.append("Simple;${EIndexPatternExt.names[idx.idx_set[0]]};${idx.hostname};${idx.key}|")
                }
                else -> throw UnknownTripleStoreTypeException()
            }
        }
        return res.toString()
    }

    public companion object {
        internal var hadShownHistogramStacktrace = false
        public operator fun invoke(metaString: String, instance: Luposdate3000Instance): TripleStoreDescription {
            val indices = mutableListOf<TripleStoreIndexDescription>()
            var graph: String? = null
            val metad = metaString.split("|")
            for (meta in metad) {
                if (graph == null) {
                    graph = meta
                } else {
                    val args = meta.split(";")
                    if (args.size > 1) {
                        when (args[0]) {
                            "PartitionedByID" -> {
                                val idx = TripleStoreIndexDescriptionPartitionedByID(EIndexPatternExt.names.indexOf(args[1]), args[2].toInt(), args[3].toInt(), instance)
                                for (i in 0 until args[2].toInt()) {
                                    idx.hostnames[i] = args[4 + i * 2]
                                    idx.keys[i] = args[4 + i * 2 + 1]
                                }
                                indices.add(idx)
                            }
                            "PartitionedByKey" -> {
                                val idx = TripleStoreIndexDescriptionPartitionedByKey(EIndexPatternExt.names.indexOf(args[1]), args[2].toInt(), instance)
                                for (i in 0 until args[2].toInt()) {
                                    idx.hostnames[i] = args[3 + i * 2]
                                    idx.keys[i] = args[3 + i * 2 + 1]
                                }
                                indices.add(idx)
                            }
                            "Simple" -> {
                                val idx = TripleStoreIndexDescriptionSimple(EIndexPatternExt.names.indexOf(args[1]), instance)
                                idx.hostname = args[2]
                                idx.key = args[3]
                                indices.add(idx)
                            }
                            else -> throw UnknownTripleStoreTypeException()
                        }
                    }
                }
            }
            val res = TripleStoreDescription(graph!!, indices.toTypedArray(), instance)
            for (idx in indices) {
                idx.tripleStoreDescription = res
            }
            return res
        }
    }

    internal fun getAllLocations(): List<Pair<LuposHostname, LuposStoreKey>> {
        val res = mutableListOf<Pair<LuposHostname, LuposStoreKey>>()
        for (idx in indices) {
            res.addAll(idx.getAllLocations())
        }
        return res
    }

    override fun modify_create_cache(query: IQuery, type: EModifyType, sortedBy: EIndexPattern, isSorted: Boolean): ITripleStoreDescriptionModifyCache {
        return TripleStoreDescriptionModifyCache(query, this, type, sortedBy, instance, isSorted, null)
    }

    override fun getIterator(query: IQuery, params: Array<IAOPBase>, idx: EIndexPattern): IOPBase {
        for (index in indices) {
            if (index.hasPattern(idx)) {
                val projectedVariables = mutableListOf<String>()
                for (param in params) {
                    if (param is AOPVariable && param.name != "_") {
                        projectedVariables.add(param.name)
                    }
                }
                return POPTripleStoreIterator(query, projectedVariables, index, arrayOf(params[0], params[1], params[2]))
            }
        }
// fallback::
        for (index in indices) {
            val projectedVariables = mutableListOf<String>()
            for (param in params) {
                if (param is AOPVariable && param.name != "_") {
                    projectedVariables.add(param.name)
                }
            }
            if (projectedVariables.size > 0) {
                val sortBy = EIndexPatternHelper.tripleIndicees[idx].map { params[it] }.filter { it is AOPVariable }.map { it as AOPVariable }.filter { it.name != "_" }
                return POPSort(query, projectedVariables, sortBy.toTypedArray(), true, POPTripleStoreIterator(query, projectedVariables, index, arrayOf(params[0], params[1], params[2])))
            } else {
                return POPTripleStoreIterator(query, projectedVariables, index, arrayOf(params[0], params[1], params[2]))
            }
        }

        throw NoValidIndexFoundException()
    }

    override fun getHistogram(query: IQuery, params: Array<IAOPBase>, idx: EIndexPattern): Pair<Int, Int> {
        var variableCount = 0
        val filter2 = mutableListOf<DictionaryValueType>()
        for (ii in 0 until 3) {
            val i = EIndexPatternHelper.tripleIndicees[idx][ii]
            val param = params[i]
            if (param is IAOPConstant) {
                filter2.add(query.getDictionary().valueToGlobal(param.getValue()))
            } else if (param is IAOPVariable) {
                if (param.getName() != "_") {
                    variableCount++
                }
            } else {
                SanityCheck.checkUnreachable()
            }
        }
        if (variableCount != 1) {
            throw BugException("TripleStoreFeature", "Filter can not be calculated using multipe variables at once. ${params.map { it.toSparql() }}")
        }
        val filter = DictionaryValueTypeArray(filter2.size) { filter2[it] }
        for (index in indices) {
            if (index.hasPattern(idx)) {
                var first = 0
                var second = 0
                for ((first1, second1) in index.getAllLocations()) {
                    if (first1 == ((query.getInstance().tripleStoreManager!!) as TripleStoreManagerImpl).localhost) {
                        val tmp = ((query.getInstance().tripleStoreManager!!) as TripleStoreManagerImpl).localStoresGet()[second1]!!.getHistogram(query, filter)
                        first += tmp.first
                        second += tmp.second
                    } else {
                        try {
                            val conn = instance.communicationHandler!!.openConnection(first1, "/distributed/query/histogram", mapOf("tag" to second1), query.getTransactionID().toInt())
                            conn.second.writeInt(filter.size)
                            for (i in 0 until filter.size) {
                                conn.second.writeDictionaryValueType(filter[i])
                            }
                            first += conn.first.readInt()
                            second += conn.first.readInt()
                            conn.first.close()
                            conn.second.close()
                        } catch (e: Throwable) {
                            if (!hadShownHistogramStacktrace) {
                                hadShownHistogramStacktrace = true
                                println("showing only first error at" + /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_triple_store_manager/src/commonMain/kotlin/lupos/triple_store_manager/TripleStoreDescription.kt:212"/*SOURCE_FILE_END*/)
                                e.myPrintStackTrace(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_triple_store_manager/src/commonMain/kotlin/lupos/triple_store_manager/TripleStoreDescription.kt:213"/*SOURCE_FILE_END*/)
                            }
                            first += 100
                            second += 100
                        }
                    }
                }
                return Pair(first, second)
            }
        }
        return Pair(1000, 1000)
        throw NoValidIndexFoundException()
    }
}

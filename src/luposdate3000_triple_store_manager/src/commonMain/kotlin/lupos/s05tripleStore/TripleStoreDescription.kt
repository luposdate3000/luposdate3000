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

package lupos.s05tripleStore

import lupos.s00misc.BugException
import lupos.s00misc.EIndexPattern
import lupos.s00misc.EIndexPatternExt
import lupos.s00misc.EIndexPatternHelper
import lupos.s00misc.EModifyType
import lupos.s00misc.EModifyTypeExt
import lupos.s00misc.SanityCheck
import lupos.s03resultRepresentation.ResultSetDictionaryExt
import lupos.s04arithmetikOperators.IAOPBase
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04arithmetikOperators.noinput.IAOPConstant
import lupos.s04arithmetikOperators.noinput.IAOPVariable
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.IQuery
import lupos.s04logicalOperators.iterator.ColumnIterator
import kotlin.jvm.JvmField

public class TripleStoreDescription(
    @JvmField internal val indices: Array<TripleStoreIndexDescription>
) : ITripleStoreDescription {
    public fun toMetaString(): String {
        var res = StringBuilder()
        for (idx in indices) {
            when (idx) {
                is TripleStoreIndexDescriptionPartitionedByID -> res.append("PartitionedByID:${EIndexPatternExt.names[idx.idx_set[0]]}:${idx.partitionCount}:${idx.partitionColumn}|")
                is TripleStoreIndexDescriptionPartitionedByKey -> res.append("PartitionedByKey:${EIndexPatternExt.names[idx.idx_set[0]]}:${idx.partitionCount}|")
                is TripleStoreIndexDescriptionSimple -> res.append("Simple:${EIndexPatternExt.names[idx.idx_set[0]]}|")
                else -> throw Exception("unexpected type")
            }
        }
        return res.toString()
    }

    public companion object {
        public operator fun invoke(metaString: String): TripleStoreDescription {
            var indices = mutableListOf<TripleStoreIndexDescription>()
            val metad = metaString.split("|")
            for (meta in metad) {
                val args = meta.split(":")
                if (args.size > 1) {
                    when (args[0]) {
                        "PartitionedByID" -> indices.add(TripleStoreIndexDescriptionPartitionedByID(EIndexPatternExt.names.indexOf(args[1]), args[2].toInt(), args[3].toInt()))
                        "PartitionedByKey" -> indices.add(TripleStoreIndexDescriptionPartitionedByKey(EIndexPatternExt.names.indexOf(args[1]), args[2].toInt()))
                        "Simple" -> indices.add(TripleStoreIndexDescriptionSimple(EIndexPatternExt.names.indexOf(args[1])))
                        else -> throw Exception("unexpected type")
                    }
                }
            }
            return TripleStoreDescription(indices.toTypedArray())
        }
    }

    public override fun getIndices(idx: EIndexPattern): List<ITripleStoreIndexDescription> {
        var res = mutableListOf<ITripleStoreIndexDescription>()
        for (index in indices) {
            if (index.hasPattern(idx)) {
                res.add(index)
            }
        }
        return res
    }

    internal fun getAllLocations(): List<Pair<LuposHostname, LuposStoreKey>> {
        var res = mutableListOf<Pair<LuposHostname, LuposStoreKey>>()
        for (idx in indices) {
            res.addAll(idx.getAllLocations())
        }
        return res
    }

    public override fun modify(query: IQuery, columns: Array<ColumnIterator>, type: EModifyType) {
        val bufSize = 128000
        val buf = IntArray(bufSize)
        val bufLimit = (bufSize / 3) * 3
        var loop = true
        while (loop) {
            println("TripleStoreDescription.loop $bufSize $bufLimit")
            var i = 0
            while (i < bufLimit) {
                println("TripleStoreDescription.loop next")
                buf[i++] = columns[0].next()
                buf[i++] = columns[1].next()
                buf[i++] = columns[2].next()
                if (buf[i - 1] == ResultSetDictionaryExt.nullValue) {
                    i -= 3
                    loop = false
                    break
                }
            }

            for (index in indices) {
                println("TripleStoreDescription.loop indices")
                for (store in index.getAllLocations()) {
                    println("TripleStoreDescription.loop locations")
                    if (store.first == (tripleStoreManager as TripleStoreManagerImpl).localhost) {
                        val tmp = (tripleStoreManager as TripleStoreManagerImpl).localStores[store.second]!!
                        if (type == EModifyTypeExt.INSERT) {
                            tmp.insertAsBulk(buf, EIndexPatternHelper.tripleIndicees[index.idx_set[0]], i)
                        } else {
                            tmp.removeAsBulk(buf, EIndexPatternHelper.tripleIndicees[index.idx_set[0]], i)
                        }
                    } else {
                        throw Exception("modify send to other nodes")
                    }
                }
            }
        }
    }

    public override fun getIterator(query: IQuery, params: Array<IAOPBase>, idx: EIndexPattern): IOPBase {
        for (index in indices) {
            if (index.hasPattern(idx)) {
                val projectedVariables = mutableListOf<String>()
                for (param in params) {
                    if (param is AOPVariable) {
                        projectedVariables.add(param.name)
                    }
                }
                return POPTripleStoreIterator(query, projectedVariables, index, arrayOf<IOPBase>(params[0], params[1], params[2]))
            }
        }
        throw Exception("no valid index found")
    }

    public override fun getHistogram(query: IQuery, params: Array<IAOPBase>, idx: EIndexPattern): Pair<Int, Int> {
        println("getHistogram ${params.map { it.toString() }} ${EIndexPatternExt.names[idx]}")
        var variableCount = 0
        val filter2 = mutableListOf<Int>()
        for (ii in 0 until 3) {
            val i = EIndexPatternHelper.tripleIndicees[idx][ii]
            val param = params[i]
            if (param is IAOPConstant) {
                SanityCheck.check { filter2.size == ii }
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
        val filter = IntArray(filter2.size) { filter2[it] }
        println("filter ${filter.map { it }}")
        for (index in indices) {
            println("check index ${index.idx_set.map { EIndexPatternExt.names[it] }}")
            if (index.hasPattern(idx)) {
                var first = 0
                var second = 0
                for (store in index.getAllLocations()) {
                    println("  check location $store")
                    if (store.first == (tripleStoreManager as TripleStoreManagerImpl).localhost) {
                        val tmp = (tripleStoreManager as TripleStoreManagerImpl).localStores[store.second]!!.getHistogram(query, filter)
                        first += tmp.first
                        second += tmp.second
                    } else {
                        throw Exception("getHistogram send to remote node")
                    }
                }
                return Pair(first, second)
            }
        }
        throw Exception("no valid index found")
    }
}

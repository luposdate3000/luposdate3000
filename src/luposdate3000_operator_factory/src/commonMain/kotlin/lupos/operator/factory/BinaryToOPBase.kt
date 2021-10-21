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
package lupos.operator.factory
import lupos.operator.base.Query
import lupos.shared.dynamicArray.ByteArrayWrapper

public typealias BinaryToOPBaseMap = (query: Query, data: ByteArrayWrapper, offset: Int) -> IteratorBundle
public typealias OPBaseToBinaryMap = (op: IOPBase, data: ByteArrayWrapper) -> Int/*offset*/

public object BinaryToOPBase {
    public var operatorMapDecode: Array< BinaryToOPBaseMap?> = Array(0) { null }
    public var operatorMapEncode: Array< OPBaseToBinaryMap?> = Array(0) { null }
    public fun assignOperatorDecode(operatorID: Int, operator: BinaryToOPBaseMap) {
        if (operatorMapDecode.size <= operatorID) {
            val s = operatorMapDecode.size
            if (s <16) {
                s = 16
            }
            while (s <operatorID) {
                s = s * 2
            }
            val tmp = Array< BinaryToOPBaseMap?>(s) { null }
            operatorMapDecode.copyInto(tmp)
            operatorMapDecode = tmp
        }
        operatorMapDecode[operatorID] = operator
    }
    public fun assignOperatorEncode(operatorID: Int, operator: OPBaseToBinaryMap) {
        if (operatorMapEncode.size <= operatorID) {
            val s = operatorMapEncode.size
            if (s <16) {
                s = 16
            }
            while (s <operatorID) {
                s = s * 2
            }
            val tmp = Array< BinaryToOPBaseMap?>(s) { null }
            operatorMapEncode.copyInto(tmp)
            operatorMapEncode = tmp
        }
        operatorMapEncode[operatorID] = operator
    }
    public fun assignOperatorEncode(operatorID: Int, encode: OPBaseToBinaryMap, decode: BinaryToOPBaseMap) {
        assignOperatorEncode(operatorID, encode)
        assignOperatorDecode(operatorID, decode)
    }
    init {
        assignOperator(
            EOperatorIDExt.POPTripleStoreIterator,
            { op, data ->
/*
write the id first
        target: Pair<LuposHostname, LuposStoreKey>,
        query: IQuery,
        index: Int,
        children: Array<IOPBase>,
*/
            },
            { query, data, offset ->
            }
        )
    }
}

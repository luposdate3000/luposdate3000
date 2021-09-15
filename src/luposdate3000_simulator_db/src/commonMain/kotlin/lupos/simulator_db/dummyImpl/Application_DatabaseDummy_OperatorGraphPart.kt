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

package lupos.simulator_db.dummyImpl

public open class Application_DatabaseDummy_OperatorGraphPart {
    public fun getUUID(): Int {
        return 0
    }

    public fun evaluate(): ByteArray {
        return ByteArray(0)
    }

    public fun canBeEvaluatedWithoutRemoteDependencies(): Boolean {
        return true
    }

    public fun canBeEvaluatedWithTheseDependencies(l: List<Application_DatabaseDummy_OperatorGraphPart>): Boolean {
        return true
    }

    public fun mergeAndGetDependencies(l: List<Application_DatabaseDummy_OperatorGraphPart>): List<Application_DatabaseDummy_OperatorGraphPart> {
        return listOf()
    }

    public companion object {
        public fun fromByteArray(b: ByteArray): List<Application_DatabaseDummy_OperatorGraphPart> {
            return listOf()
        }

        public fun encodeToByteArray(l: List<Application_DatabaseDummy_OperatorGraphPart>): ByteArray {
            return ByteArray(0) // DB can filter here to reduce network-amount
        }
    }
}

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

import lupos.simulator_db.IDatabasePackage

public class PreprocessingPackage(
    public val destinationAddresses: IntArray, // Richtung triple store
    public val operatorGraphParts: ByteArray,
    public val senderAddress: Int, // dies MUSS ein DB-node sein ... von wo kommt das paket
    public val queryID: Int, // die ist immer gleich für alles was zu einem "QueryPackage" gehört
) : IDatabasePackage {
    override fun getPackageSizeInBytes(): Int {
        @Suppress("UnnecessaryVariable")
        val dummySize = 20
        return dummySize
    }

    override fun getContentLogString(): String {
        return "PreprocessingPackage(dests=${destinationAddresses.contentToString()}, operatorGraphParts=${operatorGraphParts.contentToString()}, senderAddress=$senderAddress, queryID=$queryID)"
    }
}

public class ChoosenOperatorPackage(
    // siehe #2
    public val destinationAddress: Int, // Richtung root-node
    public val senderAddress: Int,
    public val operators: IntArray, // zeigt an welche "operatorGraphParts" teile berechnet werden - dadurch ist schnell klar, welcher node was berechnet
    public val queryID: Int,
) : IDatabasePackage {
    override fun getPackageSizeInBytes(): Int {
        @Suppress("UnnecessaryVariable")
        val dummySize = 20
        return dummySize
    }

    override fun getContentLogString(): String {
        return "ChoosenOperatorPackage(dests=$destinationAddress, senderAddress=$senderAddress, operators=${operators.contentToString()}, queryID=$queryID)"
    }
}

public class ResultPackage(
    public val result: ByteArray, // die Nutzdaten ... zurzeit alles als ein Block, später besser bidirektionales streaming, wobei primär Richtung root-node gesendet wird.
    public val destinationAddress: Int, // Richtung root-node
    public val senderAddress: Int,
    public val queryID: Int,
    public val operatorID: Int, // damit der empfänger weiß, was für ein ergebnis dies ist ... kann ggf in "result" integriert werden
) : IDatabasePackage {
    override fun getPackageSizeInBytes(): Int {
        @Suppress("UnnecessaryVariable")
        val dummySize = 20
        return dummySize
    }

    override fun getContentLogString(): String {
        return "ResultPackage(result=${result.contentToString()}, destinationAddress=$destinationAddress, senderAddress=$senderAddress, queryID=$queryID, operatorID=$operatorID)"
    }
}

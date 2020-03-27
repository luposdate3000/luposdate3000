package lupos.s00misc

import kotlin.jvm.JvmField

import lupos.s04arithmetikOperators.ResultVektorRaw
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.ResultIterator

enum class ENetworkMessageType {
    NONE, TRIPLE_ADD, DICTIONARY_ENTRY, FINISH, GRAPH_CLEAR_ALL
}

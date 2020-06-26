package lupos.s16network

import lupos.s00misc.Coverage

enum class ServerCommunicationHeader {
    LOGMESSAGE,
    COMMIT,
    INSERT,
    IMPORT,
    DELETE,
    CLEAR_ALL_GRAPH,
    CLEAR_GRAPH,
    CREATE_GRAPH,
    DROP_GRAPH,
    GET_TRIPLES,
    GET_HISTOGRAM,
    RESPONSE_FINISHED,
    RESPONSE_TRIPLES,
    RESPONSE_TRIPLES_COUNT,
    RESPONSE_HISTOGRAM
}

package lupos.s14endpoint

import lupos.s00misc.EOperatorID

object EndpointImpl {
    val fullname = "localhost:80"
    val REQUEST_BINARY = arrayOf("/binary")
    val REQUEST_TRIPLE_ADD = arrayOf("/triple/add", "graph", "id", "s", "p", "o", "idx")
    val REQUEST_TRIPLE_GET = arrayOf("/triple/get", "graph", "id", "s", "p", "o", "sv", "pv", "ov", "idx")
    val REQUEST_TRIPLE_DELETE = arrayOf("/triple/delete", "graph", "id", "s", "p", "o", "sv", "pv", "ov", "idx")
    val REQUEST_COMMIT = arrayOf("/commit", "id")
    val REQUEST_TRACE_PRINT = arrayOf("/trace/print")
    val REQUEST_SPARQL_QUERY = arrayOf("/sparql/query", "query")
    val REQUEST_GRAPH_CLEAR_ALL = arrayOf("/graph/clear")
    val REQUEST_GRAPH_OPERATION = arrayOf("/graph/operation", "name", "type")
    val REQUEST_TURTLE_INPUT = arrayOf("/import/turtle", "data")
    val REQUEST_XML_INPUT = arrayOf("/import/xml", "data")
    val REQUEST_PEERS_LIST = arrayOf("/peers/list")
    val REQUEST_PEERS_JOIN = arrayOf("/peers/join", "hostname")
    val REQUEST_PEERS_JOIN_INTERNAL = arrayOf("/peers/join_internal", "hostname")
    val REQUEST_PEERS_SELF_TEST = arrayOf("/peers/self_test")
    val REQUEST_OPERATOR_QUERY = arrayOf("operator/query", "query")
}

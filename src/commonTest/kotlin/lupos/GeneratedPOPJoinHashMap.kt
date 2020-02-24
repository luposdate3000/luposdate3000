package lupos

import lupos.s00misc.*
import lupos.s02buildSyntaxTree.sparql1_1.*
import lupos.s03resultRepresentation.*
import lupos.s04arithmetikOperators.*
import lupos.s04arithmetikOperators.multiinput.*
import lupos.s04arithmetikOperators.noinput.*
import lupos.s04arithmetikOperators.singleinput.*
import lupos.s04logicalOperators.*
import lupos.s04logicalOperators.noinput.*
import lupos.s04logicalOperators.singleinput.*
import lupos.s04logicalOperators.singleinput.modifiers.*
import lupos.s08logicalOptimisation.*
import lupos.s09physicalOperators.*
import lupos.s09physicalOperators.multiinput.*
import lupos.s09physicalOperators.noinput.*
import lupos.s09physicalOperators.singleinput.*
import lupos.s09physicalOperators.singleinput.modifiers.*
import lupos.s11outputResult.*
import lupos.s12p2p.P2P
import lupos.s14endpoint.EndpointImpl
import lupos.s15tripleStoreDistributed.*
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.*


class GeneratedPOPJoinHashMapTest {
    constructor() {
        P2P.knownClients.clear()
        P2P.knownClients.add(EndpointImpl.fullname)
    }

    fun setAggregationMode(node: OPBase, mode: Boolean, count: Int) {
        for (n in node.children)
            setAggregationMode(n, mode, count)
        if (node is AOPAggregation) {
            node.count = count
            node.collectMode = mode
            if (node.collectMode)
                node.a = null
        }
    }

    @TestFactory
    fun test() = listOf(
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "S",
                                        "O1"
                                ), listOf(
                                        mutableMapOf(
                                                "S" to "<http://www.example.org/s>",
                                                "O1" to "\"0\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        ),
                                        mutableMapOf(
                                                "S" to "<http://www.example.org/s>",
                                                "O1" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        ),
                                        mutableMapOf(
                                                "S" to "<http://www.example.org/s>",
                                                "O1" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        )
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "S",
                                        "O2"
                                ), listOf(
                                        mutableMapOf(
                                                "S" to "<http://www.example.org/s>",
                                                "O2" to "\"0\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        ),
                                        mutableMapOf(
                                                "S" to "<http://www.example.org/s>",
                                                "O2" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        ),
                                        mutableMapOf(
                                                "S" to "<http://www.example.org/s>",
                                                "O2" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        )
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "S",
                                "O1",
                                "O2"
                        ), listOf(
                                mutableMapOf(
                                        "S" to "<http://www.example.org/s>",
                                        "O1" to "\"0\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "O2" to "\"0\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                        "S" to "<http://www.example.org/s>",
                                        "O1" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "O2" to "\"0\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                        "S" to "<http://www.example.org/s>",
                                        "O1" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "O2" to "\"0\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                        "S" to "<http://www.example.org/s>",
                                        "O1" to "\"0\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "O2" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                        "S" to "<http://www.example.org/s>",
                                        "O1" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "O2" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                        "S" to "<http://www.example.org/s>",
                                        "O1" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "O2" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                        "S" to "<http://www.example.org/s>",
                                        "O1" to "\"0\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "O2" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                        "S" to "<http://www.example.org/s>",
                                        "O1" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "O2" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                        "S" to "<http://www.example.org/s>",
                                        "O1" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "O2" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/aggregates/agg08b.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "s",
                                        "p",
                                        "o",
                                        "z"
                                ), listOf(
                                        mutableMapOf(
                                                "s" to "<http://example.org/s1>",
                                                "p" to "<http://example.org/p>",
                                                "o" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                                "z" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/s2>",
                                                "p" to "<http://example.org/p>",
                                                "o" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                                "z" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/s3>",
                                                "p" to "<http://example.org/p>",
                                                "o" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                                "z" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/s4>",
                                                "p" to "<http://example.org/p>",
                                                "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                                "z" to "\"5\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        )
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "s1",
                                        "p1",
                                        "z"
                                ), listOf(
                                        mutableMapOf(
                                                "s1" to "<http://example.org/s1>",
                                                "p1" to "<http://example.org/p>",
                                                "z" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        ),
                                        mutableMapOf(
                                                "s1" to "<http://example.org/s2>",
                                                "p1" to "<http://example.org/p>",
                                                "z" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        ),
                                        mutableMapOf(
                                                "s1" to "<http://example.org/s3>",
                                                "p1" to "<http://example.org/p>",
                                                "z" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        ),
                                        mutableMapOf(
                                                "s1" to "<http://example.org/s4>",
                                                "p1" to "<http://example.org/p>",
                                                "z" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        )
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o",
                                "z",
                                "s1",
                                "p1"
                        ), listOf(
                                mutableMapOf(
                                        "s" to "<http://example.org/s1>",
                                        "p" to "<http://example.org/p>",
                                        "o" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "z" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "s1" to "<http://example.org/s2>",
                                        "p1" to "<http://example.org/p>"
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/s2>",
                                        "p" to "<http://example.org/p>",
                                        "o" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "z" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "s1" to "<http://example.org/s3>",
                                        "p1" to "<http://example.org/p>"
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/s3>",
                                        "p" to "<http://example.org/p>",
                                        "o" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "z" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "s1" to "<http://example.org/s4>",
                                        "p1" to "<http://example.org/p>"
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/bind/bind03.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "s",
                                        "p",
                                        "o"
                                ), listOf(
                                        mutableMapOf(
                                                "s" to "<http://example.org/s1>",
                                                "p" to "<http://example.org/p>",
                                                "o" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/s2>",
                                                "p" to "<http://example.org/p>",
                                                "o" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/s3>",
                                                "p" to "<http://example.org/p>",
                                                "o" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/s4>",
                                                "p" to "<http://example.org/p>",
                                                "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        )
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "o",
                                        "z"
                                ), listOf(
                                        mutableMapOf(
                                                "o" to null,
                                                "z" to null
                                        ),
                                        mutableMapOf(
                                                "o" to null,
                                                "z" to null
                                        )
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o",
                                "z"
                        ), listOf(
                                mutableMapOf(
                                        "s" to "<http://example.org/s1>",
                                        "p" to "<http://example.org/p>",
                                        "o" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "z" to null
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/s2>",
                                        "p" to "<http://example.org/p>",
                                        "o" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "z" to null
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/s3>",
                                        "p" to "<http://example.org/p>",
                                        "o" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "z" to null
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/s4>",
                                        "p" to "<http://example.org/p>",
                                        "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "z" to null
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/s1>",
                                        "p" to "<http://example.org/p>",
                                        "o" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "z" to null
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/s2>",
                                        "p" to "<http://example.org/p>",
                                        "o" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "z" to null
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/s3>",
                                        "p" to "<http://example.org/p>",
                                        "o" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "z" to null
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/s4>",
                                        "p" to "<http://example.org/p>",
                                        "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "z" to null
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/bind07.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "book",
                                        "title"
                                ), listOf(
                                        mutableMapOf(
                                                "book" to "<http://example.org/book/book1>",
                                                "title" to "\"SPARQL Tutorial\""
                                        ),
                                        mutableMapOf(
                                                "book" to "<http://example.org/book/book2>",
                                                "title" to "\"The Semantic Web\""
                                        )
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "book",
                                        "price"
                                ), listOf(
                                        mutableMapOf(
                                                "book" to "<http://example.org/book/book1>",
                                                "price" to "\"42\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        ),
                                        mutableMapOf(
                                                "book" to "<http://example.org/book/book2>",
                                                "price" to "\"23\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        )
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "book",
                                "title",
                                "price"
                        ), listOf(
                                mutableMapOf(
                                        "book" to "<http://example.org/book/book1>",
                                        "title" to "\"SPARQL Tutorial\"",
                                        "price" to "\"42\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                        "book" to "<http://example.org/book/book2>",
                                        "title" to "\"The Semantic Web\"",
                                        "price" to "\"23\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/bindings/values08.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "book"
                                ), listOf(
                                        mutableMapOf(
                                                "book" to "<http://example.org/book/book1>"
                                        )
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "book",
                                        "title",
                                        "price"
                                ), listOf(
                                        mutableMapOf(
                                                "book" to "<http://example.org/book/book1>",
                                                "title" to "\"SPARQL Tutorial\"",
                                                "price" to "\"42\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        ),
                                        mutableMapOf(
                                                "book" to "<http://example.org/book/book2>",
                                                "title" to "\"The Semantic Web\"",
                                                "price" to "\"23\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        )
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "book",
                                "title",
                                "price"
                        ), listOf(
                                mutableMapOf(
                                        "book" to "<http://example.org/book/book1>",
                                        "title" to "\"SPARQL Tutorial\"",
                                        "price" to "\"42\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/bindings/values01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "o"
                                ), listOf(
                                        mutableMapOf(
                                                "o" to "<http://example.org/b>"
                                        )
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "o"
                                ), listOf(
                                        mutableMapOf(
                                                "s" to "<http://example.org/a>",
                                                "o" to "<http://example.org/b>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/a>",
                                                "o" to "\"alan@example.org\""
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/a>",
                                                "o" to "\"Alan\""
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/b>",
                                                "o" to "\"bob@example.org\""
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/b>",
                                                "o" to "\"Bob\""
                                        )
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "o",
                                "s"
                        ), listOf(
                                mutableMapOf(
                                        "o" to "<http://example.org/b>",
                                        "s" to "<http://example.org/a>"
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/bindings/values02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "o1",
                                        "o2"
                                ), listOf(
                                        mutableMapOf(
                                                "o1" to "\"Alan\"",
                                                "o2" to "\"alan@example.org\""
                                        )
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "o1",
                                        "o2"
                                ), listOf(
                                        mutableMapOf(
                                                "s" to "<http://example.org/a>",
                                                "o1" to "<http://example.org/b>",
                                                "o2" to "<http://example.org/b>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/a>",
                                                "o1" to "\"alan@example.org\"",
                                                "o2" to "<http://example.org/b>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/a>",
                                                "o1" to "\"Alan\"",
                                                "o2" to "<http://example.org/b>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/a>",
                                                "o1" to "<http://example.org/b>",
                                                "o2" to "\"alan@example.org\""
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/a>",
                                                "o1" to "\"alan@example.org\"",
                                                "o2" to "\"alan@example.org\""
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/a>",
                                                "o1" to "\"Alan\"",
                                                "o2" to "\"alan@example.org\""
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/a>",
                                                "o1" to "<http://example.org/b>",
                                                "o2" to "\"Alan\""
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/a>",
                                                "o1" to "\"alan@example.org\"",
                                                "o2" to "\"Alan\""
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/a>",
                                                "o1" to "\"Alan\"",
                                                "o2" to "\"Alan\""
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/b>",
                                                "o1" to "\"bob@example.org\"",
                                                "o2" to "\"bob@example.org\""
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/b>",
                                                "o1" to "\"Bob\"",
                                                "o2" to "\"bob@example.org\""
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/b>",
                                                "o1" to "\"bob@example.org\"",
                                                "o2" to "\"Bob\""
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/b>",
                                                "o1" to "\"Bob\"",
                                                "o2" to "\"Bob\""
                                        )
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "o1",
                                "o2",
                                "s"
                        ), listOf(
                                mutableMapOf(
                                        "o1" to "\"Alan\"",
                                        "o2" to "\"alan@example.org\"",
                                        "s" to "<http://example.org/a>"
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/bindings/values03.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "s",
                                        "p1",
                                        "o1"
                                ), listOf(
                                        mutableMapOf(
                                                "s" to "<http://example.org/a>",
                                                "p1" to "<http://xmlns.com/foaf/0.1/knows>",
                                                "o1" to "<http://example.org/b>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/a>",
                                                "p1" to "<http://xmlns.com/foaf/0.1/mbox>",
                                                "o1" to "\"alan@example.org\""
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/a>",
                                                "p1" to "<http://xmlns.com/foaf/0.1/name>",
                                                "o1" to "\"Alan\""
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/b>",
                                                "p1" to "<http://xmlns.com/foaf/0.1/mbox>",
                                                "o1" to "\"bob@example.org\""
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/b>",
                                                "p1" to "<http://xmlns.com/foaf/0.1/name>",
                                                "o1" to "\"Bob\""
                                        )
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "p2",
                                        "o2"
                                ), listOf(
                                        mutableMapOf(
                                                "s" to "<http://example.org/a>",
                                                "p2" to "<http://xmlns.com/foaf/0.1/knows>",
                                                "o2" to "<http://example.org/b>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/a>",
                                                "p2" to "<http://xmlns.com/foaf/0.1/mbox>",
                                                "o2" to "\"alan@example.org\""
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/a>",
                                                "p2" to "<http://xmlns.com/foaf/0.1/name>",
                                                "o2" to "\"Alan\""
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/b>",
                                                "p2" to "<http://xmlns.com/foaf/0.1/mbox>",
                                                "o2" to "\"bob@example.org\""
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/b>",
                                                "p2" to "<http://xmlns.com/foaf/0.1/name>",
                                                "o2" to "\"Bob\""
                                        )
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "s",
                                "p1",
                                "o1",
                                "p2",
                                "o2"
                        ), listOf(
                                mutableMapOf(
                                        "s" to "<http://example.org/a>",
                                        "p1" to "<http://xmlns.com/foaf/0.1/knows>",
                                        "o1" to "<http://example.org/b>",
                                        "p2" to "<http://xmlns.com/foaf/0.1/knows>",
                                        "o2" to "<http://example.org/b>"
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/a>",
                                        "p1" to "<http://xmlns.com/foaf/0.1/mbox>",
                                        "o1" to "\"alan@example.org\"",
                                        "p2" to "<http://xmlns.com/foaf/0.1/knows>",
                                        "o2" to "<http://example.org/b>"
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/a>",
                                        "p1" to "<http://xmlns.com/foaf/0.1/name>",
                                        "o1" to "\"Alan\"",
                                        "p2" to "<http://xmlns.com/foaf/0.1/knows>",
                                        "o2" to "<http://example.org/b>"
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/a>",
                                        "p1" to "<http://xmlns.com/foaf/0.1/knows>",
                                        "o1" to "<http://example.org/b>",
                                        "p2" to "<http://xmlns.com/foaf/0.1/mbox>",
                                        "o2" to "\"alan@example.org\""
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/a>",
                                        "p1" to "<http://xmlns.com/foaf/0.1/mbox>",
                                        "o1" to "\"alan@example.org\"",
                                        "p2" to "<http://xmlns.com/foaf/0.1/mbox>",
                                        "o2" to "\"alan@example.org\""
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/a>",
                                        "p1" to "<http://xmlns.com/foaf/0.1/name>",
                                        "o1" to "\"Alan\"",
                                        "p2" to "<http://xmlns.com/foaf/0.1/mbox>",
                                        "o2" to "\"alan@example.org\""
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/a>",
                                        "p1" to "<http://xmlns.com/foaf/0.1/knows>",
                                        "o1" to "<http://example.org/b>",
                                        "p2" to "<http://xmlns.com/foaf/0.1/name>",
                                        "o2" to "\"Alan\""
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/a>",
                                        "p1" to "<http://xmlns.com/foaf/0.1/mbox>",
                                        "o1" to "\"alan@example.org\"",
                                        "p2" to "<http://xmlns.com/foaf/0.1/name>",
                                        "o2" to "\"Alan\""
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/a>",
                                        "p1" to "<http://xmlns.com/foaf/0.1/name>",
                                        "o1" to "\"Alan\"",
                                        "p2" to "<http://xmlns.com/foaf/0.1/name>",
                                        "o2" to "\"Alan\""
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/b>",
                                        "p1" to "<http://xmlns.com/foaf/0.1/mbox>",
                                        "o1" to "\"bob@example.org\"",
                                        "p2" to "<http://xmlns.com/foaf/0.1/mbox>",
                                        "o2" to "\"bob@example.org\""
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/b>",
                                        "p1" to "<http://xmlns.com/foaf/0.1/name>",
                                        "o1" to "\"Bob\"",
                                        "p2" to "<http://xmlns.com/foaf/0.1/mbox>",
                                        "o2" to "\"bob@example.org\""
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/b>",
                                        "p1" to "<http://xmlns.com/foaf/0.1/mbox>",
                                        "o1" to "\"bob@example.org\"",
                                        "p2" to "<http://xmlns.com/foaf/0.1/name>",
                                        "o2" to "\"Bob\""
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/b>",
                                        "p1" to "<http://xmlns.com/foaf/0.1/name>",
                                        "o1" to "\"Bob\"",
                                        "p2" to "<http://xmlns.com/foaf/0.1/name>",
                                        "o2" to "\"Bob\""
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/bindings/values05.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "o1",
                                        "o2"
                                ), listOf(
                                        mutableMapOf(
                                                "o1" to "\"Alan\"",
                                                "o2" to null
                                        )
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "o1",
                                        "o2"
                                ), listOf(
                                        mutableMapOf(
                                                "s" to "<http://example.org/a>",
                                                "o1" to "<http://example.org/b>",
                                                "o2" to "<http://example.org/b>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/a>",
                                                "o1" to "\"alan@example.org\"",
                                                "o2" to "<http://example.org/b>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/a>",
                                                "o1" to "\"Alan\"",
                                                "o2" to "<http://example.org/b>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/a>",
                                                "o1" to "<http://example.org/b>",
                                                "o2" to "\"alan@example.org\""
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/a>",
                                                "o1" to "\"alan@example.org\"",
                                                "o2" to "\"alan@example.org\""
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/a>",
                                                "o1" to "\"Alan\"",
                                                "o2" to "\"alan@example.org\""
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/a>",
                                                "o1" to "<http://example.org/b>",
                                                "o2" to "\"Alan\""
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/a>",
                                                "o1" to "\"alan@example.org\"",
                                                "o2" to "\"Alan\""
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/a>",
                                                "o1" to "\"Alan\"",
                                                "o2" to "\"Alan\""
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/b>",
                                                "o1" to "\"bob@example.org\"",
                                                "o2" to "\"bob@example.org\""
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/b>",
                                                "o1" to "\"Bob\"",
                                                "o2" to "\"bob@example.org\""
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/b>",
                                                "o1" to "\"bob@example.org\"",
                                                "o2" to "\"Bob\""
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/b>",
                                                "o1" to "\"Bob\"",
                                                "o2" to "\"Bob\""
                                        )
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "o1",
                                "o2",
                                "s"
                        ), listOf(
                                mutableMapOf(
                                        "o1" to "\"Alan\"",
                                        "o2" to "<http://example.org/b>",
                                        "s" to "<http://example.org/a>"
                                ),
                                mutableMapOf(
                                        "o1" to "\"Alan\"",
                                        "o2" to "\"alan@example.org\"",
                                        "s" to "<http://example.org/a>"
                                ),
                                mutableMapOf(
                                        "o1" to "\"Alan\"",
                                        "o2" to "\"Alan\"",
                                        "s" to "<http://example.org/a>"
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/bindings/values04.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "o1",
                                        "o2"
                                ), listOf(
                                        mutableMapOf(
                                                "o1" to null,
                                                "o2" to "\"Alan\""
                                        ),
                                        mutableMapOf(
                                                "o1" to "<http://example.org/b>",
                                                "o2" to null
                                        )
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "o1",
                                        "o2"
                                ), listOf(
                                        mutableMapOf(
                                                "s" to "<http://example.org/a>",
                                                "o1" to "<http://example.org/b>",
                                                "o2" to "<http://example.org/b>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/a>",
                                                "o1" to "\"alan@example.org\"",
                                                "o2" to "<http://example.org/b>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/a>",
                                                "o1" to "\"Alan\"",
                                                "o2" to "<http://example.org/b>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/a>",
                                                "o1" to "<http://example.org/b>",
                                                "o2" to "\"alan@example.org\""
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/a>",
                                                "o1" to "\"alan@example.org\"",
                                                "o2" to "\"alan@example.org\""
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/a>",
                                                "o1" to "\"Alan\"",
                                                "o2" to "\"alan@example.org\""
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/a>",
                                                "o1" to "<http://example.org/b>",
                                                "o2" to "\"Alan\""
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/a>",
                                                "o1" to "\"alan@example.org\"",
                                                "o2" to "\"Alan\""
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/a>",
                                                "o1" to "\"Alan\"",
                                                "o2" to "\"Alan\""
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/b>",
                                                "o1" to "\"bob@example.org\"",
                                                "o2" to "\"bob@example.org\""
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/b>",
                                                "o1" to "\"Bob\"",
                                                "o2" to "\"bob@example.org\""
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/b>",
                                                "o1" to "\"bob@example.org\"",
                                                "o2" to "\"Bob\""
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/b>",
                                                "o1" to "\"Bob\"",
                                                "o2" to "\"Bob\""
                                        )
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "o1",
                                "o2",
                                "s"
                        ), listOf(
                                mutableMapOf(
                                        "o1" to "<http://example.org/b>",
                                        "o2" to "<http://example.org/b>",
                                        "s" to "<http://example.org/a>"
                                ),
                                mutableMapOf(
                                        "o1" to "<http://example.org/b>",
                                        "o2" to "\"alan@example.org\"",
                                        "s" to "<http://example.org/a>"
                                ),
                                mutableMapOf(
                                        "o1" to "<http://example.org/b>",
                                        "o2" to "\"Alan\"",
                                        "s" to "<http://example.org/a>"
                                ),
                                mutableMapOf(
                                        "o1" to "<http://example.org/b>",
                                        "o2" to "\"Alan\"",
                                        "s" to "<http://example.org/a>"
                                ),
                                mutableMapOf(
                                        "o1" to "\"alan@example.org\"",
                                        "o2" to "\"Alan\"",
                                        "s" to "<http://example.org/a>"
                                ),
                                mutableMapOf(
                                        "o1" to "\"Alan\"",
                                        "o2" to "\"Alan\"",
                                        "s" to "<http://example.org/a>"
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/bindings/values05.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "p1"
                                ), listOf(
                                        mutableMapOf(
                                                "p1" to "<http://xmlns.com/foaf/0.1/knows>"
                                        )
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "p1",
                                        "o1"
                                ), listOf(
                                        mutableMapOf(
                                                "s" to "<http://example.org/a>",
                                                "p1" to "<http://xmlns.com/foaf/0.1/knows>",
                                                "o1" to "<http://example.org/b>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/a>",
                                                "p1" to "<http://xmlns.com/foaf/0.1/mbox>",
                                                "o1" to "\"alan@example.org\""
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/a>",
                                                "p1" to "<http://xmlns.com/foaf/0.1/name>",
                                                "o1" to "\"Alan\""
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/b>",
                                                "p1" to "<http://xmlns.com/foaf/0.1/mbox>",
                                                "o1" to "\"bob@example.org\""
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/b>",
                                                "p1" to "<http://xmlns.com/foaf/0.1/name>",
                                                "o1" to "\"Bob\""
                                        )
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "p1",
                                "s",
                                "o1"
                        ), listOf(
                                mutableMapOf(
                                        "p1" to "<http://xmlns.com/foaf/0.1/knows>",
                                        "s" to "<http://example.org/a>",
                                        "o1" to "<http://example.org/b>"
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/bindings/values06.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "o2"
                                ), listOf(
                                        mutableMapOf(
                                                "o2" to "<http://example.org/b>"
                                        )
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "o1",
                                        "o2"
                                ), listOf(
                                        mutableMapOf(
                                                "s" to "<http://example.org/a>",
                                                "o1" to "<http://example.org/b>",
                                                "o2" to "<http://example.org/b>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/a>",
                                                "o1" to "\"alan@example.org\"",
                                                "o2" to "<http://example.org/b>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/a>",
                                                "o1" to "\"Alan\"",
                                                "o2" to "<http://example.org/b>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/b>",
                                                "o1" to "<http://example.org/c>",
                                                "o2" to "<http://example.org/c>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/b>",
                                                "o1" to "\"bob@example.org\"",
                                                "o2" to "<http://example.org/c>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/b>",
                                                "o1" to "\"Bob\"",
                                                "o2" to "<http://example.org/c>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/c>",
                                                "o1" to "\"alice@example.org\"",
                                                "o2" to null
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/c>",
                                                "o1" to "\"Alice\"",
                                                "o2" to null
                                        )
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "o2",
                                "s",
                                "o1"
                        ), listOf(
                                mutableMapOf(
                                        "o2" to "<http://example.org/b>",
                                        "s" to "<http://example.org/a>",
                                        "o1" to "<http://example.org/b>"
                                ),
                                mutableMapOf(
                                        "o2" to "<http://example.org/b>",
                                        "s" to "<http://example.org/a>",
                                        "o1" to "\"alan@example.org\""
                                ),
                                mutableMapOf(
                                        "o2" to "<http://example.org/b>",
                                        "s" to "<http://example.org/a>",
                                        "o1" to "\"Alan\""
                                ),
                                mutableMapOf(
                                        "o2" to "<http://example.org/b>",
                                        "s" to "<http://example.org/c>",
                                        "o1" to "\"alice@example.org\""
                                ),
                                mutableMapOf(
                                        "o2" to "<http://example.org/b>",
                                        "s" to "<http://example.org/c>",
                                        "o1" to "\"Alice\""
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/bindings/values07.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "s",
                                        "p1",
                                        "o1"
                                ), listOf(
                                        mutableMapOf(
                                                "s" to "<http://example.org/a>",
                                                "p1" to "<http://xmlns.com/foaf/0.1/knows>",
                                                "o1" to "<http://example.org/b>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/a>",
                                                "p1" to "<http://xmlns.com/foaf/0.1/mbox>",
                                                "o1" to "\"alan@example.org\""
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/a>",
                                                "p1" to "<http://xmlns.com/foaf/0.1/name>",
                                                "o1" to "\"Alan\""
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/b>",
                                                "p1" to "<http://xmlns.com/foaf/0.1/knows>",
                                                "o1" to "<http://example.org/c>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/b>",
                                                "p1" to "<http://xmlns.com/foaf/0.1/mbox>",
                                                "o1" to "\"bob@example.org\""
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/b>",
                                                "p1" to "<http://xmlns.com/foaf/0.1/name>",
                                                "o1" to "\"Bob\""
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/c>",
                                                "p1" to "<http://xmlns.com/foaf/0.1/mbox>",
                                                "o1" to "\"alice@example.org\""
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/c>",
                                                "p1" to "<http://xmlns.com/foaf/0.1/name>",
                                                "o1" to "\"Alice\""
                                        )
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "o2"
                                ), listOf(
                                        mutableMapOf(
                                                "s" to "<http://example.org/a>",
                                                "o2" to "<http://example.org/b>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/b>",
                                                "o2" to "<http://example.org/c>"
                                        )
                                )
                                ),
                                true),
                        POPValues(dictionary, listOf(
                                "s",
                                "p1",
                                "o1",
                                "o2"
                        ), listOf(
                                mutableMapOf(
                                        "s" to "<http://example.org/a>",
                                        "p1" to "<http://xmlns.com/foaf/0.1/knows>",
                                        "o1" to "<http://example.org/b>",
                                        "o2" to "<http://example.org/b>"
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/a>",
                                        "p1" to "<http://xmlns.com/foaf/0.1/mbox>",
                                        "o1" to "\"alan@example.org\"",
                                        "o2" to "<http://example.org/b>"
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/a>",
                                        "p1" to "<http://xmlns.com/foaf/0.1/name>",
                                        "o1" to "\"Alan\"",
                                        "o2" to "<http://example.org/b>"
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/b>",
                                        "p1" to "<http://xmlns.com/foaf/0.1/knows>",
                                        "o1" to "<http://example.org/c>",
                                        "o2" to "<http://example.org/c>"
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/b>",
                                        "p1" to "<http://xmlns.com/foaf/0.1/mbox>",
                                        "o1" to "\"bob@example.org\"",
                                        "o2" to "<http://example.org/c>"
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/b>",
                                        "p1" to "<http://xmlns.com/foaf/0.1/name>",
                                        "o1" to "\"Bob\"",
                                        "o2" to "<http://example.org/c>"
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/c>",
                                        "p1" to "<http://xmlns.com/foaf/0.1/mbox>",
                                        "o1" to "\"alice@example.org\"",
                                        "o2" to null
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/c>",
                                        "p1" to "<http://xmlns.com/foaf/0.1/name>",
                                        "o1" to "\"Alice\"",
                                        "o2" to null
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/bindings/values07.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "book",
                                        "title"
                                ), listOf(
                                        mutableMapOf(
                                                "book" to null,
                                                "title" to "\"SPARQL Tutorial\""
                                        ),
                                        mutableMapOf(
                                                "book" to "<http://example.org/book/book2>",
                                                "title" to null
                                        )
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "book",
                                        "title",
                                        "price"
                                ), listOf(
                                        mutableMapOf(
                                                "book" to "<http://example.org/book/book1>",
                                                "title" to "\"SPARQL Tutorial\"",
                                                "price" to "\"42\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        ),
                                        mutableMapOf(
                                                "book" to "<http://example.org/book/book2>",
                                                "title" to "\"The Semantic Web\"",
                                                "price" to "\"23\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        )
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "book",
                                "title",
                                "price"
                        ), listOf(
                                mutableMapOf(
                                        "book" to "<http://example.org/book/book1>",
                                        "title" to "\"SPARQL Tutorial\"",
                                        "price" to "\"42\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                        "book" to "<http://example.org/book/book2>",
                                        "title" to "\"The Semantic Web\"",
                                        "price" to "\"23\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/bindings/values08.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "book"
                                ), listOf(
                                        mutableMapOf(
                                                "book" to "<http://example.org/book/book1>"
                                        )
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "book",
                                        "title"
                                ), listOf(
                                        mutableMapOf(
                                                "book" to "<http://example.org/book/book1>",
                                                "title" to "\"SPARQL Tutorial\""
                                        ),
                                        mutableMapOf(
                                                "book" to "<http://example.org/book/book2>",
                                                "title" to "\"The Semantic Web\""
                                        )
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "book",
                                "title"
                        ), listOf(
                                mutableMapOf(
                                        "book" to "<http://example.org/book/book1>",
                                        "title" to "\"SPARQL Tutorial\""
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/bindings/inline01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "book",
                                        "title"
                                ), listOf(
                                        mutableMapOf(
                                                "book" to "<http://example.org/book/book1>",
                                                "title" to "\"SPARQL Tutorial\""
                                        )
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "book",
                                        "price"
                                ), listOf(
                                        mutableMapOf(
                                                "book" to "<http://example.org/book/book1>",
                                                "price" to "\"42\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        ),
                                        mutableMapOf(
                                                "book" to "<http://example.org/book/book2>",
                                                "price" to "\"23\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        )
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "book",
                                "title",
                                "price"
                        ), listOf(
                                mutableMapOf(
                                        "book" to "<http://example.org/book/book1>",
                                        "title" to "\"SPARQL Tutorial\"",
                                        "price" to "\"42\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/bindings/inline01.rq */,
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example.org/s1>",
                                    "p" to "<http://example.org/p1>",
                                    "o" to "<http://example.org/s2>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s2>",
                                    "p" to "<http://example.org/p2>",
                                    "o" to "\"foo\""
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s3>",
                                    "p" to "<http://example.org/p3>",
                                    "o" to "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s4>",
                                    "p" to "<http://example.org/p4>",
                                    "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s5>",
                                    "p" to "<http://example.org/p5>",
                                    "o" to "\"5.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s6>",
                                    "p" to "<http://example.org/p6>",
                                    "o" to "_:o6"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "o",
                                "p2",
                                "o2"
                            ), listOf(
                                mutableMapOf(
                                    "o" to "<http://example.org/s1>",
                                    "p2" to "<http://example.org/p1>",
                                    "o2" to "<http://example.org/s2>"
                                ),
                                mutableMapOf(
                                    "o" to "<http://example.org/s2>",
                                    "p2" to "<http://example.org/p2>",
                                    "o2" to "\"foo\""
                                ),
                                mutableMapOf(
                                    "o" to "<http://example.org/s3>",
                                    "p2" to "<http://example.org/p3>",
                                    "o2" to "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                    "o" to "<http://example.org/s4>",
                                    "p2" to "<http://example.org/p4>",
                                    "o2" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "o" to "<http://example.org/s5>",
                                    "p2" to "<http://example.org/p5>",
                                    "o2" to "\"5.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                                ),
                                mutableMapOf(
                                    "o" to "<http://example.org/s6>",
                                    "p2" to "<http://example.org/p6>",
                                    "o2" to "_:o6"
                                )
                            )
                        ),
                        true                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o",
                            "p2",
                            "o2"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/s1>",
                                "p" to "<http://example.org/p1>",
                                "o" to "<http://example.org/s2>",
                                "p2" to "<http://example.org/p2>",
                                "o2" to "\"foo\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s2>",
                                "p" to "<http://example.org/p2>",
                                "o" to "\"foo\"",
                                "p2" to null,
                                "o2" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s3>",
                                "p" to "<http://example.org/p3>",
                                "o" to "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "p2" to null,
                                "o2" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s4>",
                                "p" to "<http://example.org/p4>",
                                "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "p2" to null,
                                "o2" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s5>",
                                "p" to "<http://example.org/p5>",
                                "o" to "\"5.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>",
                                "p2" to null,
                                "o2" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s6>",
                                "p" to "<http://example.org/p6>",
                                "o" to "_:o6",
                                "p2" to null,
                                "o2" to null
                            )
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/csv-tsv-res/csvtsv02.rq */
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "s",
                                        "p",
                                        "o"
                                ), listOf(
                                        mutableMapOf(
                                                "s" to "<http://example.org/s1>",
                                                "p" to "<http://example.org/p1>",
                                                "o" to "<http://example.org/s2>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/s2>",
                                                "p" to "<http://example.org/p2>",
                                                "o" to "\"foo\""
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/s3>",
                                                "p" to "<http://example.org/p3>",
                                                "o" to "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/s4>",
                                                "p" to "<http://example.org/p4>",
                                                "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/s5>",
                                                "p" to "<http://example.org/p5>",
                                                "o" to "\"5.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/s6>",
                                                "p" to "<http://example.org/p6>",
                                                "o" to "_:o6"
                                        )
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "o",
                                        "p2",
                                        "o2"
                                ), listOf(
                                        mutableMapOf(
                                                "o" to "<http://example.org/s1>",
                                                "p2" to "<http://example.org/p1>",
                                                "o2" to "<http://example.org/s2>"
                                        ),
                                        mutableMapOf(
                                                "o" to "<http://example.org/s2>",
                                                "p2" to "<http://example.org/p2>",
                                                "o2" to "\"foo\""
                                        ),
                                        mutableMapOf(
                                                "o" to "<http://example.org/s3>",
                                                "p2" to "<http://example.org/p3>",
                                                "o2" to "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                        ),
                                        mutableMapOf(
                                                "o" to "<http://example.org/s4>",
                                                "p2" to "<http://example.org/p4>",
                                                "o2" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        ),
                                        mutableMapOf(
                                                "o" to "<http://example.org/s5>",
                                                "p2" to "<http://example.org/p5>",
                                                "o2" to "\"5.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                                        ),
                                        mutableMapOf(
                                                "o" to "<http://example.org/s6>",
                                                "p2" to "<http://example.org/p6>",
                                                "o2" to "_:o6"
                                        )
                                )
                                ),
                                true),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o",
                                "p2",
                                "o2"
                        ), listOf(
                                mutableMapOf(
                                        "s" to "<http://example.org/s1>",
                                        "p" to "<http://example.org/p1>",
                                        "o" to "<http://example.org/s2>",
                                        "p2" to "<http://example.org/p2>",
                                        "o2" to "\"foo\""
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/s2>",
                                        "p" to "<http://example.org/p2>",
                                        "o" to "\"foo\"",
                                        "p2" to null,
                                        "o2" to null
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/s3>",
                                        "p" to "<http://example.org/p3>",
                                        "o" to "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                        "p2" to null,
                                        "o2" to null
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/s4>",
                                        "p" to "<http://example.org/p4>",
                                        "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "p2" to null,
                                        "o2" to null
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/s5>",
                                        "p" to "<http://example.org/p5>",
                                        "o" to "\"5.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>",
                                        "p2" to null,
                                        "o2" to null
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/s6>",
                                        "p" to "<http://example.org/p6>",
                                        "o" to "_:o6",
                                        "p2" to null,
                                        "o2" to null
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/csv-tsv-res/csvtsv02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "a"
                                ), listOf(
                                        mutableMapOf(
                                                "a" to "<http://example.org/a>"
                                        )
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "a",
                                        "b"
                                ), listOf(
                                        mutableMapOf(
                                                "a" to "<http://example.org/a>",
                                                "b" to "<http://example.org/b>"
                                        ),
                                        mutableMapOf(
                                                "a" to "<http://example.org/a>",
                                                "b" to "<http://example.org/c>"
                                        ),
                                        mutableMapOf(
                                                "a" to "<http://example.org/b>",
                                                "b" to "<http://example.org/c>"
                                        )
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "a",
                                "b"
                        ), listOf(
                                mutableMapOf(
                                        "a" to "<http://example.org/a>",
                                        "b" to "<http://example.org/b>"
                                ),
                                mutableMapOf(
                                        "a" to "<http://example.org/a>",
                                        "b" to "<http://example.org/c>"
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/delete-insert/delete-insert-02.ru */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "a"
                                ), listOf(
                                        mutableMapOf(
                                                "a" to "<http://example.org/a>"
                                        )
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "a",
                                        "Var_B"
                                ), listOf(
                                        mutableMapOf(
                                                "a" to "<http://example.org/a>",
                                                "Var_B" to "<http://example.org/b>"
                                        ),
                                        mutableMapOf(
                                                "a" to "<http://example.org/a>",
                                                "Var_B" to "<http://example.org/c>"
                                        ),
                                        mutableMapOf(
                                                "a" to "<http://example.org/b>",
                                                "Var_B" to "<http://example.org/c>"
                                        )
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "a",
                                "Var_B"
                        ), listOf(
                                mutableMapOf(
                                        "a" to "<http://example.org/a>",
                                        "Var_B" to "<http://example.org/b>"
                                ),
                                mutableMapOf(
                                        "a" to "<http://example.org/a>",
                                        "Var_B" to "<http://example.org/c>"
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/delete-insert/delete-insert-05b.ru */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "a"
                                ), listOf(
                                        mutableMapOf(
                                                "a" to "<http://example.org/a>"
                                        )
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "a",
                                        "Var_B"
                                ), listOf(
                                        mutableMapOf(
                                                "a" to "<http://example.org/b>",
                                                "Var_B" to "<http://example.org/c>"
                                        )
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "a",
                                "Var_B"
                        ), listOf(
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/delete-insert/delete-insert-05b.ru */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "s"
                                ), listOf(
                                        mutableMapOf(
                                                "s" to "<http://example.org/b>"
                                        )
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "p",
                                        "o"
                                ), listOf(
                                        mutableMapOf(
                                                "s" to "<http://example.org/a>",
                                                "p" to "<http://xmlns.com/foaf/0.1/knows>",
                                                "o" to "<http://example.org/b>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/a>",
                                                "p" to "<http://xmlns.com/foaf/0.1/mbox>",
                                                "o" to "\"alan@example.org\""
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/a>",
                                                "p" to "<http://xmlns.com/foaf/0.1/name>",
                                                "o" to "\"Alan\""
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/b>",
                                                "p" to "<http://xmlns.com/foaf/0.1/mbox>",
                                                "o" to "\"bob@example.org\""
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/b>",
                                                "p" to "<http://xmlns.com/foaf/0.1/name>",
                                                "o" to "\"Bob\""
                                        )
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                                mutableMapOf(
                                        "s" to "<http://example.org/b>",
                                        "p" to "<http://xmlns.com/foaf/0.1/mbox>",
                                        "o" to "\"bob@example.org\""
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/b>",
                                        "p" to "<http://xmlns.com/foaf/0.1/name>",
                                        "o" to "\"Bob\""
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/delete/delete-05.ru */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "s"
                                ), listOf(
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "p",
                                        "o"
                                ), listOf(
                                        mutableMapOf(
                                                "s" to "<http://example.org/a>",
                                                "p" to "<http://xmlns.com/foaf/0.1/knows>",
                                                "o" to "<http://example.org/b>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/a>",
                                                "p" to "<http://xmlns.com/foaf/0.1/mbox>",
                                                "o" to "\"alan@example.org\""
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/a>",
                                                "p" to "<http://xmlns.com/foaf/0.1/name>",
                                                "o" to "\"Alan\""
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/b>",
                                                "p" to "<http://xmlns.com/foaf/0.1/mbox>",
                                                "o" to "\"bob@example.org\""
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/b>",
                                                "p" to "<http://xmlns.com/foaf/0.1/name>",
                                                "o" to "\"Bob\""
                                        )
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/delete/delete-04.ru */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "s"
                                ), listOf(
                                        mutableMapOf(
                                                "s" to "<http://example.org/c>"
                                        )
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "p",
                                        "o"
                                ), listOf(
                                        mutableMapOf(
                                                "s" to "<http://example.org/a>",
                                                "p" to "<http://xmlns.com/foaf/0.1/knows>",
                                                "o" to "<http://example.org/b>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/b>",
                                                "p" to "<http://xmlns.com/foaf/0.1/knows>",
                                                "o" to "<http://example.org/c>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/b>",
                                                "p" to "<http://xmlns.com/foaf/0.1/mbox>",
                                                "o" to "\"bob@example.org\""
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/b>",
                                                "p" to "<http://xmlns.com/foaf/0.1/name>",
                                                "o" to "\"Bob\""
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/c>",
                                                "p" to "<http://xmlns.com/foaf/0.1/mbox>",
                                                "o" to "\"chris@example.org\""
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/c>",
                                                "p" to "<http://xmlns.com/foaf/0.1/name>",
                                                "o" to "\"Chris\""
                                        )
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                                mutableMapOf(
                                        "s" to "<http://example.org/c>",
                                        "p" to "<http://xmlns.com/foaf/0.1/mbox>",
                                        "o" to "\"chris@example.org\""
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/c>",
                                        "p" to "<http://xmlns.com/foaf/0.1/name>",
                                        "o" to "\"Chris\""
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/delete/delete-06.ru */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "s"
                                ), listOf(
                                        mutableMapOf(
                                                "s" to "<http://example.org/b>"
                                        )
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "p",
                                        "o"
                                ), listOf(
                                        mutableMapOf(
                                                "s" to "<http://example.org/a>",
                                                "p" to "<http://xmlns.com/foaf/0.1/knows>",
                                                "o" to "<http://example.org/b>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/b>",
                                                "p" to "<http://xmlns.com/foaf/0.1/knows>",
                                                "o" to "<http://example.org/c>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/b>",
                                                "p" to "<http://xmlns.com/foaf/0.1/mbox>",
                                                "o" to "\"bob@example.org\""
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/b>",
                                                "p" to "<http://xmlns.com/foaf/0.1/name>",
                                                "o" to "\"Bob\""
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/c>",
                                                "p" to "<http://xmlns.com/foaf/0.1/mbox>",
                                                "o" to "\"chris@example.org\""
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/c>",
                                                "p" to "<http://xmlns.com/foaf/0.1/name>",
                                                "o" to "\"Chris\""
                                        )
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                                mutableMapOf(
                                        "s" to "<http://example.org/b>",
                                        "p" to "<http://xmlns.com/foaf/0.1/knows>",
                                        "o" to "<http://example.org/c>"
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/b>",
                                        "p" to "<http://xmlns.com/foaf/0.1/mbox>",
                                        "o" to "\"bob@example.org\""
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/b>",
                                        "p" to "<http://xmlns.com/foaf/0.1/name>",
                                        "o" to "\"Bob\""
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/delete/delete-using-01.ru */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "s"
                                ), listOf(
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "p",
                                        "o"
                                ), listOf(
                                        mutableMapOf(
                                                "s" to "<http://example.org/c>",
                                                "p" to "<http://xmlns.com/foaf/0.1/knows>",
                                                "o" to "<http://example.org/d>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/c>",
                                                "p" to "<http://xmlns.com/foaf/0.1/mbox>",
                                                "o" to "\"chris@example.org\""
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/c>",
                                                "p" to "<http://xmlns.com/foaf/0.1/name>",
                                                "o" to "\"Chris\""
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/d>",
                                                "p" to "<http://xmlns.com/foaf/0.1/mbox>",
                                                "o" to "\"dan@example.org\""
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/d>",
                                                "p" to "<http://xmlns.com/foaf/0.1/name>",
                                                "o" to "\"Dan\""
                                        )
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/delete/delete-using-02.ru */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "s"
                                ), listOf(
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "p",
                                        "o"
                                ), listOf(
                                        mutableMapOf(
                                                "s" to "<http://example.org/a>",
                                                "p" to "<http://xmlns.com/foaf/0.1/knows>",
                                                "o" to "<http://example.org/b>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/b>",
                                                "p" to "<http://xmlns.com/foaf/0.1/knows>",
                                                "o" to "<http://example.org/c>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/b>",
                                                "p" to "<http://xmlns.com/foaf/0.1/mbox>",
                                                "o" to "\"bob@example.org\""
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/b>",
                                                "p" to "<http://xmlns.com/foaf/0.1/name>",
                                                "o" to "\"Bob\""
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/c>",
                                                "p" to "<http://xmlns.com/foaf/0.1/mbox>",
                                                "o" to "\"chris@example.org\""
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/c>",
                                                "p" to "<http://xmlns.com/foaf/0.1/name>",
                                                "o" to "\"Chris\""
                                        )
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/delete/delete-using-03.ru */,
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "s"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example.org/c>"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example.org/c>",
                                    "p" to "<http://xmlns.com/foaf/0.1/knows>",
                                    "o" to "<http://example.org/d>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/c>",
                                    "p" to "<http://xmlns.com/foaf/0.1/mbox>",
                                    "o" to "\"chris@example.org\""
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/c>",
                                    "p" to "<http://xmlns.com/foaf/0.1/name>",
                                    "o" to "\"Chris\""
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/d>",
                                    "p" to "<http://xmlns.com/foaf/0.1/mbox>",
                                    "o" to "\"dan@example.org\""
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/d>",
                                    "p" to "<http://xmlns.com/foaf/0.1/name>",
                                    "o" to "\"Dan\""
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/c>",
                                "p" to "<http://xmlns.com/foaf/0.1/knows>",
                                "o" to "<http://example.org/d>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/c>",
                                "p" to "<http://xmlns.com/foaf/0.1/mbox>",
                                "o" to "\"chris@example.org\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/c>",
                                "p" to "<http://xmlns.com/foaf/0.1/name>",
                                "o" to "\"Chris\""
                            )
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/delete/delete-using-06.ru */
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "s"
                                ), listOf(
                                        mutableMapOf(
                                                "s" to "<http://example.org/a>"
                                        )
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "p",
                                        "o"
                                ), listOf(
                                        mutableMapOf(
                                                "s" to "<http://example.org/a>",
                                                "p" to "<http://xmlns.com/foaf/0.1/knows>",
                                                "o" to "<http://example.org/b>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/a>",
                                                "p" to "<http://xmlns.com/foaf/0.1/mbox>",
                                                "o" to "\"alan@example.org\""
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/a>",
                                                "p" to "<http://xmlns.com/foaf/0.1/name>",
                                                "o" to "\"Alan\""
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/b>",
                                                "p" to "<http://xmlns.com/foaf/0.1/mbox>",
                                                "o" to "\"bob@example.org\""
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/b>",
                                                "p" to "<http://xmlns.com/foaf/0.1/name>",
                                                "o" to "\"Bob\""
                                        )
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                                mutableMapOf(
                                        "s" to "<http://example.org/a>",
                                        "p" to "<http://xmlns.com/foaf/0.1/knows>",
                                        "o" to "<http://example.org/b>"
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/a>",
                                        "p" to "<http://xmlns.com/foaf/0.1/mbox>",
                                        "o" to "\"alan@example.org\""
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/a>",
                                        "p" to "<http://xmlns.com/foaf/0.1/name>",
                                        "o" to "\"Alan\""
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/delete/delete-using-05.ru */,
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "x",
                                "c"
                            ), listOf(
                                mutableMapOf(
                                    "x" to "<http://example.org/x/c>",
                                    "c" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/x/d>",
                                    "c" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/x/p>",
                                    "c" to "<http://www.w3.org/2002/07/owl#ObjectProperty>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/x/x>",
                                    "c" to "<http://example.org/x/c>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/x/x>",
                                    "c" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/x/y>",
                                    "c" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "x" to "_:rdfs05",
                                    "c" to "<http://www.w3.org/2002/07/owl#Ontology>"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "c"
                            ), listOf(
                                mutableMapOf(
                                    "c" to "<http://example.org/x/c>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "c"
                        ), listOf(
                            mutableMapOf(
                                "x" to "<http://example.org/x/x>",
                                "c" to "<http://example.org/x/c>"
                            )
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/rdfs05.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "x"
                            ), listOf(
                                mutableMapOf(
                                    "x" to "<http://example.org/ns#b>"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "x"
                            ), listOf(
                                mutableMapOf(
                                    "x" to "<http://example.org/ns#b>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "x"
                        ), listOf(
                            mutableMapOf(
                                "x" to "<http://example.org/ns#b>"
                            )
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/rdfs11.rq */
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "x",
                                        "c"
                                ), listOf(
                                        mutableMapOf(
                                                "x" to "<http://example.org/x/a>",
                                                "c" to "<http://example.org/x/c>"
                                        ),
                                        mutableMapOf(
                                                "x" to "<http://example.org/x/a>",
                                                "c" to "<http://example.org/x/d>"
                                        ),
                                        mutableMapOf(
                                                "x" to "<http://example.org/x/a>",
                                                "c" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                        ),
                                        mutableMapOf(
                                                "x" to "<http://example.org/x/a>",
                                                "c" to "_:x"
                                        ),
                                        mutableMapOf(
                                                "x" to "<http://example.org/x/c>",
                                                "c" to "<http://www.w3.org/2002/07/owl#Class>"
                                        ),
                                        mutableMapOf(
                                                "x" to "<http://example.org/x/d>",
                                                "c" to "<http://www.w3.org/2002/07/owl#Class>"
                                        ),
                                        mutableMapOf(
                                                "x" to "<http://example.org/x/p>",
                                                "c" to "<http://www.w3.org/2002/07/owl#ObjectProperty>"
                                        ),
                                        mutableMapOf(
                                                "x" to "_:ont",
                                                "c" to "<http://www.w3.org/2002/07/owl#Ontology>"
                                        ),
                                        mutableMapOf(
                                                "x" to "_:x",
                                                "c" to "<http://www.w3.org/2002/07/owl#Restriction>"
                                        )
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "c"
                                ), listOf(
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "x",
                                "c"
                        ), listOf(
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/owlds01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "x",
                                        "y"
                                ), listOf(
                                        mutableMapOf(
                                                "x" to "<http://example.org/x/x>",
                                                "y" to "<http://example.org/x/y>"
                                        ),
                                        mutableMapOf(
                                                "x" to "<http://example.org/x/x>",
                                                "y" to "_:y"
                                        )
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "y"
                                ), listOf(
                                        mutableMapOf(
                                                "y" to "<http://example.org/x/y>"
                                        ),
                                        mutableMapOf(
                                                "y" to "_:y"
                                        )
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "x",
                                "y"
                        ), listOf(
                                mutableMapOf(
                                        "x" to "<http://example.org/x/x>",
                                        "y" to "<http://example.org/x/y>"
                                ),
                                mutableMapOf(
                                        "x" to "<http://example.org/x/x>",
                                        "y" to "_:y"
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/owlds02.rq */,
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "x",
                                "#c"
                            ), listOf(
                                mutableMapOf(
                                    "x" to "<http://example.org/Anite>",
                                    "#c" to "<http://example.org/Student>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/Anite>",
                                    "#c" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/Conference>",
                                    "#c" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/ConferencePaper>",
                                    "#c" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/Employee>",
                                    "#c" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/George>",
                                    "#c" to "<http://example.org/Employee>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/George>",
                                    "#c" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/GraduateAssistant>",
                                    "#c" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/John>",
                                    "#c" to "<http://example.org/GraduateAssistant>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/John>",
                                    "#c" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/Student>",
                                    "#c" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/Workshop>",
                                    "#c" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/hasPublication>",
                                    "#c" to "<http://www.w3.org/2002/07/owl#ObjectProperty>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/name>",
                                    "#c" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/paper1>",
                                    "#c" to "<http://example.org/ConferencePaper>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/paper1>",
                                    "#c" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/person1>",
                                    "#c" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/publishedAt>",
                                    "#c" to "<http://www.w3.org/2002/07/owl#ObjectProperty>"
                                ),
                                mutableMapOf(
                                    "x" to "_:SPARQLDAWGTestOntology",
                                    "#c" to "<http://www.w3.org/2002/07/owl#Ontology>"
                                ),
                                mutableMapOf(
                                    "x" to "_:_15921",
                                    "#c" to "<http://www.w3.org/2002/07/owl#Restriction>"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_15942"
                            ), listOf(
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "#c",
                            "#_15942"
                        ), listOf(
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/paper-sparqldl-Q2.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "x",
                                "#c",
                                "#_15942",
                                "#_15947"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "y"
                            ), listOf(
                                mutableMapOf(
                                    "x" to "<http://example.org/John>",
                                    "y" to "\"Johnnie\""
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "#c",
                            "#_15942",
                            "#_15947",
                            "y"
                        ), listOf(
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/paper-sparqldl-Q2.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "x",
                                "#b0"
                            ), listOf(
                                mutableMapOf(
                                    "x" to "<http://example.org/John>",
                                    "#b0" to "<http://example.org/paper1>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/person1>",
                                    "#b0" to "<http://example.org/paper1>"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_16067"
                            ), listOf(
                                mutableMapOf(
                                    "#_16067" to "_:_16047"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "#b0",
                            "#_16067"
                        ), listOf(
                            mutableMapOf(
                                "x" to "<http://example.org/John>",
                                "#b0" to "<http://example.org/paper1>",
                                "#_16067" to "_:_16047"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/person1>",
                                "#b0" to "<http://example.org/paper1>",
                                "#_16067" to "_:_16047"
                            )
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/paper-sparqldl-Q3.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "x",
                                "#b0",
                                "#_16067"
                            ), listOf(
                                mutableMapOf(
                                    "x" to "<http://example.org/John>",
                                    "#b0" to "<http://example.org/paper1>",
                                    "#_16067" to "_:_16047"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/person1>",
                                    "#b0" to "<http://example.org/paper1>",
                                    "#_16067" to "_:_16047"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_16067"
                            ), listOf(
                                mutableMapOf(
                                    "#_16067" to "_:_16047"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "#b0",
                            "#_16067"
                        ), listOf(
                            mutableMapOf(
                                "x" to "<http://example.org/John>",
                                "#b0" to "<http://example.org/paper1>",
                                "#_16067" to "_:_16047"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/person1>",
                                "#b0" to "<http://example.org/paper1>",
                                "#_16067" to "_:_16047"
                            )
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/paper-sparqldl-Q3.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "x",
                                "#b0",
                                "#_16067"
                            ), listOf(
                                mutableMapOf(
                                    "x" to "<http://example.org/John>",
                                    "#b0" to "<http://example.org/paper1>",
                                    "#_16067" to "_:_16047"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/person1>",
                                    "#b0" to "<http://example.org/paper1>",
                                    "#_16067" to "_:_16047"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_16076"
                            ), listOf(
                                mutableMapOf(
                                    "#_16076" to "<http://example.org/Conference>"
                                ),
                                mutableMapOf(
                                    "#_16076" to "<http://example.org/ConferencePaper>"
                                ),
                                mutableMapOf(
                                    "#_16076" to "<http://example.org/Employee>"
                                ),
                                mutableMapOf(
                                    "#_16076" to "<http://example.org/GraduateAssistant>"
                                ),
                                mutableMapOf(
                                    "#_16076" to "<http://example.org/Student>"
                                ),
                                mutableMapOf(
                                    "#_16076" to "<http://example.org/Workshop>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "#b0",
                            "#_16067",
                            "#_16076"
                        ), listOf(
                            mutableMapOf(
                                "x" to "<http://example.org/John>",
                                "#b0" to "<http://example.org/paper1>",
                                "#_16067" to "_:_16047",
                                "#_16076" to "<http://example.org/Conference>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/person1>",
                                "#b0" to "<http://example.org/paper1>",
                                "#_16067" to "_:_16047",
                                "#_16076" to "<http://example.org/Conference>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/John>",
                                "#b0" to "<http://example.org/paper1>",
                                "#_16067" to "_:_16047",
                                "#_16076" to "<http://example.org/ConferencePaper>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/person1>",
                                "#b0" to "<http://example.org/paper1>",
                                "#_16067" to "_:_16047",
                                "#_16076" to "<http://example.org/ConferencePaper>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/John>",
                                "#b0" to "<http://example.org/paper1>",
                                "#_16067" to "_:_16047",
                                "#_16076" to "<http://example.org/Employee>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/person1>",
                                "#b0" to "<http://example.org/paper1>",
                                "#_16067" to "_:_16047",
                                "#_16076" to "<http://example.org/Employee>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/John>",
                                "#b0" to "<http://example.org/paper1>",
                                "#_16067" to "_:_16047",
                                "#_16076" to "<http://example.org/GraduateAssistant>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/person1>",
                                "#b0" to "<http://example.org/paper1>",
                                "#_16067" to "_:_16047",
                                "#_16076" to "<http://example.org/GraduateAssistant>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/John>",
                                "#b0" to "<http://example.org/paper1>",
                                "#_16067" to "_:_16047",
                                "#_16076" to "<http://example.org/Student>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/person1>",
                                "#b0" to "<http://example.org/paper1>",
                                "#_16067" to "_:_16047",
                                "#_16076" to "<http://example.org/Student>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/John>",
                                "#b0" to "<http://example.org/paper1>",
                                "#_16067" to "_:_16047",
                                "#_16076" to "<http://example.org/Workshop>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/person1>",
                                "#b0" to "<http://example.org/paper1>",
                                "#_16067" to "_:_16047",
                                "#_16076" to "<http://example.org/Workshop>"
                            )
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/paper-sparqldl-Q3.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "x",
                                "#b0",
                                "#_16067",
                                "#_16076"
                            ), listOf(
                                mutableMapOf(
                                    "x" to "<http://example.org/John>",
                                    "#b0" to "<http://example.org/paper1>",
                                    "#_16067" to "_:_16047",
                                    "#_16076" to "<http://example.org/Conference>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/person1>",
                                    "#b0" to "<http://example.org/paper1>",
                                    "#_16067" to "_:_16047",
                                    "#_16076" to "<http://example.org/Conference>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/John>",
                                    "#b0" to "<http://example.org/paper1>",
                                    "#_16067" to "_:_16047",
                                    "#_16076" to "<http://example.org/ConferencePaper>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/person1>",
                                    "#b0" to "<http://example.org/paper1>",
                                    "#_16067" to "_:_16047",
                                    "#_16076" to "<http://example.org/ConferencePaper>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/John>",
                                    "#b0" to "<http://example.org/paper1>",
                                    "#_16067" to "_:_16047",
                                    "#_16076" to "<http://example.org/Employee>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/person1>",
                                    "#b0" to "<http://example.org/paper1>",
                                    "#_16067" to "_:_16047",
                                    "#_16076" to "<http://example.org/Employee>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/John>",
                                    "#b0" to "<http://example.org/paper1>",
                                    "#_16067" to "_:_16047",
                                    "#_16076" to "<http://example.org/GraduateAssistant>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/person1>",
                                    "#b0" to "<http://example.org/paper1>",
                                    "#_16067" to "_:_16047",
                                    "#_16076" to "<http://example.org/GraduateAssistant>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/John>",
                                    "#b0" to "<http://example.org/paper1>",
                                    "#_16067" to "_:_16047",
                                    "#_16076" to "<http://example.org/Student>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/person1>",
                                    "#b0" to "<http://example.org/paper1>",
                                    "#_16067" to "_:_16047",
                                    "#_16076" to "<http://example.org/Student>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/John>",
                                    "#b0" to "<http://example.org/paper1>",
                                    "#_16067" to "_:_16047",
                                    "#_16076" to "<http://example.org/Workshop>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/person1>",
                                    "#b0" to "<http://example.org/paper1>",
                                    "#_16067" to "_:_16047",
                                    "#_16076" to "<http://example.org/Workshop>"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_16076"
                            ), listOf(
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "#b0",
                            "#_16067",
                            "#_16076"
                        ), listOf(
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/paper-sparqldl-Q3.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "x",
                                "#b0",
                                "#_16067",
                                "#_16076"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_16067",
                                "#_16076"
                            ), listOf(
                                mutableMapOf(
                                    "#_16067" to "_:_16047",
                                    "#_16076" to "<http://example.org/Conference>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "#b0",
                            "#_16067",
                            "#_16076"
                        ), listOf(
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/paper-sparqldl-Q3.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "x",
                                "#b0",
                                "#_16067",
                                "#_16076"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#b0",
                                "#_16067"
                            ), listOf(
                                mutableMapOf(
                                    "#b0" to "<http://example.org/Anite>",
                                    "#_16067" to "<http://example.org/Student>"
                                ),
                                mutableMapOf(
                                    "#b0" to "<http://example.org/Anite>",
                                    "#_16067" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "#b0" to "<http://example.org/Conference>",
                                    "#_16067" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "#b0" to "<http://example.org/ConferencePaper>",
                                    "#_16067" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "#b0" to "<http://example.org/Employee>",
                                    "#_16067" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "#b0" to "<http://example.org/George>",
                                    "#_16067" to "<http://example.org/Employee>"
                                ),
                                mutableMapOf(
                                    "#b0" to "<http://example.org/George>",
                                    "#_16067" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "#b0" to "<http://example.org/GraduateAssistant>",
                                    "#_16067" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "#b0" to "<http://example.org/John>",
                                    "#_16067" to "<http://example.org/GraduateAssistant>"
                                ),
                                mutableMapOf(
                                    "#b0" to "<http://example.org/John>",
                                    "#_16067" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "#b0" to "<http://example.org/Student>",
                                    "#_16067" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "#b0" to "<http://example.org/Workshop>",
                                    "#_16067" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "#b0" to "<http://example.org/hasPublication>",
                                    "#_16067" to "<http://www.w3.org/2002/07/owl#ObjectProperty>"
                                ),
                                mutableMapOf(
                                    "#b0" to "<http://example.org/name>",
                                    "#_16067" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>"
                                ),
                                mutableMapOf(
                                    "#b0" to "<http://example.org/paper1>",
                                    "#_16067" to "<http://example.org/ConferencePaper>"
                                ),
                                mutableMapOf(
                                    "#b0" to "<http://example.org/paper1>",
                                    "#_16067" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "#b0" to "<http://example.org/person1>",
                                    "#_16067" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "#b0" to "<http://example.org/publishedAt>",
                                    "#_16067" to "<http://www.w3.org/2002/07/owl#ObjectProperty>"
                                ),
                                mutableMapOf(
                                    "#b0" to "_:SPARQLDAWGTestOntology",
                                    "#_16067" to "<http://www.w3.org/2002/07/owl#Ontology>"
                                ),
                                mutableMapOf(
                                    "#b0" to "_:_16047",
                                    "#_16067" to "<http://www.w3.org/2002/07/owl#Restriction>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "#b0",
                            "#_16067",
                            "#_16076"
                        ), listOf(
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/paper-sparqldl-Q3.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "x"
                            ), listOf(
                                mutableMapOf(
                                    "x" to "<http://example.org/Anite>"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "c"
                            ), listOf(
                                mutableMapOf(
                                    "x" to "<http://example.org/Anite>",
                                    "c" to "<http://example.org/Student>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/Anite>",
                                    "c" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/Conference>",
                                    "c" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/ConferencePaper>",
                                    "c" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/Employee>",
                                    "c" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/George>",
                                    "c" to "<http://example.org/Employee>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/George>",
                                    "c" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/GraduateAssistant>",
                                    "c" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/John>",
                                    "c" to "<http://example.org/GraduateAssistant>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/John>",
                                    "c" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/Student>",
                                    "c" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/Workshop>",
                                    "c" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/hasPublication>",
                                    "c" to "<http://www.w3.org/2002/07/owl#ObjectProperty>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/name>",
                                    "c" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/paper1>",
                                    "c" to "<http://example.org/ConferencePaper>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/paper1>",
                                    "c" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/person1>",
                                    "c" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/publishedAt>",
                                    "c" to "<http://www.w3.org/2002/07/owl#ObjectProperty>"
                                ),
                                mutableMapOf(
                                    "x" to "_:SPARQLDAWGTestOntology",
                                    "c" to "<http://www.w3.org/2002/07/owl#Ontology>"
                                ),
                                mutableMapOf(
                                    "x" to "_:_16175",
                                    "c" to "<http://www.w3.org/2002/07/owl#Restriction>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "c"
                        ), listOf(
                            mutableMapOf(
                                "x" to "<http://example.org/Anite>",
                                "c" to "<http://example.org/Student>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/Anite>",
                                "c" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                            )
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/paper-sparqldl-Q4.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "x",
                                "c"
                            ), listOf(
                                mutableMapOf(
                                    "x" to "<http://example.org/Anite>",
                                    "c" to "<http://example.org/Student>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/Anite>",
                                    "c" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "c"
                            ), listOf(
                                mutableMapOf(
                                    "c" to "<http://example.org/GraduateAssistant>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "c"
                        ), listOf(
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/paper-sparqldl-Q4.rq */
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "p"
                                ), listOf(
                                        mutableMapOf(
                                                "p" to "<http://example.org/hasPublication>"
                                        ),
                                        mutableMapOf(
                                                "p" to "<http://example.org/publishedAt>"
                                        )
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "p",
                                        "v"
                                ), listOf(
                                        mutableMapOf(
                                                "p" to "<http://example.org/hasPublication>",
                                                "v" to "<http://example.org/paper1>"
                                        ),
                                        mutableMapOf(
                                                "p" to "<http://example.org/name>",
                                                "v" to "\"Johnnie\""
                                        ),
                                        mutableMapOf(
                                                "p" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                                "v" to "<http://example.org/GraduateAssistant>"
                                        ),
                                        mutableMapOf(
                                                "p" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                                "v" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                        )
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "p",
                                "v"
                        ), listOf(
                                mutableMapOf(
                                        "p" to "<http://example.org/hasPublication>",
                                        "v" to "<http://example.org/paper1>"
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/paper-sparqldl-Q5.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "s",
                                        "p",
                                        "o",
                                        "z"
                                ), listOf(
                                        mutableMapOf(
                                                "s" to "<http://example.org/p>",
                                                "p" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                                "o" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>",
                                                "z" to null
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/s1>",
                                                "p" to "<http://example.org/p>",
                                                "o" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                                "z" to "\"11\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/s2>",
                                                "p" to "<http://example.org/p>",
                                                "o" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                                "z" to "\"12\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/s3>",
                                                "p" to "<http://example.org/p>",
                                                "o" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                                "z" to "\"13\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/s4>",
                                                "p" to "<http://example.org/p>",
                                                "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                                "z" to "\"14\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        ),
                                        mutableMapOf(
                                                "s" to "_:1",
                                                "p" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                                "o" to "<http://www.w3.org/2002/07/owl#Ontology>",
                                                "z" to null
                                        )
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "p"
                                ), listOf(
                                        mutableMapOf(
                                                "p" to "<http://example.org/p>"
                                        )
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o",
                                "z"
                        ), listOf(
                                mutableMapOf(
                                        "s" to "<http://example.org/s1>",
                                        "p" to "<http://example.org/p>",
                                        "o" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "z" to "\"11\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/s2>",
                                        "p" to "<http://example.org/p>",
                                        "o" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "z" to "\"12\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/s3>",
                                        "p" to "<http://example.org/p>",
                                        "o" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "z" to "\"13\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/s4>",
                                        "p" to "<http://example.org/p>",
                                        "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "z" to "\"14\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/bind06.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "s",
                                        "p",
                                        "o",
                                        "z",
                                        "z2"
                                ), listOf(
                                        mutableMapOf(
                                                "s" to "<http://example.org/p>",
                                                "p" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                                "o" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>",
                                                "z" to null,
                                                "z2" to null
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/s1>",
                                                "p" to "<http://example.org/p>",
                                                "o" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                                "z" to "\"11\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                                "z2" to "\"101\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/s2>",
                                                "p" to "<http://example.org/p>",
                                                "o" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                                "z" to "\"12\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                                "z2" to "\"102\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/s3>",
                                                "p" to "<http://example.org/p>",
                                                "o" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                                "z" to "\"13\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                                "z2" to "\"103\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/s4>",
                                                "p" to "<http://example.org/p>",
                                                "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                                "z" to "\"14\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                                "z2" to "\"104\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        ),
                                        mutableMapOf(
                                                "s" to "_:1",
                                                "p" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                                "o" to "<http://www.w3.org/2002/07/owl#Ontology>",
                                                "z" to null,
                                                "z2" to null
                                        )
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "p"
                                ), listOf(
                                        mutableMapOf(
                                                "p" to "<http://example.org/p>"
                                        )
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o",
                                "z",
                                "z2"
                        ), listOf(
                                mutableMapOf(
                                        "s" to "<http://example.org/s1>",
                                        "p" to "<http://example.org/p>",
                                        "o" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "z" to "\"11\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "z2" to "\"101\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/s2>",
                                        "p" to "<http://example.org/p>",
                                        "o" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "z" to "\"12\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "z2" to "\"102\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/s3>",
                                        "p" to "<http://example.org/p>",
                                        "o" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "z" to "\"13\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "z2" to "\"103\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/s4>",
                                        "p" to "<http://example.org/p>",
                                        "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "z" to "\"14\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "z2" to "\"104\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/bind02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "s",
                                        "p",
                                        "o",
                                        "z"
                                ), listOf(
                                        mutableMapOf(
                                                "s" to "<http://example.org/p>",
                                                "p" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                                "o" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>",
                                                "z" to null
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/s1>",
                                                "p" to "<http://example.org/p>",
                                                "o" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                                "z" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/s2>",
                                                "p" to "<http://example.org/p>",
                                                "o" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                                "z" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/s3>",
                                                "p" to "<http://example.org/p>",
                                                "o" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                                "z" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/s4>",
                                                "p" to "<http://example.org/p>",
                                                "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                                "z" to "\"5\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        ),
                                        mutableMapOf(
                                                "s" to "_:1",
                                                "p" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                                "o" to "<http://www.w3.org/2002/07/owl#Ontology>",
                                                "z" to null
                                        )
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "p"
                                ), listOf(
                                        mutableMapOf(
                                                "p" to "<http://example.org/p>"
                                        )
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o",
                                "z"
                        ), listOf(
                                mutableMapOf(
                                        "s" to "<http://example.org/s1>",
                                        "p" to "<http://example.org/p>",
                                        "o" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "z" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/s2>",
                                        "p" to "<http://example.org/p>",
                                        "o" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "z" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/s3>",
                                        "p" to "<http://example.org/p>",
                                        "o" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "z" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/s4>",
                                        "p" to "<http://example.org/p>",
                                        "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "z" to "\"5\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/bind08.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "s",
                                        "p",
                                        "o",
                                        "z"
                                ), listOf(
                                        mutableMapOf(
                                                "s" to "<http://example.org/s1>",
                                                "p" to "<http://example.org/p>",
                                                "o" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                                "z" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/s2>",
                                                "p" to "<http://example.org/p>",
                                                "o" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                                "z" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/s3>",
                                                "p" to "<http://example.org/p>",
                                                "o" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                                "z" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/s4>",
                                                "p" to "<http://example.org/p>",
                                                "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                                "z" to "\"5\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        )
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "s1",
                                        "p1",
                                        "z"
                                ), listOf(
                                        mutableMapOf(
                                                "s1" to "<http://example.org/p>",
                                                "p1" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                                "z" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>"
                                        ),
                                        mutableMapOf(
                                                "s1" to "<http://example.org/s1>",
                                                "p1" to "<http://example.org/p>",
                                                "z" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        ),
                                        mutableMapOf(
                                                "s1" to "<http://example.org/s2>",
                                                "p1" to "<http://example.org/p>",
                                                "z" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        ),
                                        mutableMapOf(
                                                "s1" to "<http://example.org/s3>",
                                                "p1" to "<http://example.org/p>",
                                                "z" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        ),
                                        mutableMapOf(
                                                "s1" to "<http://example.org/s4>",
                                                "p1" to "<http://example.org/p>",
                                                "z" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        ),
                                        mutableMapOf(
                                                "s1" to "_:1",
                                                "p1" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                                "z" to "<http://www.w3.org/2002/07/owl#Ontology>"
                                        )
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o",
                                "z",
                                "s1",
                                "p1"
                        ), listOf(
                                mutableMapOf(
                                        "s" to "<http://example.org/s1>",
                                        "p" to "<http://example.org/p>",
                                        "o" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "z" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "s1" to "<http://example.org/s2>",
                                        "p1" to "<http://example.org/p>"
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/s2>",
                                        "p" to "<http://example.org/p>",
                                        "o" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "z" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "s1" to "<http://example.org/s3>",
                                        "p1" to "<http://example.org/p>"
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/s3>",
                                        "p" to "<http://example.org/p>",
                                        "o" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "z" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "s1" to "<http://example.org/s4>",
                                        "p1" to "<http://example.org/p>"
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/bind03.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "s",
                                        "p",
                                        "o",
                                        "z",
                                        "s1",
                                        "p1"
                                ), listOf(
                                        mutableMapOf(
                                                "s" to "<http://example.org/s1>",
                                                "p" to "<http://example.org/p>",
                                                "o" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                                "z" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                                "s1" to "<http://example.org/s2>",
                                                "p1" to "<http://example.org/p>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/s2>",
                                                "p" to "<http://example.org/p>",
                                                "o" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                                "z" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                                "s1" to "<http://example.org/s3>",
                                                "p1" to "<http://example.org/p>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/s3>",
                                                "p" to "<http://example.org/p>",
                                                "o" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                                "z" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                                "s1" to "<http://example.org/s4>",
                                                "p1" to "<http://example.org/p>"
                                        )
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "p1"
                                ), listOf(
                                        mutableMapOf(
                                                "p1" to "<http://example.org/p>"
                                        )
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o",
                                "z",
                                "s1",
                                "p1"
                        ), listOf(
                                mutableMapOf(
                                        "s" to "<http://example.org/s1>",
                                        "p" to "<http://example.org/p>",
                                        "o" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "z" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "s1" to "<http://example.org/s2>",
                                        "p1" to "<http://example.org/p>"
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/s2>",
                                        "p" to "<http://example.org/p>",
                                        "o" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "z" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "s1" to "<http://example.org/s3>",
                                        "p1" to "<http://example.org/p>"
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/s3>",
                                        "p" to "<http://example.org/p>",
                                        "o" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "z" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "s1" to "<http://example.org/s4>",
                                        "p1" to "<http://example.org/p>"
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/bind03.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "s",
                                        "p",
                                        "o"
                                ), listOf(
                                        mutableMapOf(
                                                "s" to "<http://example.org/p>",
                                                "p" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                                "o" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/s1>",
                                                "p" to "<http://example.org/p>",
                                                "o" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/s2>",
                                                "p" to "<http://example.org/p>",
                                                "o" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/s3>",
                                                "p" to "<http://example.org/p>",
                                                "o" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/s4>",
                                                "p" to "<http://example.org/p>",
                                                "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        ),
                                        mutableMapOf(
                                                "s" to "_:1",
                                                "p" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                                "o" to "<http://www.w3.org/2002/07/owl#Ontology>"
                                        )
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "p"
                                ), listOf(
                                        mutableMapOf(
                                                "p" to "<http://example.org/p>"
                                        )
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                                mutableMapOf(
                                        "s" to "<http://example.org/s1>",
                                        "p" to "<http://example.org/p>",
                                        "o" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/s2>",
                                        "p" to "<http://example.org/p>",
                                        "o" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/s3>",
                                        "p" to "<http://example.org/p>",
                                        "o" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/s4>",
                                        "p" to "<http://example.org/p>",
                                        "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/bind07.rq */,
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "x",
                                "c"
                            ), listOf(
                                mutableMapOf(
                                    "x" to "<http://example.org/x/c>",
                                    "c" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/x/p>",
                                    "c" to "<http://www.w3.org/2002/07/owl#ObjectProperty>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/x/x>",
                                    "c" to "<http://example.org/x/c>"
                                ),
                                mutableMapOf(
                                    "x" to "_:ont",
                                    "c" to "<http://www.w3.org/2002/07/owl#Ontology>"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "c"
                            ), listOf(
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "c"
                        ), listOf(
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/sparqldl-02.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "x",
                                "c"
                            ), listOf(
                                mutableMapOf(
                                    "x" to "<http://example.org/x/a>",
                                    "c" to "<http://example.org/x/c>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/x/a>",
                                    "c" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/x/c>",
                                    "c" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/x/d>",
                                    "c" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/x/p>",
                                    "c" to "<http://www.w3.org/2002/07/owl#ObjectProperty>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/x/x>",
                                    "c" to "<http://example.org/x/c>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/x/x>",
                                    "c" to "<http://example.org/x/d>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/x/x>",
                                    "c" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/x/x>",
                                    "c" to "_:x"
                                ),
                                mutableMapOf(
                                    "x" to "_:sparql-dl",
                                    "c" to "<http://www.w3.org/2002/07/owl#Ontology>"
                                ),
                                mutableMapOf(
                                    "x" to "_:x",
                                    "c" to "<http://www.w3.org/2002/07/owl#Restriction>"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "c"
                            ), listOf(
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "c"
                        ), listOf(
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/sparqldl-03.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "x",
                                "c"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "#y"
                            ), listOf(
                                mutableMapOf(
                                    "x" to "<http://example.org/x/x>",
                                    "#y" to "<http://example.org/x/a>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "c",
                            "#y"
                        ), listOf(
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/sparqldl-03.rq */
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "X"
                                ), listOf(
                                        mutableMapOf(
                                                "X" to "<http://example.org/test#a>"
                                        ),
                                        mutableMapOf(
                                                "X" to "<http://example.org/test#b>"
                                        ),
                                        mutableMapOf(
                                                "X" to "<http://example.org/test#c>"
                                        )
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "X",
                                        "Y1"
                                ), listOf(
                                        mutableMapOf(
                                                "X" to "<http://example.org/test#a>",
                                                "Y1" to "\"A\""
                                        ),
                                        mutableMapOf(
                                                "X" to "<http://example.org/test#b>",
                                                "Y1" to "\"B\""
                                        )
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "X",
                                "Y1"
                        ), listOf(
                                mutableMapOf(
                                        "X" to "<http://example.org/test#a>",
                                        "Y1" to "\"A\""
                                ),
                                mutableMapOf(
                                        "X" to "<http://example.org/test#b>",
                                        "Y1" to "\"B\""
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/sparqldl-04.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "X",
                                        "Y1"
                                ), listOf(
                                        mutableMapOf(
                                                "X" to "<http://example.org/test#a>",
                                                "Y1" to "\"A\""
                                        ),
                                        mutableMapOf(
                                                "X" to "<http://example.org/test#b>",
                                                "Y1" to "\"B\""
                                        )
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "X",
                                        "Y2"
                                ), listOf(
                                        mutableMapOf(
                                                "X" to "<http://example.org/test#a>",
                                                "Y2" to "\"Anick\""
                                        ),
                                        mutableMapOf(
                                                "X" to "<http://example.org/test#b>",
                                                "Y2" to "\"Bnick\""
                                        )
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "X",
                                "Y1",
                                "Y2"
                        ), listOf(
                                mutableMapOf(
                                        "X" to "<http://example.org/test#a>",
                                        "Y1" to "\"A\"",
                                        "Y2" to "\"Anick\""
                                ),
                                mutableMapOf(
                                        "X" to "<http://example.org/test#b>",
                                        "Y1" to "\"B\"",
                                        "Y2" to "\"Bnick\""
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/sparqldl-04.rq */,
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#aa"
                            ), listOf(
                                mutableMapOf(
                                    "#aa" to "<http://example.org/test#aa>"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#aa",
                                "#dd"
                            ), listOf(
                                mutableMapOf(
                                    "#aa" to "<http://example.org/test#aa>",
                                    "#dd" to "<http://example.org/test#ee>"
                                ),
                                mutableMapOf(
                                    "#aa" to "<http://example.org/test#cc>",
                                    "#dd" to "<http://example.org/test#dd>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#aa",
                            "#dd"
                        ), listOf(
                            mutableMapOf(
                                "#aa" to "<http://example.org/test#aa>",
                                "#dd" to "<http://example.org/test#ee>"
                            )
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/sparqldl-06.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#aa",
                                "#dd"
                            ), listOf(
                                mutableMapOf(
                                    "#aa" to "<http://example.org/test#aa>",
                                    "#dd" to "<http://example.org/test#ee>"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#dd",
                                "#bb"
                            ), listOf(
                                mutableMapOf(
                                    "#dd" to "<http://example.org/test#dd>",
                                    "#bb" to "<http://example.org/test#bb>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#aa",
                            "#dd",
                            "#bb"
                        ), listOf(
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/sparqldl-06.rq */
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "#aa"
                                ), listOf(
                                        mutableMapOf(
                                                "#aa" to "<http://example.org/test#aa>"
                                        )
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "X",
                                        "Y"
                                ), listOf(
                                        mutableMapOf(
                                                "X" to "<http://example.org/test#dd>",
                                                "Y" to "<http://example.org/test#bb>"
                                        )
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "#aa",
                                "X",
                                "Y"
                        ), listOf(
                                mutableMapOf(
                                        "#aa" to "<http://example.org/test#aa>",
                                        "X" to "<http://example.org/test#dd>",
                                        "Y" to "<http://example.org/test#bb>"
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/sparqldl-07.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "#aa",
                                        "X",
                                        "Y"
                                ), listOf(
                                        mutableMapOf(
                                                "#aa" to "<http://example.org/test#aa>",
                                                "X" to "<http://example.org/test#dd>",
                                                "Y" to "<http://example.org/test#bb>"
                                        )
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "Y",
                                        "#aa"
                                ), listOf(
                                        mutableMapOf(
                                                "Y" to "<http://example.org/test#bb>",
                                                "#aa" to "<http://example.org/test#aa>"
                                        )
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "#aa",
                                "X",
                                "Y"
                        ), listOf(
                                mutableMapOf(
                                        "#aa" to "<http://example.org/test#aa>",
                                        "X" to "<http://example.org/test#dd>",
                                        "Y" to "<http://example.org/test#bb>"
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/sparqldl-07.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "#aa",
                                        "X",
                                        "Y"
                                ), listOf(
                                        mutableMapOf(
                                                "#aa" to "<http://example.org/test#aa>",
                                                "X" to "<http://example.org/test#dd>",
                                                "Y" to "<http://example.org/test#bb>"
                                        )
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "#aa",
                                        "Z"
                                ), listOf(
                                        mutableMapOf(
                                                "#aa" to "<http://example.org/test#aa>",
                                                "Z" to "<http://example.org/test#ee>"
                                        ),
                                        mutableMapOf(
                                                "#aa" to "<http://example.org/test#cc>",
                                                "Z" to "<http://example.org/test#dd>"
                                        )
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "#aa",
                                "X",
                                "Y",
                                "Z"
                        ), listOf(
                                mutableMapOf(
                                        "#aa" to "<http://example.org/test#aa>",
                                        "X" to "<http://example.org/test#dd>",
                                        "Y" to "<http://example.org/test#bb>",
                                        "Z" to "<http://example.org/test#ee>"
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/sparqldl-07.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "X",
                                        "#a"
                                ), listOf(
                                        mutableMapOf(
                                                "X" to "<http://example.org/test#a>",
                                                "#a" to "<http://example.org/test#aa>"
                                        )
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "#a",
                                        "Y"
                                ), listOf(
                                        mutableMapOf(
                                                "#a" to "<http://example.org/test#aa>",
                                                "Y" to "<http://example.org/test#ee>"
                                        ),
                                        mutableMapOf(
                                                "#a" to "<http://example.org/test#cc>",
                                                "Y" to "<http://example.org/test#dd>"
                                        )
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "X",
                                "#a",
                                "Y"
                        ), listOf(
                                mutableMapOf(
                                        "X" to "<http://example.org/test#a>",
                                        "#a" to "<http://example.org/test#aa>",
                                        "Y" to "<http://example.org/test#ee>"
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/sparqldl-08.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "X",
                                        "#a"
                                ), listOf(
                                        mutableMapOf(
                                                "X" to "<http://example.org/test#a>",
                                                "#a" to "<http://example.org/test#b>"
                                        )
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "#a",
                                        "Y"
                                ), listOf(
                                        mutableMapOf(
                                                "#a" to "<http://example.org/test#b>",
                                                "Y" to "<http://example.org/test#c>"
                                        ),
                                        mutableMapOf(
                                                "#a" to "<http://example.org/test#b>",
                                                "Y" to "<http://example.org/test#h>"
                                        ),
                                        mutableMapOf(
                                                "#a" to "<http://example.org/test#b>",
                                                "Y" to "<http://example.org/test#i>"
                                        ),
                                        mutableMapOf(
                                                "#a" to "<http://example.org/test#x>",
                                                "Y" to "<http://example.org/test#x>"
                                        )
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "X",
                                "#a",
                                "Y"
                        ), listOf(
                                mutableMapOf(
                                        "X" to "<http://example.org/test#a>",
                                        "#a" to "<http://example.org/test#b>",
                                        "Y" to "<http://example.org/test#c>"
                                ),
                                mutableMapOf(
                                        "X" to "<http://example.org/test#a>",
                                        "#a" to "<http://example.org/test#b>",
                                        "Y" to "<http://example.org/test#h>"
                                ),
                                mutableMapOf(
                                        "X" to "<http://example.org/test#a>",
                                        "#a" to "<http://example.org/test#b>",
                                        "Y" to "<http://example.org/test#i>"
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/sparqldl-09.rq */,
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "X",
                                "a"
                            ), listOf(
                                mutableMapOf(
                                    "X" to "<http://example.org/test#a>",
                                    "a" to "<http://example.org/test#b>"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "a",
                                "b"
                            ), listOf(
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "X",
                            "a",
                            "b"
                        ), listOf(
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/sparqldl-10.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "X",
                                "a",
                                "b",
                                "Y"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "Y",
                                "c"
                            ), listOf(
                                mutableMapOf(
                                    "Y" to "<http://example.org/test#b>",
                                    "c" to "<http://example.org/test#c>"
                                ),
                                mutableMapOf(
                                    "Y" to "<http://example.org/test#b>",
                                    "c" to "<http://example.org/test#h>"
                                ),
                                mutableMapOf(
                                    "Y" to "<http://example.org/test#b>",
                                    "c" to "<http://example.org/test#i>"
                                ),
                                mutableMapOf(
                                    "Y" to "<http://example.org/test#x>",
                                    "c" to "<http://example.org/test#x>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "X",
                            "a",
                            "b",
                            "Y",
                            "c"
                        ), listOf(
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/sparqldl-10.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "a",
                                "b"
                            ), listOf(
                                mutableMapOf(
                                    "a" to "<http://example.org/test#a>",
                                    "b" to "<http://example.org/test#b>"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "b",
                                "x"
                            ), listOf(
                                mutableMapOf(
                                    "b" to "<http://example.org/test#b>",
                                    "x" to "<http://example.org/test#x>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "a",
                            "b",
                            "x"
                        ), listOf(
                            mutableMapOf(
                                "a" to "<http://example.org/test#a>",
                                "b" to "<http://example.org/test#b>",
                                "x" to "<http://example.org/test#x>"
                            )
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/sparqldl-13.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "a",
                                "b",
                                "x"
                            ), listOf(
                                mutableMapOf(
                                    "a" to "<http://example.org/test#a>",
                                    "b" to "<http://example.org/test#b>",
                                    "x" to "<http://example.org/test#x>"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "x"
                            ), listOf(
                                mutableMapOf(
                                    "x" to "<http://example.org/test#c>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#d>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#x>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "a",
                            "b",
                            "x"
                        ), listOf(
                            mutableMapOf(
                                "a" to "<http://example.org/test#a>",
                                "b" to "<http://example.org/test#b>",
                                "x" to "<http://example.org/test#x>"
                            )
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/sparqldl-13.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#_18569"
                            ), listOf(
                                mutableMapOf(
                                    "#_18569" to "_:_18554"
                                ),
                                mutableMapOf(
                                    "#_18569" to "_:_18555"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_18569"
                            ), listOf(
                                mutableMapOf(
                                    "#_18569" to "_:_18554"
                                ),
                                mutableMapOf(
                                    "#_18569" to "_:_18555"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_18569"
                        ), listOf(
                            mutableMapOf(
                                "#_18569" to "_:_18554"
                            ),
                            mutableMapOf(
                                "#_18569" to "_:_18555"
                            )
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/parent3.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#_18569"
                            ), listOf(
                                mutableMapOf(
                                    "#_18569" to "_:_18554"
                                ),
                                mutableMapOf(
                                    "#_18569" to "_:_18555"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_18569"
                            ), listOf(
                                mutableMapOf(
                                    "#_18569" to "_:_18554"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_18569"
                        ), listOf(
                            mutableMapOf(
                                "#_18569" to "_:_18554"
                            )
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/parent3.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#_18569"
                            ), listOf(
                                mutableMapOf(
                                    "#_18569" to "_:_18554"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "parent",
                                "#_18569"
                            ), listOf(
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Alice>",
                                    "#_18569" to "<http://example.org/test#Female>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Alice>",
                                    "#_18569" to "<http://example.org/test#Parent>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Alice>",
                                    "#_18569" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Bob>",
                                    "#_18569" to "<http://example.org/test#Male>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Bob>",
                                    "#_18569" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Charlie>",
                                    "#_18569" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Dudley>",
                                    "#_18569" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Dudley>",
                                    "#_18569" to "_:_18555"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Father>",
                                    "#_18569" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Female>",
                                    "#_18569" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Male>",
                                    "#_18569" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Mother>",
                                    "#_18569" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Parent>",
                                    "#_18569" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#hasChild>",
                                    "#_18569" to "<http://www.w3.org/2002/07/owl#ObjectProperty>"
                                ),
                                mutableMapOf(
                                    "parent" to "_:_18548",
                                    "#_18569" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "_:_18551",
                                    "#_18569" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "_:_18554",
                                    "#_18569" to "<http://www.w3.org/2002/07/owl#Restriction>"
                                ),
                                mutableMapOf(
                                    "parent" to "_:_18555",
                                    "#_18569" to "<http://www.w3.org/2002/07/owl#Restriction>"
                                ),
                                mutableMapOf(
                                    "parent" to "_:_18556",
                                    "#_18569" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "_:ont",
                                    "#_18569" to "<http://www.w3.org/2002/07/owl#Ontology>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_18569",
                            "parent"
                        ), listOf(
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/parent3.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#_18659"
                            ), listOf(
                                mutableMapOf(
                                    "#_18659" to "_:_18641"
                                ),
                                mutableMapOf(
                                    "#_18659" to "_:_18642"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_18659"
                            ), listOf(
                                mutableMapOf(
                                    "#_18659" to "_:_18641"
                                ),
                                mutableMapOf(
                                    "#_18659" to "_:_18642"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_18659"
                        ), listOf(
                            mutableMapOf(
                                "#_18659" to "_:_18641"
                            ),
                            mutableMapOf(
                                "#_18659" to "_:_18642"
                            )
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/parent4.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#_18659"
                            ), listOf(
                                mutableMapOf(
                                    "#_18659" to "_:_18641"
                                ),
                                mutableMapOf(
                                    "#_18659" to "_:_18642"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_18659"
                            ), listOf(
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_18659"
                        ), listOf(
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/parent4.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#_18659"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "parent",
                                "#_18659"
                            ), listOf(
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Alice>",
                                    "#_18659" to "<http://example.org/test#Female>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Alice>",
                                    "#_18659" to "<http://example.org/test#Parent>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Alice>",
                                    "#_18659" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Bob>",
                                    "#_18659" to "<http://example.org/test#Male>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Bob>",
                                    "#_18659" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Charlie>",
                                    "#_18659" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Dudley>",
                                    "#_18659" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Dudley>",
                                    "#_18659" to "_:_18642"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Father>",
                                    "#_18659" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Female>",
                                    "#_18659" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Male>",
                                    "#_18659" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Mother>",
                                    "#_18659" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Parent>",
                                    "#_18659" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#hasChild>",
                                    "#_18659" to "<http://www.w3.org/2002/07/owl#ObjectProperty>"
                                ),
                                mutableMapOf(
                                    "parent" to "_:_18635",
                                    "#_18659" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "_:_18638",
                                    "#_18659" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "_:_18641",
                                    "#_18659" to "<http://www.w3.org/2002/07/owl#Restriction>"
                                ),
                                mutableMapOf(
                                    "parent" to "_:_18642",
                                    "#_18659" to "<http://www.w3.org/2002/07/owl#Restriction>"
                                ),
                                mutableMapOf(
                                    "parent" to "_:_18643",
                                    "#_18659" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "_:ont",
                                    "#_18659" to "<http://www.w3.org/2002/07/owl#Ontology>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_18659",
                            "parent"
                        ), listOf(
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/parent4.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#_18751"
                            ), listOf(
                                mutableMapOf(
                                    "#_18751" to "_:_18736"
                                ),
                                mutableMapOf(
                                    "#_18751" to "_:_18737"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_18751"
                            ), listOf(
                                mutableMapOf(
                                    "#_18751" to "_:_18736"
                                ),
                                mutableMapOf(
                                    "#_18751" to "_:_18737"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_18751"
                        ), listOf(
                            mutableMapOf(
                                "#_18751" to "_:_18736"
                            ),
                            mutableMapOf(
                                "#_18751" to "_:_18737"
                            )
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/parent5.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#_18751"
                            ), listOf(
                                mutableMapOf(
                                    "#_18751" to "_:_18736"
                                ),
                                mutableMapOf(
                                    "#_18751" to "_:_18737"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_18751"
                            ), listOf(
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_18751"
                        ), listOf(
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/parent5.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#_18751"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "parent",
                                "#_18751"
                            ), listOf(
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Alice>",
                                    "#_18751" to "<http://example.org/test#Female>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Alice>",
                                    "#_18751" to "<http://example.org/test#Parent>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Alice>",
                                    "#_18751" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Bob>",
                                    "#_18751" to "<http://example.org/test#Male>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Bob>",
                                    "#_18751" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Charlie>",
                                    "#_18751" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Dudley>",
                                    "#_18751" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Dudley>",
                                    "#_18751" to "_:_18737"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Father>",
                                    "#_18751" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Female>",
                                    "#_18751" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Male>",
                                    "#_18751" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Mother>",
                                    "#_18751" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Parent>",
                                    "#_18751" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#hasChild>",
                                    "#_18751" to "<http://www.w3.org/2002/07/owl#ObjectProperty>"
                                ),
                                mutableMapOf(
                                    "parent" to "_:_18730",
                                    "#_18751" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "_:_18733",
                                    "#_18751" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "_:_18736",
                                    "#_18751" to "<http://www.w3.org/2002/07/owl#Restriction>"
                                ),
                                mutableMapOf(
                                    "parent" to "_:_18737",
                                    "#_18751" to "<http://www.w3.org/2002/07/owl#Restriction>"
                                ),
                                mutableMapOf(
                                    "parent" to "_:_18738",
                                    "#_18751" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "_:ont",
                                    "#_18751" to "<http://www.w3.org/2002/07/owl#Ontology>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_18751",
                            "parent"
                        ), listOf(
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/parent5.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#_18839"
                            ), listOf(
                                mutableMapOf(
                                    "#_18839" to "_:_18823"
                                ),
                                mutableMapOf(
                                    "#_18839" to "_:_18824"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_18839"
                            ), listOf(
                                mutableMapOf(
                                    "#_18839" to "_:_18823"
                                ),
                                mutableMapOf(
                                    "#_18839" to "_:_18824"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_18839"
                        ), listOf(
                            mutableMapOf(
                                "#_18839" to "_:_18823"
                            ),
                            mutableMapOf(
                                "#_18839" to "_:_18824"
                            )
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/parent6.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#_18839"
                            ), listOf(
                                mutableMapOf(
                                    "#_18839" to "_:_18823"
                                ),
                                mutableMapOf(
                                    "#_18839" to "_:_18824"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_18839"
                            ), listOf(
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_18839"
                        ), listOf(
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/parent6.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#_18839"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "parent",
                                "#_18839"
                            ), listOf(
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Alice>",
                                    "#_18839" to "<http://example.org/test#Female>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Alice>",
                                    "#_18839" to "<http://example.org/test#Parent>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Alice>",
                                    "#_18839" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Bob>",
                                    "#_18839" to "<http://example.org/test#Male>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Bob>",
                                    "#_18839" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Charlie>",
                                    "#_18839" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Dudley>",
                                    "#_18839" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Dudley>",
                                    "#_18839" to "_:_18824"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Father>",
                                    "#_18839" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Female>",
                                    "#_18839" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Male>",
                                    "#_18839" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Mother>",
                                    "#_18839" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Parent>",
                                    "#_18839" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#hasChild>",
                                    "#_18839" to "<http://www.w3.org/2002/07/owl#ObjectProperty>"
                                ),
                                mutableMapOf(
                                    "parent" to "_:_18817",
                                    "#_18839" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "_:_18820",
                                    "#_18839" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "_:_18823",
                                    "#_18839" to "<http://www.w3.org/2002/07/owl#Restriction>"
                                ),
                                mutableMapOf(
                                    "parent" to "_:_18824",
                                    "#_18839" to "<http://www.w3.org/2002/07/owl#Restriction>"
                                ),
                                mutableMapOf(
                                    "parent" to "_:_18825",
                                    "#_18839" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "_:ont",
                                    "#_18839" to "<http://www.w3.org/2002/07/owl#Ontology>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_18839",
                            "parent"
                        ), listOf(
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/parent6.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#_18943"
                            ), listOf(
                                mutableMapOf(
                                    "#_18943" to "_:_18927"
                                ),
                                mutableMapOf(
                                    "#_18943" to "_:_18928"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_18943"
                            ), listOf(
                                mutableMapOf(
                                    "#_18943" to "_:_18927"
                                ),
                                mutableMapOf(
                                    "#_18943" to "_:_18928"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_18943"
                        ), listOf(
                            mutableMapOf(
                                "#_18943" to "_:_18927"
                            ),
                            mutableMapOf(
                                "#_18943" to "_:_18928"
                            )
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/parent7.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#_18943"
                            ), listOf(
                                mutableMapOf(
                                    "#_18943" to "_:_18927"
                                ),
                                mutableMapOf(
                                    "#_18943" to "_:_18928"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_18943"
                            ), listOf(
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_18943"
                        ), listOf(
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/parent7.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#_18943"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "parent",
                                "#_18943"
                            ), listOf(
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Alice>",
                                    "#_18943" to "<http://example.org/test#Female>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Alice>",
                                    "#_18943" to "<http://example.org/test#Parent>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Alice>",
                                    "#_18943" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Bob>",
                                    "#_18943" to "<http://example.org/test#Male>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Bob>",
                                    "#_18943" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Charlie>",
                                    "#_18943" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Dudley>",
                                    "#_18943" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Dudley>",
                                    "#_18943" to "_:_18928"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Father>",
                                    "#_18943" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Female>",
                                    "#_18943" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Male>",
                                    "#_18943" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Mother>",
                                    "#_18943" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Parent>",
                                    "#_18943" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#hasChild>",
                                    "#_18943" to "<http://www.w3.org/2002/07/owl#ObjectProperty>"
                                ),
                                mutableMapOf(
                                    "parent" to "_:_18921",
                                    "#_18943" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "_:_18924",
                                    "#_18943" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "_:_18927",
                                    "#_18943" to "<http://www.w3.org/2002/07/owl#Restriction>"
                                ),
                                mutableMapOf(
                                    "parent" to "_:_18928",
                                    "#_18943" to "<http://www.w3.org/2002/07/owl#Restriction>"
                                ),
                                mutableMapOf(
                                    "parent" to "_:_18929",
                                    "#_18943" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "_:ont",
                                    "#_18943" to "<http://www.w3.org/2002/07/owl#Ontology>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_18943",
                            "parent"
                        ), listOf(
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/parent7.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#_19047"
                            ), listOf(
                                mutableMapOf(
                                    "#_19047" to "_:_19031"
                                ),
                                mutableMapOf(
                                    "#_19047" to "_:_19032"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_19047"
                            ), listOf(
                                mutableMapOf(
                                    "#_19047" to "_:_19031"
                                ),
                                mutableMapOf(
                                    "#_19047" to "_:_19032"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_19047"
                        ), listOf(
                            mutableMapOf(
                                "#_19047" to "_:_19031"
                            ),
                            mutableMapOf(
                                "#_19047" to "_:_19032"
                            )
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/parent8.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#_19047"
                            ), listOf(
                                mutableMapOf(
                                    "#_19047" to "_:_19031"
                                ),
                                mutableMapOf(
                                    "#_19047" to "_:_19032"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_19047"
                            ), listOf(
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_19047"
                        ), listOf(
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/parent8.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#_19047"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "parent",
                                "#_19047"
                            ), listOf(
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Alice>",
                                    "#_19047" to "<http://example.org/test#Female>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Alice>",
                                    "#_19047" to "<http://example.org/test#Parent>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Alice>",
                                    "#_19047" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Bob>",
                                    "#_19047" to "<http://example.org/test#Male>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Bob>",
                                    "#_19047" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Charlie>",
                                    "#_19047" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Dudley>",
                                    "#_19047" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Dudley>",
                                    "#_19047" to "_:_19032"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Father>",
                                    "#_19047" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Female>",
                                    "#_19047" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Male>",
                                    "#_19047" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Mother>",
                                    "#_19047" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Parent>",
                                    "#_19047" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#hasChild>",
                                    "#_19047" to "<http://www.w3.org/2002/07/owl#ObjectProperty>"
                                ),
                                mutableMapOf(
                                    "parent" to "_:_19025",
                                    "#_19047" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "_:_19028",
                                    "#_19047" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "_:_19031",
                                    "#_19047" to "<http://www.w3.org/2002/07/owl#Restriction>"
                                ),
                                mutableMapOf(
                                    "parent" to "_:_19032",
                                    "#_19047" to "<http://www.w3.org/2002/07/owl#Restriction>"
                                ),
                                mutableMapOf(
                                    "parent" to "_:_19033",
                                    "#_19047" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "_:ont",
                                    "#_19047" to "<http://www.w3.org/2002/07/owl#Ontology>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_19047",
                            "parent"
                        ), listOf(
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/parent8.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#_19152"
                            ), listOf(
                                mutableMapOf(
                                    "#_19152" to "_:_19135"
                                ),
                                mutableMapOf(
                                    "#_19152" to "_:_19136"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_19152"
                            ), listOf(
                                mutableMapOf(
                                    "#_19152" to "_:_19135"
                                ),
                                mutableMapOf(
                                    "#_19152" to "_:_19136"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_19152"
                        ), listOf(
                            mutableMapOf(
                                "#_19152" to "_:_19135"
                            ),
                            mutableMapOf(
                                "#_19152" to "_:_19136"
                            )
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/parent9.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#_19152"
                            ), listOf(
                                mutableMapOf(
                                    "#_19152" to "_:_19135"
                                ),
                                mutableMapOf(
                                    "#_19152" to "_:_19136"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_19152"
                            ), listOf(
                                mutableMapOf(
                                    "#_19152" to "_:_19135"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_19152"
                        ), listOf(
                            mutableMapOf(
                                "#_19152" to "_:_19135"
                            )
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/parent9.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#_19152"
                            ), listOf(
                                mutableMapOf(
                                    "#_19152" to "_:_19135"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "C",
                                "#_19152"
                            ), listOf(
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_19152",
                            "C"
                        ), listOf(
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/parent9.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "C",
                                "#b"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#b"
                            ), listOf(
                                mutableMapOf(
                                    "#b" to "_:_19226"
                                ),
                                mutableMapOf(
                                    "#b" to "_:_19227"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "C",
                            "#b"
                        ), listOf(
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/parent10.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "C",
                                "#b"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#b"
                            ), listOf(
                                mutableMapOf(
                                    "#b" to "_:_19226"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "C",
                            "#b"
                        ), listOf(
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/parent10.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#_19343",
                                "#_19348",
                                "#_19340"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "#_19340"
                            ), listOf(
                                mutableMapOf(
                                    "x" to "<http://example.org/test#A>",
                                    "#_19340" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#B>",
                                    "#_19340" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#C>",
                                    "#_19340" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#a>",
                                    "#_19340" to "<http://example.org/test#A>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#a>",
                                    "#_19340" to "<http://example.org/test#B>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#b>",
                                    "#_19340" to "<http://example.org/test#B>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#c>",
                                    "#_19340" to "<http://example.org/test#C>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#d>",
                                    "#_19340" to "<http://example.org/test#A>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#d>",
                                    "#_19340" to "<http://example.org/test#B>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#d>",
                                    "#_19340" to "<http://example.org/test#C>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#p>",
                                    "#_19340" to "<http://www.w3.org/2002/07/owl#FunctionalProperty>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#p>",
                                    "#_19340" to "<http://www.w3.org/2002/07/owl#ObjectProperty>"
                                ),
                                mutableMapOf(
                                    "x" to "_:simple",
                                    "#_19340" to "<http://www.w3.org/2002/07/owl#Ontology>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_19343",
                            "#_19348",
                            "#_19340",
                            "x"
                        ), listOf(
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/simple1.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#_19448",
                                "#_19452",
                                "#_19462",
                                "#_19445"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "#_19445"
                            ), listOf(
                                mutableMapOf(
                                    "x" to "<http://example.org/test#A>",
                                    "#_19445" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#B>",
                                    "#_19445" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#C>",
                                    "#_19445" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#a>",
                                    "#_19445" to "<http://example.org/test#A>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#a>",
                                    "#_19445" to "<http://example.org/test#B>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#b>",
                                    "#_19445" to "<http://example.org/test#B>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#c>",
                                    "#_19445" to "<http://example.org/test#C>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#d>",
                                    "#_19445" to "<http://example.org/test#A>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#d>",
                                    "#_19445" to "<http://example.org/test#B>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#d>",
                                    "#_19445" to "<http://example.org/test#C>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#p>",
                                    "#_19445" to "<http://www.w3.org/2002/07/owl#FunctionalProperty>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#p>",
                                    "#_19445" to "<http://www.w3.org/2002/07/owl#ObjectProperty>"
                                ),
                                mutableMapOf(
                                    "x" to "_:simple",
                                    "#_19445" to "<http://www.w3.org/2002/07/owl#Ontology>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_19448",
                            "#_19452",
                            "#_19462",
                            "#_19445",
                            "x"
                        ), listOf(
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/simple2.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#_19589",
                                "#_19600",
                                "#_19605",
                                "#_19597"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "#_19589"
                            ), listOf(
                                mutableMapOf(
                                    "x" to "<http://example.org/test#A>",
                                    "#_19589" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#B>",
                                    "#_19589" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#C>",
                                    "#_19589" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#a>",
                                    "#_19589" to "<http://example.org/test#A>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#a>",
                                    "#_19589" to "<http://example.org/test#B>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#b>",
                                    "#_19589" to "<http://example.org/test#B>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#c>",
                                    "#_19589" to "<http://example.org/test#C>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#d>",
                                    "#_19589" to "<http://example.org/test#A>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#d>",
                                    "#_19589" to "<http://example.org/test#B>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#d>",
                                    "#_19589" to "<http://example.org/test#C>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#p>",
                                    "#_19589" to "<http://www.w3.org/2002/07/owl#FunctionalProperty>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#p>",
                                    "#_19589" to "<http://www.w3.org/2002/07/owl#ObjectProperty>"
                                ),
                                mutableMapOf(
                                    "x" to "_:simple",
                                    "#_19589" to "<http://www.w3.org/2002/07/owl#Ontology>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_19589",
                            "#_19600",
                            "#_19605",
                            "#_19597",
                            "x"
                        ), listOf(
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/simple3.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#_19733"
                            ), listOf(
                                mutableMapOf(
                                    "#_19733" to "<http://example.org/test#A>"
                                ),
                                mutableMapOf(
                                    "#_19733" to "<http://example.org/test#B>"
                                ),
                                mutableMapOf(
                                    "#_19733" to "<http://example.org/test#C>"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_19738"
                            ), listOf(
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_19733",
                            "#_19738"
                        ), listOf(
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/simple4.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#_19733",
                                "#_19738",
                                "#_19743"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "#_19733"
                            ), listOf(
                                mutableMapOf(
                                    "x" to "<http://example.org/test#A>",
                                    "#_19733" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#B>",
                                    "#_19733" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#C>",
                                    "#_19733" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#a>",
                                    "#_19733" to "<http://example.org/test#A>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#a>",
                                    "#_19733" to "<http://example.org/test#B>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#b>",
                                    "#_19733" to "<http://example.org/test#B>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#c>",
                                    "#_19733" to "<http://example.org/test#C>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#d>",
                                    "#_19733" to "<http://example.org/test#A>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#d>",
                                    "#_19733" to "<http://example.org/test#B>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#d>",
                                    "#_19733" to "<http://example.org/test#C>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#p>",
                                    "#_19733" to "<http://www.w3.org/2002/07/owl#FunctionalProperty>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#p>",
                                    "#_19733" to "<http://www.w3.org/2002/07/owl#ObjectProperty>"
                                ),
                                mutableMapOf(
                                    "x" to "_:simple",
                                    "#_19733" to "<http://www.w3.org/2002/07/owl#Ontology>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_19733",
                            "#_19738",
                            "#_19743",
                            "x"
                        ), listOf(
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/simple4.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#_19850"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_19858"
                            ), listOf(
                                mutableMapOf(
                                    "#_19858" to "<http://example.org/test#A>"
                                ),
                                mutableMapOf(
                                    "#_19858" to "<http://example.org/test#B>"
                                ),
                                mutableMapOf(
                                    "#_19858" to "<http://example.org/test#C>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_19850",
                            "#_19858"
                        ), listOf(
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/simple5.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#_19850",
                                "#_19858",
                                "#_19863",
                                "#_19868"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "#_19850"
                            ), listOf(
                                mutableMapOf(
                                    "x" to "<http://example.org/test#A>",
                                    "#_19850" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#B>",
                                    "#_19850" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#C>",
                                    "#_19850" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#a>",
                                    "#_19850" to "<http://example.org/test#A>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#a>",
                                    "#_19850" to "<http://example.org/test#B>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#b>",
                                    "#_19850" to "<http://example.org/test#B>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#c>",
                                    "#_19850" to "<http://example.org/test#C>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#d>",
                                    "#_19850" to "<http://example.org/test#A>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#d>",
                                    "#_19850" to "<http://example.org/test#B>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#d>",
                                    "#_19850" to "<http://example.org/test#C>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#p>",
                                    "#_19850" to "<http://www.w3.org/2002/07/owl#FunctionalProperty>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#p>",
                                    "#_19850" to "<http://www.w3.org/2002/07/owl#ObjectProperty>"
                                ),
                                mutableMapOf(
                                    "x" to "_:simple",
                                    "#_19850" to "<http://www.w3.org/2002/07/owl#Ontology>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_19850",
                            "#_19858",
                            "#_19863",
                            "#_19868",
                            "x"
                        ), listOf(
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/simple5.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#_20006"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_20014"
                            ), listOf(
                                mutableMapOf(
                                    "#_20014" to "<http://example.org/test#A>"
                                ),
                                mutableMapOf(
                                    "#_20014" to "<http://example.org/test#B>"
                                ),
                                mutableMapOf(
                                    "#_20014" to "<http://example.org/test#C>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_20006",
                            "#_20014"
                        ), listOf(
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/simple6.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#_20006",
                                "#_20014",
                                "#_20019",
                                "#_20024",
                                "#_20029"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "#_20006"
                            ), listOf(
                                mutableMapOf(
                                    "x" to "<http://example.org/test#A>",
                                    "#_20006" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#B>",
                                    "#_20006" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#C>",
                                    "#_20006" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#a>",
                                    "#_20006" to "<http://example.org/test#A>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#a>",
                                    "#_20006" to "<http://example.org/test#B>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#b>",
                                    "#_20006" to "<http://example.org/test#B>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#c>",
                                    "#_20006" to "<http://example.org/test#C>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#d>",
                                    "#_20006" to "<http://example.org/test#A>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#d>",
                                    "#_20006" to "<http://example.org/test#B>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#d>",
                                    "#_20006" to "<http://example.org/test#C>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#p>",
                                    "#_20006" to "<http://www.w3.org/2002/07/owl#FunctionalProperty>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#p>",
                                    "#_20006" to "<http://www.w3.org/2002/07/owl#ObjectProperty>"
                                ),
                                mutableMapOf(
                                    "x" to "_:simple",
                                    "#_20006" to "<http://www.w3.org/2002/07/owl#Ontology>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_20006",
                            "#_20014",
                            "#_20019",
                            "#_20024",
                            "#_20029",
                            "x"
                        ), listOf(
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/simple6.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#_20187"
                            ), listOf(
                                mutableMapOf(
                                    "#_20187" to "<http://example.org/test#A>"
                                ),
                                mutableMapOf(
                                    "#_20187" to "<http://example.org/test#B>"
                                ),
                                mutableMapOf(
                                    "#_20187" to "<http://example.org/test#C>"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_20192"
                            ), listOf(
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_20187",
                            "#_20192"
                        ), listOf(
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/simple7.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#_20187",
                                "#_20192"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_20196"
                            ), listOf(
                                mutableMapOf(
                                    "#_20196" to "<http://example.org/test#A>"
                                ),
                                mutableMapOf(
                                    "#_20196" to "<http://example.org/test#B>"
                                ),
                                mutableMapOf(
                                    "#_20196" to "<http://example.org/test#C>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_20187",
                            "#_20192",
                            "#_20196"
                        ), listOf(
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/simple7.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#_20187",
                                "#_20192",
                                "#_20196",
                                "#_20201",
                                "#_20206",
                                "#_20212"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "#_20187"
                            ), listOf(
                                mutableMapOf(
                                    "x" to "<http://example.org/test#A>",
                                    "#_20187" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#B>",
                                    "#_20187" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#C>",
                                    "#_20187" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#a>",
                                    "#_20187" to "<http://example.org/test#A>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#a>",
                                    "#_20187" to "<http://example.org/test#B>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#b>",
                                    "#_20187" to "<http://example.org/test#B>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#c>",
                                    "#_20187" to "<http://example.org/test#C>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#d>",
                                    "#_20187" to "<http://example.org/test#A>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#d>",
                                    "#_20187" to "<http://example.org/test#B>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#d>",
                                    "#_20187" to "<http://example.org/test#C>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#p>",
                                    "#_20187" to "<http://www.w3.org/2002/07/owl#FunctionalProperty>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#p>",
                                    "#_20187" to "<http://www.w3.org/2002/07/owl#ObjectProperty>"
                                ),
                                mutableMapOf(
                                    "x" to "_:simple",
                                    "#_20187" to "<http://www.w3.org/2002/07/owl#Ontology>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_20187",
                            "#_20192",
                            "#_20196",
                            "#_20201",
                            "#_20206",
                            "#_20212",
                            "x"
                        ), listOf(
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/simple7.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#_20379",
                                "#_20387"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "#_20379"
                            ), listOf(
                                mutableMapOf(
                                    "x" to "<http://example.org/test#A>",
                                    "#_20379" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#B>",
                                    "#_20379" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#C>",
                                    "#_20379" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#a>",
                                    "#_20379" to "<http://example.org/test#A>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#a>",
                                    "#_20379" to "<http://example.org/test#B>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#b>",
                                    "#_20379" to "<http://example.org/test#B>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#c>",
                                    "#_20379" to "<http://example.org/test#C>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#d>",
                                    "#_20379" to "<http://example.org/test#A>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#d>",
                                    "#_20379" to "<http://example.org/test#B>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#d>",
                                    "#_20379" to "<http://example.org/test#C>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#p>",
                                    "#_20379" to "<http://www.w3.org/2002/07/owl#FunctionalProperty>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#p>",
                                    "#_20379" to "<http://www.w3.org/2002/07/owl#ObjectProperty>"
                                ),
                                mutableMapOf(
                                    "x" to "_:simple",
                                    "#_20379" to "<http://www.w3.org/2002/07/owl#Ontology>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_20379",
                            "#_20387",
                            "x"
                        ), listOf(
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/simple8.rq */
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "str1"
                                ), listOf(
                                        mutableMapOf(
                                                "str1" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                        )
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "str2"
                                ), listOf(
                                        mutableMapOf(
                                                "str2" to "\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                        )
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "str1",
                                "str2"
                        ), listOf(
                                mutableMapOf(
                                        "str1" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                        "str2" to "\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/concat01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "s1",
                                        "str1"
                                ), listOf(
                                        mutableMapOf(
                                                "s1" to "<http://example.org/s1>",
                                                "str1" to "\"123\""
                                        ),
                                        mutableMapOf(
                                                "s1" to "<http://example.org/s2>",
                                                "str1" to "\"日本語\"@ja"
                                        ),
                                        mutableMapOf(
                                                "s1" to "<http://example.org/s3>",
                                                "str1" to "\"english\"@en"
                                        ),
                                        mutableMapOf(
                                                "s1" to "<http://example.org/s4>",
                                                "str1" to "\"français\"@fr"
                                        ),
                                        mutableMapOf(
                                                "s1" to "<http://example.org/s5>",
                                                "str1" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                        ),
                                        mutableMapOf(
                                                "s1" to "<http://example.org/s6>",
                                                "str1" to "\"def\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                        ),
                                        mutableMapOf(
                                                "s1" to "<http://example.org/s7>",
                                                "str1" to "\"7\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        )
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "s2",
                                        "str2"
                                ), listOf(
                                        mutableMapOf(
                                                "s2" to "<http://example.org/s1>",
                                                "str2" to "\"123\""
                                        ),
                                        mutableMapOf(
                                                "s2" to "<http://example.org/s2>",
                                                "str2" to "\"日本語\"@ja"
                                        ),
                                        mutableMapOf(
                                                "s2" to "<http://example.org/s3>",
                                                "str2" to "\"english\"@en"
                                        ),
                                        mutableMapOf(
                                                "s2" to "<http://example.org/s4>",
                                                "str2" to "\"français\"@fr"
                                        ),
                                        mutableMapOf(
                                                "s2" to "<http://example.org/s5>",
                                                "str2" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                        ),
                                        mutableMapOf(
                                                "s2" to "<http://example.org/s6>",
                                                "str2" to "\"def\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                        ),
                                        mutableMapOf(
                                                "s2" to "<http://example.org/s7>",
                                                "str2" to "\"7\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        )
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "s1",
                                "str1",
                                "s2",
                                "str2"
                        ), listOf(
                                mutableMapOf(
                                        "s1" to "<http://example.org/s1>",
                                        "str1" to "\"123\"",
                                        "s2" to "<http://example.org/s1>",
                                        "str2" to "\"123\""
                                ),
                                mutableMapOf(
                                        "s1" to "<http://example.org/s2>",
                                        "str1" to "\"日本語\"@ja",
                                        "s2" to "<http://example.org/s1>",
                                        "str2" to "\"123\""
                                ),
                                mutableMapOf(
                                        "s1" to "<http://example.org/s3>",
                                        "str1" to "\"english\"@en",
                                        "s2" to "<http://example.org/s1>",
                                        "str2" to "\"123\""
                                ),
                                mutableMapOf(
                                        "s1" to "<http://example.org/s4>",
                                        "str1" to "\"français\"@fr",
                                        "s2" to "<http://example.org/s1>",
                                        "str2" to "\"123\""
                                ),
                                mutableMapOf(
                                        "s1" to "<http://example.org/s5>",
                                        "str1" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                        "s2" to "<http://example.org/s1>",
                                        "str2" to "\"123\""
                                ),
                                mutableMapOf(
                                        "s1" to "<http://example.org/s6>",
                                        "str1" to "\"def\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                        "s2" to "<http://example.org/s1>",
                                        "str2" to "\"123\""
                                ),
                                mutableMapOf(
                                        "s1" to "<http://example.org/s7>",
                                        "str1" to "\"7\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "s2" to "<http://example.org/s1>",
                                        "str2" to "\"123\""
                                ),
                                mutableMapOf(
                                        "s1" to "<http://example.org/s1>",
                                        "str1" to "\"123\"",
                                        "s2" to "<http://example.org/s2>",
                                        "str2" to "\"日本語\"@ja"
                                ),
                                mutableMapOf(
                                        "s1" to "<http://example.org/s2>",
                                        "str1" to "\"日本語\"@ja",
                                        "s2" to "<http://example.org/s2>",
                                        "str2" to "\"日本語\"@ja"
                                ),
                                mutableMapOf(
                                        "s1" to "<http://example.org/s3>",
                                        "str1" to "\"english\"@en",
                                        "s2" to "<http://example.org/s2>",
                                        "str2" to "\"日本語\"@ja"
                                ),
                                mutableMapOf(
                                        "s1" to "<http://example.org/s4>",
                                        "str1" to "\"français\"@fr",
                                        "s2" to "<http://example.org/s2>",
                                        "str2" to "\"日本語\"@ja"
                                ),
                                mutableMapOf(
                                        "s1" to "<http://example.org/s5>",
                                        "str1" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                        "s2" to "<http://example.org/s2>",
                                        "str2" to "\"日本語\"@ja"
                                ),
                                mutableMapOf(
                                        "s1" to "<http://example.org/s6>",
                                        "str1" to "\"def\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                        "s2" to "<http://example.org/s2>",
                                        "str2" to "\"日本語\"@ja"
                                ),
                                mutableMapOf(
                                        "s1" to "<http://example.org/s7>",
                                        "str1" to "\"7\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "s2" to "<http://example.org/s2>",
                                        "str2" to "\"日本語\"@ja"
                                ),
                                mutableMapOf(
                                        "s1" to "<http://example.org/s1>",
                                        "str1" to "\"123\"",
                                        "s2" to "<http://example.org/s3>",
                                        "str2" to "\"english\"@en"
                                ),
                                mutableMapOf(
                                        "s1" to "<http://example.org/s2>",
                                        "str1" to "\"日本語\"@ja",
                                        "s2" to "<http://example.org/s3>",
                                        "str2" to "\"english\"@en"
                                ),
                                mutableMapOf(
                                        "s1" to "<http://example.org/s3>",
                                        "str1" to "\"english\"@en",
                                        "s2" to "<http://example.org/s3>",
                                        "str2" to "\"english\"@en"
                                ),
                                mutableMapOf(
                                        "s1" to "<http://example.org/s4>",
                                        "str1" to "\"français\"@fr",
                                        "s2" to "<http://example.org/s3>",
                                        "str2" to "\"english\"@en"
                                ),
                                mutableMapOf(
                                        "s1" to "<http://example.org/s5>",
                                        "str1" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                        "s2" to "<http://example.org/s3>",
                                        "str2" to "\"english\"@en"
                                ),
                                mutableMapOf(
                                        "s1" to "<http://example.org/s6>",
                                        "str1" to "\"def\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                        "s2" to "<http://example.org/s3>",
                                        "str2" to "\"english\"@en"
                                ),
                                mutableMapOf(
                                        "s1" to "<http://example.org/s7>",
                                        "str1" to "\"7\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "s2" to "<http://example.org/s3>",
                                        "str2" to "\"english\"@en"
                                ),
                                mutableMapOf(
                                        "s1" to "<http://example.org/s1>",
                                        "str1" to "\"123\"",
                                        "s2" to "<http://example.org/s4>",
                                        "str2" to "\"français\"@fr"
                                ),
                                mutableMapOf(
                                        "s1" to "<http://example.org/s2>",
                                        "str1" to "\"日本語\"@ja",
                                        "s2" to "<http://example.org/s4>",
                                        "str2" to "\"français\"@fr"
                                ),
                                mutableMapOf(
                                        "s1" to "<http://example.org/s3>",
                                        "str1" to "\"english\"@en",
                                        "s2" to "<http://example.org/s4>",
                                        "str2" to "\"français\"@fr"
                                ),
                                mutableMapOf(
                                        "s1" to "<http://example.org/s4>",
                                        "str1" to "\"français\"@fr",
                                        "s2" to "<http://example.org/s4>",
                                        "str2" to "\"français\"@fr"
                                ),
                                mutableMapOf(
                                        "s1" to "<http://example.org/s5>",
                                        "str1" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                        "s2" to "<http://example.org/s4>",
                                        "str2" to "\"français\"@fr"
                                ),
                                mutableMapOf(
                                        "s1" to "<http://example.org/s6>",
                                        "str1" to "\"def\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                        "s2" to "<http://example.org/s4>",
                                        "str2" to "\"français\"@fr"
                                ),
                                mutableMapOf(
                                        "s1" to "<http://example.org/s7>",
                                        "str1" to "\"7\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "s2" to "<http://example.org/s4>",
                                        "str2" to "\"français\"@fr"
                                ),
                                mutableMapOf(
                                        "s1" to "<http://example.org/s1>",
                                        "str1" to "\"123\"",
                                        "s2" to "<http://example.org/s5>",
                                        "str2" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                        "s1" to "<http://example.org/s2>",
                                        "str1" to "\"日本語\"@ja",
                                        "s2" to "<http://example.org/s5>",
                                        "str2" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                        "s1" to "<http://example.org/s3>",
                                        "str1" to "\"english\"@en",
                                        "s2" to "<http://example.org/s5>",
                                        "str2" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                        "s1" to "<http://example.org/s4>",
                                        "str1" to "\"français\"@fr",
                                        "s2" to "<http://example.org/s5>",
                                        "str2" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                        "s1" to "<http://example.org/s5>",
                                        "str1" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                        "s2" to "<http://example.org/s5>",
                                        "str2" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                        "s1" to "<http://example.org/s6>",
                                        "str1" to "\"def\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                        "s2" to "<http://example.org/s5>",
                                        "str2" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                        "s1" to "<http://example.org/s7>",
                                        "str1" to "\"7\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "s2" to "<http://example.org/s5>",
                                        "str2" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                        "s1" to "<http://example.org/s1>",
                                        "str1" to "\"123\"",
                                        "s2" to "<http://example.org/s6>",
                                        "str2" to "\"def\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                        "s1" to "<http://example.org/s2>",
                                        "str1" to "\"日本語\"@ja",
                                        "s2" to "<http://example.org/s6>",
                                        "str2" to "\"def\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                        "s1" to "<http://example.org/s3>",
                                        "str1" to "\"english\"@en",
                                        "s2" to "<http://example.org/s6>",
                                        "str2" to "\"def\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                        "s1" to "<http://example.org/s4>",
                                        "str1" to "\"français\"@fr",
                                        "s2" to "<http://example.org/s6>",
                                        "str2" to "\"def\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                        "s1" to "<http://example.org/s5>",
                                        "str1" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                        "s2" to "<http://example.org/s6>",
                                        "str2" to "\"def\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                        "s1" to "<http://example.org/s6>",
                                        "str1" to "\"def\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                        "s2" to "<http://example.org/s6>",
                                        "str2" to "\"def\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                        "s1" to "<http://example.org/s7>",
                                        "str1" to "\"7\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "s2" to "<http://example.org/s6>",
                                        "str2" to "\"def\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                        "s1" to "<http://example.org/s1>",
                                        "str1" to "\"123\"",
                                        "s2" to "<http://example.org/s7>",
                                        "str2" to "\"7\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                        "s1" to "<http://example.org/s2>",
                                        "str1" to "\"日本語\"@ja",
                                        "s2" to "<http://example.org/s7>",
                                        "str2" to "\"7\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                        "s1" to "<http://example.org/s3>",
                                        "str1" to "\"english\"@en",
                                        "s2" to "<http://example.org/s7>",
                                        "str2" to "\"7\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                        "s1" to "<http://example.org/s4>",
                                        "str1" to "\"français\"@fr",
                                        "s2" to "<http://example.org/s7>",
                                        "str2" to "\"7\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                        "s1" to "<http://example.org/s5>",
                                        "str1" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                        "s2" to "<http://example.org/s7>",
                                        "str2" to "\"7\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                        "s1" to "<http://example.org/s6>",
                                        "str1" to "\"def\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                        "s2" to "<http://example.org/s7>",
                                        "str2" to "\"7\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                        "s1" to "<http://example.org/s7>",
                                        "str1" to "\"7\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "s2" to "<http://example.org/s7>",
                                        "str2" to "\"7\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/concat02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "s",
                                        "x"
                                ), listOf(
                                        mutableMapOf(
                                                "s" to "<http://example/x1>",
                                                "x" to "\"a\""
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example/x2>",
                                                "x" to "_:b"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example/x3>",
                                                "x" to "<http://example/a>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example/x4>",
                                                "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example/x5>",
                                                "x" to "\"1.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example/x6>",
                                                "x" to "\"1\""
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example/x7>",
                                                "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example/x8>",
                                                "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                        )
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "y"
                                ), listOf(
                                        mutableMapOf(
                                                "s" to "<http://example/x1>",
                                                "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example/x2>",
                                                "y" to "\"1\""
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example/x3>",
                                                "y" to "\"1\""
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example/x4>",
                                                "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example/x5>",
                                                "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example/x6>",
                                                "y" to "\"2\""
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example/x7>",
                                                "y" to "\"2\""
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example/x8>",
                                                "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        )
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "s",
                                "x",
                                "y"
                        ), listOf(
                                mutableMapOf(
                                        "s" to "<http://example/x1>",
                                        "x" to "\"a\"",
                                        "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                        "s" to "<http://example/x2>",
                                        "x" to "_:b",
                                        "y" to "\"1\""
                                ),
                                mutableMapOf(
                                        "s" to "<http://example/x3>",
                                        "x" to "<http://example/a>",
                                        "y" to "\"1\""
                                ),
                                mutableMapOf(
                                        "s" to "<http://example/x4>",
                                        "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                        "s" to "<http://example/x5>",
                                        "x" to "\"1.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>",
                                        "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                        "s" to "<http://example/x6>",
                                        "x" to "\"1\"",
                                        "y" to "\"2\""
                                ),
                                mutableMapOf(
                                        "s" to "<http://example/x7>",
                                        "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                        "y" to "\"2\""
                                ),
                                mutableMapOf(
                                        "s" to "<http://example/x8>",
                                        "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                        "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/plus-2.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "a",
                                        "s1"
                                ), listOf(
                                        mutableMapOf(
                                                "a" to "<http://example.org/s1>",
                                                "s1" to "\"foo\""
                                        ),
                                        mutableMapOf(
                                                "a" to "<http://example.org/s2>",
                                                "s1" to "\"bar\"@en"
                                        ),
                                        mutableMapOf(
                                                "a" to "<http://example.org/s3>",
                                                "s1" to "\"BAZ\""
                                        ),
                                        mutableMapOf(
                                                "a" to "<http://example.org/s4>",
                                                "s1" to "\"食べ物\""
                                        ),
                                        mutableMapOf(
                                                "a" to "<http://example.org/s5>",
                                                "s1" to "\"100%\""
                                        ),
                                        mutableMapOf(
                                                "a" to "<http://example.org/s6>",
                                                "s1" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                        ),
                                        mutableMapOf(
                                                "a" to "<http://example.org/s7>",
                                                "s1" to "\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                        )
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "b",
                                        "s2"
                                ), listOf(
                                        mutableMapOf(
                                                "b" to "<http://example.org/s1>",
                                                "s2" to "\"foo\""
                                        ),
                                        mutableMapOf(
                                                "b" to "<http://example.org/s2>",
                                                "s2" to "\"bar\"@en"
                                        ),
                                        mutableMapOf(
                                                "b" to "<http://example.org/s3>",
                                                "s2" to "\"BAZ\""
                                        ),
                                        mutableMapOf(
                                                "b" to "<http://example.org/s4>",
                                                "s2" to "\"食べ物\""
                                        ),
                                        mutableMapOf(
                                                "b" to "<http://example.org/s5>",
                                                "s2" to "\"100%\""
                                        ),
                                        mutableMapOf(
                                                "b" to "<http://example.org/s6>",
                                                "s2" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                        ),
                                        mutableMapOf(
                                                "b" to "<http://example.org/s7>",
                                                "s2" to "\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                        )
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "a",
                                "s1",
                                "b",
                                "s2"
                        ), listOf(
                                mutableMapOf(
                                        "a" to "<http://example.org/s1>",
                                        "s1" to "\"foo\"",
                                        "b" to "<http://example.org/s1>",
                                        "s2" to "\"foo\""
                                ),
                                mutableMapOf(
                                        "a" to "<http://example.org/s2>",
                                        "s1" to "\"bar\"@en",
                                        "b" to "<http://example.org/s1>",
                                        "s2" to "\"foo\""
                                ),
                                mutableMapOf(
                                        "a" to "<http://example.org/s3>",
                                        "s1" to "\"BAZ\"",
                                        "b" to "<http://example.org/s1>",
                                        "s2" to "\"foo\""
                                ),
                                mutableMapOf(
                                        "a" to "<http://example.org/s4>",
                                        "s1" to "\"食べ物\"",
                                        "b" to "<http://example.org/s1>",
                                        "s2" to "\"foo\""
                                ),
                                mutableMapOf(
                                        "a" to "<http://example.org/s5>",
                                        "s1" to "\"100%\"",
                                        "b" to "<http://example.org/s1>",
                                        "s2" to "\"foo\""
                                ),
                                mutableMapOf(
                                        "a" to "<http://example.org/s6>",
                                        "s1" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                        "b" to "<http://example.org/s1>",
                                        "s2" to "\"foo\""
                                ),
                                mutableMapOf(
                                        "a" to "<http://example.org/s7>",
                                        "s1" to "\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                        "b" to "<http://example.org/s1>",
                                        "s2" to "\"foo\""
                                ),
                                mutableMapOf(
                                        "a" to "<http://example.org/s1>",
                                        "s1" to "\"foo\"",
                                        "b" to "<http://example.org/s2>",
                                        "s2" to "\"bar\"@en"
                                ),
                                mutableMapOf(
                                        "a" to "<http://example.org/s2>",
                                        "s1" to "\"bar\"@en",
                                        "b" to "<http://example.org/s2>",
                                        "s2" to "\"bar\"@en"
                                ),
                                mutableMapOf(
                                        "a" to "<http://example.org/s3>",
                                        "s1" to "\"BAZ\"",
                                        "b" to "<http://example.org/s2>",
                                        "s2" to "\"bar\"@en"
                                ),
                                mutableMapOf(
                                        "a" to "<http://example.org/s4>",
                                        "s1" to "\"食べ物\"",
                                        "b" to "<http://example.org/s2>",
                                        "s2" to "\"bar\"@en"
                                ),
                                mutableMapOf(
                                        "a" to "<http://example.org/s5>",
                                        "s1" to "\"100%\"",
                                        "b" to "<http://example.org/s2>",
                                        "s2" to "\"bar\"@en"
                                ),
                                mutableMapOf(
                                        "a" to "<http://example.org/s6>",
                                        "s1" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                        "b" to "<http://example.org/s2>",
                                        "s2" to "\"bar\"@en"
                                ),
                                mutableMapOf(
                                        "a" to "<http://example.org/s7>",
                                        "s1" to "\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                        "b" to "<http://example.org/s2>",
                                        "s2" to "\"bar\"@en"
                                ),
                                mutableMapOf(
                                        "a" to "<http://example.org/s1>",
                                        "s1" to "\"foo\"",
                                        "b" to "<http://example.org/s3>",
                                        "s2" to "\"BAZ\""
                                ),
                                mutableMapOf(
                                        "a" to "<http://example.org/s2>",
                                        "s1" to "\"bar\"@en",
                                        "b" to "<http://example.org/s3>",
                                        "s2" to "\"BAZ\""
                                ),
                                mutableMapOf(
                                        "a" to "<http://example.org/s3>",
                                        "s1" to "\"BAZ\"",
                                        "b" to "<http://example.org/s3>",
                                        "s2" to "\"BAZ\""
                                ),
                                mutableMapOf(
                                        "a" to "<http://example.org/s4>",
                                        "s1" to "\"食べ物\"",
                                        "b" to "<http://example.org/s3>",
                                        "s2" to "\"BAZ\""
                                ),
                                mutableMapOf(
                                        "a" to "<http://example.org/s5>",
                                        "s1" to "\"100%\"",
                                        "b" to "<http://example.org/s3>",
                                        "s2" to "\"BAZ\""
                                ),
                                mutableMapOf(
                                        "a" to "<http://example.org/s6>",
                                        "s1" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                        "b" to "<http://example.org/s3>",
                                        "s2" to "\"BAZ\""
                                ),
                                mutableMapOf(
                                        "a" to "<http://example.org/s7>",
                                        "s1" to "\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                        "b" to "<http://example.org/s3>",
                                        "s2" to "\"BAZ\""
                                ),
                                mutableMapOf(
                                        "a" to "<http://example.org/s1>",
                                        "s1" to "\"foo\"",
                                        "b" to "<http://example.org/s4>",
                                        "s2" to "\"食べ物\""
                                ),
                                mutableMapOf(
                                        "a" to "<http://example.org/s2>",
                                        "s1" to "\"bar\"@en",
                                        "b" to "<http://example.org/s4>",
                                        "s2" to "\"食べ物\""
                                ),
                                mutableMapOf(
                                        "a" to "<http://example.org/s3>",
                                        "s1" to "\"BAZ\"",
                                        "b" to "<http://example.org/s4>",
                                        "s2" to "\"食べ物\""
                                ),
                                mutableMapOf(
                                        "a" to "<http://example.org/s4>",
                                        "s1" to "\"食べ物\"",
                                        "b" to "<http://example.org/s4>",
                                        "s2" to "\"食べ物\""
                                ),
                                mutableMapOf(
                                        "a" to "<http://example.org/s5>",
                                        "s1" to "\"100%\"",
                                        "b" to "<http://example.org/s4>",
                                        "s2" to "\"食べ物\""
                                ),
                                mutableMapOf(
                                        "a" to "<http://example.org/s6>",
                                        "s1" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                        "b" to "<http://example.org/s4>",
                                        "s2" to "\"食べ物\""
                                ),
                                mutableMapOf(
                                        "a" to "<http://example.org/s7>",
                                        "s1" to "\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                        "b" to "<http://example.org/s4>",
                                        "s2" to "\"食べ物\""
                                ),
                                mutableMapOf(
                                        "a" to "<http://example.org/s1>",
                                        "s1" to "\"foo\"",
                                        "b" to "<http://example.org/s5>",
                                        "s2" to "\"100%\""
                                ),
                                mutableMapOf(
                                        "a" to "<http://example.org/s2>",
                                        "s1" to "\"bar\"@en",
                                        "b" to "<http://example.org/s5>",
                                        "s2" to "\"100%\""
                                ),
                                mutableMapOf(
                                        "a" to "<http://example.org/s3>",
                                        "s1" to "\"BAZ\"",
                                        "b" to "<http://example.org/s5>",
                                        "s2" to "\"100%\""
                                ),
                                mutableMapOf(
                                        "a" to "<http://example.org/s4>",
                                        "s1" to "\"食べ物\"",
                                        "b" to "<http://example.org/s5>",
                                        "s2" to "\"100%\""
                                ),
                                mutableMapOf(
                                        "a" to "<http://example.org/s5>",
                                        "s1" to "\"100%\"",
                                        "b" to "<http://example.org/s5>",
                                        "s2" to "\"100%\""
                                ),
                                mutableMapOf(
                                        "a" to "<http://example.org/s6>",
                                        "s1" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                        "b" to "<http://example.org/s5>",
                                        "s2" to "\"100%\""
                                ),
                                mutableMapOf(
                                        "a" to "<http://example.org/s7>",
                                        "s1" to "\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                        "b" to "<http://example.org/s5>",
                                        "s2" to "\"100%\""
                                ),
                                mutableMapOf(
                                        "a" to "<http://example.org/s1>",
                                        "s1" to "\"foo\"",
                                        "b" to "<http://example.org/s6>",
                                        "s2" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                        "a" to "<http://example.org/s2>",
                                        "s1" to "\"bar\"@en",
                                        "b" to "<http://example.org/s6>",
                                        "s2" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                        "a" to "<http://example.org/s3>",
                                        "s1" to "\"BAZ\"",
                                        "b" to "<http://example.org/s6>",
                                        "s2" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                        "a" to "<http://example.org/s4>",
                                        "s1" to "\"食べ物\"",
                                        "b" to "<http://example.org/s6>",
                                        "s2" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                        "a" to "<http://example.org/s5>",
                                        "s1" to "\"100%\"",
                                        "b" to "<http://example.org/s6>",
                                        "s2" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                        "a" to "<http://example.org/s6>",
                                        "s1" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                        "b" to "<http://example.org/s6>",
                                        "s2" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                        "a" to "<http://example.org/s7>",
                                        "s1" to "\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                        "b" to "<http://example.org/s6>",
                                        "s2" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                        "a" to "<http://example.org/s1>",
                                        "s1" to "\"foo\"",
                                        "b" to "<http://example.org/s7>",
                                        "s2" to "\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                        "a" to "<http://example.org/s2>",
                                        "s1" to "\"bar\"@en",
                                        "b" to "<http://example.org/s7>",
                                        "s2" to "\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                        "a" to "<http://example.org/s3>",
                                        "s1" to "\"BAZ\"",
                                        "b" to "<http://example.org/s7>",
                                        "s2" to "\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                        "a" to "<http://example.org/s4>",
                                        "s1" to "\"食べ物\"",
                                        "b" to "<http://example.org/s7>",
                                        "s2" to "\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                        "a" to "<http://example.org/s5>",
                                        "s1" to "\"100%\"",
                                        "b" to "<http://example.org/s7>",
                                        "s2" to "\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                        "a" to "<http://example.org/s6>",
                                        "s1" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                        "b" to "<http://example.org/s7>",
                                        "s2" to "\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                        "a" to "<http://example.org/s7>",
                                        "s1" to "\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                        "b" to "<http://example.org/s7>",
                                        "s2" to "\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/bnode01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "s",
                                        "v"
                                ), listOf(
                                        mutableMapOf(
                                                "s" to "<http://example/s1>",
                                                "v" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example/s2>",
                                                "v" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        )
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "w"
                                ), listOf(
                                        mutableMapOf(
                                                "s" to "<http://example/s1>",
                                                "w" to "\"9\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        )
                                )
                                ),
                                true),
                        POPValues(dictionary, listOf(
                                "s",
                                "v",
                                "w"
                        ), listOf(
                                mutableMapOf(
                                        "s" to "<http://example/s1>",
                                        "v" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "w" to "\"9\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                        "s" to "<http://example/s2>",
                                        "v" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "w" to null
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/grouping/group03.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "s",
                                        "v"
                                ), listOf(
                                        mutableMapOf(
                                                "s" to "<http://example/s1>",
                                                "v" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example/s2>",
                                                "v" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example/s3>",
                                                "v" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        )
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "w"
                                ), listOf(
                                        mutableMapOf(
                                                "s" to "<http://example/s1>",
                                                "w" to "\"9\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        )
                                )
                                ),
                                true),
                        POPValues(dictionary, listOf(
                                "s",
                                "v",
                                "w"
                        ), listOf(
                                mutableMapOf(
                                        "s" to "<http://example/s1>",
                                        "v" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "w" to "\"9\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                        "s" to "<http://example/s2>",
                                        "v" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "w" to null
                                ),
                                mutableMapOf(
                                        "s" to "<http://example/s3>",
                                        "v" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "w" to null
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/grouping/group05.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "s",
                                        "p",
                                        "o"
                                ), listOf(
                                        mutableMapOf(
                                                "s" to "<http://example.org/s1>",
                                                "p" to "<http://example.org/p1>",
                                                "o" to "<http://example.org/s2>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/s2>",
                                                "p" to "<http://example.org/p2>",
                                                "o" to "\"foo\""
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/s3>",
                                                "p" to "<http://example.org/p2>",
                                                "o" to "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/s4>",
                                                "p" to "<http://example.org/p4>",
                                                "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/s5>",
                                                "p" to "<http://example.org/p5>",
                                                "o" to "\"5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/s6>",
                                                "p" to "<http://example.org/p6>",
                                                "o" to "_:o6"
                                        )
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "o",
                                        "p2",
                                        "o2"
                                ), listOf(
                                        mutableMapOf(
                                                "o" to "<http://example.org/s1>",
                                                "p2" to "<http://example.org/p1>",
                                                "o2" to "<http://example.org/s2>"
                                        ),
                                        mutableMapOf(
                                                "o" to "<http://example.org/s2>",
                                                "p2" to "<http://example.org/p2>",
                                                "o2" to "\"foo\""
                                        ),
                                        mutableMapOf(
                                                "o" to "<http://example.org/s3>",
                                                "p2" to "<http://example.org/p2>",
                                                "o2" to "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                        ),
                                        mutableMapOf(
                                                "o" to "<http://example.org/s4>",
                                                "p2" to "<http://example.org/p4>",
                                                "o2" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        ),
                                        mutableMapOf(
                                                "o" to "<http://example.org/s5>",
                                                "p2" to "<http://example.org/p5>",
                                                "o2" to "\"5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                                        ),
                                        mutableMapOf(
                                                "o" to "<http://example.org/s6>",
                                                "p2" to "<http://example.org/p6>",
                                                "o2" to "_:o6"
                                        )
                                )
                                ),
                                true),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o",
                                "p2",
                                "o2"
                        ), listOf(
                                mutableMapOf(
                                        "s" to "<http://example.org/s1>",
                                        "p" to "<http://example.org/p1>",
                                        "o" to "<http://example.org/s2>",
                                        "p2" to "<http://example.org/p2>",
                                        "o2" to "\"foo\""
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/s2>",
                                        "p" to "<http://example.org/p2>",
                                        "o" to "\"foo\"",
                                        "p2" to null,
                                        "o2" to null
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/s3>",
                                        "p" to "<http://example.org/p2>",
                                        "o" to "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                        "p2" to null,
                                        "o2" to null
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/s4>",
                                        "p" to "<http://example.org/p4>",
                                        "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "p2" to null,
                                        "o2" to null
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/s5>",
                                        "p" to "<http://example.org/p5>",
                                        "o" to "\"5\"^^<http://www.w3.org/2001/XMLSchema#decimal>",
                                        "p2" to null,
                                        "o2" to null
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/s6>",
                                        "p" to "<http://example.org/p6>",
                                        "o" to "_:o6",
                                        "p2" to null,
                                        "o2" to null
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/json-res/jsonres02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "x",
                                        "y"
                                ), listOf(
                                        mutableMapOf(
                                                "x" to "<http://www.example.org/instance#a>",
                                                "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        )
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "x",
                                        "z"
                                ), listOf(
                                        mutableMapOf(
                                                "x" to "<http://www.example.org/instance#a>",
                                                "z" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        ),
                                        mutableMapOf(
                                                "x" to "<http://www.example.org/instance#a>",
                                                "z" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        )
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "x",
                                "y",
                                "z"
                        ), listOf(
                                mutableMapOf(
                                        "x" to "<http://www.example.org/instance#a>",
                                        "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "z" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                        "x" to "<http://www.example.org/instance#a>",
                                        "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "z" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "x",
                                        "y"
                                ), listOf(
                                        mutableMapOf(
                                                "x" to "<http://www.example.org/instance#a>",
                                                "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        )
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "x",
                                        "z"
                                ), listOf(
                                        mutableMapOf(
                                                "x" to "<http://www.example.org/instance#a>",
                                                "z" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        ),
                                        mutableMapOf(
                                                "x" to "<http://www.example.org/instance#a>",
                                                "z" to "\"foobar\""
                                        )
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "x",
                                "y",
                                "z"
                        ), listOf(
                                mutableMapOf(
                                        "x" to "<http://www.example.org/instance#a>",
                                        "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "z" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                        "x" to "<http://www.example.org/instance#a>",
                                        "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "z" to "\"foobar\""
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "x",
                                        "y"
                                ), listOf(
                                        mutableMapOf(
                                                "x" to "<http://www.example.org/instance#a>",
                                                "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        )
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "x",
                                        "z"
                                ), listOf(
                                        mutableMapOf(
                                                "x" to "<http://www.example.org/instance#a>",
                                                "z" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        )
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "x",
                                "y",
                                "z"
                        ), listOf(
                                mutableMapOf(
                                        "x" to "<http://www.example.org/instance#a>",
                                        "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "z" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp03.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "x",
                                        "y"
                                ), listOf(
                                        mutableMapOf(
                                                "x" to "<http://www.example.org/instance#a>",
                                                "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        ),
                                        mutableMapOf(
                                                "x" to "<http://www.example.org/instance#b>",
                                                "y" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        )
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "x",
                                        "l"
                                ), listOf(
                                        mutableMapOf(
                                                "x" to "<http://www.example.org/instance#a>",
                                                "l" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        )
                                )
                                ),
                                true),
                        POPValues(dictionary, listOf(
                                "x",
                                "y",
                                "l"
                        ), listOf(
                                mutableMapOf(
                                        "x" to "<http://www.example.org/instance#a>",
                                        "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "l" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                        "x" to "<http://www.example.org/instance#b>",
                                        "y" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "l" to null
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp07.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "#_34876",
                                        "L"
                                ), listOf(
                                        mutableMapOf(
                                                "#_34876" to "_:_34853",
                                                "L" to "\"Ice Cream\""
                                        ),
                                        mutableMapOf(
                                                "#_34876" to "_:_34854",
                                                "L" to "\"Pizza\""
                                        ),
                                        mutableMapOf(
                                                "#_34876" to "_:_34855",
                                                "L" to "\"Wine\""
                                        ),
                                        mutableMapOf(
                                                "#_34876" to "_:_34856",
                                                "L" to "\"Ice Cream\""
                                        ),
                                        mutableMapOf(
                                                "#_34876" to "_:_34857",
                                                "L" to "\"Pasta\""
                                        ),
                                        mutableMapOf(
                                                "#_34876" to "_:_34858",
                                                "L" to "\"Soft Drink\""
                                        ),
                                        mutableMapOf(
                                                "#_34876" to "_:_34859",
                                                "L" to "\"Sandwich\""
                                        ),
                                        mutableMapOf(
                                                "#_34876" to "_:_34860",
                                                "L" to "\"Soft Drink\""
                                        ),
                                        mutableMapOf(
                                                "#_34876" to "_:_34861",
                                                "L" to "\"Bagel\""
                                        ),
                                        mutableMapOf(
                                                "#_34876" to "_:_34862",
                                                "L" to "\"Soft Drink\""
                                        )
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "O",
                                        "#_34876"
                                ), listOf(
                                        mutableMapOf(
                                                "O" to "<http://www.example.orgorder1>",
                                                "#_34876" to "_:_34853"
                                        ),
                                        mutableMapOf(
                                                "O" to "<http://www.example.orgorder1>",
                                                "#_34876" to "_:_34854"
                                        ),
                                        mutableMapOf(
                                                "O" to "<http://www.example.orgorder1>",
                                                "#_34876" to "_:_34855"
                                        ),
                                        mutableMapOf(
                                                "O" to "<http://www.example.orgorder2>",
                                                "#_34876" to "_:_34856"
                                        ),
                                        mutableMapOf(
                                                "O" to "<http://www.example.orgorder2>",
                                                "#_34876" to "_:_34857"
                                        ),
                                        mutableMapOf(
                                                "O" to "<http://www.example.orgorder2>",
                                                "#_34876" to "_:_34858"
                                        ),
                                        mutableMapOf(
                                                "O" to "<http://www.example.orgorder3>",
                                                "#_34876" to "_:_34859"
                                        ),
                                        mutableMapOf(
                                                "O" to "<http://www.example.orgorder3>",
                                                "#_34876" to "_:_34860"
                                        ),
                                        mutableMapOf(
                                                "O" to "<http://www.example.orgorder4>",
                                                "#_34876" to "_:_34861"
                                        ),
                                        mutableMapOf(
                                                "O" to "<http://www.example.orgorder4>",
                                                "#_34876" to "_:_34862"
                                        )
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "#_34876",
                                "L",
                                "O"
                        ), listOf(
                                mutableMapOf(
                                        "#_34876" to "_:_34853",
                                        "L" to "\"Ice Cream\"",
                                        "O" to "<http://www.example.orgorder1>"
                                ),
                                mutableMapOf(
                                        "#_34876" to "_:_34854",
                                        "L" to "\"Pizza\"",
                                        "O" to "<http://www.example.orgorder1>"
                                ),
                                mutableMapOf(
                                        "#_34876" to "_:_34855",
                                        "L" to "\"Wine\"",
                                        "O" to "<http://www.example.orgorder1>"
                                ),
                                mutableMapOf(
                                        "#_34876" to "_:_34856",
                                        "L" to "\"Ice Cream\"",
                                        "O" to "<http://www.example.orgorder2>"
                                ),
                                mutableMapOf(
                                        "#_34876" to "_:_34857",
                                        "L" to "\"Pasta\"",
                                        "O" to "<http://www.example.orgorder2>"
                                ),
                                mutableMapOf(
                                        "#_34876" to "_:_34858",
                                        "L" to "\"Soft Drink\"",
                                        "O" to "<http://www.example.orgorder2>"
                                ),
                                mutableMapOf(
                                        "#_34876" to "_:_34859",
                                        "L" to "\"Sandwich\"",
                                        "O" to "<http://www.example.orgorder3>"
                                ),
                                mutableMapOf(
                                        "#_34876" to "_:_34860",
                                        "L" to "\"Soft Drink\"",
                                        "O" to "<http://www.example.orgorder3>"
                                ),
                                mutableMapOf(
                                        "#_34876" to "_:_34861",
                                        "L" to "\"Bagel\"",
                                        "O" to "<http://www.example.orgorder4>"
                                ),
                                mutableMapOf(
                                        "#_34876" to "_:_34862",
                                        "L" to "\"Soft Drink\"",
                                        "O" to "<http://www.example.orgorder4>"
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/subquery/sq11.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "#_34876",
                                        "L",
                                        "O"
                                ), listOf(
                                        mutableMapOf(
                                                "#_34876" to "_:_34853",
                                                "L" to "\"Ice Cream\"",
                                                "O" to "<http://www.example.orgorder1>"
                                        ),
                                        mutableMapOf(
                                                "#_34876" to "_:_34854",
                                                "L" to "\"Pizza\"",
                                                "O" to "<http://www.example.orgorder1>"
                                        ),
                                        mutableMapOf(
                                                "#_34876" to "_:_34855",
                                                "L" to "\"Wine\"",
                                                "O" to "<http://www.example.orgorder1>"
                                        ),
                                        mutableMapOf(
                                                "#_34876" to "_:_34856",
                                                "L" to "\"Ice Cream\"",
                                                "O" to "<http://www.example.orgorder2>"
                                        ),
                                        mutableMapOf(
                                                "#_34876" to "_:_34857",
                                                "L" to "\"Pasta\"",
                                                "O" to "<http://www.example.orgorder2>"
                                        ),
                                        mutableMapOf(
                                                "#_34876" to "_:_34858",
                                                "L" to "\"Soft Drink\"",
                                                "O" to "<http://www.example.orgorder2>"
                                        ),
                                        mutableMapOf(
                                                "#_34876" to "_:_34859",
                                                "L" to "\"Sandwich\"",
                                                "O" to "<http://www.example.orgorder3>"
                                        ),
                                        mutableMapOf(
                                                "#_34876" to "_:_34860",
                                                "L" to "\"Soft Drink\"",
                                                "O" to "<http://www.example.orgorder3>"
                                        ),
                                        mutableMapOf(
                                                "#_34876" to "_:_34861",
                                                "L" to "\"Bagel\"",
                                                "O" to "<http://www.example.orgorder4>"
                                        ),
                                        mutableMapOf(
                                                "#_34876" to "_:_34862",
                                                "L" to "\"Soft Drink\"",
                                                "O" to "<http://www.example.orgorder4>"
                                        )
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "O"
                                ), listOf(
                                        mutableMapOf(
                                                "O" to "<http://www.example.orgorder1>"
                                        ),
                                        mutableMapOf(
                                                "O" to "<http://www.example.orgorder2>"
                                        )
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "#_34876",
                                "L",
                                "O"
                        ), listOf(
                                mutableMapOf(
                                        "#_34876" to "_:_34853",
                                        "L" to "\"Ice Cream\"",
                                        "O" to "<http://www.example.orgorder1>"
                                ),
                                mutableMapOf(
                                        "#_34876" to "_:_34854",
                                        "L" to "\"Pizza\"",
                                        "O" to "<http://www.example.orgorder1>"
                                ),
                                mutableMapOf(
                                        "#_34876" to "_:_34855",
                                        "L" to "\"Wine\"",
                                        "O" to "<http://www.example.orgorder1>"
                                ),
                                mutableMapOf(
                                        "#_34876" to "_:_34856",
                                        "L" to "\"Ice Cream\"",
                                        "O" to "<http://www.example.orgorder2>"
                                ),
                                mutableMapOf(
                                        "#_34876" to "_:_34857",
                                        "L" to "\"Pasta\"",
                                        "O" to "<http://www.example.orgorder2>"
                                ),
                                mutableMapOf(
                                        "#_34876" to "_:_34858",
                                        "L" to "\"Soft Drink\"",
                                        "O" to "<http://www.example.orgorder2>"
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/subquery/sq11.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "P",
                                        "F"
                                ), listOf(
                                        mutableMapOf(
                                                "P" to "<http://p1>",
                                                "F" to "\"John\""
                                        )
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "P",
                                        "L"
                                ), listOf(
                                        mutableMapOf(
                                                "P" to "<http://p1>",
                                                "L" to "\"Doe\""
                                        )
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "P",
                                "F",
                                "L"
                        ), listOf(
                                mutableMapOf(
                                        "P" to "<http://p1>",
                                        "F" to "\"John\"",
                                        "L" to "\"Doe\""
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/subquery/sq12.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "#_35225",
                                        "L"
                                ), listOf(
                                        mutableMapOf(
                                                "#_35225" to "_:_35202",
                                                "L" to "\"Ice Cream\""
                                        ),
                                        mutableMapOf(
                                                "#_35225" to "_:_35203",
                                                "L" to "\"Pizza\""
                                        ),
                                        mutableMapOf(
                                                "#_35225" to "_:_35204",
                                                "L" to "\"Wine\""
                                        ),
                                        mutableMapOf(
                                                "#_35225" to "_:_35205",
                                                "L" to "\"Ice Cream\""
                                        ),
                                        mutableMapOf(
                                                "#_35225" to "_:_35206",
                                                "L" to "\"Pasta\""
                                        ),
                                        mutableMapOf(
                                                "#_35225" to "_:_35207",
                                                "L" to "\"Soft Drink\""
                                        ),
                                        mutableMapOf(
                                                "#_35225" to "_:_35208",
                                                "L" to "\"Sandwich\""
                                        ),
                                        mutableMapOf(
                                                "#_35225" to "_:_35209",
                                                "L" to "\"Soft Drink\""
                                        ),
                                        mutableMapOf(
                                                "#_35225" to "_:_35210",
                                                "L" to "\"Bagel\""
                                        ),
                                        mutableMapOf(
                                                "#_35225" to "_:_35211",
                                                "L" to "\"Soft Drink\""
                                        )
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "O",
                                        "#_35225"
                                ), listOf(
                                        mutableMapOf(
                                                "O" to "<http://www.example.orgorder1>",
                                                "#_35225" to "_:_35202"
                                        ),
                                        mutableMapOf(
                                                "O" to "<http://www.example.orgorder1>",
                                                "#_35225" to "_:_35203"
                                        ),
                                        mutableMapOf(
                                                "O" to "<http://www.example.orgorder1>",
                                                "#_35225" to "_:_35204"
                                        ),
                                        mutableMapOf(
                                                "O" to "<http://www.example.orgorder2>",
                                                "#_35225" to "_:_35205"
                                        ),
                                        mutableMapOf(
                                                "O" to "<http://www.example.orgorder2>",
                                                "#_35225" to "_:_35206"
                                        ),
                                        mutableMapOf(
                                                "O" to "<http://www.example.orgorder2>",
                                                "#_35225" to "_:_35207"
                                        ),
                                        mutableMapOf(
                                                "O" to "<http://www.example.orgorder3>",
                                                "#_35225" to "_:_35208"
                                        ),
                                        mutableMapOf(
                                                "O" to "<http://www.example.orgorder3>",
                                                "#_35225" to "_:_35209"
                                        ),
                                        mutableMapOf(
                                                "O" to "<http://www.example.orgorder4>",
                                                "#_35225" to "_:_35210"
                                        ),
                                        mutableMapOf(
                                                "O" to "<http://www.example.orgorder4>",
                                                "#_35225" to "_:_35211"
                                        )
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "#_35225",
                                "L",
                                "O"
                        ), listOf(
                                mutableMapOf(
                                        "#_35225" to "_:_35202",
                                        "L" to "\"Ice Cream\"",
                                        "O" to "<http://www.example.orgorder1>"
                                ),
                                mutableMapOf(
                                        "#_35225" to "_:_35203",
                                        "L" to "\"Pizza\"",
                                        "O" to "<http://www.example.orgorder1>"
                                ),
                                mutableMapOf(
                                        "#_35225" to "_:_35204",
                                        "L" to "\"Wine\"",
                                        "O" to "<http://www.example.orgorder1>"
                                ),
                                mutableMapOf(
                                        "#_35225" to "_:_35205",
                                        "L" to "\"Ice Cream\"",
                                        "O" to "<http://www.example.orgorder2>"
                                ),
                                mutableMapOf(
                                        "#_35225" to "_:_35206",
                                        "L" to "\"Pasta\"",
                                        "O" to "<http://www.example.orgorder2>"
                                ),
                                mutableMapOf(
                                        "#_35225" to "_:_35207",
                                        "L" to "\"Soft Drink\"",
                                        "O" to "<http://www.example.orgorder2>"
                                ),
                                mutableMapOf(
                                        "#_35225" to "_:_35208",
                                        "L" to "\"Sandwich\"",
                                        "O" to "<http://www.example.orgorder3>"
                                ),
                                mutableMapOf(
                                        "#_35225" to "_:_35209",
                                        "L" to "\"Soft Drink\"",
                                        "O" to "<http://www.example.orgorder3>"
                                ),
                                mutableMapOf(
                                        "#_35225" to "_:_35210",
                                        "L" to "\"Bagel\"",
                                        "O" to "<http://www.example.orgorder4>"
                                ),
                                mutableMapOf(
                                        "#_35225" to "_:_35211",
                                        "L" to "\"Soft Drink\"",
                                        "O" to "<http://www.example.orgorder4>"
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/subquery/sq11.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "#_35225",
                                        "L",
                                        "O"
                                ), listOf(
                                        mutableMapOf(
                                                "#_35225" to "_:_35202",
                                                "L" to "\"Ice Cream\"",
                                                "O" to "<http://www.example.orgorder1>"
                                        ),
                                        mutableMapOf(
                                                "#_35225" to "_:_35203",
                                                "L" to "\"Pizza\"",
                                                "O" to "<http://www.example.orgorder1>"
                                        ),
                                        mutableMapOf(
                                                "#_35225" to "_:_35204",
                                                "L" to "\"Wine\"",
                                                "O" to "<http://www.example.orgorder1>"
                                        ),
                                        mutableMapOf(
                                                "#_35225" to "_:_35205",
                                                "L" to "\"Ice Cream\"",
                                                "O" to "<http://www.example.orgorder2>"
                                        ),
                                        mutableMapOf(
                                                "#_35225" to "_:_35206",
                                                "L" to "\"Pasta\"",
                                                "O" to "<http://www.example.orgorder2>"
                                        ),
                                        mutableMapOf(
                                                "#_35225" to "_:_35207",
                                                "L" to "\"Soft Drink\"",
                                                "O" to "<http://www.example.orgorder2>"
                                        ),
                                        mutableMapOf(
                                                "#_35225" to "_:_35208",
                                                "L" to "\"Sandwich\"",
                                                "O" to "<http://www.example.orgorder3>"
                                        ),
                                        mutableMapOf(
                                                "#_35225" to "_:_35209",
                                                "L" to "\"Soft Drink\"",
                                                "O" to "<http://www.example.orgorder3>"
                                        ),
                                        mutableMapOf(
                                                "#_35225" to "_:_35210",
                                                "L" to "\"Bagel\"",
                                                "O" to "<http://www.example.orgorder4>"
                                        ),
                                        mutableMapOf(
                                                "#_35225" to "_:_35211",
                                                "L" to "\"Soft Drink\"",
                                                "O" to "<http://www.example.orgorder4>"
                                        )
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "O"
                                ), listOf(
                                        mutableMapOf(
                                                "O" to "<http://www.example.orgorder1>"
                                        ),
                                        mutableMapOf(
                                                "O" to "<http://www.example.orgorder2>"
                                        )
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "#_35225",
                                "L",
                                "O"
                        ), listOf(
                                mutableMapOf(
                                        "#_35225" to "_:_35202",
                                        "L" to "\"Ice Cream\"",
                                        "O" to "<http://www.example.orgorder1>"
                                ),
                                mutableMapOf(
                                        "#_35225" to "_:_35203",
                                        "L" to "\"Pizza\"",
                                        "O" to "<http://www.example.orgorder1>"
                                ),
                                mutableMapOf(
                                        "#_35225" to "_:_35204",
                                        "L" to "\"Wine\"",
                                        "O" to "<http://www.example.orgorder1>"
                                ),
                                mutableMapOf(
                                        "#_35225" to "_:_35205",
                                        "L" to "\"Ice Cream\"",
                                        "O" to "<http://www.example.orgorder2>"
                                ),
                                mutableMapOf(
                                        "#_35225" to "_:_35206",
                                        "L" to "\"Pasta\"",
                                        "O" to "<http://www.example.orgorder2>"
                                ),
                                mutableMapOf(
                                        "#_35225" to "_:_35207",
                                        "L" to "\"Soft Drink\"",
                                        "O" to "<http://www.example.orgorder2>"
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/subquery/sq11.rq */,
            {
                MicroTest0(AOPUndef(), AOPUndef())
            }()
    ).mapIndexed { index, data ->
        DynamicTest.dynamicTest("$index") {
            try {
                if (data.input is AOPBase) {
                    val input = data.input as AOPBase
                    val output: AOPConstant
                    if (data is MicroTestA1) {
                        output = input.calculate(data.resultSet, data.resultRow)
                    } else if (data is MicroTestAN) {
                        setAggregationMode(input, true, data.resultRows.count())
                        for (resultRow in data.resultRows)
                            input.calculate(data.resultSet, resultRow)
                        setAggregationMode(input, false, data.resultRows.count())
                        output = input.calculate(data.resultSet, data.resultSet.createResultRow())
                    } else {
                        val resultSet = ResultSet(ResultSetDictionary())
                        output = input.calculate(resultSet, resultSet.createResultRow())
                    }
                    assertTrue(data.expected is AOPConstant)
                    if (!data.expected.equals(output)) {
                        if (data is MicroTestA1)
                            println(data.resultRow)
                        println(output.valueToString())
                        println((data.expected as AOPConstant).valueToString())
                    }
                    assertTrue(data.expected.equals(output))
                } else if (data.input is POPBase) {
                    val input = data.input as POPBase
                    assertTrue(data.expected is POPValues)
                    val output = QueryResultToXML.toXML(input).first()
                    val expected = QueryResultToXML.toXML(data.expected as POPValues).first()
                    if (!expected.myEquals(output)) {
                        println(output.toPrettyString())
                        println(expected.toPrettyString())
                    }
                    assertTrue(expected.myEquals(output))
                }
            } catch (e: Throwable) {
                e.printStackTrace()
                assertTrue(data.expected is Throwable)
            }
        }
    }
}

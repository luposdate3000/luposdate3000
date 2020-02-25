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
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://www.example.org/s>", "<http://www.example.org/p>", "\"0\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                    graph.addData(1L, listOf("<http://www.example.org/s>", "<http://www.example.org/p>", "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                    graph.addData(1L, listOf("<http://www.example.org/s>", "<http://www.example.org/p>", "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                    DistributedTripleStore.commit(1L)
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "S", "<http://www.example.org/p>", "O1", false, true, false, EIndexPattern.SPO)
                                }()
                                ,
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://www.example.org/s>", "<http://www.example.org/q>", "\"0\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                    graph.addData(1L, listOf("<http://www.example.org/s>", "<http://www.example.org/q>", "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                    graph.addData(1L, listOf("<http://www.example.org/s>", "<http://www.example.org/q>", "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                    DistributedTripleStore.commit(1L)
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "S", "<http://www.example.org/q>", "O2", false, true, false, EIndexPattern.SPO)
                                }()
                                ,
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
            /* {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                            ), listOf(
                                mutableMapOf(
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "S",
                                "P",
                                "O"
                            ), listOf(
                                mutableMapOf(
                                    "S" to null,
                                    "P" to null,
                                    "O" to null
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "S",
                            "P",
                            "O"
                        ), listOf(
                            mutableMapOf(
                                "S" to null,
                                "P" to null,
                                "O" to null
                            )
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/basic-update/insert-05a.ru */
            /* {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "S",
                                "P",
                                "O"
                            ), listOf(
                                mutableMapOf(
                                    "S" to null,
                                    "P" to null,
                                    "O" to null
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "S",
                                "P",
                                "O"
                            ), listOf(
                                mutableMapOf(
                                    "S" to null,
                                    "P" to null,
                                    "O" to null
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "S",
                            "P",
                            "O"
                        ), listOf(
                            mutableMapOf(
                                "S" to null,
                                "P" to null,
                                "O" to null
                            )
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/basic-update/insert-05a.ru */
            /* {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "S",
                                "P",
                                "O"
                            ), listOf(
                                mutableMapOf(
                                    "S" to null,
                                    "P" to null,
                                    "O" to null
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "count"
                            ), listOf(
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "S",
                            "P",
                            "O",
                            "count"
                        ), listOf(
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/basic-update/insert-05a.ru */
            /* {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                            ), listOf(
                                mutableMapOf(
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                            ), listOf(
                                mutableMapOf(
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                        ), listOf(
                            mutableMapOf(
                            )
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/basic-update/insert-where-same-bnode2.ru */
            /* {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                            ), listOf(
                                mutableMapOf(
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "S",
                                "P",
                                "O"
                            ), listOf(
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "S",
                            "P",
                            "O"
                        ), listOf(
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/basic-update/insert-where-same-bnode2.ru */
            /* {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                            ), listOf(
                                mutableMapOf(
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "X"
                            ), listOf(
                                mutableMapOf(
                                    "X" to null
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "X"
                        ), listOf(
                            mutableMapOf(
                                "X" to null
                            )
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/basic-update/insert-where-same-bnode.ru */
            /* {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "X"
                            ), listOf(
                                mutableMapOf(
                                    "X" to null
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "X"
                            ), listOf(
                                mutableMapOf(
                                    "X" to null
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "X"
                        ), listOf(
                            mutableMapOf(
                                "X" to null
                            )
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/basic-update/insert-where-same-bnode.ru */
            /* {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "X"
                            ), listOf(
                                mutableMapOf(
                                    "X" to null
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "S",
                                "P",
                                "O"
                            ), listOf(
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "X",
                            "S",
                            "P",
                            "O"
                        ), listOf(
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/basic-update/insert-where-same-bnode.ru */
            /* {
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
                        false                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o",
                            "s1",
                            "p1",
                            "z"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/s1>",
                                "p" to "<http://example.org/p>",
                                "o" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s1" to "<http://example.org/s1>",
                                "p1" to "<http://example.org/p>",
                                "z" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s2>",
                                "p" to "<http://example.org/p>",
                                "o" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s1" to "<http://example.org/s1>",
                                "p1" to "<http://example.org/p>",
                                "z" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s3>",
                                "p" to "<http://example.org/p>",
                                "o" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s1" to "<http://example.org/s1>",
                                "p1" to "<http://example.org/p>",
                                "z" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s4>",
                                "p" to "<http://example.org/p>",
                                "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s1" to "<http://example.org/s1>",
                                "p1" to "<http://example.org/p>",
                                "z" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s1>",
                                "p" to "<http://example.org/p>",
                                "o" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s1" to "<http://example.org/s2>",
                                "p1" to "<http://example.org/p>",
                                "z" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s2>",
                                "p" to "<http://example.org/p>",
                                "o" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s1" to "<http://example.org/s2>",
                                "p1" to "<http://example.org/p>",
                                "z" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s3>",
                                "p" to "<http://example.org/p>",
                                "o" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s1" to "<http://example.org/s2>",
                                "p1" to "<http://example.org/p>",
                                "z" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s4>",
                                "p" to "<http://example.org/p>",
                                "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s1" to "<http://example.org/s2>",
                                "p1" to "<http://example.org/p>",
                                "z" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s1>",
                                "p" to "<http://example.org/p>",
                                "o" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s1" to "<http://example.org/s3>",
                                "p1" to "<http://example.org/p>",
                                "z" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s2>",
                                "p" to "<http://example.org/p>",
                                "o" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s1" to "<http://example.org/s3>",
                                "p1" to "<http://example.org/p>",
                                "z" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s3>",
                                "p" to "<http://example.org/p>",
                                "o" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s1" to "<http://example.org/s3>",
                                "p1" to "<http://example.org/p>",
                                "z" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s4>",
                                "p" to "<http://example.org/p>",
                                "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s1" to "<http://example.org/s3>",
                                "p1" to "<http://example.org/p>",
                                "z" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s1>",
                                "p" to "<http://example.org/p>",
                                "o" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s1" to "<http://example.org/s4>",
                                "p1" to "<http://example.org/p>",
                                "z" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s2>",
                                "p" to "<http://example.org/p>",
                                "o" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s1" to "<http://example.org/s4>",
                                "p1" to "<http://example.org/p>",
                                "z" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s3>",
                                "p" to "<http://example.org/p>",
                                "o" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s1" to "<http://example.org/s4>",
                                "p1" to "<http://example.org/p>",
                                "z" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s4>",
                                "p" to "<http://example.org/p>",
                                "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s1" to "<http://example.org/s4>",
                                "p1" to "<http://example.org/p>",
                                "z" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            )
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/bind/bind03.rq */
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
                                        "z"
                                ), listOf(
                                        mutableMapOf(
                                                "z" to null
                                        ),
                                        mutableMapOf(
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
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/book/book1>", "<http://purl.org/dc/elements/1.1/title>", "\"SPARQL Tutorial\""))
                                    graph.addData(1L, listOf("<http://example.org/book/book2>", "<http://purl.org/dc/elements/1.1/title>", "\"The Semantic Web\""))
                                    DistributedTripleStore.commit(1L)
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "book", "<http://purl.org/dc/elements/1.1/title>", "title", false, true, false, EIndexPattern.SPO)
                                }()
                                ,
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/book/book1>", "<http://example.org/ns#price>", "\"42\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                    graph.addData(1L, listOf("<http://example.org/book/book2>", "<http://example.org/ns#price>", "\"23\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                    DistributedTripleStore.commit(1L)
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "book", "<http://example.org/ns#price>", "price", false, true, false, EIndexPattern.SPO)
                                }()
                                ,
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
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/a>", "<http://xmlns.com/foaf/0.1/knows>", "<http://example.org/b>"))
                                    graph.addData(1L, listOf("<http://example.org/a>", "<http://xmlns.com/foaf/0.1/mbox>", "\"alan@example.org\""))
                                    graph.addData(1L, listOf("<http://example.org/a>", "<http://xmlns.com/foaf/0.1/name>", "\"Alan\""))
                                    graph.addData(1L, listOf("<http://example.org/b>", "<http://xmlns.com/foaf/0.1/mbox>", "\"bob@example.org\""))
                                    graph.addData(1L, listOf("<http://example.org/b>", "<http://xmlns.com/foaf/0.1/name>", "\"Bob\""))
                                    DistributedTripleStore.commit(1L)
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "s", "p1", "o1", false, false, false, EIndexPattern.SPO)
                                }()
                                ,
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/a>", "<http://xmlns.com/foaf/0.1/knows>", "<http://example.org/b>"))
                                    graph.addData(1L, listOf("<http://example.org/a>", "<http://xmlns.com/foaf/0.1/mbox>", "\"alan@example.org\""))
                                    graph.addData(1L, listOf("<http://example.org/a>", "<http://xmlns.com/foaf/0.1/name>", "\"Alan\""))
                                    graph.addData(1L, listOf("<http://example.org/b>", "<http://xmlns.com/foaf/0.1/mbox>", "\"bob@example.org\""))
                                    graph.addData(1L, listOf("<http://example.org/b>", "<http://xmlns.com/foaf/0.1/name>", "\"Bob\""))
                                    DistributedTripleStore.commit(1L)
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "s", "p2", "o2", false, false, false, EIndexPattern.SPO)
                                }()
                                ,
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
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/a>", "<http://xmlns.com/foaf/0.1/knows>", "<http://example.org/b>"))
                                    graph.addData(1L, listOf("<http://example.org/a>", "<http://xmlns.com/foaf/0.1/mbox>", "\"alan@example.org\""))
                                    graph.addData(1L, listOf("<http://example.org/a>", "<http://xmlns.com/foaf/0.1/name>", "\"Alan\""))
                                    graph.addData(1L, listOf("<http://example.org/b>", "<http://xmlns.com/foaf/0.1/knows>", "<http://example.org/c>"))
                                    graph.addData(1L, listOf("<http://example.org/b>", "<http://xmlns.com/foaf/0.1/mbox>", "\"bob@example.org\""))
                                    graph.addData(1L, listOf("<http://example.org/b>", "<http://xmlns.com/foaf/0.1/name>", "\"Bob\""))
                                    graph.addData(1L, listOf("<http://example.org/c>", "<http://xmlns.com/foaf/0.1/mbox>", "\"alice@example.org\""))
                                    graph.addData(1L, listOf("<http://example.org/c>", "<http://xmlns.com/foaf/0.1/name>", "\"Alice\""))
                                    DistributedTripleStore.commit(1L)
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "s", "p1", "o1", false, false, false, EIndexPattern.SPO)
                                }()
                                ,
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/a>", "<http://xmlns.com/foaf/0.1/knows>", "<http://example.org/b>"))
                                    graph.addData(1L, listOf("<http://example.org/b>", "<http://xmlns.com/foaf/0.1/knows>", "<http://example.org/c>"))
                                    DistributedTripleStore.commit(1L)
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "s", "<http://xmlns.com/foaf/0.1/knows>", "o2", false, true, false, EIndexPattern.SPO)
                                }()
                                ,
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
            /* {
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
            }() */ /* resources/sparql11-test-suite/csv-tsv-res/csvtsv02.rq */
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
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/s1>", "<http://example.org/p1>", "<http://example.org/s2>"))
                                    graph.addData(1L, listOf("<http://example.org/s2>", "<http://example.org/p2>", "\"foo\""))
                                    graph.addData(1L, listOf("<http://example.org/s3>", "<http://example.org/p3>", "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                    graph.addData(1L, listOf("<http://example.org/s4>", "<http://example.org/p4>", "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                    graph.addData(1L, listOf("<http://example.org/s5>", "<http://example.org/p5>", "\"5.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                    graph.addData(1L, listOf("<http://example.org/s6>", "<http://example.org/p6>", "_:o6"))
                                    DistributedTripleStore.commit(1L)
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "s", "p", "o", false, false, false, EIndexPattern.SPO)
                                }()
                                ,
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/s1>", "<http://example.org/p1>", "<http://example.org/s2>"))
                                    graph.addData(1L, listOf("<http://example.org/s2>", "<http://example.org/p2>", "\"foo\""))
                                    graph.addData(1L, listOf("<http://example.org/s3>", "<http://example.org/p3>", "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                    graph.addData(1L, listOf("<http://example.org/s4>", "<http://example.org/p4>", "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                    graph.addData(1L, listOf("<http://example.org/s5>", "<http://example.org/p5>", "\"5.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                    graph.addData(1L, listOf("<http://example.org/s6>", "<http://example.org/p6>", "_:o6"))
                                    DistributedTripleStore.commit(1L)
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "o", "p2", "o2", false, false, false, EIndexPattern.SPO)
                                }()
                                ,
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
            /* {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                            ), listOf(
                                mutableMapOf(
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "a",
                                "b"
                            ), listOf(
                                mutableMapOf(
                                    "a" to null,
                                    "b" to null
                                ),
                                mutableMapOf(
                                    "a" to null,
                                    "b" to null
                                ),
                                mutableMapOf(
                                    "a" to null,
                                    "b" to null
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "a",
                            "b"
                        ), listOf(
                            mutableMapOf(
                                "a" to null,
                                "b" to null
                            ),
                            mutableMapOf(
                                "a" to null,
                                "b" to null
                            ),
                            mutableMapOf(
                                "a" to null,
                                "b" to null
                            )
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/delete-insert/delete-insert-01c.ru */
            /* {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "a",
                                "b"
                            ), listOf(
                                mutableMapOf(
                                    "a" to null,
                                    "b" to null
                                ),
                                mutableMapOf(
                                    "a" to null,
                                    "b" to null
                                ),
                                mutableMapOf(
                                    "a" to null,
                                    "b" to null
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "a",
                                "b"
                            ), listOf(
                                mutableMapOf(
                                    "a" to null,
                                    "b" to null
                                ),
                                mutableMapOf(
                                    "a" to null,
                                    "b" to null
                                ),
                                mutableMapOf(
                                    "a" to null,
                                    "b" to null
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "a",
                            "b"
                        ), listOf(
                            mutableMapOf(
                                "a" to null,
                                "b" to null
                            ),
                            mutableMapOf(
                                "a" to null,
                                "b" to null
                            ),
                            mutableMapOf(
                                "a" to null,
                                "b" to null
                            ),
                            mutableMapOf(
                                "a" to null,
                                "b" to null
                            ),
                            mutableMapOf(
                                "a" to null,
                                "b" to null
                            ),
                            mutableMapOf(
                                "a" to null,
                                "b" to null
                            ),
                            mutableMapOf(
                                "a" to null,
                                "b" to null
                            ),
                            mutableMapOf(
                                "a" to null,
                                "b" to null
                            ),
                            mutableMapOf(
                                "a" to null,
                                "b" to null
                            )
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/delete-insert/delete-insert-01c.ru */
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
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/a>", "<http://xmlns.com/foaf/0.1/name>", "\"Alan\""))
                                    DistributedTripleStore.commit(1L)
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "a", "<http://xmlns.com/foaf/0.1/name>", "\"Alan\"", false, true, true, EIndexPattern.SPO)
                                }()
                                ,
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/a>", "<http://xmlns.com/foaf/0.1/knows>", "<http://example.org/b>"))
                                    graph.addData(1L, listOf("<http://example.org/a>", "<http://xmlns.com/foaf/0.1/knows>", "<http://example.org/c>"))
                                    graph.addData(1L, listOf("<http://example.org/b>", "<http://xmlns.com/foaf/0.1/knows>", "<http://example.org/c>"))
                                    DistributedTripleStore.commit(1L)
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "a", "<http://xmlns.com/foaf/0.1/knows>", "b", false, true, false, EIndexPattern.SPO)
                                }()
                                ,
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
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/a>", "<http://xmlns.com/foaf/0.1/name>", "\"Alan\""))
                                    DistributedTripleStore.commit(1L)
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "a", "<http://xmlns.com/foaf/0.1/name>", "\"Alan\"", false, true, true, EIndexPattern.SPO)
                                }()
                                ,
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/a>", "<http://xmlns.com/foaf/0.1/knows>", "<http://example.org/b>"))
                                    graph.addData(1L, listOf("<http://example.org/a>", "<http://xmlns.com/foaf/0.1/knows>", "<http://example.org/c>"))
                                    graph.addData(1L, listOf("<http://example.org/b>", "<http://xmlns.com/foaf/0.1/knows>", "<http://example.org/c>"))
                                    DistributedTripleStore.commit(1L)
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "a", "<http://xmlns.com/foaf/0.1/knows>", "Var_B", false, true, false, EIndexPattern.SPO)
                                }()
                                ,
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
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/a>", "<http://xmlns.com/foaf/0.1/name>", "\"Alan\""))
                                    DistributedTripleStore.commit(1L)
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "a", "<http://xmlns.com/foaf/0.1/name>", "\"Alan\"", false, true, true, EIndexPattern.SPO)
                                }()
                                ,
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/b>", "<http://xmlns.com/foaf/0.1/knows>", "<http://example.org/c>"))
                                    DistributedTripleStore.commit(1L)
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "a", "<http://xmlns.com/foaf/0.1/knows>", "Var_B", false, true, false, EIndexPattern.SPO)
                                }()
                                ,
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
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/a>", "<http://xmlns.com/foaf/0.1/knows>", "<http://example.org/b>"))
                                    DistributedTripleStore.commit(1L)
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "<http://example.org/a>", "<http://xmlns.com/foaf/0.1/knows>", "s", true, true, false, EIndexPattern.SPO)
                                }()
                                ,
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/a>", "<http://xmlns.com/foaf/0.1/knows>", "<http://example.org/b>"))
                                    graph.addData(1L, listOf("<http://example.org/a>", "<http://xmlns.com/foaf/0.1/mbox>", "\"alan@example.org\""))
                                    graph.addData(1L, listOf("<http://example.org/a>", "<http://xmlns.com/foaf/0.1/name>", "\"Alan\""))
                                    graph.addData(1L, listOf("<http://example.org/b>", "<http://xmlns.com/foaf/0.1/mbox>", "\"bob@example.org\""))
                                    graph.addData(1L, listOf("<http://example.org/b>", "<http://xmlns.com/foaf/0.1/name>", "\"Bob\""))
                                    DistributedTripleStore.commit(1L)
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "s", "p", "o", false, false, false, EIndexPattern.SPO)
                                }()
                                ,
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
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    DistributedTripleStore.commit(1L)
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "s", "<http://xmlns.com/foaf/0.1/knows>", "<http://example.org/c>", false, true, true, EIndexPattern.SPO)
                                }()
                                ,
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/a>", "<http://xmlns.com/foaf/0.1/knows>", "<http://example.org/b>"))
                                    graph.addData(1L, listOf("<http://example.org/a>", "<http://xmlns.com/foaf/0.1/mbox>", "\"alan@example.org\""))
                                    graph.addData(1L, listOf("<http://example.org/a>", "<http://xmlns.com/foaf/0.1/name>", "\"Alan\""))
                                    graph.addData(1L, listOf("<http://example.org/b>", "<http://xmlns.com/foaf/0.1/mbox>", "\"bob@example.org\""))
                                    graph.addData(1L, listOf("<http://example.org/b>", "<http://xmlns.com/foaf/0.1/name>", "\"Bob\""))
                                    DistributedTripleStore.commit(1L)
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "s", "p", "o", false, false, false, EIndexPattern.SPO)
                                }()
                                ,
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
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/c>", "<http://xmlns.com/foaf/0.1/name>", "\"Chris\""))
                                    DistributedTripleStore.commit(1L)
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "s", "<http://xmlns.com/foaf/0.1/name>", "\"Chris\"", false, true, true, EIndexPattern.SPO)
                                }()
                                ,
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/a>", "<http://xmlns.com/foaf/0.1/knows>", "<http://example.org/b>"))
                                    graph.addData(1L, listOf("<http://example.org/b>", "<http://xmlns.com/foaf/0.1/knows>", "<http://example.org/c>"))
                                    graph.addData(1L, listOf("<http://example.org/b>", "<http://xmlns.com/foaf/0.1/mbox>", "\"bob@example.org\""))
                                    graph.addData(1L, listOf("<http://example.org/b>", "<http://xmlns.com/foaf/0.1/name>", "\"Bob\""))
                                    graph.addData(1L, listOf("<http://example.org/c>", "<http://xmlns.com/foaf/0.1/mbox>", "\"chris@example.org\""))
                                    graph.addData(1L, listOf("<http://example.org/c>", "<http://xmlns.com/foaf/0.1/name>", "\"Chris\""))
                                    DistributedTripleStore.commit(1L)
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "s", "p", "o", false, false, false, EIndexPattern.SPO)
                                }()
                                ,
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
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/a>", "<http://xmlns.com/foaf/0.1/knows>", "<http://example.org/b>"))
                                    DistributedTripleStore.commit(1L)
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "<http://example.org/a>", "<http://xmlns.com/foaf/0.1/knows>", "s", true, true, false, EIndexPattern.SPO)
                                }()
                                ,
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/a>", "<http://xmlns.com/foaf/0.1/knows>", "<http://example.org/b>"))
                                    graph.addData(1L, listOf("<http://example.org/b>", "<http://xmlns.com/foaf/0.1/knows>", "<http://example.org/c>"))
                                    graph.addData(1L, listOf("<http://example.org/b>", "<http://xmlns.com/foaf/0.1/mbox>", "\"bob@example.org\""))
                                    graph.addData(1L, listOf("<http://example.org/b>", "<http://xmlns.com/foaf/0.1/name>", "\"Bob\""))
                                    graph.addData(1L, listOf("<http://example.org/c>", "<http://xmlns.com/foaf/0.1/mbox>", "\"chris@example.org\""))
                                    graph.addData(1L, listOf("<http://example.org/c>", "<http://xmlns.com/foaf/0.1/name>", "\"Chris\""))
                                    DistributedTripleStore.commit(1L)
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "s", "p", "o", false, false, false, EIndexPattern.SPO)
                                }()
                                ,
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
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    DistributedTripleStore.commit(1L)
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "<http://example.org/a>", "<http://xmlns.com/foaf/0.1/knows>", "s", true, true, false, EIndexPattern.SPO)
                                }()
                                ,
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/c>", "<http://xmlns.com/foaf/0.1/knows>", "<http://example.org/d>"))
                                    graph.addData(1L, listOf("<http://example.org/c>", "<http://xmlns.com/foaf/0.1/mbox>", "\"chris@example.org\""))
                                    graph.addData(1L, listOf("<http://example.org/c>", "<http://xmlns.com/foaf/0.1/name>", "\"Chris\""))
                                    graph.addData(1L, listOf("<http://example.org/d>", "<http://xmlns.com/foaf/0.1/mbox>", "\"dan@example.org\""))
                                    graph.addData(1L, listOf("<http://example.org/d>", "<http://xmlns.com/foaf/0.1/name>", "\"Dan\""))
                                    DistributedTripleStore.commit(1L)
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "s", "p", "o", false, false, false, EIndexPattern.SPO)
                                }()
                                ,
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
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPJoinHashMap(
                                dictionary,
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    DistributedTripleStore.commit(1L)
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "s", "<http://xmlns.com/foaf/0.1/knows>", "<http://example.org/d>", false, true, true, EIndexPattern.SPO)
                                }()
                                ,
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/a>", "<http://xmlns.com/foaf/0.1/knows>", "<http://example.org/b>"))
                                    graph.addData(1L, listOf("<http://example.org/b>", "<http://xmlns.com/foaf/0.1/knows>", "<http://example.org/c>"))
                                    graph.addData(1L, listOf("<http://example.org/b>", "<http://xmlns.com/foaf/0.1/mbox>", "\"bob@example.org\""))
                                    graph.addData(1L, listOf("<http://example.org/b>", "<http://xmlns.com/foaf/0.1/name>", "\"Bob\""))
                                    graph.addData(1L, listOf("<http://example.org/c>", "<http://xmlns.com/foaf/0.1/mbox>", "\"chris@example.org\""))
                                    graph.addData(1L, listOf("<http://example.org/c>", "<http://xmlns.com/foaf/0.1/name>", "\"Chris\""))
                                    DistributedTripleStore.commit(1L)
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "s", "p", "o", false, false, false, EIndexPattern.SPO)
                                }()
                                ,
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
            /* {
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
            }() */ /* resources/sparql11-test-suite/delete/delete-using-06.ru */
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
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPJoinHashMap(
                                dictionary,
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/a>", "<http://xmlns.com/foaf/0.1/knows>", "<http://example.org/b>"))
                                    DistributedTripleStore.commit(1L)
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "s", "<http://xmlns.com/foaf/0.1/knows>", "<http://example.org/b>", false, true, true, EIndexPattern.SPO)
                                }()
                                ,
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/a>", "<http://xmlns.com/foaf/0.1/knows>", "<http://example.org/b>"))
                                    graph.addData(1L, listOf("<http://example.org/a>", "<http://xmlns.com/foaf/0.1/mbox>", "\"alan@example.org\""))
                                    graph.addData(1L, listOf("<http://example.org/a>", "<http://xmlns.com/foaf/0.1/name>", "\"Alan\""))
                                    graph.addData(1L, listOf("<http://example.org/b>", "<http://xmlns.com/foaf/0.1/mbox>", "\"bob@example.org\""))
                                    graph.addData(1L, listOf("<http://example.org/b>", "<http://xmlns.com/foaf/0.1/name>", "\"Bob\""))
                                    DistributedTripleStore.commit(1L)
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "s", "p", "o", false, false, false, EIndexPattern.SPO)
                                }()
                                ,
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
            /* {
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
            }() */ /* resources/sparql11-test-suite/entailment/rdfs05.rq */
            /* {
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
            }() */ /* resources/sparql11-test-suite/entailment/rdfs11.rq */
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
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/x/a>", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://example.org/x/c>"))
                                    graph.addData(1L, listOf("<http://example.org/x/a>", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://example.org/x/d>"))
                                    graph.addData(1L, listOf("<http://example.org/x/a>", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://www.w3.org/2002/07/owl#NamedIndividual>"))
                                    graph.addData(1L, listOf("<http://example.org/x/a>", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "_:x"))
                                    graph.addData(1L, listOf("<http://example.org/x/c>", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://www.w3.org/2002/07/owl#Class>"))
                                    graph.addData(1L, listOf("<http://example.org/x/d>", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://www.w3.org/2002/07/owl#Class>"))
                                    graph.addData(1L, listOf("<http://example.org/x/p>", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://www.w3.org/2002/07/owl#ObjectProperty>"))
                                    graph.addData(1L, listOf("_:ont", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://www.w3.org/2002/07/owl#Ontology>"))
                                    graph.addData(1L, listOf("_:x", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://www.w3.org/2002/07/owl#Restriction>"))
                                    DistributedTripleStore.commit(1L)
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "x", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "c", false, true, false, EIndexPattern.SPO)
                                }()
                                ,
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    DistributedTripleStore.commit(1L)
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "c", "<http://www.w3.org/2000/01/rdf-schema#subClassOf>", "<http://example.org/x/c>", false, true, true, EIndexPattern.SPO)
                                }()
                                ,
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
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPJoinHashMap(
                                dictionary,
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/x/x>", "<http://example.org/x/p>", "<http://example.org/x/y>"))
                                    graph.addData(1L, listOf("<http://example.org/x/x>", "<http://example.org/x/p>", "_:y"))
                                    DistributedTripleStore.commit(1L)
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "x", "<http://example.org/x/p>", "y", false, true, false, EIndexPattern.SPO)
                                }()
                                ,
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/x/y>", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://example.org/x/c>"))
                                    graph.addData(1L, listOf("_:y", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://example.org/x/c>"))
                                    DistributedTripleStore.commit(1L)
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "y", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://example.org/x/c>", false, true, true, EIndexPattern.SPO)
                                }()
                                ,
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
            /* {
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
                                    "x" to "_:_15849",
                                    "#c" to "<http://www.w3.org/2002/07/owl#Restriction>"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_15870"
                            ), listOf(
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "#c",
                            "#_15870"
                        ), listOf(
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/paper-sparqldl-Q2.rq */
            /* {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "x",
                                "#c",
                                "#_15870",
                                "#_15875"
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
                            "#_15870",
                            "#_15875",
                            "y"
                        ), listOf(
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/paper-sparqldl-Q2.rq */
            /* {
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
                                "#_15995"
                            ), listOf(
                                mutableMapOf(
                                    "#_15995" to "_:_15975"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "#b0",
                            "#_15995"
                        ), listOf(
                            mutableMapOf(
                                "x" to "<http://example.org/John>",
                                "#b0" to "<http://example.org/paper1>",
                                "#_15995" to "_:_15975"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/person1>",
                                "#b0" to "<http://example.org/paper1>",
                                "#_15995" to "_:_15975"
                            )
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/paper-sparqldl-Q3.rq */
            /* {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "x",
                                "#b0",
                                "#_15995"
                            ), listOf(
                                mutableMapOf(
                                    "x" to "<http://example.org/John>",
                                    "#b0" to "<http://example.org/paper1>",
                                    "#_15995" to "_:_15975"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/person1>",
                                    "#b0" to "<http://example.org/paper1>",
                                    "#_15995" to "_:_15975"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_15995"
                            ), listOf(
                                mutableMapOf(
                                    "#_15995" to "_:_15975"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "#b0",
                            "#_15995"
                        ), listOf(
                            mutableMapOf(
                                "x" to "<http://example.org/John>",
                                "#b0" to "<http://example.org/paper1>",
                                "#_15995" to "_:_15975"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/person1>",
                                "#b0" to "<http://example.org/paper1>",
                                "#_15995" to "_:_15975"
                            )
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/paper-sparqldl-Q3.rq */
            /* {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "x",
                                "#b0",
                                "#_15995"
                            ), listOf(
                                mutableMapOf(
                                    "x" to "<http://example.org/John>",
                                    "#b0" to "<http://example.org/paper1>",
                                    "#_15995" to "_:_15975"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/person1>",
                                    "#b0" to "<http://example.org/paper1>",
                                    "#_15995" to "_:_15975"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_16004"
                            ), listOf(
                                mutableMapOf(
                                    "#_16004" to "<http://example.org/Conference>"
                                ),
                                mutableMapOf(
                                    "#_16004" to "<http://example.org/ConferencePaper>"
                                ),
                                mutableMapOf(
                                    "#_16004" to "<http://example.org/Employee>"
                                ),
                                mutableMapOf(
                                    "#_16004" to "<http://example.org/GraduateAssistant>"
                                ),
                                mutableMapOf(
                                    "#_16004" to "<http://example.org/Student>"
                                ),
                                mutableMapOf(
                                    "#_16004" to "<http://example.org/Workshop>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "#b0",
                            "#_15995",
                            "#_16004"
                        ), listOf(
                            mutableMapOf(
                                "x" to "<http://example.org/John>",
                                "#b0" to "<http://example.org/paper1>",
                                "#_15995" to "_:_15975",
                                "#_16004" to "<http://example.org/Conference>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/person1>",
                                "#b0" to "<http://example.org/paper1>",
                                "#_15995" to "_:_15975",
                                "#_16004" to "<http://example.org/Conference>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/John>",
                                "#b0" to "<http://example.org/paper1>",
                                "#_15995" to "_:_15975",
                                "#_16004" to "<http://example.org/ConferencePaper>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/person1>",
                                "#b0" to "<http://example.org/paper1>",
                                "#_15995" to "_:_15975",
                                "#_16004" to "<http://example.org/ConferencePaper>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/John>",
                                "#b0" to "<http://example.org/paper1>",
                                "#_15995" to "_:_15975",
                                "#_16004" to "<http://example.org/Employee>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/person1>",
                                "#b0" to "<http://example.org/paper1>",
                                "#_15995" to "_:_15975",
                                "#_16004" to "<http://example.org/Employee>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/John>",
                                "#b0" to "<http://example.org/paper1>",
                                "#_15995" to "_:_15975",
                                "#_16004" to "<http://example.org/GraduateAssistant>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/person1>",
                                "#b0" to "<http://example.org/paper1>",
                                "#_15995" to "_:_15975",
                                "#_16004" to "<http://example.org/GraduateAssistant>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/John>",
                                "#b0" to "<http://example.org/paper1>",
                                "#_15995" to "_:_15975",
                                "#_16004" to "<http://example.org/Student>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/person1>",
                                "#b0" to "<http://example.org/paper1>",
                                "#_15995" to "_:_15975",
                                "#_16004" to "<http://example.org/Student>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/John>",
                                "#b0" to "<http://example.org/paper1>",
                                "#_15995" to "_:_15975",
                                "#_16004" to "<http://example.org/Workshop>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/person1>",
                                "#b0" to "<http://example.org/paper1>",
                                "#_15995" to "_:_15975",
                                "#_16004" to "<http://example.org/Workshop>"
                            )
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/paper-sparqldl-Q3.rq */
            /* {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "x",
                                "#b0",
                                "#_15995",
                                "#_16004"
                            ), listOf(
                                mutableMapOf(
                                    "x" to "<http://example.org/John>",
                                    "#b0" to "<http://example.org/paper1>",
                                    "#_15995" to "_:_15975",
                                    "#_16004" to "<http://example.org/Conference>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/person1>",
                                    "#b0" to "<http://example.org/paper1>",
                                    "#_15995" to "_:_15975",
                                    "#_16004" to "<http://example.org/Conference>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/John>",
                                    "#b0" to "<http://example.org/paper1>",
                                    "#_15995" to "_:_15975",
                                    "#_16004" to "<http://example.org/ConferencePaper>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/person1>",
                                    "#b0" to "<http://example.org/paper1>",
                                    "#_15995" to "_:_15975",
                                    "#_16004" to "<http://example.org/ConferencePaper>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/John>",
                                    "#b0" to "<http://example.org/paper1>",
                                    "#_15995" to "_:_15975",
                                    "#_16004" to "<http://example.org/Employee>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/person1>",
                                    "#b0" to "<http://example.org/paper1>",
                                    "#_15995" to "_:_15975",
                                    "#_16004" to "<http://example.org/Employee>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/John>",
                                    "#b0" to "<http://example.org/paper1>",
                                    "#_15995" to "_:_15975",
                                    "#_16004" to "<http://example.org/GraduateAssistant>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/person1>",
                                    "#b0" to "<http://example.org/paper1>",
                                    "#_15995" to "_:_15975",
                                    "#_16004" to "<http://example.org/GraduateAssistant>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/John>",
                                    "#b0" to "<http://example.org/paper1>",
                                    "#_15995" to "_:_15975",
                                    "#_16004" to "<http://example.org/Student>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/person1>",
                                    "#b0" to "<http://example.org/paper1>",
                                    "#_15995" to "_:_15975",
                                    "#_16004" to "<http://example.org/Student>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/John>",
                                    "#b0" to "<http://example.org/paper1>",
                                    "#_15995" to "_:_15975",
                                    "#_16004" to "<http://example.org/Workshop>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/person1>",
                                    "#b0" to "<http://example.org/paper1>",
                                    "#_15995" to "_:_15975",
                                    "#_16004" to "<http://example.org/Workshop>"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_16004"
                            ), listOf(
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "#b0",
                            "#_15995",
                            "#_16004"
                        ), listOf(
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/paper-sparqldl-Q3.rq */
            /* {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "x",
                                "#b0",
                                "#_15995",
                                "#_16004"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_15995",
                                "#_16004"
                            ), listOf(
                                mutableMapOf(
                                    "#_15995" to "_:_15975",
                                    "#_16004" to "<http://example.org/Conference>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "#b0",
                            "#_15995",
                            "#_16004"
                        ), listOf(
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/paper-sparqldl-Q3.rq */
            /* {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "x",
                                "#b0",
                                "#_15995",
                                "#_16004"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#b0",
                                "#_15995"
                            ), listOf(
                                mutableMapOf(
                                    "#b0" to "<http://example.org/Anite>",
                                    "#_15995" to "<http://example.org/Student>"
                                ),
                                mutableMapOf(
                                    "#b0" to "<http://example.org/Anite>",
                                    "#_15995" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "#b0" to "<http://example.org/Conference>",
                                    "#_15995" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "#b0" to "<http://example.org/ConferencePaper>",
                                    "#_15995" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "#b0" to "<http://example.org/Employee>",
                                    "#_15995" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "#b0" to "<http://example.org/George>",
                                    "#_15995" to "<http://example.org/Employee>"
                                ),
                                mutableMapOf(
                                    "#b0" to "<http://example.org/George>",
                                    "#_15995" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "#b0" to "<http://example.org/GraduateAssistant>",
                                    "#_15995" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "#b0" to "<http://example.org/John>",
                                    "#_15995" to "<http://example.org/GraduateAssistant>"
                                ),
                                mutableMapOf(
                                    "#b0" to "<http://example.org/John>",
                                    "#_15995" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "#b0" to "<http://example.org/Student>",
                                    "#_15995" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "#b0" to "<http://example.org/Workshop>",
                                    "#_15995" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "#b0" to "<http://example.org/hasPublication>",
                                    "#_15995" to "<http://www.w3.org/2002/07/owl#ObjectProperty>"
                                ),
                                mutableMapOf(
                                    "#b0" to "<http://example.org/name>",
                                    "#_15995" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>"
                                ),
                                mutableMapOf(
                                    "#b0" to "<http://example.org/paper1>",
                                    "#_15995" to "<http://example.org/ConferencePaper>"
                                ),
                                mutableMapOf(
                                    "#b0" to "<http://example.org/paper1>",
                                    "#_15995" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "#b0" to "<http://example.org/person1>",
                                    "#_15995" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "#b0" to "<http://example.org/publishedAt>",
                                    "#_15995" to "<http://www.w3.org/2002/07/owl#ObjectProperty>"
                                ),
                                mutableMapOf(
                                    "#b0" to "_:SPARQLDAWGTestOntology",
                                    "#_15995" to "<http://www.w3.org/2002/07/owl#Ontology>"
                                ),
                                mutableMapOf(
                                    "#b0" to "_:_15975",
                                    "#_15995" to "<http://www.w3.org/2002/07/owl#Restriction>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "#b0",
                            "#_15995",
                            "#_16004"
                        ), listOf(
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/paper-sparqldl-Q3.rq */
            /* {
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
                                    "x" to "_:_16103",
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
            }() */ /* resources/sparql11-test-suite/entailment/paper-sparqldl-Q4.rq */
            /* {
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
            }() */ /* resources/sparql11-test-suite/entailment/paper-sparqldl-Q4.rq */
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
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/hasPublication>", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://www.w3.org/2002/07/owl#ObjectProperty>"))
                                    graph.addData(1L, listOf("<http://example.org/publishedAt>", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://www.w3.org/2002/07/owl#ObjectProperty>"))
                                    DistributedTripleStore.commit(1L)
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "p", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://www.w3.org/2002/07/owl#ObjectProperty>", false, true, true, EIndexPattern.SPO)
                                }()
                                ,
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/John>", "<http://example.org/hasPublication>", "<http://example.org/paper1>"))
                                    graph.addData(1L, listOf("<http://example.org/John>", "<http://example.org/name>", "\"Johnnie\""))
                                    graph.addData(1L, listOf("<http://example.org/John>", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://example.org/GraduateAssistant>"))
                                    graph.addData(1L, listOf("<http://example.org/John>", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://www.w3.org/2002/07/owl#NamedIndividual>"))
                                    DistributedTripleStore.commit(1L)
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "<http://example.org/John>", "p", "v", true, false, false, EIndexPattern.SPO)
                                }()
                                ,
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
            }() /* resources/sparql11-test-suite/entailment/bind08.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPJoinHashMap(
                                dictionary,
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/p>", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://www.w3.org/2002/07/owl#DatatypeProperty>"))
                                    graph.addData(1L, listOf("<http://example.org/s1>", "<http://example.org/p>", "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                    graph.addData(1L, listOf("<http://example.org/s2>", "<http://example.org/p>", "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                    graph.addData(1L, listOf("<http://example.org/s3>", "<http://example.org/p>", "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                    graph.addData(1L, listOf("<http://example.org/s4>", "<http://example.org/p>", "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                    graph.addData(1L, listOf("_:1", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://www.w3.org/2002/07/owl#Ontology>"))
                                    DistributedTripleStore.commit(1L)
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "s", "p", "o", false, false, false, EIndexPattern.SPO)
                                }()
                                ,
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/p>", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://www.w3.org/2002/07/owl#DatatypeProperty>"))
                                    DistributedTripleStore.commit(1L)
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "p", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://www.w3.org/2002/07/owl#DatatypeProperty>", false, true, true, EIndexPattern.SPO)
                                }()
                                ,
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
            }() /* resources/sparql11-test-suite/entailment/bind08.rq */,
            /* {
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
                        false                    ),
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
            }() */ /* resources/sparql11-test-suite/entailment/bind03.rq */
            /* {
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
                        false                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o",
                            "s1",
                            "p1",
                            "z"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/s1>",
                                "p" to "<http://example.org/p>",
                                "o" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s1" to "<http://example.org/p>",
                                "p1" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "z" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s2>",
                                "p" to "<http://example.org/p>",
                                "o" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s1" to "<http://example.org/p>",
                                "p1" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "z" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s3>",
                                "p" to "<http://example.org/p>",
                                "o" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s1" to "<http://example.org/p>",
                                "p1" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "z" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s4>",
                                "p" to "<http://example.org/p>",
                                "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s1" to "<http://example.org/p>",
                                "p1" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "z" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s1>",
                                "p" to "<http://example.org/p>",
                                "o" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s1" to "<http://example.org/s1>",
                                "p1" to "<http://example.org/p>",
                                "z" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s2>",
                                "p" to "<http://example.org/p>",
                                "o" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s1" to "<http://example.org/s1>",
                                "p1" to "<http://example.org/p>",
                                "z" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s3>",
                                "p" to "<http://example.org/p>",
                                "o" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s1" to "<http://example.org/s1>",
                                "p1" to "<http://example.org/p>",
                                "z" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s4>",
                                "p" to "<http://example.org/p>",
                                "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s1" to "<http://example.org/s1>",
                                "p1" to "<http://example.org/p>",
                                "z" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s1>",
                                "p" to "<http://example.org/p>",
                                "o" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s1" to "<http://example.org/s2>",
                                "p1" to "<http://example.org/p>",
                                "z" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s2>",
                                "p" to "<http://example.org/p>",
                                "o" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s1" to "<http://example.org/s2>",
                                "p1" to "<http://example.org/p>",
                                "z" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s3>",
                                "p" to "<http://example.org/p>",
                                "o" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s1" to "<http://example.org/s2>",
                                "p1" to "<http://example.org/p>",
                                "z" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s4>",
                                "p" to "<http://example.org/p>",
                                "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s1" to "<http://example.org/s2>",
                                "p1" to "<http://example.org/p>",
                                "z" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s1>",
                                "p" to "<http://example.org/p>",
                                "o" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s1" to "<http://example.org/s3>",
                                "p1" to "<http://example.org/p>",
                                "z" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s2>",
                                "p" to "<http://example.org/p>",
                                "o" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s1" to "<http://example.org/s3>",
                                "p1" to "<http://example.org/p>",
                                "z" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s3>",
                                "p" to "<http://example.org/p>",
                                "o" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s1" to "<http://example.org/s3>",
                                "p1" to "<http://example.org/p>",
                                "z" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s4>",
                                "p" to "<http://example.org/p>",
                                "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s1" to "<http://example.org/s3>",
                                "p1" to "<http://example.org/p>",
                                "z" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s1>",
                                "p" to "<http://example.org/p>",
                                "o" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s1" to "<http://example.org/s4>",
                                "p1" to "<http://example.org/p>",
                                "z" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s2>",
                                "p" to "<http://example.org/p>",
                                "o" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s1" to "<http://example.org/s4>",
                                "p1" to "<http://example.org/p>",
                                "z" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s3>",
                                "p" to "<http://example.org/p>",
                                "o" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s1" to "<http://example.org/s4>",
                                "p1" to "<http://example.org/p>",
                                "z" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s4>",
                                "p" to "<http://example.org/p>",
                                "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s1" to "<http://example.org/s4>",
                                "p1" to "<http://example.org/p>",
                                "z" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s1>",
                                "p" to "<http://example.org/p>",
                                "o" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s1" to "_:1",
                                "p1" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "z" to "<http://www.w3.org/2002/07/owl#Ontology>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s2>",
                                "p" to "<http://example.org/p>",
                                "o" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s1" to "_:1",
                                "p1" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "z" to "<http://www.w3.org/2002/07/owl#Ontology>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s3>",
                                "p" to "<http://example.org/p>",
                                "o" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s1" to "_:1",
                                "p1" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "z" to "<http://www.w3.org/2002/07/owl#Ontology>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s4>",
                                "p" to "<http://example.org/p>",
                                "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s1" to "_:1",
                                "p1" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "z" to "<http://www.w3.org/2002/07/owl#Ontology>"
                            )
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/bind03.rq */
            /* {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o",
                                "s1",
                                "p1",
                                "z"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example.org/s1>",
                                    "p" to "<http://example.org/p>",
                                    "o" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "s1" to "<http://example.org/p>",
                                    "p1" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "z" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s2>",
                                    "p" to "<http://example.org/p>",
                                    "o" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "s1" to "<http://example.org/p>",
                                    "p1" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "z" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s3>",
                                    "p" to "<http://example.org/p>",
                                    "o" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "s1" to "<http://example.org/p>",
                                    "p1" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "z" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s4>",
                                    "p" to "<http://example.org/p>",
                                    "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "s1" to "<http://example.org/p>",
                                    "p1" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "z" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s1>",
                                    "p" to "<http://example.org/p>",
                                    "o" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "s1" to "<http://example.org/s1>",
                                    "p1" to "<http://example.org/p>",
                                    "z" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s2>",
                                    "p" to "<http://example.org/p>",
                                    "o" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "s1" to "<http://example.org/s1>",
                                    "p1" to "<http://example.org/p>",
                                    "z" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s3>",
                                    "p" to "<http://example.org/p>",
                                    "o" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "s1" to "<http://example.org/s1>",
                                    "p1" to "<http://example.org/p>",
                                    "z" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s4>",
                                    "p" to "<http://example.org/p>",
                                    "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "s1" to "<http://example.org/s1>",
                                    "p1" to "<http://example.org/p>",
                                    "z" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s1>",
                                    "p" to "<http://example.org/p>",
                                    "o" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "s1" to "<http://example.org/s2>",
                                    "p1" to "<http://example.org/p>",
                                    "z" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s2>",
                                    "p" to "<http://example.org/p>",
                                    "o" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "s1" to "<http://example.org/s2>",
                                    "p1" to "<http://example.org/p>",
                                    "z" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s3>",
                                    "p" to "<http://example.org/p>",
                                    "o" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "s1" to "<http://example.org/s2>",
                                    "p1" to "<http://example.org/p>",
                                    "z" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s4>",
                                    "p" to "<http://example.org/p>",
                                    "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "s1" to "<http://example.org/s2>",
                                    "p1" to "<http://example.org/p>",
                                    "z" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s1>",
                                    "p" to "<http://example.org/p>",
                                    "o" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "s1" to "<http://example.org/s3>",
                                    "p1" to "<http://example.org/p>",
                                    "z" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s2>",
                                    "p" to "<http://example.org/p>",
                                    "o" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "s1" to "<http://example.org/s3>",
                                    "p1" to "<http://example.org/p>",
                                    "z" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s3>",
                                    "p" to "<http://example.org/p>",
                                    "o" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "s1" to "<http://example.org/s3>",
                                    "p1" to "<http://example.org/p>",
                                    "z" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s4>",
                                    "p" to "<http://example.org/p>",
                                    "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "s1" to "<http://example.org/s3>",
                                    "p1" to "<http://example.org/p>",
                                    "z" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s1>",
                                    "p" to "<http://example.org/p>",
                                    "o" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "s1" to "<http://example.org/s4>",
                                    "p1" to "<http://example.org/p>",
                                    "z" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s2>",
                                    "p" to "<http://example.org/p>",
                                    "o" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "s1" to "<http://example.org/s4>",
                                    "p1" to "<http://example.org/p>",
                                    "z" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s3>",
                                    "p" to "<http://example.org/p>",
                                    "o" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "s1" to "<http://example.org/s4>",
                                    "p1" to "<http://example.org/p>",
                                    "z" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s4>",
                                    "p" to "<http://example.org/p>",
                                    "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "s1" to "<http://example.org/s4>",
                                    "p1" to "<http://example.org/p>",
                                    "z" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s1>",
                                    "p" to "<http://example.org/p>",
                                    "o" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "s1" to "_:1",
                                    "p1" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "z" to "<http://www.w3.org/2002/07/owl#Ontology>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s2>",
                                    "p" to "<http://example.org/p>",
                                    "o" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "s1" to "_:1",
                                    "p1" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "z" to "<http://www.w3.org/2002/07/owl#Ontology>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s3>",
                                    "p" to "<http://example.org/p>",
                                    "o" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "s1" to "_:1",
                                    "p1" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "z" to "<http://www.w3.org/2002/07/owl#Ontology>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s4>",
                                    "p" to "<http://example.org/p>",
                                    "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "s1" to "_:1",
                                    "p1" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "z" to "<http://www.w3.org/2002/07/owl#Ontology>"
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
                        false                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o",
                            "s1",
                            "p1",
                            "z"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/s1>",
                                "p" to "<http://example.org/p>",
                                "o" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s1" to "<http://example.org/s1>",
                                "p1" to "<http://example.org/p>",
                                "z" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s2>",
                                "p" to "<http://example.org/p>",
                                "o" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s1" to "<http://example.org/s1>",
                                "p1" to "<http://example.org/p>",
                                "z" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s3>",
                                "p" to "<http://example.org/p>",
                                "o" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s1" to "<http://example.org/s1>",
                                "p1" to "<http://example.org/p>",
                                "z" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s4>",
                                "p" to "<http://example.org/p>",
                                "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s1" to "<http://example.org/s1>",
                                "p1" to "<http://example.org/p>",
                                "z" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s1>",
                                "p" to "<http://example.org/p>",
                                "o" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s1" to "<http://example.org/s2>",
                                "p1" to "<http://example.org/p>",
                                "z" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s2>",
                                "p" to "<http://example.org/p>",
                                "o" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s1" to "<http://example.org/s2>",
                                "p1" to "<http://example.org/p>",
                                "z" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s3>",
                                "p" to "<http://example.org/p>",
                                "o" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s1" to "<http://example.org/s2>",
                                "p1" to "<http://example.org/p>",
                                "z" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s4>",
                                "p" to "<http://example.org/p>",
                                "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s1" to "<http://example.org/s2>",
                                "p1" to "<http://example.org/p>",
                                "z" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s1>",
                                "p" to "<http://example.org/p>",
                                "o" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s1" to "<http://example.org/s3>",
                                "p1" to "<http://example.org/p>",
                                "z" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s2>",
                                "p" to "<http://example.org/p>",
                                "o" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s1" to "<http://example.org/s3>",
                                "p1" to "<http://example.org/p>",
                                "z" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s3>",
                                "p" to "<http://example.org/p>",
                                "o" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s1" to "<http://example.org/s3>",
                                "p1" to "<http://example.org/p>",
                                "z" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s4>",
                                "p" to "<http://example.org/p>",
                                "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s1" to "<http://example.org/s3>",
                                "p1" to "<http://example.org/p>",
                                "z" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s1>",
                                "p" to "<http://example.org/p>",
                                "o" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s1" to "<http://example.org/s4>",
                                "p1" to "<http://example.org/p>",
                                "z" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s2>",
                                "p" to "<http://example.org/p>",
                                "o" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s1" to "<http://example.org/s4>",
                                "p1" to "<http://example.org/p>",
                                "z" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s3>",
                                "p" to "<http://example.org/p>",
                                "o" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s1" to "<http://example.org/s4>",
                                "p1" to "<http://example.org/p>",
                                "z" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s4>",
                                "p" to "<http://example.org/p>",
                                "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s1" to "<http://example.org/s4>",
                                "p1" to "<http://example.org/p>",
                                "z" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            )
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/bind03.rq */
            /* {
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
            }() */ /* resources/sparql11-test-suite/entailment/sparqldl-02.rq */
            /* {
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
            }() */ /* resources/sparql11-test-suite/entailment/sparqldl-03.rq */
            /* {
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
            }() */ /* resources/sparql11-test-suite/entailment/sparqldl-03.rq */
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
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/test#a>", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://example.org/test#Person>"))
                                    graph.addData(1L, listOf("<http://example.org/test#b>", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://example.org/test#Person>"))
                                    graph.addData(1L, listOf("<http://example.org/test#c>", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://example.org/test#Person>"))
                                    DistributedTripleStore.commit(1L)
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "X", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://example.org/test#Person>", false, true, true, EIndexPattern.SPO)
                                }()
                                ,
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/test#a>", "<http://example.org/test#name>", "\"A\""))
                                    graph.addData(1L, listOf("<http://example.org/test#b>", "<http://example.org/test#name>", "\"B\""))
                                    DistributedTripleStore.commit(1L)
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "X", "<http://example.org/test#name>", "Y1", false, true, false, EIndexPattern.SPO)
                                }()
                                ,
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
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPJoinHashMap(
                                dictionary,
                                POPJoinHashMap(
                                        dictionary,
                                        {
                                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                            val graph = DistributedTripleStore.createGraph(graphName)
                                            graph.addData(1L, listOf("<http://example.org/test#a>", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://example.org/test#Person>"))
                                            graph.addData(1L, listOf("<http://example.org/test#b>", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://example.org/test#Person>"))
                                            graph.addData(1L, listOf("<http://example.org/test#c>", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://example.org/test#Person>"))
                                            DistributedTripleStore.commit(1L)
                                            TripleStoreIteratorGlobal(1L, dictionary, graphName, "X", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://example.org/test#Person>", false, true, true, EIndexPattern.SPO)
                                        }()
                                        ,
                                        {
                                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                            val graph = DistributedTripleStore.createGraph(graphName)
                                            graph.addData(1L, listOf("<http://example.org/test#a>", "<http://example.org/test#name>", "\"A\""))
                                            graph.addData(1L, listOf("<http://example.org/test#b>", "<http://example.org/test#name>", "\"B\""))
                                            DistributedTripleStore.commit(1L)
                                            TripleStoreIteratorGlobal(1L, dictionary, graphName, "X", "<http://example.org/test#name>", "Y1", false, true, false, EIndexPattern.SPO)
                                        }()
                                        ,
                                        false)
                                ,
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/test#a>", "<http://example.org/test#nick>", "\"Anick\""))
                                    graph.addData(1L, listOf("<http://example.org/test#b>", "<http://example.org/test#nick>", "\"Bnick\""))
                                    DistributedTripleStore.commit(1L)
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "X", "<http://example.org/test#nick>", "Y2", false, true, false, EIndexPattern.SPO)
                                }()
                                ,
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
            /* {
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
            }() */ /* resources/sparql11-test-suite/entailment/sparqldl-06.rq */
            /* {
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
            }() */ /* resources/sparql11-test-suite/entailment/sparqldl-06.rq */
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
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/test#a>", "<http://example.org/test#p>", "<http://example.org/test#aa>"))
                                    DistributedTripleStore.commit(1L)
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "<http://example.org/test#a>", "<http://example.org/test#p>", "#aa", true, true, false, EIndexPattern.SPO)
                                }()
                                ,
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/test#dd>", "<http://example.org/test#t>", "<http://example.org/test#bb>"))
                                    DistributedTripleStore.commit(1L)
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "X", "<http://example.org/test#t>", "Y", false, true, false, EIndexPattern.SPO)
                                }()
                                ,
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
                                POPJoinHashMap(
                                        dictionary,
                                        {
                                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                            val graph = DistributedTripleStore.createGraph(graphName)
                                            graph.addData(1L, listOf("<http://example.org/test#a>", "<http://example.org/test#p>", "<http://example.org/test#aa>"))
                                            DistributedTripleStore.commit(1L)
                                            TripleStoreIteratorGlobal(1L, dictionary, graphName, "<http://example.org/test#a>", "<http://example.org/test#p>", "#aa", true, true, false, EIndexPattern.SPO)
                                        }()
                                        ,
                                        {
                                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                            val graph = DistributedTripleStore.createGraph(graphName)
                                            graph.addData(1L, listOf("<http://example.org/test#dd>", "<http://example.org/test#t>", "<http://example.org/test#bb>"))
                                            DistributedTripleStore.commit(1L)
                                            TripleStoreIteratorGlobal(1L, dictionary, graphName, "X", "<http://example.org/test#t>", "Y", false, true, false, EIndexPattern.SPO)
                                        }()
                                        ,
                                        false)
                                ,
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/test#bb>", "<http://example.org/test#s>", "<http://example.org/test#aa>"))
                                    DistributedTripleStore.commit(1L)
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "Y", "<http://example.org/test#s>", "#aa", false, true, false, EIndexPattern.SPO)
                                }()
                                ,
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
                                POPJoinHashMap(
                                        dictionary,
                                        POPJoinHashMap(
                                                dictionary,
                                                {
                                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                    val graph = DistributedTripleStore.createGraph(graphName)
                                                    graph.addData(1L, listOf("<http://example.org/test#a>", "<http://example.org/test#p>", "<http://example.org/test#aa>"))
                                                    DistributedTripleStore.commit(1L)
                                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "<http://example.org/test#a>", "<http://example.org/test#p>", "#aa", true, true, false, EIndexPattern.SPO)
                                                }()
                                                ,
                                                {
                                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                    val graph = DistributedTripleStore.createGraph(graphName)
                                                    graph.addData(1L, listOf("<http://example.org/test#dd>", "<http://example.org/test#t>", "<http://example.org/test#bb>"))
                                                    DistributedTripleStore.commit(1L)
                                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "X", "<http://example.org/test#t>", "Y", false, true, false, EIndexPattern.SPO)
                                                }()
                                                ,
                                                false)
                                        ,
                                        {
                                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                            val graph = DistributedTripleStore.createGraph(graphName)
                                            graph.addData(1L, listOf("<http://example.org/test#bb>", "<http://example.org/test#s>", "<http://example.org/test#aa>"))
                                            DistributedTripleStore.commit(1L)
                                            TripleStoreIteratorGlobal(1L, dictionary, graphName, "Y", "<http://example.org/test#s>", "#aa", false, true, false, EIndexPattern.SPO)
                                        }()
                                        ,
                                        false)
                                ,
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/test#aa>", "<http://example.org/test#r>", "<http://example.org/test#ee>"))
                                    graph.addData(1L, listOf("<http://example.org/test#cc>", "<http://example.org/test#r>", "<http://example.org/test#dd>"))
                                    DistributedTripleStore.commit(1L)
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "#aa", "<http://example.org/test#r>", "Z", false, true, false, EIndexPattern.SPO)
                                }()
                                ,
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
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/test#a>", "<http://example.org/test#p>", "<http://example.org/test#aa>"))
                                    DistributedTripleStore.commit(1L)
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "X", "<http://example.org/test#p>", "#a", false, true, false, EIndexPattern.SPO)
                                }()
                                ,
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/test#aa>", "<http://example.org/test#r>", "<http://example.org/test#ee>"))
                                    graph.addData(1L, listOf("<http://example.org/test#cc>", "<http://example.org/test#r>", "<http://example.org/test#dd>"))
                                    DistributedTripleStore.commit(1L)
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "#a", "<http://example.org/test#r>", "Y", false, true, false, EIndexPattern.SPO)
                                }()
                                ,
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
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPJoinHashMap(
                                dictionary,
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/test#a>", "<http://example.org/test#p>", "<http://example.org/test#b>"))
                                    DistributedTripleStore.commit(1L)
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "X", "<http://example.org/test#p>", "#a", false, true, false, EIndexPattern.SPO)
                                }()
                                ,
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/test#b>", "<http://example.org/test#q>", "<http://example.org/test#c>"))
                                    graph.addData(1L, listOf("<http://example.org/test#b>", "<http://example.org/test#q>", "<http://example.org/test#h>"))
                                    graph.addData(1L, listOf("<http://example.org/test#b>", "<http://example.org/test#q>", "<http://example.org/test#i>"))
                                    graph.addData(1L, listOf("<http://example.org/test#x>", "<http://example.org/test#q>", "<http://example.org/test#x>"))
                                    DistributedTripleStore.commit(1L)
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "#a", "<http://example.org/test#q>", "Y", false, true, false, EIndexPattern.SPO)
                                }()
                                ,
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
            /* {
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
            }() */ /* resources/sparql11-test-suite/entailment/sparqldl-10.rq */
            /* {
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
            }() */ /* resources/sparql11-test-suite/entailment/sparqldl-10.rq */
            /* {
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
            }() */ /* resources/sparql11-test-suite/entailment/sparqldl-13.rq */
            /* {
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
            }() */ /* resources/sparql11-test-suite/entailment/sparqldl-13.rq */
            /* {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#_18589"
                            ), listOf(
                                mutableMapOf(
                                    "#_18589" to "_:_18574"
                                ),
                                mutableMapOf(
                                    "#_18589" to "_:_18575"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_18589"
                            ), listOf(
                                mutableMapOf(
                                    "#_18589" to "_:_18574"
                                ),
                                mutableMapOf(
                                    "#_18589" to "_:_18575"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_18589"
                        ), listOf(
                            mutableMapOf(
                                "#_18589" to "_:_18574"
                            ),
                            mutableMapOf(
                                "#_18589" to "_:_18575"
                            )
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/parent3.rq */
            /* {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#_18589"
                            ), listOf(
                                mutableMapOf(
                                    "#_18589" to "_:_18574"
                                ),
                                mutableMapOf(
                                    "#_18589" to "_:_18575"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_18589"
                            ), listOf(
                                mutableMapOf(
                                    "#_18589" to "_:_18574"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_18589"
                        ), listOf(
                            mutableMapOf(
                                "#_18589" to "_:_18574"
                            )
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/parent3.rq */
            /* {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#_18589"
                            ), listOf(
                                mutableMapOf(
                                    "#_18589" to "_:_18574"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "parent",
                                "#_18589"
                            ), listOf(
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Alice>",
                                    "#_18589" to "<http://example.org/test#Female>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Alice>",
                                    "#_18589" to "<http://example.org/test#Parent>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Alice>",
                                    "#_18589" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Bob>",
                                    "#_18589" to "<http://example.org/test#Male>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Bob>",
                                    "#_18589" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Charlie>",
                                    "#_18589" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Dudley>",
                                    "#_18589" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Dudley>",
                                    "#_18589" to "_:_18575"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Father>",
                                    "#_18589" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Female>",
                                    "#_18589" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Male>",
                                    "#_18589" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Mother>",
                                    "#_18589" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Parent>",
                                    "#_18589" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#hasChild>",
                                    "#_18589" to "<http://www.w3.org/2002/07/owl#ObjectProperty>"
                                ),
                                mutableMapOf(
                                    "parent" to "_:_18568",
                                    "#_18589" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "_:_18571",
                                    "#_18589" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "_:_18574",
                                    "#_18589" to "<http://www.w3.org/2002/07/owl#Restriction>"
                                ),
                                mutableMapOf(
                                    "parent" to "_:_18575",
                                    "#_18589" to "<http://www.w3.org/2002/07/owl#Restriction>"
                                ),
                                mutableMapOf(
                                    "parent" to "_:_18576",
                                    "#_18589" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "_:ont",
                                    "#_18589" to "<http://www.w3.org/2002/07/owl#Ontology>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_18589",
                            "parent"
                        ), listOf(
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/parent3.rq */
            /* {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#_18679"
                            ), listOf(
                                mutableMapOf(
                                    "#_18679" to "_:_18661"
                                ),
                                mutableMapOf(
                                    "#_18679" to "_:_18662"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_18679"
                            ), listOf(
                                mutableMapOf(
                                    "#_18679" to "_:_18661"
                                ),
                                mutableMapOf(
                                    "#_18679" to "_:_18662"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_18679"
                        ), listOf(
                            mutableMapOf(
                                "#_18679" to "_:_18661"
                            ),
                            mutableMapOf(
                                "#_18679" to "_:_18662"
                            )
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/parent4.rq */
            /* {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#_18679"
                            ), listOf(
                                mutableMapOf(
                                    "#_18679" to "_:_18661"
                                ),
                                mutableMapOf(
                                    "#_18679" to "_:_18662"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_18679"
                            ), listOf(
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_18679"
                        ), listOf(
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/parent4.rq */
            /* {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#_18679"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "parent",
                                "#_18679"
                            ), listOf(
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Alice>",
                                    "#_18679" to "<http://example.org/test#Female>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Alice>",
                                    "#_18679" to "<http://example.org/test#Parent>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Alice>",
                                    "#_18679" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Bob>",
                                    "#_18679" to "<http://example.org/test#Male>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Bob>",
                                    "#_18679" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Charlie>",
                                    "#_18679" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Dudley>",
                                    "#_18679" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Dudley>",
                                    "#_18679" to "_:_18662"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Father>",
                                    "#_18679" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Female>",
                                    "#_18679" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Male>",
                                    "#_18679" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Mother>",
                                    "#_18679" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Parent>",
                                    "#_18679" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#hasChild>",
                                    "#_18679" to "<http://www.w3.org/2002/07/owl#ObjectProperty>"
                                ),
                                mutableMapOf(
                                    "parent" to "_:_18655",
                                    "#_18679" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "_:_18658",
                                    "#_18679" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "_:_18661",
                                    "#_18679" to "<http://www.w3.org/2002/07/owl#Restriction>"
                                ),
                                mutableMapOf(
                                    "parent" to "_:_18662",
                                    "#_18679" to "<http://www.w3.org/2002/07/owl#Restriction>"
                                ),
                                mutableMapOf(
                                    "parent" to "_:_18663",
                                    "#_18679" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "_:ont",
                                    "#_18679" to "<http://www.w3.org/2002/07/owl#Ontology>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_18679",
                            "parent"
                        ), listOf(
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/parent4.rq */
            /* {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#_18771"
                            ), listOf(
                                mutableMapOf(
                                    "#_18771" to "_:_18756"
                                ),
                                mutableMapOf(
                                    "#_18771" to "_:_18757"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_18771"
                            ), listOf(
                                mutableMapOf(
                                    "#_18771" to "_:_18756"
                                ),
                                mutableMapOf(
                                    "#_18771" to "_:_18757"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_18771"
                        ), listOf(
                            mutableMapOf(
                                "#_18771" to "_:_18756"
                            ),
                            mutableMapOf(
                                "#_18771" to "_:_18757"
                            )
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/parent5.rq */
            /* {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#_18771"
                            ), listOf(
                                mutableMapOf(
                                    "#_18771" to "_:_18756"
                                ),
                                mutableMapOf(
                                    "#_18771" to "_:_18757"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_18771"
                            ), listOf(
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_18771"
                        ), listOf(
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/parent5.rq */
            /* {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#_18771"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "parent",
                                "#_18771"
                            ), listOf(
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Alice>",
                                    "#_18771" to "<http://example.org/test#Female>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Alice>",
                                    "#_18771" to "<http://example.org/test#Parent>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Alice>",
                                    "#_18771" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Bob>",
                                    "#_18771" to "<http://example.org/test#Male>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Bob>",
                                    "#_18771" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Charlie>",
                                    "#_18771" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Dudley>",
                                    "#_18771" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Dudley>",
                                    "#_18771" to "_:_18757"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Father>",
                                    "#_18771" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Female>",
                                    "#_18771" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Male>",
                                    "#_18771" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Mother>",
                                    "#_18771" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Parent>",
                                    "#_18771" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#hasChild>",
                                    "#_18771" to "<http://www.w3.org/2002/07/owl#ObjectProperty>"
                                ),
                                mutableMapOf(
                                    "parent" to "_:_18750",
                                    "#_18771" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "_:_18753",
                                    "#_18771" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "_:_18756",
                                    "#_18771" to "<http://www.w3.org/2002/07/owl#Restriction>"
                                ),
                                mutableMapOf(
                                    "parent" to "_:_18757",
                                    "#_18771" to "<http://www.w3.org/2002/07/owl#Restriction>"
                                ),
                                mutableMapOf(
                                    "parent" to "_:_18758",
                                    "#_18771" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "_:ont",
                                    "#_18771" to "<http://www.w3.org/2002/07/owl#Ontology>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_18771",
                            "parent"
                        ), listOf(
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/parent5.rq */
            /* {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#_18859"
                            ), listOf(
                                mutableMapOf(
                                    "#_18859" to "_:_18843"
                                ),
                                mutableMapOf(
                                    "#_18859" to "_:_18844"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_18859"
                            ), listOf(
                                mutableMapOf(
                                    "#_18859" to "_:_18843"
                                ),
                                mutableMapOf(
                                    "#_18859" to "_:_18844"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_18859"
                        ), listOf(
                            mutableMapOf(
                                "#_18859" to "_:_18843"
                            ),
                            mutableMapOf(
                                "#_18859" to "_:_18844"
                            )
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/parent6.rq */
            /* {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#_18859"
                            ), listOf(
                                mutableMapOf(
                                    "#_18859" to "_:_18843"
                                ),
                                mutableMapOf(
                                    "#_18859" to "_:_18844"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_18859"
                            ), listOf(
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_18859"
                        ), listOf(
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/parent6.rq */
            /* {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#_18859"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "parent",
                                "#_18859"
                            ), listOf(
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Alice>",
                                    "#_18859" to "<http://example.org/test#Female>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Alice>",
                                    "#_18859" to "<http://example.org/test#Parent>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Alice>",
                                    "#_18859" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Bob>",
                                    "#_18859" to "<http://example.org/test#Male>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Bob>",
                                    "#_18859" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Charlie>",
                                    "#_18859" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Dudley>",
                                    "#_18859" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Dudley>",
                                    "#_18859" to "_:_18844"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Father>",
                                    "#_18859" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Female>",
                                    "#_18859" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Male>",
                                    "#_18859" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Mother>",
                                    "#_18859" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Parent>",
                                    "#_18859" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#hasChild>",
                                    "#_18859" to "<http://www.w3.org/2002/07/owl#ObjectProperty>"
                                ),
                                mutableMapOf(
                                    "parent" to "_:_18837",
                                    "#_18859" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "_:_18840",
                                    "#_18859" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "_:_18843",
                                    "#_18859" to "<http://www.w3.org/2002/07/owl#Restriction>"
                                ),
                                mutableMapOf(
                                    "parent" to "_:_18844",
                                    "#_18859" to "<http://www.w3.org/2002/07/owl#Restriction>"
                                ),
                                mutableMapOf(
                                    "parent" to "_:_18845",
                                    "#_18859" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "_:ont",
                                    "#_18859" to "<http://www.w3.org/2002/07/owl#Ontology>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_18859",
                            "parent"
                        ), listOf(
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/parent6.rq */
            /* {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#_18963"
                            ), listOf(
                                mutableMapOf(
                                    "#_18963" to "_:_18947"
                                ),
                                mutableMapOf(
                                    "#_18963" to "_:_18948"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_18963"
                            ), listOf(
                                mutableMapOf(
                                    "#_18963" to "_:_18947"
                                ),
                                mutableMapOf(
                                    "#_18963" to "_:_18948"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_18963"
                        ), listOf(
                            mutableMapOf(
                                "#_18963" to "_:_18947"
                            ),
                            mutableMapOf(
                                "#_18963" to "_:_18948"
                            )
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/parent7.rq */
            /* {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#_18963"
                            ), listOf(
                                mutableMapOf(
                                    "#_18963" to "_:_18947"
                                ),
                                mutableMapOf(
                                    "#_18963" to "_:_18948"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_18963"
                            ), listOf(
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_18963"
                        ), listOf(
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/parent7.rq */
            /* {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#_18963"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "parent",
                                "#_18963"
                            ), listOf(
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Alice>",
                                    "#_18963" to "<http://example.org/test#Female>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Alice>",
                                    "#_18963" to "<http://example.org/test#Parent>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Alice>",
                                    "#_18963" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Bob>",
                                    "#_18963" to "<http://example.org/test#Male>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Bob>",
                                    "#_18963" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Charlie>",
                                    "#_18963" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Dudley>",
                                    "#_18963" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Dudley>",
                                    "#_18963" to "_:_18948"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Father>",
                                    "#_18963" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Female>",
                                    "#_18963" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Male>",
                                    "#_18963" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Mother>",
                                    "#_18963" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Parent>",
                                    "#_18963" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#hasChild>",
                                    "#_18963" to "<http://www.w3.org/2002/07/owl#ObjectProperty>"
                                ),
                                mutableMapOf(
                                    "parent" to "_:_18941",
                                    "#_18963" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "_:_18944",
                                    "#_18963" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "_:_18947",
                                    "#_18963" to "<http://www.w3.org/2002/07/owl#Restriction>"
                                ),
                                mutableMapOf(
                                    "parent" to "_:_18948",
                                    "#_18963" to "<http://www.w3.org/2002/07/owl#Restriction>"
                                ),
                                mutableMapOf(
                                    "parent" to "_:_18949",
                                    "#_18963" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "_:ont",
                                    "#_18963" to "<http://www.w3.org/2002/07/owl#Ontology>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_18963",
                            "parent"
                        ), listOf(
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/parent7.rq */
            /* {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#_19067"
                            ), listOf(
                                mutableMapOf(
                                    "#_19067" to "_:_19051"
                                ),
                                mutableMapOf(
                                    "#_19067" to "_:_19052"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_19067"
                            ), listOf(
                                mutableMapOf(
                                    "#_19067" to "_:_19051"
                                ),
                                mutableMapOf(
                                    "#_19067" to "_:_19052"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_19067"
                        ), listOf(
                            mutableMapOf(
                                "#_19067" to "_:_19051"
                            ),
                            mutableMapOf(
                                "#_19067" to "_:_19052"
                            )
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/parent8.rq */
            /* {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#_19067"
                            ), listOf(
                                mutableMapOf(
                                    "#_19067" to "_:_19051"
                                ),
                                mutableMapOf(
                                    "#_19067" to "_:_19052"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_19067"
                            ), listOf(
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_19067"
                        ), listOf(
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/parent8.rq */
            /* {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#_19067"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "parent",
                                "#_19067"
                            ), listOf(
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Alice>",
                                    "#_19067" to "<http://example.org/test#Female>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Alice>",
                                    "#_19067" to "<http://example.org/test#Parent>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Alice>",
                                    "#_19067" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Bob>",
                                    "#_19067" to "<http://example.org/test#Male>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Bob>",
                                    "#_19067" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Charlie>",
                                    "#_19067" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Dudley>",
                                    "#_19067" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Dudley>",
                                    "#_19067" to "_:_19052"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Father>",
                                    "#_19067" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Female>",
                                    "#_19067" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Male>",
                                    "#_19067" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Mother>",
                                    "#_19067" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Parent>",
                                    "#_19067" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#hasChild>",
                                    "#_19067" to "<http://www.w3.org/2002/07/owl#ObjectProperty>"
                                ),
                                mutableMapOf(
                                    "parent" to "_:_19045",
                                    "#_19067" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "_:_19048",
                                    "#_19067" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "_:_19051",
                                    "#_19067" to "<http://www.w3.org/2002/07/owl#Restriction>"
                                ),
                                mutableMapOf(
                                    "parent" to "_:_19052",
                                    "#_19067" to "<http://www.w3.org/2002/07/owl#Restriction>"
                                ),
                                mutableMapOf(
                                    "parent" to "_:_19053",
                                    "#_19067" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "_:ont",
                                    "#_19067" to "<http://www.w3.org/2002/07/owl#Ontology>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_19067",
                            "parent"
                        ), listOf(
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/parent8.rq */
            /* {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#_19172"
                            ), listOf(
                                mutableMapOf(
                                    "#_19172" to "_:_19155"
                                ),
                                mutableMapOf(
                                    "#_19172" to "_:_19156"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_19172"
                            ), listOf(
                                mutableMapOf(
                                    "#_19172" to "_:_19155"
                                ),
                                mutableMapOf(
                                    "#_19172" to "_:_19156"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_19172"
                        ), listOf(
                            mutableMapOf(
                                "#_19172" to "_:_19155"
                            ),
                            mutableMapOf(
                                "#_19172" to "_:_19156"
                            )
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/parent9.rq */
            /* {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#_19172"
                            ), listOf(
                                mutableMapOf(
                                    "#_19172" to "_:_19155"
                                ),
                                mutableMapOf(
                                    "#_19172" to "_:_19156"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_19172"
                            ), listOf(
                                mutableMapOf(
                                    "#_19172" to "_:_19155"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_19172"
                        ), listOf(
                            mutableMapOf(
                                "#_19172" to "_:_19155"
                            )
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/parent9.rq */
            /* {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#_19172"
                            ), listOf(
                                mutableMapOf(
                                    "#_19172" to "_:_19155"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "C",
                                "#_19172"
                            ), listOf(
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_19172",
                            "C"
                        ), listOf(
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/parent9.rq */
            /* {
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
                                    "#b" to "_:_19246"
                                ),
                                mutableMapOf(
                                    "#b" to "_:_19247"
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
            }() */ /* resources/sparql11-test-suite/entailment/parent10.rq */
            /* {
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
                                    "#b" to "_:_19246"
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
            }() */ /* resources/sparql11-test-suite/entailment/parent10.rq */
            /* {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#_19360",
                                "#_19365",
                                "#_19357"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "#_19357"
                            ), listOf(
                                mutableMapOf(
                                    "x" to "<http://example.org/test#A>",
                                    "#_19357" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#B>",
                                    "#_19357" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#C>",
                                    "#_19357" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#a>",
                                    "#_19357" to "<http://example.org/test#A>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#a>",
                                    "#_19357" to "<http://example.org/test#B>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#b>",
                                    "#_19357" to "<http://example.org/test#B>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#c>",
                                    "#_19357" to "<http://example.org/test#C>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#d>",
                                    "#_19357" to "<http://example.org/test#A>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#d>",
                                    "#_19357" to "<http://example.org/test#B>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#d>",
                                    "#_19357" to "<http://example.org/test#C>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#p>",
                                    "#_19357" to "<http://www.w3.org/2002/07/owl#FunctionalProperty>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#p>",
                                    "#_19357" to "<http://www.w3.org/2002/07/owl#ObjectProperty>"
                                ),
                                mutableMapOf(
                                    "x" to "_:simple",
                                    "#_19357" to "<http://www.w3.org/2002/07/owl#Ontology>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_19360",
                            "#_19365",
                            "#_19357",
                            "x"
                        ), listOf(
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/simple1.rq */
            /* {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#_19465",
                                "#_19469",
                                "#_19479",
                                "#_19462"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "#_19462"
                            ), listOf(
                                mutableMapOf(
                                    "x" to "<http://example.org/test#A>",
                                    "#_19462" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#B>",
                                    "#_19462" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#C>",
                                    "#_19462" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#a>",
                                    "#_19462" to "<http://example.org/test#A>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#a>",
                                    "#_19462" to "<http://example.org/test#B>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#b>",
                                    "#_19462" to "<http://example.org/test#B>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#c>",
                                    "#_19462" to "<http://example.org/test#C>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#d>",
                                    "#_19462" to "<http://example.org/test#A>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#d>",
                                    "#_19462" to "<http://example.org/test#B>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#d>",
                                    "#_19462" to "<http://example.org/test#C>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#p>",
                                    "#_19462" to "<http://www.w3.org/2002/07/owl#FunctionalProperty>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#p>",
                                    "#_19462" to "<http://www.w3.org/2002/07/owl#ObjectProperty>"
                                ),
                                mutableMapOf(
                                    "x" to "_:simple",
                                    "#_19462" to "<http://www.w3.org/2002/07/owl#Ontology>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_19465",
                            "#_19469",
                            "#_19479",
                            "#_19462",
                            "x"
                        ), listOf(
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/simple2.rq */
            /* {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#_19606",
                                "#_19617",
                                "#_19622",
                                "#_19614"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "#_19606"
                            ), listOf(
                                mutableMapOf(
                                    "x" to "<http://example.org/test#A>",
                                    "#_19606" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#B>",
                                    "#_19606" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#C>",
                                    "#_19606" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#a>",
                                    "#_19606" to "<http://example.org/test#A>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#a>",
                                    "#_19606" to "<http://example.org/test#B>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#b>",
                                    "#_19606" to "<http://example.org/test#B>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#c>",
                                    "#_19606" to "<http://example.org/test#C>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#d>",
                                    "#_19606" to "<http://example.org/test#A>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#d>",
                                    "#_19606" to "<http://example.org/test#B>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#d>",
                                    "#_19606" to "<http://example.org/test#C>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#p>",
                                    "#_19606" to "<http://www.w3.org/2002/07/owl#FunctionalProperty>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#p>",
                                    "#_19606" to "<http://www.w3.org/2002/07/owl#ObjectProperty>"
                                ),
                                mutableMapOf(
                                    "x" to "_:simple",
                                    "#_19606" to "<http://www.w3.org/2002/07/owl#Ontology>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_19606",
                            "#_19617",
                            "#_19622",
                            "#_19614",
                            "x"
                        ), listOf(
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/simple3.rq */
            /* {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#_19750"
                            ), listOf(
                                mutableMapOf(
                                    "#_19750" to "<http://example.org/test#A>"
                                ),
                                mutableMapOf(
                                    "#_19750" to "<http://example.org/test#B>"
                                ),
                                mutableMapOf(
                                    "#_19750" to "<http://example.org/test#C>"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_19755"
                            ), listOf(
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_19750",
                            "#_19755"
                        ), listOf(
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/simple4.rq */
            /* {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#_19750",
                                "#_19755",
                                "#_19760"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "#_19750"
                            ), listOf(
                                mutableMapOf(
                                    "x" to "<http://example.org/test#A>",
                                    "#_19750" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#B>",
                                    "#_19750" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#C>",
                                    "#_19750" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#a>",
                                    "#_19750" to "<http://example.org/test#A>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#a>",
                                    "#_19750" to "<http://example.org/test#B>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#b>",
                                    "#_19750" to "<http://example.org/test#B>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#c>",
                                    "#_19750" to "<http://example.org/test#C>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#d>",
                                    "#_19750" to "<http://example.org/test#A>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#d>",
                                    "#_19750" to "<http://example.org/test#B>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#d>",
                                    "#_19750" to "<http://example.org/test#C>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#p>",
                                    "#_19750" to "<http://www.w3.org/2002/07/owl#FunctionalProperty>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#p>",
                                    "#_19750" to "<http://www.w3.org/2002/07/owl#ObjectProperty>"
                                ),
                                mutableMapOf(
                                    "x" to "_:simple",
                                    "#_19750" to "<http://www.w3.org/2002/07/owl#Ontology>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_19750",
                            "#_19755",
                            "#_19760",
                            "x"
                        ), listOf(
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/simple4.rq */
            /* {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#_19867"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_19875"
                            ), listOf(
                                mutableMapOf(
                                    "#_19875" to "<http://example.org/test#A>"
                                ),
                                mutableMapOf(
                                    "#_19875" to "<http://example.org/test#B>"
                                ),
                                mutableMapOf(
                                    "#_19875" to "<http://example.org/test#C>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_19867",
                            "#_19875"
                        ), listOf(
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/simple5.rq */
            /* {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#_19867",
                                "#_19875",
                                "#_19880",
                                "#_19885"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "#_19867"
                            ), listOf(
                                mutableMapOf(
                                    "x" to "<http://example.org/test#A>",
                                    "#_19867" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#B>",
                                    "#_19867" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#C>",
                                    "#_19867" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#a>",
                                    "#_19867" to "<http://example.org/test#A>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#a>",
                                    "#_19867" to "<http://example.org/test#B>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#b>",
                                    "#_19867" to "<http://example.org/test#B>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#c>",
                                    "#_19867" to "<http://example.org/test#C>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#d>",
                                    "#_19867" to "<http://example.org/test#A>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#d>",
                                    "#_19867" to "<http://example.org/test#B>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#d>",
                                    "#_19867" to "<http://example.org/test#C>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#p>",
                                    "#_19867" to "<http://www.w3.org/2002/07/owl#FunctionalProperty>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#p>",
                                    "#_19867" to "<http://www.w3.org/2002/07/owl#ObjectProperty>"
                                ),
                                mutableMapOf(
                                    "x" to "_:simple",
                                    "#_19867" to "<http://www.w3.org/2002/07/owl#Ontology>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_19867",
                            "#_19875",
                            "#_19880",
                            "#_19885",
                            "x"
                        ), listOf(
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/simple5.rq */
            /* {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#_20023"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_20031"
                            ), listOf(
                                mutableMapOf(
                                    "#_20031" to "<http://example.org/test#A>"
                                ),
                                mutableMapOf(
                                    "#_20031" to "<http://example.org/test#B>"
                                ),
                                mutableMapOf(
                                    "#_20031" to "<http://example.org/test#C>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_20023",
                            "#_20031"
                        ), listOf(
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/simple6.rq */
            /* {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#_20023",
                                "#_20031",
                                "#_20036",
                                "#_20041",
                                "#_20046"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "#_20023"
                            ), listOf(
                                mutableMapOf(
                                    "x" to "<http://example.org/test#A>",
                                    "#_20023" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#B>",
                                    "#_20023" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#C>",
                                    "#_20023" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#a>",
                                    "#_20023" to "<http://example.org/test#A>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#a>",
                                    "#_20023" to "<http://example.org/test#B>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#b>",
                                    "#_20023" to "<http://example.org/test#B>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#c>",
                                    "#_20023" to "<http://example.org/test#C>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#d>",
                                    "#_20023" to "<http://example.org/test#A>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#d>",
                                    "#_20023" to "<http://example.org/test#B>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#d>",
                                    "#_20023" to "<http://example.org/test#C>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#p>",
                                    "#_20023" to "<http://www.w3.org/2002/07/owl#FunctionalProperty>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#p>",
                                    "#_20023" to "<http://www.w3.org/2002/07/owl#ObjectProperty>"
                                ),
                                mutableMapOf(
                                    "x" to "_:simple",
                                    "#_20023" to "<http://www.w3.org/2002/07/owl#Ontology>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_20023",
                            "#_20031",
                            "#_20036",
                            "#_20041",
                            "#_20046",
                            "x"
                        ), listOf(
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/simple6.rq */
            /* {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#_20204"
                            ), listOf(
                                mutableMapOf(
                                    "#_20204" to "<http://example.org/test#A>"
                                ),
                                mutableMapOf(
                                    "#_20204" to "<http://example.org/test#B>"
                                ),
                                mutableMapOf(
                                    "#_20204" to "<http://example.org/test#C>"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_20209"
                            ), listOf(
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_20204",
                            "#_20209"
                        ), listOf(
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/simple7.rq */
            /* {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#_20204",
                                "#_20209"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_20213"
                            ), listOf(
                                mutableMapOf(
                                    "#_20213" to "<http://example.org/test#A>"
                                ),
                                mutableMapOf(
                                    "#_20213" to "<http://example.org/test#B>"
                                ),
                                mutableMapOf(
                                    "#_20213" to "<http://example.org/test#C>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_20204",
                            "#_20209",
                            "#_20213"
                        ), listOf(
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/simple7.rq */
            /* {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#_20204",
                                "#_20209",
                                "#_20213",
                                "#_20218",
                                "#_20223",
                                "#_20229"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "#_20204"
                            ), listOf(
                                mutableMapOf(
                                    "x" to "<http://example.org/test#A>",
                                    "#_20204" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#B>",
                                    "#_20204" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#C>",
                                    "#_20204" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#a>",
                                    "#_20204" to "<http://example.org/test#A>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#a>",
                                    "#_20204" to "<http://example.org/test#B>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#b>",
                                    "#_20204" to "<http://example.org/test#B>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#c>",
                                    "#_20204" to "<http://example.org/test#C>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#d>",
                                    "#_20204" to "<http://example.org/test#A>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#d>",
                                    "#_20204" to "<http://example.org/test#B>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#d>",
                                    "#_20204" to "<http://example.org/test#C>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#p>",
                                    "#_20204" to "<http://www.w3.org/2002/07/owl#FunctionalProperty>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#p>",
                                    "#_20204" to "<http://www.w3.org/2002/07/owl#ObjectProperty>"
                                ),
                                mutableMapOf(
                                    "x" to "_:simple",
                                    "#_20204" to "<http://www.w3.org/2002/07/owl#Ontology>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_20204",
                            "#_20209",
                            "#_20213",
                            "#_20218",
                            "#_20223",
                            "#_20229",
                            "x"
                        ), listOf(
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/simple7.rq */
            /* {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#_20396",
                                "#_20404"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "#_20396"
                            ), listOf(
                                mutableMapOf(
                                    "x" to "<http://example.org/test#A>",
                                    "#_20396" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#B>",
                                    "#_20396" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#C>",
                                    "#_20396" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#a>",
                                    "#_20396" to "<http://example.org/test#A>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#a>",
                                    "#_20396" to "<http://example.org/test#B>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#b>",
                                    "#_20396" to "<http://example.org/test#B>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#c>",
                                    "#_20396" to "<http://example.org/test#C>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#d>",
                                    "#_20396" to "<http://example.org/test#A>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#d>",
                                    "#_20396" to "<http://example.org/test#B>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#d>",
                                    "#_20396" to "<http://example.org/test#C>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#p>",
                                    "#_20396" to "<http://www.w3.org/2002/07/owl#FunctionalProperty>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#p>",
                                    "#_20396" to "<http://www.w3.org/2002/07/owl#ObjectProperty>"
                                ),
                                mutableMapOf(
                                    "x" to "_:simple",
                                    "#_20396" to "<http://www.w3.org/2002/07/owl#Ontology>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_20396",
                            "#_20404",
                            "x"
                        ), listOf(
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/simple8.rq */
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
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/s6>", "<http://example.org/str>", "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                    DistributedTripleStore.commit(1L)
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "<http://example.org/s6>", "<http://example.org/str>", "str1", true, true, false, EIndexPattern.SPO)
                                }()
                                ,
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/s7>", "<http://example.org/str>", "\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                    DistributedTripleStore.commit(1L)
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "<http://example.org/s7>", "<http://example.org/str>", "str2", true, true, false, EIndexPattern.SPO)
                                }()
                                ,
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
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/s1>", "<http://example.org/str>", "\"123\""))
                                    graph.addData(1L, listOf("<http://example.org/s2>", "<http://example.org/str>", "\"日本語\"@ja"))
                                    graph.addData(1L, listOf("<http://example.org/s3>", "<http://example.org/str>", "\"english\"@en"))
                                    graph.addData(1L, listOf("<http://example.org/s4>", "<http://example.org/str>", "\"français\"@fr"))
                                    graph.addData(1L, listOf("<http://example.org/s5>", "<http://example.org/str>", "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                    graph.addData(1L, listOf("<http://example.org/s6>", "<http://example.org/str>", "\"def\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                    graph.addData(1L, listOf("<http://example.org/s7>", "<http://example.org/str>", "\"7\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                    DistributedTripleStore.commit(1L)
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "s1", "<http://example.org/str>", "str1", false, true, false, EIndexPattern.SPO)
                                }()
                                ,
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/s1>", "<http://example.org/str>", "\"123\""))
                                    graph.addData(1L, listOf("<http://example.org/s2>", "<http://example.org/str>", "\"日本語\"@ja"))
                                    graph.addData(1L, listOf("<http://example.org/s3>", "<http://example.org/str>", "\"english\"@en"))
                                    graph.addData(1L, listOf("<http://example.org/s4>", "<http://example.org/str>", "\"français\"@fr"))
                                    graph.addData(1L, listOf("<http://example.org/s5>", "<http://example.org/str>", "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                    graph.addData(1L, listOf("<http://example.org/s6>", "<http://example.org/str>", "\"def\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                    graph.addData(1L, listOf("<http://example.org/s7>", "<http://example.org/str>", "\"7\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                    DistributedTripleStore.commit(1L)
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "s2", "<http://example.org/str>", "str2", false, true, false, EIndexPattern.SPO)
                                }()
                                ,
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
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example/x1>", "<http://example/p>", "\"a\""))
                                    graph.addData(1L, listOf("<http://example/x2>", "<http://example/p>", "_:b"))
                                    graph.addData(1L, listOf("<http://example/x3>", "<http://example/p>", "<http://example/a>"))
                                    graph.addData(1L, listOf("<http://example/x4>", "<http://example/p>", "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                    graph.addData(1L, listOf("<http://example/x5>", "<http://example/p>", "\"1.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                    graph.addData(1L, listOf("<http://example/x6>", "<http://example/p>", "\"1\""))
                                    graph.addData(1L, listOf("<http://example/x7>", "<http://example/p>", "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                    graph.addData(1L, listOf("<http://example/x8>", "<http://example/p>", "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                    DistributedTripleStore.commit(1L)
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "s", "<http://example/p>", "x", false, true, false, EIndexPattern.SPO)
                                }()
                                ,
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example/x1>", "<http://example/q>", "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                    graph.addData(1L, listOf("<http://example/x2>", "<http://example/q>", "\"1\""))
                                    graph.addData(1L, listOf("<http://example/x3>", "<http://example/q>", "\"1\""))
                                    graph.addData(1L, listOf("<http://example/x4>", "<http://example/q>", "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                    graph.addData(1L, listOf("<http://example/x5>", "<http://example/q>", "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                    graph.addData(1L, listOf("<http://example/x6>", "<http://example/q>", "\"2\""))
                                    graph.addData(1L, listOf("<http://example/x7>", "<http://example/q>", "\"2\""))
                                    graph.addData(1L, listOf("<http://example/x8>", "<http://example/q>", "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                    DistributedTripleStore.commit(1L)
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "s", "<http://example/q>", "y", false, true, false, EIndexPattern.SPO)
                                }()
                                ,
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
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/s1>", "<http://example.org/str>", "\"foo\""))
                                    graph.addData(1L, listOf("<http://example.org/s2>", "<http://example.org/str>", "\"bar\"@en"))
                                    graph.addData(1L, listOf("<http://example.org/s3>", "<http://example.org/str>", "\"BAZ\""))
                                    graph.addData(1L, listOf("<http://example.org/s4>", "<http://example.org/str>", "\"食べ物\""))
                                    graph.addData(1L, listOf("<http://example.org/s5>", "<http://example.org/str>", "\"100%\""))
                                    graph.addData(1L, listOf("<http://example.org/s6>", "<http://example.org/str>", "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                    graph.addData(1L, listOf("<http://example.org/s7>", "<http://example.org/str>", "\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                    DistributedTripleStore.commit(1L)
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "a", "<http://example.org/str>", "s1", false, true, false, EIndexPattern.SPO)
                                }()
                                ,
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/s1>", "<http://example.org/str>", "\"foo\""))
                                    graph.addData(1L, listOf("<http://example.org/s2>", "<http://example.org/str>", "\"bar\"@en"))
                                    graph.addData(1L, listOf("<http://example.org/s3>", "<http://example.org/str>", "\"BAZ\""))
                                    graph.addData(1L, listOf("<http://example.org/s4>", "<http://example.org/str>", "\"食べ物\""))
                                    graph.addData(1L, listOf("<http://example.org/s5>", "<http://example.org/str>", "\"100%\""))
                                    graph.addData(1L, listOf("<http://example.org/s6>", "<http://example.org/str>", "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                    graph.addData(1L, listOf("<http://example.org/s7>", "<http://example.org/str>", "\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                    DistributedTripleStore.commit(1L)
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "b", "<http://example.org/str>", "s2", false, true, false, EIndexPattern.SPO)
                                }()
                                ,
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
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example/s1>", "<http://example/p>", "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                    graph.addData(1L, listOf("<http://example/s2>", "<http://example/p>", "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                    DistributedTripleStore.commit(1L)
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "s", "<http://example/p>", "v", false, true, false, EIndexPattern.SPO)
                                }()
                                ,
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example/s1>", "<http://example/q>", "\"9\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                    DistributedTripleStore.commit(1L)
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "s", "<http://example/q>", "w", false, true, false, EIndexPattern.SPO)
                                }()
                                ,
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
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example/s1>", "<http://example/p>", "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                    graph.addData(1L, listOf("<http://example/s2>", "<http://example/p>", "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                    graph.addData(1L, listOf("<http://example/s3>", "<http://example/p>", "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                    DistributedTripleStore.commit(1L)
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "s", "<http://example/p>", "v", false, true, false, EIndexPattern.SPO)
                                }()
                                ,
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example/s1>", "<http://example/q>", "\"9\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                    DistributedTripleStore.commit(1L)
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "s", "<http://example/q>", "w", false, true, false, EIndexPattern.SPO)
                                }()
                                ,
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
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/s1>", "<http://example.org/p1>", "<http://example.org/s2>"))
                                    graph.addData(1L, listOf("<http://example.org/s2>", "<http://example.org/p2>", "\"foo\""))
                                    graph.addData(1L, listOf("<http://example.org/s3>", "<http://example.org/p2>", "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                    graph.addData(1L, listOf("<http://example.org/s4>", "<http://example.org/p4>", "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                    graph.addData(1L, listOf("<http://example.org/s5>", "<http://example.org/p5>", "\"5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                    graph.addData(1L, listOf("<http://example.org/s6>", "<http://example.org/p6>", "_:o6"))
                                    DistributedTripleStore.commit(1L)
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "s", "p", "o", false, false, false, EIndexPattern.SPO)
                                }()
                                ,
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/s1>", "<http://example.org/p1>", "<http://example.org/s2>"))
                                    graph.addData(1L, listOf("<http://example.org/s2>", "<http://example.org/p2>", "\"foo\""))
                                    graph.addData(1L, listOf("<http://example.org/s3>", "<http://example.org/p2>", "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                    graph.addData(1L, listOf("<http://example.org/s4>", "<http://example.org/p4>", "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                    graph.addData(1L, listOf("<http://example.org/s5>", "<http://example.org/p5>", "\"5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                    graph.addData(1L, listOf("<http://example.org/s6>", "<http://example.org/p6>", "_:o6"))
                                    DistributedTripleStore.commit(1L)
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "o", "p2", "o2", false, false, false, EIndexPattern.SPO)
                                }()
                                ,
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
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://www.example.org/instance#a>", "<http://www.example.org/schema#p>", "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                    DistributedTripleStore.commit(1L)
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "x", "<http://www.example.org/schema#p>", "y", false, true, false, EIndexPattern.SPO)
                                }()
                                ,
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://www.example.org/instance#a>", "<http://www.example.org/schema#q>", "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                    graph.addData(1L, listOf("<http://www.example.org/instance#a>", "<http://www.example.org/schema#q>", "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                    DistributedTripleStore.commit(1L)
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "x", "<http://www.example.org/schema#q>", "z", false, true, false, EIndexPattern.SPO)
                                }()
                                ,
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
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://www.example.org/instance#a>", "<http://www.example.org/schema#p>", "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                    DistributedTripleStore.commit(1L)
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "x", "<http://www.example.org/schema#p>", "y", false, true, false, EIndexPattern.SPO)
                                }()
                                ,
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://www.example.org/instance#a>", "<http://www.example.org/schema#q>", "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                    graph.addData(1L, listOf("<http://www.example.org/instance#a>", "<http://www.example.org/schema#q>", "\"foobar\""))
                                    DistributedTripleStore.commit(1L)
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "x", "<http://www.example.org/schema#q>", "z", false, true, false, EIndexPattern.SPO)
                                }()
                                ,
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
            /* {
                val dictionary=ResultSetDictionary()
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
                        false                    ),
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
            }() */ /* resources/sparql11-test-suite/project-expression/projexp03.rq */
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
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://www.example.org/instance#a>", "<http://www.example.org/schema#p>", "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                    graph.addData(1L, listOf("<http://www.example.org/instance#b>", "<http://www.example.org/schema#p>", "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                    DistributedTripleStore.commit(1L)
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "x", "<http://www.example.org/schema#p>", "y", false, true, false, EIndexPattern.SPO)
                                }()
                                ,
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://www.example.org/instance#a>", "<http://www.example.org/schema#q>", "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                    DistributedTripleStore.commit(1L)
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "x", "<http://www.example.org/schema#q>", "l", false, true, false, EIndexPattern.SPO)
                                }()
                                ,
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
                                        "#_34549",
                                        "L"
                                ), listOf(
                                        mutableMapOf(
                                                "#_34549" to "_:_34526",
                                                "L" to "\"Ice Cream\""
                                        ),
                                        mutableMapOf(
                                                "#_34549" to "_:_34527",
                                                "L" to "\"Pizza\""
                                        ),
                                        mutableMapOf(
                                                "#_34549" to "_:_34528",
                                                "L" to "\"Wine\""
                                        ),
                                        mutableMapOf(
                                                "#_34549" to "_:_34529",
                                                "L" to "\"Ice Cream\""
                                        ),
                                        mutableMapOf(
                                                "#_34549" to "_:_34530",
                                                "L" to "\"Pasta\""
                                        ),
                                        mutableMapOf(
                                                "#_34549" to "_:_34531",
                                                "L" to "\"Soft Drink\""
                                        ),
                                        mutableMapOf(
                                                "#_34549" to "_:_34532",
                                                "L" to "\"Sandwich\""
                                        ),
                                        mutableMapOf(
                                                "#_34549" to "_:_34533",
                                                "L" to "\"Soft Drink\""
                                        ),
                                        mutableMapOf(
                                                "#_34549" to "_:_34534",
                                                "L" to "\"Bagel\""
                                        ),
                                        mutableMapOf(
                                                "#_34549" to "_:_34535",
                                                "L" to "\"Soft Drink\""
                                        )
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "O",
                                        "#_34549"
                                ), listOf(
                                        mutableMapOf(
                                                "O" to "<http://www.example.orgorder1>",
                                                "#_34549" to "_:_34526"
                                        ),
                                        mutableMapOf(
                                                "O" to "<http://www.example.orgorder1>",
                                                "#_34549" to "_:_34527"
                                        ),
                                        mutableMapOf(
                                                "O" to "<http://www.example.orgorder1>",
                                                "#_34549" to "_:_34528"
                                        ),
                                        mutableMapOf(
                                                "O" to "<http://www.example.orgorder2>",
                                                "#_34549" to "_:_34529"
                                        ),
                                        mutableMapOf(
                                                "O" to "<http://www.example.orgorder2>",
                                                "#_34549" to "_:_34530"
                                        ),
                                        mutableMapOf(
                                                "O" to "<http://www.example.orgorder2>",
                                                "#_34549" to "_:_34531"
                                        ),
                                        mutableMapOf(
                                                "O" to "<http://www.example.orgorder3>",
                                                "#_34549" to "_:_34532"
                                        ),
                                        mutableMapOf(
                                                "O" to "<http://www.example.orgorder3>",
                                                "#_34549" to "_:_34533"
                                        ),
                                        mutableMapOf(
                                                "O" to "<http://www.example.orgorder4>",
                                                "#_34549" to "_:_34534"
                                        ),
                                        mutableMapOf(
                                                "O" to "<http://www.example.orgorder4>",
                                                "#_34549" to "_:_34535"
                                        )
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "#_34549",
                                "L",
                                "O"
                        ), listOf(
                                mutableMapOf(
                                        "#_34549" to "_:_34526",
                                        "L" to "\"Ice Cream\"",
                                        "O" to "<http://www.example.orgorder1>"
                                ),
                                mutableMapOf(
                                        "#_34549" to "_:_34527",
                                        "L" to "\"Pizza\"",
                                        "O" to "<http://www.example.orgorder1>"
                                ),
                                mutableMapOf(
                                        "#_34549" to "_:_34528",
                                        "L" to "\"Wine\"",
                                        "O" to "<http://www.example.orgorder1>"
                                ),
                                mutableMapOf(
                                        "#_34549" to "_:_34529",
                                        "L" to "\"Ice Cream\"",
                                        "O" to "<http://www.example.orgorder2>"
                                ),
                                mutableMapOf(
                                        "#_34549" to "_:_34530",
                                        "L" to "\"Pasta\"",
                                        "O" to "<http://www.example.orgorder2>"
                                ),
                                mutableMapOf(
                                        "#_34549" to "_:_34531",
                                        "L" to "\"Soft Drink\"",
                                        "O" to "<http://www.example.orgorder2>"
                                ),
                                mutableMapOf(
                                        "#_34549" to "_:_34532",
                                        "L" to "\"Sandwich\"",
                                        "O" to "<http://www.example.orgorder3>"
                                ),
                                mutableMapOf(
                                        "#_34549" to "_:_34533",
                                        "L" to "\"Soft Drink\"",
                                        "O" to "<http://www.example.orgorder3>"
                                ),
                                mutableMapOf(
                                        "#_34549" to "_:_34534",
                                        "L" to "\"Bagel\"",
                                        "O" to "<http://www.example.orgorder4>"
                                ),
                                mutableMapOf(
                                        "#_34549" to "_:_34535",
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
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("_:_34526", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Ice Cream\""))
                                    graph.addData(1L, listOf("_:_34527", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Pizza\""))
                                    graph.addData(1L, listOf("_:_34528", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Wine\""))
                                    graph.addData(1L, listOf("_:_34529", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Ice Cream\""))
                                    graph.addData(1L, listOf("_:_34530", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Pasta\""))
                                    graph.addData(1L, listOf("_:_34531", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Soft Drink\""))
                                    graph.addData(1L, listOf("_:_34532", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Sandwich\""))
                                    graph.addData(1L, listOf("_:_34533", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Soft Drink\""))
                                    graph.addData(1L, listOf("_:_34534", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Bagel\""))
                                    graph.addData(1L, listOf("_:_34535", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Soft Drink\""))
                                    DistributedTripleStore.commit(1L)
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "#_34549", "<http://www.w3.org/2000/01/rdf-schema#label>", "L", false, true, false, EIndexPattern.SPO)
                                }()
                                ,
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://www.example.orgorder1>", "<http://www.example.orghasItem>", "_:_34526"))
                                    graph.addData(1L, listOf("<http://www.example.orgorder1>", "<http://www.example.orghasItem>", "_:_34527"))
                                    graph.addData(1L, listOf("<http://www.example.orgorder1>", "<http://www.example.orghasItem>", "_:_34528"))
                                    graph.addData(1L, listOf("<http://www.example.orgorder2>", "<http://www.example.orghasItem>", "_:_34529"))
                                    graph.addData(1L, listOf("<http://www.example.orgorder2>", "<http://www.example.orghasItem>", "_:_34530"))
                                    graph.addData(1L, listOf("<http://www.example.orgorder2>", "<http://www.example.orghasItem>", "_:_34531"))
                                    graph.addData(1L, listOf("<http://www.example.orgorder3>", "<http://www.example.orghasItem>", "_:_34532"))
                                    graph.addData(1L, listOf("<http://www.example.orgorder3>", "<http://www.example.orghasItem>", "_:_34533"))
                                    graph.addData(1L, listOf("<http://www.example.orgorder4>", "<http://www.example.orghasItem>", "_:_34534"))
                                    graph.addData(1L, listOf("<http://www.example.orgorder4>", "<http://www.example.orghasItem>", "_:_34535"))
                                    DistributedTripleStore.commit(1L)
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "O", "<http://www.example.orghasItem>", "#_34549", false, true, false, EIndexPattern.SPO)
                                }()
                                ,
                                false),
                        POPValues(dictionary, listOf(
                                "#_34549",
                                "L",
                                "O"
                        ), listOf(
                                mutableMapOf(
                                        "#_34549" to "_:_34526",
                                        "L" to "\"Ice Cream\"",
                                        "O" to "<http://www.example.orgorder1>"
                                ),
                                mutableMapOf(
                                        "#_34549" to "_:_34527",
                                        "L" to "\"Pizza\"",
                                        "O" to "<http://www.example.orgorder1>"
                                ),
                                mutableMapOf(
                                        "#_34549" to "_:_34528",
                                        "L" to "\"Wine\"",
                                        "O" to "<http://www.example.orgorder1>"
                                ),
                                mutableMapOf(
                                        "#_34549" to "_:_34529",
                                        "L" to "\"Ice Cream\"",
                                        "O" to "<http://www.example.orgorder2>"
                                ),
                                mutableMapOf(
                                        "#_34549" to "_:_34530",
                                        "L" to "\"Pasta\"",
                                        "O" to "<http://www.example.orgorder2>"
                                ),
                                mutableMapOf(
                                        "#_34549" to "_:_34531",
                                        "L" to "\"Soft Drink\"",
                                        "O" to "<http://www.example.orgorder2>"
                                ),
                                mutableMapOf(
                                        "#_34549" to "_:_34532",
                                        "L" to "\"Sandwich\"",
                                        "O" to "<http://www.example.orgorder3>"
                                ),
                                mutableMapOf(
                                        "#_34549" to "_:_34533",
                                        "L" to "\"Soft Drink\"",
                                        "O" to "<http://www.example.orgorder3>"
                                ),
                                mutableMapOf(
                                        "#_34549" to "_:_34534",
                                        "L" to "\"Bagel\"",
                                        "O" to "<http://www.example.orgorder4>"
                                ),
                                mutableMapOf(
                                        "#_34549" to "_:_34535",
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
                                        "#_34549",
                                        "L",
                                        "O"
                                ), listOf(
                                        mutableMapOf(
                                                "#_34549" to "_:_34526",
                                                "L" to "\"Ice Cream\"",
                                                "O" to "<http://www.example.orgorder1>"
                                        ),
                                        mutableMapOf(
                                                "#_34549" to "_:_34527",
                                                "L" to "\"Pizza\"",
                                                "O" to "<http://www.example.orgorder1>"
                                        ),
                                        mutableMapOf(
                                                "#_34549" to "_:_34528",
                                                "L" to "\"Wine\"",
                                                "O" to "<http://www.example.orgorder1>"
                                        ),
                                        mutableMapOf(
                                                "#_34549" to "_:_34529",
                                                "L" to "\"Ice Cream\"",
                                                "O" to "<http://www.example.orgorder2>"
                                        ),
                                        mutableMapOf(
                                                "#_34549" to "_:_34530",
                                                "L" to "\"Pasta\"",
                                                "O" to "<http://www.example.orgorder2>"
                                        ),
                                        mutableMapOf(
                                                "#_34549" to "_:_34531",
                                                "L" to "\"Soft Drink\"",
                                                "O" to "<http://www.example.orgorder2>"
                                        ),
                                        mutableMapOf(
                                                "#_34549" to "_:_34532",
                                                "L" to "\"Sandwich\"",
                                                "O" to "<http://www.example.orgorder3>"
                                        ),
                                        mutableMapOf(
                                                "#_34549" to "_:_34533",
                                                "L" to "\"Soft Drink\"",
                                                "O" to "<http://www.example.orgorder3>"
                                        ),
                                        mutableMapOf(
                                                "#_34549" to "_:_34534",
                                                "L" to "\"Bagel\"",
                                                "O" to "<http://www.example.orgorder4>"
                                        ),
                                        mutableMapOf(
                                                "#_34549" to "_:_34535",
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
                                "#_34549",
                                "L",
                                "O"
                        ), listOf(
                                mutableMapOf(
                                        "#_34549" to "_:_34526",
                                        "L" to "\"Ice Cream\"",
                                        "O" to "<http://www.example.orgorder1>"
                                ),
                                mutableMapOf(
                                        "#_34549" to "_:_34527",
                                        "L" to "\"Pizza\"",
                                        "O" to "<http://www.example.orgorder1>"
                                ),
                                mutableMapOf(
                                        "#_34549" to "_:_34528",
                                        "L" to "\"Wine\"",
                                        "O" to "<http://www.example.orgorder1>"
                                ),
                                mutableMapOf(
                                        "#_34549" to "_:_34529",
                                        "L" to "\"Ice Cream\"",
                                        "O" to "<http://www.example.orgorder2>"
                                ),
                                mutableMapOf(
                                        "#_34549" to "_:_34530",
                                        "L" to "\"Pasta\"",
                                        "O" to "<http://www.example.orgorder2>"
                                ),
                                mutableMapOf(
                                        "#_34549" to "_:_34531",
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
                                POPJoinHashMap(
                                        dictionary,
                                        {
                                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                            val graph = DistributedTripleStore.createGraph(graphName)
                                            graph.addData(1L, listOf("_:_34526", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Ice Cream\""))
                                            graph.addData(1L, listOf("_:_34527", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Pizza\""))
                                            graph.addData(1L, listOf("_:_34528", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Wine\""))
                                            graph.addData(1L, listOf("_:_34529", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Ice Cream\""))
                                            graph.addData(1L, listOf("_:_34530", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Pasta\""))
                                            graph.addData(1L, listOf("_:_34531", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Soft Drink\""))
                                            graph.addData(1L, listOf("_:_34532", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Sandwich\""))
                                            graph.addData(1L, listOf("_:_34533", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Soft Drink\""))
                                            graph.addData(1L, listOf("_:_34534", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Bagel\""))
                                            graph.addData(1L, listOf("_:_34535", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Soft Drink\""))
                                            DistributedTripleStore.commit(1L)
                                            TripleStoreIteratorGlobal(1L, dictionary, graphName, "#_34549", "<http://www.w3.org/2000/01/rdf-schema#label>", "L", false, true, false, EIndexPattern.SPO)
                                        }()
                                        ,
                                        {
                                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                            val graph = DistributedTripleStore.createGraph(graphName)
                                            graph.addData(1L, listOf("<http://www.example.orgorder1>", "<http://www.example.orghasItem>", "_:_34526"))
                                            graph.addData(1L, listOf("<http://www.example.orgorder1>", "<http://www.example.orghasItem>", "_:_34527"))
                                            graph.addData(1L, listOf("<http://www.example.orgorder1>", "<http://www.example.orghasItem>", "_:_34528"))
                                            graph.addData(1L, listOf("<http://www.example.orgorder2>", "<http://www.example.orghasItem>", "_:_34529"))
                                            graph.addData(1L, listOf("<http://www.example.orgorder2>", "<http://www.example.orghasItem>", "_:_34530"))
                                            graph.addData(1L, listOf("<http://www.example.orgorder2>", "<http://www.example.orghasItem>", "_:_34531"))
                                            graph.addData(1L, listOf("<http://www.example.orgorder3>", "<http://www.example.orghasItem>", "_:_34532"))
                                            graph.addData(1L, listOf("<http://www.example.orgorder3>", "<http://www.example.orghasItem>", "_:_34533"))
                                            graph.addData(1L, listOf("<http://www.example.orgorder4>", "<http://www.example.orghasItem>", "_:_34534"))
                                            graph.addData(1L, listOf("<http://www.example.orgorder4>", "<http://www.example.orghasItem>", "_:_34535"))
                                            DistributedTripleStore.commit(1L)
                                            TripleStoreIteratorGlobal(1L, dictionary, graphName, "O", "<http://www.example.orghasItem>", "#_34549", false, true, false, EIndexPattern.SPO)
                                        }()
                                        ,
                                        false)
                                ,
                                POPDistinct(
                                        dictionary,
                                        POPProjection(
                                                dictionary,
                                                mutableListOf(
                                                        AOPVariable("O")
                                                ),
                                                POPLimit(
                                                        dictionary,
                                                        2,
                                                        POPSort(
                                                                dictionary,
                                                                AOPVariable("O"),
                                                                true,
                                                                {
                                                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                                    val graph = DistributedTripleStore.createGraph(graphName)
                                                                    graph.addData(1L, listOf("<http://www.example.orgorder1>", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://www.example.orgOrder>"))
                                                                    graph.addData(1L, listOf("<http://www.example.orgorder2>", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://www.example.orgOrder>"))
                                                                    graph.addData(1L, listOf("<http://www.example.orgorder3>", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://www.example.orgOrder>"))
                                                                    graph.addData(1L, listOf("<http://www.example.orgorder4>", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://www.example.orgOrder>"))
                                                                    DistributedTripleStore.commit(1L)
                                                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "O", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://www.example.orgOrder>", false, true, true, EIndexPattern.SPO)
                                                                }()

                                                        )

                                                )

                                        )

                                )
                                ,
                                false),
                        POPValues(dictionary, listOf(
                                "#_34549",
                                "L",
                                "O"
                        ), listOf(
                                mutableMapOf(
                                        "#_34549" to "_:_34526",
                                        "L" to "\"Ice Cream\"",
                                        "O" to "<http://www.example.orgorder1>"
                                ),
                                mutableMapOf(
                                        "#_34549" to "_:_34527",
                                        "L" to "\"Pizza\"",
                                        "O" to "<http://www.example.orgorder1>"
                                ),
                                mutableMapOf(
                                        "#_34549" to "_:_34528",
                                        "L" to "\"Wine\"",
                                        "O" to "<http://www.example.orgorder1>"
                                ),
                                mutableMapOf(
                                        "#_34549" to "_:_34529",
                                        "L" to "\"Ice Cream\"",
                                        "O" to "<http://www.example.orgorder2>"
                                ),
                                mutableMapOf(
                                        "#_34549" to "_:_34530",
                                        "L" to "\"Pasta\"",
                                        "O" to "<http://www.example.orgorder2>"
                                ),
                                mutableMapOf(
                                        "#_34549" to "_:_34531",
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
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://p1>", "<http://xmlns.com/foaf/0.1/firstName>", "\"John\""))
                                    DistributedTripleStore.commit(1L)
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "P", "<http://xmlns.com/foaf/0.1/firstName>", "F", false, true, false, EIndexPattern.SPO)
                                }()
                                ,
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://p1>", "<http://xmlns.com/foaf/0.1/lastName>", "\"Doe\""))
                                    DistributedTripleStore.commit(1L)
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "P", "<http://xmlns.com/foaf/0.1/lastName>", "L", false, true, false, EIndexPattern.SPO)
                                }()
                                ,
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
                                        "#_34870",
                                        "L"
                                ), listOf(
                                        mutableMapOf(
                                                "#_34870" to "_:_34847",
                                                "L" to "\"Ice Cream\""
                                        ),
                                        mutableMapOf(
                                                "#_34870" to "_:_34848",
                                                "L" to "\"Pizza\""
                                        ),
                                        mutableMapOf(
                                                "#_34870" to "_:_34849",
                                                "L" to "\"Wine\""
                                        ),
                                        mutableMapOf(
                                                "#_34870" to "_:_34850",
                                                "L" to "\"Ice Cream\""
                                        ),
                                        mutableMapOf(
                                                "#_34870" to "_:_34851",
                                                "L" to "\"Pasta\""
                                        ),
                                        mutableMapOf(
                                                "#_34870" to "_:_34852",
                                                "L" to "\"Soft Drink\""
                                        ),
                                        mutableMapOf(
                                                "#_34870" to "_:_34853",
                                                "L" to "\"Sandwich\""
                                        ),
                                        mutableMapOf(
                                                "#_34870" to "_:_34854",
                                                "L" to "\"Soft Drink\""
                                        ),
                                        mutableMapOf(
                                                "#_34870" to "_:_34855",
                                                "L" to "\"Bagel\""
                                        ),
                                        mutableMapOf(
                                                "#_34870" to "_:_34856",
                                                "L" to "\"Soft Drink\""
                                        )
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "O",
                                        "#_34870"
                                ), listOf(
                                        mutableMapOf(
                                                "O" to "<http://www.example.orgorder1>",
                                                "#_34870" to "_:_34847"
                                        ),
                                        mutableMapOf(
                                                "O" to "<http://www.example.orgorder1>",
                                                "#_34870" to "_:_34848"
                                        ),
                                        mutableMapOf(
                                                "O" to "<http://www.example.orgorder1>",
                                                "#_34870" to "_:_34849"
                                        ),
                                        mutableMapOf(
                                                "O" to "<http://www.example.orgorder2>",
                                                "#_34870" to "_:_34850"
                                        ),
                                        mutableMapOf(
                                                "O" to "<http://www.example.orgorder2>",
                                                "#_34870" to "_:_34851"
                                        ),
                                        mutableMapOf(
                                                "O" to "<http://www.example.orgorder2>",
                                                "#_34870" to "_:_34852"
                                        ),
                                        mutableMapOf(
                                                "O" to "<http://www.example.orgorder3>",
                                                "#_34870" to "_:_34853"
                                        ),
                                        mutableMapOf(
                                                "O" to "<http://www.example.orgorder3>",
                                                "#_34870" to "_:_34854"
                                        ),
                                        mutableMapOf(
                                                "O" to "<http://www.example.orgorder4>",
                                                "#_34870" to "_:_34855"
                                        ),
                                        mutableMapOf(
                                                "O" to "<http://www.example.orgorder4>",
                                                "#_34870" to "_:_34856"
                                        )
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "#_34870",
                                "L",
                                "O"
                        ), listOf(
                                mutableMapOf(
                                        "#_34870" to "_:_34847",
                                        "L" to "\"Ice Cream\"",
                                        "O" to "<http://www.example.orgorder1>"
                                ),
                                mutableMapOf(
                                        "#_34870" to "_:_34848",
                                        "L" to "\"Pizza\"",
                                        "O" to "<http://www.example.orgorder1>"
                                ),
                                mutableMapOf(
                                        "#_34870" to "_:_34849",
                                        "L" to "\"Wine\"",
                                        "O" to "<http://www.example.orgorder1>"
                                ),
                                mutableMapOf(
                                        "#_34870" to "_:_34850",
                                        "L" to "\"Ice Cream\"",
                                        "O" to "<http://www.example.orgorder2>"
                                ),
                                mutableMapOf(
                                        "#_34870" to "_:_34851",
                                        "L" to "\"Pasta\"",
                                        "O" to "<http://www.example.orgorder2>"
                                ),
                                mutableMapOf(
                                        "#_34870" to "_:_34852",
                                        "L" to "\"Soft Drink\"",
                                        "O" to "<http://www.example.orgorder2>"
                                ),
                                mutableMapOf(
                                        "#_34870" to "_:_34853",
                                        "L" to "\"Sandwich\"",
                                        "O" to "<http://www.example.orgorder3>"
                                ),
                                mutableMapOf(
                                        "#_34870" to "_:_34854",
                                        "L" to "\"Soft Drink\"",
                                        "O" to "<http://www.example.orgorder3>"
                                ),
                                mutableMapOf(
                                        "#_34870" to "_:_34855",
                                        "L" to "\"Bagel\"",
                                        "O" to "<http://www.example.orgorder4>"
                                ),
                                mutableMapOf(
                                        "#_34870" to "_:_34856",
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
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("_:_34847", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Ice Cream\""))
                                    graph.addData(1L, listOf("_:_34848", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Pizza\""))
                                    graph.addData(1L, listOf("_:_34849", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Wine\""))
                                    graph.addData(1L, listOf("_:_34850", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Ice Cream\""))
                                    graph.addData(1L, listOf("_:_34851", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Pasta\""))
                                    graph.addData(1L, listOf("_:_34852", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Soft Drink\""))
                                    graph.addData(1L, listOf("_:_34853", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Sandwich\""))
                                    graph.addData(1L, listOf("_:_34854", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Soft Drink\""))
                                    graph.addData(1L, listOf("_:_34855", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Bagel\""))
                                    graph.addData(1L, listOf("_:_34856", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Soft Drink\""))
                                    DistributedTripleStore.commit(1L)
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "#_34870", "<http://www.w3.org/2000/01/rdf-schema#label>", "L", false, true, false, EIndexPattern.SPO)
                                }()
                                ,
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://www.example.orgorder1>", "<http://www.example.orghasItem>", "_:_34847"))
                                    graph.addData(1L, listOf("<http://www.example.orgorder1>", "<http://www.example.orghasItem>", "_:_34848"))
                                    graph.addData(1L, listOf("<http://www.example.orgorder1>", "<http://www.example.orghasItem>", "_:_34849"))
                                    graph.addData(1L, listOf("<http://www.example.orgorder2>", "<http://www.example.orghasItem>", "_:_34850"))
                                    graph.addData(1L, listOf("<http://www.example.orgorder2>", "<http://www.example.orghasItem>", "_:_34851"))
                                    graph.addData(1L, listOf("<http://www.example.orgorder2>", "<http://www.example.orghasItem>", "_:_34852"))
                                    graph.addData(1L, listOf("<http://www.example.orgorder3>", "<http://www.example.orghasItem>", "_:_34853"))
                                    graph.addData(1L, listOf("<http://www.example.orgorder3>", "<http://www.example.orghasItem>", "_:_34854"))
                                    graph.addData(1L, listOf("<http://www.example.orgorder4>", "<http://www.example.orghasItem>", "_:_34855"))
                                    graph.addData(1L, listOf("<http://www.example.orgorder4>", "<http://www.example.orghasItem>", "_:_34856"))
                                    DistributedTripleStore.commit(1L)
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "O", "<http://www.example.orghasItem>", "#_34870", false, true, false, EIndexPattern.SPO)
                                }()
                                ,
                                false),
                        POPValues(dictionary, listOf(
                                "#_34870",
                                "L",
                                "O"
                        ), listOf(
                                mutableMapOf(
                                        "#_34870" to "_:_34847",
                                        "L" to "\"Ice Cream\"",
                                        "O" to "<http://www.example.orgorder1>"
                                ),
                                mutableMapOf(
                                        "#_34870" to "_:_34848",
                                        "L" to "\"Pizza\"",
                                        "O" to "<http://www.example.orgorder1>"
                                ),
                                mutableMapOf(
                                        "#_34870" to "_:_34849",
                                        "L" to "\"Wine\"",
                                        "O" to "<http://www.example.orgorder1>"
                                ),
                                mutableMapOf(
                                        "#_34870" to "_:_34850",
                                        "L" to "\"Ice Cream\"",
                                        "O" to "<http://www.example.orgorder2>"
                                ),
                                mutableMapOf(
                                        "#_34870" to "_:_34851",
                                        "L" to "\"Pasta\"",
                                        "O" to "<http://www.example.orgorder2>"
                                ),
                                mutableMapOf(
                                        "#_34870" to "_:_34852",
                                        "L" to "\"Soft Drink\"",
                                        "O" to "<http://www.example.orgorder2>"
                                ),
                                mutableMapOf(
                                        "#_34870" to "_:_34853",
                                        "L" to "\"Sandwich\"",
                                        "O" to "<http://www.example.orgorder3>"
                                ),
                                mutableMapOf(
                                        "#_34870" to "_:_34854",
                                        "L" to "\"Soft Drink\"",
                                        "O" to "<http://www.example.orgorder3>"
                                ),
                                mutableMapOf(
                                        "#_34870" to "_:_34855",
                                        "L" to "\"Bagel\"",
                                        "O" to "<http://www.example.orgorder4>"
                                ),
                                mutableMapOf(
                                        "#_34870" to "_:_34856",
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
                                        "#_34870",
                                        "L",
                                        "O"
                                ), listOf(
                                        mutableMapOf(
                                                "#_34870" to "_:_34847",
                                                "L" to "\"Ice Cream\"",
                                                "O" to "<http://www.example.orgorder1>"
                                        ),
                                        mutableMapOf(
                                                "#_34870" to "_:_34848",
                                                "L" to "\"Pizza\"",
                                                "O" to "<http://www.example.orgorder1>"
                                        ),
                                        mutableMapOf(
                                                "#_34870" to "_:_34849",
                                                "L" to "\"Wine\"",
                                                "O" to "<http://www.example.orgorder1>"
                                        ),
                                        mutableMapOf(
                                                "#_34870" to "_:_34850",
                                                "L" to "\"Ice Cream\"",
                                                "O" to "<http://www.example.orgorder2>"
                                        ),
                                        mutableMapOf(
                                                "#_34870" to "_:_34851",
                                                "L" to "\"Pasta\"",
                                                "O" to "<http://www.example.orgorder2>"
                                        ),
                                        mutableMapOf(
                                                "#_34870" to "_:_34852",
                                                "L" to "\"Soft Drink\"",
                                                "O" to "<http://www.example.orgorder2>"
                                        ),
                                        mutableMapOf(
                                                "#_34870" to "_:_34853",
                                                "L" to "\"Sandwich\"",
                                                "O" to "<http://www.example.orgorder3>"
                                        ),
                                        mutableMapOf(
                                                "#_34870" to "_:_34854",
                                                "L" to "\"Soft Drink\"",
                                                "O" to "<http://www.example.orgorder3>"
                                        ),
                                        mutableMapOf(
                                                "#_34870" to "_:_34855",
                                                "L" to "\"Bagel\"",
                                                "O" to "<http://www.example.orgorder4>"
                                        ),
                                        mutableMapOf(
                                                "#_34870" to "_:_34856",
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
                                "#_34870",
                                "L",
                                "O"
                        ), listOf(
                                mutableMapOf(
                                        "#_34870" to "_:_34847",
                                        "L" to "\"Ice Cream\"",
                                        "O" to "<http://www.example.orgorder1>"
                                ),
                                mutableMapOf(
                                        "#_34870" to "_:_34848",
                                        "L" to "\"Pizza\"",
                                        "O" to "<http://www.example.orgorder1>"
                                ),
                                mutableMapOf(
                                        "#_34870" to "_:_34849",
                                        "L" to "\"Wine\"",
                                        "O" to "<http://www.example.orgorder1>"
                                ),
                                mutableMapOf(
                                        "#_34870" to "_:_34850",
                                        "L" to "\"Ice Cream\"",
                                        "O" to "<http://www.example.orgorder2>"
                                ),
                                mutableMapOf(
                                        "#_34870" to "_:_34851",
                                        "L" to "\"Pasta\"",
                                        "O" to "<http://www.example.orgorder2>"
                                ),
                                mutableMapOf(
                                        "#_34870" to "_:_34852",
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
                                POPJoinHashMap(
                                        dictionary,
                                        {
                                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                            val graph = DistributedTripleStore.createGraph(graphName)
                                            graph.addData(1L, listOf("_:_34847", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Ice Cream\""))
                                            graph.addData(1L, listOf("_:_34848", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Pizza\""))
                                            graph.addData(1L, listOf("_:_34849", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Wine\""))
                                            graph.addData(1L, listOf("_:_34850", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Ice Cream\""))
                                            graph.addData(1L, listOf("_:_34851", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Pasta\""))
                                            graph.addData(1L, listOf("_:_34852", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Soft Drink\""))
                                            graph.addData(1L, listOf("_:_34853", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Sandwich\""))
                                            graph.addData(1L, listOf("_:_34854", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Soft Drink\""))
                                            graph.addData(1L, listOf("_:_34855", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Bagel\""))
                                            graph.addData(1L, listOf("_:_34856", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Soft Drink\""))
                                            DistributedTripleStore.commit(1L)
                                            TripleStoreIteratorGlobal(1L, dictionary, graphName, "#_34870", "<http://www.w3.org/2000/01/rdf-schema#label>", "L", false, true, false, EIndexPattern.SPO)
                                        }()
                                        ,
                                        {
                                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                            val graph = DistributedTripleStore.createGraph(graphName)
                                            graph.addData(1L, listOf("<http://www.example.orgorder1>", "<http://www.example.orghasItem>", "_:_34847"))
                                            graph.addData(1L, listOf("<http://www.example.orgorder1>", "<http://www.example.orghasItem>", "_:_34848"))
                                            graph.addData(1L, listOf("<http://www.example.orgorder1>", "<http://www.example.orghasItem>", "_:_34849"))
                                            graph.addData(1L, listOf("<http://www.example.orgorder2>", "<http://www.example.orghasItem>", "_:_34850"))
                                            graph.addData(1L, listOf("<http://www.example.orgorder2>", "<http://www.example.orghasItem>", "_:_34851"))
                                            graph.addData(1L, listOf("<http://www.example.orgorder2>", "<http://www.example.orghasItem>", "_:_34852"))
                                            graph.addData(1L, listOf("<http://www.example.orgorder3>", "<http://www.example.orghasItem>", "_:_34853"))
                                            graph.addData(1L, listOf("<http://www.example.orgorder3>", "<http://www.example.orghasItem>", "_:_34854"))
                                            graph.addData(1L, listOf("<http://www.example.orgorder4>", "<http://www.example.orghasItem>", "_:_34855"))
                                            graph.addData(1L, listOf("<http://www.example.orgorder4>", "<http://www.example.orghasItem>", "_:_34856"))
                                            DistributedTripleStore.commit(1L)
                                            TripleStoreIteratorGlobal(1L, dictionary, graphName, "O", "<http://www.example.orghasItem>", "#_34870", false, true, false, EIndexPattern.SPO)
                                        }()
                                        ,
                                        false)
                                ,
                                POPDistinct(
                                        dictionary,
                                        POPProjection(
                                                dictionary,
                                                mutableListOf(
                                                        AOPVariable("O")
                                                ),
                                                POPLimit(
                                                        dictionary,
                                                        2,
                                                        POPSort(
                                                                dictionary,
                                                                AOPVariable("O"),
                                                                true,
                                                                {
                                                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                                    val graph = DistributedTripleStore.createGraph(graphName)
                                                                    graph.addData(1L, listOf("<http://www.example.orgorder1>", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://www.example.orgOrder>"))
                                                                    graph.addData(1L, listOf("<http://www.example.orgorder2>", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://www.example.orgOrder>"))
                                                                    graph.addData(1L, listOf("<http://www.example.orgorder3>", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://www.example.orgOrder>"))
                                                                    graph.addData(1L, listOf("<http://www.example.orgorder4>", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://www.example.orgOrder>"))
                                                                    DistributedTripleStore.commit(1L)
                                                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "O", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://www.example.orgOrder>", false, true, true, EIndexPattern.SPO)
                                                                }()

                                                        )

                                                )

                                        )

                                )
                                ,
                                false),
                        POPValues(dictionary, listOf(
                                "#_34870",
                                "L",
                                "O"
                        ), listOf(
                                mutableMapOf(
                                        "#_34870" to "_:_34847",
                                        "L" to "\"Ice Cream\"",
                                        "O" to "<http://www.example.orgorder1>"
                                ),
                                mutableMapOf(
                                        "#_34870" to "_:_34848",
                                        "L" to "\"Pizza\"",
                                        "O" to "<http://www.example.orgorder1>"
                                ),
                                mutableMapOf(
                                        "#_34870" to "_:_34849",
                                        "L" to "\"Wine\"",
                                        "O" to "<http://www.example.orgorder1>"
                                ),
                                mutableMapOf(
                                        "#_34870" to "_:_34850",
                                        "L" to "\"Ice Cream\"",
                                        "O" to "<http://www.example.orgorder2>"
                                ),
                                mutableMapOf(
                                        "#_34870" to "_:_34851",
                                        "L" to "\"Pasta\"",
                                        "O" to "<http://www.example.orgorder2>"
                                ),
                                mutableMapOf(
                                        "#_34870" to "_:_34852",
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

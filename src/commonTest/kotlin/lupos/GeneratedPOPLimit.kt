package lupos

import lupos.s10physicalOptimisation.PhysicalOptimizer
import lupos.s13keyDistributionOptimizer.KeyDistributionOptimizer
import lupos.s12p2p.P2P
import lupos.s14endpoint.EndpointImpl
import lupos.s00misc.*
import lupos.s15tripleStoreDistributed.*
import lupos.s02buildSyntaxTree.sparql1_1.*
import lupos.s03resultRepresentation.*
import lupos.s04arithmetikOperators.*
import lupos.s04arithmetikOperators.multiinput.*
import lupos.s04arithmetikOperators.noinput.*
import lupos.s04arithmetikOperators.singleinput.*
import lupos.s04logicalOperators.*
import lupos.s04logicalOperators.noinput.*
import lupos.s04logicalOperators.multiinput.*
import lupos.s04logicalOperators.singleinput.*
import lupos.s04logicalOperators.singleinput.modifiers.*
import lupos.s08logicalOptimisation.*
import lupos.s09physicalOperators.*
import lupos.s09physicalOperators.multiinput.*
import lupos.s09physicalOperators.noinput.*
import lupos.s09physicalOperators.singleinput.*
import lupos.s09physicalOperators.singleinput.modifiers.*
import lupos.s11outputResult.*
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.*


class GeneratedPOPLimitTest {
    constructor() {
        P2P.knownClients.clear()
        P2P.knownClients.add(EndpointImpl.fullname)
    }
    fun setAggregationMode(node: OPBase, mode: Boolean, count: Int) {
        for (n in  node.children)
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
                    dictionary,
                    POPLimit(
                        dictionary,
                        2,
                        POPValues(dictionary, listOf(
                                "O"
                            ), listOf(
                                GeneratedMutableMap.map37191map,
                                GeneratedMutableMap.map37192map,
                                GeneratedMutableMap.map37193map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "O"
                        ), listOf(
                            GeneratedMutableMap.map37191map,
                            GeneratedMutableMap.map37192map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/subquery/sq11.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPLimit(
                        dictionary,
                        2,
                                    POPSort(
                                        dictionary,
                                        AOPVariable("O"),
                                        true,
                                                    {
                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                        graph.addData(1L,listOf("<http://www.example.orgorder1>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.example.orgOrder>"))
                                                        graph.addData(1L,listOf("<http://www.example.orgorder2>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.example.orgOrder>"))
                                                        graph.addData(1L,listOf("<http://www.example.orgorder3>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.example.orgOrder>"))
                                                        graph.addData(1L,listOf("<http://www.example.orgorder4>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.example.orgOrder>"))
                                                        DistributedTripleStore.commit(1L)
                                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"O","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.example.orgOrder>",false,true,true,EIndexPattern.SPO)
                                                    }()

                                    )

                    ),
                    POPValues(dictionary, listOf(
                            "O"
                        ), listOf(
                            GeneratedMutableMap.map37191map,
                            GeneratedMutableMap.map37192map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/subquery/sq11.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPLimit(
                        2,
                        LOPValues(listOf(
                                AOPVariable("O")
                            ), listOf(
                                GeneratedMutableMap.map37195map,
                                GeneratedMutableMap.map37196map,
                                GeneratedMutableMap.map37197map
                            )
                        )
                    ),
                    LOPValues(listOf(
                            AOPVariable("O")
                        ), listOf(
                            GeneratedMutableMap.map37195map,
                            GeneratedMutableMap.map37196map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/subquery/sq11.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPLimit(
                        2,
                                    LOPSort(
                                        true,
                                        AOPVariable("O"),
                                                    {
                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                        graph.addData(1L,listOf("<http://www.example.orgorder1>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.example.orgOrder>"))
                                                        graph.addData(1L,listOf("<http://www.example.orgorder2>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.example.orgOrder>"))
                                                        graph.addData(1L,listOf("<http://www.example.orgorder3>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.example.orgOrder>"))
                                                        graph.addData(1L,listOf("<http://www.example.orgorder4>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.example.orgOrder>"))
                                                        DistributedTripleStore.commit(1L)
                                                        LOPTriple(AOPVariable("O"),AOPVariable.calculate("<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>"),AOPVariable.calculate("<http://www.example.orgOrder>"),graphName,false)                                                    }()

                                    )

                    ),
                    LOPValues(listOf(
                            AOPVariable("O")
                        ), listOf(
                            GeneratedMutableMap.map37195map,
                            GeneratedMutableMap.map37196map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/subquery/sq11.rq */ ,
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
                } else if (data.input is POPBase && data is MicroTestPN) {
                    val input = data.input as POPBase
                    assertTrue(data.expected is POPValues)
                    val output = QueryResultToXML.toXML(input).first()
                    val expected = QueryResultToXML.toXML(data.expected as POPValues).first()
                    if (!expected.myEquals(output)){
                        println(output.toPrettyString())
                        println(expected.toPrettyString())
                    }
                    assertTrue(expected.myEquals(output))
                } else if (data.input is LOPBase && data is MicroTestLN) {
                    val lop_node = data.input as LOPBase
                    val dictionary = data.dictionary
                    ExecuteOptimizer.enabledOptimizers.clear()
                    val lOptimizer=LogicalOptimizer(1L, dictionary)
                    val pOptimizer=PhysicalOptimizer(1L, dictionary)
                    val dOptimizer=KeyDistributionOptimizer(1L, dictionary)
                    val lop_node2 =lOptimizer.optimizeCall(lop_node)
                    val pop_node = pOptimizer.optimizeCall(lop_node2)
                    val input = dOptimizer.optimizeCall(pop_node) as POPBase
                    assertTrue(data.expected is POPValues)
                    val output = QueryResultToXML.toXML(input).first()
                    val expected = QueryResultToXML.toXML(data.expected as POPValues).first()
                    if (!expected.myEquals(output)){
                        println(output.toPrettyString())
                        println(expected.toPrettyString())
                    }
                    assertTrue(expected.myEquals(output))
                    for(k in ExecuteOptimizer.enabledOptimizers.keys){
                        ExecuteOptimizer.enabledOptimizers[k]=true
                        val lop_node2 =lOptimizer.optimizeCall(lop_node)
                        val pop_node = pOptimizer.optimizeCall(lop_node2)
                        val input = dOptimizer.optimizeCall(pop_node) as POPBase
                        assertTrue(data.expected is POPValues)
                        val output = QueryResultToXML.toXML(input).first()
                        val expected = QueryResultToXML.toXML(data.expected as POPValues).first()
                        if (!expected.myEquals(output)){
                            println(ExecuteOptimizer.enabledOptimizers.keys.map{it to ExecuteOptimizer.enabledOptimizers[it]})
                            println(output.toPrettyString())
                            println(expected.toPrettyString())
                        }
                        assertTrue(expected.myEquals(output))
                    }
                }
            } catch (e: Throwable) {
                e.printStackTrace()
                assertTrue(data.expected is Throwable)
            }
        }
    }
}

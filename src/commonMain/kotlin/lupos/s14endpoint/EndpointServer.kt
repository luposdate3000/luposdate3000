package lupos.s14endpoint

import com.soywiz.korio.net.http.createHttpServer
import com.soywiz.korio.net.http.Http
import com.soywiz.korio.net.http.HttpServer
import kotlin.concurrent.thread
import kotlinx.coroutines.delay
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import lupos.s00misc.CoroutinesHelper
import lupos.s00misc.EGraphOperationType
import lupos.s00misc.EIndexPattern
import lupos.s00misc.ELoggerType
import lupos.s00misc.GlobalLogger
import lupos.s00misc.Trace
import lupos.s03resultRepresentation.ResultRepresenationNetwork
import lupos.s03resultRepresentation.ResultSet
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.noinput.AOPConstant
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s12p2p.P2P
import lupos.s12p2p.TransferHelperNetwork
import lupos.s14endpoint.Endpoint

@UseExperimental(ExperimentalStdlibApi::class)
abstract class EndpointServer(val hostname:String="localhost",val port:Int=80){
    val fullname = hostname + ":" + port

suspend fun process_print_traces(): String {
        return Trace.toString()
    }

suspend    fun receive(path:String, data: ByteArray):ByteArray{
	when(path){
		Endpoint.REQUEST_BINARY[0]->{
			return TransferHelperNetwork.processBinary(data!!)
		}
		else->throw Exception("unknown for binary $path")
	}
    }
suspend    fun receive(path:String,isPost:Boolean, data: String,params:Map<String,String>):ByteArray{
var responseStr = ""
        var responseBytes: ByteArray? = null
	when(path){
                Endpoint.REQUEST_TRIPLE_ADD[0] -> {
                    val s: AOPConstant = AOPVariable.calculate(params[Endpoint.REQUEST_TRIPLE_ADD[3]]!!)
                    val p: AOPConstant = AOPVariable.calculate(params[Endpoint.REQUEST_TRIPLE_ADD[4]]!!)
                    val o: AOPConstant = AOPVariable.calculate(params[Endpoint.REQUEST_TRIPLE_ADD[5]]!!)
                    responseStr = Endpoint.process_local_triple_add(params[Endpoint.REQUEST_TRIPLE_ADD[1]]!!,
                            params[Endpoint.REQUEST_TRIPLE_ADD[2]]!!.toLong(),
                            s, p, o,
                            EIndexPattern.valueOf(params[Endpoint.REQUEST_TRIPLE_ADD[6]]!!)).toPrettyString()
                }
                Endpoint.REQUEST_TRIPLE_GET[0] -> {
                    val s: AOPBase
                    if (params[Endpoint.REQUEST_TRIPLE_GET[6]]!!.toBoolean())
                        s = AOPVariable.calculate(params[Endpoint.REQUEST_TRIPLE_GET[3]]!!)
                    else
                        s = AOPVariable(params[Endpoint.REQUEST_TRIPLE_GET[3]]!!)
                    val p: AOPBase
                    if (params[Endpoint.REQUEST_TRIPLE_GET[7]]!!.toBoolean())
                        p = AOPVariable.calculate(params[Endpoint.REQUEST_TRIPLE_GET[4]]!!)
                    else
                        p = AOPVariable(params[Endpoint.REQUEST_TRIPLE_GET[4]]!!)
                    val o: AOPBase
                    if (params[Endpoint.REQUEST_TRIPLE_GET[8]]!!.toBoolean())
                        o = AOPVariable.calculate(params[Endpoint.REQUEST_TRIPLE_GET[5]]!!)
                    else
                        o = AOPVariable(params[Endpoint.REQUEST_TRIPLE_GET[5]]!!)
                    responseBytes = ResultRepresenationNetwork.toNetworkPackage(Endpoint.process_local_triple_get(params[Endpoint.REQUEST_TRIPLE_GET[1]]!!,
                            ResultSet(ResultSetDictionary()),
                            params[Endpoint.REQUEST_TRIPLE_GET[2]]!!.toLong(),
                            s, p, o,
                            EIndexPattern.valueOf(params[Endpoint.REQUEST_TRIPLE_GET[9]]!!)))
                }
                Endpoint.REQUEST_TRIPLE_DELETE[0] -> {
                    val s: AOPBase
                    if (params[Endpoint.REQUEST_TRIPLE_DELETE[6]]!!.toBoolean())
                        s = AOPVariable.calculate(params[Endpoint.REQUEST_TRIPLE_GET[3]]!!)
                    else
                        s = AOPVariable(params[Endpoint.REQUEST_TRIPLE_GET[3]]!!)
                    val p: AOPBase
                    if (params[Endpoint.REQUEST_TRIPLE_GET[7]]!!.toBoolean())
                        p = AOPVariable.calculate(params[Endpoint.REQUEST_TRIPLE_GET[4]]!!)
                    else
                        p = AOPVariable(params[Endpoint.REQUEST_TRIPLE_GET[4]]!!)
                    val o: AOPBase
                    if (params[Endpoint.REQUEST_TRIPLE_GET[8]]!!.toBoolean())
                        o = AOPVariable.calculate(params[Endpoint.REQUEST_TRIPLE_GET[5]]!!)
                    else
                        o = AOPVariable(params[Endpoint.REQUEST_TRIPLE_GET[5]]!!)
                    responseStr = Endpoint.process_local_triple_delete(params[Endpoint.REQUEST_TRIPLE_DELETE[1]]!!,
                            params[Endpoint.REQUEST_TRIPLE_DELETE[2]]!!.toLong(),
                            s, p, o,
                            EIndexPattern.valueOf(params[Endpoint.REQUEST_TRIPLE_DELETE[9]]!!)).toPrettyString()
                }
                Endpoint.REQUEST_TRACE_PRINT[0] -> responseStr = process_print_traces()
                Endpoint.REQUEST_PEERS_LIST[0] -> responseStr = P2P.process_peers_list()
                Endpoint.REQUEST_PEERS_SELF_TEST[0] -> responseStr = P2P.process_peers_self_test()
                Endpoint.REQUEST_COMMIT[0] -> responseStr = Endpoint.process_local_commit(params[Endpoint.REQUEST_COMMIT[1]]!!.toLong()).toPrettyString()
                Endpoint.REQUEST_PEERS_JOIN[0] -> responseStr = P2P.process_peers_join(params[Endpoint.REQUEST_PEERS_JOIN[1]]!!)
                Endpoint.REQUEST_PEERS_JOIN_INTERNAL[0] -> responseStr = P2P.process_peers_join_internal(params[Endpoint.REQUEST_PEERS_JOIN_INTERNAL[1]]!!)
                Endpoint.REQUEST_GRAPH_OPERATION[0] -> responseStr = Endpoint.process_local_graph_operation(params[Endpoint.REQUEST_GRAPH_OPERATION[1]]!!, EGraphOperationType.valueOf(params[Endpoint.REQUEST_GRAPH_OPERATION[2]]!!)).toPrettyString()
                Endpoint.REQUEST_OPERATOR_QUERY[0] -> {
                    if (isPost)
                        responseStr = Endpoint.process_operatorgraph_query(data).toPrettyString()
                    else
                        responseStr = Endpoint.process_operatorgraph_query(params[Endpoint.REQUEST_OPERATOR_QUERY[1]]!!).toPrettyString()
                }
Endpoint.REQUEST_SPARQL_QUERY[0] -> {
                    if (isPost)
                        responseStr = Endpoint.process_sparql_query(data).toPrettyString()
                    else
                        responseStr = Endpoint.process_sparql_query(params[Endpoint.REQUEST_SPARQL_QUERY[1]]!!).toPrettyString()
                }
                Endpoint.REQUEST_XML_INPUT[0] -> {
                    if (isPost)
                        responseStr = Endpoint.process_xml_input(data).toPrettyString()
                    else
                        responseStr = Endpoint.process_xml_input(params[Endpoint.REQUEST_XML_INPUT[1]]!!).toPrettyString()
                }
                Endpoint.REQUEST_TURTLE_INPUT[0] -> {
                    if (isPost)
                        responseStr = Endpoint.process_turtle_input(data).toPrettyString()
                    else
                        responseStr = Endpoint.process_turtle_input(params[Endpoint.REQUEST_TURTLE_INPUT[1]]!!).toPrettyString()
                }
		else->throw Exception("unknown for text $path")
	}
 if (responseBytes != null)
            return responseBytes
        else
            return responseStr.encodeToByteArray()
    }
    abstract suspend fun start(bootstrap:String?=null)
}

var endpointServer:EndpointServer?=null

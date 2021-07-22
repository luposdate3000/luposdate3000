package lupos.simulator_db.luposdate3000
import lupos.shared.DictionaryValueHelper
import lupos.shared.inline.dynamicArray.ByteArrayWrapperExt
import lupos.simulator_db.IDatabasePackage
import lupos.simulator_db.QueryPackage
import lupos.simulator_db.QueryResponsePackage
import lupos.visualize.distributed.database.VisualisationMessage
import lupos.visualize.distributed.database.VisualisationNetwork
public object PostProcessSend {
    public fun process(source: Int, destination: Int, clock: Long, visual: VisualisationNetwork, pck: IDatabasePackage) {
        println("sending $clock: $source->$destination .. $pck")
        when (pck) {
            is MySimulatorAbstractPackage -> {
                when (pck.path) {
                    "/distributed/query/dictionary/remove",
                    "/distributed/query/dictionary/register" -> {
// ignore dictionary right now
                    }
                    "/distributed/graph/create" -> {
                        visual.addDistributedStorage(source, destination, clock, pck.params["name"]!!, pck.params["metadata"]!!)
                    }
                    "/distributed/graph/modify" -> {
                        val count = ((ByteArrayWrapperExt.getSize(pck.data) / DictionaryValueHelper.getSize()) - 1) / 3
                        visual.addMessage(VisualisationMessage(source, destination, clock, "modify ${pck.params["mode"]} ${pck.params["idx"]}@$destination:${pck.params["key"]} .. triples=$count"))
                    }
                    "simulator-intermediate-result" -> {
                        val bytes = ByteArrayWrapperExt.getSize(pck.data)
                        visual.addMessage(VisualisationMessage(source, destination, clock, "intermediate ${pck.params["key"]} .. count=$bytes"))
                    }
                    else -> visual.addMessage(VisualisationMessage(source, destination, clock, pck.toString()))
                }
            }
            is MySimulatorOperatorGraphPackage -> {
                visual.addMessage(VisualisationMessage(source, destination, clock, "operatorgraph ${pck.queryID} .. ${pck.operatorGraph.keys}"))
            }
            is QueryResponsePackage -> {
                visual.addMessage(VisualisationMessage(source, destination, clock, "response ${pck.queryID} .. ${pck.result.size}"))
            }
            is QueryPackage -> {
                visual.addMessage(VisualisationMessage(source, destination, clock, "query ${pck.queryID} .. ${pck.query.decodeToString()}"))
            }
            else -> visual.addMessage(VisualisationMessage(source, destination, clock, pck.toString()))
        }
    }
}

package lupos.simulator_db
import lupos.shared.DictionaryValueHelper
import lupos.shared.inline.dynamicArray.ByteArrayWrapperExt
import lupos.simulator_db.luposdate3000.MySimulatorAbstractPackage
import lupos.simulator_db.luposdate3000.MySimulatorOperatorGraphPackage
import lupos.visualize.distributed.database.VisualisationMessage
import lupos.visualize.distributed.database.VisualisationNetwork
public object PostProcessSend {
    public fun process(source: Int, destination: Int, clock: Long, visual: VisualisationNetwork, pck: IDatabasePackage) {
        when (pck) {
            is MySimulatorAbstractPackage -> {
                when (pck.path) {
                    "/distributed/query/dictionary/remove",
                    "/distributed/query/dictionary/register" -> {
// ignore dictionary right now
                    }
                    "/distributed/graph/modify" -> {
                        visual.addMessage(VisualisationMessage(source, destination, clock, "modify ${pck.params["mode"]} ${pck.params["idx"]}@$destination:${pck.params["key"]} .. count=${(ByteArrayWrapperExt.getSize(pck.data) / DictionaryValueHelper.getSize()) - 1}"))
                    }
                    "simulator-intermediate-result" -> {
                        visual.addMessage(VisualisationMessage(source, destination, clock, "intermediate ${pck.params["key"]} .. count=${(ByteArrayWrapperExt.getSize(pck.data) / DictionaryValueHelper.getSize()) - 1}"))
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
                visual.addMessage(VisualisationMessage(source, destination, clock, "query ${pck.queryID} .. ${pck.query.size}"))
            }
            else -> visual.addMessage(VisualisationMessage(source, destination, clock, pck.toString()))
        }
    }
}

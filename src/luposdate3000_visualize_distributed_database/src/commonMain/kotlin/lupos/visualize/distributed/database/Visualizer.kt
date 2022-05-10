package lupos.visualize.distributed.database
import lupos.shared.inline.File
import lupos.shared.inline.dynamicArray.ByteArrayWrapperExt
import lupos.simulator_db.luposdate3000.Package_Luposdate3000_Abstract
import lupos.simulator_db.luposdate3000.PendingWork
import simora.ILogger
import simora.IPayload
import simora.SimulationRun
import simora.applications.scenario.parking.Package_Query

public class Visualizer : ILogger {
    internal val relevantQueryIDs = mutableSetOf<Int>()
    internal val graphs = mutableMapOf<Int, DotGraph>()
    internal var counter = 0
    override fun addConnectionTable(src: Int, dest: Int, hop: Int) {}
    override fun addDevice(address: Int, x: Double, y: Double) {}
    override fun initialize(simRun: SimulationRun) {}
    override fun onReceiveNetworkPackage(address: Int, pck: IPayload) {}

    override fun onReceivePackage(address: Int, pck: IPayload) {
        when (pck) {
            is Package_Luposdate3000_Abstract -> {
                when (pck.path) {
                    "simulator-intermediate-result" -> {
                        val dep = pck.params["key"]!!.toInt()
                        val g_query = graphs.getOrPut(pck.queryID, { DotGraph() })
                        g_query.edges.add(DotEdge("send$dep", "rec$dep").setLabel("${ByteArrayWrapperExt.getSize(pck.data)}"))
                    }
                }
            }
        }
    }

    override fun onSendNetworkPackage(src: Int, dest: Int, hop: Int, pck: IPayload, delay: Long) {}
    override fun onSendPackage(src: Int, dest: Int, pck: IPayload) {}
    override fun onShutDown() {
        for ((k, v) in graphs) {
            if (relevantQueryIDs.contains(k)) {
                File("graph$k.dot").withOutputStream { out ->
                    out.println(v.toDotString())
                }
            }
        }
    }

    override fun onStartSimulation() {}
    override fun onStartUp() {}
    override fun onStartUpRouting() {}
    override fun onStopSimulation() {}
    override fun reset(label: String, finish: Boolean) {}
    override fun costumData(data: Any) {
        when (data) {
            is PendingWork -> {
                val g_query = graphs.getOrPut(data.queryID, { DotGraph() })
                val g_device = g_query.subgraphs.getOrPut("device_${data.deviceAdress}", { DotGraph() })
                var dataId = if (data.dataID == -1) {
                    "query_root"
                } else {
                    "query_part_${data.dataID}"
                }
                val g_subgraph = g_device.subgraphs.getOrPut(dataId, { DotGraph() })
                ConverterBinaryToPOPDot.decode(data.query, data.data, data.dataID, g_subgraph, { counter++ })
            }
            is Package_Query -> {
                relevantQueryIDs.add(data.queryID)
            }
        }
    }
}

internal class DotNode(internal val label: String, internal val id: String, internal val _do_not_use: Boolean) {
    internal companion object {
        operator fun invoke(label: String): DotNode {
            return DotNode(label.replace("#[0-9]*".toRegex(), ""), label.replace("[^a-zA-Z0-9]".toRegex(), ""), false)
        }
    }

    internal fun toDotString(indention: String): String {
        val res = StringBuilder()
        res.appendLine("${indention}$id [label = \"$label\"];")
        return res.toString()
    }
}

internal data class DotEdge(internal val start: String, internal val end: String) {
    internal var label: String = ""

    internal fun setLabel(label: String): DotEdge {
        this.label = label
        return this
    }

    internal fun toDotString(indention: String): String {
        val res = StringBuilder()
        if (label.length > 0) {
            res.appendLine("${indention}$start -> $end [label=\"$label\"];")
        } else {
            res.appendLine("${indention}$start -> $end;")
        }
        return res.toString()
    }
}

internal class DotGraph() {
    internal val subgraphs = mutableMapOf<String, DotGraph>()
    internal val nodes = mutableListOf<DotNode>()
    internal val edges = mutableListOf<DotEdge>()
    internal fun getAllEdges(): List<DotEdge> = edges + subgraphs.values.map { it.getAllEdges() }.flatten()
    internal fun toDotString(): String {
        val res = StringBuilder()
        res.appendLine("digraph G {")
        res.appendLine("  newrank = true;")
        res.append(toDotString("  "))
        for (edge in getAllEdges()) {
            res.append(edge.toDotString("  "))
        }
        res.appendLine("  overlap = scale;")
        res.appendLine("  splines = true;")
        res.appendLine("}")
        return res.toString()
    }
    internal fun addNode(label: String): String {
        val node = DotNode(label)
        nodes.add(node)
        return node.id
    }
    internal fun addEdge(label1: String, label2: String) {
        edges.add(DotEdge(label1, label2))
    }
    internal fun toDotString(indention: String): String {
        val res = StringBuilder()
        for (node in nodes) {
            res.append(node.toDotString(indention))
        }
        for ((label, g) in subgraphs) {
            res.appendLine("${indention}subgraph cluster$label {")
            res.appendLine("  ${indention}label = \"$label\";")
            res.append(g.toDotString("  $indention"))
            res.appendLine("$indention}")
        }
        return res.toString()
    }
}
/*

digraph G {
  subgraph cluster_0 {
    label = "cluster 0";
    n_0;
  }
  subgraph cluster_1 {
    label = "cluster 1";
    n_1;
  }
  n_0 -> n_1 ;
}

*/

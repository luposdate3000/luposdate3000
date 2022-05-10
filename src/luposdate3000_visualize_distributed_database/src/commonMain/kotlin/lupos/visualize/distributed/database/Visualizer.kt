package lupos.visualize.distributed.database

import  lupos.simulator_db.luposdate3000.PendingWork
import simora.ILogger
import simora.SimulationRun
import simora.IPayload
import lupos.shared.inline.File
import lupos.simulator_db.luposdate3000.Package_Luposdate3000_Abstract
import lupos.shared.inline.dynamicArray.ByteArrayWrapperExt

public class Visualizer : ILogger {
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
                        g_query.edges.add(DotEdge("s$dep", "r$dep").setLabel("${ByteArrayWrapperExt.getSize(pck.data)}"))
                    }
                }
            }
        }
    }

    override fun onSendNetworkPackage(src: Int, dest: Int, hop: Int, pck: IPayload, delay: Long) {}
    override fun onSendPackage(src: Int, dest: Int, pck: IPayload) {}
    override fun onShutDown() {
        for ((k, v) in graphs) {
            File("graph$k.dot").withOutputStream { out ->
                out.println(v.toDotString())
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
//                val g_device = g_query.subgraphs.getOrPut("device_${data.deviceAdress}", { DotGraph() })
                var dataId = if (data.dataID == -1) {
                    "query_root"
                } else {
                    "query_part_${data.dataID}"
                }
                val g_subgraph = g_query.subgraphs.getOrPut(dataId, { DotGraph() })
                val centerNode = counter++
                g_subgraph.nodes.add(DotNode("c$centerNode"))
                for (dep in data.dependencies) {
                    g_subgraph.nodes.add(DotNode("r$dep"))
                    g_subgraph.edges.add(DotEdge("r$dep", "c$centerNode"))
                }
                for ((dest, _) in data.destinations) {
                    g_subgraph.nodes.add(DotNode("s$dest"))
                    g_subgraph.edges.add(DotEdge("c$centerNode", "s$dest"))
                }
            }
        }
    }
}

internal data class DotNode(internal val name: String) {
    internal fun toDotString(indention: String): String {
        val res = StringBuilder()
        res.appendLine("${indention}$name;");
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
            res.appendLine("${indention}$start -> $end [label=\"$label\"];");
        } else {
            res.appendLine("${indention}$start -> $end;");
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
        res.appendLine("  overlap = scale;");
        res.appendLine("  splines = true;");
        res.appendLine("}");
        return res.toString()
    }

    internal fun toDotString(indention: String): String {
        val res = StringBuilder()
        for (node in nodes) {
            res.append(node.toDotString(indention))
        }
        for ((label, g) in subgraphs) {
            res.appendLine("${indention}subgraph cluster$label {")
            res.appendLine("  ${indention}label = \"$label\";");
            res.append(g.toDotString("  ${indention}"))
            res.appendLine("$indention}");
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

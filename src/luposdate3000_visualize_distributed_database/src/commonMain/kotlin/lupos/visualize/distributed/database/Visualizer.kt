package lupos.visualize.distributed.database

import simora.ILogger
import simora.SimulationRun
import simora.IPayload

public class Visualizer : ILogger {

    override fun addConnectionTable(src: Int, dest: Int, hop: Int) {}
    override fun addDevice(address: Int, x: Double, y: Double) {}
    override fun initialize(simRun: SimulationRun) {}
    override fun onReceiveNetworkPackage(address: Int, pck: IPayload) {}
    override fun onReceivePackage(address: Int, pck: IPayload) {}
    override fun onSendNetworkPackage(src: Int, dest: Int, hop: Int, pck: IPayload, delay: Long) {}
    override fun onSendPackage(src: Int, dest: Int, pck: IPayload) {}
    override fun onShutDown() {}
    override fun onStartSimulation() {}
    override fun onStartUp() {}
    override fun onStartUpRouting() {}
    override fun onStopSimulation() {}
    override fun reset(label: String, finish: Boolean) {}
}

internal data class DotNode(internal val name: String) {
}

internal data class DotEdge(internal val start: String, internal val end: String) {
}

internal class DotGraph() {
    internal val subgraphs = mutableMapOf<String, DotGraph>()
    internal val nodes = mutableSetOf<DotNode>()
    internal val edges = mutableSetOf<DotEdge>()
    override fun toDotString(): String {
        res.appendLine("${indention}subgraph $label {")
        res.append(toDotString("  "))
        res.appendLine("$indention}");
    }

    override fun toDotString(indention: String): String {
        val res = StringBuilder()
        for ((name, g) in subgraphs) {
            res.appendLine("${indention}subgraph $label {")
            res.appendLine("  ${indention}label = \"$label\"");
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

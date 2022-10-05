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
    internal val connections = mutableSetOf<Pair<Int, Int>>()
    internal val connectionsReceivers = mutableSetOf<Int>()
    override fun addConnectionTable(src: Int, dest: Int, hop: Int) {
    }
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
                        val path = mutableListOf<Int>()
                        path.addAll(pck.getAllHops())
                        path.add(address)
                        for (i in 0 until path.size - 1) {
                            g_query.edges.add(DotEdge("msg${path[i]}at$dep", "msg${path[i + 1]}at$dep").setLabel("${ByteArrayWrapperExt.getSize(pck.data)}"))
                        }
                        for (i in 0 until path.size) {
                            val g_device = g_query.subgraphs.getOrPut("device_${path[i]}", { DotGraph() })
                            g_device.addNode("msg${path[i]}at$dep", 1)
                        }
                    }
                }
            }
        }
    }

    override fun onSendNetworkPackage(src: Int, dest: Int, hop: Int, pck: IPayload, delay: Long) {
        if (src <hop) {
            connections.add(src to hop)
        } else if (hop <src) {
            connections.add(hop to src)
        }
    }
    override fun onSendPackage(src: Int, dest: Int, pck: IPayload) {
        connectionsReceivers.add(dest)
    }
    override fun onShutDown() {
        for ((k, v) in graphs) {
            if (relevantQueryIDs.contains(k)) {
                File("graph$k.dot").withOutputStream { out ->
                    out.println(v.toDotString())

                    out.println("graph G2 {")
                    for ((a, b) in connections) {
// if(connectionsReceivers.contains(a) && connectionsReceivers.contains(b)){
                        out.println("  node" + a + " -- node" + b + ";")
// }
                    }
                    out.println("  overlap = scale;")
                    out.println("  splines = true;")
                    out.println("}")
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
                ConverterBinaryToPOPDot(data.deviceAdress).decode(data.query, data.data, data.dataID, g_subgraph, { counter++ })
            }
            is Package_Query -> {
                relevantQueryIDs.add(data.queryID)
            }
        }
    }
}

internal class DotNode(internal val label: String, internal val id: String, internal val color: Int, internal val _do_not_use: Boolean) {
    internal companion object {
        operator fun invoke(label: String): DotNode {
            return DotNode(label.replace("#[0-9]*".toRegex(), ""), label.replace("[^a-zA-Z0-9]".toRegex(), ""), 0, false)
        }

        operator fun invoke(label: String, color: Int): DotNode {
            return DotNode(label.replace("#[0-9]*".toRegex(), ""), label.replace("[^a-zA-Z0-9]".toRegex(), ""), color, false)
        }
    }

    internal fun toDotString(indention: String): String {
        val res = StringBuilder()
        val color_string = when (color) {
            1 -> "gray"
            2 -> "red"
            3 -> "green"
            else -> "white"
        }
        res.appendLine("${indention}$id [label = \"$label\", fillcolor=$color_string, style=filled];")
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
        res.appendLine("digraph G1 {")
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

    internal fun addNode(label: String, color: Int): String {
        val node = DotNode(label, color)
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

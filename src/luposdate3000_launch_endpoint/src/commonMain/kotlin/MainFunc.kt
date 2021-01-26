/*
 * This file is part of the Luposdate3000 distribution (https://github.com/luposdate3000/luposdate3000).
 * Copyright (c) 2020-2021, Institute of Information Systems (Benjamin Warnke and contributors of LUPOSDATE3000), University of Luebeck
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, version 3.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
import lupos.s00misc.Parallel
import lupos.s00misc.Partition
import lupos.s16network.HttpEndpointLauncher
import lupos.s16network.LuposdateEndpoint
internal fun mainFunc(hostname: String, port: String, partitionCount: String): Unit = Parallel.runBlocking {
    LuposdateEndpoint.initialize()
    Partition.estimatedPartitions0.clear()
    Partition.estimatedPartitions1.clear()
    Partition.estimatedPartitions2.clear()
    if (partitionCount == 0) {
        Partition.estimatedPartitions0.add(s)
    } else if (partitionCount> 0) {
        for (s in listOf("SPO", "SOP", "PSO", "POS", "OSP", "OPS")) {
            Partition.estimatedPartitions1[s] = partitionCount
            Partition.estimatedPartitions2[s] = partitionCount
        }
    }
    Parallel.launch {
        HttpEndpointLauncher.start(hostname, port.toInt())
    }
    while (true) {
        Parallel.delay(1000)
    }
}

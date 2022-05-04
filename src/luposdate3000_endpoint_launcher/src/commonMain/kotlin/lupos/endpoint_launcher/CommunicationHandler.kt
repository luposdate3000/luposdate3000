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
package lupos.endpoint_launcher

import lupos.network.wrapper.Socket
import lupos.shared.ConnectionFailedException
import lupos.shared.ICommunicationHandler
import lupos.shared.IMyInputStream
import lupos.shared.IMyOutputStream
import lupos.shared.network.URLEncoder
public class CommunicationHandler : ICommunicationHandler {
    override fun sendData(targetHost: String, path: String, params: Map<String, String>, queryID: Int) {
        val content = StringBuilder()
        var first = true
        for ((k, v) in params) {
            if (!first) {
                content.append("&")
            }
            first = false
            content.append("$k=${URLEncoder.encode(v)}")
        }
        val contentStr = content.toString()
        val header = "POST $path\nContent-Length: ${contentStr.length}\n\n"
        val data = header + content
        val target = targetHost.split(":")
        val targetName = target[0]
        val targetPort = if (target.size > 1) {
            target[1].toInt()
        } else {
            80
        }
        val client = Socket(targetName, targetPort)
        val input = client.getInputStream()
        val output = client.getOutputStream()
        output.write(data.encodeToByteArray())
        output.flush()
        var line = input.readLine()
        var status: Int? = null
        while (line != null && line.isNotEmpty()) {
            if (line.startsWith("HTTP/1.1")) {
                val t = line.split(" ")
                if (t.size == 3) {
                    status = t[1].toInt()
                }
            }
            line = input.readLine()
        }
        input.close()
        output.close()
        if (status != 200) {
            throw ConnectionFailedException(status)
        }
    }

    override fun openConnection(targetHost: String, path: String, params: Map<String, String>, queryID: Int): Pair<IMyInputStream, IMyOutputStream> {
        var p = "POST $path"
        var first = true
        for ((k, v) in params) {
            p += if (first) {
                "?"
            } else {
                "&"
            }
            first = false
            p += "$k=${URLEncoder.encode(v)}"
        }
        return openConnection(targetHost, "$p\n\n", queryID)
    }

    override fun openConnection(targetHost: String, header: String/*caller MUST finish the header by appending an empty line*/, queryID: Int): Pair<IMyInputStream, IMyOutputStream> {
        val target = targetHost.split(":")
        val targetName = target[0]
        val targetPort = if (target.size > 1) {
            target[1].toInt()
        } else {
            80
        }
        val client = Socket(targetName, targetPort)
        val input = client.getInputStream()
        val output = client.getOutputStream()
        val buf = header.encodeToByteArray()
        output.write(buf, buf.size)
        output.flush()
        return Pair(input, output)
    }
}

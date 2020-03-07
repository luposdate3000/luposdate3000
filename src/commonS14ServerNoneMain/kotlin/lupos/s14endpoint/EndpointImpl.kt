package lupos.s14endpoint
import lupos.s04logicalOperators.Query

import kotlin.jvm.JvmField


class EndpointServerImpl(hostname: String = "localhost", port: Int = 80) : EndpointServer(hostname, port) {
    override suspend fun start() {}
}

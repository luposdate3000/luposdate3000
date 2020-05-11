package lupos.s14endpoint

import lupos.s00misc.Coverage

class EndpointServerImpl(hostname: String = "localhost", port: Int = 80) : EndpointServer(hostname, port) {
    override suspend fun start() {}
}

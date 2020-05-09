package lupos.s14endpoint


class EndpointServerImpl(hostname: String = "localhost", port: Int = 80) : EndpointServer(hostname, port) {
    override suspend fun start() {}
}

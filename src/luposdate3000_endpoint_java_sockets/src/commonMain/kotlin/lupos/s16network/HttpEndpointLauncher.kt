package lupos.s16network
@OptIn(ExperimentalStdlibApi::class)
expect object HttpEndpointLauncher {
    /*suspend*/ fun start(hostname: String = "localhost", port: Int = 80)
}

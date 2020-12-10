package lupos.s16network
@OptIn(ExperimentalStdlibApi::class)
actual object HttpEndpointLauncher {
    actual /*suspend*/ fun start(hostname: String, port: Int) {}
}

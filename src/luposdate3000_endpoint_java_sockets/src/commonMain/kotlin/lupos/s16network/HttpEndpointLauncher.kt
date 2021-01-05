package lupos.s16network
@OptIn(ExperimentalStdlibApi::class)
public expect object HttpEndpointLauncher {
    /*suspend*/ public fun start(hostname: String = "localhost", port: Int = 80)
}

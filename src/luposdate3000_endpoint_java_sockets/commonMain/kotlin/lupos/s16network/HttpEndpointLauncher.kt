package lupos.s16network



@UseExperimental(ExperimentalStdlibApi::class)
expect object HttpEndpointLauncher {
    suspend fun start(hostname: String = "localhost", port: Int = 80) 
}

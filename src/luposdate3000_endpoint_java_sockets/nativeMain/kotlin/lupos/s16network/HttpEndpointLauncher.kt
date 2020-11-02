package lupos.s16network



@UseExperimental(ExperimentalStdlibApi::class)
actual object HttpEndpointLauncher {
actual    suspend fun start(hostname: String , port: Int ) {}
}

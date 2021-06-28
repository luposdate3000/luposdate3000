package lupos.simulator_iot.net

internal class NetworkPackage(
    internal val sourceAddress: Int,
    internal val destinationAddress: Int,
    internal val payload: IPayload
) {

    internal companion object {
        // Ipv6 has fixed header size of 40 Bytes
        const val headerSize: Int = 40
    }

    override fun toString(): String {
        return "NetworkPackage(src=$sourceAddress, dest=$destinationAddress, payload=$payload)"
    }


}

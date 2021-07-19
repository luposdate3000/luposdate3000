package lupos.simulator_iot.models.net

public class NetworkPackage(
    internal val sourceAddress: Int,
    internal val destinationAddress: Int,
    internal val payload: IPayload
) {
    internal val pckSize = headerSize + payload.getSizeInBytes()

    internal companion object {
        // Ipv6 has fixed header size of 40 Bytes
        const val headerSize: Int = 40
    }

    override fun toString(): String {
        return "Pck(src $sourceAddress, dest $destinationAddress, size $pckSize, payload $payload)"
    }
}

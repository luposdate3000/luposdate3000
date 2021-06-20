package lupos.simulator_iot.net


public class NetworkPackage(
    public val sourceAddress: Int,
    public val destinationAddress: Int,
    public val payload: IPayload) {

    internal companion object {
        //Ipv6 has fixed header size of 40 Bytes
        const val headerSize: Int = 40
    }
}

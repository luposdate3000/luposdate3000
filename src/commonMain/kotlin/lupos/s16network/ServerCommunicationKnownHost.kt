package lupos.s16network


abstract class ServerCommunicationKnownHostBase(val hostname: String, val port: Int) : Comparable<ServerCommunicationKnownHostBase> {
    override fun equals(other: Any?) = other is ServerCommunicationKnownHostBase && hostname == other.hostname && port == other.port
    override fun hashCode() = hostname.hashCode() + port
    override fun compareTo(other: ServerCommunicationKnownHostBase): Int {
        var res = hostname.compareTo(other.hostname)
        if (res != 0) {
            return res
        }
        res = port - other.port
        return res
    }
}

package lupos.s00misc
public interface ICommunicationHandler {
    public fun sendData(targetHost: String, path: String, params: Map<String, String>)
}

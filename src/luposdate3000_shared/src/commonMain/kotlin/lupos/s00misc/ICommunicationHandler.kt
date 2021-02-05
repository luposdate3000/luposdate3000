package lupos.s00misc
public interface ICommunicationHandler {
    public fun sendData(targetHost: String, path: String, message: String)
}

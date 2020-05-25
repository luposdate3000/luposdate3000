package lupos.s14endpoint

import lupos.s00misc.Coverage
import lupos.s00misc.DynamicByteArray

object EndpointClientImpl {
    fun encodeParam(key: String, value: Any) = key + "=" + value
    suspend fun requestGetBytes(url: String): ByteArray {
        return ByteArray(0)
    }

    suspend fun requestPostBytes(url: String, data: DynamicByteArray): ByteArray {
        return ByteArray(0)
    }

    suspend fun requestGetString(url: String): String {
        return ""
    }

    suspend fun requestPostString(url: String, data: DynamicByteArray): String {
        return ""
    }

    suspend fun requestPostString(url: String, data: String): String {
        return ""
    }
}

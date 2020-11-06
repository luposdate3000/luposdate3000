package lupos.s00misc
import platform.posix.getenv
import kotlinx.cinterop.toKString
internal actual object Configuration {
    actual fun getEnv(key: String, default: String?): String? {
val tmp=getenv(key)?.toKString()
if(tmp!=null){
return tmp
}
return default
    }
}

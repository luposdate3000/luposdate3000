package lupos.s00misc
import java.io.File
internal actual object Platform {
    @JvmField
    val userHome:String = System.getProperty("user.home")
    @JvmField
    val operatingSystem = if (System.getProperty("os.name").contains("Windows")) EOperatingSystem.Windows else EOperatingSystem.Linux
    @JvmField
    val pathSepatator = if (operatingSystem == EOperatingSystem.Windows) "\\\\" else "/"

actual inline fun getOperatingSystem()=operatingSystem
    actual inline fun getUserHome() = userHome
    actual inline fun getPathSeparator() = pathSepatator
    actual inline fun findNamedFileInDirectory(dir: String, name: String): List<String> {
        val res = mutableListOf<String>()
        for (f in File(dir).walk()) {
            if (f.isFile()) {
                if (f.getName() == name) {
                    res.add(f.toString())
                }
            }
        }
        return res
    }
}

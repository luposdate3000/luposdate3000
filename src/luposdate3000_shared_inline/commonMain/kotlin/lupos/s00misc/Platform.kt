package lupos.s00misc
internal expect object Platform {
inline fun getUserHome():String
inline fun getPathSeparator():String
inline fun findNamedFileInDirectory(dir:String,name:String):List<String>
inline fun getOperatingSystem():EOperatingSystem
inline fun getNullFileName():String
}

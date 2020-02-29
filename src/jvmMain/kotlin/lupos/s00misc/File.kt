package lupos.s00misc

import java.io.InputStream

class File(val filename:String){

fun readAsString()= java.io.File(filename).inputStream().bufferedReader().use { it.readText() }
fun walk(action:(String)->Unit){
java.io.File(filename).walk().forEach {
action(it.toRelativeString(java.io.File(".")))
}
}
fun readAsDynamicByteArray():DynamicByteArray{
var res:DynamicByteArray?=null
java.io.File(filename).inputStream().use { instream ->
 val data = instream.readBytes()
        res = DynamicByteArray(data)
}
return res!!
}
}

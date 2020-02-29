package lupos.s00misc


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
fun write(buffer:DynamicByteArray){
java.io.File(filename).outputStream().use { out ->
                val data = buffer.finish()
                out.write(data, 0, buffer.pos)
            }
}
fun printWriter(action:(java.io.PrintWriter)->Unit)=java.io.File(filename).printWriter().use{
action(it)
}
}

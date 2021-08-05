package lupos.parser.turtle
import java.io.FileReader
actual public class MyFileReader actual public constructor(name:String){
private val reader=FileReader(name)
public actual fun read(buf:CharArray):Int=reader.read(buf)
public actual fun read(buf:CharArray,offset:Int,len:Int):Int=reader.read(buf,offset,len)
}

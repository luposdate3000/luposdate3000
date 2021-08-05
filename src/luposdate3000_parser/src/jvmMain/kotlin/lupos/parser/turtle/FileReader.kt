package lupos.parser.turtle
import java.io.FileReader
actual public class MyFileReader public constructor(name:String){
val reader=FileReader(name)
actual fun read(buf:CharArray)=reader.read(buf)
}

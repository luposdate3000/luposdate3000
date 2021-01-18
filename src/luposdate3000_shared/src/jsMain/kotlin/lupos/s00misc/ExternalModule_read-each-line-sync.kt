package ext.readEachLineSync

public fun readEachLine(filename:String,action:(String)->Unit){
val f:(String,(String)->Unit)->Unit = js("require(\"read-each-line-sync\")")
f(filename,action)
}

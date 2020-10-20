package lupos.s00misc

expect object Crypto{
fun md5(value:String):String
fun sha256(value:String):String
fun sha1(value:String):String
fun uuid():String
}



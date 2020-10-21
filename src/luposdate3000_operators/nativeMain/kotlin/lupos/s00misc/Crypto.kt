package lupos.s00misc

actual  object Crypto{
actual fun md5(value:String):String=throw  NotImplementedException("Crypto", "md5 not implemented")
actual fun sha256(value:String):String=throw  NotImplementedException("Crypto", "sha256 not implemented")
actual fun sha1(value:String):String=throw  NotImplementedException("Crypto", "sha1 not implemented")
actual fun uuid():String=throw  NotImplementedException("Crypto", "uuid not implemented")
}


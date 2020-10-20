package lupos.s00misc

actual  object Crypto{
actual fun md5(value:Int):Int=throw  NotImplementedException("Crypto", "md5 not implemented")
actual fun sha256(value:Int):Int=throw  NotImplementedException("Crypto", "sha256 not implemented")
actual fun sha1(value:Int):Int=throw  NotImplementedException("Crypto", "sha1 not implemented")
actual fun uuid():String=throw  NotImplementedException("Crypto", "uuid not implemented")
}


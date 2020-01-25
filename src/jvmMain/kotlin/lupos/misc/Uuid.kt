package lupos.misc

class Uuid(){
	
	companion object {
	        var global_uuid = 0L
	    }
	fun next(): Long{
		return global_uuid++
	}
}

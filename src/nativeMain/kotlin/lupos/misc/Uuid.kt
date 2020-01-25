package lupos.misc

import kotlin.native.concurrent.AtomicLong

class Uuid(){
	private var max_id = AtomicLong(0L);
	fun next(): Long{
		return max_id.addAndGet(delta)
	}
}

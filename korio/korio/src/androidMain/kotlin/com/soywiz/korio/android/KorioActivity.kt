package com.soywiz.korio.android

/*
object KorioApp {
	val initOnce = Once()
	val resized = Signal<Unit>()
}
open class KorioActivity : Activity(), Extra by Extra.Mixin() {
	lateinit var eventLoop: EventLoop
	override final fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		KorioAndroidInit(this)
		KorioApp.initOnce {
			EventLoop.main {
				eventLoop = this@main
				println()
				main(arrayOf())
			}
		}
	}
	override fun onWindowFocusChanged(hasFocus: Boolean) {
		super.onWindowFocusChanged(hasFocus)
		eventLoop.queue { KorioApp.resized(Unit) }
	}
	suspend open fun requestPermission(name: String): Boolean {
		eventLoop.sleep(1000)
		return false
	}
	/*
	override fun onConfigurationChanged(newConfig: Configuration) {
		//println(newConfig.orientation)
		resized(Unit)
	}
	*/
	suspend protected open fun main(args: Array<String>) {
	}
}
*/

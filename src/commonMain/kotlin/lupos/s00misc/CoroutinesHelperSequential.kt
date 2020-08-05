package lupos.s00misc

import kotlin.jvm.JvmField
import kotlinx.coroutines.channels.Channel.Factory.UNLIMITED
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.runBlocking
import lupos.s00misc.Coverage

object CoroutinesHelperSequential {
    @JvmField
    val channelType = UNLIMITED
}

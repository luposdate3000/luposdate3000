package lupos.s00misc

import kotlin.jvm.JvmField
import kotlinx.coroutines.channels.Channel.Factory.UNLIMITED

object CoroutinesHelperSequential {
    @JvmField
    val channelType = UNLIMITED
}

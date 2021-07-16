package lupos.simulator_iot.queryproc.pck

import lupos.simulator_db.IDatabasePackage
import lupos.simulator_iot.models.net.IPayload

internal class DBQuerySenderPackage(
    internal val content: IDatabasePackage
) : IPayload {
    override fun getSizeInBytes(): Int {
        return content.getPackageSizeInBytes()
    }
}

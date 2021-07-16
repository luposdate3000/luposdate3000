package lupos.simulator_iot

import lupos.simulator_iot.models.queryproc.DatabaseAdapter
import kotlin.test.Test

class DatabaseAdapterTest {

    @Test
    fun createAndDeleteFiles() {
        val device = Stubs.createEmptyDevice(1)
        val db = DatabaseAdapter(device, true)
        db.startUp()
        db.shutDown()
    }
}

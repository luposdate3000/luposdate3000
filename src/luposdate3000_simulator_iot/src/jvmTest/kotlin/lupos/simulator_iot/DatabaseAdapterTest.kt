package lupos.simulator_iot

import kotlin.test.Ignore
import kotlin.test.Test

class DatabaseAdapterTest {
    @Ignore
    @Test
    fun createAndDeleteFiles() {
        val device = Stubs.createEmptyDevice(1)
        val db = DatabaseAdapter(device)
        db.startUp()
        db.shutDown()
    }
}

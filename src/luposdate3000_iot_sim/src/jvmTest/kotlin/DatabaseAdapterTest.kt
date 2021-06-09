package lupos.iot_sim

import kotlin.test.*

class DatabaseAdapterTest {
    @Test
    fun createAndDeleteFiles() {
        val device = Stubs.createEmptyDevice(1)
        val db = DatabaseAdapter(device)
        db.startUp()
        db.shutDown()
    }
}

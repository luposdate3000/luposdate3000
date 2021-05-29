import org.junit.jupiter.api.Test

class IDatabaseAdapterTest {
    @Test
    fun createAndDeleteFiles() {
        val device = Stubs.createEmptyDevice(1)
        val db = DatabaseAdapter(device)
        db.startUp()
        db.shutDown()
    }
}
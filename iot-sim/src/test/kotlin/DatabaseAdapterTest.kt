import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class DatabaseAdapterTest {
    @Test
    fun createAndDeleteFiles() {
        val device = Stubs.createEmptyDevice(1)
        val db = DatabaseAdapter(device)
        db.startUp()
        db.shutDown()
    }



}
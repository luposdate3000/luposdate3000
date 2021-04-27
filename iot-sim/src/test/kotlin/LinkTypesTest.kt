import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class LinkTypesTest {
    @Test
    fun `get sorted LinkType Indices`() {
        val linkTypeW = LinkType("W", 51, 13 )
        val linkTypeX = LinkType("X", 50, 7 )
        val linkTypeY = LinkType("Y", 50, 8 )
        val linkTypeZ = LinkType("Z", 48, 9 )
        LinkTypes.sortedLinkTypes = arrayOf(linkTypeW, linkTypeX, linkTypeY, linkTypeZ)

        val expected1 = intArrayOf(1, 2)
        val actual1 = LinkTypes.getSortedLinkTypeIndices(arrayListOf(linkTypeY, linkTypeZ))
        val expected2 = intArrayOf(0, 3)
        val actual2 = LinkTypes.getSortedLinkTypeIndices(arrayListOf(linkTypeW, linkTypeX))

        Assertions.assertTrue(expected1.contentEquals(actual1))
        Assertions.assertTrue(expected2.contentEquals(actual2))
    }
}
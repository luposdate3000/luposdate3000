package layer1.net

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import kotlin.math.pow


class AdjacencyMatrixTest {


    @Test
    fun `A new matrix should be empty`() {
        val matrix = AdjacencyMatrix<Int, Int>()
        val noVertices =  matrix.vertices.isEmpty()
        val noEdges = matrix.edges.isEmpty()
        assertTrue(noVertices)
        assertTrue(noEdges)
    }

    @ParameterizedTest
    @ValueSource(ints = [1, 2, 53])
    fun `Add a new vertex to a new matrix`(verticesCount: Int) {
        val matrix = AdjacencyMatrix<Int, Int>()
        for (x in 0 until verticesCount) {
            matrix.addVertex(4)
        }
        val hasXVertices =  matrix.vertices.size == verticesCount
        val matrixHasXRowSize = matrix.edges.size == verticesCount
        assertTrue(hasXVertices)
        assertTrue(matrixHasXRowSize)
    }

    @Test
    fun `New vertex in new matrix has 1 cell`() {
        val matrix = AdjacencyMatrix<String, Int>()
        matrix.addVertex("A new Vertex")
        val has1Row =  matrix.edges.size == 1
        val has1Column = matrix.edges[0].size == 1
        val has1Vertex = matrix.vertices.size == 1
        assertTrue(has1Row)
        assertTrue(has1Column)
        assertTrue(has1Vertex)
    }

    @ParameterizedTest
    @ValueSource(ints = [1, 2, 15])
    fun `Add x vertices the matrix have x^2 cells`(x: Int) {
        val matrix = AdjacencyMatrix<String, String>()
        for (i in 0 until x) {
            matrix.addVertex("a new vertex")
        }
        val cellCountExcepted = x.toDouble().pow(2).toInt()
        var cellCountResult = 0
        for(column in 0 until matrix.edges.size) {
            for(row in 0 until matrix.edges[column].size) {
                cellCountResult++
            }
        }
        assertEquals(cellCountExcepted, cellCountResult)
    }

    @ParameterizedTest
    @ValueSource(ints = [0, 1, 2, 20])
    fun `All Cells are empty`(verticesCount: Int) {
        val matrix = AdjacencyMatrix<String, String>()
        for (i in 0 until verticesCount) {
            matrix.addVertex("a new vertex")
        }
        var isEmpty = true
        for(column in 0 until matrix.edges.size) {
            for(row in 0 until matrix.edges[column].size) {
                isEmpty = matrix.edges[column][row] == null
            }
        }
        assertTrue(isEmpty)
    }

    @Test
    fun `Add directed edge`() {
        val matrix = AdjacencyMatrix<String, String>()
        val verticesCount = 4
        for (i in 0 until verticesCount) {
            matrix.addVertex("")
        }
        val edge1 = "Edge1"
        val edge2 = "Edge2"
        val edge3 = "Edge3"
        val edge4 = "Edge4"
        matrix.addDirectedEdge(0,0, edge1)
        assertTrue(matrix.edges[0][0] == edge1)
        matrix.addDirectedEdge(0,verticesCount-1, edge2)
        assertTrue(matrix.edges[0][verticesCount-1] == edge2)
        matrix.addDirectedEdge(verticesCount-1,0, edge3)
        assertTrue(matrix.edges[verticesCount-1][0] == edge3)
        matrix.addDirectedEdge(verticesCount-1,verticesCount-1, edge4)
        assertTrue(matrix.edges[verticesCount-1][verticesCount-1] == edge4)
    }



}
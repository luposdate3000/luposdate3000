package graph

import AdjacencyMatrix
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


class AdjacencyMatrixTest {


    @Test
    fun `A new matrix with empty vertices is empty`() {
        val vertices: List<String> = arrayListOf()
        val matrix = AdjacencyMatrix<String, Int>(vertices)
        val numVertices =  matrix.getVerticesCount()
        assertEquals(0, numVertices)
    }

    @Test
    fun `matrix with one vertex`() {
        val vertexName = "first"
        val vertices: List<String> = arrayListOf(vertexName)
        val matrix = AdjacencyMatrix<String, Int>(vertices)
        assertEquals(1, matrix.getVerticesCount())
        assertEquals(0, matrix.getIndexOf(vertexName))
        assertEquals(null, matrix.getEdge(vertexName, vertexName))
    }

    @Test
    fun `matrix with many vertices`() {
        val vertices: List<String> = arrayListOf("a","b","c","d","e","f","g","h","i")
        val matrix = AdjacencyMatrix<String, Int>(vertices)
        assertEquals(vertices.size, matrix.getVerticesCount())
        assertEquals(0, matrix.getIndexOf("a"))
        assertEquals(vertices.size-1, matrix.getIndexOf("i"))
        assertEquals(null, matrix.getEdge("a", "d"))
        assertEquals(null, matrix.getEdge("d", "a"))
    }

    @Test
    fun `Add directed edge`() {
        val vertices: List<String> = arrayListOf("a","b","c","d","e","f","g","h","i")
        val matrix = AdjacencyMatrix<String, String>(vertices)
        val edge1 = "Edge1"
        val edge2 = "Edge2"
        Assertions.assertNull(matrix.getEdge("a", "b"))
        matrix.addDirectedEdge("a","b", edge1)
        assertEquals(edge1, matrix.getEdge("a", "b"))

        matrix.addDirectedEdge("a","b", edge2)
        assertEquals(edge2, matrix.getEdge("a", "b"))
    }

    @Test
    fun `Add undirected edge`() {
        val vertices: List<String> = arrayListOf("a","b","c","d","e","f","g","h","i")
        val matrix = AdjacencyMatrix<String, String>(vertices)
        val edge1 = "Edge1"
        matrix.addUndirectedEdge("b","i", edge1)
        assertEquals(edge1, matrix.getEdge("b", "i"))
        assertEquals(edge1, matrix.getEdge("i", "b"))
    }




}
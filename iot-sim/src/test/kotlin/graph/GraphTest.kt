package graph

import Graph
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


class GraphTest {


    @Test
    fun `A new graph with empty vertices is empty`() {
        val vertices: List<String> = arrayListOf()
        val graph = Graph<String, Int>(vertices)
        val numVertices =  graph.getVerticesCount()
        assertEquals(0, numVertices)
    }

    @Test
    fun `graph with one vertex`() {
        val vertexName = "first"
        val vertices: List<String> = arrayListOf(vertexName)
        val graph = Graph<String, Int>(vertices)
        assertEquals(1, graph.getVerticesCount())
        assertEquals(0, graph.getIndexOf(vertexName))
        assertEquals(null, graph.getEdge(vertexName, vertexName))
    }

    @Test
    fun `graph with many vertices`() {
        val vertices: List<String> = arrayListOf("a","b","c","d","e","f","g","h","i")
        val graph = Graph<String, Int>(vertices)
        assertEquals(vertices.size, graph.getVerticesCount())
        assertEquals(0, graph.getIndexOf("a"))
        assertEquals(vertices.size-1, graph.getIndexOf("i"))
        assertEquals(null, graph.getEdge("a", "d"))
        assertEquals(null, graph.getEdge("d", "a"))
    }

    @Test
    fun `Add undirected edge`() {
        val vertices: List<String> = arrayListOf("a","b","c","d","e","f","g","h","i")
        val graph = Graph<String, String>(vertices)
        val edge1 = "Edge1"
        Assertions.assertNull(graph.getEdge("b", "i"))
        graph.addUndirectedEdge("b","i", edge1)
        assertEquals(edge1, graph.getEdge("b", "i"))
        assertEquals(edge1, graph.getEdge("i", "b"))
    }

    @Test
    fun `Rewrite an undirected edge`() {
        val vertices: List<String> = arrayListOf("a","b","c","d","e","f","g","h","i")
        val graph = Graph<String, String>(vertices)
        val edge1 = "Edge1"
        val edge2 = "Edge2"
        graph.addUndirectedEdge("i","a", edge1)
        assertEquals(edge1, graph.getEdge("i", "a"))
        assertEquals(edge1, graph.getEdge("a", "i"))

        graph.addUndirectedEdge("i","a", edge2)
        assertEquals(edge2, graph.getEdge("i", "a"))
        assertEquals(edge2, graph.getEdge("a", "i"))
    }

    @Test
    fun `Add self loop`() {
        val vertices: List<String> = arrayListOf("a","b")
        val graph = Graph<String, String>(vertices)
        val edge1 = "loop"
        graph.addUndirectedEdge("b","b", edge1)
        assertEquals(edge1, graph.getEdge("b", "b"))
    }



}
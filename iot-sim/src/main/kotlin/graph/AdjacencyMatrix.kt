package graph

class AdjacencyMatrix<T, E>{

    val vertices = arrayListOf<Vertex<T>>()
    val edges = arrayListOf<ArrayList<E?>>()

    fun addVertex(data: T): Vertex<T> {
        val vertexIndex = vertices.size
        val vertex = Vertex(vertexIndex, data)
        vertices.add(vertex)
        addEmptyColumns()
        addEmptyRow()
        return vertex
    }

    private fun addEmptyColumns() {
        for(row in 0 until edges.size) {
            edges[row].add(null)
        }
    }

    private fun addEmptyRow() {
        val newRow = ArrayList<E?>(vertices.size)
        for (i in 0 until vertices.size) {
            newRow.add(null)
        }
        edges.add(newRow)
    }

    fun addDirectedEdge(
        sourceIndex: Int,
        destinationIndex: Int,
        edge: E?
    ) {
        edges[sourceIndex][destinationIndex] = edge
    }

    fun addDirectedEdge(
        source: Vertex<T>,
        destination: Vertex<T>,
        edge: E?
    ) {
        addDirectedEdge(source.index, destination.index, edge)
    }



}

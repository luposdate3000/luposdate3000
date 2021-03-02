class AdjacencyMatrix<V, E>(private val vertices: List<V>) {

    private val indices: MutableMap<V, Int> = HashMap(vertices.size)
    private val matrix = ArrayList<ArrayList<E?>>(vertices.size)
    init {
        buildInitialMatrix()
    }


    fun getVerticesCount() = vertices.size

    fun getIndexOf(vertex: V) = indices[vertex]!!

    private fun buildInitialMatrix() {
        for ((index, vertex) in vertices.withIndex()) {
            indices[vertex] = index
            addEmptyColumn()
            addEmptyRow()
        }
    }

    private fun addEmptyColumn() {
        for(row in 0 until matrix.size) {
            matrix[row].add(null)
        }
    }

    private fun addEmptyRow() {
        val newRow = ArrayList<E?>(vertices.size)
        for (i in vertices.indices) {
            newRow.add(null)
        }
       matrix.add(newRow)
    }

    fun addUndirectedEdge(one: V, two: V, edge: E) {
        addDirectedEdge(one, two, edge)
        addDirectedEdge(two, one, edge)
    }

    fun addDirectedEdge(from: V, to: V, edge: E) {
        val fromIndex = getIndexOf(from)
        val toIndex = getIndexOf(to)
        matrix[fromIndex][toIndex] = edge
    }

    fun getEdge(from: V, to: V): E? {
        val fromIndex = getIndexOf(from)
        val toIndex = getIndexOf(to)
        return matrix[fromIndex][toIndex]
    }

}

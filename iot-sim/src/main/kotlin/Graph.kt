class Graph<V, E>(private val vertices: List<V>) {

    private var vertexIndices: MutableMap<V, Int> = HashMap(vertices.size)

    private val adjacencyList: Array<HashMap<Int,E>> = Array(vertices.size) { HashMap() }


    init {
        for ((index, vertex) in vertices.withIndex()) {
            vertexIndices[vertex] = index
        }
    }


    fun getVerticesCount() = vertices.size


    fun getIndexOf(vertex: V) = vertexIndices[vertex]!!


    fun addUndirectedEdge(one: V, two: V, edge: E) {
        addDirectedEdge(one, two, edge)
        addDirectedEdge(two, one, edge)
    }

    fun addDirectedEdge(from: V, to: V, edge: E) {
        val fromIndex = getIndexOf(from)
        val toIndex = getIndexOf(to)
        adjacencyList[fromIndex][toIndex] = edge
    }

    fun getEdge(from: V, to: V): E? {
        val fromIndex = getIndexOf(from)
        val toIndex = getIndexOf(to)
        return adjacencyList[fromIndex][toIndex]
    }

}

package bellman

// Generated using Anthropic Claude and tweaked a small syntactic error
//
// Prompt:
// kotlin program that implements bellman ford algorithm and captures both
// shortest path weight and the also prints the shortest path to each vertex

// Edge class to represent weighted directed edges
data class Edge(val source: Int, val destination: Int, val weight: Int)

// Graph class
class Graph(val vertices: Int) {
    val edges = mutableListOf<Edge>()

    // Add edge to the graph
    fun addEdge(source: Int, destination: Int, weight: Int) {
        edges.add(Edge(source, destination, weight))
    }

    // Bellman-Ford algorithm implementation
    fun bellmanFord(source: Int): Pair<Array<Int>, Array<Int?>> {
        // Initialize distance array with infinity for all vertices except source
        val distance = Array(vertices) { if (it == source) 0 else Int.MAX_VALUE }

        // Initialize predecessor array to keep track of paths
        val predecessor = Array<Int?>(vertices) { null }

        // Relax all edges |V| - 1 times
        for (i in 1 until vertices) {
            for (edge in edges) {
                val u = edge.source
                val v = edge.destination
                val weight = edge.weight

                // Relax edge if possible
                if (distance[u] != Int.MAX_VALUE && distance[u] + weight < distance[v]) {
                    distance[v] = distance[u] + weight
                    predecessor[v] = u
                }
            }
        }

        // Check for negative-weight cycles
        for (edge in edges) {
            val u = edge.source
            val v = edge.destination
            val weight = edge.weight

            if (distance[u] != Int.MAX_VALUE && distance[u] + weight < distance[v]) {
                println("Graph contains negative weight cycle")
                // Return arrays with negative values to indicate a negative cycle
                return Pair(Array(vertices) { -1 }, Array(vertices) { null })
            }
        }

        return Pair(distance, predecessor)
    }

    // Function to get path from source to a given vertex
    fun getPath(predecessor: Array<Int?>, destination: Int): List<Int> {
        val path = mutableListOf<Int>()
        var current: Int? = destination

        // Reconstruct path by following predecessors
        while (current != null) {
            path.add(current)
            current = predecessor[current]
        }

        // Path is constructed in reverse order, so reverse it
        return path.reversed()
    }

    // Print results
    fun printResults(source: Int, distances: Array<Int>, predecessors: Array<Int?>) {
        println("Shortest paths from vertex $source:")

        for (i in 0 until vertices) {
            when {
                distances[i] == Int.MAX_VALUE -> println("Vertex $i: No path exists")
                distances[i] < 0 -> println("Vertex $i: Part of negative cycle")
                else -> {
                    val path = getPath(predecessors, i)
                    println("Vertex $i: Distance = ${distances[i]}, Path = ${path.joinToString(" -> ")}")
                }
            }
        }
    }
}

fun main() {
    // Create a sample graph
    val graph = Graph(5)

    // Add edges to the graph (source, destination, weight)
    graph.addEdge(0, 1, 6)
    graph.addEdge(0, 3, 7)
    graph.addEdge(1, 2, 5)
    graph.addEdge(1, 3, 8)
    graph.addEdge(1, 4, -4)
    graph.addEdge(2, 1, -2)
    graph.addEdge(3, 2, -3)
    graph.addEdge(3, 4, 9)
    graph.addEdge(4, 0, 2)
    graph.addEdge(4, 2, 7)

    // Source vertex
    val source = 0

    // Run Bellman-Ford algorithm
    val (distances, predecessors) = graph.bellmanFord(source)

    // Print results
    graph.printResults(source, distances, predecessors)
}
import java.util.*

// Generated using Anthropic Claude and tweaked a small syntactic error
// and avoided the 'processed' list
//
// Prompt 1:
// kotlin program that implements bellman ford algorithm and captures both
// shortest path weight and the also prints the shortest path to each vertex
//
// Prompt 2:
// Similarly give a solution for the dijkstra algorithm

// Edge class to represent weighted directed edges
data class Edge(val destination: Int, val weight: Int)

// Graph class using adjacency list representation
class Graph(val vertices: Int) {
    private val adjacencyList = Array(vertices) { mutableListOf<Edge>() }

    // Add edge to the graph
    fun addEdge(source: Int, destination: Int, weight: Int) {
        // Throw exception if weight is negative
        if (weight < 0) {adjacencyList
            throw IllegalArgumentException("Dijkstra's algorithm doesn't support negative weights")
        }
        adjacencyList[source].add(Edge(destination, weight))
    }

    // Dijkstra's algorithm implementation
    fun dijkstra(source: Int): Pair<Array<Int>, Array<Int?>> {
        // Distance array to store shortest distance from source to each vertex
        val distance = Array(vertices) { Int.MAX_VALUE }
        distance[source] = 0

        // Predecessor array to store the previous vertex in the shortest path
        val predecessor = Array<Int?>(vertices) { null }

        // Priority queue to get vertex with minimum distance
        val priorityQueue = PriorityQueue<Pair<Int, Int>>(compareBy { it.second })
        priorityQueue.add(Pair(source, 0))

        // Set to keep track of processed vertices
//        val processed = mutableSetOf<Int>()

        while (priorityQueue.isNotEmpty()) {
            // Get vertex with minimum distance
            val vertexPair = priorityQueue.poll()
            val u = vertexPair.first

            // Skip if already processed
            // or if the distance is larger than known distance - unnecessary
//            if ( in processed /*|| distU > distance[u]*/) continue

            // Remove processed
            priorityQueue.remove(vertexPair)

            // For each neighbor of u
            for (edge in adjacencyList[u]) {
                val v = edge.destination
                val weight = edge.weight

                // If there's a shorter path to v through u
                if (distance[u] != Int.MAX_VALUE && distance[u] + weight < distance[v]) {
                    // Update distance and predecessor
                    distance[v] = distance[u] + weight
                    predecessor[v] = u

                    // Add to priority queue
                    priorityQueue.add(Pair(v, distance[v]))
                }
            }
        }

        return Pair(distance, predecessor)
    }

    // Function to get path from source to a given vertex
    fun getPath(predecessor: Array<Int?>, destination: Int): List<Int> {
        val path = mutableListOf<Int>()
        var current: Int? = destination

        // If no path exists
        if (predecessor[destination] == null && destination != 0) {
            return emptyList()
        }

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
                else -> {
                    val path = getPath(predecessors, i)
                    if (path.isEmpty()) {
                        println("Vertex $i: No path exists")
                    } else {
                        println("Vertex $i: Distance = ${distances[i]}, Path = ${path.joinToString(" -> ")}")
                    }
                }
            }
        }
    }
}

fun main() {
    // Create a sample graph
    val graph = Graph(6)

    // Add edges to the graph (source, destination, weight)
    graph.addEdge(0, 1, 4)
    graph.addEdge(0, 2, 3)
    graph.addEdge(1, 2, 1)
    graph.addEdge(1, 3, 2)
    graph.addEdge(2, 3, 4)
    graph.addEdge(2, 4, 3)
    graph.addEdge(3, 4, 2)
    graph.addEdge(3, 5, 1)
    graph.addEdge(4, 5, 6)

    // Source vertex
    val source = 0

    // Run Dijkstra's algorithm
    val (distances, predecessors) = graph.dijkstra(source)

    // Print results
    graph.printResults(source, distances, predecessors)
}
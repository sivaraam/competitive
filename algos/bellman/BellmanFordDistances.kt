package bellman

import java.lang.Integer.MAX_VALUE

// Ref: https://www.programiz.com/dsa/bellman-ford-algorithm

internal class CreateGraph(var Vertices: Int, var Edges: Int) {
    // CreateGraph - it consists of edges
    internal inner class CreateEdge {
        var s: Int
        var d: Int
        var w: Int = 0

        init {
            d = w
            s = d
        }
    }

    var edge: Array<CreateEdge?> = arrayOfNulls<CreateEdge>(Edges)

    // Creates a graph with V vertices and E edges
    init {
        for (i in 0 until Edges) edge[i] = CreateEdge()
    }

    fun BellmanFord(graph: CreateGraph, s: Int) {
        val V = graph.Vertices
        val E = graph.Edges
        val dist = IntArray(V)

        // Step 1: fill the distance array and predecessor array
        for (i in 0 until V) dist[i] = MAX_VALUE

        // Mark the source vertex
        dist[s] = 0

        // Step 2: relax edges |V| - 1 times
        for (i in 1 until V) {
            for (j in 0 until E) {
                // Get the edge data
                val u = graph.edge[j]!!.s
                val v = graph.edge[j]!!.d
                val w = graph.edge[j]!!.w
                if (dist[u] != MAX_VALUE && dist[u] + w < dist[v])
                    dist[v] = dist[u] + w
            }
        }

        // Step 3: detect negative cycle
        // if value changes then we have a negative cycle in the graph
        // and we cannot find the shortest distances
        for (j in 0 until E) {
            val u = graph.edge[j]!!.s
            val v = graph.edge[j]!!.d
            val w = graph.edge[j]!!.w
            if (dist[u] != MAX_VALUE && dist[u] + w < dist[v]) {
                println("CreateGraph contains negative w cycle")
                return
            }
        }

        // No negative w cycle found!
        // Print the distance and predecessor array
        printSolution(dist, V)
    }

    // Print the solution
    fun printSolution(dist: IntArray, V: Int) {
        println("Vertex Distance from Source")
        for (i in 0 until V - 1)
            println(i.toString() + "\t\t" + dist[i])
    }

    companion object {
        @kotlin.jvm.JvmStatic
        fun main(args: Array<String>) {
            val Vertices = 5 // Total vertices
            val Edges = 5 // Total Edges

            val graph = CreateGraph(Vertices, Edges)

            // edge 0 --> 1
            graph.edge[0]!!.s = 0
            graph.edge[0]!!.d = 1
            graph.edge[0]!!.w = 5

            // edge 0 --> 2
            graph.edge[1]!!.s = 0
            graph.edge[1]!!.w = 4
            graph.edge[1]!!.d = 2

            // edge 1 --> 3
            graph.edge[2]!!.s = 1
            graph.edge[2]!!.d = 3
            graph.edge[2]!!.w = 3

            // edge 2 --> 1
            graph.edge[3]!!.s = 2
            graph.edge[3]!!.d = 1
            graph.edge[3]!!.w = 6

            // edge 3 --> 2
            graph.edge[4]!!.s = 3
            graph.edge[4]!!.d = 2
            graph.edge[4]!!.w = 2

            graph.BellmanFord(graph, 0) // 0 is the source vertex
        }
    }
}
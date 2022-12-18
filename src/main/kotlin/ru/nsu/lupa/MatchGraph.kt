package ru.nsu.lupa

/**
 * Graph to represent matches between different profiles
 */
data class MatchGraph(
    /**
     * Adjacency list, represented by the map
     */
    val adjacencyList: MutableMap<Profile, MutableList<Edge<MatchCriteria, Profile>>> = mutableMapOf()
)
/**
 * Edge
 * @param W type of the label of the edge, e.g. weight
 * @param N type of the node
 */
data class Edge<W, N>(val label: W, val node: N)

/**
 * Criteria of match
 */
enum class MatchCriteria { USERNAME, NAME_SURNAME }
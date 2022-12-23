package ru.nsu.lupa

/**
 * Edge of graph for adjacency list storing
 * @param W type of the label of the edge, e.g. weight
 * @param N type of the node
 */
data class Edge<W, N>(val label: W, val node: N)
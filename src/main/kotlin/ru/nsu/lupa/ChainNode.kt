package ru.nsu.lupa

/**
 * Linked list like data structure
 */
data class ChainNode<L, N>(
    val value: N?,
    /**
     * Null if there is no next node
     */
    var next: ChainNode<L, N>?,
    /**
     * Null if there is no next node
     */
    val label: L?
)
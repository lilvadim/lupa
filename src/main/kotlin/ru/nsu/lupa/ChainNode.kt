package ru.nsu.lupa

/**
 * Linked list like data structure
 */
data class ChainNode<L, N>(
    val value: N,
    val label: L,
    val next: ChainNode<L, N>,
)
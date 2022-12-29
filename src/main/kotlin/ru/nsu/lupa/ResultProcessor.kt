package ru.nsu.lupa

/**
 * Convert result match graph to list of chain from longest to shortest
 */
interface ResultProcessor {
    /**
     * Convert match graph to list of chains from longest to shortest
     */
    fun process(matchGraph: MatchGraph): List<ChainNode<Set<MatchCriteria>, Profile>>
}
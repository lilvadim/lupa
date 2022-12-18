package ru.nsu.lupa

/**
 * Search runner
 */
class Searcher(
    /**
     * List of resources to search, order is important
     */
    val resources: List<Resource>,
    val matchGraph: MatchGraph = MatchGraph(),
) : Runnable {
    override fun run() {
        resources.forEach { it.performSearch(matchGraph) }
    }
}
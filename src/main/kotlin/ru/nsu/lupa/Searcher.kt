package ru.nsu.lupa

import javax.inject.Inject

/**
 * Search runner
 */
class Searcher @Inject constructor(
    /**
     * List of resources to search, order is important
     */
    val resourceManager: ResourceManager,
    val matchGraph: MatchGraph,
) : Runnable {
    override fun run() {
        resourceManager.resources.forEach { it.performSearch(matchGraph) }
    }
}
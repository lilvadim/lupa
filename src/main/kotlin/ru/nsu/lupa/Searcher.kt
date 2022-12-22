package ru.nsu.lupa

import javax.inject.Inject

/**
 * Search runner
 */
class Searcher @Inject constructor(
    private val resourceManager: ResourceManager,
    val matchGraph: MatchGraph,
) : Runnable {
    override fun run() {
        resourceManager.resources.forEach { it.performSearch(matchGraph) }
        matchGraph
    }
}
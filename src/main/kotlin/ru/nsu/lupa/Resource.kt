package ru.nsu.lupa

/**
 * Resource interface
 */
interface Resource {
    /**
     * URL of home page
     */
    val homeUrl: String

    /**
     * Search for user info using given match graph,
     * found information written in same graph
     */
    fun performSearch(matchGraph: MatchGraph)
}
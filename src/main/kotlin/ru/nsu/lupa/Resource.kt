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

    /**
     * Executed queries stored here.
     * It should be used to optimize request avoiding duplicates.
     */
    val queryCache: Map<String, String>
}
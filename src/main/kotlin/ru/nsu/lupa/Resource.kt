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
     * Resource name from url, before domain name, e.g. "maps.yandex.ru" -> "yandex"
     */
    val id: String

    /**
     * User access token if needed for query
     */
    var token: String?

    /**
     * Search for user info using given match graph,
     * found information written in same graph
     */
    fun performSearch(matchGraph: MatchGraph)

    abstract class BaseResource(
        final override val homeUrl: String,
        /**
         * Executed queries stored here.
         * It should be used to optimize request avoiding duplicates.
         */
        val queryCache: Map<String, String> = mapOf(),
    ) : Resource {
        override var token: String? = null
        override val id: String = homeUrl
            .substring(0, homeUrl.indexOfLast { it == '.' })
            .run { drop(indexOfLast { !it.isLetter() } + 1) }
    }
}
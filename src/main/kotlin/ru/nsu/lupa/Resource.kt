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
     * Search for user info using given match graph,
     * found information written in same graph
     */
    fun performSearch(matchGraph: MatchGraph)

    abstract class BaseResource(
        final override val homeUrl: String,
    ) : Resource {
        override val id: String = homeUrl
            .substring(0, homeUrl.indexOfLast { it == '.' })
            .run { drop(indexOfLast { !it.isLetter() } + 1) }
    }
}
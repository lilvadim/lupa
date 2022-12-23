package ru.nsu.lupa

import java.util.*
import javax.inject.Inject

/**
 * Graph to represent matches between different profiles
 */
class MatchGraph constructor(
    val comparingContext: ComparingContext,
    adjacencyList: Map<Profile, List<Edge<MatchCriteria, Profile>>>
) {
    @Inject
    constructor(comparingContext: ComparingContext) : this(comparingContext, mapOf())

    private val adjacencyList: MutableMap<Profile, MutableList<Edge<MatchCriteria, Profile>>> = buildMap {
        adjacencyList.map { (key, value) -> put(key, LinkedList(value)) }
    }.toMutableMap()

    /**
     * Adds new profile in graph
     */
    fun addProfile(profile: Profile) {
        adjacencyList.putIfAbsent(profile, mutableListOf())
        for ((vertex, edges) in adjacencyList) {
            val matches = compareProfiles(vertex, profile, comparingContext)
            edges += matches.map { Edge(it, profile) }
        }
    }

    /**
     * @return immutable view of graph
     */
    fun asAdjacencyList(): Map<Profile, List<Edge<MatchCriteria, Profile>>> {
        return buildMap {
            adjacencyList.map { (key, value) -> put(key, value.toList()) }
        }
    }
}

/**
 * Edge
 * @param W type of the label of the edge, e.g. weight
 * @param N type of the node
 */
data class Edge<W, N>(val label: W, val node: N)

/**
 * Criteria of match
 */
enum class MatchCriteria {
    USERNAME {
        override fun isMatch(x: Profile, y: Profile, ctx: ComparingContext?): Boolean =
            x.username != null && y.username != null && x.username == y.username && notSameResource(x, y)
    },
    NAME_SURNAME {
        override fun isMatch(x: Profile, y: Profile, ctx: ComparingContext?): Boolean {
            val nameProcessor = ctx?.nameProcessor ?: simpleNameProcessor()
            return x.name != null && y.name != null && x.surname != null && y.surname != null
                    && (nameProcessor.synonymsOf(x.name).toSet() == nameProcessor.synonymsOf(y.name).toSet())
                    && x.surname == y.surname && notSameResource(x, y)
        }
    };

    /**
     * @return true if profiles matches with this criteria
     */
    abstract fun isMatch(x: Profile, y: Profile, ctx: ComparingContext? = null): Boolean
}

fun notSameResource(x: Profile, y: Profile): Boolean = x.resourceUrl != y.resourceUrl

/**
 * Context that used to compare profiles, can contain different helper objects, such as NameProcessor
 */
class ComparingContext @Inject constructor(
    val nameProcessor: NameProcessor
)

/**
 * @return list of matches
 */
fun compareProfiles(x: Profile, y: Profile, ctx: ComparingContext? = null): List<MatchCriteria> {
    val result = mutableListOf<MatchCriteria>()
    for (criteria in MatchCriteria.values()) {
        if (criteria.isMatch(x, y, ctx)) {
            result += criteria
        }
    }
    return result
}
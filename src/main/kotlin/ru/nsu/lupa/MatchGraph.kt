package ru.nsu.lupa

/**
 * Graph to represent matches between different profiles
 */
class MatchGraph(
    val comparingContext: ComparingContext?,
    adjacencyList: Map<Profile, List<Edge<MatchCriteria, Profile>>>
) {
    private val adjacencyList: MutableMap<Profile, MutableList<Edge<MatchCriteria, Profile>>> = buildMap {
        adjacencyList.map { (key, value) -> put(key, value.toMutableList()) }
    }.toMutableMap()

    /**
     * Adds new profile in graph
     */
    fun addProfile(profile: Profile) {
        for ((vertex, edges) in adjacencyList) {
            val matches = compareProfiles(vertex, profile, comparingContext)
            if (matches.isNotEmpty()) {
                edges += matches.map { Edge(it, profile) }
            }
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
        override fun isMatch(x: Profile, y: Profile, ctx: ComparingContext?): Boolean = x.username == y.username
    },
    NAME_SURNAME {
        override fun isMatch(x: Profile, y: Profile, ctx: ComparingContext?): Boolean {
            val nameProcessor = ctx?.nameProcessor ?: simpleNameProcessor()
            x.name!!
            y.name!!
            return (nameProcessor.synonymsOf(x.name).toSet() == nameProcessor.synonymsOf(y.name).toSet())
                    && x.surname == y.surname
        }
    };

    /**
     * @return true if profiles matches with this criteria
     */
    abstract fun isMatch(x: Profile, y: Profile, ctx: ComparingContext? = null): Boolean
}

class ComparingContext(
    val nameProcessor: NameProcessor?
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
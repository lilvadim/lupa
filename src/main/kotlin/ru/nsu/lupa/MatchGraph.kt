package ru.nsu.lupa

import javax.inject.Inject

/**
 * Graph to represent matches between different profiles
 */
class MatchGraph @Inject constructor(
    private val comparingContext: ComparingContext
) {

    private val adjacencyList: MutableMap<Profile, MutableMap<Profile, MutableSet<MatchCriteria>>> = mutableMapOf()

    /**
     * Adds new profile in graph
     */
    fun addProfile(profile: Profile) {
        adjacencyList.putIfAbsent(profile, mutableMapOf())
        for ((vertex, edges) in adjacencyList) {
            val matches = compareProfiles(vertex, profile, comparingContext)
            if (matches.isNotEmpty()) {
                edges.getOrPut(profile) { mutableSetOf() } += matches
            }
        }
    }

    /**
     * @return immutable view of graph
     */
    fun asAdjacencyList(): Map<Profile, Map<Profile, Set<MatchCriteria>>> {
        return adjacencyList.toMap()
    }
}
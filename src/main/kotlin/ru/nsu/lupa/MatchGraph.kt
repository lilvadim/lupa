package ru.nsu.lupa

import java.util.*
import javax.inject.Inject

/**
 * Graph to represent matches between different profiles
 */
class MatchGraph constructor(
    private val comparingContext: ComparingContext,
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
        for ((vertex, edges) in adjacencyList) {
            val matches = compareProfiles(vertex, profile, comparingContext)
            edges += matches.map { Edge(it, profile) }
            adjacencyList.getOrPut(profile) { LinkedList() } += matches.map { Edge(it, vertex) }
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
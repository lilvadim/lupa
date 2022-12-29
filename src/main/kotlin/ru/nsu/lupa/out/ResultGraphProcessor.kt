//package ru.nsu.lupa.out
//
//import ru.nsu.lupa.*
//
//class ResultGraphProcessor : ResultProcessor {
//
//    val chains = mutableListOf<ChainNode<Set<MatchCriteria>, Profile>>()
//    lateinit var adjList: Map<Profile, Map<Profile, Set<MatchCriteria>>>
//
//    override fun process(matchGraph: MatchGraph, rootProfile: Profile): List<ChainNode<Set<MatchCriteria>, Profile>> {
//        adjList = matchGraph.asAdjacencyList()
//
//    }
//
//}
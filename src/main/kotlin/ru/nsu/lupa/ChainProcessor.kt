package ru.nsu.lupa

import kotlin.collections.HashSet

class ChainProcessor: ResultProcessor {
    /**
     * Convert match graph to list of chains from longest to shortest
     */
    private lateinit var g: Map<Profile, Map<Profile, Set<MatchCriteria>>>

    private val chainList = mutableListOf<ChainNode<Set<MatchCriteria>, Profile>>()

    private lateinit var rootChainNode: ChainNode<Set<MatchCriteria>, Profile>

    override fun process(matchGraph: MatchGraph): List<ChainNode<Set<MatchCriteria>, Profile>> {
        val cs = HashSet<Profile>()
        g = matchGraph.asAdjacencyList()
        g.forEach{ entry -> entry.value.forEach { cs.add(it.key) } }
        val rootProfile = g.keys.find { e -> !cs.contains(e) }
        rootChainNode = ChainNode(rootProfile!!, null, null)
        if (g[rootProfile]!!.isEmpty()) {
            return chainList
        }
        dfs(rootProfile, rootChainNode)
        return chainList
    }

    private fun dfs(node: Profile, chainNode: ChainNode<Set<MatchCriteria>, Profile>) {
        if (g[node]!!.isEmpty()) {
            var cCN = rootChainNode
            while (cCN.next != null) {
                cCN.next = cCN.next!!.copy()
                cCN = cCN.next!!
            }
            chainList.add(rootChainNode.next!!)
            return
        }
        for (key in g[node]!!.keys) {
            val nextCN = ChainNode(key, null, g[node]!![key])
            chainNode.next = nextCN
            dfs(key, nextCN)
        }
    }
}
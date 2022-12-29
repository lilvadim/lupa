package ru.nsu.lupa

class ChainProcessor: ResultProcessor {
    /**
     * Convert match graph to list of chains from longest to shortest
     */
    private lateinit var g: Map<Profile, Map<Profile, Set<MatchCriteria>>>

    private val chainList = mutableListOf<ChainNode<Set<MatchCriteria>, Profile>>()

    private lateinit var rootChainNode: ChainNode<Set<MatchCriteria>, Profile>

    override fun process(matchGraph: MatchGraph, rootProfile: Profile): List<ChainNode<Set<MatchCriteria>, Profile>> {
        rootChainNode = ChainNode(rootProfile, null, null)
        g = matchGraph.asAdjacencyList()
        if (g[rootProfile]!!.isEmpty()) {
            return emptyList()
        }
        dfs(rootProfile, rootChainNode)
        return chainList
    }

    private fun dfs(node: Profile, chainNode: ChainNode<Set<MatchCriteria>, Profile>) {
        if (g[node]!!.isEmpty()) {
            var cCN1 = rootChainNode.copy()
            var cCN = cCN1
            while (cCN.next != null) {
                cCN.next = cCN.next!!.copy()
                cCN = cCN.next!!
            }
            chainList.add(cCN1.next!!)
            return
        }
        for ((key, value) in g[node]!!) {
            val nextCN = ChainNode(node, null, value)
            chainNode.next = nextCN
            dfs(key, nextCN)
        }
    }
}
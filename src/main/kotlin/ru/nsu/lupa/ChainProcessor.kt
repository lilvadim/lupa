package ru.nsu.lupa

class ChainProcessor: ResultProcessor {
    /**
     * Convert match graph to list of chains from longest to shortest
     */
    private lateinit var g: Map<Profile, Map<Profile, Set<MatchCriteria>>>

    private lateinit var chainList: MutableList<ChainNode<Set<MatchCriteria>, Profile>>

    private lateinit var rootChainNode: ChainNode<Set<MatchCriteria>, Profile>

    override fun process(matchGraph: MatchGraph, rootProfile: Profile): List<ChainNode<Set<MatchCriteria>, Profile>> {
        chainList = mutableListOf()
        rootChainNode = ChainNode(null, null, null)
        g = matchGraph.asAdjacencyList()
        if (g[rootProfile]!!.isEmpty()) {
            return emptyList()
        }
        dfs(rootProfile, rootChainNode)
        return chainList
    }

    private fun dfs(node: Profile, previousChainNode: ChainNode<Set<MatchCriteria>, Profile>) {
        if (g[node]!!.isEmpty()) {
            var currentChainNode: ChainNode<Set<MatchCriteria>, Profile> = ChainNode(node, null, null)
            previousChainNode.next = currentChainNode
            val rootChainNodeCopy = rootChainNode.copy()
            currentChainNode = rootChainNodeCopy
            while (currentChainNode.next != null) {
                currentChainNode.next = currentChainNode.next!!.copy()
                currentChainNode = currentChainNode.next!!
            }
            chainList.add(rootChainNodeCopy.next!!)
            return
        }
        for ((key, value) in g[node]!!) {
            val currentChainNode = ChainNode(node, null, value)
            previousChainNode.next = currentChainNode
            dfs(key, currentChainNode)
        }
    }
}
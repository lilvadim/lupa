package ru.nsu.lupa

class ChainProcessor: ResultProcessor {
    /**
     * Convert match graph to list of chains from longest to shortest
     */
    private lateinit var g: Map<Profile, Map<Profile, Set<MatchCriteria>>>

    private lateinit var rootChainNode: ChainNode<Set<MatchCriteria>, Profile>

    private var chainList = mutableListOf<Pair<ChainNode<Set<MatchCriteria>, Profile>, Int>>()

    override fun process(matchGraph: MatchGraph, rootProfile: Profile): List<ChainNode<Set<MatchCriteria>, Profile>> {
        chainList = mutableListOf()
        rootChainNode = ChainNode(null, null, null)
        g = matchGraph.asAdjacencyList()
        if (g[rootProfile]!!.isEmpty()) {
            return emptyList()
        }
        dfs(rootProfile, rootChainNode)
        return chainList.sortedBy { (_, len) -> len }.map { (chain, _) -> chain }
    }

    private fun dfs(node: Profile, previousChainNode: ChainNode<Set<MatchCriteria>, Profile>) {
        if (g[node]!!.isEmpty()) {
            var currentChainNode: ChainNode<Set<MatchCriteria>, Profile> = ChainNode(node, null, null)
            previousChainNode.next = currentChainNode
            val rootChainNodeCopy = rootChainNode.copy()
            currentChainNode = rootChainNodeCopy
            var currentLength = 0
            while (currentChainNode.next != null) {
                currentChainNode.next = currentChainNode.next!!.copy()
                currentChainNode = currentChainNode.next!!
                currentLength++
            }
            chainList.add(Pair(rootChainNodeCopy.next!!, currentLength))
            return
        }
        for ((key, value) in g[node]!!) {
            val currentChainNode = ChainNode(node, null, value)
            previousChainNode.next = currentChainNode
            dfs(key, currentChainNode)
        }
    }
}
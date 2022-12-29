package ru.nsu.lupa

import javax.inject.Inject

class App @Inject constructor(
    private val configuration: Configuration,
    private val searcher: Searcher,
    private val matchGraph: MatchGraph,
    private val resultProcessor: ResultProcessor
) : Runnable {
    override fun run() {
        configuration.profiles.forEach { matchGraph.addProfile(it) }
        searcher.run()
        output(matchGraph)
    }

    private fun output(matchGraph: MatchGraph) {
//        TODO вывод результата
        println(resultProcessor.process(matchGraph))
    }
}
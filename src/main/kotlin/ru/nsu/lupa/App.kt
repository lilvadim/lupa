package ru.nsu.lupa

import ru.nsu.lupa.out.convertToHtml
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
        val outFile = configuration.outputFile
        if (outFile != null) {
            outFile.createNewFile()
            outFile.writeText(convertToHtml(resultProcessor.process(matchGraph, configuration.profiles[0])))
        }
    }
}
package ru.nsu.lupa

import javax.inject.Inject

class App @Inject constructor(
    val configuration: Configuration,
    val searcher: Searcher,
    val matchGraph: MatchGraph
) : Runnable {
    override fun run() {
        println(configuration.profiles)
    }
}
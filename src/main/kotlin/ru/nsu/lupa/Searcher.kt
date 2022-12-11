package ru.nsu.lupa

/**
 * Search runner
 */
class Searcher(
    /**
     * List of resources to search, order is important
     */
    val resources: List<Resource>,
    /**
     * Known information
     */
    val initialProfile: Profile
) : Runnable {
    override fun run() {

    }
}
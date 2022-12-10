package ru.nsu.lupa

class Searcher(
    val resources: List<Resource>,
    val initialProfile: Profile
) : Runnable {

    var resultProfile = initialProfile

    override fun run() {
        for (resource in resources) {
            resultProfile = resource.search(resultProfile)
        }
    }
}
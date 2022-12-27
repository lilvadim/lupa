package ru.nsu.lupa.res

import org.kohsuke.github.GHFileNotFoundException
import org.kohsuke.github.GHUser
import org.kohsuke.github.GitHub
import ru.nsu.lupa.*

class GitHubSearch : Resource.BaseResource(homeUrl = "https://github.com/") {
    override fun performSearch(matchGraph: MatchGraph) {
        val g = GitHub.connectAnonymously()
        for ((profile, _) in matchGraph.asAdjacencyList()) {
            val user: GHUser
            try {
                user = g.getUser(profile.username.toString())
            } catch (e: GHFileNotFoundException) {
                continue
            }
            val gitProfile = Profile(
                username = Username(user.login),
                resourceUrl = homeUrl,
                relatedLinks = listOf("https://github.com/${user.login}"),
                email = user.email?.let { Email(it) },
                name = user.name?.let { Name(it) },
            )
            matchGraph.addProfile(gitProfile)
        }
    }
}
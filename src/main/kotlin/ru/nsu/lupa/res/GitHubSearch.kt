package ru.nsu.lupa.res

import org.kohsuke.github.GHFileNotFoundException
import org.kohsuke.github.GHUser
import org.kohsuke.github.GitHub
import ru.nsu.lupa.*
import java.io.IOException

class GitHubSearch(
    parameters: Map<String, Map<String, String>>
) : Resource.BaseResource(homeUrl = "https://api.github.com/") {
    override fun performSearch(matchGraph: MatchGraph) {
        val g = GitHub.connectAnonymously()
        for ((profile, _) in matchGraph.asAdjacencyList()) {
            val user:GHUser
            try {
                 user = g.getUser(profile.username.toString())
            } catch (e:GHFileNotFoundException) {
                return
            }
            val gitProfile = Profile(
                username = Username(user.login),
                resourceUrl = homeUrl,
                email = Email( user.email),
                name = Name(user.name),
            )
            matchGraph.addProfile(gitProfile)
        }
    }
}
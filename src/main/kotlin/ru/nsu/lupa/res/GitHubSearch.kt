package ru.nsu.lupa.res

import org.kohsuke.github.GHFileNotFoundException
import org.kohsuke.github.GHUser
import org.kohsuke.github.GitHub
import ru.nsu.lupa.*
import java.util.logging.Logger
import javax.inject.Inject

class GitHubSearch @Inject constructor(
    private val logger: Logger
) : Resource.BaseResource(homeUrl = "https://github.com/") {
    private val g = GitHub.connectAnonymously()

    override fun performSearch(matchGraph: MatchGraph) {
        for ((profile, _) in matchGraph.asAdjacencyList()) {
            if (profile.username == null) {
                continue
            }
            val user: GHUser
            try {
                user = g.getUser(profile.username.value)
                logger.info(user.toString())
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
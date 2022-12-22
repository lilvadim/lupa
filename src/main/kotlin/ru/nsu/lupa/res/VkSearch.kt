package ru.nsu.lupa.res

import com.vk.api.sdk.client.VkApiClient
import com.vk.api.sdk.client.actors.UserActor
import com.vk.api.sdk.httpclient.HttpTransportClient
import com.vk.api.sdk.queries.users.UsersSearchQuery
import ru.nsu.lupa.*

private const val APP_ID = 51506122
private const val CLIENT_SECRET = "zj7hCzmwsiRVUn6VxFwj"
private const val REDIRECT_URI = "https://api.vk.com/blank.html"

class VkSearch(
    parameters: Map<String, Map<String, String>>
) : Resource.BaseResource(homeUrl = "https://vk.com/") {
    private val vkClient = VkApiClient(HttpTransportClient())

    private val userId by parameters[id]!!
    private val accessToken by parameters[id]!!

    private val actor by lazy { UserActor(userId.toInt(), accessToken) }

    override fun performSearch(matchGraph: MatchGraph) {
        for ((profile, _) in matchGraph.asAdjacencyList()) {
            val response = UsersSearchQuery(vkClient, actor)
                .q("${profile.name?.value ?: ""} ${profile.surname?.value ?: ""}")
                .execute()
            val profiles = response.items.map { user ->
                Profile(
                    resourceUrl = homeUrl,
                    name = Name(user.firstName),
                    surname = Surname(user.lastName),
                    relatedLinks = listOf("https://vk.com/${user.id}"),
                    username = user.nickname?.let { Username(it) },
                    email = user.email?.let { Email(it) },
                    phone = user.mobilePhone?.let { PhoneNumber(it) },
                )
            }
            profiles.forEach { matchGraph.addProfile(it) }
        }
    }
}
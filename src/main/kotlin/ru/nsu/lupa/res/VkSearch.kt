package ru.nsu.lupa.res

import com.vk.api.sdk.client.VkApiClient
import com.vk.api.sdk.client.actors.UserActor
import com.vk.api.sdk.httpclient.HttpTransportClient
import com.vk.api.sdk.objects.users.Fields
import com.vk.api.sdk.queries.users.UsersGetQuery
import com.vk.api.sdk.queries.users.UsersSearchQuery
import ru.nsu.lupa.*

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
            // TODO screen name не находит, попробовать User.get()
            val profiles = response.items.map { user ->
                val fullUser = UsersGetQuery(vkClient, actor)
                    .userIds(user.id.toString())
                    .fields(Fields.SCREEN_NAME)
                    .execute()
                    .first()
                Profile(
                    resourceUrl = homeUrl,
                    name = Name(fullUser.firstName),
                    surname = Surname(fullUser.lastName),
                    relatedLinks = listOf("https://vk.com/id${user.id}"),
                    username = fullUser.screenName?.let { Username(it) },
                    email = fullUser.email?.let { Email(it) },
                    phone = fullUser.mobilePhone?.let { PhoneNumber(it) },
                )
            }
            profiles.forEach { matchGraph.addProfile(it) }
        }
    }
}
package ru.nsu.lupa.res

import com.vk.api.sdk.client.VkApiClient
import com.vk.api.sdk.client.actors.UserActor
import com.vk.api.sdk.httpclient.HttpTransportClient
import com.vk.api.sdk.objects.UserAuthResponse
import com.vk.api.sdk.queries.users.UsersSearchQuery
import ru.nsu.lupa.MatchGraph
import ru.nsu.lupa.NameProcessor
import ru.nsu.lupa.Resource


class VkSearch : Resource.BaseResource(homeUrl = "https://vk.com/") {
    override fun performSearch(matchGraph: MatchGraph, nameProcessor: NameProcessor) {
        val usersSearchQuery = UsersSearchQuery(vkClient, actor)
    }

    private companion object {
        const val APP_ID = 51506122
        const val CLIENT_SECRET = ""
        const val REDIRECT_URI = ""
        const val code = ""

        val vkClient = VkApiClient(HttpTransportClient())
        val actor: UserActor
        init {
            val authResponse: UserAuthResponse = vkClient.oAuth()
                .userAuthorizationCodeFlow(APP_ID, CLIENT_SECRET, REDIRECT_URI, code)
                .execute()

            actor = UserActor(authResponse.userId, authResponse.accessToken)
        }
    }
}
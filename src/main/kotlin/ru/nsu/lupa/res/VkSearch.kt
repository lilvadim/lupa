package ru.nsu.lupa.res

import ru.nsu.lupa.Profile
import ru.nsu.lupa.Resource

class VkSearch : Resource {
    override val homeUrl: String = "https://vk.com/"

    override fun search(profile: Profile): Profile {
        TODO("Not yet implemented")
    }
}
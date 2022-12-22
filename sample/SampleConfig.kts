package ru.nsu.lupa

import ru.nsu.lupa.dsl.*

config {
    profiles {
        profile(
            name = "Vadim",
            surname = "Mostovoy"
        )
    }

    parameters {
        resource("vk") {
            "accessToken" set "..."
            "userId" set "..."
        }
    }
}
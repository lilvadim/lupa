profiles {
    profile(
        name = "Иван",
        surname = "Иванов"
    )
}

parameters {
    resource("vk") {
        "accessToken" set "<YOUR_VALUE>"
        "userId" set "<YOUR_VALUE>"
    }
}

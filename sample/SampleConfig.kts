import java.io.File

config {
    profiles {
        profile(
            name = "Марк",
            surname = "Бухнер"
        )
    }

    parameters {
        resource("vk") {
            "accessToken" set "<YOUR_VALUE>"
            "userId" set "<YOUR_VALUE>"
        }
    }

    outputFile = File("path/to/output/file.html")
}
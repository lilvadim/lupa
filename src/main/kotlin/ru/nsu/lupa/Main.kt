package ru.nsu.lupa

import com.google.inject.Guice
import com.google.inject.Provides
import com.google.inject.Singleton
import kotlinx.cli.ArgParser
import kotlinx.cli.ArgType
import ru.nsu.lupa.inject.AppModule
import java.io.File

internal fun main(args: Array<String>) {
    val parser = ArgParser("lupa")

    val configPath by parser.argument(
        ArgType.String,
        description = "Path to *.kts configuration file",
        fullName = "configuration-file-path"
    )

    parser.parse(args)

    val configFile = File(configPath)
    val config = ConfigurationProvider().fromKtsFile(configFile)

    val inject = Guice.createInjector(object : AppModule() {
        @Singleton
        @Provides
        fun configuration() = config
    })

    val app = inject.getInstance(App::class.java)
    app.run()
}
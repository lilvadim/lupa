package ru.nsu.lupa

import kotlinx.cli.ArgParser
import kotlinx.cli.ArgType
import kotlinx.cli.required
import ru.nsu.lupa.res.VkSearch

val resources = listOf(VkSearch())

internal fun main(args: Array<String>) {
    val parser = ArgParser("lupa")

    val name by parser.option(
        type = ArgType.String,
        fullName = "name",
        shortName = "n",
        description = "Any form of name"
    ).required()

    val surname by parser.option(
        type = ArgType.String,
        fullName = "surname",
        shortName = "s",
        description = "Surname"
    ).required()

    parser.parse(args)

    val initialProfile = Profile(
        name = Name(name),
        surname = Surname(surname)
    )

    val matchGraph = MatchGraph(ComparingContext()).apply {
        addProfile(initialProfile)
    }

    val searcher = Searcher(resources, matchGraph)

    println(searcher.matchGraph)
}
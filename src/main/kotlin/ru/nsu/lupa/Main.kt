package ru.nsu.lupa

import kotlinx.cli.*
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

    val searcher = Searcher(resources, initialProfile)

    println(searcher.resultProfile)
}
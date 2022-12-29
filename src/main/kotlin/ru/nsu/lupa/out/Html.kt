package ru.nsu.lupa.out

import kotlinx.html.*
import kotlinx.html.stream.createHTML
import ru.nsu.lupa.ChainNode
import ru.nsu.lupa.MatchCriteria
import ru.nsu.lupa.Profile

fun convertToHtml(chains: List<ChainNode<Set<MatchCriteria>, Profile>>): String = createHTML().html {
    body {
        h1 { +"Found Profiles" }

        for (chain in chains) {
            table {
                var node: ChainNode<Set<MatchCriteria>, Profile>? = chain
                while (node != null) {
                    val profile = node.value

                    if (node.next != null) {
                        +"Matches by ${node.label?.joinToString(separator = ", ")} with "
                    }

                    node = node.next
                }
                br
            }
        }
    }
}

fun FlowContent.profileAsHtml(profile: Profile) {
    return div {
        h3 { +"Profile" }
        profile.name?.let { +"Name: ${it.value}"; br }
        profile.surname?.let { +"Surname: ${it.value}"; br }
        profile.username?.let { +"Username: ${it.value}"; br }
        profile.email?.let { +"Email: ${it.value}"; br }
        profile.phone?.let { +"Phone: ${it.value}"; br }
        profile.relatedLinks.forEach { a(href = it) { +it }; br }
    }
}
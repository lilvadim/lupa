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
                style {
                    unsafe {
                        raw(
                            "table, th, td {" +
                                    "border:1px solid DarkGray;" +
                                    "width: 100%;" +
                                    "}"
                        )
                    }
                }
                tr {
                    th { +"Name" }
                    th { +"Surname" }
                    th { +"Username" }
                    th { +"Email" }
                    th { +"Phone" }
                    th { +"Links" }
                }
                var node: ChainNode<Set<MatchCriteria>, Profile>? = chain
                while (node != null) {
                    val profile = node.value
                    tr {
                        td { +profile.name?.value.toString() }
                        td { +profile.surname?.value.toString() }
                        td { +profile.username?.value.toString() }
                        td { +profile.email?.value.toString() }
                        td { +profile.phone?.value.toString() }
                        td { profile.relatedLinks.forEach { a(href = it) { +it } } }
                    }

                    if (node.next != null) {
                        tr {
                            td {
                                +"Matches by ${node!!.label?.joinToString(separator = ", ")} with profile below"
                            }
                        }
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
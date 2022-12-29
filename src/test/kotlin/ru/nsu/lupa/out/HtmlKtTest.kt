//package ru.nsu.lupa.out
//
//import org.junit.jupiter.api.Test
//
//import ru.nsu.lupa.ChainNode
//import ru.nsu.lupa.MatchCriteria
//import ru.nsu.lupa.Name
//import ru.nsu.lupa.Profile
//
//class HtmlKtTest {
//    val profile = Profile(
//        name = Name("Вадим"),
//        relatedLinks = listOf("https://vk.com")
//    )
//
//    val chainNode2 = ChainNode<Set<MatchCriteria>, Profile>(
//        value = profile,
//        label = null,
//        next = null
//    )
//
//    val chainNode1 = ChainNode(
//        value = profile,
//        label = setOf(MatchCriteria.NAME_SURNAME, MatchCriteria.USERNAME),
//        next = chainNode2
//    )
//
//    @Test
//    fun convertToHtml() {
//        println(
//            convertToHtml(
//                listOf(chainNode1, chainNode2)
//            )
//        )
//    }
//
//    @Test
//    fun profileAsText() {
//        println(
////            profileAsHtml(profile)
//        )
//    }
//}
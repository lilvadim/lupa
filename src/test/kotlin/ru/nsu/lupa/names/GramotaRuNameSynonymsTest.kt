package ru.nsu.lupa.names

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import ru.nsu.lupa.Name

internal class GramotaRuNameSynonymsTest {

    @Test
    fun synonymsOf() {
        val ref = setOf("Ваня", "ЕВСЕВОН", "ИВАН", "ИВАННА", "КЕТЕВАНЬ", "СЕЛИВАН", "СИЛЬВАН", "УРВАН").map {
            Name(it.lowercase())
        }.toSet()
        val nameProcessor = GramotaRuNameSynonyms()
        val result = nameProcessor.synonymsOf(Name("Ваня"))
        assertEquals(ref, result)
    }
}
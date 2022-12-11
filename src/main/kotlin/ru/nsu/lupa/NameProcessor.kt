package ru.nsu.lupa

/**
 * Name processor
 */
interface NameProcessor {
    /**
     * Returns synonymic forms of `name`
     */
    fun synonymsOf(name: Name): List<Name>
}


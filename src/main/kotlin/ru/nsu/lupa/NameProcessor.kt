package ru.nsu.lupa

/**
 * Name processor
 */
interface NameProcessor {
    /**
     * Returns synonymic forms of `name`
     */
    fun synonymsOf(name: Name): Set<String>
}

/**
 * Returns name processor which doesn't find any synonymic forms
 */
fun simpleNameProcessor() = object : NameProcessor {
    override fun synonymsOf(name: Name): MutableSet<String> = mutableSetOf()
}


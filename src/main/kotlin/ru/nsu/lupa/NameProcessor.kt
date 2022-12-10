package ru.nsu.lupa

interface NameProcessor {
    fun synonymsOf(name: Name): List<Name>
}


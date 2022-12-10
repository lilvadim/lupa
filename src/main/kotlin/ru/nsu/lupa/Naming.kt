package ru.nsu.lupa

data class Name(val value: String)

data class Surname(val value: String)

data class Username(val value: String, val resourceUrl: String)

data class Email(val value: String)

data class PhoneNumber(val value: String)

interface NameProcessor {
    fun synonymsOf(name: Name): List<Name>
}


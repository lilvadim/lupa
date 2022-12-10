package ru.nsu.lupa

import java.time.LocalDate

data class Profile(
    val name: Name? = null,
    val surname: Surname? = null,
    val username: Username? = null,
    val email: Email? = null,
    val phoneNumber: PhoneNumber? = null,
    val birthDate: LocalDate? = null,
    val images: List<Image> = listOf()
)

data class Name(val value: String)
data class Surname(val value: String)
data class Username(val value: String, val resourceUrl: String)
data class Email(val value: String)
data class PhoneNumber(val value: String)
data class Image(val url: String, val resourceUrl: String)
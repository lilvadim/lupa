package ru.nsu.lupa

import java.time.LocalDate

data class Profile(
    val name: Name? = null,
    val surname: Surname? = null,
    val username: Username? = null,
    val email: Email? = null,
    val phoneNumber: PhoneNumber? = null,
    val birthDate: LocalDate? = null,
    val imageLinks: List<String> = listOf()
)
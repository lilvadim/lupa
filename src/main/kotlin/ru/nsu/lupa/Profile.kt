package ru.nsu.lupa

/**
 * Profile represents user info from the resource
 */
data class Profile(
    val resourceUrl: String? = null,
    val name: Name? = null,
    val surname: Surname? = null,
    val username: Username? = null,
    val email: Email? = null,
    val phone: PhoneNumber? = null,
    val bDate: BDate = BDate()
)

/**
 * Name
 */
data class Name(val value: String)

/**
 * Surname
 */
data class Surname(val value: String)

/**
 * Username/nickname
 */
data class Username(val value: String)

/**
 * Email
 */
data class Email(val value: String)

/**
 * Phone number
 */
data class PhoneNumber(val value: String)

/**
 * Date of birth, but some info may be not known,
 * e.g. only year is known, then other properties are null
 */
data class BDate(
    val birthDay: Int? = null,
    val birthMonth: Int? = null,
    val birthYear: Int? = null,
)
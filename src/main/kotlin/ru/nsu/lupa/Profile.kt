package ru.nsu.lupa

/**
 * Profile represents user info from the resource
 */
data class Profile(
    /**
     * URL of resource home page, used as name
     */
    val resourceUrl: String? = null,
    /**
     * Links related to this profile, which were sources of information
     */
    val relatedLinks: List<String> = listOf(),
    val name: Name? = null,
    val surname: Surname? = null,
    val username: Username? = null,
    val email: Email? = null,
    val phone: PhoneNumber? = null,
    val ageData: AgeData = AgeData()
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
 * Date of birth or approximate age (e.g. 29 years old), but some info may be not known,
 * e.g. only year is known, then other properties are null
 */
data class AgeData(
    val approxAge: Int? = null,
    val birthDay: Int? = null,
    val birthMonth: Int? = null,
    val birthYear: Int? = null,
)
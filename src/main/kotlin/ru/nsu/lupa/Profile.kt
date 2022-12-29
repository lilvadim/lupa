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
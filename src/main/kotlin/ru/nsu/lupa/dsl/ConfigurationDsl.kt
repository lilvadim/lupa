package ru.nsu.lupa.dsl

import ru.nsu.lupa.*

fun config(block: Configuration.() -> Unit) = Configuration().apply { block() }

fun Configuration.profiles(block: ProfilesContext.() -> Unit) {
    val ctx = ProfilesContext().apply { block() }
    this.profiles.addAll(ctx.list)
}

class ProfilesContext {
    val list = mutableListOf<Profile>()
    fun profile(
        resourceUrl: String? = null,
        name: String? = null,
        surname: String? = null,
        username: String? = null,
        email: String? = null,
        phone: String? = null,
    ) {
        list += Profile(
            resourceUrl = resourceUrl,
            name = name?.let { Name(it) },
            surname = surname?.let { Surname(it) },
            username = username?.let { Username(it) },
            email = email?.let { Email(it) },
            phone = phone?.let { PhoneNumber(it) }
        )
    }
}
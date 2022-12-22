package ru.nsu.lupa.dsl

import ru.nsu.lupa.*

fun config(block: ConfigurationContext.() -> Unit): Configuration {
    val ctx = ConfigurationContext().apply(block)
    return Configuration(ctx.parameters, ctx.profiles)
}

class ConfigurationContext {
    var parameters: MutableMap<String, MutableMap<String, String>> = mutableMapOf()
    val profiles: MutableList<Profile> = mutableListOf()
    fun parameters(block: ParametersContext.() -> Unit) {
        val ctx = ParametersContext().apply(block)
        this.parameters.putAll(ctx.map)
    }

    fun profiles(block: ProfilesContext.() -> Unit) {
        val ctx = ProfilesContext().apply(block)
        this.profiles.addAll(ctx.list)
    }
}

class ParametersContext {
    val map = mutableMapOf<String, MutableMap<String, String>>()
    fun resource(id: String, block: ResourceParametersContext.() -> Unit) {
        val ctx = ResourceParametersContext().apply(block)
        map += id to ctx.map
    }
}

class ResourceParametersContext {
    val map = mutableMapOf<String, String>()
    infix fun String.set(value: String) {
        map += this@set to value
    }
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
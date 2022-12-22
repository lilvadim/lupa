package ru.nsu.lupa

fun config(block: Configuration.() -> Unit) = Configuration().apply { block() }

fun Configuration.profiles(vararg profiles: Profile) {
    this.profiles.addAll(profiles)
}
package ru.nsu.lupa

interface Resource {
    val homeUrl: String
    fun search(profile: Profile): Profile
}
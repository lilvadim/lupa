package ru.nsu.lupa

import javax.inject.Inject

/**
 * Context that used to compare profiles, can contain different helper objects, such as NameProcessor
 */
class ComparingContext @Inject constructor(
    val nameProcessor: NameProcessor
)
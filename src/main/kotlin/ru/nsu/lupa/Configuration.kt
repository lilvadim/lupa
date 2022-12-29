package ru.nsu.lupa

import java.io.File

open class Configuration(
    /**
     * `Resource.id -> param_name -> value`
     */
    val parameters: Map<String, Map<String, String>>,
    /**
     * Initial dataset
     */
    val profiles: List<Profile>,
    val outputFile: File?
) {
    fun param(resourceId: String, paramName: String): String? {
        return parameters[resourceId]?.get(paramName)
    }
}

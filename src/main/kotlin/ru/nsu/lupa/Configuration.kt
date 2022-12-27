package ru.nsu.lupa

class Configuration(
    /**
     * `Resource.id -> param_name -> value`
     */
    var parameters: Map<String, Map<String, String>>,
    /**
     * Initial dataset
     */
    val profiles: List<Profile>
) {
    fun param(resourceId: String, paramName: String): String? {
        return parameters[resourceId]?.get(paramName)
    }
}

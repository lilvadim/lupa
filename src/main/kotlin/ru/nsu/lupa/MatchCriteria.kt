package ru.nsu.lupa

/**
 * Criteria of match
 */
enum class MatchCriteria {
    USERNAME {
        override fun isMatch(x: Profile, y: Profile, ctx: ComparingContext?): Boolean =
            x.username != null && y.username != null
                    && x.username.value.equals(y.username.value, ignoreCase = true)
    },
    NAME_SURNAME {
        override fun isMatch(x: Profile, y: Profile, ctx: ComparingContext?): Boolean {
            val nameProcessor = ctx?.nameProcessor ?: simpleNameProcessor()
            return x.name != null && y.name != null && x.surname != null && y.surname != null
                    && (nameProcessor.synonymsOf(x.name) == nameProcessor.synonymsOf(y.name))
                    && x.surname == y.surname
        }
    };

    /**
     * @return true if profiles matches with this criteria
     */
    abstract fun isMatch(x: Profile, y: Profile, ctx: ComparingContext? = null): Boolean
}

/**
 * Tells if resources of profiles are the same
 */
private fun sameResource(x: Profile, y: Profile): Boolean = x.resourceUrl == y.resourceUrl

/**
 * @return list of matches
 */
fun compareProfiles(x: Profile, y: Profile, ctx: ComparingContext? = null): Set<MatchCriteria> {
    if (sameResource(x, y)) {
        return emptySet()
    }
    val result = mutableSetOf<MatchCriteria>()
    for (criteria in MatchCriteria.values()) {
        if (criteria.isMatch(x, y, ctx)) {
            result += criteria
        }
    }
    return result
}
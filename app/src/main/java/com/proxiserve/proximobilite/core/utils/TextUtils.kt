package com.proxiserve.proximobilite.core.utils


/**
 * Created by dloriot on 07/07/2024.
 */
object TextUtils {

    fun reduceTextLength(text: String, limit: Int): String {
        val result = text.replace(" - ", " ").trim()

        if (result.length > limit)
            return result.substring(0, limit - 3) + "..."

        return result
    }
}
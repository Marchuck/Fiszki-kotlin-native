package org.kotlin.mpp.mobile.com.marchuck.fiszki.model

enum class Language(val code: String) {
    POLISH("pl"),
    ENGLISH("en"),
    GERMAN("de");

    companion object {
        fun recognize(languageCode: String): Language {
            for (language in values()) {
                if (languageCode.toLowerCase() == language.code) return language
            }
            throw IllegalStateException("cannot recognize $languageCode")
        }
    }
}
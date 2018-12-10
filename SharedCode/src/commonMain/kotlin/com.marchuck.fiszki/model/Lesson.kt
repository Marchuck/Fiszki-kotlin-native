package org.kotlin.mpp.mobile.com.marchuck.fiszki.model

data class Lesson(val id: Long,
                  val translationFrom: Language,
                  val translationTo: Language,
                  val name: String) {

    constructor(id: Long) : this(id, Language.ENGLISH, Language.GERMAN, "")
}
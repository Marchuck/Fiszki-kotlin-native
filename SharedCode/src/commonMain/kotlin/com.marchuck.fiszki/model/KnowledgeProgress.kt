package org.kotlin.mpp.mobile.com.marchuck.fiszki.model

data class KnowledgeProgress(val known: List<String>,
                             val skipped: List<String>,
                             val wrong: List<String>)
package org.kotlin.mpp.mobile.com.marchuck.fiszki.model


enum class FlashCardState(val code: Int) {

    NOT_SEEN(1),
    OK(2),
    SKIPPED(3),
    WRONG(4);

    companion object {
        fun fromCode(code: Int): FlashCardState {
            for (state in values()) {
                if (state.code == code) return state
            }
            return NOT_SEEN
        }
    }
}

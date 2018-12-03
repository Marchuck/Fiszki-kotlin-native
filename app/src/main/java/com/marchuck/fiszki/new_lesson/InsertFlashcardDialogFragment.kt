package com.marchuck.fiszki.new_lesson

import android.os.Bundle
import android.support.design.widget.TextInputEditText
import android.support.design.widget.TextInputLayout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.marchuck.fiszki.R
import com.marchuck.fiszki.base.BaseDialogFragment


class InsertFlashcardDialogFragment : BaseDialogFragment<CreateLessonActivity>() {

    companion object {
        fun newInstance(heads: String, tails: String): InsertFlashcardDialogFragment {
            val f = InsertFlashcardDialogFragment()
            val b = Bundle()
            b.putString("HEADS", heads)
            b.putString("TAILS", tails)
            f.arguments = b
            return f
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_create_flashcard, container)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btn: Button = view.findViewById(R.id.save)
        val headsL: TextInputLayout = view.findViewById(R.id.heads_layout)
        val tailsL: TextInputLayout = view.findViewById(R.id.tails_layout)

        headsL.hint = arguments?.getString("HEADS", "Heads") ?: "Heads"
        tailsL.hint = arguments?.getString("TAILS", "Tails") ?: "Tails"

        val heads: TextInputEditText = view.findViewById(R.id.heads_edit)
        val tails: TextInputEditText = view.findViewById(R.id.tails_edit)

        btn.setOnClickListener {
            headsL.error = null
            tailsL.error = null
            if (heads.trimmedText().isEmpty()) {
                headsL.error = "Please enter a value"
            } else if (tails.trimmedText().isEmpty()) {
                tails.error = "Please enter a value"
            } else {
                getDirectParent().hideKeyboard()
                getDirectParent().presenter.putNewFlashcard(heads.trimmedText(), tails.trimmedText())
                dismiss()
            }
        }
    }
}

fun TextInputEditText.trimmedText() = text.toString().trim()

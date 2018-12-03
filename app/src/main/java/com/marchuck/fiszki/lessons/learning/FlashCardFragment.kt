package com.marchuck.fiszki.lessons.learning

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.marchuck.fiszki.R
import com.marchuck.fiszki.base.BaseFragment

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [FlashCardFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [FlashCardFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class FlashCardFragment : BaseFragment<LearningActivity>() {

    private var flashcard: FlashcardParcel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            flashcard = it.getParcelable(FLASHCARD)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_flash_card, container, false)

        return view
    }

    companion object {
        val FLASHCARD = "FLASHCARD"
        @JvmStatic
        fun newInstance(flashCard: FlashcardParcel) =
                FlashCardFragment().apply {
                    arguments = Bundle().apply {
                        putParcelable(FLASHCARD, flashCard)
                    }
                }
    }
}


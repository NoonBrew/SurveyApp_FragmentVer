package com.example.surveyapp_fragmentver

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider

const val SHOW_SURVEY_RESULTS = "com.example.surveyapp_fragmentver.SurveyFragment.ShowResults"
const val START_NEW_SURVEY = "com.example.surveyapp_fragmentver.SurveyFragment.StartNew"
class SurveyFragment: Fragment() {


    private lateinit var yesButton: Button
    private lateinit var noButton: Button
    private lateinit var newSurvey: Button
    private lateinit var showResults: Button
    private lateinit var questionDisplay: TextView

    private val surveyViewModel: Survey by lazy {
        ViewModelProvider(requireActivity()).get(Survey::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_survey, container, false)

        yesButton = view.findViewById(R.id.yes_button)
        noButton = view.findViewById(R.id.no_button)
        newSurvey = view.findViewById(R.id.new_survey_button)
        showResults = view.findViewById(R.id.show_results_button)
        questionDisplay = view.findViewById(R.id.question_display_textView)

        questionDisplay.text = surveyViewModel.retrieveQuestion()

        yesButton.setOnClickListener {
            surveyViewModel.addYes()
        }

        noButton.setOnClickListener {
            surveyViewModel.addNo()
        }

        newSurvey.setOnClickListener {
            surveyViewModel.clearForNewSurvey()
            parentFragmentManager.setFragmentResult(START_NEW_SURVEY, Bundle.EMPTY)
        }

        showResults.setOnClickListener {
            parentFragmentManager.setFragmentResult(SHOW_SURVEY_RESULTS, Bundle.EMPTY)
        }

        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         */

        @JvmStatic
        fun newInstance() = SurveyFragment()
    }
}
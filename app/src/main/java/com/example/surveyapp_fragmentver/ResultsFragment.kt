package com.example.surveyapp_fragmentver

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider

const val CONTINUE_SURVEY = "com.example.surveyapp_fragmentver.ResultFragment.Continue"
class ResultsFragment: Fragment() {


    private lateinit var resetSurvey: Button
    private lateinit var continueSurvey: Button
    private lateinit var yesAnswers: TextView
    private lateinit var noAnswers: TextView
    private lateinit var newSurvey: Button

    private val surveyViewModel: Survey by lazy {
        ViewModelProvider(requireActivity()).get(Survey::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_results, container, false)
        resetSurvey = view.findViewById(R.id.reset_survey_button)
        continueSurvey = view.findViewById(R.id.continue_survey_button)
        yesAnswers = view.findViewById(R.id.yes_answer_textView)
        noAnswers = view.findViewById(R.id.no_answer_textView)
        newSurvey = view.findViewById(R.id.new_survey_button)
        // Use a display answers function to read and display are yes no values.
        // Use a function so we can call it on inflation and when our reset button is clicked.
        displayAnswers()

        resetSurvey.setOnClickListener {
            // Uses our SurveyViewModel reset method to return the yes no answers back to 0
            // and display them.
            surveyViewModel.resetSurvey()
            displayAnswers()

        }

        continueSurvey.setOnClickListener {
            // Asks our main activity to read our key and launch our SurveyFragment so the survey can
            // continue.
            parentFragmentManager.setFragmentResult(CONTINUE_SURVEY, Bundle.EMPTY)
        }

        newSurvey.setOnClickListener {
            // Added a new feature since my first survey app. Thought a user might want to start
            // a new survey from the resultsFragment so this button is a mirror of our
            // New survey button in the SurveyFragment.
            surveyViewModel.clearForNewSurvey()
            parentFragmentManager.setFragmentResult(START_NEW_SURVEY, Bundle.EMPTY)
        }

        return view
    }
    // Function reads yes and no values from the view model and displays them.
    private fun displayAnswers() {
        val countYes = surveyViewModel.currentYesAnswers
        val countNo = surveyViewModel.currentNoAnswers

        yesAnswers.text = countYes.toString()
        noAnswers.text = countNo.toString()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         */

        @JvmStatic
        fun newInstance() = ResultsFragment()
    }
}
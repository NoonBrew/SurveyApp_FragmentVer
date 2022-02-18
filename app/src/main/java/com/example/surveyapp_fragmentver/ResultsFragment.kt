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

        displayAnswers()

        resetSurvey.setOnClickListener {
            surveyViewModel.resetSurvey()
            displayAnswers()

        }

        continueSurvey.setOnClickListener {
            parentFragmentManager.setFragmentResult(CONTINUE_SURVEY, Bundle.EMPTY)
        }

        newSurvey.setOnClickListener {
            surveyViewModel.clearForNewSurvey()
            parentFragmentManager.setFragmentResult(START_NEW_SURVEY, Bundle.EMPTY)
        }

        return view
    }

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
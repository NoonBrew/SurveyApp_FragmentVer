package com.example.surveyapp_fragmentver

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
// these strings could be anything, we use the package name plus extras to make them unique even
// to other apps.
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
        // Inflates our layout view.
        val view = inflater.inflate(R.layout.fragment_survey, container, false)

        yesButton = view.findViewById(R.id.yes_button)
        noButton = view.findViewById(R.id.no_button)
        newSurvey = view.findViewById(R.id.new_survey_button)
        showResults = view.findViewById(R.id.show_results_button)
        questionDisplay = view.findViewById(R.id.question_display_textView)

        questionDisplay.text = surveyViewModel.retrieveQuestion()
        // Yes and no button listeners call our surveyViewModels methods to add values to yes or no.
        yesButton.setOnClickListener {
            surveyViewModel.addYes()
        }

        noButton.setOnClickListener {
            surveyViewModel.addNo()
        }
        // New Survey calls our surveyViewModel method to clear the data in the view model
        newSurvey.setOnClickListener {
            surveyViewModel.clearForNewSurvey()
            // We then ask main activity to read our key and open the QuestionPromptFragment
            parentFragmentManager.setFragmentResult(START_NEW_SURVEY, Bundle.EMPTY)
        }

        showResults.setOnClickListener {
            // Asks main activity to read our key and launch the ResultsFragment
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
package com.example.surveyapp_fragmentver

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider

const val SURVEY_QUESTION_MADE = "com.example.surveyapp_fragmentver.QuestionPrompt.QuestionMade"
class QuestionPromptFragment: Fragment() {



    private  lateinit var startSurveyButton: Button
    private lateinit var surveyQuestion: EditText

    private val surveyViewModel: Survey by lazy {
        ViewModelProvider(requireActivity()).get(Survey::class.java)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_question_prompt, container, false)

        startSurveyButton = view.findViewById(R.id.start_survey_button)
        surveyQuestion = view.findViewById(R.id.survey_question)

        startSurveyButton.setOnClickListener {
            val questionToAsk = surveyQuestion.text.toString()
            surveyViewModel.storeQuestion(questionToAsk)

            if(questionToAsk.isNotBlank()){
                parentFragmentManager.setFragmentResult(SURVEY_QUESTION_MADE, Bundle.EMPTY)
            } else {
                Toast.makeText(activity, "Please enter a question to ask.", Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }

    override fun onStart() {
        super.onStart()

        val titleWatcher = object: TextWatcher {

            override fun beforeTextChanged(sequence: CharSequence?,
                                           start: Int,
                                           count: Int,
                                           after: Int
            ){
                // this space intentionally left blank
            }

            override fun onTextChanged(sequence: CharSequence?,
                                       start: Int,
                                       before: Int,
                                       count: Int
            ) {
                val questionStore = sequence.toString()
                surveyViewModel.storeQuestion(questionStore)
            }

            override fun afterTextChanged(sequence: Editable?) {
                // this one too.
            }
        }

        surveyQuestion.addTextChangedListener(titleWatcher)

        }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         */

        @JvmStatic
        fun newInstance() = QuestionPromptFragment()
    }
}
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
    // Creates our view model we have to use require activity to instantiate it.
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
            // Stores our question and writes it to our view model
            val questionToAsk = surveyQuestion.text.toString()
            surveyViewModel.storeQuestion(questionToAsk)
            // Checks that their is a question.
            if(questionToAsk.isNotBlank()){
                // IF there is a question we tell our main activity to read our key and launch
                // the corresponding fragment.
                parentFragmentManager.setFragmentResult(SURVEY_QUESTION_MADE, Bundle.EMPTY)
            } else {
                Toast.makeText(activity, "Please enter a question to ask.", Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }
    // Got this code from the book. It creates an on start listener that maintains the text entered
    // the Question EditText and restores it. This is useful if a user rotates their phone while
    // writing their question it won't be lost.
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
        // From the videos we just need to instantiate a new instance of our fragment since
        // we are reading data from the view model.
        @JvmStatic
        fun newInstance() = QuestionPromptFragment()
    }
}
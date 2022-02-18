package com.example.surveyapp_fragmentver

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val currentFragment =
            supportFragmentManager.findFragmentById(R.id.fragment_container)

        if(currentFragment == null) {
            val fragment = SurveyFragment()
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container, fragment)
                .commit()
        }

        supportFragmentManager.setFragmentResultListener(SURVEY_QUESTION_MADE, this) {
            requestKey, bundle ->
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, SurveyFragment.newInstance())
                    .addToBackStack("SURVEY")
                    .commit()
        }

        supportFragmentManager.setFragmentResultListener(START_NEW_SURVEY, this) {
            requestKey, bundle ->
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, QuestionPromptFragment.newInstance())
                    .addToBackStack("QUESTION")
                    .commit()
        }

        supportFragmentManager.setFragmentResultListener(SHOW_SURVEY_RESULTS, this) {
            requestKey, bundle ->
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, ResultsFragment.newInstance())
                    .addToBackStack("RESULTS")
                    .commit()
        }

        supportFragmentManager.setFragmentResultListener(CONTINUE_SURVEY, this) {
            requestKey, bundle ->
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, SurveyFragment.newInstance())
                    .addToBackStack("QUESTION")
                    .commit()
        }

    }
}
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
        // Checks to see if there is currently a fragment being displayed. by Finding the
        // Fragment ID in the fragment container.
        // grabbed from the book but did not appear to do anything with my code.

//        val currentFragment =
//            supportFragmentManager.findFragmentById(R.id.fragment_container)
        // If the app just started and the current fragment ID is empty we launch
//        if(currentFragment == null) {
//            val fragment = QuestionPromptFragment()
//            supportFragmentManager.beginTransaction()
//                .add(R.id.fragment_container, fragment)
//                .commit()
//        }

        // The default fragment displayed in our activity is the QuestionPromptFragment so on create
        // The activity looks to inflate that fragment view.

        //These handle our requestKeys and send a string to our result listener so it knows what
        // fragment to launch.

        // We pass the setFragmentResultListener a key and owner of the life cycle, in this case
        // the main activity.
        supportFragmentManager.setFragmentResultListener(SURVEY_QUESTION_MADE, this) {
            requestKey, bundle ->
            // This then asks our activity to inflate a fragment into our fragment container.
            // We use replace here so not to have fragments on top of each other.
            // The Back stack makes it so if the user hits the back button they do not close out
            // of the app.
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, SurveyFragment.newInstance())
                    .addToBackStack("SURVEY")
                    .commit()
        }
        // We then repeat this for all the movements between fragments our app should be able to make
        // a way to tidy this up would be writing a function that takes a key, a fragment, and a
        // backstack.
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
package com.example.surveyapp_fragmentver

import androidx.lifecycle.ViewModel

class Survey() : ViewModel() {

    private var yesAnswers = 0
    private var noAnswers= 0
    private var surveyQuestion = ""
    // creates getters for our yes an no answer values.
    val currentYesAnswers: Int
        get() = yesAnswers

    val currentNoAnswers: Int
        get() = noAnswers


    // This function stores a string to our question variable.
    fun storeQuestion(question: String) {
        surveyQuestion = question
    }
    // Allows us to access the stored question variable, I could remove this
    // function if I made surveyQuestion a public var, though I think this may be better
    // for future encapsulation.
    fun retrieveQuestion(): String {
        return surveyQuestion
    }
    // This increments our yesAnswer int by one.
    fun addYes() {
        yesAnswers++
    }
    // This increments our noAnswer int by one.
    fun addNo() {
        noAnswers++
    }
    // Converts our yesAnswer int to a string and returns the string.
    fun getYesAnswers(): String {
        return yesAnswers.toString()
    }
    // converts our noAnswer int to a string and returns the string.
    fun getNoAnswers(): String {
        return noAnswers.toString()
    }
    // Resets the stored values in our survey Class.
    fun clearForNewSurvey () {
        yesAnswers = 0
        noAnswers = 0
        surveyQuestion = ""
    }
    // Just resets the numbers for our survey -- Used by the results fragment.
    fun resetSurvey () {
        yesAnswers = 0
        noAnswers = 0
    }

}
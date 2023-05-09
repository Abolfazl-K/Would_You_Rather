package com.example.wouldyourather

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel(){
    private lateinit var questions: MutableList<Options>

//    private val _onSkipClicked = MutableLiveData<Boolean>()
//    val onSkipClicked : LiveData<Boolean>
//        get() = _onSkipClicked

    private val _clickState = MutableLiveData<Boolean>()
    val clickState : LiveData<Boolean>
        get() = _clickState

    private val _question = MutableLiveData<Options>()
    val question : LiveData<Options>
        get() = _question

    private val _option1 = MutableLiveData<String>()
    val option1 : LiveData<String>
        get() = _option1

    private val _option2 = MutableLiveData<String>()
    val option2 : LiveData<String>
        get() = _option2

    init {
        _clickState.value = false
//        _onSkipClicked.value = false
        resetList()
        nextOption()
        _question.value = questions.removeAt(0)
        _option1.value = question.value?.option1
        _option2.value = question.value?.option2
    }
    private fun resetList(){
        questions = mutableListOf(
            Options("to die as a hero!", "to live as a monster!", 0, 0),
            Options("to eat for the rest of your life!", "to drink for the rest of your life!", 0, 0),
            Options("to cure cancer!", "to end hunger!", 0, 0),
            Options("invisibility!", "super strength!", 0, 0),
        )
        questions.shuffle()
    }

    private fun nextOption(){
        if(questions.isEmpty()){
            resetList()
        }
        _question.value = questions.removeAt(0)
        _option1.value = question.value?.option1
        _option2.value = question.value?.option2
    }
    fun onOption1Clicked(){
        question.value?.onOption1Clicked()
        _option1.value = formatedOption1String(question)
        _option2.value = formatedOption2String(question)
        _clickState.value = true
    }

    fun onOption2Clicked(){
        question.value?.onOption2Clicked()
        _option1.value = formatedOption1String(question)
        _option2.value = formatedOption2String(question)
        _clickState.value = true
    }

    fun onSkip(){
//        _onSkipClicked.value = false
        nextOption()
    }

    private fun formatedOption1String(question: LiveData<Options>): String {
        //TODO there is a bug in here somewhere O_o
        return "${question.value?.option1}\n" +
                "${question.value?.countOption1?.div((question.value?.countOption1?.plus(question.value!!.countOption2)!!))}%"
    }

    private fun formatedOption2String(question: LiveData<Options>): String {
        return "${question.value?.option2}\n" +
                "${question.value?.countOption1?.div((question.value?.countOption1?.plus(question.value!!.countOption2)!!))}%"
    }
}
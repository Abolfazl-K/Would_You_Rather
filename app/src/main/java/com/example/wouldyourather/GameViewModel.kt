package com.example.wouldyourather

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel(){
    private lateinit var questions: MutableList<Options>

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
    fun onOptionClicked(enum: Enum<ButtonState>){
        if(enum.equals("BUTTON_ONE")){
            _question.value!!.onOption1Clicked()
        }else if(enum.equals("BUTTON_TWO")){
            _question.value!!.onOption2Clicked()
        }
        _option1.value = formattedOptionString(question.value!!.option1, question.value!!.countOption1, question.value!!.countOption2)
        _option2.value = formattedOptionString(question.value!!.option2, question.value!!.countOption2, question.value!!.countOption1)
        _clickState.value = true
    }

    fun onSkip(){
        _clickState.value = false
        nextOption()
    }

    private fun formattedOptionString(option: String, count1:Int, count2:Int): String {
        //TODO there is a bug in here somewhere O_o
        return "${option}\n"
    }
}
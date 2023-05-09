package com.example.wouldyourather

class Options(val option1: String, val option2:String, var countOption1: Int, var countOption2: Int){
    fun onOption1Clicked(){
        countOption1.inc()
    }

    fun onOption2Clicked(){
        countOption2.inc()
    }
}
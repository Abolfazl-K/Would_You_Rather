package com.example.wouldyourather

import android.util.Log

class Options(val option1: String, val option2:String, var countOption1: Int, var countOption2: Int){
    fun onOption1Clicked(){
        countOption1.inc()
        Log.d("countOne", "count one updated ${countOption1}")
    }

    fun onOption2Clicked(){
        countOption2.inc()
        Log.d("countTwo", "count two updated ${countOption2}")

    }
}
package com.berakahnd.bmi.ui.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import kotlin.math.roundToInt

data class UiState(
    var sex : Int = 0,
    var height : Int = 0,
    var weight : Int = 0,
    var age : Int = 0,
    var img : Float = 0F,
    var interpretation : String = ""
)
class BMIViewModel : ViewModel() {
    var uiState = mutableStateOf(UiState())

    init {
        uiState.value = UiState(sex = 0, height = 150, weight = 50, age = 18)
        calculate()
    }
    fun calculate(){
        val height = uiState.value.height.toFloat() / 100
        var imc = (uiState.value.weight.toFloat()) / (height * height)
        imc = (imc * 100.0).roundToInt() / 100F
        when(uiState.value.sex){
            0 ->{
                var img = ((1.20 * imc) + (0.23 * uiState.value.age) - 5.4).toFloat()
                img = (img * 100.0).roundToInt() / 100F
                uiState.value.img = img
                uiState.value.interpretation = femaleEvaluateImg(uiState.value.img)
            }
            1 ->{
                var img = ((1.20 * imc) + (0.23 * uiState.value.age) - 10.8 - 5.4).toFloat()
                img = (img * 100.0).roundToInt() / 100F
                uiState.value.img = img
                uiState.value.interpretation = maleEvaluateImg(uiState.value.img)
            }
        }
        Log.i("img",uiState.value.img.toString())
    }
    private fun maleEvaluateImg(img: Float): String {
        return when {
            img < 15 -> "Too skinny"
            img in 15F..20F -> "Normal"
            img > 20 -> "Too much fat"
            else -> "Poor"
        }
    }
    private fun femaleEvaluateImg(img: Float): String {
        return when {
            img < 25 -> "Too skinny"
            img in 25F..30F -> "Normal"
            img > 30 -> "Too much fat"
            else -> "Poor"
        }
    }
}
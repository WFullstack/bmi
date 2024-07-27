package com.berakahnd.bmi.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.berakahnd.bmi.ui.component.CardSection
import com.berakahnd.bmi.ui.component.GenderCardSection
import com.berakahnd.bmi.ui.theme.BMITheme
import com.berakahnd.bmi.ui.viewmodel.BMIViewModel
import com.berakahnd.bmi.util.Constant.titleSize

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel : BMIViewModel = BMIViewModel(),
    modifier : Modifier = Modifier
){
    var uiState by viewModel.uiState
    var imgResult by remember {
        mutableStateOf(uiState.img)
    }
    var interpretation by remember {
        mutableStateOf(uiState.interpretation)
    }
    val scrollState = rememberScrollState()
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "BMI Calculator")
                }
            )
        }
    ) {
        Column(
            modifier = modifier.verticalScroll(scrollState)
                .padding(16.dp).fillMaxSize()
                .width(100.dp).padding(it),
            verticalArrangement = Arrangement.spacedBy(24.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(text = "What are you ?")
            GenderCardSection(){
                uiState.sex = it
                viewModel.calculate()
                imgResult = uiState.img
                interpretation = uiState.interpretation
            }
            Divider()
            CardSection(title = "What's your age ?", valueStart = uiState.age, param = "yrs"){ age ->
                uiState.age = age
                viewModel.calculate()
                imgResult = uiState.img
                interpretation = uiState.interpretation
            }
            Divider()
            Row(
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                CardSection(title = "What's your height ?", valueStart = uiState.height, param = "cm"){ height ->
                    uiState.height = height
                    viewModel.calculate()
                    imgResult = uiState.img
                    interpretation = uiState.interpretation
                }
                Spacer(modifier = Modifier.width(16.dp))
                CardSection(title = "What's your weight ?", valueStart = uiState.weight, param = "cm"){ weight ->
                    uiState.weight = weight
                    viewModel.calculate()
                    imgResult = uiState.img
                    interpretation = uiState.interpretation
                }
            }
            Divider()
            Text(
                modifier = modifier.fillMaxWidth(),
                text = "Body Mass Index",
                fontSize = titleSize,
                textAlign = TextAlign.Center
            )
            Text(
                modifier = modifier.fillMaxWidth(),
                text = "$imgResult %",
                fontSize = titleSize,
                textAlign = TextAlign.Center
            )
            Text(
                modifier = modifier.fillMaxWidth(),
                text = "$interpretation",
                fontSize = titleSize,
                textAlign = TextAlign.Center
            )
        }
    }


}
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GenderPreview() {
    BMITheme {
        //GenderComponent()
        HomeScreen()
    }
}
package com.berakahnd.bmi.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Female
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Male
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.berakahnd.bmi.ui.theme.BMITheme
import com.berakahnd.bmi.util.Constant.titleSize

data class Gender(
    val icon : ImageVector,
    val title : String
)

@Composable
fun GenderCardSection(
    onGenderClick : (Int) -> Unit
){
    val data = listOf(
        Gender(icon = Icons.Default.Female, title = "Female"),
        Gender(icon = Icons.Default.Male, title = "Male")
    )
    var selectedIndex by rememberSaveable { mutableStateOf(0) }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(48.dp)
    ) {
        data.forEachIndexed { index, gender ->
            val backgroundColor = if(index == selectedIndex) MaterialTheme.colorScheme.primary
            else MaterialTheme.colorScheme.primaryContainer
            GenderCard(
                gender = gender,
                backgroundColor = backgroundColor
            ){
                onGenderClick(index)
                selectedIndex = index
            }
        }
    }
}
@Composable
fun GenderCard(
    modifier : Modifier = Modifier,
    backgroundColor: Color,
    gender: Gender, onGenderClick : () -> Unit
){
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Surface(modifier = Modifier
            .size(64.dp)
            .clickable {
                onGenderClick()
            },
            color = backgroundColor,
            //contentColor = contentColorFor(color),
            shape = RoundedCornerShape(50.dp)
        ){
            Icon(modifier = Modifier.padding(8.dp)
                .size(50.dp),imageVector = gender.icon, contentDescription = null)
        }
        Spacer(modifier = Modifier.height(24.dp))
        Text(text = gender.title)
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardSection(modifier : Modifier = Modifier, title: String, param: String, valueStart : Int, onUpDown : (Int) -> Unit){
    var valueText by rememberSaveable { mutableStateOf(valueStart) }
    val config = LocalConfiguration.current
    val widthScreen = config.screenWidthDp.dp
    val width = (widthScreen  / 2) - 16.dp

    Column(
        modifier = modifier
            .width(width),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        Text(text = title)
        Spacer(modifier = Modifier.height(24.dp))
        Row(
            modifier= modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "$valueText", fontSize = titleSize)
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Icon(modifier = Modifier
                    .clickable {
                        valueText++
                        onUpDown(valueText)
                    }
                    .size(35.dp),imageVector = Icons.Default.KeyboardArrowUp,
                    contentDescription = null)
                Spacer(modifier = Modifier.height(16.dp))
                Icon(modifier = Modifier
                    .clickable {
                        valueText--
                        onUpDown(valueText)
                    }
                    .size(35.dp),imageVector = Icons.Default.KeyboardArrowDown,
                    contentDescription = null)
            }
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = param, fontSize = titleSize)
        }
    }

}
@Preview(showBackground = true)
@Composable
fun GenderPreview() {
    BMITheme {
        //GenderComponent()
        CardSection(title = "weight", valueStart = 100 , param = ""){

        }
    }
}
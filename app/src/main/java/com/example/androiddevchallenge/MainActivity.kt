/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androiddevchallenge.domain.models.Dog
import com.example.androiddevchallenge.ui.theme.MyTheme
import com.example.androiddevchallenge.ui.theme.teal200
import com.example.androiddevchallenge.ui.theme.typography

class MainActivity : AppCompatActivity() {

    private val listOfDogs: MutableList<Dog> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addDoggosInList()
        setContent {
            MyTheme {
                MyApp(listOfDogs)
            }
        }
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun addDoggosInList() {
        listOfDogs.add(Dog(0, "Foxy", "Female", R.drawable.foxy, "2 years"))
        listOfDogs.add(Dog(1, "Icy", "Female", R.drawable.icy, "1 year"))
        listOfDogs.add(Dog(2, "Goofy", "Male", R.drawable.labrador, "3 months"))
        listOfDogs.add(Dog(3, "Mike", "Male", R.drawable.mike, "4 years"))
        listOfDogs.add(Dog(4, "Rex", "Female", R.drawable.rex, "7 years"))
        listOfDogs.add(Dog(5, "Sleepy", "Male", R.drawable.sleepy, "5 months"))
        listOfDogs.add(Dog(6, "Slither", "Male", R.drawable.slither, "1.5 years"))
        listOfDogs.add(Dog(7, "Tonia", "Female", R.drawable.tommy, "2 years"))
        listOfDogs.add(Dog(8, "Mayra", "Female", R.drawable.toy, "10 months"))
        listOfDogs.add(Dog(9, "Zak", "Male", R.drawable.foxy, "9 months"))
    }
}

// Start building your app here!
@Composable
fun MyApp(dogsList: List<Dog>) {
    val selectedDoggo = remember { mutableStateOf(Dog()) }
    if (selectedDoggo.value.id == -1) {
        LazyColumn {
            items(dogsList) { doggo ->
                dogCard(dog = doggo, selectedDoggo)
            }
        }
    } else {
        details(doggo = selectedDoggo.value)
    }
}

@Composable
fun dogCard(dog: Dog, selectedDoggo: MutableState<Dog>) {
    Card(shape = RoundedCornerShape(3.dp), modifier = Modifier.padding(4.dp)) {
        Column {
            Image(
                painter = painterResource(id = dog.imageRes), contentDescription = null,
                modifier = Modifier
                    .height(250.dp)
                    .fillMaxWidth()
                    .clip(
                        shape = RoundedCornerShape(8.dp)
                    ),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.padding(top = 2.dp))
            Text(dog.doggoName, style = typography.body1, textAlign = TextAlign.Center)
            Text(text = "Sex: " + dog.sex, style = typography.body2)
            Text(text = "Age: " + dog.age + " old", style = typography.body2)
            Button(
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = teal200),
                modifier = Modifier
                    .padding(4.dp)
                    .fillMaxWidth(),
                onClick = { selectedDoggo.value = dog }
            ) {
                Text(
                    text = "Details", textAlign = TextAlign.Center, color = Color.White,
                    modifier = Modifier
                        .wrapContentHeight()
                        .wrapContentWidth()
                )
            }
        }
    }
}

@Composable
fun details(doggo: Dog) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .verticalScroll(rememberScrollState())
            .padding(bottom = 6.dp)
    ) {
        Image(
            painter = painterResource(id = doggo.imageRes),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp),
            contentScale = ContentScale.Crop
        )
        Text(
            doggo.doggoName,
            style = typography.body1,
            fontSize = 32.sp,
            textAlign = TextAlign.Center
        )
        Text(text = "Sex: " + doggo.sex, style = typography.body2, fontSize = 24.sp)
        Text(text = "Age: " + doggo.age + " old", style = typography.body2, fontSize = 24.sp)
    }
}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightPreview() {
    MyTheme {
        val dogs: MutableList<Dog> =
            mutableListOf(Dog(1, "Icy", "Female", R.drawable.icy, "1 year"))
        MyApp(dogs)
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun DarkPreview() {
    MyTheme(darkTheme = true) {
        val dogs: MutableList<Dog> =
            mutableListOf(Dog(1, "Icy", "Female", R.drawable.icy, "1 year"))
        MyApp(dogs)
    }
}

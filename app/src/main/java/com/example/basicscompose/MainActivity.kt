package com.example.basicscompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.basicscompose.ui.theme.BasicsComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App()
        }
    }
}

@Composable
fun App(){
    var item by remember {
        mutableStateOf("")
    }
    var deletedItem by remember {
        mutableStateOf("")
    }
    val items = mutableListOf<String>()
    BasicsComposeTheme {
        // A surface container using the 'background' color from the theme
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
            Column {
                Text(text = "To Do List!", fontSize = 32.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(12.dp))
                Row() {
                    OutlinedTextField(value = item, onValueChange = {item=it},label={
                        Text(text = "New Item")
                    })
                    if(deletedItem.isNotEmpty())
                    OutlinedTextField(value = deletedItem, onValueChange = {item=it
                                                                           deletedItem=""},label={
                        Text(text = "Deleted Item")
                    })
                    
                    Button(onClick = { if(item.isNotEmpty()){
                        items.add(item)
                        item = ""
                    }
                    }, modifier = Modifier.padding(14.dp)) {
                        Text(text = "Save")
                    }

                }
                Divider()
                Column {
                    for((index, value) in items.withIndex()){
                        ToDoListItem(name = value)
                        Button(onClick = {
                            deletedItem = items.removeAt(index)
                        }, modifier = Modifier.padding(14.dp)) {
                            Text(text = "Delete")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ToDoListItem(name: String) {
    var isSelected by remember {
        mutableStateOf(false)
    }
    var color = MaterialTheme.colors.background
    if(isSelected){
        color = MaterialTheme.colors.primary
    }
    Surface(color = color,
    modifier = Modifier.clickable {
        isSelected = !isSelected
    }
    ){
        Text(text = name,modifier = Modifier.padding(12.dp))
        Divider()
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    App()
}
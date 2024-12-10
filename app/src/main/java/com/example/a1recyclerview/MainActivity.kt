package com.example.a1recyclerview

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Modifier
import com.example.a1recyclerview.ui.theme._1RecyclerViewTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            _1RecyclerViewTheme {
                ListaDeTareas()
            }
        }
    }
}

@Composable
fun ListaDeTareas() {
    var tareas by remember {
        mutableStateOf(
            List(10) { index -> Tarea("Tarea $index", false) }
        )
    }

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(tareas) { tarea ->
            TareaItem(tarea) { tareaCompletada ->
                tareas = tareas.map {
                    if (it.titulo == tareaCompletada.titulo) {
                        it.copy(completada = !it.completada)
                    } else it
                }
            }
        }
    }
}

@Composable
fun TareaItem(tarea: Tarea, onCompletar: (Tarea) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(8.dp)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = if (tarea.completada) Icons.Default.Check else Icons.Default.Close,
            contentDescription = null,
            tint = if (tarea.completada) Color.Green else Color.Red,
            modifier = Modifier.padding(8.dp)
        )
        Text(
            text = tarea.titulo,
            modifier = Modifier.weight(1f),
            style = MaterialTheme.typography.bodyLarge
        )
        Button(onClick = { onCompletar(tarea) }) {
            Text(if (tarea.completada) "Pendiente" else "Completar")
        }
    }
}

data class Tarea(val titulo: String, val completada: Boolean)

@Preview(showBackground = true)
@Composable
fun ListaDeTareasPreview() {
    ListaDeTareas()
}
package com.example.campuscardlab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CampusCardScreen()
                }
            }
        }
    }
}

@Composable
fun ProfileHeader(name: String, status: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Box(
            modifier = Modifier
                .size(56.dp)
                .background(MaterialTheme.colorScheme.primaryContainer)
        )
        Column {
            Text(text = name, style = MaterialTheme.typography.titleMedium)
            Text(text = status, style = MaterialTheme.typography.bodyMedium)
        }
    }
}

@Composable
fun ScheduleRow(
    time: String,
    title: String,
    room: String,
    isLargeTime: Boolean = false,
    alignment: Alignment.Vertical = Alignment.CenterVertically
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = alignment,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = time,
            style = if (isLargeTime) MaterialTheme.typography.titleLarge else MaterialTheme.typography.bodyMedium,
            modifier = Modifier.width(70.dp)
        )

        Text(
            text = title,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.weight(1f),
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )

        Text(
            text = room,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.width(60.dp)
        )
    }
}

@Composable
fun CampusCardScreen() {
    var useSpaceBetween by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Campus Card", style = MaterialTheme.typography.headlineMedium)
            Text(text = "Dariya Akunova")
            Text(text = "Student ID: 2026001")
        }

        Spacer(modifier = Modifier.height(20.dp))

        ProfileHeader(
            name = "Dariya Akunova",
            status = "In class · Building B"
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(text = "Today", style = MaterialTheme.typography.titleLarge)

        Spacer(modifier = Modifier.height(12.dp))

        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            ScheduleRow(time = "09:00", title = "CS101 Intro to Programming", room = "Lab 3")

            ScheduleRow(
                time = "11:00",
                title = "MATH210 Advanced Calculus and Linear Algebra",
                room = "A-204",
                isLargeTime = true,
                alignment = Alignment.Top
            )


            ScheduleRow(
                time = "14:00",
                title = "Soft Skills & Professional Communication Workshop",
                room = "Hall 1"
            )
        }

        Spacer(modifier = Modifier.weight(1f))


        TextButton(
            onClick = { useSpaceBetween = !useSpaceBetween },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(text = "Toggle Action Row Layout (Current: ${if (useSpaceBetween) "SpaceBetween" else "Center"})")
        }


        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = if (useSpaceBetween) {
                Arrangement.SpaceBetween
            } else {
                Arrangement.Center
            },
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (!useSpaceBetween) {
                Button(onClick = { }) { Text("Map") }
                Spacer(modifier = Modifier.width(16.dp))
                OutlinedButton(onClick = { }) { Text("Show QR") }
            } else {
                Button(onClick = { }) { Text("Map") }
                OutlinedButton(onClick = { }) { Text("Show QR") }
            }
        }
    }
}
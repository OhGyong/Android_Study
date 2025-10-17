package com.study.workmanagerwithalarmmanager.feature

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.study.workmanagerwithalarmmanager.feature.theme.WorkManagerWithAlarmManagerTheme
import dagger.hilt.android.AndroidEntryPoint
import java.util.Locale

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WorkManagerWithAlarmManagerTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    TimeCounterRoute(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimeCounterRoute(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = viewModel()
) {
    val context = LocalContext.current
    val count by viewModel.count.collectAsStateWithLifecycle()
    val hour by viewModel.selectedHour.collectAsStateWithLifecycle()
    val minute by viewModel.selectedMinute.collectAsStateWithLifecycle()
    val second by viewModel.selectedSecond.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = true) {
        viewModel.toastEvent.collect { message ->
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = count.toString(),
            style = MaterialTheme.typography.displayLarge,
            textAlign = TextAlign.Center
        )

        val timeLabel = String.format(Locale.getDefault(), "%02d:%02d:%02d", hour, minute, second)
        Text(
            text = "AlarmManager 설정 시간\n→ $timeLabel",
            modifier = Modifier.padding(top = 16.dp),
            style = MaterialTheme.typography.bodyLarge
        )

        Button(
            onClick = {
                viewModel.setAlarmManager()
            },
            modifier = Modifier.padding(top = 12.dp)
        ) {
            Text("AlarmManager Set")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TimeCounterPreview() {
    WorkManagerWithAlarmManagerTheme {
        TimeCounterRoute()
    }
}
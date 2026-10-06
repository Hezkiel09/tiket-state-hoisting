package com.example.tikethoisting

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import kotlinx.coroutines.delay
import screens.TicketScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                TicketParent(modifier = Modifier.padding(innerPadding))
            }
        }
    }
}

@Composable
fun TicketParent(modifier: Modifier = Modifier) {
    val hargaTiket = 50000
    var jumlahTiket by remember { mutableStateOf(1) }
    var namaPembeli by remember { mutableStateOf("") }

    var statusText by remember { mutableStateOf("Silakan pesan tiket") }
    var isProcessing by remember { mutableStateOf(false) }


    var processTriggerCount by remember { mutableStateOf(0) }

    LaunchedEffect(processTriggerCount) {

        if (processTriggerCount > 0) {
            if (namaPembeli.isBlank()) {
                statusText = "Nama Masih Kosong"
            } else {
                isProcessing = true
                statusText = "Memproses pesanan........."
                delay(5000)
                statusText = "Tiket telah dipesan"
                isProcessing = false
            }
        }
    }

    TicketScreen(
        nama = namaPembeli,
        onNamaChange = { namaPembeli = it },
        jumlahTiket = jumlahTiket,
        onTambahTiket = { jumlahTiket++ },
        onKurangTiket = { if (jumlahTiket > 1) jumlahTiket-- },
        hargaTiket = hargaTiket,
        statusText = statusText,
        isProcessing = isProcessing,
        onPesanClick = {
            processTriggerCount++
        },
        modifier = modifier
    )
}
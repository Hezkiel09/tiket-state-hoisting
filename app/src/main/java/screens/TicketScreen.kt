package screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TicketScreen(
    nama: String,
    onNamaChange: (String) -> Unit,
    jumlahTiket: Int,
    onTambahTiket: () -> Unit,
    onKurangTiket: () -> Unit,
    hargaTiket: Int,
    statusText: String,
    isProcessing: Boolean,
    onPesanClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(text = "Pemesanan Tiket", fontSize = 24.sp)


        OutlinedTextField(
            value = nama,
            onValueChange = onNamaChange,
            label = { Text("Nama Pembeli") },
            modifier = Modifier.fillMaxWidth(),
            enabled = !isProcessing
        )


        Text(text = "Harga per tiket: Rp $hargaTiket")
        Text(text = "Total Harga: Rp ${hargaTiket * jumlahTiket}")


        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Button(
                onClick = onKurangTiket,
                enabled = !isProcessing && jumlahTiket > 1
            ) {
                Text("-")
            }
            Text(text = "$jumlahTiket", fontSize = 20.sp)
            Button(
                onClick = onTambahTiket,
                enabled = !isProcessing
            ) {
                Text("+")
            }
        }


        Button(
            onClick = onPesanClick,
            modifier = Modifier.fillMaxWidth(),
            enabled = !isProcessing
        ) {
            Text("Pesan Tiket")
        }


        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(modifier = Modifier.padding(16.dp)) {
                Text(text = "Status: $statusText")
            }
        }
    }
}
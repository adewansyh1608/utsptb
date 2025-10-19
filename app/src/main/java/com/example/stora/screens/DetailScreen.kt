package com.example.stora.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.stora.data.DummyData
import com.example.stora.ui.theme.StoraBlueDark
import com.example.stora.ui.theme.StoraWhite
import com.example.stora.ui.theme.StoraYellow
import kotlinx.coroutines.delay

@Composable
fun DetailScreen(navController: NavHostController, itemId: String?) {
    val item = DummyData.inventoryItemList.find { it.id == itemId }
    var isVisible by remember { mutableStateOf(false) }

    // Efek animasi saat layar pertama kali dibuka
    LaunchedEffect(Unit) {
        delay(100) // Sedikit delay agar animasi berjalan mulus
        isVisible = true
    }

    // Animasi untuk ketinggian latar belakang biru
    val blueBgWeight by animateFloatAsState(
        targetValue = if (isVisible) 0.15f else 1f, // 15% tinggi saat visible
        animationSpec = tween(durationMillis = 800),
        label = "Blue BG Weight"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(StoraBlueDark) // Background default putih
    ) {
        // --- BAGIAN BIRU (ATAS) ---
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(blueBgWeight) // Tinggi animatif
                .background(StoraBlueDark),
            contentAlignment = Alignment.Center
        ) {
            // Top Bar Kustom
            StoraTopBar(
                title = "Detail",
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }

        // --- BAGIAN PUTIH (BAWAH) ---
        Box(
            modifier = Modifier
                .fillMaxSize() // Mengisi sisa tinggi
                .clip(RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp))
                .background(StoraWhite)
        ) {
            // Konten Detail (muncul dengan animasi)
            androidx.compose.animation.AnimatedVisibility(
                visible = isVisible,
                enter = slideInVertically(animationSpec = tween(800, delayMillis = 200)) { it } + fadeIn(animationSpec = tween(800, delayMillis = 200)),
                modifier = Modifier.fillMaxSize()
            ) {
                if (item != null) {
                    DetailContent(item = item)
                } else {
                    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                        Text("Item tidak ditemukan!", color = Color.Black)
                    }
                }
            }
        }
    }
}

@Composable
fun DetailContent(item: com.example.stora.data.InventoryItem) {
    val textGray = Color(0xFF585858)

    // Box utama untuk menampung konten dan footer
    Box(modifier = Modifier.fillMaxSize()) {

        // Konten yang bisa di-scroll
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 24.dp, vertical = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Nama Inventaris
            Text(
                text = item.name,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black, // Sesuai gambar
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(4.dp))

            // No Inventaris
            Text(
                text = item.noinv,
                fontSize = 16.sp,
                color = textGray, // Warna abu-abu
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(24.dp))

            // Info Detail (Rata Kiri)
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.Start
            ) {
                DetailInfoRow(label = "Jumlah", value = item.quantity.toString())
                DetailInfoRow(label = "Kategori", value = item.category)
                DetailInfoRow(label = "Kondisi", value = item.condition)
                DetailInfoRow(label = "Lokasi", value = item.location)
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Garis Pemisah
            Divider(
                color = StoraYellow,
                thickness = 1.dp,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Deskripsi
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "Deskripsi :",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = item.description,
                    fontSize = 14.sp,
                    color = textGray,
                    lineHeight = 20.sp
                )
            }

            // Spacer untuk memberi ruang agar footer tidak menutupi konten
            Spacer(modifier = Modifier.height(100.dp))
        }

        // Footer "Dibuat pada"
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter) // Menempel di bawah
                .padding(bottom = 24.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(StoraBlueDark.copy(alpha = 0.13f)) // Sesuai instruksi: transparansi 13%
                .padding(horizontal = 24.dp, vertical = 12.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Dibuat pada ${item.date}",
                color = Color.Black.copy(alpha = 0.7f) // Warna font agar kontras
            )
        }
    }
}

@Composable
fun DetailInfoRow(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Teks Label
        Text(
            text = label,
            modifier = Modifier.width(100.dp), // Lebar tetap untuk label
            fontSize = 16.sp,
            color = Color.Black,
            fontWeight = FontWeight.Normal
        )
        // Teks Titik Dua
        Text(
            text = ": ",
            fontSize = 16.sp,
            color = Color.Black,
            fontWeight = FontWeight.Normal
        )
        // Teks Value
        Text(
            text = value,
            fontSize = 16.sp,
            color = Color.Black,
            fontWeight = FontWeight.Normal,
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}
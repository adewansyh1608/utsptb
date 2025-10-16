package com.example.stora.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.stora.data.DummyData
import com.example.stora.data.InventoryItem
import com.example.stora.navigation.Routes
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavHostController) {
    val items = DummyData.inventoryItemList
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            TopAppBar(
                title = { Text("STORA") }, // Nama aplikasi di TopAppBar
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = Color.White
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate(Routes.ADD_ITEM_SCREEN) },
                containerColor = MaterialTheme.colorScheme.secondary
            ) {
                Icon(Icons.Filled.Add, contentDescription = "Tambah Item", tint = Color.Black)
            }
        }
    ) { paddingValues ->
        if (items.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize().padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                Text("Penyimpanan kosong. Tambahkan item baru!")
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(horizontal = 8.dp)
            ) {
                items(items, key = { it.id }) { item ->
                    // Animasi untuk setiap item yang muncul di daftar
                    AnimatedVisibility(
                        visible = true, // Selalu true, untuk trigger animasi saat item ditambahkan
                        enter = fadeIn(animationSpec = tween(500)) + slideInVertically(initialOffsetY = { it / 2 })
                    ) {
                        InventoryItemCard(item = item) {
                            // Navigasi ke detail dengan mengirim ID item
                            navController.navigate(Routes.detailScreen(item.id))
                        }
                    }
                }
            }
        }

        // Efek untuk menampilkan Snackbar saat item baru ditambahkan
        LaunchedEffect(navController.currentBackStackEntry) {
            val newItemAdded = navController.currentBackStackEntry
                ?.savedStateHandle?.get<Boolean>("newItemAdded")
            if (newItemAdded == true) {
                scope.launch {
                    snackbarHostState.showSnackbar("Item baru berhasil ditambahkan!")
                }
                // Reset state agar Snackbar tidak muncul lagi saat re-compose
                navController.currentBackStackEntry?.savedStateHandle?.set("newItemAdded", false)
            }
        }
    }
}

@Composable
fun InventoryItemCard(item: InventoryItem, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp, horizontal = 8.dp)
            .clickable(onClick = onClick), // Memberikan efek ripple saat diklik
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = item.name,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                modifier = Modifier.weight(1f)
            )
            Text(
                text = "Qty: ${item.quantity}",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}
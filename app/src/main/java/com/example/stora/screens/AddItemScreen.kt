package com.example.stora.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.stora.data.DummyData
import com.example.stora.data.InventoryItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddItemScreen(navController: NavHostController) {
    var name by remember { mutableStateOf("") }
    var quantity by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var isError by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Tambah Item Baru") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Kembali")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = name,
                onValueChange = { name = it; isError = false },
                label = { Text("Nama Item") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )
            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = quantity,
                onValueChange = { quantity = it; isError = false },
                label = { Text("Jumlah") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true
            )
            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = description,
                onValueChange = { description = it; isError = false },
                label = { Text("Deskripsi") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp),
            )
            Spacer(modifier = Modifier.height(24.dp))

            if (isError) {
                Text(
                    text = "Semua kolom harus diisi dengan benar!",
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }

            Button(
                onClick = {
                    val qtyInt = quantity.toIntOrNull()
                    if (name.isNotBlank() && qtyInt != null && description.isNotBlank()) {
                        val newItem = InventoryItem(
                            name = name,
                            quantity = qtyInt,
                            description = description
                        )
                        DummyData.inventoryItemList.add(0, newItem) // Tambah item baru di posisi teratas

                        // Kirim sinyal ke HomeScreen untuk menampilkan Snackbar
                        navController.previousBackStackEntry
                            ?.savedStateHandle?.set("newItemAdded", true)
                        navController.popBackStack()
                        isError = false
                    } else {
                        isError = true
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Simpan Item")
            }
        }
    }
}
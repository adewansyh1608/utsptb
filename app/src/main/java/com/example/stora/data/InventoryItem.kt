package com.example.stora.data

import java.util.UUID

// Data class untuk merepresentasikan satu item di STORA
data class InventoryItem(
    val id: String = UUID.randomUUID().toString(), // ID unik untuk setiap item
    val name: String,
    val quantity: Int,
    val description: String
)
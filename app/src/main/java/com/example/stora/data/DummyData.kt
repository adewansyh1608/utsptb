package com.example.stora.data

import androidx.compose.runtime.mutableStateListOf

// Menggunakan object agar menjadi singleton (satu instance di seluruh aplikasi)
object DummyData {
    // mutableStateListOf akan secara otomatis memberi tahu UI jika ada perubahan data
    val inventoryItemList = mutableStateListOf(
        InventoryItem(
            name = "Laptop ASUS ROG",
            quantity = 5,
            description = "Laptop gaming dengan spek tinggi, prosesor i9, RTX 4090."
        ),
        InventoryItem(
            name = "Keyboard Mekanikal Keychron K2",
            quantity = 12,
            description = "Keyboard wireless dengan brown switch, layout 75%."
        ),
        InventoryItem(
            name = "Mouse Logitech MX Master 3S",
            quantity = 8,
            description = "Mouse ergonomis untuk produktivitas, silent click."
        ),
        InventoryItem(
            name = "Monitor LG UltraGear 27 inch",
            quantity = 10,
            description = "Monitor 144Hz dengan resolusi 2K untuk gaming dan editing."
        ),
        InventoryItem(
            name = "Webcam Razer Kiyo Pro",
            quantity = 7,
            description = "Webcam Full HD dengan sensor cahaya adaptif."
        )
    )
}
package com.example.stora.navigation

// Mendefinisikan rute sebagai object agar terpusat dan menghindari kesalahan pengetikan
object Routes {
    const val HOME_SCREEN = "home"
    const val DETAIL_SCREEN = "detail/{itemId}" // Menggunakan argumen untuk mengirim ID item
    const val ADD_ITEM_SCREEN = "add_item"

    // Fungsi bantuan untuk membuat rute detail dengan ID yang spesifik
    fun detailScreen(itemId: String) = "detail/$itemId"
}
package com.example.stora.navigation
object Routes {
    const val HOME_SCREEN = "home"
    const val DETAIL_SCREEN = "detail/{itemId}"
    const val ADD_ITEM_SCREEN = "add_item"

    fun detailScreen(itemId: String) = "detail/$itemId"
}
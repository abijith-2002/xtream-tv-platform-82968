package com.example.android_tv_frontend.data

data class ContentItem(
    val id: String,
    val title: String,
    val assetName: String, // filename in assets/figmaimages
    val progress: Float = 0f
)

/**
 * Helper to build URI for Android assets folder for Coil.
 */
fun assetUri(name: String): String = "file:///android_asset/figmaimages/$name"

object SampleContent {
    val hero = "figma_image_1_13.png" // Highlight image

    val continueWatching = listOf(
        ContentItem("cw1", "Rogue One", "figma_image_1_41.png", progress = 0.40f),
        ContentItem("cw2", "Ex Machina", "figma_image_1_68.png", progress = 0.35f),
        ContentItem("cw3", "Sing Street", "figma_image_1_85.png", progress = 0.30f),
        ContentItem("cw4", "2012", "figma_image_1_102.png", progress = 0.20f),
        ContentItem("cw5", "Ad Astra", "figma_image_1_119.png", progress = 0.15f)
    )

    val channels = listOf(
        ContentItem("ch1", "Marca Claro Radio", "figma_image_1_154.png"),
        ContentItem("ch2", "E.T.", "figma_image_1_179.png"),
        ContentItem("ch3", "Marca Claro Radio", "figma_image_1_218.png")
    )
}

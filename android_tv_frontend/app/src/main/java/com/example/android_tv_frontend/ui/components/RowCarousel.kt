package com.example.android_tv_frontend.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.android_tv_frontend.data.ContentItem
import com.example.android_tv_frontend.ui.theme.Dimens

@Composable
fun RowCarousel(
    title: String,
    items: List<ContentItem>,
    largeCardFirst: Boolean = false
) {
    Column {
        if (title.isNotEmpty()) {
            Text(text = title)
            Spacer(modifier = Modifier.height(12.dp))
        }

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(Dimens.CardSpacing)
        ) {
            items(items) { item ->
                val isLarge = largeCardFirst && item == items.first()
                ContentCard(
                    title = item.title,
                    assetName = item.assetName,
                    width = if (isLarge) Dimens.CardWidthLarge else Dimens.CardWidth,
                    height = if (isLarge) Dimens.CardHeightLarge else Dimens.CardHeight,
                    progress = item.progress
                )
            }
        }
    }
}

package com.example.android_tv_frontend.ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.draw.scale
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.android_tv_frontend.data.assetUri
import com.example.android_tv_frontend.ui.theme.AppColors
import com.example.android_tv_frontend.ui.theme.Dimens

@Composable
fun ContentCard(
    title: String,
    assetName: String,
    width: Dp,
    height: Dp,
    progress: Float = 0f
) {
    val focused = remember { mutableStateOf(false) }
    val scale by animateFloatAsState(targetValue = if (focused.value) Dimens.FocusScale else 1f, label = "card_scale")
    val shape = RoundedCornerShape(8.dp)

    Column(
        horizontalAlignment = Alignment.Start
    ) {
        Box(
            modifier = Modifier
                .width(width)
                .height(height)
                .clip(shape)
                .shadow(if (focused.value) 12.dp else 4.dp, shape = shape, clip = false)
                .onFocusChanged { focused.value = it.isFocused }
                .focusable()
                .then(Modifier)
                .padding(0.dp)
                .size(width, height)
                .background(AppColors.CardBg)
                .then(Modifier)
                .scale(scale = scale)
        ) {
            AsyncImage(
                model = assetUri(assetName),
                contentDescription = title,
                modifier = Modifier
                    .width(width)
                    .height(height)
                    .clip(shape),
                contentScale = ContentScale.Crop
            )

            if (progress > 0f) {
                val trackWidth = width - 38.dp
                Box(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(bottom = 8.dp)
                        .width(trackWidth)
                        .height(18.dp)
                        .clip(RoundedCornerShape(9.dp))
                        .background(AppColors.ProgressTrack)
                ) {
                    Box(
                        modifier = Modifier
                            .width(trackWidth * progress)
                            .height(10.dp)
                            .align(Alignment.CenterStart)
                            .clip(RoundedCornerShape(5.dp))
                            .background(AppColors.ProgressFill)
                    )
                }
            }
        }

        // Title background bar
        Box(
            modifier = Modifier
                .width(width - 62.dp)
                .height(Dimens.CardTitleHeight)
                .padding(top = 8.dp)
                .clip(RoundedCornerShape(6.dp))
                .background(AppColors.CardBg)
        ) {
            Text(
                text = title,
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(horizontal = 16.dp),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = AppColors.PrimaryText
            )
        }
    }
}

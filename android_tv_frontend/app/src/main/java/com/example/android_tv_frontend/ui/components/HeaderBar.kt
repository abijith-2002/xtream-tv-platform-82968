package com.example.android_tv_frontend.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.android_tv_frontend.data.assetUri
import com.example.android_tv_frontend.ui.theme.AppColors
import com.example.android_tv_frontend.ui.theme.Dimens

@Composable
fun HeaderBar(
    navItems: List<String>,
    selectedNavIndex: MutableState<Int>,
    onNavSelected: (Int) -> Unit
) {
    Row(
        modifier = Modifier
            .height(Dimens.HeaderHeight)
            .background(AppColors.Surface, RoundedCornerShape(34.dp))
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        // Logo placeholder (text block to keep pixel alignment if SVG logo not provided as PNG)
        Box(
            modifier = Modifier
                .size(120.dp, 34.dp)
                .background(Color.Transparent)
        )

        Spacer(modifier = Modifier.width(24.dp))

        // Search rounded box placeholder
        Box(
            modifier = Modifier
                .size(36.dp)
                .clip(RoundedCornerShape(18.dp))
                .background(AppColors.CardBg)
                .border(1.dp, AppColors.SecondaryText.copy(alpha = 0.4f), RoundedCornerShape(18.dp))
        )

        Spacer(modifier = Modifier.width(24.dp))

        navItems.forEachIndexed { idx, label ->
            val focused = remember { mutableStateOf(false) }
            val bg = if (selectedNavIndex.value == idx) AppColors.AccentDark else Color.Transparent
            val shape = RoundedCornerShape(37.dp)
            Box(
                modifier = Modifier
                    .height(53.dp)
                    .background(bg, shape)
                    .padding(horizontal = 16.dp)
                    .onFocusChanged {
                        if (it.isFocused) {
                            selectedNavIndex.value = idx
                        }
                    }
                    .focusable(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = label,
                    color = if (selectedNavIndex.value == idx) AppColors.PrimaryText else AppColors.SecondaryText
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
        }

        Spacer(modifier = Modifier.weight(1f, fill = true))

        // Avatar
        AsyncImage(
            model = assetUri("figma_image_1_231.png"),
            contentDescription = "Perfil",
            modifier = Modifier
                .size(56.dp)
                .clip(CircleShape)
                .border(1.dp, AppColors.SecondaryText.copy(alpha = 0.4f), CircleShape),
            contentScale = ContentScale.Crop
        )
    }
}

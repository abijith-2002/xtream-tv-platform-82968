package com.example.android_tv_frontend.ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusOrder
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.android_tv_frontend.ui.theme.AppColors
import com.example.android_tv_frontend.ui.theme.Dimens

data class SidebarItem(val label: String)

@Composable
fun Sidebar(
    items: List<SidebarItem>,
    selectedIndex: MutableState<Int>,
    onItemSelected: (Int) -> Unit,
    sidebarFocusState: MutableState<Boolean>,
    firstItemFocusRequester: FocusRequester
) {
    Column(
        modifier = Modifier
            .width(Dimens.SidebarWidth)
            .fillMaxHeight()
            .background(AppColors.SidebarBg)
            .padding(top = Dimens.SafeMargin, bottom = Dimens.SafeMargin),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        items.forEachIndexed { index, item ->
            val focused = remember { mutableStateOf(false) }
            val scale by animateFloatAsState(targetValue = if (focused.value) Dimens.FocusScale else 1f, label = "sb_scale")

            Row(
                modifier = Modifier
                    .height(Dimens.SidebarItemHeight)
                    .fillMaxWidth()
                    .padding(vertical = 6.dp)
                    .onFocusChanged {
                        focused.value = it.isFocused
                        if (it.isFocused) {
                            sidebarFocusState.value = true
                            selectedIndex.value = index
                        }
                    }
                    .focusOrder(if (index == 0) firstItemFocusRequester else FocusRequester())
                    .focusable()
                    .scale(scale),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Selected indicator
                Box(
                    modifier = Modifier
                        .width(Dimens.SidebarIndicatorWidth)
                        .height(Dimens.SidebarItemHeight - 12.dp)
                        .background(
                            color = if (selectedIndex.value == index) AppColors.SidebarIndicator else Color.Transparent,
                            shape = RoundedCornerShape(2.dp)
                        )
                )

                // "Icon" placeholder box (use brand accent for now)
                Box(
                    modifier = Modifier
                        .padding(start = 12.dp)
                        .size(Dimens.SidebarIconSize)
                        .background(
                            color = if (selectedIndex.value == index) AppColors.Accent else AppColors.SecondaryText.copy(alpha = 0.5f),
                            shape = RoundedCornerShape(4.dp)
                        )
                )

                Text(
                    text = item.label,
                    modifier = Modifier.padding(start = 12.dp),
                    color = if (selectedIndex.value == index) AppColors.PrimaryText else AppColors.SecondaryText,
                    fontWeight = if (selectedIndex.value == index) FontWeight.SemiBold else FontWeight.Normal
                )
            }
        }
    }
}

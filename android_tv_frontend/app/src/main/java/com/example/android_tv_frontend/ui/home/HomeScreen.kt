package com.example.android_tv_frontend.ui.home

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.android_tv_frontend.data.SampleContent
import com.example.android_tv_frontend.data.assetUri
import com.example.android_tv_frontend.ui.components.HeaderBar
import com.example.android_tv_frontend.ui.components.RowCarousel
import com.example.android_tv_frontend.ui.components.Sidebar
import com.example.android_tv_frontend.ui.components.SidebarItem
import com.example.android_tv_frontend.ui.theme.AppColors
import com.example.android_tv_frontend.ui.theme.Dimens

@Composable
fun HomeScreen(
    sidebarFocusState: MutableState<Boolean>
) {
    val sidebarItems = listOf(
        SidebarItem("Inicio"),
        SidebarItem("Películas"),
        SidebarItem("Series"),
        SidebarItem("TV en vivo"),
        SidebarItem("Kids"),
        SidebarItem("Mis Contenidos")
    )
    val selectedSidebarIndex = remember { mutableIntStateOf(0) }
    val selectedTopNavIndex = remember { mutableIntStateOf(0) }
    val firstSidebarItemFocusRequester = remember { FocusRequester() }

    BackHandler(enabled = !sidebarFocusState.value) {
        // Return focus to sidebar when back is pressed in content area
        firstSidebarItemFocusRequester.requestFocus()
        sidebarFocusState.value = true
    }

    Row(
        modifier = Modifier
            .fillMaxSize()
            .background(AppColors.Background)
    ) {
        // Sidebar
        Sidebar(
            items = sidebarItems,
            selectedIndex = selectedSidebarIndex,
            onItemSelected = { /* No-op: sample */ },
            sidebarFocusState = sidebarFocusState,
            firstItemFocusRequester = firstSidebarItemFocusRequester
        )

        // Main content area
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 24.dp, end = Dimens.SafeMargin, top = Dimens.SafeMargin, bottom = Dimens.SafeMargin),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            // Header
            HeaderBar(
                navItems = sidebarItems.map { it.label },
                selectedNavIndex = selectedTopNavIndex,
                onNavSelected = { selectedTopNavIndex.intValue = it }
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Hero / Highlights image
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(444.dp)
                    .clip(RoundedCornerShape(8.dp))
            ) {
                AsyncImage(
                    model = assetUri(SampleContent.hero),
                    contentDescription = "Destacado",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Seguí viendo",
                color = AppColors.PrimaryText
            )

            Spacer(modifier = Modifier.height(12.dp))

            RowCarousel(
                title = "",
                items = SampleContent.continueWatching,
                largeCardFirst = true
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Canales de TV",
                color = AppColors.PrimaryText
            )

            Spacer(modifier = Modifier.height(12.dp))

            RowCarousel(
                title = "",
                items = SampleContent.channels,
                largeCardFirst = false
            )
        }
    }
}

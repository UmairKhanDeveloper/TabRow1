package com.example.tabrow1

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.Call
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TabRow() {
    var selectedTabIndex by remember {
        mutableIntStateOf(0)
    }
    val tabItem = listOf(
        TabItem("Chat", selectedicon = Icons.Filled.Home, unselectedicon = Icons.Outlined.Home),
        TabItem(
            "Status",
            selectedicon = Icons.Filled.ShoppingCart,
            unselectedicon = Icons.Outlined.ShoppingCart
        ),
        TabItem("Calls", selectedicon = Icons.Filled.Call, unselectedicon = Icons.Outlined.Call),

        )
    var pagerState = rememberPagerState {
        tabItem.size

    }
    LaunchedEffect(selectedTabIndex) {
        pagerState.animateScrollToPage(selectedTabIndex)
    }
    LaunchedEffect(key1 =pagerState) {
        if (!pagerState.isScrollInProgress)
            selectedTabIndex=pagerState.currentPage

    }


    Column(modifier = Modifier.fillMaxWidth()) {
        androidx.compose.material3.TabRow(selectedTabIndex = selectedTabIndex) {
            tabItem.forEachIndexed { index, tabItem ->
                Tab(selected = index == selectedTabIndex, onClick = {
                    selectedTabIndex = index
                }, icon = {
                    if (selectedTabIndex == index)
                        Icon(imageVector = tabItem.selectedicon, contentDescription = "")
                    else
                        Icon(imageVector = tabItem.unselectedicon, contentDescription = "")
                }, text = {
                    Text(text = tabItem.title)
                })


            }

        }
        HorizontalPager(
            state = pagerState, modifier = Modifier
                .fillMaxSize()
                .weight(1f)
        ) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = tabItem[it].title)

            }

        }

    }

}


data class TabItem(
    val title: String,
    val selectedicon: ImageVector,
    val unselectedicon: ImageVector
)

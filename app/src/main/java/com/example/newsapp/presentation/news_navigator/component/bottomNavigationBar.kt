package com.example.newsapp.presentation.news_navigator.component

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController


data class BottomNavigationItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unSelectedIcon: ImageVector,
    val contentDescription: String,
    val route: String
)

@Composable
fun BottomNavigationBar(
    items: List<BottomNavigationItem>,
    navController: NavHostController,
    currentDestination: NavDestination?,
) {
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.surface
    ) {
        items.forEach { item ->

            val selected = currentDestination
                ?.hierarchy
                ?.any { it.route == item.route } == true

            NavigationBarItem(
                selected = selected,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Icon(
                        imageVector = if (selected)
                            item.selectedIcon
                        else
                            item.unSelectedIcon,
                        contentDescription = null
                    )
                },
                label = {
                    Text(text = item.title)
                }
            )
        }
    }
}


//@Composable
//fun BottomNavigationBar(
//     bottomNavigationItem: List<BottomNavigationItem>,
//     navHostController: NavHostController,
//     currentDestination: NavDestination?,
//) {
//
//    NavigationBar (
//        containerColor =  MaterialTheme.colorScheme.surface,
//    ){
//
//
//        bottomNavigationItem.forEach{ item ->
//            val selected = item.route == currentDestination?.route
//            NavigationBarItem(
//                selected = selected,
//                onClick = {
//                    navHostController.navigate(item.route) {
//                        popUpTo(navHostController.graph.startDestinationId) {
//                            saveState = true
//                        }
//                        launchSingleTop = true
//                        restoreState = true
//                    }
//                },
//                icon = {
//                    Icon(
//                        imageVector = if (selected) item.selectedIcon
//                            else item.unSelectedIcon,
//                        contentDescription = item.contentDescription,
//                    )
//                },
//                label = {
//                    Text(
//                        text = item.title,
//                        color = MaterialTheme.colorScheme.onSurface
//                    )
//                }
//
//            )
//
//
//        }
//    }
//
//}
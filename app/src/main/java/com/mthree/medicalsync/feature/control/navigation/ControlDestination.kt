package com.mthree.medicalsync.feature.control.navigation

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.mthree.medicalsync.core.navigation.ControlNavigationDestination
import com.mthree.medicalsync.domain.model.Control
import com.mthree.medicalsync.feature.control.ControlRoute

const val ASK_NOTIFICATION_PERMISSION = "notification_permission"
const val ASK_ALARM_PERMISSION = "alarm_permission"
object ControlDestination : ControlNavigationDestination {
    override val route = "control_route"
    override val destination = "control_destination"
}

fun NavGraphBuilder.controlGraph(navController: NavController, bottomBarVisibility: MutableState<Boolean>, fabVisibility: MutableState<Boolean>, navigateToControlDetail: (Control) -> Unit) {
    composable(route = ControlDestination.route) {
        LaunchedEffect(null) {
            bottomBarVisibility.value = true
            fabVisibility.value = true
        }
        val askNotificationPermission = navController.currentBackStackEntry?.savedStateHandle?.get<Boolean>(ASK_NOTIFICATION_PERMISSION) ?: false
        val askAlarmPermission = navController.currentBackStackEntry?.savedStateHandle?.get<Boolean>(ASK_ALARM_PERMISSION) ?: false

        ControlRoute(navController, askNotificationPermission, askAlarmPermission, navigateToControlDetail)
    }
}

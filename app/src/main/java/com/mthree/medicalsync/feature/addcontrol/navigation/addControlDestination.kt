package com.mthree.medicalsync.feature.addcontrol.navigation

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.mthree.medicalsync.core.navigation.ControlNavigationDestination
import com.mthree.medicalsync.domain.model.Control
import com.mthree.medicalsync.feature.addcontrol.AddControlRoute
import com.mthree.medicalsync.feature.home.navigation.ASK_ALARM_PERMISSION
import com.mthree.medicalsync.feature.home.navigation.ASK_NOTIFICATION_PERMISSION

object AddControlDestination : ControlNavigationDestination {
    override val route = "add_control_route"
    override val destination = "add_control_destination"
}

fun NavGraphBuilder.addControlGraph(navController: NavController, bottomBarVisibility: MutableState<Boolean>, fabVisibility: MutableState<Boolean>, onBackClicked: () -> Unit, navigateToControlConfirm: (List<Control>) -> Unit) {
    composable(route = AddControlDestination.route) {
        LaunchedEffect(null) {
            bottomBarVisibility.value = false
            fabVisibility.value = false
        }

        navController.previousBackStackEntry?.savedStateHandle.apply {
            this?.set(ASK_NOTIFICATION_PERMISSION, true)
        }
        navController.previousBackStackEntry?.savedStateHandle.apply {
            this?.set(ASK_ALARM_PERMISSION, true)
        }
        AddControlRoute(onBackClicked, navigateToControlConfirm)
    }
}

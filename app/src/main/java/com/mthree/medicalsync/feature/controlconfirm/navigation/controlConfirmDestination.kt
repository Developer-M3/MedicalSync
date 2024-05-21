package com.mthree.medicalsync.feature.controlconfirm.navigation

import android.os.Bundle
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.mthree.medicalsync.core.navigation.MedicalNavigationDestination
import com.mthree.medicalsync.domain.model.Control
import com.mthree.medicalsync.feature.controlconfirm.ControlConfirmRoute

const val CONTROL = "control"

object ControlConfirmDestination : MedicalNavigationDestination {
    override val route = "control_confirm_route"
    override val destination = "control_confirm_destination"
}

fun NavGraphBuilder.controlConfirmGraph(navController: NavController, bottomBarVisibility: MutableState<Boolean>, fabVisibility: MutableState<Boolean>, onBackClicked: () -> Unit, navigateToHome: () -> Unit) {

    composable(
        route = ControlConfirmDestination.route,
    ) {
        LaunchedEffect(null) {
            bottomBarVisibility.value = false
            fabVisibility.value = false
        }
        val controlBundle = navController.previousBackStackEntry?.savedStateHandle?.get<Bundle>(CONTROL)
        val controlList = controlBundle?.getParcelableArrayList<Control>(CONTROL)
        ControlConfirmRoute(controlList, onBackClicked, navigateToHome)
    }
}

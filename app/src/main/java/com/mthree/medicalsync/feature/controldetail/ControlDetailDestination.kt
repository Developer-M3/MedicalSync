package com.mthree.medicalsync.feature.controldetail

import android.os.Bundle
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.mthree.medicalsync.core.navigation.MedicalNavigationDestination
import com.mthree.medicalsync.domain.model.Control
import com.mthree.medicalsync.feature.controlconfirm.navigation.CONTROL

object ControlDetailDestination : MedicalNavigationDestination {
    override val route = "control_detail_route"
    override val destination = "control_detail_destination"
}

fun NavGraphBuilder.controlDetailGraph(navController: NavController, bottomBarVisibility: MutableState<Boolean>, fabVisibility: MutableState<Boolean>, onBackClicked: () -> Unit) {

    composable(
        route = ControlDetailDestination.route,
    ) {
        LaunchedEffect(null) {
            bottomBarVisibility.value = false
            fabVisibility.value = false
        }
        val controlBundle = navController.previousBackStackEntry?.savedStateHandle?.get<Bundle>(
            CONTROL
        )
        val control = controlBundle?.getParcelable<Control>(CONTROL)
        ControlDetailRoute(control, onBackClicked)
    }
}

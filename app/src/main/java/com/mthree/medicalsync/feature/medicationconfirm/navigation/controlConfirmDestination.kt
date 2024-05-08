package com.mthree.medicalsync.feature.medicationconfirm.navigation

import android.os.Bundle
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.mthree.medicalsync.core.navigation.MedicalNavigationDestination
import com.mthree.medicalsync.domain.model.Medication
import com.mthree.medicalsync.feature.medicationconfirm.MedicationConfirmRoute

const val MEDICATION = "medication"

object MedicationConfirmDestination : MedicalNavigationDestination {
    override val route = "medication_confirm_route"
    override val destination = "medication_confirm_destination"
}

fun NavGraphBuilder.medicationConfirmGraph(navController: NavController, bottomBarVisibility: MutableState<Boolean>, fabVisibility: MutableState<Boolean>, onBackClicked: () -> Unit, navigateToHome: () -> Unit) {

    composable(
        route = MedicationConfirmDestination.route,
    ) {
        LaunchedEffect(null) {
            bottomBarVisibility.value = false
            fabVisibility.value = false
        }
        val medicationBundle = navController.previousBackStackEntry?.savedStateHandle?.get<Bundle>(MEDICATION)
        val medicationList = medicationBundle?.getParcelableArrayList<Medication>(MEDICATION)
        MedicationConfirmRoute(medicationList, onBackClicked, navigateToHome)
    }
}

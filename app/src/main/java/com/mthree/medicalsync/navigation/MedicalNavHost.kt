package com.mthree.medicalsync.navigation

import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.mthree.medicalsync.feature.addmedication.navigation.addMedicationGraph
import com.mthree.medicalsync.feature.calendar.navigation.calendarGraph
import com.mthree.medicalsync.feature.history.historyGraph
import com.mthree.medicalsync.feature.home.navigation.HomeDestination
import com.mthree.medicalsync.feature.home.navigation.homeGraph
import com.mthree.medicalsync.feature.medicationconfirm.navigation.MEDICATION
import com.mthree.medicalsync.feature.medicationconfirm.navigation.MedicationConfirmDestination
import com.mthree.medicalsync.feature.medicationconfirm.navigation.medicationConfirmGraph
import com.mthree.medicalsync.feature.medicationdetail.MedicationDetailDestination
import com.mthree.medicalsync.feature.medicationdetail.medicationDetailGraph
import com.mthree.medicalsync.util.navigateSingleTop

@Composable
fun MedicalNavHost(
    bottomBarVisibility: MutableState<Boolean>,
    fabVisibility: MutableState<Boolean>,
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = HomeDestination.route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        homeGraph(
            navController = navController,
            bottomBarVisibility = bottomBarVisibility,
            fabVisibility = fabVisibility,
            navigateToMedicationDetail = {
                val bundle = Bundle()
                bundle.putParcelable(MEDICATION, it)
                navController.currentBackStackEntry?.savedStateHandle.apply {
                    this?.set(MEDICATION, bundle)
                }
                navController.navigate(MedicationDetailDestination.route)
            }
        )
        historyGraph(
            bottomBarVisibility = bottomBarVisibility,
            fabVisibility = fabVisibility,
            navigateToMedicationDetail = {
                val bundle = Bundle()
                bundle.putParcelable(MEDICATION, it)
                navController.currentBackStackEntry?.savedStateHandle.apply {
                    this?.set(MEDICATION, bundle)
                }
                navController.navigate(MedicationDetailDestination.route)
            }
        )
        medicationDetailGraph(
            navController = navController,
            bottomBarVisibility = bottomBarVisibility,
            fabVisibility = fabVisibility,
            onBackClicked = { navController.navigateUp() }
        )
        calendarGraph(bottomBarVisibility, fabVisibility)
        addMedicationGraph(
            navController = navController,
            bottomBarVisibility = bottomBarVisibility,
            fabVisibility = fabVisibility,
            onBackClicked = { navController.navigateUp() },
            navigateToMedicationConfirm = {
                // TODO: Replace with medication id
                val bundle = Bundle()
                bundle.putParcelableArrayList(MEDICATION, ArrayList(it))
                navController.currentBackStackEntry?.savedStateHandle.apply {
                    this?.set(MEDICATION, bundle)
                }
                navController.navigate(MedicationConfirmDestination.route)
            }
        )
        medicationConfirmGraph(
            navController = navController,
            bottomBarVisibility = bottomBarVisibility,
            fabVisibility = fabVisibility,
            onBackClicked = { navController.navigateUp() },
            navigateToHome = {
                navController.navigateSingleTop(HomeDestination.route)
            }
        )
    }
}

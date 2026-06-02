package com.example.manejobugs

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.manejobugs.ui.screens.bugdetail.BugDetailScreen
import com.example.manejobugs.ui.screens.bugdetail.BugDetailViewModel
import com.example.manejobugs.ui.screens.buglist.BugListScreen
import com.example.manejobugs.ui.screens.buglist.BugListViewModel
import com.example.manejobugs.ui.screens.createbug.CreateBugScreen
import com.example.manejobugs.ui.screens.createbug.CreateBugViewModel
import com.example.manejobugs.ui.screens.login.LoginScreen
import com.example.manejobugs.ui.screens.login.LoginViewModel

private object Routes {
    const val Login = "login"
    const val BugList = "bugs"
    const val Detail = "bugs/{bugId}"
    const val Create = "bugs/create"
}

@Composable
fun ManejoBugsApp() {
    val navController = rememberNavController()
    val repository = AppGraph.bugRepository

    NavHost(navController = navController, startDestination = Routes.Login) {
        composable(Routes.Login) {
            LoginScreen(
                viewModel = LoginViewModel(),
                onLoginSuccess = {
                    navController.navigate(Routes.BugList) {
                        popUpTo(Routes.Login) { inclusive = true }
                    }
                }
            )
        }
        composable(Routes.BugList) {
            BugListScreen(
                viewModel = BugListViewModel(repository),
                onBugClick = { bugId -> navController.navigate("bugs/$bugId") },
                onCreateClick = { navController.navigate(Routes.Create) }
            )
        }
        composable(
            route = Routes.Detail,
            arguments = listOf(navArgument("bugId") { type = NavType.StringType })
        ) { backStackEntry ->
            val bugId = backStackEntry.arguments?.getString("bugId").orEmpty()
            BugDetailScreen(
                viewModel = BugDetailViewModel(repository, bugId),
                onBack = { navController.popBackStack() }
            )
        }
        composable(Routes.Create) {
            CreateBugScreen(
                viewModel = CreateBugViewModel(repository),
                onBack = { navController.popBackStack() },
                onCreated = { navController.popBackStack() }
            )
        }
    }
}

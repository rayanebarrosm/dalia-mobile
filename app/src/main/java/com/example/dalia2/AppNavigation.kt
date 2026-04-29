package com.example.dalia2

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.dalia2.ui.components.BottomNavigationBar
import com.example.dalia2.ui.theme.screen.*

// implementar viewModel para o salvamento no banco de dados
fun saveData(month: Int, weeks: Int) {
    println(" Dados salvos - Mês: $month, Semanas: $weeks")

}
fun saveFactor(factor: String) {
  println("Fator escolhido: $factor")
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route


    // Lista de rotas onde a barra deve aparecer
    val bottomBarRoutes = listOf("home", "calendar", "bot", "forum", "settings")

    Scaffold(
        bottomBar = {
            if (currentRoute in bottomBarRoutes) {
                BottomNavigationBar(navController)
            }
        }
    ) { padding ->
    NavHost(
        navController = navController,
        startDestination = "welcomeScreen" // pagina inícial

    ) {

        composable("welcomeScreen") {
            WelcomeScreen(
                onLoginClick = {
                    navController.navigate("login")
                },
                onSignupClick = {
                    navController.navigate("signup")
                }
            )
        }

        composable("login") {
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate("home")
                },
                onSignUpClick = {
                    navController.navigate("signup")
                }
            )
        }

        composable("signup") {
            SignupScreen(navController = navController,
                onSignUpSuccess = {
                    navController.navigate("verification") // vai para o quiz
                },
                onLoginClick = {
                    navController.navigate("login") // Navega para login
                }
            )
        }

        //passa o email pela "url"
        composable("verification/{email}") { backStackEntry ->
            val email = backStackEntry.arguments?.getString("email") ?: ""
            VerificationScreen(
                email = email,
                onVerificationSucess = {
                    navController.navigate("startQuiz")
                }
            )
        }

        composable("startQuiz") {
            StartQuizScreen(
                onStartClick = {
                    navController.navigate("quizAge")
                }
            )
        }

        composable("quizAge") {
            QuizAgeScreen(
                onNextClick = {
                    navController.navigate("quizMode")
                }
            )
        }

        composable("quizMode") {
            QuizModeScreen(
                onPregnantModeClick = {
                    navController.navigate("quizPregnant-1")
                },
                onPeriodModeClick = {
                    navController.navigate("quizPeriod-1")
                }
            )
        }

        composable("quizPregnant-1") {
            QuizPregnant1Screen(
                onNextClick = {
                    navController.navigate("quizPregnant-2")
                }
            )
        }

        composable("quizPregnant-2") {
            QuizPregnant2Screen(
                onNextClick = {
                    navController.navigate("quizPregnant-3")
                }
            )
        }

        composable("quizPregnant-3") {
            QuizPregnant3Screen(
                onNextClick = { months, weeks ->
                    saveData(months, weeks)
                    navController.navigate("quizPregnant-4")
                }
            )
        }

        composable("quizPregnant-4") {
            QuizPregnant4Screen(
                onNextClick = { months, weeks ->
                    saveData(months, weeks)
                    navController.navigate("quizPregnant-5")
                }
            )
        }

        composable("quizPregnant-5") {
            QuizPregnant5Screen(
                onNextClick = {
                    navController.navigate("home")
                }
            )
        }

        composable("quizPeriod-1") {
            QuizPeriod1Screen(
                onNextClick = {
                    navController.navigate("quizPeriod-2")
                }
            )
        }

        composable("quizPeriod-2") {
            QuizPeriod2Screen(
                onNextClick = {
                    navController.navigate("quizPeriod-3")
                }
            )
        }

        composable("quizPeriod-3") {
            QuizPeriod3Screen(
                onNextClick = {
                    navController.navigate("quizPeriod-4")
                }
            )
        }

        composable("quizPeriod-4") {
            QuizPeriod4Screen(
                onNextClick = {
                    navController.navigate("quizPeriod-5")
                }
            )
        }

        composable("quizPeriod-5") {
            QuizPeriod5Screen(
                onNextClick = { factor ->
                    saveFactor(factor)
                    navController.navigate("home")
                }
            )
        }


        composable("home") {
            HomeScreen()
        }
        composable("calendar") {
            CalendarScreen()
        }
        composable("bot") {
            DaliaBotScreen()
        }
        composable("forum") {
            ForumScreen()
        }
        composable("settings") {
            ProfileScreen()
        }

    }
    }
}
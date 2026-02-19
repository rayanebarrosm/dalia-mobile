package com.example.dalia2

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.dalia2.ui.theme.screen.*

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

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
                    navController.navigate("signup") // Navega para home
                }
            )
        }

        composable("signup") {
            SignupScreen(
                onSignUpSuccess = {
                    navController.navigate("startQuiz") // vai para o quiz
                },
                onLoginClick = {
                    navController.navigate("login") // Navega para login
                }
            )
        }

        composable ("startQuiz"){
            StartQuizScreen(
                onStartClick = {
                    navController.navigate("quizAge")
                }
            )
        }

        composable("quizAge") {
            QuizAgeScreen (
                onNextClick = {
                    navController.navigate("quizMode")
                }
            )
        }

        composable("quizMode") {
            QuizModeScreen (
                onPregnantModeClick = {
                    navController.navigate("quizPregnant-1")
                },
                onPeriodModeClick = {
                    navController.navigate("quizPeriod-1")
                }
            )
        }

        composable("quizPregnant-1") {
            QuizPregnant1Screen (
                onNextClick = {
                    navController.navigate("quizPregnant-2")
                }
            )
        }

        composable("quizPregnant-2") {
            QuizPregnant2Screen (
                onNextClick = {
                    navController.navigate("quizPregnant-3")
                }
            )
        }

        composable("quizPregnant-3") {
            QuizPregnant3Screen (
                onNextClick = {
                    navController.navigate("quizPregnant-4")
                }
            )
        }

        composable("quizPregnant-4") {
            QuizPregnant4Screen(
                onNextClick = {
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
                onNextClick = {
                    navController.navigate("home")
                }
            )
        }

    }
}

package com.example.tugas8.view.uicontroller

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tugas8.view.FormSiswa
import com.example.tugas8.view.TampilanSiswa
import com.example.tugas8.viewmodel.SiswaViewModel

enum class Navigasi {
    FormSiswa,
    DetailSiswa
}

@Composable
fun DataApp(
    navController: NavHostController = rememberNavController(),
    siswaViewModel: SiswaViewModel = viewModel()
) {
    Scaffold { innerPadding ->

        NavHost(
            navController = navController,
            startDestination = Navigasi.FormSiswa.name,
            modifier = Modifier.padding(innerPadding)
        ) {

            // ⬅️ Halaman Form
            composable(Navigasi.FormSiswa.name) {
                FormSiswa(
                    pilihanJK = listOf("Laki-laki", "Perempuan"),
                    onSubmitButtonClicked = { listData ->
                        siswaViewModel.setSiswa(listData)
                        navController.navigate(Navigasi.DetailSiswa.name)
                    }
                )
            }

            // ⬅️ Halaman Detail
            composable(Navigasi.DetailSiswa.name) {

                val state = siswaViewModel.statusUI.collectAsState()

                TampilanSiswa(
                    statusUISiswa = state.value,
                    onBackBtnClick = {
                        backToForm(navController)
                    }
                )
            }
        }
    }
}

private fun backToForm(navController: NavHostController) {
    navController.popBackStack(
        route = Navigasi.FormSiswa.name,
        inclusive = false
    )
}

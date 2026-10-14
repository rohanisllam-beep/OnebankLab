package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.model.ScreenRoute
import com.example.state.BankAppViewModel
import com.example.ui.components.GlassEffects.ambientLiquidBackground
import com.example.ui.components.ReceiptDialog
import com.example.ui.theme.MyApplicationTheme
import com.example.ui.unified.OneBankLiquidPrecisionScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .ambientLiquidBackground(),
                    color = Color.Transparent
                ) {
                    OneBankAppRoot()
                }
            }
        }
    }
}

@Composable
fun OneBankAppRoot(
    viewModel: BankAppViewModel = viewModel()
) {
    val state by viewModel.uiState.collectAsState()

    // Handle System Back button: returns to HOME if on a secondary tab
    BackHandler(enabled = state.currentScreen != ScreenRoute.HOME) {
        viewModel.navigateTo(ScreenRoute.HOME)
    }

    // Direct Unified Experience: Concept 01 Liquid Glass + Concept 04 Precision Financial
    OneBankLiquidPrecisionScreen(state = state, viewModel = viewModel)

    // Transfer Confirmation Receipt Dialog
    if (state.isTransferSuccessDialogOpen && state.lastTransferReceipt != null) {
        ReceiptDialog(
            transaction = state.lastTransferReceipt!!,
            language = state.language,
            onDismiss = { viewModel.closeTransferReceipt() }
        )
    }

    // Transaction Details Receipt Dialog (when tapping any transaction in history or dashboard)
    state.selectedTransactionForDetail?.let { tx ->
        ReceiptDialog(
            transaction = tx,
            language = state.language,
            onDismiss = { viewModel.showTransactionDetail(null) }
        )
    }
}

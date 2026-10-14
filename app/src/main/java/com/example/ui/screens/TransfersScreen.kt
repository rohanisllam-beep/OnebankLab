package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalance
import androidx.compose.material.icons.filled.AccountBalanceWallet
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.FlashOn
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.localization.Strings
import com.example.model.Language
import com.example.state.BankAppViewModel
import com.example.state.BankUiState

@Composable
fun TransfersScreen(
    state: BankUiState,
    viewModel: BankAppViewModel,
    modifier: Modifier = Modifier
) {
    val language = state.language
    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Spacer(modifier = Modifier.height(4.dp))

        // Title
        Text(
            text = Strings.tr("transfer_title", language),
            fontSize = 24.sp,
            fontWeight = FontWeight.ExtraBold,
            color = Color(0xFF1E1B1B)
        )
        Text(
            text = Strings.tr("transfer_subtitle", language),
            fontSize = 13.sp,
            color = Color(0xFF757575)
        )

        // Source Account Card
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(18.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = Strings.tr("source_account", language),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF757575)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = if (language == Language.BN) state.transferSourceAccount.nameBn else state.transferSourceAccount.nameEn,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF1E1B1B)
                        )
                        Text(
                            text = state.transferSourceAccount.accountNumber,
                            fontSize = 12.sp,
                            color = Color(0xFF757575)
                        )
                    }
                    Text(
                        text = Strings.formatCurrency(state.transferSourceAccount.balance, language),
                        fontSize = 15.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color(0xFF16A34A)
                    )
                }
            }
        }

        // Destination Type Tabs
        Text(
            text = Strings.tr("transfer_to", language),
            fontSize = 13.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color(0xFF1E1B1B)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            val destinationOptions = listOf(
                "BKASH" to ("bKash" to "বিকাশ"),
                "NAGAD" to ("Nagad" to "নগদ"),
                "OBL" to ("ONE Bank" to "ওয়ান ব্যাংক"),
                "NPSB" to ("Other Bank" to "অন্য ব্যাংক")
            )

            destinationOptions.forEach { (type, labels) ->
                val isSelected = state.transferDestinationType == type
                Surface(
                    modifier = Modifier
                        .weight(1f)
                        .clip(RoundedCornerShape(12.dp))
                        .clickable { viewModel.setTransferDestinationType(type) },
                    shape = RoundedCornerShape(12.dp),
                    color = if (isSelected) Color(0xFFE82E2E) else Color.White,
                    border = androidx.compose.foundation.BorderStroke(1.dp, if (isSelected) Color(0xFFE82E2E) else Color(0xFFF3ECEC))
                ) {
                    Column(
                        modifier = Modifier.padding(vertical = 10.dp, horizontal = 4.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Icon(
                            imageVector = when (type) {
                                "BKASH", "NAGAD" -> Icons.Default.AccountBalanceWallet
                                "OBL" -> Icons.Default.FlashOn
                                else -> Icons.Default.AccountBalance
                            },
                            contentDescription = type,
                            tint = if (isSelected) Color.White else Color(0xFFE82E2E),
                            modifier = Modifier.size(18.dp)
                        )
                        Text(
                            text = if (language == Language.BN) labels.second else labels.first,
                            fontSize = 11.sp,
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                            color = if (isSelected) Color.White else Color(0xFF424242)
                        )
                    }
                }
            }
        }

        // Recipient Input
        OutlinedTextField(
            value = state.transferRecipient,
            onValueChange = { viewModel.setTransferRecipient(it) },
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text(
                    text = when (state.transferDestinationType) {
                        "BKASH" -> if (language == Language.BN) "বিকাশ ওয়ালেট নম্বর" else "bKash Mobile Number"
                        "NAGAD" -> if (language == Language.BN) "নগদ ওয়ালেট নম্বর" else "Nagad Mobile Number"
                        "OBL" -> if (language == Language.BN) "ওয়ান ব্যাংক হিসাব নম্বর" else "ONE Bank Account Number"
                        else -> if (language == Language.BN) "অন্য ব্যাংকের অ্যাকাউন্ট নং / রাউটিং" else "Other Bank A/C & Routing"
                    },
                    fontSize = 13.sp
                )
            },
            placeholder = {
                Text(
                    text = if (state.transferDestinationType in listOf("BKASH", "NAGAD")) "01712-XXXXXX" else "018 2049 XXXX XX",
                    fontSize = 13.sp,
                    color = Color(0xFF9E9E9E)
                )
            },
            singleLine = true,
            shape = RoundedCornerShape(16.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFFE82E2E),
                unfocusedBorderColor = Color(0xFFF3ECEC),
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White
            )
        )

        // Amount Input
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            OutlinedTextField(
                value = state.transferAmount,
                onValueChange = { viewModel.setTransferAmount(it) },
                modifier = Modifier.fillMaxWidth(),
                label = { Text(text = Strings.tr("amount_label", language), fontSize = 13.sp) },
                placeholder = { Text(text = "0.00", fontSize = 13.sp, color = Color(0xFF9E9E9E)) },
                prefix = {
                    Text(
                        text = "৳ ",
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFFE82E2E),
                        fontSize = 18.sp
                    )
                },
                singleLine = true,
                shape = RoundedCornerShape(16.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFFE82E2E),
                    unfocusedBorderColor = Color(0xFFF3ECEC),
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White
                )
            )

            // Preset Amount Chips
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                listOf("500", "1000", "5000", "10000").forEach { amt ->
                    val isPicked = state.transferAmount == amt
                    Surface(
                        modifier = Modifier
                            .weight(1f)
                            .clip(RoundedCornerShape(10.dp))
                            .clickable { viewModel.setTransferAmount(amt) },
                        shape = RoundedCornerShape(10.dp),
                        color = if (isPicked) Color(0xFFE82E2E) else Color.White,
                        border = androidx.compose.foundation.BorderStroke(1.dp, if (isPicked) Color(0xFFE82E2E) else Color(0xFFF3ECEC))
                    ) {
                        Box(
                            modifier = Modifier.padding(vertical = 8.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = if (language == Language.BN) Strings.toBengaliDigits("৳ $amt") else "৳ $amt",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = if (isPicked) Color.White else Color(0xFF1E1B1B)
                            )
                        }
                    }
                }
            }
        }

        // Purpose Note Input
        OutlinedTextField(
            value = state.transferPurpose,
            onValueChange = { viewModel.setTransferPurpose(it) },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text(text = Strings.tr("purpose_hint", language), fontSize = 13.sp, color = Color(0xFF9E9E9E)) },
            singleLine = true,
            shape = RoundedCornerShape(16.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFFE82E2E),
                unfocusedBorderColor = Color(0xFFECEAF4),
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White
            )
        )

        // Security & Fast Transfer Badge
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .background(Color(0xFFF5F3FA))
                .border(1.dp, Color(0xFFEAE7F2), RoundedCornerShape(12.dp))
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Lock,
                contentDescription = "Secured",
                tint = Color(0xFFE82E2E),
                modifier = Modifier.size(16.dp)
            )
            Text(
                text = if (language == Language.BN) "বাংলাদেশ ব্যাংক অনুমোদিত এনপিএসবি ও বিইএফটিএন নেটওয়ার্কে সুরক্ষিত" else "Protected by Bangladesh Bank certified NPSB & BEFTN protocols",
                fontSize = 11.sp,
                color = Color(0xFF616161)
            )
        }

        // Send Button
        Button(
            onClick = { viewModel.executeTransfer() },
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE82E2E))
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Send,
                    contentDescription = "Transfer",
                    tint = Color.White,
                    modifier = Modifier.size(18.dp)
                )
                Text(
                    text = Strings.tr("send_button", language),
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))
    }
}

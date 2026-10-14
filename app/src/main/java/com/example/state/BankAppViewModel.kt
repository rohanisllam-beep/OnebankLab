package com.example.state

import androidx.lifecycle.ViewModel
import com.example.data.SampleBankData
import com.example.model.BankAccount
import com.example.model.BankCard
import com.example.model.BranchLocation
import com.example.model.ConceptId
import com.example.model.Language
import com.example.model.ScreenRoute
import com.example.model.Transaction
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.util.UUID

data class BankUiState(
    val currentConcept: ConceptId? = null, // null = Showroom Screen
    val preferredConcept: ConceptId = ConceptId.LIQUID_GLASS,
    val currentScreen: ScreenRoute = ScreenRoute.HOME,
    val language: Language = Language.EN,
    val isBalanceHidden: Boolean = false,
    val accounts: List<BankAccount> = SampleBankData.accounts,
    val selectedAccount: BankAccount = SampleBankData.accounts[0],
    val cards: List<BankCard> = SampleBankData.cards,
    val transactions: List<Transaction> = SampleBankData.transactions,
    val branches: List<BranchLocation> = SampleBankData.branches,
    val searchQuery: String = "",
    val selectedFilter: String = "ALL", // "ALL", "IN", "OUT"
    val selectedTransactionForDetail: Transaction? = null,
    val isCardFlipped: Boolean = false,
    // Transfer State
    val transferSourceAccount: BankAccount = SampleBankData.accounts[0],
    val transferDestinationType: String = "BKASH", // "OBL", "BKASH", "NAGAD", "NPSB"
    val transferRecipient: String = "",
    val transferAmount: String = "",
    val transferPurpose: String = "",
    val lastTransferReceipt: Transaction? = null,
    val isTransferSuccessDialogOpen: Boolean = false,
    // Service Feedback Dialogs
    val feedbackMessage: String? = null,
    val showBranchLocatorDialog: Boolean = false
)

class BankAppViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(BankUiState())
    val uiState: StateFlow<BankUiState> = _uiState.asStateFlow()

    fun openShowroom() {
        _uiState.update { it.copy(currentConcept = null, currentScreen = ScreenRoute.HOME) }
    }

    fun selectConcept(concept: ConceptId) {
        _uiState.update { it.copy(currentConcept = concept, currentScreen = ScreenRoute.HOME) }
    }

    fun setPreferredConcept(concept: ConceptId) {
        _uiState.update { it.copy(preferredConcept = concept) }
    }

    fun navigateTo(screen: ScreenRoute) {
        _uiState.update { it.copy(currentScreen = screen) }
    }

    fun toggleLanguage() {
        _uiState.update {
            val nextLang = if (it.language == Language.EN) Language.BN else Language.EN
            it.copy(language = nextLang)
        }
    }

    fun setLanguage(lang: Language) {
        _uiState.update { it.copy(language = lang) }
    }

    fun toggleBalancePrivacy() {
        _uiState.update { it.copy(isBalanceHidden = !it.isBalanceHidden) }
    }

    fun selectAccount(account: BankAccount) {
        _uiState.update { it.copy(selectedAccount = account) }
    }

    fun setSearchQuery(query: String) {
        _uiState.update { it.copy(searchQuery = query) }
    }

    fun setFilter(filter: String) {
        _uiState.update { it.copy(selectedFilter = filter) }
    }

    fun showTransactionDetail(transaction: Transaction?) {
        _uiState.update { it.copy(selectedTransactionForDetail = transaction) }
    }

    fun toggleCardFlip() {
        _uiState.update { it.copy(isCardFlipped = !it.isCardFlipped) }
    }

    fun toggleFreezeCard(cardId: String) {
        _uiState.update { state ->
            val updated = state.cards.map { card ->
                if (card.id == cardId) card.copy(isFrozen = !card.isFrozen) else card
            }
            state.copy(cards = updated)
        }
    }

    fun setTransferSource(account: BankAccount) {
        _uiState.update { it.copy(transferSourceAccount = account) }
    }

    fun setTransferDestinationType(dest: String) {
        _uiState.update { it.copy(transferDestinationType = dest) }
    }

    fun setTransferRecipient(recipient: String) {
        _uiState.update { it.copy(transferRecipient = recipient) }
    }

    fun setTransferAmount(amount: String) {
        _uiState.update { it.copy(transferAmount = amount) }
    }

    fun setTransferPurpose(purpose: String) {
        _uiState.update { it.copy(transferPurpose = purpose) }
    }

    fun executeTransfer() {
        val state = _uiState.value
        val amt = state.transferAmount.toDoubleOrNull() ?: 1000.0
        val targetName = when (state.transferDestinationType) {
            "BKASH" -> "bKash Transfer"
            "NAGAD" -> "Nagad Transfer"
            "OBL" -> "ONE Bank Own Account"
            else -> "Other Bank NPSB Instant"
        }
        val targetNameBn = when (state.transferDestinationType) {
            "BKASH" -> "বিকাশ স্থানান্তর"
            "NAGAD" -> "নগদ স্থানান্তর"
            "OBL" -> "ওয়ান ব্যাংক হিসাব"
            else -> "অন্য ব্যাংক (এনপিএসবি)"
        }
        val recipientLabel = if (state.transferRecipient.isNotBlank()) state.transferRecipient else "01712-882914"

        val receipt = Transaction(
            id = "TX-" + (100000..999999).random(),
            titleEn = targetName,
            titleBn = targetNameBn,
            subtitleEn = "To: $recipientLabel",
            subtitleBn = "প্রাপক: $recipientLabel",
            category = "TRANSFER",
            amount = amt,
            isCredit = false,
            dateEn = "14 Oct 2026, 08:30 PM",
            dateBn = "১৪ অক্টো ২০২৬, ০৮:৩০ রাত",
            referenceNumber = "OBL-" + UUID.randomUUID().toString().take(8).uppercase(),
            fee = 0.0
        )

        // Deduct from source account balance in state
        val updatedAccounts = state.accounts.map { acc ->
            if (acc.id == state.transferSourceAccount.id) {
                acc.copy(balance = (acc.balance - amt).coerceAtLeast(0.0))
            } else acc
        }

        _uiState.update {
            it.copy(
                accounts = updatedAccounts,
                selectedAccount = updatedAccounts.find { a -> a.id == state.selectedAccount.id } ?: updatedAccounts[0],
                transactions = listOf(receipt) + it.transactions,
                lastTransferReceipt = receipt,
                isTransferSuccessDialogOpen = true,
                transferAmount = "",
                transferRecipient = "",
                transferPurpose = ""
            )
        }
    }

    fun closeTransferReceipt() {
        _uiState.update { it.copy(isTransferSuccessDialogOpen = false) }
    }

    fun requestChequeBook() {
        _uiState.update {
            it.copy(feedbackMessage = "Cheque book (25 leaves) request successfully logged for Home Branch.")
        }
    }

    fun requestSolvencyCertificate() {
        _uiState.update {
            it.copy(feedbackMessage = "Bank Solvency Certificate requested. Ready for collection in 2 business days.")
        }
    }

    fun dismissFeedbackMessage() {
        _uiState.update { it.copy(feedbackMessage = null) }
    }

    fun setShowBranchLocator(show: Boolean) {
        _uiState.update { it.copy(showBranchLocatorDialog = show) }
    }
}

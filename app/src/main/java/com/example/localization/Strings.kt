package com.example.localization

import com.example.model.Language
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.Locale

object Strings {
    private val en = mapOf(
        "app_title" to "ONE Bank",
        "app_tagline" to "Always there for you",
        "showroom_badge" to "UI/UX Research & Exploration",
        "showroom_title" to "ONE Bank Design Studio",
        "showroom_subtitle" to "Six distinct next-generation mobile banking concepts combining Apple Liquid Glass elegance with authentic ONE Bank identity.",
        "explore_concepts" to "Explore 6 Concepts",
        "preview_concept" to "Preview Concept",
        "back_to_showroom" to "Showroom",
        "preferred_choice" to "Selected Choice",
        "mark_as_preferred" to "Select as Preferred",
        "preferred_badge" to "Your Preferred Design",
        "switch_concept" to "Switch Concept",
        
        // Navigation
        "nav_home" to "Home",
        "nav_accounts" to "Accounts",
        "nav_transfers" to "Transfer",
        "nav_cards" to "Cards",
        "nav_profile" to "More",
        
        // Home & Balance
        "greeting_morning" to "Good Morning,",
        "greeting_afternoon" to "Good Afternoon,",
        "greeting_evening" to "Good Evening,",
        "user_name" to "Shahriar Ahmed",
        "account_primary" to "ONE Sanchay Savings",
        "account_current" to "ONE Premium Current",
        "available_balance" to "Available Balance",
        "tap_for_balance" to "Tap for Balance",
        "total_balance" to "Total Net Worth",
        "account_num_label" to "A/C",
        "hide_balance" to "Hide",
        "show_balance" to "Show",
        "quick_actions" to "Quick Banking",
        "recent_transactions" to "Recent Transactions",
        "see_all" to "View All",
        "total_deposits" to "Term Deposits",
        "credit_available" to "Credit Limit",
        "services_header" to "Services",
        
        // Quick Action items (Verified ONE Bank commercial banking features)
        "action_fund_transfer" to "Fund Transfer",
        "action_bill_payment" to "Bill Payment",
        "action_credit_card" to "Credit Card",
        "action_mobile_topup" to "Mobile Top-up",
        "action_account_summary" to "Account Summary",
        "action_open_dps" to "DPS & Deposits",
        "action_statements" to "e-Statements",
        "action_service_requests" to "Service Requests",
        "section_quick_banking" to "ONE Bank Services",
        "section_requests" to "Requests & Network",
        "action_send" to "Fund Transfer",
        "action_cash_out" to "Cash Out",
        "action_recharge" to "Mobile Top-up",
        "action_add_money" to "Add Money",
        "action_transfer_money" to "Fund Transfer",
        "action_bills" to "Bill Payment",
        "action_emi" to "Card Payment",
        "action_merchant_pay" to "Merchant Pay",
        "action_cards" to "My Cards",
        "action_statement" to "e-Statement",
        "action_qr" to "Scan QR",
        "action_cheque" to "Cheque Book",
        "action_branch" to "Branch/ATM",
        "service_dps_fdr" to "DPS & FDR",
        "service_solvency" to "Solvency",
        "service_branches" to "Branches & ATMs",
        "service_helpline" to "16269 Helpline",
        "nav_history" to "History",
        "nav_scan" to "Scan QR",
        
        // Accounts & History
        "search_transactions" to "Search by merchant or reference...",
        "filter_all" to "All",
        "filter_in" to "Money In",
        "filter_out" to "Money Out",
        "no_transactions_found" to "No transactions found matching your criteria",
        "transaction_details" to "Transaction Receipt",
        "tx_id" to "Transaction ID",
        "tx_category" to "Category",
        "tx_status" to "Status",
        "tx_date" to "Date & Time",
        "tx_fee" to "Bank Fee",
        "tx_reference" to "Reference Number",
        "completed" to "Completed",
        "close" to "Close",
        
        // Transfer Flow
        "transfer_title" to "Fund Transfer",
        "transfer_subtitle" to "Instant, secure transfers across Bangladesh",
        "source_account" to "From Account",
        "transfer_to" to "Transfer Destination",
        "dest_obl" to "ONE Bank Account",
        "dest_bkash" to "bKash MFS",
        "dest_nagad" to "Nagad MFS",
        "dest_npsb" to "Other Bank (NPSB Instant)",
        "dest_beftn" to "Other Bank (BEFTN)",
        "recipient_hint" to "Enter account or mobile number",
        "amount_label" to "Transfer Amount (BDT)",
        "purpose_hint" to "Note or reference (e.g. Rent, Family)",
        "preset_500" to "৳ 500",
        "preset_1000" to "৳ 1,000",
        "preset_5000" to "৳ 5,000",
        "preset_10000" to "৳ 10,000",
        "send_button" to "Confirm Transfer",
        "transfer_successful" to "Transfer Completed!",
        "transfer_success_msg" to "Funds have been securely routed through ONE Bank Core Banking.",
        "simulated_notice" to "Prototype Demonstration: No real money was moved.",
        "done" to "Done",
        "share_receipt" to "Share Receipt",
        
        // Cards
        "cards_title" to "Cards & Services",
        "cards_subtitle" to "Manage your physical and digital ONE Bank cards",
        "card_platinum" to "ONE Bank VISA Platinum",
        "card_debit" to "ONE Bank Contactless EMV",
        "tap_to_flip" to "Tap card to reveal CVV & details",
        "freeze_card" to "Freeze Card",
        "unfreeze_card" to "Unfreeze Card",
        "card_active" to "Card is Active",
        "card_frozen" to "Card is Frozen for Security",
        "daily_limit" to "Daily Limit",
        "available_limit" to "Available Limit",
        "card_services" to "Card Management",
        "request_cheque" to "Request Cheque Book",
        "request_solvency" to "Solvency Certificate",
        "request_tax" to "Tax Certificate (IT-10B)",
        "cheque_requested" to "Cheque book request submitted for 25 leaves.",
        "solvency_requested" to "Solvency certificate requested for Branch collection.",
        
        // Branches
        "branches_title" to "ONE Bank Branches & ATMs",
        "call_branch" to "Call Branch",
        "has_atm" to "24/7 Cash ATM & Fast Track",
        
        // Profile & Settings
        "profile_title" to "Profile & Settings",
        "customer_id" to "Customer ID: OBL-8492041",
        "branch_assigned" to "Home Branch: Gulshan Corporate, Dhaka",
        "language_setting" to "Display Language",
        "security_settings" to "Security & Authentication",
        "biometric_login" to "Biometric Login (Face ID / Fingerprint)",
        "two_factor" to "Two-Factor Authentication (OTP on 017****241)",
        "push_notifications" to "Instant SMS & Push Notifications",
        "customer_support" to "ONE Bank 24/7 Helpline",
        "helpline_number" to "16269 or +880 2 5566 2414",
        "call_helpline" to "Call 16269",
        "about_obl" to "About ONE Bank PLC",
        "obl_info_text" to "ONE Bank PLC is an 'A' rated commercial bank incorporated in Bangladesh in 1999, operating over 110 branches nationwide with world-class digital banking.",
        "version_tag" to "ONE Bank Mobile v4.8.0 (Redesign Concept)"
    )

    private val bn = mapOf(
        "app_title" to "ওয়ান ব্যাংক",
        "app_tagline" to "সর্বদা আপনার পাশে",
        "showroom_badge" to "ইউআই/ইউএক্স গবেষণা ও ধারণা অন্বেষণ",
        "showroom_title" to "ওয়ান ব্যাংক ডিজাইন স্টুডিও",
        "showroom_subtitle" to "অ্যাপল লিকুইড গ্লাসের মার্জিত রূপ ও ওয়ান ব্যাংকের নিজস্ব পরিচয়ের সমন্বয়ে ৬টি আধুনিক মোবাইল ব্যাংকিং ডিজাইন।",
        "explore_concepts" to "৬টি ডিজাইন দেখুন",
        "preview_concept" to "প্রিভিউ দেখুন",
        "back_to_showroom" to "শোরুম",
        "preferred_choice" to "নির্বাচিত পছন্দ",
        "mark_as_preferred" to "পছন্দ হিসেবে নির্বাচন করুন",
        "preferred_badge" to "আপনার পছন্দের ডিজাইন",
        "switch_concept" to "কনসেপ্ট পরিবর্তন",
        
        // Navigation
        "nav_home" to "হোম",
        "nav_accounts" to "হিসাব",
        "nav_transfers" to "স্থানান্তর",
        "nav_cards" to "কার্ডস",
        "nav_profile" to "আরও",
        
        // Home & Balance
        "greeting_morning" to "শুভ সকাল,",
        "greeting_afternoon" to "শুভ দুপুর,",
        "greeting_evening" to "শুভ সন্ধ্যা,",
        "user_name" to "শাহরিয়ার আহমেদ",
        "account_primary" to "ওয়ান সঞ্চয় সেভিংস",
        "account_current" to "ওয়ান প্রিমিয়াম চলতি",
        "available_balance" to "ব্যবহারযোগ্য স্থিতি",
        "tap_for_balance" to "ব্যালান্স জানতে ট্যাপ করুন",
        "total_balance" to "মোট ব্যালান্স",
        "account_num_label" to "হিসাব নং",
        "hide_balance" to "লুকান",
        "show_balance" to "দেখুন",
        "quick_actions" to "দ্রুত ব্যাংকিং",
        "recent_transactions" to "সাম্প্রতিক লেনদেন",
        "see_all" to "সব দেখুন",
        "total_deposits" to "মেয়াদী আমানত",
        "credit_available" to "ক্রেডিট লিমিট",
        "services_header" to "সেবাসমূহ",
        
        // Quick Action items (Verified ONE Bank commercial banking features)
        "action_fund_transfer" to "তহবিল স্থানান্তর",
        "action_bill_payment" to "বিল পরিশোধ",
        "action_credit_card" to "ক্রেডিট কার্ড পে",
        "action_mobile_topup" to "মোবাইল রিচার্জ",
        "action_account_summary" to "হিসাব বিবরণী",
        "action_open_dps" to "ডিপিএস ও আমানত",
        "action_statements" to "ই-স্টেটমেন্ট",
        "action_service_requests" to "সেবা অনুরোধ",
        "section_quick_banking" to "ওয়ান ব্যাংক সেবাসমূহ",
        "section_requests" to "অনুরোধ ও নেটওয়ার্ক",
        "action_send" to "তহবিল স্থানান্তর",
        "action_cash_out" to "ক্যাশ আউট",
        "action_recharge" to "মোবাইল রিচার্জ",
        "action_add_money" to "টাকা গ্রহণ",
        "action_transfer_money" to "তহবিল স্থানান্তর",
        "action_bills" to "বিল পরিশোধ",
        "action_emi" to "কার্ড পেমেন্ট",
        "action_merchant_pay" to "মার্চেন্ট পে",
        "action_cards" to "আমার কার্ড",
        "action_statement" to "ই-স্টেটমেন্ট",
        "action_qr" to "কিউআর স্ক্যান",
        "action_cheque" to "চেকবই",
        "action_branch" to "শাখা/এটিএম",
        "service_dps_fdr" to "ডিপিএস ও এফডিআর",
        "service_solvency" to "সলভেন্সি",
        "service_branches" to "শাখা ও এটিএম",
        "service_helpline" to "১৬২৬৯ হেল্পলাইন",
        "nav_history" to "লেনদেন",
        "nav_scan" to "কিউআর",
        
        // Accounts & History
        "search_transactions" to "ব্যবসায়ী বা রেফারেন্স দিয়ে খুঁজুন...",
        "filter_all" to "সকল",
        "filter_in" to "জমা (ইন)",
        "filter_out" to "খরচ (আউট)",
        "no_transactions_found" to "আপনার অনুসন্ধানের সাথে কোনো লেনদেন পাওয়া যায়নি",
        "transaction_details" to "লেনদেনের রসিদ",
        "tx_id" to "ট্রানজেকশন আইডি",
        "tx_category" to "ক্যাটেগরি",
        "tx_status" to "অবস্থা",
        "tx_date" to "তারিখ ও সময়",
        "tx_fee" to "ব্যাংক ফি",
        "tx_reference" to "রেফারেন্স নম্বর",
        "completed" to "সম্পন্ন",
        "close" to "বন্ধ করুন",
        
        // Transfer Flow
        "transfer_title" to "তহবিল স্থানান্তর",
        "transfer_subtitle" to "বাংলাদেশ জুড়ে তাৎক্ষণিক ও নিরাপদ ফান্ড ট্রান্সফার",
        "source_account" to "যে হিসাব থেকে পাঠাবেন",
        "transfer_to" to "স্থানান্তরের মাধ্যম",
        "dest_obl" to "ওয়ান ব্যাংক হিসাব",
        "dest_bkash" to "বিকাশ এমএফএস",
        "dest_nagad" to "নগদ এমএফএস",
        "dest_npsb" to "অন্য ব্যাংক (এনপিএসবি ইনস্ট্যান্ট)",
        "dest_beftn" to "অন্য ব্যাংক (বিইএফটিএন)",
        "recipient_hint" to "হিসাব নম্বর বা মোবাইল নম্বর লিখুন",
        "amount_label" to "টাকার পরিমাণ (টাকা)",
        "purpose_hint" to "উদ্দেশ্য বা রেফারেন্স (যেমন: বাসা ভাড়া, পরিবার)",
        "preset_500" to "৳ ৫০০",
        "preset_1000" to "৳ ১,০০০",
        "preset_5000" to "৳ ৫,০০০",
        "preset_10000" to "৳ ১০,০০০",
        "send_button" to "স্থানান্তর নিশ্চিত করুন",
        "transfer_successful" to "স্থানান্তর সফল হয়েছে!",
        "transfer_success_msg" to "ওয়ান ব্যাংক কোর ব্যাংকিংয়ের মাধ্যমে অর্থ সফলভাবে গন্তব্যে পাঠানো হয়েছে।",
        "simulated_notice" to "প্রোটোটাইপ প্রদর্শন: কোনো প্রকৃত অর্থ স্থানান্তরিত হয়নি।",
        "done" to "সম্পন্ন",
        "share_receipt" to "রসিদ শেয়ার করুন",
        
        // Cards
        "cards_title" to "কার্ড ও সেবাসমূহ",
        "cards_subtitle" to "আপনার ফিজিক্যাল ও ডিজিটাল ওয়ান ব্যাংক কার্ড নিয়ন্ত্রণ করুন",
        "card_platinum" to "ওয়ান ব্যাংক ভিসা প্ল্যাটিনাম",
        "card_debit" to "ওয়ান ব্যাংক কন্ট্যাক্টলেস ইএমভি",
        "tap_to_flip" to "কার্ডে ট্যাপ করে সিভিভি ও বিস্তারিত দেখুন",
        "freeze_card" to "কার্ড সাময়িক বন্ধ করুন",
        "unfreeze_card" to "কার্ড চালু করুন",
        "card_active" to "কার্ডটি সক্রিয় আছে",
        "card_frozen" to "নিরাপত্তার স্বার্থে কার্ডটি সাময়িক স্থগিত",
        "daily_limit" to "দৈনিক সীমা",
        "available_limit" to "ব্যবহারযোগ্য লিমিট",
        "card_services" to "কার্ড ব্যবস্থাপনা",
        "request_cheque" to "নতুন চেকবই আবেদন",
        "request_solvency" to "সলভেন্সি সার্টিফিকেট",
        "request_tax" to "ট্যাক্স সার্টিফিকেট (আইটি-১০বি)",
        "cheque_requested" to "২৫ পাতার চেকবইয়ের আবেদন সফলভাবে জমা হয়েছে।",
        "solvency_requested" to "শাখা থেকে সংগ্রহের জন্য সলভেন্সি সার্টিফিকেট আবেদন গৃহীত হয়েছে।",
        
        // Branches
        "branches_title" to "ওয়ান ব্যাংক শাখা ও এটিএম",
        "call_branch" to "শাখা কল করুন",
        "has_atm" to "২৪/৭ এটিএম ও ফাস্ট ট্র্যাক সুবিধা",
        
        // Profile & Settings
        "profile_title" to "প্রোফাইল ও সেটিংস",
        "customer_id" to "গ্রাহক নম্বর: OBL-৮৪৯২০৪১",
        "branch_assigned" to "মূল শাখা: গুলশান কর্পোরেট, ঢাকা",
        "language_setting" to "অ্যাপের ভাষা",
        "security_settings" to "নিরাপত্তা ও যাচাইকরণ",
        "biometric_login" to "বায়োমেট্রিক লগইন (ফেস আইডি / ফিঙ্গারপ্রিন্ট)",
        "two_factor" to "দ্বিমুখী যাচাইকরণ (০১৭****২৪১ নম্বরে ওটিপি)",
        "push_notifications" to "তাৎক্ষণিক এসএমএস ও নোটিফিকেশন",
        "customer_support" to "ওয়ান ব্যাংক ২৪/৭ হেল্পলাইন",
        "helpline_number" to "১৬২৬৯ অথবা +৮৮০ ২ ৫৫৬৬ ২৪১৪",
        "call_helpline" to "১৬২৬৯ নম্বরে কল করুন",
        "about_obl" to "ওয়ান ব্যাংক পিএলসি সম্পর্কে",
        "obl_info_text" to "ওয়ান ব্যাংক পিএলসি ১৯৯৯ সালে বাংলাদেশে প্রতিষ্ঠিত একটি প্রথম সারির 'এ' রেটেড বাণিজ্যিক ব্যাংক, যার রয়েছে দেশব্যাপী ১১০টিরও বেশি আধুনিক শাখা ও ডিজিটাল ব্যাংকিং নেটওয়ার্ক।",
        "version_tag" to "ওয়ান ব্যাংক মোবাইল ৪.৮.০ (রিডিজাইন কনসেপ্ট)"
    )

    fun tr(key: String, lang: Language): String {
        return if (lang == Language.BN) {
            bn[key] ?: en[key] ?: key
        } else {
            en[key] ?: key
        }
    }

    private val bengaliDigits = charArrayOf('০', '১', '২', '৩', '৪', '৫', '৬', '৭', '৮', '৯')

    fun toBengaliDigits(input: String): String {
        val sb = java.lang.StringBuilder()
        for (ch in input) {
            if (ch in '0'..'9') {
                sb.append(bengaliDigits[ch - '0'])
            } else {
                sb.append(ch)
            }
        }
        return sb.toString()
    }

    fun formatCurrency(amount: Double, lang: Language): String {
        val formatter = DecimalFormat("#,##,##0.00", DecimalFormatSymbols(Locale.US))
        val formatted = formatter.format(amount)
        return if (lang == Language.BN) {
            "৳ " + toBengaliDigits(formatted)
        } else {
            "৳ $formatted"
        }
    }

    fun formatDate(dateStr: String, lang: Language): String {
        return if (lang == Language.BN) {
            toBengaliDigits(dateStr)
        } else {
            dateStr
        }
    }
}

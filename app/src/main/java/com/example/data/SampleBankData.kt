package com.example.data

import com.example.model.BankAccount
import com.example.model.BankCard
import com.example.model.BranchLocation
import com.example.model.Transaction

object SampleBankData {
    val accounts = listOf(
        BankAccount(
            id = "acc_1",
            nameEn = "ONE Sanchay Savings",
            nameBn = "ওয়ান সঞ্চয় সেভিংস",
            typeEn = "Savings Account",
            typeBn = "সঞ্চয়ী হিসাব",
            accountNumber = "018 2049 8192 01",
            balance = 248750.50,
            isPrimary = true
        ),
        BankAccount(
            id = "acc_2",
            nameEn = "ONE Premium Current",
            nameBn = "ওয়ান প্রিমিয়াম চলতি",
            typeEn = "Current Account",
            typeBn = "চলতি হিসাব",
            accountNumber = "018 2049 8192 02",
            balance = 85420.00,
            isPrimary = false
        ),
        BankAccount(
            id = "acc_3",
            nameEn = "ONE Kotipoti Scheme (DPS)",
            nameBn = "ওয়ান কোটিপতি স্কিম (ডিপিএস)",
            typeEn = "Deposit Pension",
            typeBn = "ডিপোজিট পেনশন",
            accountNumber = "DPS-992014-04",
            balance = 520000.00,
            isPrimary = false
        )
    )

    val cards = listOf(
        BankCard(
            id = "card_1",
            cardHolder = "SHAHRIAR AHMED",
            cardNumberMasked = "4214 •••• •••• 9018",
            cardType = "VISA Platinum",
            expiryDate = "08/29",
            cvv = "482",
            isFrozen = false,
            dailyLimit = 100000.0,
            availableCredit = 214500.00
        ),
        BankCard(
            id = "card_2",
            cardHolder = "SHAHRIAR AHMED",
            cardNumberMasked = "5399 •••• •••• 3145",
            cardType = "EMV Contactless Debit",
            expiryDate = "11/28",
            cvv = "194",
            isFrozen = false,
            dailyLimit = 50000.0,
            availableCredit = 248750.50
        )
    )

    val transactions = listOf(
        Transaction(
            id = "TX-902184",
            titleEn = "Salary Deposit",
            titleBn = "বেতন জমা",
            subtitleEn = "Brain Station 23 / Tech Corp",
            subtitleBn = "ব্রেন স্টেশন ২৩ / টেক কর্প",
            category = "SALARY",
            amount = 120000.00,
            isCredit = true,
            dateEn = "14 Oct 2026, 09:30 AM",
            dateBn = "১৪ অক্টো ২০২৬, ০৯:৩০ সকাল",
            referenceNumber = "OBL-SAL-883921",
            fee = 0.0
        ),
        Transaction(
            id = "TX-902183",
            titleEn = "bKash Transfer",
            titleBn = "বিকাশ স্থানান্তর",
            subtitleEn = "To: Tanvir Hasan (01712-345678)",
            subtitleBn = "প্রাপক: তানভীর হাসান (০১৭১২-৩৪৫৬৭৮)",
            category = "TRANSFER",
            amount = 5000.00,
            isCredit = false,
            dateEn = "13 Oct 2026, 07:15 PM",
            dateBn = "১৩ অক্টো ২০২৬, ০৭:১৫ সন্ধ্যা",
            referenceNumber = "BKS-TRX-449102",
            fee = 0.0
        ),
        Transaction(
            id = "TX-902182",
            titleEn = "DESCO Electricity Bill",
            titleBn = "ডেসকো বিদ্যুৎ বিল",
            subtitleEn = "Meter # 49201948 - Paid",
            subtitleBn = "মিটার নং ৪৯২০১৯৪৮ - পরিশোধিত",
            category = "BILL",
            amount = 3450.00,
            isCredit = false,
            dateEn = "12 Oct 2026, 02:40 PM",
            dateBn = "১২ অক্টো ২০২৬, ০২:৪০ দুপুর",
            referenceNumber = "DSC-BIL-902148",
            fee = 0.0
        ),
        Transaction(
            id = "TX-902181",
            titleEn = "Grameenphone Top-Up",
            titleBn = "গ্রামীণফোন রিচার্জ",
            subtitleEn = "Prepaid # 01711-987654",
            subtitleBn = "প্রিপেইড নং ০১৭১১-৯৮৭৬৫৪",
            category = "RECHARGE",
            amount = 500.00,
            isCredit = false,
            dateEn = "11 Oct 2026, 11:10 AM",
            dateBn = "১১ অক্টো ২০২৬, ১১:১০ সকাল",
            referenceNumber = "GP-REC-119402",
            fee = 0.0
        ),
        Transaction(
            id = "TX-902180",
            titleEn = "Shwapno Superstore",
            titleBn = "স্বপ্ন সুপারস্টোর",
            subtitleEn = "POS Card Swipe - Gulshan 2",
            subtitleBn = "পিওএস কার্ড সোয়াইপ - গুলশান ২",
            category = "CARD",
            amount = 4820.00,
            isCredit = false,
            dateEn = "10 Oct 2026, 08:22 PM",
            dateBn = "১০ অক্টো ২০২৬, ০৮:২২ রাত",
            referenceNumber = "POS-SHW-550192",
            fee = 0.0
        ),
        Transaction(
            id = "TX-902179",
            titleEn = "ATM Cash Withdrawal",
            titleBn = "এটিএম টাকা উত্তোলন",
            subtitleEn = "ONE Bank FastTrack, Banani",
            subtitleBn = "ওয়ান ব্যাংক ফাস্টট্র্যাক, বনানী",
            category = "ATM",
            amount = 10000.00,
            isCredit = false,
            dateEn = "09 Oct 2026, 04:15 PM",
            dateBn = "০৯ অক্টো ২০২৬, ০৪:১৫ বিকাল",
            referenceNumber = "ATM-WTH-882019",
            fee = 0.0
        ),
        Transaction(
            id = "TX-902178",
            titleEn = "Quarterly Profit Credit",
            titleBn = "ত্রৈমাসিক মুনাফা জমা",
            subtitleEn = "ONE Sanchay Interest Earned",
            subtitleBn = "ওয়ান সঞ্চয় অর্জিত সুদ/মুনাফা",
            category = "INTEREST",
            amount = 1840.50,
            isCredit = true,
            dateEn = "01 Oct 2026, 12:00 AM",
            dateBn = "০১ অক্টো ২০২৬, ১২:০০ রাত",
            referenceNumber = "OBL-INT-771920",
            fee = 0.0
        ),
        Transaction(
            id = "TX-902177",
            titleEn = "DPDC Utility Payment",
            titleBn = "ডিপিডিসি বিল পরিশোধ",
            subtitleEn = "Bill # 882049102",
            subtitleBn = "বিল নং ৮৮২০৪৯১০২",
            category = "BILL",
            amount = 2150.00,
            isCredit = false,
            dateEn = "28 Sep 2026, 03:05 PM",
            dateBn = "২৮ সেপ্টে ২০২৬, ০৩:০৫ দুপুর",
            referenceNumber = "DPDC-881920-01",
            fee = 0.0
        )
    )

    val branches = listOf(
        BranchLocation(
            nameEn = "Gulshan Corporate Branch",
            nameBn = "গুলশান কর্পোরেট শাখা",
            addressEn = "HRC Building, 46 Kawran Bazar / Gulshan Avenue, Dhaka",
            addressBn = "এইচআরসি ভবন, ৪৬ কাওরান বাজার / গুলশান এভিনিউ, ঢাকা",
            phone = "+880 2 988 2414",
            hasAtm = true
        ),
        BranchLocation(
            nameEn = "Principal Branch, Motijheel",
            nameBn = "প্রধান শাখা, মতিঝিল",
            addressEn = "45 Dilkusha C/A, Motijheel, Dhaka-1000",
            addressBn = "৪৫ দিলকুশা বা/এ, মতিঝিল, ঢাকা-১০০০",
            phone = "+880 2 956 8140",
            hasAtm = true
        ),
        BranchLocation(
            nameEn = "Banani Branch",
            nameBn = "বনানী শাখা",
            addressEn = "House 67, Block D, Road 11, Banani, Dhaka",
            addressBn = "বাড়ি ৬৭, ব্লক ডি, রোড ১১, বনানী, ঢাকা",
            phone = "+880 2 982 1045",
            hasAtm = true
        ),
        BranchLocation(
            nameEn = "Dhanmondi Branch",
            nameBn = "ধানমন্ডি শাখা",
            addressEn = "House 4, Road 27 (Old), Dhanmondi, Dhaka",
            addressBn = "বাড়ি ৪, রোড ২৭ (পুরাতন), ধানমন্ডি, ঢাকা",
            phone = "+880 2 913 2244",
            hasAtm = true
        ),
        BranchLocation(
            nameEn = "Agrabad Commercial Branch, Ctg",
            nameBn = "আগ্রাবাদ বাণিজ্যিক শাখা, চট্টগ্রাম",
            addressEn = "World Trade Centre, 102 Agrabad C/A, Chittagong",
            addressBn = "ওয়ার্ল্ড ট্রেড সেন্টার, ১০২ আগ্রাবাদ বা/এ, চট্টগ্রাম",
            phone = "+880 31 716 521",
            hasAtm = true
        ),
        BranchLocation(
            nameEn = "Sylhet Main Branch",
            nameBn = "সিলেট প্রধান শাখা",
            addressEn = "City Centre, Zindabazar, Sylhet",
            addressBn = "সিটি সেন্টার, জিন্দাবাজার, সিলেট",
            phone = "+880 821 723 119",
            hasAtm = true
        )
    )
}

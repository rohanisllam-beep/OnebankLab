package com.example.model

import androidx.compose.ui.graphics.Color

enum class Language {
    EN, BN
}

enum class ConceptId(
    val number: String,
    val title: String,
    val titleBn: String,
    val subtitle: String,
    val subtitleBn: String,
    val philosophy: String,
    val philosophyBn: String,
    val primaryColor: Color,
    val accentColor: Color,
    val surfaceColor: Color,
    val badge: String
) {
    LIQUID_GLASS(
        number = "01",
        title = "Liquid Glass Signature",
        titleBn = "লিকুইড গ্লাস সিগনেচার",
        subtitle = "Apple-inspired Translucent Minimalism",
        subtitleBn = "অ্যাপল-অনুপ্রাণিত স্বচ্ছ মিনিমালিজম",
        philosophy = "Pearl-white foundation, icy-blue gradients, delicate edge reflections, and soft floating glass surfaces engineered for clarity.",
        philosophyBn = "মুক্তা-সাদা ব্যাকগ্রাউন্ড, বরফ-নীল গ্রেডিয়েন্ট, নরম কাঁচের স্তর ও ভাসমান স্বচ্ছ ব্যালান্স ইন্টারফেস।",
        primaryColor = Color(0xFF0F3B82),
        accentColor = Color(0xFF38BDF8),
        surfaceColor = Color(0xFFF1F6FD),
        badge = "Signature"
    ),
    QUIET_LUXURY(
        number = "02",
        title = "Quiet Luxury",
        titleBn = "কোয়ায়েট লাক্সারি",
        subtitle = "Timeless Prestige & Understated Elegance",
        subtitleBn = "টাইমলেস প্রেস্টিজ ও পরিশীলিত মার্জিতভাব",
        philosophy = "Warm ivory, soft champagne accents, deep charcoal typography, generous whitespace, and pure financial focus without decorative noise.",
        philosophyBn = "আইভরি সাদা, নরম শ্যাম্পেন সোনালী আভা, গভীর চারকোল টাইপোগ্রাফি এবং কোনো বাড়তি কোলাহলহীন অভিজাত ব্যাংকিং।",
        primaryColor = Color(0xFF1F2421),
        accentColor = Color(0xFFC5A059),
        surfaceColor = Color(0xFFFCFBF8),
        badge = "Prestige"
    ),
    AURORA_FLOW(
        number = "03",
        title = "Aurora Flow",
        titleBn = "অরোরা ফ্লো",
        subtitle = "Fluid Luminous Atmosphere",
        subtitleBn = "তরল ও উজ্জ্বল বায়ুমণ্ডলীয় ডিজাইন",
        philosophy = "Bright white base with mint, aqua, and pale cyan gradients. Fluid micro-interactions and luminous balance waves that breathe softly.",
        philosophyBn = "উজ্জ্বল সাদা জমিন, অ্যাকুয়া ও মিন্ট সবুজ রঙের মসৃণ ছটা এবং গতিশীল অরোরা আলোয় ভাসমান ব্যাংকিং।",
        primaryColor = Color(0xFF006B7A),
        accentColor = Color(0xFF10B981),
        surfaceColor = Color(0xFFF0FDF4),
        badge = "Dynamic"
    ),
    PRECISION_FINANCIAL(
        number = "04",
        title = "Precision Financial",
        titleBn = "প্রিসিশন ফিন্যান্সিয়াল",
        subtitle = "Structured Professional Architecture",
        subtitleBn = "পরিকল্পিত ও তথ্যবহুল প্রফেশনাল কাঠামো",
        philosophy = "Crisp white panels, deep cobalt accents, cool steel slate, modular bento grids, and high-efficiency scanning for frequent bankers.",
        philosophyBn = "সুনির্দিষ্ট গ্রিড, গভীর কোবাল্ট ব্লু, মডুলার বেন্তো কার্ড এবং দ্রুত লেনদেনের জন্য উচ্চ ক্ষমতাসম্পন্ন ড্যাশবোর্ড।",
        primaryColor = Color(0xFF0047AB),
        accentColor = Color(0xFF2563EB),
        surfaceColor = Color(0xFFF8FAFC),
        badge = "Analytical"
    ),
    ORGANIC_CALM(
        number = "05",
        title = "Organic Calm",
        titleBn = "অর্গানিক কাম",
        subtitle = "Tactile Rounded Softness",
        subtitleBn = "কোমল গোলাকার ও স্পর্শযোগ্য প্রশান্তি",
        philosophy = "Cloud white, gentle sage, pale sand, and soothing olive notes. Deep pebble curves, comforting rhythm, and tactile confidence.",
        philosophyBn = "মেঘের মতো সাদা, সেজ গ্রিন, বালুকাময় স্পর্শ ও আরামদায়ক কার্ভড সারফেস যা ব্যবহারকারীকে শান্ত অভিজ্ঞতা দেয়।",
        primaryColor = Color(0xFF2D4A3E),
        accentColor = Color(0xFF84A98C),
        surfaceColor = Color(0xFFF7F9F6),
        badge = "Harmonious"
    ),
    CRYSTAL_EDGE(
        number = "06",
        title = "Crystal Edge",
        titleBn = "ক্রিস্টাল এজ",
        subtitle = "Architectural Prismatic Geometry",
        subtitleBn = "স্থাপত্যশৈলী ও জ্যামিতিক ক্রিস্টাল গ্লাস",
        philosophy = "Porcelain foundation, silver-blue facets, subtle violet luminescence, crisp architectural edges, and precision laser aesthetics.",
        philosophyBn = "চীনামাটির সাদা স্তর, রূপালী-নীল ও ভায়োলেট প্রিজমীয় বর্ডার এবং ভবিষ্যতের জ্যামিতিক কাঁচের ফিনিশ।",
        primaryColor = Color(0xFF3730A3),
        accentColor = Color(0xFF6366F1),
        surfaceColor = Color(0xFFF5F3FF),
        badge = "Futuristic"
    )
}

enum class ScreenRoute {
    HOME, ACCOUNTS, TRANSFERS, CARDS, PROFILE
}

data class BankAccount(
    val id: String,
    val nameEn: String,
    val nameBn: String,
    val typeEn: String,
    val typeBn: String,
    val accountNumber: String,
    val balance: Double,
    val isPrimary: Boolean = false
)

data class Transaction(
    val id: String,
    val titleEn: String,
    val titleBn: String,
    val subtitleEn: String,
    val subtitleBn: String,
    val category: String, // "RECHARGE", "TRANSFER", "SALARY", "BILL", "CARD", "ATM", "INTEREST"
    val amount: Double,
    val isCredit: Boolean,
    val dateEn: String,
    val dateBn: String,
    val statusEn: String = "Completed",
    val statusBn: String = "সম্পন্ন",
    val referenceNumber: String,
    val fee: Double = 0.0
)

data class BankCard(
    val id: String,
    val cardHolder: String,
    val cardNumberMasked: String,
    val cardType: String, // "VISA Platinum", "EMV Debit"
    val expiryDate: String,
    val cvv: String,
    val isFrozen: Boolean = false,
    val dailyLimit: Double,
    val availableCredit: Double
)

data class BranchLocation(
    val nameEn: String,
    val nameBn: String,
    val addressEn: String,
    val addressBn: String,
    val phone: String,
    val hasAtm: Boolean = true
)

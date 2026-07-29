package com.example.model

data class ServiceItem(
    val id: String,
    val title: String,
    val iconName: String,
    val description: String,
    val startingPrice: String,
    val features: List<String>,
    val badge: String? = null
)

data class ReviewItem(
    val name: String,
    val locality: String,
    val rating: Int,
    val comment: String,
    val dateText: String,
    val serviceUsed: String
)

data class StatItem(
    val label: String,
    val valueNumber: Int,
    val suffix: String,
    val description: String
)

object PestData {
    val PROPERTY_TYPES = listOf(
        "Apartment",
        "Independent House / Villa",
        "Shop / Retail",
        "Office",
        "Warehouse / Industrial"
    )

    val PROPERTY_SIZES = listOf(
        "Under 500 sq ft",
        "500 – 1,000 sq ft",
        "1,000 – 2,000 sq ft",
        "2,000+ sq ft"
    )

    val PEST_TYPES = listOf(
        "General Pest Control",
        "Cockroach Control",
        "Termite Control",
        "Bed Bug Treatment",
        "Rodent Control",
        "Mosquito Control",
        "Wood Borer Treatment",
        "Hornet & Wasp Extermination",
        "Spider Extermination",
        "Bee Extermination",
        "Annual Maintenance Contract (AMC)"
    )

    val FREQUENCIES = listOf(
        "One-Time Treatment",
        "Quarterly AMC (15% Off)",
        "Annual AMC (Save 20% Best Value)"
    )

    fun calculateEstimate(
        propertyType: String,
        propertySize: String,
        pestType: String,
        frequency: String
    ): Pair<Int, Int> {
        var baseMin = when (pestType) {
            "General Pest Control" -> 1199
            "Cockroach Control" -> 999
            "Termite Control" -> 3499
            "Bed Bug Treatment" -> 1799
            "Rodent Control" -> 1499
            "Mosquito Control" -> 1399
            "Wood Borer Treatment" -> 2199
            "Hornet & Wasp Extermination" -> 1199
            "Spider Extermination" -> 999
            "Bee Extermination" -> 1499
            "Annual Maintenance Contract (AMC)" -> 3199
            else -> 1200
        }

        var baseMax = when (pestType) {
            "General Pest Control" -> 2499
            "Cockroach Control" -> 1799
            "Termite Control" -> 7499
            "Bed Bug Treatment" -> 3799
            "Rodent Control" -> 3199
            "Mosquito Control" -> 2799
            "Wood Borer Treatment" -> 4799
            "Hornet & Wasp Extermination" -> 2799
            "Spider Extermination" -> 2199
            "Bee Extermination" -> 3499
            "Annual Maintenance Contract (AMC)" -> 7199
            else -> 2500
        }

        // Size Multiplier
        val sizeMultiplier = when (propertySize) {
            "Under 500 sq ft" -> 1.0
            "500 – 1,000 sq ft" -> 1.35
            "1,000 – 2,000 sq ft" -> 1.8
            "2,000+ sq ft" -> 2.4
            else -> 1.0
        }

        // Type Multiplier
        val typeMultiplier = when (propertyType) {
            "Apartment" -> 1.0
            "Independent House / Villa" -> 1.25
            "Shop / Retail" -> 1.15
            "Office" -> 1.3
            "Warehouse / Industrial" -> 1.6
            else -> 1.0
        }

        // Frequency Discount
        val freqDiscount = when {
            frequency.contains("20%") -> 0.80
            frequency.contains("15%") -> 0.85
            else -> 1.0
        }

        val minPrice = (baseMin * sizeMultiplier * typeMultiplier * freqDiscount).toInt()
        val maxPrice = (baseMax * sizeMultiplier * typeMultiplier * freqDiscount).toInt()

        // Round to nearest 50
        val roundedMin = (minPrice / 50) * 50
        val roundedMax = (maxPrice / 50) * 50

        return Pair(roundedMin, roundedMax)
    }

    val SERVICES_LIST = listOf(
        ServiceItem(
            id = "general",
            title = "General Pest Control",
            iconName = "BugReport",
            description = "Complete odorless chemical spray targeting ants, silverfish, lizards & crawling pests with long-lasting protection.",
            startingPrice = "₹1,199",
            features = listOf("Odorless & Non-Toxic", "Lizard & Ant Barrier", "Safe for Children & Pets"),
            badge = "Popular"
        ),
        ServiceItem(
            id = "cockroach",
            title = "Cockroach Control",
            iconName = "ShieldWithBug",
            description = "Advanced herbal gel baiting & spot spray system targeting cockroach nests without kitchen disruption.",
            startingPrice = "₹999",
            features = listOf("Herbal Gel Technology", "No Kitchen Emptying Needed", "Single Session Nest Eradication"),
            badge = "Best Value"
        ),
        ServiceItem(
            id = "termite",
            title = "Termite Control",
            iconName = "Handyman",
            description = "Drill-Fill-Seal (DFS) anti-termite treatment creating a subterranean barrier protecting wood & foundation.",
            startingPrice = "₹3,499",
            features = listOf("Drill-Fill-Seal Method", "Up to 5 Years Warranty", "Odourless Termiticide"),
            badge = "5-Yr Warranty"
        ),
        ServiceItem(
            id = "bedbug",
            title = "Bed Bug Treatment",
            iconName = "Hotel",
            description = "2-step specialized chemical & heat application eliminating bed bug eggs, nymphs, and adult bugs completely.",
            startingPrice = "₹1,799",
            features = listOf("Dual Session Guarantee", "Egg & Larvae Destruction", "Deep Mattress Sanitization")
        ),
        ServiceItem(
            id = "rodent",
            title = "Rodent Control",
            iconName = "CatchingPokemon",
            description = "Strategic tracking powder, baiting stations & entry point sealing preventing rat & mouse infestations.",
            startingPrice = "₹1,499",
            features = listOf("Glue Boards & Bait Boxes", "Entry Blockage Audit", "Zero Decomposition Smell")
        ),
        ServiceItem(
            id = "mosquito",
            title = "Mosquito Control",
            iconName = "WavingHand",
            description = "Thermal fogging and anti-larval treatment for garden, balcony, and drainage areas preventing Dengue & Malaria.",
            startingPrice = "₹1,399",
            features = listOf("Outdoor Thermal Fogging", "Larvicidal Spray", "Dengue & Vector Shield")
        ),
        ServiceItem(
            id = "woodborer",
            title = "Wood Borer Treatment",
            iconName = "SquareFoot",
            description = "Specialized oil-based syringe injection targeting powder-post beetles damaging expensive wooden furniture.",
            startingPrice = "₹2,199",
            features = listOf("Direct Syringe Injection", "Furniture Preservative Coating", "Deep Timber Protection")
        ),
        ServiceItem(
            id = "wasp",
            title = "Hornet & Wasp Extermination",
            iconName = "Shield",
            description = "Safe high-reach removal of yellow-jacket & hornet nests with protective gear and residual deterrents.",
            startingPrice = "₹1,199",
            features = listOf("Eco Protective Gear Removal", "High Wall & Roof Nest Extraction", "Deterrent Barrier")
        ),
        ServiceItem(
            id = "spider",
            title = "Spider Extermination",
            iconName = "Polymer",
            description = "Cobweb removal & fine spray barrier preventing venomous spider nests around corners, eaves & false ceilings.",
            startingPrice = "₹999",
            features = listOf("Cobweb Eradication", "Ceiling Corner Coating", "Residual Barrier Protection")
        ),
        ServiceItem(
            id = "bee",
            title = "Bee Extermination & Removal",
            iconName = "Eco",
            description = "Humane bee hive extraction & safe relocation from balconies, trees, and residential terraces in Solapur.",
            startingPrice = "₹1,499",
            features = listOf("Non-Lethal Extraction Option", "Terrace & Window Safe Removal", "Immediate Relief")
        ),
        ServiceItem(
            id = "amc",
            title = "Annual Maintenance Contract (AMC)",
            iconName = "VerifiedUser",
            description = "Year-round pest protection with scheduled quarterly audits, emergency call-outs, and zero extra charges.",
            startingPrice = "₹3,199 / yr",
            features = listOf("3 to 4 Inspections / Year", "Free Unscheduled Emergency Calls", "Priority WhatsApp Booking"),
            badge = "Save 20%"
        )
    )

    val REVIEWS = listOf(
        ReviewItem(
            name = "Ramesh Kulkarni",
            locality = "Jule Solapur, Solapur",
            rating = 5,
            comment = "Delta Force handled a severe cockroach problem in our hotel kitchen. The herbal gel treatment was clean, effective, and had no harsh odor. Highly recommended in Solapur!",
            dateText = "2 days ago",
            serviceUsed = "Cockroach Gel Treatment"
        ),
        ReviewItem(
            name = "Pooja Patil",
            locality = "Vijayapur Road, Solapur",
            rating = 5,
            comment = "We booked termite drill-fill-seal for our double bungalow. They arrived promptly at 10 AM with full safety gear. Very polite team and excellent 5-year warranty document.",
            dateText = "1 week ago",
            serviceUsed = "Termite Control (5-Yr Warranty)"
        ),
        ReviewItem(
            name = "Anil Deshmukh",
            locality = "Hotgi Road, Solapur",
            rating = 5,
            comment = "Their instant cost calculator in the app gave us an exact estimate before booking. No hidden charges! Solapur's most transparent pest control service.",
            dateText = "2 weeks ago",
            serviceUsed = "Annual AMC Plan"
        ),
        ReviewItem(
            name = "Sunita Jadhav",
            locality = "Sanmati Nagar, Solapur",
            rating = 5,
            comment = "Had a dangerous hornet nest under my balcony eave. Delta Force removed it safely within 45 minutes of calling! Outstanding rapid response.",
            dateText = "3 weeks ago",
            serviceUsed = "Hornet & Wasp Extermination"
        )
    )

    val PAIN_POINTS = listOf(
        Pair("I've tried treatments before but pests keep coming back", "We provide structured Annual Maintenance Contracts (AMC) with guaranteed long-term protection and free re-treatment visits."),
        Pair("I'm worried about toxic chemicals around my kids & pets", "Exclusively Government-approved, 100% odorless, child & pet-safe herbal formulations applied with medical precision."),
        Pair("I don't know how much pest control will actually cost", "Transparent instant cost calculator right here in our app for upfront estimates with zero hidden fees."),
        Pair("I don't trust unverified technicians in my home", "Every Delta Force technician is background-verified, professionally trained, uniformed, and carries official ID."),
        Pair("Other companies take forever or don't show up on time", "Scheduled appointment confirmations with automated reminders and guaranteed punctual on-site visits.")
    )

    val PROCESS_STEPS = listOf(
        Triple("1", "Instant Estimate", "Select your pest type & property size in our app calculator for instant pricing."),
        Triple("2", "Free On-Site Inspection", "Our certified technician conducts a thorough audit of all entry points."),
        Triple("3", "Targeted Treatment", "We execute odorless, Govt-approved eco-friendly treatments tailored for Solapur homes."),
        Triple("4", "Warranty & Follow-up", "Enjoy peace of mind with written service warranty and free follow-up checks.")
    )
}

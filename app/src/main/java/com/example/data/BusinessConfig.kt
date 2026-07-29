package com.example.data

object BusinessConfig {
    const val PHONE = "9067257872"
    const val WHATSAPP = "918329931123"
    const val BUSINESS_NAME = "Delta Force Pest Control and Sanitization Cleaning Services"
    const val SHORT_NAME = "Delta Force Pest Control"
    const val ADDRESS = "4 A, Sanmati Nagar Vijayapur Road, Jule Solapur Rd, Solapur, Maharashtra 413004"
    const val SERVICE_AREA = "Solapur, Maharashtra"
    const val YEARS_IN_BUSINESS = 10
    const val CLIENTS_SERVED = 10000
    const val GOOGLE_MAPS_QUERY = "Delta+Force+Pest+Control+Solapur"
    
    fun getTelUri(): String = "tel:$PHONE"
    
    fun getWhatsAppUri(message: String): String {
        val encodedMessage = java.net.URLEncoder.encode(message, "UTF-8")
        return "https://wa.me/$WHATSAPP?text=$encodedMessage"
    }
}

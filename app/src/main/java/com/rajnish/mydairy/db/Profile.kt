package com.rajnish.mydairy.db

data class Profile(
    var name: String = "",
    var phoneNum: String = "",
    var address: String = "",
    var email: String = "",
    var password: String = "",
    var familyDetails: FamilyDetails = FamilyDetails()
)
data class FamilyDetails(
    var fatherName: String = "",
    var motherName: String = "",
    var siblings: List<String> = listOf()
)

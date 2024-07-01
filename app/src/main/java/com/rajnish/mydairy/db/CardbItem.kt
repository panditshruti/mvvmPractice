package com.rajnish.mydairy.db

data class CardbItem(
    val color: String,
    val engine: String,
    val features: List<String>,
    val fuelType: String,
    val horsepower: Int,
    val id: Int,
    val image: String,
    val make: String,
    val mileage: Int,
    val model: String,
    val owners: Int,
    val price: Int,
    val transmission: String,
    val year: Int
)
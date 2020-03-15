package `in`.indilabz.student_union.model

data class Discount(
    val unique_id: String,
    val student_id: String,
    val shop_id : String,
    val discount : String,
    val allowed_discount : String
)
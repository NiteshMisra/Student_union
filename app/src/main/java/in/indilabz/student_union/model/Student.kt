package `in`.indilabz.student_union.model

data class
Student(
    val id: Int,
    val full_name: String,
    val phone : String,
    val email : String,
    val password : String,
    val dob : String,
    val course: String,
    val course_year: String,
    val college: String,
    val father_name: String,
    val current_address: String,
    val permanent_address: String,
    val created_at : String,
    val updated_at : String
)
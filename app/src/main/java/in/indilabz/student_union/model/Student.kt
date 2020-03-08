package `in`.indilabz.student_union.model

data class
Student(
    val user_id: String,
    val username: String,
    val name: String,
    val user_img: String,
    val student_id: String,
    val student_p_id: String,
    val dob: String,
    val gender: String,
    val contact_number: String,
    val course: String,
    val college: String,
    val year: String,
    val father_name: String,
    val current_address: String,
    val paramanent_address: String,
    val login_status: Boolean
)
package `in`.indilabz.student_union

import `in`.indilabz.student_union.rest.API
import `in`.indilabz.student_union.rest.RetrofitInstance
import android.app.Application
import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.request.RequestOptions
import com.google.gson.GsonBuilder
import java.security.NoSuchAlgorithmException
import java.security.SecureRandom
import java.util.*

class INDIMaster : Application(){

    var context: Context? = null


    override fun onCreate() {
        super.onCreate()
        instance = this@INDIMaster
        //CrashReporter.initialize(this)
        //ACRA.init(this);
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
        context = this.baseContext

    }

    companion object {

        @get:Synchronized
        var instance: INDIMaster? = null
            private set

        fun applicationContext() : INDIMaster {
            return instance!!.applicationContext as INDIMaster
        }

        private val newApi = RetrofitInstance.newInstance().create(API::class.java)

        private val session = RetrofitInstance.session().create(API::class.java)

        private var linearLayoutManager: LinearLayoutManager? = null
        private var gridLayoutManager: GridLayoutManager? = null

        private val gsonBuilder = GsonBuilder()
        val gson = gsonBuilder.create()
        private val requestOptions: RequestOptions? = null

        private val otpRandom = Random(System.currentTimeMillis())
        lateinit var otp: String
        //var loading:DialogLoading = DialogLoading()

        fun generateOTP() {

            val generatedToken = StringBuilder()

            try {
                val number = SecureRandom.getInstance("SHA1PRNG")
                // Generate 20 integers 0..20
                for (i in 0..5) {
                    generatedToken.append(number.nextInt(9))
                }

                otp = generatedToken.toString()

            } catch (e: NoSuchAlgorithmException) {
                e.printStackTrace()

                otp = "886617"
            }
        }

        fun newApi(): API {
            return newApi
        }

        fun session(): API {
            return session
        }

        // linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        val verticalLayoutManager: LinearLayoutManager
            get() {

                linearLayoutManager = LinearLayoutManager(
                    applicationContext()
                )

                return linearLayoutManager!!
            }

        val horizontalLayoutManager: LinearLayoutManager
            get() {

                linearLayoutManager = LinearLayoutManager(
                    applicationContext()
                )
                linearLayoutManager!!.orientation = LinearLayoutManager.HORIZONTAL

                return linearLayoutManager!!
            }

        fun getGridLayoutManager(span: Int): GridLayoutManager {

            gridLayoutManager = GridLayoutManager(
                applicationContext(), span)

            return gridLayoutManager!!
        }
    }
}
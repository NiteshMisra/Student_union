package `in`.indilabz.student_union.activity

import `in`.indilabz.student_union.R
import `in`.indilabz.student_union.fragment.HomeFragment
import `in`.indilabz.yorneeds.utils.INDIPreferences
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.FragmentManager
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var fragmentManager: FragmentManager
    private lateinit var drawerLayout : DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        drawerLayout = findViewById(R.id.drawer_layout)
        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)

        val toggle = ActionBarDrawerToggle(this,drawerLayout,toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close)

        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navigationView.setCheckedItem(R.id.nav_home)
        if(savedInstanceState == null){
            fragmentManager = supportFragmentManager
            fragmentManager.beginTransaction()
                .replace(R.id.fragment,HomeFragment())
                .commit()
        }

    }

    override fun onNavigationItemSelected(p0: MenuItem): Boolean {

        when(p0.itemId){
            R.id.nav_home -> {
                fragmentManager = supportFragmentManager
                        fragmentManager.beginTransaction()
                            .replace(R.id.fragment,HomeFragment())
                            .commit()
            }

            R.id.nav_profile-> {
                startActivity(Intent(this,ProfileActivity::class.java))
            }

            R.id.nav_terms -> {
                startActivity(Intent(this,TermsAndCondition::class.java))
            }

            R.id.nav_history -> {
                startActivity(Intent(this,HistoryActivity::class.java))
            }

            R.id.nav_logout -> {
                INDIPreferences.session(false)
                startActivity(Intent(this,LoginActivity::class.java))
                finish()
            }

            R.id.nav_support -> {
                startActivity(Intent(this,HelpDesk::class.java))
            }

        }

        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.noti_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.nav_notify -> {
                startActivity(Intent(this,Notification::class.java))
                return true
            }

        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {

        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }

    }

}

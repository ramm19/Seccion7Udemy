package com.example.seccion7udemy.activities


import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.alejandrolora.mylibrary.ToolbarActivity
import com.example.seccion7udemy.R
import com.example.seccion7udemy.fragments.ArrivalsFragment
import com.example.seccion7udemy.fragments.DeparturesFragment
import com.example.seccion7udemy.fragments.HomeFragment
import com.example.seccion7udemy.toast
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : ToolbarActivity(), NavigationView.OnNavigationItemSelectedListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toolbarToLoad(toolbar as Toolbar)

        setNavDrawer()
        setUserHeaderIntormation()

        if (savedInstanceState == null){
            fragmentTransaction(HomeFragment())
            navView.menu.getItem(0).isChecked = true
        }
    }

    private fun setNavDrawer(){
        val toggle = ActionBarDrawerToggle(this, drawerLayout, _toolbar,
            R.string.open_drawer,
            R.string.close_drawer
        )
        toggle.isDrawerIndicatorEnabled = true
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navView.setNavigationItemSelectedListener(this)
    }

    private fun fragmentTransaction(fragment: Fragment){
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .commit()
    }

    private fun loadFragmentById(id: Int){
        when(id){
            R.id.nav_home -> fragmentTransaction(HomeFragment())
            R.id.nav_departures -> fragmentTransaction(DeparturesFragment())
            R.id.nav_arrivals -> fragmentTransaction(ArrivalsFragment())
        }
    }

    private fun showMessageNavItemSelectedById(id: Int){
        when(id){
            R.id.nav_profile -> toast("Profile")
            R.id.nav_settings -> toast("Settings")
        }
    }

    private fun setUserHeaderIntormation(){

        //solo para aprender como se hace, porque si es algo fijo, hacerlo directamente en el layout
        val name = navView.getHeaderView(0).findViewById<TextView>(R.id.textViewName)
        val email = navView.getHeaderView(0).findViewById<TextView>(R.id.textViewEmail)

        name?.let { name.text = getString(R.string.user_name) }
        email?.let { email.text = getString(R.string.user_gmail) }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        showMessageNavItemSelectedById(item.itemId)
        loadFragmentById(item.itemId)
        drawerLayout.closeDrawer(GravityCompat.START)

        return true
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

}

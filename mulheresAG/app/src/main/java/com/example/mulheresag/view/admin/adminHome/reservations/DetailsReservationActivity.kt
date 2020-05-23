package com.example.mulheresag.view.admin.adminHome.reservations

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.mulheresag.R
import com.example.mulheresag.view.homePage.HomeFragment

class DetailsReservationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_details_reservation)
        val intExtra = intent.getIntExtra("id", -1)
        val bundle = Bundle()
        bundle.putInt("id", intExtra)
        val homeFragment = HomeFragment()
        homeFragment.arguments = bundle
        openFragment(homeFragment)

    }

    private fun openFragment(fragment: Fragment?) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.containerAdminHome, fragment!!)
            .commit()
        //transaction.addToBackStack(null)
    }
}

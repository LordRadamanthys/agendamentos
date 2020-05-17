package com.example.mulheresag.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import androidx.fragment.app.Fragment
import com.example.mulheresag.view.Chat.ListUsersChatFragment
import com.example.mulheresag.R
import com.example.mulheresag.infra.App
import com.example.mulheresag.view.DialogExamples.Companion.DialogListener
import com.example.mulheresag.view.admin.AdminHomeFragment
import com.example.mulheresag.view.agendamento.AgendamentosFragment
import com.example.mulheresag.view.homePage.HomeFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_default.*


class DefaultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_default)

        bottomNavigation()
    }

    private fun bottomNavigation() {
        bottom_navigation.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener { item ->

            when (item.itemId) {
                R.id.navigation_home -> {
                    if (App.isAdmin) {
                        openFragment(AdminHomeFragment())
                    } else {
                        openFragment(HomeFragment())
                    }

                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_agendamento -> {
                    openFragment(AgendamentosFragment())
//                    Toast.makeText(baseContext, "token", Toast.LENGTH_SHORT).show()
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_chat -> {
                    openFragment(ListUsersChatFragment())
//                    Toast.makeText(baseContext, "token", Toast.LENGTH_SHORT).show()
                    return@OnNavigationItemSelectedListener true
                }

                R.id.navigation_servicos -> {

                    openFragment(ListUsersChatFragment())
//                    Toast.makeText(baseContext, "token", Toast.LENGTH_SHORT).show()
                    return@OnNavigationItemSelectedListener true
                }

                R.id.navigation_sair -> {
                   DialogExamples.showDialogExit(this)
//                    Toast.makeText(baseContext, "token", Toast.LENGTH_SHORT).show()
                    return@OnNavigationItemSelectedListener true
                }


            }
            return@OnNavigationItemSelectedListener false
        })
    }

    private fun openFragment(fragment: Fragment?) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment!!)
            .commit()
        //transaction.addToBackStack(null)
    }

}

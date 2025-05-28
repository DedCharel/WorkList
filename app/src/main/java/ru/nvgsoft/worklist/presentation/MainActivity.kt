package ru.nvgsoft.worklist.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import ru.nvgsoft.worklist.R

class MainActivity : AppCompatActivity() {




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val fragment = WorkListFragment.newInstance()
       // launchFragment(fragment)

    }

    fun launchFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_container,fragment)
            .commit()
    }



}
package com.example.todo_app_hw.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.todo_app_hw.R
import com.example.todo_app_hw.adapter.MovieAdapter
import com.example.todo_app_hw.database.NoteDatabase
import com.example.todo_app_hw.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        NoteDatabase.getInstance(applicationContext)

    }

//    override fun onResume() {
//        super.onResume()
//
//        val navController = findNavController(R.id.fragment_host)
//        NavigationUI.setupActionBarWithNavController(this, navController)
//    }

    override fun onSupportNavigateUp(): Boolean {
        findNavController(R.id.fragment_host).navigateUp()
        return super.onSupportNavigateUp()
    }

}
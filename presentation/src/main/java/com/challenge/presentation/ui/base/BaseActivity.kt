package com.challenge.presentation.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import dagger.android.support.DaggerAppCompatActivity

/**
 * Created by Razi on 2019/10/09.
 */
abstract class BaseActivity : DaggerAppCompatActivity() {

    private val navController: NavController by lazy {
        findNavController(getNavControllerId())
    }

    override fun onSupportNavigateUp() = navController.navigateUp()

    abstract fun getNavControllerId(): Int

}
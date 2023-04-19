package com.example.notions

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.notions.contract.CustomAction
import com.example.notions.contract.HasCustomAction
import com.example.notions.contract.Navigator
import com.example.notions.databinding.ActivityMainBinding
import com.example.notions.views.NotionEditFragment
import com.example.notions.views.NotionInfoFragment
import com.example.notions.views.NotionsListFragment

class MainActivity : AppCompatActivity(),Navigator{

    lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        //setSupportActionBar(binding.toolbar)
        setContentView(binding.root)
        if(savedInstanceState==null){
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragmentContainerView, NotionsListFragment(),null)
                .setReorderingAllowed(true)
                .commit()
        }
        supportFragmentManager.registerFragmentLifecycleCallbacks(fragmentListener, false)
    }

    override fun onDestroy() {
        super.onDestroy()
        supportFragmentManager.unregisterFragmentLifecycleCallbacks(fragmentListener)
    }

    private val currentFragment: Fragment
        get() = supportFragmentManager.findFragmentById(R.id.fragmentContainerView)!!

    private val fragmentListener = object : FragmentManager.FragmentLifecycleCallbacks() {
        override fun onFragmentViewCreated(fm: FragmentManager, f: Fragment, v: View, savedInstanceState: Bundle?) {
            super.onFragmentViewCreated(fm, f, v, savedInstanceState)
            updateUi()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return true
    }

    private fun launchFragment(fragment: Fragment){
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainerView, fragment)
            .addToBackStack(null)
            .commit()
    }

    override fun goBack() {
        onBackPressedDispatcher.onBackPressed()
    }

    fun goToMainFragment(){
        supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }

    override fun onSupportNavigateUp(): Boolean {
        goToMainFragment()
        return true
    }

    override fun showNotionsListFragment() {
        launchFragment(NotionsListFragment())
    }

    override fun showNotionInfoFragment() {
        launchFragment(NotionInfoFragment())
    }

    override fun showNotionEditFragment() {
        launchFragment(NotionEditFragment())
    }

    fun updateUi(){
        if(supportFragmentManager.backStackEntryCount>0){
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.setDisplayShowHomeEnabled(true)
        }
        else{
            supportActionBar?.setDisplayHomeAsUpEnabled(false)
            supportActionBar?.setDisplayShowHomeEnabled(false)
        }
    }

//    private fun updateUi() {
//        val fragment = currentFragment
//
//        if (supportFragmentManager.backStackEntryCount > 0) {
//            supportActionBar?.setDisplayHomeAsUpEnabled(true)
//            supportActionBar?.setDisplayShowHomeEnabled(true)
//        } else {
//            supportActionBar?.setDisplayHomeAsUpEnabled(false)
//            supportActionBar?.setDisplayShowHomeEnabled(false)
//        }
//
//        if (fragment is HasCustomAction) {
//            for(action in fragment.getCustomAction()){
//                createCustomToolbarAction(action)
//            }
//        } else {
//            binding.toolbar.menu.clear()
//        }
//    }
//
//    private fun createCustomToolbarAction(action: CustomAction) {
//        binding.toolbar.menu.clear() // clearing old action if it exists before assigning a new one
//
//        val iconDrawable = DrawableCompat.wrap(ContextCompat.getDrawable(this, action.iconRes)!!)
//        iconDrawable.setTint(Color.WHITE)
//
//        val menuItem = binding.toolbar.menu.add(action.textRes)
//        menuItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM)
//        menuItem.icon = iconDrawable
//        menuItem.setOnMenuItemClickListener {
//            action.onCustomAction.run()
//            return@setOnMenuItemClickListener true
//        }
//    }
}
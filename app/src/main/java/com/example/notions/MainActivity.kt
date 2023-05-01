package com.example.notions

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.notions.contract.Navigator
import com.example.notions.databinding.ActivityMainBinding
import com.example.notions.notions.views.NotionEditFragment
import com.example.notions.notions.views.NotionInfoFragment
import com.example.notions.notions.views.NotionsListFragment
import com.example.notions.tasks.view.TaskEditFragment
import com.example.notions.tasks.view.TaskInfoFragment
import com.example.notions.tasks.view.TasksListFragment
import com.example.notions.trackers.views.TrackerEditFragment
import com.example.notions.trackers.views.TrackerInfoFragment
import com.example.notions.trackers.views.TrackersListFragment

class MainActivity : AppCompatActivity(),Navigator{

    lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        //setSupportActionBar(binding.toolbar)
        supportActionBar!!.title= resources.getString(R.string.my_notions)
        setContentView(binding.root)
        if(savedInstanceState==null){
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragmentContainerView, NotionsListFragment(),"home")
                .setReorderingAllowed(true)
                .commit()
        }
        supportFragmentManager.registerFragmentLifecycleCallbacks(fragmentListener, false)
    }

    override fun onDestroy() {
        super.onDestroy()
        supportFragmentManager.unregisterFragmentLifecycleCallbacks(fragmentListener)
    }

    private val fragmentListener = object : FragmentManager.FragmentLifecycleCallbacks() {
        override fun onFragmentViewCreated(fm: FragmentManager, f: Fragment, v: View, savedInstanceState: Bundle?) {
            super.onFragmentViewCreated(fm, f, v, savedInstanceState)
            updateUi()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return true
    }

    private fun launchSideFragment(fragment: Fragment){
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainerView, fragment)
            .addToBackStack(null)
            .commit()
    }

    private fun launchMainFragment(fragment: Fragment){
        supportFragmentManager
            .beginTransaction()
            .remove(supportFragmentManager.findFragmentByTag("home")!!)
            .commit()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainerView, fragment,"home")
            .setReorderingAllowed(true)
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
        launchMainFragment(NotionsListFragment())
        supportActionBar!!.title= resources.getString(R.string.my_notions)
    }

    override fun showNotionInfoFragment(notionId:Int) {
        launchSideFragment(NotionInfoFragment.newInstance(notionId))
    }

    override fun showNotionEditFragment(notionId: Int) {
        launchSideFragment(NotionEditFragment.newInstance(notionId))
    }

    override fun showTrackersListFragment() {
        launchMainFragment(TrackersListFragment())
        supportActionBar!!.title= resources.getString(R.string.my_trackers)
    }

    override fun showTrackerInfoFragment() {
        launchSideFragment(TrackerInfoFragment())
    }

    override fun showTrackerEditFragment() {
        launchSideFragment(TrackerEditFragment())
    }

    override fun showTasksListFragment() {
        launchMainFragment(TasksListFragment())
        supportActionBar!!.title=resources.getString(R.string.my_tasks)
    }

    override fun showTaskInfoFragment() {
        launchSideFragment(TaskInfoFragment())
    }

    override fun showTaskEditFragment() {
        launchSideFragment(TaskEditFragment())
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
}
package com.example.notions.trackers.views

import android.os.Bundle
import android.view.*
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.example.notions.R
import com.example.notions.contract.ListFragment
import com.example.notions.contract.navigator
import com.example.notions.databinding.FragmentTrackerListBinding

class TrackersListFragment : Fragment(),ListFragment{


    private lateinit var binding:FragmentTrackerListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
//        arguments?.let {
//            param1 = it.getString(ARG_PARAM1)
//            param2 = it.getString(ARG_PARAM2)
//        }
    }

    @Deprecated("Deprecated in Java")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.list_menu,menu)
    }

    @Deprecated("Deprecated in Java")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.addButton -> {
                navigator().showTrackerInfoFragment()
                true
            }
            R.id.menuButton ->{
                showDrawerLayout()
                true
            }
            else -> false
        }
    }

    override fun setUpRecyclerView() {
        TODO("Not yet implemented")
    }

    override fun setUpMenu(){
        binding.apply {
            navigationView.setNavigationItemSelectedListener {
                when(it.itemId){
                    R.id.itemNotions -> {
                        closeDrawerLayout()
                        navigator().showNotionsListFragment()
                        true
                    }
                    R.id.itemTasks -> {
                        closeDrawerLayout()
                        navigator().showTasksListFragment()
                        true
                    }
                    R.id.itemTrackers -> {
                        closeDrawerLayout()
                        navigator().showTrackersListFragment()
                        true
                    }
                    R.id.itemFolders-> {
                        closeDrawerLayout()
                        true
                    }
                    else -> false
                }
            }
        }
    }

    override fun showDrawerLayout(){
        binding.drawerLayout.openDrawer(GravityCompat.START)
    }

    override fun closeDrawerLayout(){
        binding.drawerLayout.closeDrawer(GravityCompat.START)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentTrackerListBinding.inflate(inflater,container,false)
        setUpMenu()
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        setUpMenu()
    }

//    companion object {
//
//        @JvmStatic
//        fun newInstance(param1: String, param2: String) =
//            TrackersListFragment().apply {
//                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
//                }
//            }
//    }
}
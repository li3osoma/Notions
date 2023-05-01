package com.example.notions.notions.views

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.*
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Dao
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.notions.R
import com.example.notions.contract.ListFragment
import com.example.notions.contract.navigator
import com.example.notions.notions.dao.NotionDao
import com.example.notions.database.NotionsDatabase
import com.example.notions.databinding.FragmentNotionsListBinding
import com.example.notions.notions.service.NotionClickListener
import com.example.notions.notions.service.NotionsListAdapter
import kotlin.concurrent.thread


class NotionsListFragment : Fragment(), NotionClickListener, ListFragment {

    lateinit var binding:FragmentNotionsListBinding
    lateinit var adapter: NotionsListAdapter
    lateinit var db: RoomDatabase
    lateinit var notionDao: NotionDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        db= Room.databaseBuilder(requireContext(), NotionsDatabase::class.java, "notions1").build()
        notionDao= (db as NotionsDatabase).notionDao()
        setUpRecyclerView()
        //setUpMenu()
        setHasOptionsMenu(true)
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
                navigator().showNotionEditFragment(-1)
                true
            }
            R.id.menuButton ->{
                showDrawerLayout()
                true
            }
            else -> false
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
    ): View {
        binding = FragmentNotionsListBinding.inflate(inflater,container,false)
        setUpMenu()
        setUpRecyclerView()
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        setUpMenu()
        setUpRecyclerView()
    }

    override fun setUpRecyclerView(){

        val uiHandler= Handler(Looper.getMainLooper())
        thread{
            adapter= NotionsListAdapter(this)
            adapter.notions=notionDao.getNotionsList()
        }
        uiHandler.post {
            binding.recyclerView.adapter=adapter
            val layoutManager= LinearLayoutManager(context)
            binding.recyclerView.layoutManager=layoutManager
        }
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

    override fun openChosen(notionId: Int) {
        navigator().showNotionInfoFragment(notionId)
    }

}


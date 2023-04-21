package com.example.notions.views

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.notions.R
import com.example.notions.contract.navigator
import com.example.notions.dao.NotionDao
import com.example.notions.database.NotionDatabase
import com.example.notions.databinding.FragmentNotionsListBinding
import com.example.notions.service.NotionsListAdapter
import kotlin.concurrent.thread


class NotionsListFragment : Fragment(){

    lateinit var binding:FragmentNotionsListBinding
    lateinit var adapter:NotionsListAdapter
    lateinit var db: RoomDatabase
    lateinit var notionDao: NotionDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        db= Room.databaseBuilder(requireContext(), NotionDatabase::class.java, "notions").build()
        notionDao= (db as NotionDatabase).notionDao()
        setUpRecyclerView()
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
                navigator().showNotionEditFragment()
                true
            }
            else -> false
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNotionsListBinding.inflate(inflater,container,false)
        //setUpRecyclerView()
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        setUpRecyclerView()
    }


    private fun setUpRecyclerView(){

        val uiHandler= Handler(Looper.getMainLooper())
        thread{
            adapter=NotionsListAdapter()
            adapter.notions=notionDao.getNotionsList()
        }
        uiHandler.post {
            binding.recyclerView.adapter=adapter
            val layoutManager= LinearLayoutManager(context)
            binding.recyclerView.layoutManager=layoutManager
        }
    }
}
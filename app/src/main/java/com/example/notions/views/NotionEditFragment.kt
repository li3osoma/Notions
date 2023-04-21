package com.example.notions.views

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.notions.R
import com.example.notions.contract.navigator
import com.example.notions.dao.NotionDao
import com.example.notions.database.NotionDatabase
import com.example.notions.databinding.FragmentNotionEditBinding
import com.example.notions.entity.Notion
import kotlin.concurrent.thread

class NotionEditFragment : Fragment(){

    lateinit var binding:FragmentNotionEditBinding
    lateinit var db: RoomDatabase
    lateinit var notionDao: NotionDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        db= Room.databaseBuilder(requireContext(), NotionDatabase::class.java, "notions").build()
        notionDao= (db as NotionDatabase).notionDao()
        setHasOptionsMenu(true)
    }

    @Deprecated("Deprecated in Java")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.edit_menu,menu)
    }

    @Deprecated("Deprecated in Java")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.saveButton -> {
                saveNotion()
                Toast.makeText(requireContext(),R.string.save_notification,Toast.LENGTH_SHORT).show()
                navigator().goBack()
                true
            }
            else -> false
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding= FragmentNotionEditBinding.inflate(inflater,container,false)
        return binding.root
    }

    private fun saveNotion(){
        val title = binding.titleEditText.text.toString()
        val text=binding.textEditText.text.toString()

        thread{
            notionDao.insert(Notion(title, text))
        }
    }
}
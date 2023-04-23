package com.example.notions.views

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
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
import java.text.SimpleDateFormat
import java.util.*
import kotlin.concurrent.thread

class NotionEditFragment : Fragment(){

    private var notionId:Int = 0
    lateinit var binding:FragmentNotionEditBinding
    lateinit var db: RoomDatabase
    lateinit var notionDao: NotionDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        db= Room.databaseBuilder(requireContext(), NotionDatabase::class.java, "notions1").build()
        notionDao= (db as NotionDatabase).notionDao()
        setHasOptionsMenu(true)
        arguments?.let{
            notionId=it.getInt(NotionEditFragment.NOTION_ID)
        }
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
        if(notionId!=-1) setUpUi()
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        if(notionId!=-1) setUpUi()
    }

    private fun saveNotion(){
        val title = binding.titleEditText.text.toString()
        val text=binding.textEditText.text.toString()
        val currentDate: Date = Date()
        val dateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
        val dateText = dateFormat.format(currentDate)

        if(notionId==-1) {
            thread {
                notionDao.insert(Notion(title, text, dateText))
            }
        }
        else{
            thread {
                notionDao.updateById(notionId,title,text, dateText)
            }
        }
    }

    private fun setUpUi(){
        val uiHandler= Handler(Looper.getMainLooper())
        var notion:Notion=Notion("","")
        thread{
            notion=notionDao.getNotionById(notionId)
        }
        uiHandler.post {
            binding.titleEditText.text=notion.title.toEditable()
            binding.textEditText.text=notion.text.toEditable()
        }
    }
    fun String.toEditable(): Editable =  Editable.Factory.getInstance().newEditable(this)
    companion object {
        @JvmStatic
        private var NOTION_ID="NOTION_ID"

        @JvmStatic
        fun newInstance(notionId:Int):NotionEditFragment{
            val arguments = Bundle()
            arguments.putInt(NOTION_ID, notionId)
            val fragment = NotionEditFragment()
            fragment.arguments = arguments
            return fragment
        }
    }
}
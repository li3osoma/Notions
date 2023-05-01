package com.example.notions.notions.views

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
import com.example.notions.contract.EditFragment
import com.example.notions.contract.navigator
import com.example.notions.notions.dao.NotionDao
import com.example.notions.database.NotionsDatabase
import com.example.notions.databinding.FragmentNotionEditBinding
import com.example.notions.notions.entity.Notion
import java.text.SimpleDateFormat
import java.util.*
import kotlin.concurrent.thread

class NotionEditFragment : Fragment(),EditFragment{

    private var notionId:Int = 0
    lateinit var binding:FragmentNotionEditBinding
    lateinit var db: RoomDatabase
    lateinit var notionDao: NotionDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        db= Room.databaseBuilder(requireContext(), NotionsDatabase::class.java, "notions1").build()
        notionDao= (db as NotionsDatabase).notionDao()
        setHasOptionsMenu(true)
        arguments?.let{
            notionId=it.getInt(NOTION_ID)
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
                saveItem()
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

    override fun setUpUi(){
        val uiHandler= Handler(Looper.getMainLooper())
        var notion: Notion = Notion("","")
        if(notionId == -1){
            binding.titleEditText.hint = resources.getString(R.string.notion_title).toEditable()
            binding.textEditText.hint= resources.getString(R.string.notion_text).toEditable()
        }
        thread{
            notion=notionDao.getNotionById(notionId)
        }
        uiHandler.post {
            binding.titleEditText.text=notion.title.toEditable()
            binding.textEditText.text=notion.text.toEditable()
        }
    }

    override fun saveItem(){
        var title = binding.titleEditText.text.toString()
        var text=binding.textEditText.text.toString()

        if(title == "" && text == "") {
            Toast.makeText(requireContext(), R.string.empty_notion, Toast.LENGTH_SHORT).show()
        }
        else {
            if (title == "") title = "<no title>"
            if(text == "") text = "<no text>"


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
            Toast.makeText(requireContext(),R.string.save_notion_notification,Toast.LENGTH_SHORT).show()
            //CRUTCH
            navigator().goBack()
        }
    }

    fun String.toEditable(): Editable =  Editable.Factory.getInstance().newEditable(this)
    companion object {
        @JvmStatic
        private var NOTION_ID="NOTION_ID"

        @JvmStatic
        fun newInstance(notionId:Int): NotionEditFragment {
            val arguments = Bundle()
            arguments.putInt(NOTION_ID, notionId)
            val fragment = NotionEditFragment()
            fragment.arguments = arguments
            return fragment
        }
    }
}
package com.example.notions.views

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.notions.R
import com.example.notions.contract.navigator
import com.example.notions.dao.NotionDao
import com.example.notions.database.NotionDatabase
import com.example.notions.databinding.FragmentNotionInfoBinding
import com.example.notions.entity.Notion
import kotlin.concurrent.thread

class NotionInfoFragment : Fragment(){

    private var notionId:Int = 0
    lateinit var binding: FragmentNotionInfoBinding
    lateinit var db: RoomDatabase
    lateinit var notionDao: NotionDao
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        db= Room.databaseBuilder(requireContext(), NotionDatabase::class.java, "notions").build()
        notionDao= (db as NotionDatabase).notionDao()
        setHasOptionsMenu(true)
        arguments?.let{
            notionId=it.getInt(NOTION_ID)
        }
    }


    @Deprecated("Deprecated in Java")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.info_menu,menu)
    }

    @Deprecated("Deprecated in Java")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.editButton -> {
                navigator().showNotionEditFragment()
                true
            }
            R.id.deleteButton -> {
                Toast.makeText(requireContext(),R.string.delete_notification,Toast.LENGTH_SHORT).show()
                navigator().goBack()
                true
            }
            else -> false
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentNotionInfoBinding.inflate(inflater,container,false)
        setUpUi()
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        setUpUi()
    }

//    override fun getCustomAction(): List<CustomAction> {
//        return listOf(CustomAction(
//            iconRes = R.drawable.ic_edit,
//            textRes = R.string.edit,
//            onCustomAction = Runnable {
//                navigator().showNotionsListFragment()
//            }
//        ), CustomAction(
//            iconRes = R.drawable.ic_delete,
//            textRes = R.string.delete,
//            onCustomAction = Runnable {
//                navigator().showNotionsListFragment()
//                Toast.makeText(requireContext(),R.string.delete_notification,Toast.LENGTH_SHORT).show()
//            })
//        )
//    }

    private fun setUpUi(){
        val uiHandler= Handler(Looper.getMainLooper())
        var notion:Notion=Notion("","")
        thread{
            notion=notionDao.getNotionById(notionId)
        }
        uiHandler.post {
            binding.titleTextView.text=notion.title
            binding.textTextView.text=notion.text
        }
    }


    companion object {
        @JvmStatic
        private var NOTION_ID="NOTION_ID"

        @JvmStatic
        fun newInstance(notionId:Int):NotionInfoFragment{
            val arguments = Bundle()
            arguments.putInt(NOTION_ID, notionId)
            val fragment = NotionInfoFragment()
            fragment.arguments = arguments
            return fragment
        }
    }
}
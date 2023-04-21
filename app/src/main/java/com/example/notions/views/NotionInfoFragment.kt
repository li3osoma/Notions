package com.example.notions.views

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.notions.R
import com.example.notions.contract.navigator

class NotionInfoFragment : Fragment(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notion_info, container, false)
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


//    companion object {
//        @JvmStatic
//        fun newInstance(){}
//    }
}
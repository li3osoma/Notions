package com.example.notions.tasks.view

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.notions.R
import com.example.notions.contract.EditFragment
import com.example.notions.contract.navigator

class TaskEditFragment : Fragment(),EditFragment {


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
        inflater.inflate(R.menu.edit_menu,menu)
    }

    @Deprecated("Deprecated in Java")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.saveButton -> {
                //saveNotion()
                Toast.makeText(requireContext(),R.string.save_task_notification, Toast.LENGTH_SHORT).show()
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
        return inflater.inflate(R.layout.fragment_task_edit, container, false)
    }

    override fun setUpUi() {
        TODO("Not yet implemented")
    }

    override fun saveItem() {
        TODO("Not yet implemented")
    }

//    companion object {
//
//        @JvmStatic
//        fun newInstance(param1: String, param2: String) =
//            TaskEditFragment().apply {
//                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
//                }
//            }
//    }
}
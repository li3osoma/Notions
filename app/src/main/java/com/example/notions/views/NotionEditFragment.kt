package com.example.notions.views

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.notions.R
import com.example.notions.contract.CustomAction
import com.example.notions.contract.HasCustomAction
import com.example.notions.contract.navigator
import com.example.notions.databinding.FragmentNotionEditBinding

class NotionEditFragment : Fragment(){

    lateinit var binding:FragmentNotionEditBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
    ): View? {
        binding= FragmentNotionEditBinding.inflate(inflater,container,false)
        return binding.root
    }

//    override fun getCustomAction(): List<CustomAction> {
//        return listOf(CustomAction(
//            iconRes = R.drawable.ic_check,
//            textRes = R.string.save,
//            onCustomAction = Runnable {
//                navigator().showNotionsListFragment()
//                Toast.makeText(requireContext(),R.string.save_notification,Toast.LENGTH_SHORT).show()
//            }
//        ))
//    }


//    companion object {
//        @JvmStatic
//        fun newInstance(param1: String, param2: String) =
//            NotionEditFragment().apply {
//            }
//    }
}
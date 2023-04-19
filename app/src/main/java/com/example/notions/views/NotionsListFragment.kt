package com.example.notions.views

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.example.notions.R
import com.example.notions.contract.CustomAction
import com.example.notions.contract.HasCustomAction
import com.example.notions.contract.navigator
import com.example.notions.databinding.FragmentNotionsListBinding


class NotionsListFragment : Fragment(){
    lateinit var binding:FragmentNotionsListBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
    ): View? {
        binding = FragmentNotionsListBinding.inflate(inflater,container,false)
        return binding.root
    }

//    override fun getCustomAction(): List<CustomAction> {
//        return listOf(CustomAction(
//            iconRes = R.drawable.ic_add,
//            textRes = R.string.add,
//            onCustomAction = Runnable {
//                navigator().showNotionInfoFragment()
//            }
//        ))
//    }


//    companion object {
//        @JvmStatic
//        fun newInstance(param1: String, param2: String) =
//    }
}
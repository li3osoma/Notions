package com.example.notions.contract
import androidx.fragment.app.Fragment

fun Fragment.navigator():Navigator{
    return requireActivity() as Navigator
}

interface Navigator {
    fun goBack()

    fun showNotionsListFragment()
    fun showNotionInfoFragment(notionId:Int)
    fun showNotionEditFragment(notionId: Int)

    fun showTrackersListFragment()
    fun showTrackerInfoFragment()
    fun showTrackerEditFragment()

    fun showTasksListFragment()
    fun showTaskInfoFragment()
    fun showTaskEditFragment()
}
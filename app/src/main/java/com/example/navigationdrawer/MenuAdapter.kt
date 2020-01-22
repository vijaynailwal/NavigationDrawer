package com.example.navigationdrawer

import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import java.util.ArrayList
import nl.psdcompany.duonavigationdrawer.views.DuoOptionView

internal class MenuAdapter(options: ArrayList<String>) : BaseAdapter() {
    private var mOptions = ArrayList<String>()
    private val mOptionViews = ArrayList<DuoOptionView>()

    init {
        mOptions = options
    }

    override fun getCount(): Int {
        return mOptions.size
    }

    override fun getItem(position: Int): Any {
        return mOptions[position]
    }

    fun setViewSelected(position: Int, selected: Boolean) {
        for (i in mOptionViews.indices) {
            if (i == position) {
                mOptionViews[i].isSelected = selected
            } else {
                mOptionViews[i].isSelected = !selected
            }
        }
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val option = mOptions[position]
        val optionView: DuoOptionView
        if (convertView == null) {
            optionView = DuoOptionView(parent.context)
        } else {
            optionView = convertView as DuoOptionView
        }
        optionView.bind(option, null, null)
        mOptionViews.add(optionView)
        return optionView
    }
}

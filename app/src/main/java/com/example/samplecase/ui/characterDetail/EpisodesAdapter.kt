package com.example.samplecase.ui.characterDetail

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.TextView
import com.example.samplecase.R

class EpisodesAdapter (var context: Context, var episodeList: List<String>) : BaseExpandableListAdapter () {


    override fun getGroupCount(): Int {
        return 1
    }

    override fun getChildrenCount(groupPosition: Int): Int {
        return episodeList.size
    }

    override fun getGroup(groupPosition: Int): String {
        return "Episodes"
    }

    override fun getChild(groupPosition: Int, childPosition: Int): Any {
        return episodeList[childPosition]
    }

    override fun getGroupId(groupPosition: Int): Long {
        return groupPosition.toLong()
    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
        return childPosition.toLong()
    }

    override fun hasStableIds(): Boolean {
        return false
    }

    override fun getGroupView(
        groupPosition: Int,
        isExpanded: Boolean,
        convertView: View?,
        parent: ViewGroup?
    ): View? {
    var convertView = convertView
        if(convertView == null){
            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = inflater.inflate(R.layout.expanded_list_item,null)

        }
        val episodeName: TextView? = convertView?.findViewById(R.id.expandedListItem)

        if (episodeName != null) {
            episodeName.text = "Episodes"
        }
        return convertView
    }

    override fun getChildView(
        groupPosition: Int,
        childPosition: Int,
        isLastChild: Boolean,
        convertView: View?,
        parent: ViewGroup?
    ): View? {
        var convertView = convertView
        if(convertView == null){
            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = inflater.inflate(R.layout.expanded_list_group,null)

        }
        val episodeName: TextView? = convertView?.findViewById(R.id.expandedListGroupItem)
        if (episodeName != null) {
            episodeName.text = episodeList.get(childPosition)
        }
        return convertView
    }

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean {
        return false
    }

}
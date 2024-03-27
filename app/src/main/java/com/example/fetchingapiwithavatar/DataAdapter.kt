package com.example.fetchingapiwithavatar

import android.content.Context
import android.content.SearchRecentSuggestionsProvider
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import org.w3c.dom.Text

class DataAdapter(private val context: Context, private val itemClickListener: (List<String>) -> Unit): BaseAdapter() {

    private val data = mutableListOf<List<String>>()
    override fun getCount(): Int = data.size
    override fun getItem(position: Int): Any = data[position]
    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertview: View?, parent: ViewGroup?): View {
        var view = convertview ?: LayoutInflater.from(context).inflate(R.layout.activity_data,parent,false)
        val holder = ViewHolder(view)

        val rowdata = data[position]

        holder.firstname.text = rowdata[0]
        holder.email.text = rowdata[2]


        view.setOnClickListener {
            itemClickListener(rowdata)
        }

        return view
    }

    fun addData(rowdata: List<String>) {
        data.add(rowdata)
        notifyDataSetChanged()
    }

    fun ClearData() {
        data.clear()
        notifyDataSetChanged()
    }

    private class ViewHolder(view: View) {
        val firstname: TextView = view.findViewById(R.id.firsNametext)
        val email: TextView = view.findViewById(R.id.emailtext)

        init {
            firstname.isFocusable = true
            email.isFocusable = true
        }
    }


}
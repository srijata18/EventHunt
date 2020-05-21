package com.example.eventhunt.dashboard.views

import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.BitmapEncoder
import com.example.eventhunt.R
import kotlinx.android.synthetic.main.row_item_thingstodo.view.*
import java.util.*
import kotlin.collections.ArrayList

class ThingsToDoAdapter(private val list : ArrayList<String>?,
                        private val itemSelected: ItemSelected,
                        private val context : Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemCount(): Int {
        return list?.size?:0
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        Log.i("tag::","Onbindviewcalled")
        if (holder is ChildAdapterViewHolder){
            holder.apply {
                list?.let {
                    search_Type.text = it[position]
                    holder.search_Type.setOnClickListener {
                        list?.let {it1-> itemSelected.onItemSelected(it1[position])}
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_item_thingstodo, parent, false)
        return ChildAdapterViewHolder(
            view
        )
    }

    fun updateAdapter(updatedList : ArrayList<String>?){
        updatedList?.let {
            list?.clear()
            list?.addAll(updatedList)
            notifyDataSetChanged() }
    }

    class ChildAdapterViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        val search_Type = item.tv_thingsToDo
    }

}

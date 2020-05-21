package com.example.eventhunt.dashboard.views

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.animation.GlideAnimation
import com.bumptech.glide.request.target.SimpleTarget
import com.example.eventhunt.R
import com.example.eventhunt.dashboard.dataModel.FeaturedModel
import kotlinx.android.synthetic.main.row_item_events_horizontal.view.*


class ScrollAdapter(private val list : ArrayList<FeaturedModel>?,
                    private val itemSelected: ItemSelected,
                    private val context : Context,
                    private val type : Type = Type.Vertical) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val HORIZONTAL_VIEW_TYPE = 0
    private val VERTICAL_VIEW_TYPE = 1

    override fun getItemCount(): Int {
        return list?.size?:0
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ChildAdapterViewHolder){
            holder.apply {
                list?.let {
                    tv_header.text = if(!it[position].name.isNullOrBlank()) it[position].name
                    else it[position].action_button_text
                    tv_event_type.text = it[position].category_id.name
                    tv_date_time.text = it[position]?.venue_date_string
                    tv_price.text = "Rs ${it[position]?.price_display_string}"
                    tv_watch_option.text = it[position]?.venue_name
                    val view : View
                    val url = if (type == Type.Horizontal) {
                        vertical_layout?.visibility = View.GONE
                        tv_header_vertical?.visibility = View.GONE
                        horizontal_layout?.visibility = View.VISIBLE
                        view = iv_event
                        "${it[position].vertical_cover_image }"
                    }
                    else {
                        vertical_layout?.visibility = View.VISIBLE
                        tv_header_vertical?.visibility = View.VISIBLE
                        horizontal_layout?.visibility = View.GONE
                        view = iv_event_vertical
                        "${it[position].horizontal_cover_image}"
                    }
                    tv_header_vertical.text = if(!it[position].name.isNullOrBlank()) it[position].name
                    else it[position].action_button_text
                    tv_event_type_vertical.text = it[position].category_id.name


                    Glide.with(context)
                        .load(url)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .fitCenter()
                        .dontTransform()
                        .crossFade(20)
                        .placeholder(R.drawable.img_bg)
                        .into(view)

                    holder.itemView.setOnClickListener {
                        list?.let {it1-> itemSelected.onItemSelected(it1[position].name)}
                    }
                }
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view  = LayoutInflater.from(parent.context).inflate(R.layout.row_item_events_horizontal, parent, false)
        return ChildAdapterViewHolder(view)
    }


    fun updateAdapter(updatedList : ArrayList<FeaturedModel>?){
        updatedList?.let {
            list?.clear()
            list?.addAll(updatedList)
            notifyDataSetChanged()
        }
    }

    class ChildAdapterViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        val iv_event = item.iv_event
        val tv_event_type = item.tv_event_type
        val tv_header = item.tv_header
        val tv_date_time = item.tv_date_time
        val tv_watch_option = item.tv_watch_option
        val tv_price = item.tv_price
        val vertical_layout = item.vertical_layout
        val horizontal_layout = item.horizontal_layout
        val iv_event_vertical = item.iv_event_vertical
        val tv_event_type_vertical = item.tv_event_type_vertical
        val tv_header_vertical = item.tv_header_vertical

    }
}

enum class Type {
    Horizontal,
    Vertical
}
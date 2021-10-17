package com.manishbhati.baseproject.fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.manishbhati.baseproject.R
import com.manishbhati.baseproject.helper.CustomView
import com.manishbhati.domain.Response

class ItemUserAdapter(
    val data: List<Response>,
    val rejectClick: (data: Response) -> Unit,
    val acceptClick: (data: Response) -> Unit
) :
    RecyclerView.Adapter<ItemUserAdapter.ItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.apply {
            accept.setImage(true)
            accept.handleClick(true) {
                acceptClick.invoke(data[position])
            }
            reject.setImage(false)
            reject.handleClick(false) {
                rejectClick.invoke(data[position])
            }
            Glide.with(holder.itemView)
                .load(data[position].img)
                .into(profile)
            name.text = data[position].name
            info.text =
                "${data[position].age},${data[position].gender[0].uppercase()} \n\n ${data[position].location}"

        }
    }

    override fun getItemCount() = data.size

    class ItemViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        val profile = item.findViewById<ImageView>(R.id.iv_profile)
        val accept = item.findViewById<CustomView>(R.id.iv_accept)
        val reject = item.findViewById<CustomView>(R.id.iv_reject)
        val name = item.findViewById<TextView>(R.id.tv_name)
        val info = item.findViewById<TextView>(R.id.tv_info)

    }
}
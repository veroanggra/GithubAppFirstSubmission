package com.veronica.idn.githubappfirstsubmission.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.veronica.idn.githubappfirstsubmission.model.Users
import com.veronica.idn.githubappfirstsubmission.databinding.ItemUserBinding


class ItemAdapter(private val listUsers: ArrayList<Users>) :
    RecyclerView.Adapter<ItemAdapter.ItemListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback
    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ItemListViewHolder {
        val itemUserBinding =
            ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemListViewHolder(itemUserBinding)
    }


    override fun onBindViewHolder(holder: ItemListViewHolder, position: Int) {
        val users = listUsers[position]
        Glide.with(holder.itemView.context).load(users.avatar).circleCrop()
            .into(holder.itemBinding.ivItemUser)

        holder.itemBinding.tvItemName.text = users.name
        holder.itemBinding.tvItemUsername.text = users.username

        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(listUsers[holder.adapterPosition]) }

    }

    override fun getItemCount(): Int {
        return listUsers.size
    }

    class ItemListViewHolder(var itemBinding: ItemUserBinding) :
        RecyclerView.ViewHolder(itemBinding.root)

    interface OnItemClickCallback {
        fun onItemClicked(user: Users)
    }

}



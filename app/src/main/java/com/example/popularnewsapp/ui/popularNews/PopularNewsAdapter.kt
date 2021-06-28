package com.example.popularnewsapp.ui.popularNews

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.popularnewsapp.databinding.ItemPopularNewsBinding
import com.example.popularnewsapp.model.NewsModel

class PopularNewsAdapter(private val itemClickListener: OnItemClickListener) :
    ListAdapter<NewsModel, PopularNewsAdapter.PopularNewsViewHolder>(DiffCallback) {
    object DiffCallback : DiffUtil.ItemCallback<NewsModel>() {
        override fun areItemsTheSame(oldItem: NewsModel, newItem: NewsModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: NewsModel, newItem: NewsModel): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PopularNewsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemPopularNewsBinding.inflate(layoutInflater,parent,false)
        return PopularNewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PopularNewsViewHolder, position: Int) {
        val currentArticle = getItem(position)
        holder.binding.article = currentArticle
        holder.binding.executePendingBindings()
        holder.itemView.setOnClickListener { itemClickListener.onItemClick(currentArticle) }
    }

    class PopularNewsViewHolder(val binding: ItemPopularNewsBinding) : RecyclerView.ViewHolder(binding.root)

    interface OnItemClickListener {
        fun onItemClick(item: NewsModel)
    }
}
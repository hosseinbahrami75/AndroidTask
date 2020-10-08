package com.keyvan.android.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.keyvan.android.api.models.response.MoviesResult
import com.keyvan.android.databinding.MovieItemBinding

class MoviesAdapter(
    private val context: Context,
    private var moviesResults: MutableList<MoviesResult>,
    private var onclick: ((MoviesResult) -> Unit)?
) :
    RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>() {
    init {
        setHasStableIds(true)
    }

    class MoviesViewHolder(
        private val binding: MovieItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MoviesResult, context: Context, onclick: ((MoviesResult) -> Unit)?) {
            binding.data = item

            binding.item.setOnClickListener {
                onclick.let { click ->
                    click?.invoke(item)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = MovieItemBinding.inflate(inflater)
        return MoviesViewHolder(binding)
    }


    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        holder.bind(moviesResults[position], context, onclick)
    }

    override fun getItemViewType(position: Int): Int = position
    override fun getItemCount(): Int = moviesResults.size
//    override fun getItemId(position: Int): Long = storeData[position].id

    fun addItem(items: List<MoviesResult>) {
        val discoverDiffUtilCallback = DiscoverDiffUtilCallback(this.moviesResults, items)
        val diffResult = DiffUtil.calculateDiff(discoverDiffUtilCallback)
        this.moviesResults.addAll(items)
        diffResult.dispatchUpdatesTo(this)
    }

    fun updateItem(items: List<MoviesResult>) {
        val discoverDiffUtilCallback = DiscoverDiffUtilCallback(this.moviesResults, items)
        val diffResult = DiffUtil.calculateDiff(discoverDiffUtilCallback)
        this.moviesResults = items as ArrayList<MoviesResult>
        diffResult.dispatchUpdatesTo(this)
    }

    class DiscoverDiffUtilCallback(
        private val oldList: List<MoviesResult>,
        private val newList: List<MoviesResult>
    ) : DiffUtil.Callback() {
        override fun getOldListSize() = oldList.size

        override fun getNewListSize() = newList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
            oldList[oldItemPosition].id == newList[newItemPosition].id


        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
            oldList[oldItemPosition] == newList[newItemPosition]
    }
}

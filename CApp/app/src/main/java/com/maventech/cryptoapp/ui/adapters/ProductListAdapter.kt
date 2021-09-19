 package com.maventech.cryptoapp.ui.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.maventech.cryptoapp.R
import com.maventech.cryptoapp.databinding.RowProductItemBinding
import com.maventech.cryptoapp.model.products.Data
import com.maventech.cryptoapp.ui.callbacks.ProductClickCallback


 class ProductListAdapter(
    private val context: Context, list: List<Data>,
    onItemClickListener: ProductClickCallback
) : PagingDataAdapter<Data,ProductListAdapter.ViewHolder>(ProductsComparator) {
    private var list: List<Data>
    private val onItemClickListener: ProductClickCallback
    fun setList(list: List<Data>) {
        this.list = list
    }

    class ViewHolder(private val binding: RowProductItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bindProducts(item: Data) = with(binding) {
            binding.tv1Title.setText(item.product_name)
            binding.tvPrice.setText( item.product_price)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: RowProductItemBinding = DataBindingUtil
            .inflate(
                LayoutInflater.from(parent.context), R.layout.row_product_item,
                parent, false
            )
        binding.setCallback(onItemClickListener)

        return ViewHolder(binding)

    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        item?.let { holder.bindProducts(it) }
    }


    override fun getItemCount(): Int {
        return list.size
    }

    companion object {
        private val TAG: String = ProductListAdapter::class.java.getSimpleName()
    }

    init {
        this.list = list
        this.onItemClickListener = onItemClickListener
    }

     object ProductsComparator : DiffUtil.ItemCallback<Data>() {
         override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
             return oldItem._id == newItem._id
         }

         override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
             return oldItem == newItem
         }
     }
}
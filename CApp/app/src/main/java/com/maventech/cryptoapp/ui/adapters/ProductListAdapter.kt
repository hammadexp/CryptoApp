package com.maventech.cryptoapp.ui.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.maventech.cryptoapp.R
import com.maventech.cryptoapp.databinding.RowProductItemBinding
import com.maventech.cryptoapp.model.product.Product
import com.maventech.cryptoapp.ui.callbacks.ProductClickCallback


class ProductListAdapter(
    private val context: Context, list: MutableList<Product>,
    onItemClickListener: ProductClickCallback
) : ListAdapter<Product, ProductListAdapter.ViewHolder>(TodoDiffCallback()) {
    private var listProduct: MutableList<Product>
    private val onItemClickListener: ProductClickCallback
    fun setList(list: MutableList<Product>) {
        this.listProduct = list
    }


    class ViewHolder(private val binding: RowProductItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bindProducts(item: Product) = with(binding) {
            binding.data = item
            Glide.with(binding.ivImage.context).load(item.image).into(binding.ivImage);
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
        val item = currentList[position]
        item?.let { holder.bindProducts(it) }
    }


    override fun getItemCount(): Int {
        return currentList.size
    }

    companion object {
        private val TAG: String = ProductListAdapter::class.java.getSimpleName()
    }

    init {
        this.listProduct = list
        this.onItemClickListener = onItemClickListener
    }

    open class MyDiffCallback(
        private val oldGalaxies: List<Product>,
        private val newGalaxies: List<Product>
    ) : DiffUtil.Callback() {
        override fun getOldListSize(): Int {
            return oldGalaxies.size
        }

        override fun getNewListSize(): Int {
            return newGalaxies.size
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            // In the real world you need to compare something unique like id
            return oldGalaxies[oldItemPosition] == newGalaxies[newItemPosition]
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            // This is called if areItemsTheSame() == true;
            return oldGalaxies[oldItemPosition] == newGalaxies[newItemPosition]
        }
    }


}

class TodoDiffCallback : DiffUtil.ItemCallback<Product>() {

    override fun areItemsTheSame(p0: Product, p1: Product): Boolean {
        Log.i("debug", " here " + p0 + " inItems " + p1)
        return p0 == p1
    }

    override fun areContentsTheSame(p0: Product, p1: Product): Boolean {
        Log.i("debug", "here " + p0 + " inContents" + p1)
        return p0 == p1
    }
}
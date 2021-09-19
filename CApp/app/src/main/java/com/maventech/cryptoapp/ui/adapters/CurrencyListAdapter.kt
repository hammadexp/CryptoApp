 package com.maventech.cryptoapp.ui.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.maventech.cryptoapp.R
import com.maventech.cryptoapp.databinding.RowCurrencyItemBinding
import com.maventech.cryptoapp.model.currencyList.CurrencyItem
import com.maventech.cryptoapp.ui.callbacks.ProductClickCallback


 class CurrencyListAdapter(
    private val context: Context, list: MutableList<CurrencyItem>,
    onItemClickListener: ProductClickCallback
) : ListAdapter<CurrencyItem,CurrencyListAdapter.ViewHolder>(TodoDiffCallback()) {
    private var listCurrency: MutableList<CurrencyItem>
    private val onItemClickListener: ProductClickCallback
    fun setList(list: MutableList<CurrencyItem>) {
        this.listCurrency = list
    }

     fun setData(newCurrencyItem: MutableList<CurrencyItem>) {
         val diffCallback = MyDiffCallback(this.listCurrency, newCurrencyItem)
         val diffResult = DiffUtil.calculateDiff(diffCallback)
         diffResult.dispatchUpdatesTo(this)
//         this.listCurrency=newCurrencyItem
     }

    class ViewHolder(private val binding: RowCurrencyItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bindProducts(item: CurrencyItem) = with(binding) {
            binding.tvName.setText(item.name)
            binding.tvRate.setText( item.rate)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: RowCurrencyItemBinding = DataBindingUtil
            .inflate(
                LayoutInflater.from(parent.context), R.layout.row_currency_item,
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
        private val TAG: String = CurrencyListAdapter::class.java.getSimpleName()
    }

    init {
        this.listCurrency = list
        this.onItemClickListener = onItemClickListener
    }

     open class MyDiffCallback(
         private val oldGalaxies: List<CurrencyItem>,
         private val newGalaxies: List<CurrencyItem>
     ): DiffUtil.Callback() {
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
  class TodoDiffCallback : DiffUtil.ItemCallback<CurrencyItem>() {

     override fun areItemsTheSame(p0: CurrencyItem, p1: CurrencyItem): Boolean {
         Log.i("debug", " here " + p0 + " inItems " + p1)
         return p0 == p1
     }

     override fun areContentsTheSame(p0: CurrencyItem, p1: CurrencyItem): Boolean {
         Log.i("debug", "here " + p0 + " inContents" + p1)
         return p0 == p1
     }
 }
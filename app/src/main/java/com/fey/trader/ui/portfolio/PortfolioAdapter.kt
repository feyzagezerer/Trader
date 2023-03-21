package com.fey.trader.ui.portfolio

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import com.fey.trader.databinding.ItemStockBinding
import androidx.recyclerview.widget.RecyclerView
import com.fey.trader.R
import com.fey.trader.core.BaseViewHolder
import com.fey.trader.data.model.Item
import com.fey.trader.data.model.ListItem



import com.squareup.picasso.Picasso

class PortfolioAdapter  : RecyclerView.Adapter<PortfolioAdapter.PortfolioViewHolder>() {

    private val stocks = mutableListOf<Item>()
    fun setStocks(stocks: List<Item>){
        this.stocks.clear()
        this.stocks.addAll(stocks)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PortfolioViewHolder {
       val binding = DataBindingUtil.inflate<ItemStockBinding>(
           LayoutInflater.from(parent.context),
       R.layout.item_stock,
        parent,
        false
        )
        return PortfolioViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PortfolioViewHolder, position: Int) {
        holder.bind(stocks[position])

    }

    override fun getItemCount(): Int {
        return stocks.size
    }

    class PortfolioViewHolder(private val binding: ItemStockBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(stock: Item) {
            binding.symbol.text = stock.symbol
            binding.qtyT2.text = stock.qtyT2.toString()
            binding.lastPx.text = stock.lastPx.toString()
        //    binding.apply {
       // }
    }
}
}
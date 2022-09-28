package com.example.myinventory


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myinventory.data.Item
import com.example.myinventory.data.getFormattedPrice
import com.example.myinventory.databinding.ItemListItemBinding

class ItemListAdapter(private val onItemClicked: (Item) -> Unit) :
    ListAdapter<Item, ItemListAdapter.ItemViewHolder>(DiffCallback)
{
    class ItemViewHolder(private var binding: ItemListItemBinding) :
        RecyclerView.ViewHolder(binding.root)  {

            fun bind(item: Item){
                binding.itemName.text = item.itemName
                binding.itemPrice.text = item.getFormattedPrice()
                binding.itemQuantity.text = item.quantityInStock.toString()
            }

    }

    companion object {
            private val DiffCallback = object : DiffUtil.ItemCallback<Item>() {
                override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
                    return oldItem === newItem
                }

                override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
                    return oldItem.itemName == newItem.itemName
                }
            }
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemListAdapter.ItemViewHolder {
      return ItemViewHolder(
          ItemListItemBinding.inflate(
              LayoutInflater.from(
                  parent.context
              )
          )
      )
    }

    override fun onBindViewHolder(holder: ItemListAdapter.ItemViewHolder, position: Int) {
   val current = getItem(position)
        holder.itemView.setOnClickListener {
            onItemClicked(current)
        }
        holder.bind(current)
    }

}
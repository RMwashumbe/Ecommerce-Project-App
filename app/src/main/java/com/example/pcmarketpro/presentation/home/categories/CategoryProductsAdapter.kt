package com.example.pcmarketpro.presentation.home.categories

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pcmarketpro.data.model.Product

class CategoryProductsAdapter : RecyclerView.Adapter<CategoryProductsAdapter.ProductsViewHolder>() {

    private val list = ArrayList<Product>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsViewHolder =
        ProductsViewHolder(
            ItemProductBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) =
        holder.bind(list[position])

    inner class ProductsViewHolder(private var binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Product) {
            binding.product = item

            binding.imgProduct.setOnClickListener {
                val action = HomeFragmentDirections.actionHomeFragmentToProductDetailFragment(item)
                it.findNavController().navigate(action)
            }
        }
    }

    override fun getItemCount() = list.size

    fun updateList(updatedList: List<Product>) {
        list.clear()
        list.addAll(updatedList)
        notifyItemRangeInserted(0, updatedList.size)
    }
}
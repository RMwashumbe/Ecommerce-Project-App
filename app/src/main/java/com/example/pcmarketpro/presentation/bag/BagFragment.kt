package com.example.pcmarketpro.presentation.bag

import android.app.Fragment
import android.os.Bundle
import android.view.View
import com.example.pcmarketpro.R
import com.example.pcmarketpro.common.Resource
import com.example.pcmarketpro.common.delegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BagFragment : Fragment(R.layout.fragment_bag) {

    private val binding by viewBinding(FragmentBagBinding::bind)

    private val bagViewModel: BagViewModel by viewModels()

    private val bagProductsAdapter by lazy { BagProductsAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObservers()

        with(bagViewModel) {

            bagProductsAdapter.onIncreaseClick = {
                increase(it)
            }

            bagProductsAdapter.onDecreaseClick = {
                decrease(it)
            }

            bagProductsAdapter.onDeleteClick = { id ->
                deleteFromBag(id)
                resetTotalAmount()
            }
        }
    }

    private fun initObservers() {

        with(binding) {
            with(bagViewModel) {

                bagProducts.observe(viewLifecycleOwner) { result ->
                    when (result) {
                        is Resource.Success -> {
                            result.data.forEach { product ->
                                increase(product.price)
                            }
                            progressBar.gone()
                            bagProductsAdapter.updateList(result.data)
                            rvBagProducts.adapter = bagProductsAdapter

                            binding.btnBuyNow.setOnClickListener {
                                if (result.data.isNotEmpty()) {
                                    findNavController().navigate(R.id.action_bagFragment_to_paymentFragment)
                                } else {
                                    requireView().showSnackbar(getString(R.string.invalid_bag_products))
                                }
                            }
                        }

                        is Resource.Error -> {
                            progressBar.gone()
                            requireView().showSnackbar(result.throwable.message.toString())
                        }

                        Resource.Loading -> progressBar.visible()
                    }
                }

                totalAmount.observe(viewLifecycleOwner) {
                    tvTotalAmount.text = String.format("%.3f$", it)
                }
            }
        }
    }
}
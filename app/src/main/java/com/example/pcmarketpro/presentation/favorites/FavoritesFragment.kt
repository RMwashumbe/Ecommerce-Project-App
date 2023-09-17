package com.example.pcmarketpro.presentation.favorites

import android.app.Fragment
import android.os.Bundle
import android.view.View
import com.example.pcmarketpro.R
import com.example.pcmarketpro.common.Resource
import com.example.pcmarketpro.common.delegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoritesFragment : Fragment(R.layout.fragment_favorites) {

    private val binding by viewBinding(FragmentFavoritesBinding::bind)

    private val favoritesViewModel: FavoritesViewModel by viewModels()

    private val favoritesAdapter by lazy { FavoritesAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObservers()

        favoritesAdapter.onDeleteClick = {
            favoritesViewModel.deleteFromFavorites(it)
        }
    }

    private fun initObservers() {

        with(binding) {

            favoritesViewModel.favoriteList.observe(viewLifecycleOwner) {
                when (it) {
                    is Resource.Success -> {
                        progressBar.gone()
                        favoritesAdapter.updateList(it.data)
                        rvFavorites.adapter = favoritesAdapter
                    }

                    is Resource.Error -> {
                        progressBar.gone()
                        requireView().showSnackbar(it.throwable.message.toString())
                    }

                    Resource.Loading -> progressBar.gone()
                }
            }
        }
    }
}
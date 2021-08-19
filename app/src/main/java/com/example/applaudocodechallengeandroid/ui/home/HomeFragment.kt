package com.example.applaudocodechallengeandroid.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.applaudocodechallengeandroid.R
import com.example.applaudocodechallengeandroid.databinding.FragmentHomeBinding
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    lateinit var adapter: HomeAdapter
    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_home,
            container,
            false
        )
        binding.viewModel = this.viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.seriesResponse.observe(binding.lifecycleOwner!!, {
            viewModel.hideProgress.postValue(false)
            adapter = HomeAdapter(
                this,
                it.data ?: ArrayList()
            )
            binding.adapter = adapter
            adapter.notifyDataSetChanged()
        })

//        viewModel.seasons.observe(binding.lifecycleOwner!!, {
//            viewModel.hideProgress.postValue(false)
//            val bundle =
//                bundleOf(
//                    "Seasons" to Gson().toJson(it),
//                    "Show" to Gson().toJson(viewModel.showSelected.value),
//                    "Favorite" to true
//                )
//            findNavController().navigate(
//                R.id.action_homeFragment_to_showFragment,
//                bundle
//            )
//        })

//        viewModel.showFavorites.observe(binding.lifecycleOwner!!, {
//            findNavController().navigate(R.id.action_homeFragment_to_favoritesFragment)
//        })

        if (!::adapter.isInitialized) {
            viewModel.getSeries()
            adapter = HomeAdapter(this, ArrayList())
            binding.adapter = adapter
        } else {
            binding.adapter = adapter
        }

    }

}
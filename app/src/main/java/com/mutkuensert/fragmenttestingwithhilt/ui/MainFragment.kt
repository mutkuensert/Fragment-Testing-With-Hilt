package com.mutkuensert.fragmenttestingwithhilt.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.mutkuensert.fragmenttestingwithhilt.data.ImageHitsModel
import com.mutkuensert.fragmenttestingwithhilt.databinding.FragmentMainBinding
import com.mutkuensert.fragmenttestingwithhilt.util.Resource
import com.mutkuensert.fragmenttestingwithhilt.util.Status
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainFragment : Fragment() {
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainFragmentViewModel by viewModels()
    @Inject lateinit var recyclerAdapter: MyRecyclerAdapter //private val recyclerAdapter = MyRecyclerAdapter()
    private var oldHitsList = mutableListOf<ImageHitsModel>()
    private var pageNumber: Int = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.layoutManager = LinearLayoutManager(this.context)
        binding.recyclerView.adapter = recyclerAdapter

        setObserver()
        setClickListeners()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun setObserver(){
        viewModel.data.observe(viewLifecycleOwner){ resource->
            when(resource.status){
                Status.STANDBY -> {}
                Status.LOADING -> {}
                Status.SUCCESS -> {resource.data?.hits?.let {
                    binding.progressBarLoadingMore.visibility = View.GONE
                    binding.progressBarLoadingSearchResults.visibility = View.GONE
                    if(it.isEmpty()){
                        Toast.makeText(requireContext(), "The end of the search results.", Toast.LENGTH_LONG).show()
                    }else{
                        binding.recyclerView.visibility = View.VISIBLE
                        recyclerAdapter.submitList(oldHitsList + it)
                        oldHitsList += it
                        binding.loadMoreButton.visibility = View.VISIBLE
                    }
                }}
                Status.ERROR -> {
                    Toast.makeText(requireContext(), "Error", Toast.LENGTH_LONG).show()
                    binding.progressBarLoadingMore.visibility = View.GONE
                    binding.progressBarLoadingSearchResults.visibility = View.GONE
                    binding.loadMoreButton.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun setClickListeners(){
        binding.loadMoreButton.setOnClickListener {
            it.visibility = View.GONE
            binding.progressBarLoadingMore.visibility = View.VISIBLE
            pageNumber += 1
            viewModel.requestImages(binding.editTextSearch.text.toString(), pageNumber)
        }

        binding.searchButton.setOnClickListener {
            with(binding){
                progressBarLoadingSearchResults.visibility = View.VISIBLE
                recyclerView.visibility = View.GONE
                loadMoreButton.visibility = View.GONE
            }
            oldHitsList = mutableListOf()
            pageNumber = 1
            viewModel.requestImages(binding.editTextSearch.text.toString(), pageNumber)
        }
    }
}
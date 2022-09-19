package es.jolusan.dogbreedspictures.presentation.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import dagger.hilt.android.AndroidEntryPoint
import es.jolusan.dogbreedspictures.R
import es.jolusan.dogbreedspictures.databinding.MainFragmentBinding
import es.jolusan.dogbreedspictures.domain.model.DogBreed
import es.jolusan.dogbreedspictures.domain.model.DogBreedItem
import es.jolusan.dogbreedspictures.domain.model.toListItem
import es.jolusan.dogbreedspictures.utils.ResponseStatus
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: MainFragmentBinding
    private lateinit var dogBreedsAdapter: DogBreedsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        setupObserver()
        getDogBreeds()
    }

    private fun setupUI() {
        dogBreedsAdapter = DogBreedsAdapter(viewModel::onBreedClicked)
        binding.breedsRecyclerView.adapter = dogBreedsAdapter
    }

    private fun setupObserver() {
        lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.dogBreed.collect {
                    when (it) {
                        is ResponseStatus.Success -> {
                            binding.progressBar.visibility = View.GONE
                            it.data?.let { breedsList -> updateBreedsList(breedsList) }
                            binding.breedsRecyclerView.visibility = View.VISIBLE
                        }
                        is ResponseStatus.Loading -> {
                            binding.progressBar.visibility = View.VISIBLE
                            binding.breedsRecyclerView.visibility = View.GONE
                        }
                        is ResponseStatus.Error -> {
                            binding.progressBar.visibility = View.GONE
                            binding.infoTextView.visibility = View.VISIBLE
                            binding.infoTextView.text = it.messageResource?.let { resource -> getString(resource) } ?: getString(R.string.error_response)
                        }
                    }
                }
            }
        }
    }

    private fun getDogBreeds() {
        viewModel.getDogBreeds()
    }

    private fun updateBreedsList (breedsList: List<DogBreed>) {
        if (breedsList.isEmpty()){
            binding.infoTextView.visibility = View.VISIBLE
            binding.infoTextView.text = getString(R.string.error_response)
            binding.breedsRecyclerView.visibility = View.GONE
        } else {
            val breedsItems = breedsList.map { breed -> breed.toListItem() }
            dogBreedsAdapter.breedsList = breedsItems
        }
    }
}
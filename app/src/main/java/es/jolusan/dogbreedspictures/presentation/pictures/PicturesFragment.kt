package es.jolusan.dogbreedspictures.presentation.pictures

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import dagger.hilt.android.AndroidEntryPoint
import es.jolusan.dogbreedspictures.R
import es.jolusan.dogbreedspictures.databinding.PicturesFragmentBinding
import es.jolusan.dogbreedspictures.utils.Constants
import es.jolusan.dogbreedspictures.utils.ResponseStatus
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PicturesFragment : Fragment() {
    private lateinit var binding: PicturesFragmentBinding
    private val viewModel: PicturesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = PicturesFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getString(Constants.PICTURE_BUNDLE_ARGUMENT)?.let {
            viewModel.getBreedImages(it)
        }
        setupObservers()
    }

    private fun setupObservers() {
        lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.breedPictures.collect {
                    when (it) {
                        is ResponseStatus.Success -> {
                            binding.progressBar.visibility = View.GONE
                           // it.data?.let { breedsList -> updateBreedsList(breedsList) }
                           // binding.breedsRecyclerView.visibility = View.VISIBLE
                        }
                        is ResponseStatus.Loading -> {
                            binding.progressBar.visibility = View.VISIBLE
                           // binding.breedsRecyclerView.visibility = View.GONE
                        }
                        is ResponseStatus.Error -> {
                            binding.progressBar.visibility = View.GONE
                          //  binding.infoTextView.visibility = View.VISIBLE
                          //  binding.infoTextView.text = it.messageResource?.let { resource -> getString(resource) } ?: getString(
                          //      R.string.error_response)
                        }
                    }
                }
            }
        }
    }
}
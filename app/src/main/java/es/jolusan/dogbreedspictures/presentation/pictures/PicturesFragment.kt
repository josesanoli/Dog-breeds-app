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

    private val viewModel: PicturesViewModel by viewModels()
    private lateinit var binding: PicturesFragmentBinding
    private lateinit var breedPicturesAdapter: PicturesAdapter

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
            getBreedPictures(it)
        }
        setupUI()
        setupObservers()
    }

    private fun setupUI() {
        breedPicturesAdapter = PicturesAdapter(this)
        binding.breedPicturesPager.adapter = breedPicturesAdapter
    }

    private fun setupObservers() {
        lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.breedPictures.collect {
                    when (it) {
                        is ResponseStatus.Success -> {
                            binding.progressBar.visibility = View.GONE
                            it.data?.let { breedsList -> updatePicturesPager(breedsList) }
                            binding.breedPicturesPager.visibility = View.VISIBLE
                        }
                        is ResponseStatus.Loading -> {
                            binding.progressBar.visibility = View.VISIBLE
                            binding.breedPicturesPager.visibility = View.GONE
                        }
                        is ResponseStatus.Error -> {
                            binding.progressBar.visibility = View.GONE
                            binding.picturesInfoTextView.visibility = View.VISIBLE
                            binding.picturesInfoTextView.text = getString(R.string.error_response)
                        }
                    }
                }
            }
        }
    }

    private fun getBreedPictures(breedName: String) {
        viewModel.getBreedImages(breedName)
    }

    private fun updatePicturesPager (picturesList: List<String>) {
        if (picturesList.isEmpty()){
            binding.picturesInfoTextView.visibility = View.VISIBLE
            binding.picturesInfoTextView.text = getString(R.string.error_response)
            binding.breedPicturesPager.visibility = View.GONE
        } else {
            breedPicturesAdapter.breedUrlPictures = picturesList
        }
    }

}
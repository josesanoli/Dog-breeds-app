package es.jolusan.dogbreedspictures.presentation.pictures

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import es.jolusan.dogbreedspictures.databinding.PicturesFragmentBinding
import es.jolusan.dogbreedspictures.utils.Constants

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
            viewModel.getBreedPictures(it)
        }
        setupObservers()
    }

    private fun setupObservers() {

    }
}
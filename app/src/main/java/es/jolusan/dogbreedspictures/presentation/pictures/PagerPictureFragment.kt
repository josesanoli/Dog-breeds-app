package es.jolusan.dogbreedspictures.presentation.pictures

import android.os.Bundle
import android.provider.SyncStateContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import es.jolusan.dogbreedspictures.R
import es.jolusan.dogbreedspictures.databinding.PagerPictureFragmentBinding
import es.jolusan.dogbreedspictures.databinding.PicturesFragmentBinding
import es.jolusan.dogbreedspictures.utils.Constants
import es.jolusan.dogbreedspictures.utils.loadUrl

class PagerPictureFragment : Fragment() {

    lateinit var binding: PagerPictureFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = PagerPictureFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        arguments?.getString(Constants.PAGER_ARGUMENT)?.let { urlString ->
            binding.pictureImageview.loadUrl(urlString)
        }
    }
}
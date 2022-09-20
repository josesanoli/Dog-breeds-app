package es.jolusan.dogbreedspictures.presentation.pictures

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import es.jolusan.dogbreedspictures.utils.Constants.PAGER_ARGUMENT

class PicturesAdapter(
    fragment: Fragment,
) : FragmentStateAdapter(fragment) {

    var breedUrlPictures: List<String> = listOf()

    override fun getItemCount(): Int = breedUrlPictures.size

    override fun createFragment(position: Int): Fragment {
        val fragment = PagerPictureFragment()
        fragment.arguments = Bundle().apply {
            putString(PAGER_ARGUMENT, breedUrlPictures[position])
        }
        return fragment
    }
}
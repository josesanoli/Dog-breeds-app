package es.jolusan.dogbreedspictures.presentation.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import es.jolusan.dogbreedspictures.databinding.DogBreedItemBinding
import es.jolusan.dogbreedspictures.domain.model.DogBreed
import es.jolusan.dogbreedspictures.utils.loadUrl

class DogBreedsAdapter(
    private val listener: (DogBreed) -> Unit
) : RecyclerView.Adapter<DogBreedsAdapter.BreedViewHolder>(){

    var breedsList: List<DogBreed> = listOf()

    inner class BreedViewHolder(
        private val binding: DogBreedItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(breed: DogBreed) = with(binding) {
            breedName.text = breed.breedName
                .replaceFirstChar { it.uppercase() }

            subbreeds.text = breed.subBreeds
                .joinToString(", ") { it }
                .replaceFirstChar { it.uppercase() }

            breedImage
                .loadUrl(breed.imageURL)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BreedViewHolder {
        val binding = DogBreedItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BreedViewHolder(binding)
    }

    override fun getItemCount(): Int = breedsList.size

    override fun onBindViewHolder(holder: BreedViewHolder, position: Int) = with(holder) {
        val breed = breedsList[position]
        bind(breed)
        itemView.setOnClickListener { listener(breed) }
    }
}
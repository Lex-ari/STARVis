package com.broncospace.android.starvis

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.R
import com.broncospace.android.starvis.spacecraft.SpacecraftItem
import com.databinding.ListItemGalleryBinding

class SatelliteViewHolder (
    private val binding: ListItemGalleryBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(spacecraftItem: SpacecraftItem, onSpacecraftItemClicked: (noradId: Integer) -> Unit) {
        binding.listItemSatelliteNameTextview.text = spacecraftItem.name
        binding.listItemIdTextview.text = spacecraftItem.noradId.toString()
        binding.listItemImageBackground.load(spacecraftItem.photoLink) {
            placeholder(R.drawable.space_laser)
        }
    }
}

class SatelliteListAdapter(
    private val positionItems: List<SpacecraftItem>,
    private val onSpacecraftItemClicked: (noradId: Integer) -> Unit
) : RecyclerView.Adapter<SatelliteViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SatelliteViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemGalleryBinding.inflate(inflater, parent, false)
        return SatelliteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SatelliteViewHolder, position: Int) {
        val item = positionItems[position]
        holder.bind(item, onSpacecraftItemClicked)
    }

    override fun getItemCount() = positionItems.size
}
package com.broncospace.android.starvis

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.databinding.FragmentSatelliteGalleryBinding
import kotlinx.coroutines.launch

private const val TAG = "SatelliteGalleryFragment"
class SatelliteGalleryFragment : Fragment() {
    private var _binding: FragmentSatelliteGalleryBinding? = null
    private val binding
        get() = checkNotNull(_binding) {
            "Cannot access binding because it is null. Is the view visible?"
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding =
            FragmentSatelliteGalleryBinding.inflate(inflater, container, false)
        binding.photoGrid.layoutManager = GridLayoutManager(context, 1)
        return binding.root
    }

    private val satelliteGalleryViewModel : SatelliteGalleryViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            satelliteGalleryViewModel.galleryItems.collect { items ->
                binding.photoGrid.adapter = SatelliteListAdapter(items){noradId ->
                    //ToDO
                }
            }
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
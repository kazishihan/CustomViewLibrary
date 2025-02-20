package com.kazishihan.myCustomLibrary.minirail.fragmentConfig

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.kazishihan.myCustomLibrary.databinding.FragmentMiniRailBinding
import com.kazishihan.myCustomLibrary.security.SecurityHelper


internal class MiniRailFragment : Fragment() {
    
    private val binding get() = _binding
    private var _binding: FragmentMiniRailBinding? = null
    private val adapter = MiniRailAdapter()
    
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        
        _binding = FragmentMiniRailBinding.inflate(inflater, container, false)
        
        if (!SecurityHelper.isValidCaller(requireContext())) {
            throw SecurityException("Unauthorized access! This library is restricted to specific apps.")
        }
        return binding?.root
    }
    
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        binding?.recyclerView?.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL,false)
        binding?.recyclerView?.adapter = adapter
        
        // Retrieve arguments safely
        val title = arguments?.getString(ARG_TITLE) ?: "Default Title"
        val items = arguments?.getStringArrayList(ARG_ITEMS) ?: arrayListOf()
        
        binding?.titleTextView?.text = title
        adapter.submitList(items)
        
    }
    
    companion object {
        private const val ARG_TITLE = "title"
        private const val ARG_ITEMS = "items"
        
        fun builder() = MiniRailFragmentBuilder()  // Direct access to Builder
    }
}

package com.kazishihan.myCustomLibrary.landing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.kazishihan.myCustomLibrary.R
import com.kazishihan.myCustomLibrary.bridge.LibraryBridgeManager
import com.kazishihan.myCustomLibrary.bridge.LibraryEvent

class PageOneFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_page_one, container, false)
        view.findViewById<Button>(R.id.goToPageTwoButton)?.setOnClickListener {
            findNavController().navigate(R.id.action_fragmentOne_to_fragmentTwo)
        }
        view.findViewById<Button>(R.id.sendToAppButton)?.setOnClickListener {
            val message = "Hello from Library"
            LibraryBridgeManager.sendToApp(LibraryEvent(event = "LIBRARY_MESSAGE", data = message))
            Toast.makeText(requireContext(), "Sent to app: $message", Toast.LENGTH_SHORT).show()
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val goToPageTwoButton = view.findViewById<Button>(R.id.goToPageTwoButton)
        goToPageTwoButton.setOnClickListener {
            findNavController().navigate(R.id.action_fragmentOne_to_fragmentTwo)
        }

        val sendToAppButton = view.findViewById<Button>(R.id.sendToAppButton)
        sendToAppButton.setOnClickListener {
            val message = "Hello from Library"
            LibraryBridgeManager.sendToApp(LibraryEvent(event = "LIBRARY_MESSAGE", data = message))
           // Toast.makeText(requireContext(), "Sent to app: $message", Toast.LENGTH_SHORT).show()
        }
    }
}
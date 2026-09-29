package com.kazishihan.myCustomLibrary.landing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.kazishihan.myCustomLibrary.R
import com.kazishihan.myCustomLibrary.bridge.LibraryBridgeListener
import com.kazishihan.myCustomLibrary.bridge.LibraryBridgeManager
import com.kazishihan.myCustomLibrary.bridge.LibraryEvent

class PageTwoFragment : Fragment() {

    private val bridgeListener = object : LibraryBridgeListener {
        override fun onLibraryEvent(event: LibraryEvent) {
            if (event.event == "LIBRARY_MESSAGE") {
                view?.findViewById<TextView>(R.id.receivedMessageText)?.text = event.data ?: "No message"
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_page_two, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        LibraryBridgeManager.registerListener(bridgeListener)

        val sendMessageButton = view.findViewById<Button>(R.id.sendMessageButton)
        sendMessageButton.setOnClickListener {
            LibraryBridgeManager.sendToLibrary(LibraryEvent(event = "LIBRARY_MESSAGE", data = "Hello from main App!"))
        }
    }

    override fun onDestroyView() {
        LibraryBridgeManager.unregisterListener(bridgeListener)
        super.onDestroyView()
    }
}
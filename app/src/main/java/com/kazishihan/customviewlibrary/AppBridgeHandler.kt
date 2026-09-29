package com.kazishihan.customviewlibrary

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.kazishihan.myCustomLibrary.bridge.LibraryBridge
import com.kazishihan.myCustomLibrary.bridge.LibraryBridgeListener
import com.kazishihan.myCustomLibrary.bridge.LibraryBridgeManager
import com.kazishihan.myCustomLibrary.bridge.LibraryEvent

class AppBridgeHandler(
    private val context: Context
) : LibraryBridge {

    private val listener = object : LibraryBridgeListener {
        override fun onLibraryEvent(event: LibraryEvent) {
            when (event.event) {
                "LIBRARY_MESSAGE" -> {
                    val value = event.data ?: ""
                    Log.d("AppBridgeHandler", "Received from library: $value")
                    Toast.makeText(context, "AppBridgeHandler: ${value}", Toast.LENGTH_SHORT).show()
                    context.getSharedPreferences("library_bridge_prefs", Context.MODE_PRIVATE)
                        .edit()
                        .putString("library_message", value)
                        .apply()
                }
            }
        }
    }

    fun register() {
        LibraryBridgeManager.initialize(this)
        LibraryBridgeManager.registerListener(listener)
    }

    fun unregister() {
        LibraryBridgeManager.unregisterListener(listener)
    }

    override fun sendToApp(event: LibraryEvent) {
        Log.d("AppBridgeHandler", "App received library event: $event")
        Toast.makeText(context, "Received from library: ${event.data}", Toast.LENGTH_SHORT).show()
        context.getSharedPreferences("library_bridge_prefs", Context.MODE_PRIVATE)
            .edit()
            .putString("last_library_event", event.data)
            .apply()
    }

    override fun sendToLibrary(event: LibraryEvent) {
        LibraryBridgeManager.dispatchFromHost(event)
    }
}

package com.kazishihan.myCustomLibrary.bridge

import android.util.Log
import java.util.concurrent.CopyOnWriteArraySet

data class LibraryEvent(
    val event: String,
    val data: String? = null
)

interface LibraryBridge {
    fun sendToApp(event: LibraryEvent)
    fun sendToLibrary(event: LibraryEvent)
}

interface LibraryBridgeListener {
    fun onLibraryEvent(event: LibraryEvent)
}

object LibraryBridgeManager {
    private const val TAG = "LibraryBridgeManager"

    @Volatile
    private var bridge: LibraryBridge? = null

    private val listeners = CopyOnWriteArraySet<LibraryBridgeListener>()

    fun initialize(bridge: LibraryBridge) {
        this.bridge = bridge
    }

    fun registerListener(listener: LibraryBridgeListener) {
        listeners.add(listener)
    }

    fun unregisterListener(listener: LibraryBridgeListener) {
        listeners.remove(listener)
    }

    fun sendToApp(event: LibraryEvent) {
        val activeBridge = bridge
        if (activeBridge == null) {
            Log.w(TAG, "Bridge not initialized. Dropping library event: $event")
            return
        }
        activeBridge.sendToApp(event)
    }

    fun sendToLibrary(event: LibraryEvent) {
        val activeBridge = bridge
        if (activeBridge == null) {
            Log.w(TAG, "Bridge not initialized. Dropping app event: $event")
            return
        }
        activeBridge.sendToLibrary(event)
    }

    fun dispatchFromHost(event: LibraryEvent) {
        if (listeners.isEmpty()) {
            Log.w(TAG, "No registered library listeners for event: $event")
            return
        }

        listeners.forEach { listener ->
            listener.onLibraryEvent(event)
        }
    }
}

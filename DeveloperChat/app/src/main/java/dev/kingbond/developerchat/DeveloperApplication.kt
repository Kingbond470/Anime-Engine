package dev.kingbond.developerchat

import android.app.Application
import io.getstream.chat.android.client.ChatClient
import io.getstream.chat.android.client.logger.ChatLogLevel
import io.getstream.chat.android.livedata.ChatDomain

class DeveloperApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        // chat log level - show all the log from stream chat
        // entry point for all low level operation

        // main entry point for all live data for chat
        val client =
            ChatClient.Builder(getString(R.string.api_key), this).logLevel(ChatLogLevel.ALL).build()
        ChatDomain.Builder(client, this).build()
    }

}

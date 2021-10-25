package dev.kingbond.developerchat.ui.chat


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.getstream.sdk.chat.viewmodel.MessageInputViewModel
import com.getstream.sdk.chat.viewmodel.messages.MessageListViewModel
import dev.kingbond.developerchat.R
import dev.kingbond.developerchat.databinding.FragmentChatBinding
import io.getstream.chat.android.ui.message.input.viewmodel.bindView
import io.getstream.chat.android.ui.message.list.header.viewmodel.MessageListHeaderViewModel
import io.getstream.chat.android.ui.message.list.header.viewmodel.bindView
import io.getstream.chat.android.ui.message.list.viewmodel.bindView
import io.getstream.chat.android.ui.message.list.viewmodel.factory.MessageListViewModelFactory

class ChatFragment : Fragment() {

    private val args:ChatFragmentArgs by navArgs()


    private var _binding: FragmentChatBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentChatBinding.inflate(inflater, container, false)

        binding.messagesHeaderView.setBackButtonClickListener{
            requireActivity().onBackPressed()
        }

        setMessages()

        return binding.root
    }

    private fun setMessages() {
        val factory=MessageListViewModelFactory(cid = args.channelId)

        val messageListHeaderViewModel:MessageListHeaderViewModel by viewModels { factory }
        val messageListViewModel:MessageListViewModel by viewModels { factory }
        val messageInputViewModel: MessageInputViewModel by viewModels {factory  }

        messageListHeaderViewModel.bindView(binding.messagesHeaderView,viewLifecycleOwner)
        messageListViewModel.bindView(binding.messageList, viewLifecycleOwner)
        messageInputViewModel.bindView(binding.messageInputView, viewLifecycleOwner)

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }
}




package dev.kingbond.developerchat.ui.channel

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import dev.kingbond.developerchat.R
import dev.kingbond.developerchat.databinding.FragmentChannelBinding
import io.getstream.chat.android.client.ChatClient
import io.getstream.chat.android.client.models.Channel
import io.getstream.chat.android.client.models.Filters
import io.getstream.chat.android.client.models.User
import io.getstream.chat.android.livedata.ChatDomain
import io.getstream.chat.android.ui.avatar.AvatarView
import io.getstream.chat.android.ui.channel.list.header.viewmodel.ChannelListHeaderViewModel
import io.getstream.chat.android.ui.channel.list.header.viewmodel.bindView
import io.getstream.chat.android.ui.channel.list.viewmodel.ChannelListViewModel
import io.getstream.chat.android.ui.channel.list.viewmodel.bindView
import io.getstream.chat.android.ui.channel.list.viewmodel.factory.ChannelListViewModelFactory

class ChannelFragment : Fragment() {
    private val args: ChannelFragmentArgs by navArgs()

    private var _binding: FragmentChannelBinding? = null
    private val binding get() = _binding!!

    private val client = ChatClient.instance()
    private lateinit var user: User

    // sjxdjdjc
    @SuppressLint("WrongConstant")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentChannelBinding.inflate(inflater, container, false)

        setUpUser()
        setUpChannel()
        setUpDrawer()

        // to delete the channel
        binding.channelsView.setChannelDeleteClickListener { channel ->
            deleteChannel(channel)
        }

        // to go to chat fragment
        binding.channelsView.setChannelItemClickListener{
            channel->
            val action=ChannelFragmentDirections.actionChannelFragmentToChatFragment(channel.cid)
            findNavController().navigate(action)
        }

        //action button - navigation to user
        binding.channelListHeaderView.setOnActionButtonClickListener{
            findNavController().navigate(R.id.action_channelFragment_to_usersFragment)
        }

        // clicking on avatar to open drawer
        binding.channelListHeaderView.setOnUserAvatarClickListener {
            binding.drawerLayout.openDrawer(Gravity.START)
        }

        return binding.root
    }

    private fun setUpDrawer() {
        binding.navigationView.setNavigationItemSelectedListener { menuItem ->
            if (menuItem.itemId == R.id.logout_menu) {
                logout()
            }
            false

        }

        val currentUser = client.getCurrentUser()!!
        val headerView = binding.navigationView.getHeaderView(0)
        val headerAvatar = headerView.findViewById<AvatarView>(R.id.avatarView)
        headerAvatar.setUserData(currentUser)
        val headerId = headerView.findViewById<TextView>(R.id.id_textView)
        headerId.text = currentUser.id
        val headerName = headerView.findViewById<TextView>(R.id.name_textView)
        headerName.text = currentUser.name
    }

    private fun deleteChannel(channel: Channel) {
        ChatDomain.instance().deleteChannel(channel.cid).enqueue { result ->
            if (result.isSuccess) {
                showToast("Channel: ${channel.name} removed!")
            } else {
                Log.e("ChannelFragment", result.error().message.toString())
            }
        }
    }

    private fun logout() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") { _, _ ->
            client.disconnect()
            findNavController().navigate(R.id.action_channelFragment_to_loginFragment)
            showToast("Logged out successfully")
        }

        builder.setNegativeButton("No") { _, _ ->
        }
        builder.setTitle("Logout?")
        builder.setMessage("Are you sure you want to logout?")
        builder.create().show()
    }

    private fun showToast(s: String) {
        Toast.makeText(
            requireContext(),
            s,
            Toast.LENGTH_SHORT
        ).show()
    }

    // create user object, and token (client)
    // to connect the user with same SDK
    private fun setUpUser() {

        if (client.getCurrentUser() == null) {
            // if user exists then it have name, country and image
            user = if (args.chatUser.firstName.contains("mausam")) {
                User(
                    id = args.chatUser.username,
                    extraData = mutableMapOf(
                        "name" to args.chatUser.firstName,
                        "country" to "India",
                        "image" to "https://media-exp1.licdn.com/dms/image/C4D03AQHSLoKdcEsFVA/profile-displayphoto-shrink_800_800/0/1610519403350?e=1640822400&v=beta&t=gWG8VsyaiAed1QO8joDLQD21yo_trTGs6v153WYlNGo"
                    )
                )
            }
            // otherwise just set the name
            else {
                User(
                    id = args.chatUser.username,
                    extraData = mutableMapOf(
                        "name" to args.chatUser.firstName
                    )
                )
            }
            // connect user with stream chat sdk -> enqueue will handle async in background  -> dev token - to create dev token/ or use token from backend
            // go to overview- authentication-> disable auth checks
            val token = client.devToken(user.id)
            client.connectUser(
                user,
                token
            ).enqueue { result ->
                if (result.isSuccess) {
                    Log.d("Channel Fragment", "Success Connecting the User")
                } else {
                    Log.d("Channel Fragment", result.error().message.toString())
                }
            }

        }

    }

    // display all channel in which user is present
    private fun setUpChannel() {
        //  filters in which user is present
        val filters = Filters.and(
            Filters.eq("type", "messaging"),
            Filters.`in`("members", listOf(client.getCurrentUser()!!.id))

        )

        val viewModelFactory = ChannelListViewModelFactory(
            filters,
            ChannelListViewModel.DEFAULT_SORT
        )

        // provide all necessary data
        val listViewModel: ChannelListViewModel by viewModels { viewModelFactory }
        val listHeaderViewModel: ChannelListHeaderViewModel by viewModels()
        listHeaderViewModel.bindView(
            binding.channelListHeaderView,
            viewLifecycleOwner
        )
        listHeaderViewModel.bindView(binding.channelListHeaderView, viewLifecycleOwner)
        listViewModel.bindView(binding.channelsView, viewLifecycleOwner)

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}

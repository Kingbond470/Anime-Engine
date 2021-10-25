package dev.kingbond.developerchat.ui.users

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import dev.kingbond.developerchat.R
import dev.kingbond.developerchat.adapter.UsersAdapter
import dev.kingbond.developerchat.databinding.FragmentUsersBinding
import io.getstream.chat.android.client.ChatClient
import io.getstream.chat.android.client.api.models.QueryUsersRequest
import io.getstream.chat.android.client.models.Filters
import io.getstream.chat.android.client.models.User

class UsersFragment : Fragment() {

    private var _binding: FragmentUsersBinding? = null
    private val binding get() = _binding!!

    private val usersAdapter by lazy { UsersAdapter() }

    private val client = ChatClient.instance()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // layout inflation
        _binding = FragmentUsersBinding.inflate(inflater, container, false)

        setHasOptionsMenu(true)
        //(activity as AppCompatActivity).setSupportActionBar(binding.toolbar)

        setUpToolbar()
        setUpRecyclerView()
        queryAllUsers()

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        //super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.users_menu, menu)
        val search = menu.findItem(R.id.menu_search)

        val searchView = search.actionView as? SearchView
        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {

                return false
            }

            override fun onQueryTextChange(query: String?): Boolean {

                if (query!!.isEmpty()) {
                    queryAllUsers()
                } else {
                    searchUser(query)
                }
                return true
            }

        })

        // to close
        searchView?.setOnCloseListener {
            queryAllUsers()
            false
        }

    }

    // to search specific user
    private fun searchUser(query: String) {
        val filters = Filters.and(
            Filters.autocomplete("id", query),
            Filters.ne("id", client.getCurrentUser()!!.id)
        )
        val request = QueryUsersRequest(
            filter = filters,
            offset = 0,
            limit = 100
        )

        client.queryUsers(request).enqueue { result ->
            if (result.isSuccess) {
                val users: List<User> = result.data()
                usersAdapter.setData(users)
            } else {
                Log.e("UsersFragment", result.error().message.toString())
            }
        }
    }

    // getting all the user except current user(me)
    private fun queryAllUsers() {
        val request = QueryUsersRequest(
            filter = Filters.ne("id", client.getCurrentUser()!!.id),
            offset = 0,
            limit = 100
        )
        client.queryUsers(request).enqueue { result ->
            if (result.isSuccess) {
                val users: List<User> = result.data()
                usersAdapter.setData(users)
            } else {
                Log.e("UsersFragment", result.error().message.toString())
            }
        }
    }

    private fun setUpRecyclerView() {
        binding.usersRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.usersRecyclerView.adapter = usersAdapter


    }

    private fun setUpToolbar() {
        binding.toolbar.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
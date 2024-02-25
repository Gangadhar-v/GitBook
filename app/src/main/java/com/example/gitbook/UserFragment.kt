package com.example.gitbook

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.gitbook.data.RepoAdapter
import com.example.gitbook.databinding.FragmentUserBinding
import com.example.gitbook.viewModel.UserViewModel
import www.sanju.zoomrecyclerlayout.ZoomRecyclerLayout


class UserFragment : Fragment() {
    private lateinit var userViewModel : UserViewModel
    private lateinit var binding: FragmentUserBinding
    private lateinit var repositories:String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentUserBinding.inflate(inflater,container,false)
        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val username = arguments?.getString("username").toString()


        userViewModel.getUser(username).observe(viewLifecycleOwner, Observer {
            binding.name.text = it.name
            Glide.with(this)
                .load(it?.avatar_url)
                .centerCrop()
                .into(binding.profile)
            binding.tvFollowers.text = it.followers.toString()
            binding.tvFollowing.text = it.following.toString()
            binding.tvRepo.text = it.public_repos.toString()
            binding.bio.text = it.bio
            binding.userProgressBar.visibility = View.GONE



        })

        userViewModel.getUserRepos(username).observe(viewLifecycleOwner, Observer {

            val repoAdapter = RepoAdapter(it)
            repositories = it.size.toString()

            val linearLayoutManager = ZoomRecyclerLayout(this@UserFragment.requireContext())
            binding.recyclerView.adapter = repoAdapter
            linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
            linearLayoutManager.reverseLayout = true
            linearLayoutManager.stackFromEnd = true
            binding.recyclerView.layoutManager = linearLayoutManager // Add your recycler view to this ZoomRecycler layout
            binding.userProgressBar.visibility = View.GONE
        })
    }

}
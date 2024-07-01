package com.rajnish.mydairy.uiFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.rajnish.mydairy.R
import com.rajnish.mydairy.adapter.PostAdapter
import com.rajnish.mydairy.adapter.interfaceForListner.UserProfileListener
import com.rajnish.mydairy.databinding.FragmentPostTagBinding
import com.rajnish.mydairy.databinding.FragmentUrlPostTagBinding
import com.rajnish.mydairy.mvvm.model.PostDetailsModel
import com.rajnish.mydairy.mvvm.model.PostTagModel

class UrlPostTagFragment : Fragment(R.layout.fragment_url_post_tag),UserProfileListener {
private lateinit var binding:FragmentUrlPostTagBinding

private lateinit var postDetailsModel:PostDetailsModel
private lateinit var postAdapter: PostAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentUrlPostTagBinding.bind(view)


        postDetailsModel= ViewModelProvider(this)[PostDetailsModel::class.java]
        postDetailsModel.getPostDetails(arguments?.getString("tag")!!)

        postDetailsModel.postDetailsLiveData.observe(viewLifecycleOwner){


            if (it != null) {
               postAdapter = PostAdapter(it.posts,this)
                binding.postDetailsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
                binding.postDetailsRecyclerView.adapter = postAdapter
            }

        }

    }

    override fun onUserProfileListener(id: String) {
       val action = UrlPostTagFragmentDirections.actionUrlPostTagFragmentToUserProfileFragment(id)
        findNavController().navigate(action)
    }
}

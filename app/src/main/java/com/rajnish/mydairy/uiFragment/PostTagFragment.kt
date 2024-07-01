package com.rajnish.mydairy.uiFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.rajnish.mydairy.R
import com.rajnish.mydairy.adapter.PostTagAdapter
import com.rajnish.mydairy.adapter.interfaceForListner.PostTagItemClickListner
import com.rajnish.mydairy.databinding.FragmentPostTagBinding
import com.rajnish.mydairy.db.PostTag
import com.rajnish.mydairy.mvvm.model.PostTagModel


class PostTagFragment : Fragment(R.layout.fragment_post_tag),PostTagItemClickListner {
        private lateinit var binding: FragmentPostTagBinding
        private lateinit var postTag: PostTag
        private lateinit var postTagModel: PostTagModel
        private lateinit var postTagAdapter: PostTagAdapter


        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)
            binding = FragmentPostTagBinding.bind(view)

            postTagModel = ViewModelProvider(this)[PostTagModel::class.java]

            postTagModel.getPostTag()
            postTagModel.postTagLiveData.observe(viewLifecycleOwner){
                postTagAdapter = PostTagAdapter(it!!,this)
                binding.postTagRecyclerView.adapter = postTagAdapter
                binding.postTagRecyclerView.layoutManager = LinearLayoutManager(requireContext())
            }

    }

    override fun onPostTagItemClickListner(url: String,tag:String) {

        val action = PostTagFragmentDirections.actionPostTagFragmentToUrlPostTagFragment(url,tag)
        findNavController().navigate(action)


    }


}

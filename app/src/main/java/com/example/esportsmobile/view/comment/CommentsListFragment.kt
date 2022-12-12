package com.example.esportsmobile.view.comment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.esportsmobile.R
import com.example.esportsmobile.databinding.FragmentCommentsListBinding
import com.example.esportsmobile.model.Comment
import com.example.esportsmobile.adapters.CommentItemAdapter
import com.example.esportsmobile.dao.CommentsDataSource
import com.google.firebase.firestore.FirebaseFirestore

class CommentsListFragment : Fragment(R.layout.fragment_comments_list) {

    private lateinit var binding : FragmentCommentsListBinding

    private lateinit var commentListView : RecyclerView
    private lateinit var commentItemAdapter: CommentItemAdapter

    private var commentList : MutableList<Comment> = ArrayList()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initCommentList()

        binding = FragmentCommentsListBinding.bind(view)

        commentListView = binding.recycleView
        commentListView.layoutManager = LinearLayoutManager(requireContext())
        commentListView.setHasFixedSize(true)

        initRecyclerView()
        setItemTeamListener()
    }


    private fun setItemTeamListener(){
        commentItemAdapter = CommentItemAdapter(commentList){
            val commentReadFragment = CommentReadFragment()
            val bundle = bundleOf(
                "id" to it.id,
                "author" to it.author
            )
            commentReadFragment.arguments = bundle
            parentFragmentManager.beginTransaction().apply {
                replace(R.id.fragment_comments, commentReadFragment)
                    .commit()
            }
        }

        commentListView.adapter = commentItemAdapter
    }

    private fun initRecyclerView(){


        commentListView.setHasFixedSize(true)
        commentListView.layoutManager = LinearLayoutManager(requireContext())

    }

    private fun initCommentList(){
        commentList = CommentsDataSource().createUsersList()
    }
}
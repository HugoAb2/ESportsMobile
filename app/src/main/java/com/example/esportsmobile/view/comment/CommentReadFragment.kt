package com.example.esportsmobile.view.comment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.os.bundleOf
import com.example.esportsmobile.R
import com.example.esportsmobile.databinding.FragmentCommentReadBinding
import com.example.esportsmobile.view.friend.FriendActivity
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore

class CommentReadFragment : Fragment(R.layout.fragment_comment_read) {

    private lateinit var binding : FragmentCommentReadBinding

    private lateinit var commentDoc : DocumentReference

    private lateinit var data : Bundle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        data = requireArguments()
        val commentId = data.getString("id").toString()
        commentDoc = FirebaseFirestore.getInstance().collection("Comments").document(commentId)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentCommentReadBinding.bind(view)

        bundlePageData(data)

        receiveCommentData()
    }

    private fun receiveCommentData(){
        commentDoc.addSnapshotListener{ comment, _ ->
            binding.apply {
                if (comment != null){
                    author.text = comment.getString("author")
                    target.text = comment.getString("target")
                    commentText.text = comment.getString("text")
                }
            }
        }
    }

    private fun bundlePageData(data : Bundle?){
        if (data != null) {
            binding.title.text = data.get("id").toString()
            binding.author.text = data.getString("author").toString()
        }
    }

    override fun onResume() {
        super.onResume()

        binding.author.setOnClickListener{
            val intent = Intent(requireContext(), FriendActivity::class.java)
            intent.putExtra("operation", "friend")
            intent.putExtra("userID", binding.title.text.toString())
            requireActivity().startActivity(intent)
        }

        binding.updateButton.setOnClickListener {
            val intent = Intent(requireContext(), CommentActivity::class.java)
            intent.putExtra("operation", "update")
            intent.putExtra("target", binding.title.text.toString())
        }
    }
}
package com.example.esportsmobile.view.comment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.esportsmobile.R
import com.example.esportsmobile.databinding.FragmentReadCommentBinding
import com.example.esportsmobile.view.friend.FriendActivity

class CommentReadFragment : Fragment(R.layout.fragment_read_comment) {

    private lateinit var binding : FragmentReadCommentBinding

    private lateinit var commentID : TextView
    private lateinit var commentAuthor : TextView
    private lateinit var commentTarget : TextView
    private lateinit var commentText : TextView
    private lateinit var backButton : Button

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentReadCommentBinding.bind(view)

        commentID = binding.title
        commentAuthor = binding.author
        commentTarget = binding.target
        commentText = binding.commentText
        backButton = binding.backButton

        val data = arguments
        initPageData(data)
    }

    private fun initPageData(data : Bundle?){
        if (data != null) {
            commentID.text = data.getString("id")
            commentAuthor.text = data.getString("author")
            commentTarget.text = data.getString("target")
            commentText.text = data.getString("text")
        }
    }

    override fun onResume() {
        super.onResume()

        commentAuthor.setOnClickListener{
            val intent = Intent(requireContext(), FriendActivity::class.java)
            intent.putExtra("operation", "friend")
            intent.putExtra("userID", "1")
            requireActivity().startActivity(intent)
        }

        backButton.setOnClickListener {
            Toast.makeText(requireContext(), "Back", Toast.LENGTH_SHORT).show()
        }
    }
}
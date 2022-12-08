package com.example.esportsmobile.view.comment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.esportsmobile.R
import com.example.esportsmobile.databinding.FragmentCommentsAddBinding

class CommentsAddFragment : Fragment(R.layout.fragment_comments_add) {

    private lateinit var binding : FragmentCommentsAddBinding

    private lateinit var target : TextView
    private lateinit var commentText : EditText
    private lateinit var addButton: Button
    private lateinit var cancelButton: Button

    private val viewModel = ""

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentCommentsAddBinding.bind(view)

        target = binding.title
        commentText = binding.commentText
        addButton = binding.addComment
        cancelButton = binding.cancelComment

        val data = arguments
        checkOperation(data)
    }

    private fun checkOperation(data : Bundle?){
        if (data!!.getString("operation") == "update"){
            val commentID = data.getString("target")
            target.text = commentID
            //firebase
        }
    }

    override fun onResume() {
        super.onResume()
        addButton.setOnClickListener {
            /*
            viewModel.addComment(target.text, commentText.text)
             */
        }
        cancelButton.setOnClickListener {
            /*
            commentText.text = ""
            transitionBack or finish activity
            */
        }
    }
}
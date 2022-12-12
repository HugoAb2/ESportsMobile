package com.example.esportsmobile.view.comment

import android.app.Activity.RESULT_OK
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.esportsmobile.R
import com.example.esportsmobile.databinding.FragmentCommentWriteBinding
import com.example.esportsmobile.model.Comment
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import java.util.UUID

class CommentWriteFragment : Fragment(R.layout.fragment_comment_write) {

    private lateinit var binding : FragmentCommentWriteBinding

    private lateinit var author : TextView
    private lateinit var target : TextView
    private lateinit var commentText : EditText
    private lateinit var addButton: Button
    private lateinit var cancelButton: Button

    private lateinit var comment : DocumentReference
    private lateinit var db: FirebaseFirestore
    private lateinit var userDoc : DocumentReference
    private val userID = FirebaseAuth.getInstance().currentUser?.uid.toString()

    private lateinit var data : Bundle

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentCommentWriteBinding.bind(view)
        db = FirebaseFirestore.getInstance()
        userDoc = db.collection("Users").document(userID)

        author = binding.author
        target = binding.title
        commentText = binding.commentText
        addButton = binding.addComment
        cancelButton = binding.cancelComment

        getUserName()

        data = requireArguments()
        checkOperation()
    }

    private fun getUserName() {
        userDoc.addSnapshotListener{ userDoc, _ ->
            if (userDoc != null) author.text = userDoc.getString("name").toString()
        }
    }

    private fun checkOperation(){
        if (data.getString("operation") == "update"){
            val commentID = data.getString("target")
            target.text = commentID
            if (commentID != null) {
                comment = FirebaseFirestore.getInstance().collection("Comments").document(commentID)
                comment.addSnapshotListener { comment, _ ->
                    target.text = comment?.getString("target")
                    commentText.setText(comment?.getString("text"))
                    addButton.text = "update"
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        if (addButton.text == "update"){
        addButton.setOnClickListener {
            val nComment = Comment(UUID.randomUUID().toString(), userID, target.text.toString(), commentText.text.toString())
            comment.update("text", nComment.text.toString())
            }
        }else{
            addButton.setOnClickListener {
                addComment()
                requireActivity().setResult(RESULT_OK)
                requireActivity().finish()
            }
        }

        cancelButton.setOnClickListener {
            requireActivity().finish()
        }
    }

    private fun saveReference(userID : String, targetID: String, commentID: String){
        db.collection("Users").document(userID).update("comments", FieldValue.arrayUnion(commentID))
        db.collection("Teams").document(targetID).update("comments", FieldValue.arrayUnion(commentID))
    }

    private fun addComment() {
        target.text = data.getString("target")
        val nComment = Comment(UUID.randomUUID().toString(), author.text.toString(), target.text.toString(), commentText.text.toString())
        val userMap = hashMapOf(
            "id" to nComment.id,
            "author" to nComment.author,
            "target" to nComment.target,
            "text" to nComment.text
        )

        db.collection("Comments").document(nComment.id).set(userMap)
            .addOnCompleteListener{
                saveReference(userID, nComment.target, nComment.id)
            }.addOnFailureListener {
                Toast.makeText(requireContext(), it.toString(), Toast.LENGTH_SHORT).show()
            }
    }

}
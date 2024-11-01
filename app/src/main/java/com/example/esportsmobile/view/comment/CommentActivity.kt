package com.example.esportsmobile.view.comment


import android.os.Bundle
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.example.esportsmobile.DrawerBaseActivity
import com.example.esportsmobile.R
import com.example.esportsmobile.databinding.ActivityCommentBinding

class CommentActivity : DrawerBaseActivity() {

    private lateinit var binding : ActivityCommentBinding

    private lateinit var tittle : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCommentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        tittle = binding.tittle

        val operation = intent.getStringExtra("operation") as String
        fragmentManager(operation)

        implementsNavDrawer()

    }

    private fun fragmentManager(operation: String){
        when(operation){
            "list" -> {
                when(intent.getBooleanExtra("userComments", true)) {
                    true -> {
                        showUserComments()
                    }
                    false -> {
                        showTeamComments()
                    }
                }
            }

            "add" -> {
                addComment(operation)
            }

            "update" -> {
                val target = intent.getStringExtra("target") as String
                tittle.text = "Comment ID:"
                val bundle = bundleOf(
                    "target" to target,
                    "operation" to operation
                )
                supportFragmentManager.commit {
                    setReorderingAllowed(true)
                    add<CommentWriteFragment>(R.id.fragment_comments, args = bundle)
                }
            }
        }
    }

    private fun showUserComments(){
        tittle.text = "User Section"
        val bundle = bundleOf(
            "edit" to true
        )
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            add<CommentsListFragment>(R.id.fragment_comments, args = bundle)
        }
    }

    private fun showTeamComments(){
        val target = intent.getStringExtra("target") as String
        tittle.text = target
        val bundle = bundleOf(
            "target" to target
        )
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            add<CommentsListFragment>(R.id.fragment_comments, args = bundle)
        }
    }

    private fun addComment(operation : String){
        val target = intent.getStringExtra("target") as String
        tittle.text = target
        val bundle = bundleOf(
            "target" to target,
            "operation" to operation
        )
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            add<CommentWriteFragment>(R.id.fragment_comments, args = bundle)
        }
    }


    private fun implementsNavDrawer(){
        allocateActivityTittle("Comments")
        initNavUser()
    }
}
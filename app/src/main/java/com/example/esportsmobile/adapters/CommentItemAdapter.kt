package com.example.esportsmobile.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.esportsmobile.databinding.ItemCommentBinding
import com.example.esportsmobile.model.Comment

class CommentItemAdapter(private val commentItemList: MutableList<Comment>, private val onItemClicked: (Comment)->Unit) : RecyclerView.Adapter<CommentItemAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val commentItemView = ItemCommentBinding.inflate(
            LayoutInflater.from(parent.context),parent, false
        )
        return MyViewHolder(commentItemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val comment = commentItemList[position]
        holder.apply {
            author.text = comment.author
            target.text = comment.target
            content.text = comment.text
            layout.setOnClickListener{
                onItemClicked(comment)
            }
        }
    }

    override fun getItemCount(): Int {
        return commentItemList.size
    }

    class MyViewHolder (binding : ItemCommentBinding): RecyclerView.ViewHolder(binding.root){
        val layout = binding.commentLayout
        val author = binding.author
        val content = binding.content
        val target = binding.target


    }

}
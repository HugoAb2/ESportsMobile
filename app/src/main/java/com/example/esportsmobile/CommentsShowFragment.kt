package com.example.esportsmobile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.esportsmobile.databinding.FragmentCommentsShowBinding
import com.example.esportsmobile.model.Comment
import com.example.esportsmobile.view.CommentItemAdapter



class CommentsShowFragment : Fragment(R.layout.fragment_comments_show) {

    private lateinit var binding : FragmentCommentsShowBinding

    private lateinit var commentItemAdapter: CommentItemAdapter
    private lateinit var commentListView : RecyclerView
    private var commentList : MutableList<Comment> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initCommentList()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentCommentsShowBinding.bind(view)

        initRecyclerView()
        setItemTeamListener()
    }

    private fun setItemTeamListener(){
        commentItemAdapter = CommentItemAdapter(commentList){
            val commentReadFragment = CommentReadFragment()
            val bundle = bundleOf(
                "id" to it.id,
                "author" to it.author,
                "target" to it.target,
                "text" to it.text
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

        commentListView = binding.recycleView
        commentListView.setHasFixedSize(true)
        commentListView.layoutManager = LinearLayoutManager(requireContext())

    }

    private fun initCommentList(){
        //commentList = LeagueTeamIconsDataSource.createCBLOLDataSet()
        commentList.apply {
            add(Comment(generateID(),"Luis", "Loud", "nkasjdaskfhnaofhnefhn ajopdjaopfhnalfhn aopfhaneofphnapfhnafan aolajfçljafpájflanf aopfjapfjafpmaf" ))
            add(Comment(generateID(),"Mario", "Loud", "asodjap´jmapfjap´fjap´fjpadjap´d aosjldpç´jdpa~´djpãjd aoldnkalnfafnoaçdmapdjs~´apda adnaldna´~pdja´~djásjd´pad" ))
            add(Comment(generateID(),"Luana", "Pain", "asjdapsdjap´sdja´psdja´psda´pdjasp´d" ))
            add(Comment(generateID(),"Priscila", "SKT", "nice team" ))
            add(Comment(generateID(),"Tomas", "Miners", "bad team" ))
            add(Comment(generateID(),"Joao", "Birula", "sopdk´pakdáwkdá~wkd[´ka[dkásdkadásd" ))
        }
    }

    private fun generateID(): String{
        return (commentList.size + 1).toString()
    }
}
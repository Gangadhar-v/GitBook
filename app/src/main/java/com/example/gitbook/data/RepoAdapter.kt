package com.example.gitbook.data

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.gitbook.R

class RepoAdapter(val repos:RepoResponse):Adapter<RepoAdapter.RepoViewHolder>() {

    inner class RepoViewHolder(item: View): RecyclerView.ViewHolder(item){
        val repoTitle = item.findViewById<TextView>(R.id.tv_repo_title)
        val repoCreatedDate = item.findViewById<TextView>(R.id.tv_createdDate)
        val repoStargaze = item.findViewById<TextView>(R.id.tv_stargaze)
        val repoLanguage = item.findViewById<TextView>(R.id.tv_language)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        val item = LayoutInflater.from(parent.context).inflate(R.layout.repo_item,parent,false)
        return RepoViewHolder(item)
    }

    override fun getItemCount(): Int {
        return repos.size
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        val repo = repos[position]
        holder.repoTitle.text = repo.name
        holder.repoCreatedDate.text = repo.created_at
        holder.repoStargaze.text = repo.stargazers_count.toString()
        holder.repoLanguage.text = repo.language


        holder.itemView.setOnClickListener {
            val bundle = Bundle()
            val url = repo.html_url
            bundle.putString("repoUrl",url)
            it.findNavController().navigate(R.id.action_userFragment_to_repoDetailFragment,bundle)
        }
    }

}
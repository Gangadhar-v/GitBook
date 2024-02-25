package com.example.gitbook

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.gitbook.databinding.FragmentRepoDetailBinding

class RepoDetailFragment : Fragment() {
    private lateinit var binding: FragmentRepoDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRepoDetailBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val url = arguments?.getString("repoUrl")
        if(url != null){
            binding.detailedWebView.apply{
                settings.javaScriptEnabled = true
                settings.userAgentString = "Mozilla/5.0 (iPhone; U; CPU like Mac OS X; en) AppleWebKit/420+ (KHTML, like Gecko) Version/3.0 Mobile/1A543a Safari/419.3"
                webViewClient = object: WebViewClient(){
                    override fun onPageFinished(view: WebView?, url: String?) {
                        super.onPageFinished(view, url)
                        binding.repoProgressBar.visibility = View.GONE
                        binding.detailedWebView.visibility = View.VISIBLE
                    }
                }
                binding.detailedWebView.loadUrl(url)
            }

        }
    }


}
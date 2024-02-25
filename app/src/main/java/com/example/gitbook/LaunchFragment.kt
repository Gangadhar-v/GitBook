package com.example.gitbook

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.CheckConnection
import com.example.gitbook.databinding.FragmentLaunchBinding
import com.example.gitbook.viewModel.UserViewModel
import www.sanju.motiontoast.MotionToast
import www.sanju.motiontoast.MotionToastStyle


class LaunchFragment : Fragment() {
    private lateinit var binding : FragmentLaunchBinding
    private lateinit var userViewModel : UserViewModel
    private lateinit var checkConnection: CheckConnection



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLaunchBinding.inflate(inflater,container,false)
        // Inflate the layout for this fragment
        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        checkConnection = CheckConnection(requireActivity().application)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.editText.addTextChangedListener {
            if(it?.count()!! >0)
                binding.editTextLyt.error = null
        }
        checkConnection.observe(viewLifecycleOwner, Observer {
            if(it){
                searchQuery()
            }else{
                binding.noInternet.visibility = View.VISIBLE
                MotionToast.createToast(
                    this@LaunchFragment.requireActivity(),
                    "Info",
                    "No Internet ☹️",
                    MotionToastStyle.NO_INTERNET,
                    MotionToast.GRAVITY_BOTTOM,
                    MotionToast.LONG_DURATION,
                    ResourcesCompat.getFont(
                        this@LaunchFragment.requireActivity(),
                        www.sanju.motiontoast.R.font.helvetica_regular
                    )
                )
            }
        })

    }

    override fun onResume() {
        super.onResume()
        checkConnection.observe(viewLifecycleOwner, Observer {
            if(it){
                searchQuery()
                binding.noInternet.visibility = View.GONE
            }else{

                binding.noInternet.visibility = View.VISIBLE
                MotionToast.createToast(
                    this@LaunchFragment.requireActivity(),
                    "Info",
                    "No Internet ☹️",
                    MotionToastStyle.NO_INTERNET,
                    MotionToast.GRAVITY_BOTTOM,
                    MotionToast.LONG_DURATION,
                    ResourcesCompat.getFont(
                        this@LaunchFragment.requireActivity(),
                        www.sanju.motiontoast.R.font.helvetica_regular
                    )
                )
            }
        })
    }

    override fun onPause() {
        super.onPause()

        checkConnection.observe(viewLifecycleOwner, Observer {
            if(it){
                searchQuery()
                binding.noInternet.visibility = View.GONE
            }else{

                binding.noInternet.visibility = View.VISIBLE
                MotionToast.createToast(
                    this@LaunchFragment.requireActivity(),
                    "Info",
                    "No Internet ☹️",
                    MotionToastStyle.NO_INTERNET,
                    MotionToast.GRAVITY_BOTTOM,
                    MotionToast.LONG_DURATION,
                    ResourcesCompat.getFont(
                        this@LaunchFragment.requireActivity(),
                        www.sanju.motiontoast.R.font.helvetica_regular
                    )
                )
            }
        })
    }

    fun searchQuery() {

        binding.shareBtn.setOnClickListener { btn ->
            binding.launchProgressBar.visibility = View.VISIBLE
            val username = binding.editText.text.toString()
            if (username.isNotEmpty()) {
                userViewModel.getUser(username).observe(viewLifecycleOwner, Observer {

                    val bundle = Bundle()
                    bundle.putString("username", username)
                    btn.findNavController()
                        .navigate(R.id.action_launchFragment_to_userFragment, bundle)

                })
            } else {
                binding.launchProgressBar.visibility = View.INVISIBLE
                binding.editTextLyt.error = "Enter this field"
                MotionToast.createToast(
                    this@LaunchFragment.requireActivity(),
                    "Warning",
                    "Fill this field ☹️",
                    MotionToastStyle.ERROR,
                    MotionToast.GRAVITY_BOTTOM,
                    MotionToast.LONG_DURATION,
                    ResourcesCompat.getFont(
                        this@LaunchFragment.requireActivity(),
                        www.sanju.motiontoast.R.font.helvetica_regular
                    )
                )
            }
        }
    }
}
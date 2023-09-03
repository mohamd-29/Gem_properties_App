package com.app.gemproperties.fragments.firstpage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import androidx.navigation.findNavController
import com.app.gemproperties.R
import com.app.gemproperties.databinding.FragmentFirstPageBinding
import com.app.gemproperties.databinding.FragmentLogBinding
import com.app.gemproperties.fragments.loginf.LogFragment


class FirstPage : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentFirstPageBinding>(
            inflater, R.layout.fragment_first_page, container, false
        )
        val fm : FragmentManager =childFragmentManager
        binding.RegisterBtn.setOnClickListener {
view?.findNavController()?.navigate(FirstPageDirections.actionFirstPageToListingFragment())
        }

        binding.LoginBtn.setOnClickListener {
            val dialog = LogFragment ()
            dialog.show(fm,"log fragment")
        }
        return binding.root
    }

    }

package com.app.gemproperties.fragments.loginf

import android.app.Dialog
import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.navigation.findNavController
import com.app.gemproperties.R
import com.app.gemproperties.databinding.FragmentLogBinding
import com.app.gemproperties.databinding.FragmentRegisterBinding
import com.app.gemproperties.fragments.firstpage.FirstPageDirections

class LogFragment :  DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = Dialog(requireContext())
        dialog.window?.apply {
            setGravity(Gravity.BOTTOM)
            // set other properties like width, height, etc.
            setLayout(600, 400)
            dialog?.window?.setBackgroundDrawableResource(R.drawable.dg)
        }
        return dialog
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentLogBinding>(
            inflater, R.layout.fragment_log, container, false
        )

        binding.logBtn.setOnClickListener {
            view?.findNavController()?.navigate(LogFragmentDirections.actionLogFragmentToListingFragment())

        }
        return binding.root
    }
    override fun onStart() {
        super.onStart()
        val dialog = dialog
        if (dialog != null) {
            val width = ViewGroup.LayoutParams.MATCH_PARENT
            val height = 1000
            dialog.window!!.setLayout(width, height)
        }
    }
    }
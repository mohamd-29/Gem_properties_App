package com.app.gemproperties.fragments.listings

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.app.gemproperties.R
import com.app.gemproperties.databinding.FragmentAddBinding
import retrofit2.HttpException
import java.io.IOException


class AddFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentAddBinding>(
            inflater, R.layout.fragment_add, container, false
        )



            binding.button.setOnClickListener {
                lifecycleScope.launchWhenCreated {
                    var comp = binding.checkBox.isChecked
                    var name = binding.editTextTextPersonName.text.toString()
                    val newHouse=House(comp, 0, name )
                    val response = try {
                        RetrofitInstance.api.createHouse(newHouse)
                    } catch (e: IOException) {
                        Log.e(TAG, "IOException, you might not have internet connection")

                        return@launchWhenCreated
                    } catch (e: HttpException) {
                        Log.e(TAG, "HttpException, unexpected response")

                        return@launchWhenCreated
                    }
                    if (response.isSuccessful && response.body() != null) {

                    } else {
                        Log.e(TAG, "Response not successful")
                    }
                }
            }

        return binding.root

    }
}



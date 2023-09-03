package com.app.gemproperties.fragments.listings

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.app.gemproperties.R
import com.app.gemproperties.databinding.FragmentListingBinding
import com.app.gemproperties.databinding.FragmentUpdateBinding
import retrofit2.HttpException
import java.io.IOException


class UpdateFragment : Fragment() {
    private val args by navArgs<UpdateFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentUpdateBinding>(
            inflater, R.layout.fragment_update, container, false
        )
        binding.editTextTextPersonName2.setText(args.curHouse.title)
        binding.checkBox2.isChecked= args.curHouse.complete

        binding.updateBtn.setOnClickListener {


            lifecycleScope.launchWhenCreated {
                var comp = binding.checkBox2.isChecked
                var name = binding.editTextTextPersonName2.text.toString()
                val updatedHouse=House(comp, 0, name )
                val response = try {
                    RetrofitInstance.api.updateTask(args.curHouse.id,updatedHouse)
                } catch (e: IOException) {
                    Log.e(ContentValues.TAG, "IOException, you might not have internet connection")

                    return@launchWhenCreated
                } catch (e: HttpException) {
                    Log.e(ContentValues.TAG, "HttpException, unexpected response")

                    return@launchWhenCreated
                }
                if (response.isSuccessful && response.body() != null) {

                } else {
                    Log.e(ContentValues.TAG, "Response not successful")
                }
            }
            view?.findNavController()?.navigate(UpdateFragmentDirections.actionUpdateFragmentToListingFragment())

        }

        binding.deleteBtn.setOnClickListener {



            lifecycleScope.launchWhenCreated {


                val response = try {
                    RetrofitInstance.api.deleteTask(args.curHouse.id)
                } catch (e: IOException) {
                    Log.e(ContentValues.TAG, "IOException, you might not have internet connection")

                    return@launchWhenCreated
                } catch (e: HttpException) {
                    Log.e(ContentValues.TAG, "HttpException, unexpected response")

                    return@launchWhenCreated
                }
                if (response.isSuccessful && response.body() != null) {

                } else {
                    Log.e(ContentValues.TAG, "Response not successful")
                }
            }

            view?.findNavController()?.navigate(UpdateFragmentDirections.actionUpdateFragmentToListingFragment())

        }

        return  binding.root
    }


}
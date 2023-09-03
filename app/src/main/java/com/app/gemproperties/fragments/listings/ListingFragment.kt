package com.app.gemproperties.fragments.listings

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.gemproperties.R
import com.app.gemproperties.databinding.FragmentListingBinding

import com.app.gemproperties.databinding.FragmentRegisterBinding
import retrofit2.HttpException
import java.io.IOException
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.OkHttpClient


class ListingFragment : Fragment() {
    private lateinit var radapter: ItemAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentListingBinding>(
            inflater, R.layout.fragment_listing, container, false
        )
        radapter=ItemAdapter()
        val recyclerView = binding.recyclerView
        recyclerView.adapter = radapter
        recyclerView.layoutManager = LinearLayoutManager(this.context)

        lifecycleScope.launchWhenCreated {
            binding.ProgressBar.isVisible = true
            val response = try {
                RetrofitInstance.api.getHouse()
            } catch (e: IOException) {
                Log.e(TAG, "IOException, you might not have internet connection")
                binding.ProgressBar.isVisible = false
                return@launchWhenCreated
            } catch (e: HttpException) {
                Log.e(TAG, "HttpException, unexpected response")
                binding.ProgressBar.isVisible = false
                return@launchWhenCreated
            }
            if (response.isSuccessful && response.body() != null) {
               radapter.houses = response.body()!!
            } else {
                Log.e(TAG, "Response not successful")
            }
            if ( response.body() == null )
            {
                Log.e(TAG, "Response null")
            }
            binding.ProgressBar.isVisible = false

//
//            try {
//                val response = RetrofitInstance.api.getHousetwo()
//                // Find the item you want to update
//                val itemToUpdate = response.find { it.id == 7 }
//                // Update the complete boolean
//                itemToUpdate?.complete = true
//                // Send the updated data to the server
//                val updatedResponse = RetrofitInstance.api.updateExampleData(itemToUpdate?.id ?: 0, itemToUpdate!!)
//                // Handle the response here
//                val updatedComplete = updatedResponse.complete
//
//            } catch (e: Exception) {
//                // Handle any errors here
//            }
        }


binding.change.setOnClickListener {
    view?.findNavController()?.navigate(ListingFragmentDirections.actionListingFragmentToAddFragment())
}

return binding.root
    }

//    private fun setupRecyclerView() = view?.findViewById<RecyclerView>(R.id.recyclerView)?.apply {
//        radapter = ItemAdapter()
//        adapter = radapter
//        layoutManager = LinearLayoutManager(this.context)
//    }


}
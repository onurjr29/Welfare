package com.works.solutionchallange2024.view

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.works.solutionchallange2024.R
import com.works.solutionchallange2024.common.GoogleMapsFragment
import com.works.solutionchallange2024.databinding.ActivityLocationBinding

class LocationActivity() : AppCompatActivity() {
    private lateinit var binding:ActivityLocationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityLocationBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}
package com.example.myapplication3.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.HapticFeedbackConstants
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication3.databinding.ActivityRecyclerViewBinding
import com.example.myapplication3.model.MyObjectForRecyclerView
import com.example.myapplication3.model.ObjectDataFooterSample
import com.example.myapplication3.model.ObjectDataHeaderSample
import com.example.myapplication3.model.ObjectDataSample

class RecyclerViewActivity : AppCompatActivity() {

    private lateinit var binding : ActivityRecyclerViewBinding
    private lateinit var mAdapter: CarsNameAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecyclerViewBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // Create the instance of adapter
        mAdapter = CarsNameAdapter { item, view ->
            onItemClick(item, view)
        }



        // We define the style
        binding.recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)


        // We set the adapter to recycler view
        binding.recyclerView.adapter = mAdapter


        // Generate data and give it to adapter
        mAdapter.submitList(generateFakeData())
    }

    private fun generateFakeData(): MutableList<MyObjectForRecyclerView> {
        val result = mutableListOf<MyObjectForRecyclerView>()
        // Create data raw
        val speeds = mutableListOf(
            ObjectDataSample("Renault Espace II", 169f),
            ObjectDataSample("Renault Espace F1", 310f),
            ObjectDataSample("Benz Velo", 20f),
            ObjectDataSample("Mercedes-Benz 300SL", 245.5f),
            ObjectDataSample("Iso Grifo GL 365", 259f),
            ObjectDataSample("Aston Martin DB4 GT", 245f),
            ObjectDataSample("AC Cobra Mk III 427", 266f),
        )
        speeds.sortBy { it.maxSpeed } //Sort by speed value
        speeds.groupBy {
            // Split in 2 list, modulo and not
            it.maxSpeed >= 200
        }.forEach { (isFast, items) ->
            // For each mean for each list split
            // Here we have a map (key = isModulo) and each key have a list of it's items
            if(isFast) {
                result.add(ObjectDataHeaderSample("Voitures rapide : >= 200km/h "))
                result.addAll(items)
                result.add(ObjectDataFooterSample("Voitures rapide fin"))
            }
            else {
                result.add(ObjectDataHeaderSample("Voitures lente : < 200km/h"))
                result.addAll(items)
                result.add(ObjectDataFooterSample("Voitures lente fin"))
            }

        }
        return result
    }

    private fun onItemClick(objectDataSample: ObjectDataSample, view : View) {
        view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY)
        Toast.makeText(this, objectDataSample.maxSpeed.toString()+" km/h", Toast.LENGTH_SHORT).show()
    }



}
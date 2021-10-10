package com.example.myapplication3.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication3.databinding.ItemCustomRecyclerBinding
import com.example.myapplication3.databinding.ItemCustomRecyclerHeaderBinding
import com.example.myapplication3.databinding.ItemCustomRecyclerFooterBinding
import com.example.myapplication3.model.MyObjectForRecyclerView
import com.example.myapplication3.model.ObjectDataFooterSample
import com.example.myapplication3.model.ObjectDataHeaderSample
import com.example.myapplication3.model.ObjectDataSample

private val diffItemUtils = object : DiffUtil.ItemCallback<MyObjectForRecyclerView>() {


    override fun areItemsTheSame(oldItem: MyObjectForRecyclerView, newItem: MyObjectForRecyclerView): Boolean {
        return oldItem == newItem
    }


    override fun areContentsTheSame(oldItem: MyObjectForRecyclerView, newItem: MyObjectForRecyclerView): Boolean {
        return oldItem == newItem
    }
}


class CarsNameAdapter(
    private val onItemClick: (quoteUi: ObjectDataSample, view: View) -> Unit,
) : ListAdapter<MyObjectForRecyclerView, RecyclerView.ViewHolder>(diffItemUtils) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        when (viewType) {
            MyItemType.ROW.type -> {
                CarsSpeedViewHolder(
                    ItemCustomRecyclerBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    ), onItemClick
                )
            }


            MyItemType.HEADER.type -> {
                CarsSpeedHeaderViewHolder(
                    ItemCustomRecyclerHeaderBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }

            MyItemType.FOOTER.type -> {
                CarsSpeedFooterViewHolder(
                    ItemCustomRecyclerFooterBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
            else -> throw RuntimeException("Wrong view type received $viewType")
        }



    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            MyItemType.ROW.type -> (holder as CarsSpeedViewHolder).bind(getItem(position) as ObjectDataSample)
            MyItemType.HEADER.type -> (holder as CarsSpeedHeaderViewHolder).bind(getItem(position) as ObjectDataHeaderSample)
            MyItemType.FOOTER.type -> (holder as CarsSpeedFooterViewHolder).bind(getItem(position) as ObjectDataFooterSample)
            else -> throw RuntimeException("Wrong view type received ${holder.itemView}")
        }

    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is ObjectDataSample -> MyItemType.ROW.type
            is ObjectDataHeaderSample -> MyItemType.HEADER.type
            is ObjectDataFooterSample -> MyItemType.FOOTER.type
        }
    }


}



class CarsSpeedViewHolder(
    private val binding: ItemCustomRecyclerBinding,
    onItemClick: (objectDataSample: ObjectDataSample, view: View) -> Unit
) : RecyclerView.ViewHolder(binding.root) {


    private lateinit var ui: ObjectDataSample


    init {
        binding.root.setOnClickListener {
            onItemClick(ui, itemView)
        }
    }


    fun bind(objectDataSample: ObjectDataSample) {
        ui = objectDataSample
        binding.itemRecyclerViewVersionName.text = objectDataSample.cars
        binding.itemRecyclerViewVersionCode.text = "${objectDataSample.maxSpeed} km/h"
    }
}


class CarsSpeedHeaderViewHolder(
    private val binding : ItemCustomRecyclerHeaderBinding
) : RecyclerView.ViewHolder(binding.root){

    fun bind(objectDataHeaderSample: ObjectDataHeaderSample) {
        binding.itemRecyclerViewHeader.text = objectDataHeaderSample.headerText
    }
}

class CarsSpeedFooterViewHolder(
    private val binding : ItemCustomRecyclerFooterBinding
) : RecyclerView.ViewHolder(binding.root){

    fun bind(objectDataFooterSample: ObjectDataFooterSample) {
        binding.itemRecyclerViewHeader.text = objectDataFooterSample.footerText
    }
}

enum class MyItemType(val type: Int){
    ROW(0),
    HEADER(1),
    FOOTER(2);
}
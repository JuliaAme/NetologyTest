package com.mikevarl.netology

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.progressindicator.CircularProgressIndicator
import com.mikevarl.netology.viewadapter.DirectionsAdapter
import com.mikevarl.netology.viewadapter.DirectionsHeaderAdapter
import com.mikevarl.netology.viewmodel.DirectionListViewModel
import com.mikevarl.netology.viewmodel.DirectionListViewModelFactory

class MainActivity : AppCompatActivity() {
    private val directionListViewModel: DirectionListViewModel by viewModels { DirectionListViewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val progressIndicator: CircularProgressIndicator = findViewById(R.id.progress_indicator)
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                recyclerView.context,
                DividerItemDecoration.VERTICAL
            )
        )
        val directionsAdapter = DirectionsAdapter()
        val headerAdapter = DirectionsHeaderAdapter()
        recyclerView.adapter = ConcatAdapter(headerAdapter, directionsAdapter)

        directionListViewModel.directionsLoaded.observe(this) {
            if (it) {
                progressIndicator.visibility = View.GONE
                recyclerView.visibility = View.VISIBLE
            } else {
                progressIndicator.visibility = View.VISIBLE
                recyclerView.visibility = View.GONE
            }
        }
        directionListViewModel.directions.observe(this) {
            directionsAdapter.submitList(it)
        }
    }
}

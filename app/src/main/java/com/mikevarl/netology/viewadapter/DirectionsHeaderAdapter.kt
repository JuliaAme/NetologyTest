package com.mikevarl.netology.viewadapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mikevarl.netology.R

/**
 * Boilerplate класс. Он необходим для корректной работы [RecyclerView] но не содержит какой-то логики.
 * В нашем RecyclerView есть всего один элемент типа [DirectionsHeaderViewHolder], и вся логика отрисовки содержится в [R.layout.direction_list_header_item]
 */
class DirectionsHeaderAdapter :
    RecyclerView.Adapter<DirectionsHeaderAdapter.DirectionsHeaderViewHolder>() {

    class DirectionsHeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DirectionsHeaderViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.direction_list_header_item, parent, false)
        return DirectionsHeaderViewHolder(view)
    }

    override fun getItemCount(): Int {
        return 1
    }

    override fun onBindViewHolder(holder: DirectionsHeaderViewHolder, position: Int) {

    }
}

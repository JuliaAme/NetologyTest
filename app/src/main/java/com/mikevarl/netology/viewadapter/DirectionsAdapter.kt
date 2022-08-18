package com.mikevarl.netology.viewadapter

import android.graphics.Color
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.OvalShape
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mikevarl.netology.R
import com.mikevarl.netology.data.model.Direction
import java.lang.IllegalArgumentException

private const val BADGE_SIZE = 100

class DirectionsAdapter :
    ListAdapter<Direction, DirectionsAdapter.DirectionViewHolder>(DirectionDiffCallback) {

    class DirectionViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        private val directionTitleTextView: TextView = itemView.findViewById(R.id.direction_title)
        private val directionSubtitleTextView: TextView =
            itemView.findViewById(R.id.direction_subtitle)
        private val directionBadgeView: TextView = itemView.findViewById(R.id.direction_badge)
        private val badgeDrawable = ShapeDrawable(OvalShape())


        fun bind(direction: Direction) {
            directionTitleTextView.text = direction.title
            val amount = direction.getCoursesAmount()
            directionSubtitleTextView.text = itemView.resources.getQuantityString(
                R.plurals.direction_cources_amount,
                amount,
                amount
            )

            try {
                badgeDrawable.paint.color = Color.parseColor(direction.badge.bgColor)
            } catch (e: IllegalArgumentException) {
                badgeDrawable.paint.color = Color.LTGRAY
            }
            badgeDrawable.intrinsicHeight = BADGE_SIZE
            badgeDrawable.intrinsicWidth = BADGE_SIZE
            directionBadgeView.background = badgeDrawable
            directionBadgeView.text = direction.badge.text
            try {
                directionBadgeView.setTextColor(Color.parseColor(direction.badge.color))
            } catch (e: IllegalArgumentException) {
                directionBadgeView.setTextColor(Color.BLACK)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DirectionViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.direction_item, parent, false)
        return DirectionViewHolder(view)
    }

    override fun onBindViewHolder(holder: DirectionViewHolder, position: Int) {
        val direction = getItem(position)
        holder.bind(direction)
    }
}

object DirectionDiffCallback : DiffUtil.ItemCallback<Direction>() {
    override fun areItemsTheSame(oldItem: Direction, newItem: Direction): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Direction, newItem: Direction): Boolean {
        return oldItem.id == newItem.id
    }
}

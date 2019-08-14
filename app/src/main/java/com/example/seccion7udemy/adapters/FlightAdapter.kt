package com.example.seccion7udemy.adapters

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.seccion7udemy.R
import com.example.seccion7udemy.inflate
import com.example.seccion7udemy.listeners.RecyclerFlightListener
import com.example.seccion7udemy.loadByResource
import com.example.seccion7udemy.models.Flight
import kotlinx.android.synthetic.main.recycler_flight.view.*

class FlightAdapter (private val flights: List<Flight>, private  val listener: RecyclerFlightListener)
    : RecyclerView.Adapter<FlightAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(parent.inflate(R.layout.recycler_flight))

    override fun getItemCount() = flights.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(flights[position], listener)

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind(flight: Flight, listener: RecyclerFlightListener) = with(itemView){
            textViewCityName.text = flight.city
            imageViewBg.loadByResource(flight.imgResource)
            // click event
            setOnClickListener { listener.onClick(flight, adapterPosition) }
            buttonDelete.setOnClickListener { listener.onDelete(flight, adapterPosition) }
        }
    }
}
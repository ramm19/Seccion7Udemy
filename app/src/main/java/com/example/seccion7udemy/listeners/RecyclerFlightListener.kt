package com.example.seccion7udemy.listeners

import com.example.seccion7udemy.models.Flight

abstract class RecyclerFlightListener{

    abstract fun onClick(flight: Flight, position: Int)
    abstract fun onDelete(flight: Flight, position: Int)

}
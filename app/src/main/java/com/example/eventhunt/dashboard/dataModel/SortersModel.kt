package com.example.eventhunt.dashboard.dataModel

data class SortersModel(
    val DigitalEvents: List<Event?>?,
    val Events:  List<Event?>?,
    val Food:  List<Event?>?,
    val Sports:  List<Event?>?,
    val Theatre:  List<Event?>?,
    val Travel:  List<Event?>?,
    val Workshops:  List<Event?>?
)


data class Event(
    val display: String,
    val key: String,
    val type: String
)

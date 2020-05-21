package com.example.eventhunt.dashboard.dataModel

data class TextViewModel(
    val text: Text
)

data class Text(
    val DigitalEvents: DigitalEventsText,
    val Events: Events,
    val Food: FoodText,
    val Sports: Sports,
    val Theatre: TheatreText,
    val Travel: TravelText,
    val Workshops: Workshops
)

data class TheatreText(
    val all_description: String,
    val all_title: String,
    val pick_description: String,
    val pick_title: String,
    val seo_description: String,
    val seo_title: String
)
data class TravelText(
    val all_description: String,
    val all_title: String,
    val colour: String,
    val pick_description: String,
    val pick_title: String,
    val seo_description: String,
    val seo_title: String
)

data class Workshops(
    val all_description: String,
    val all_title: String,
    val colour: String,
    val pick_description: String,
    val pick_title: String,
    val seo_description: String,
    val seo_title: String
)


data class FoodText(
    val all_description: String,
    val all_title: String,
    val colour: String,
    val pick_description: String,
    val pick_title: String,
    val seo_description: String,
    val seo_title: String
)

data class Events(
    val all_description: String,
    val all_title: String,
    val colour: String,
    val pick_description: String,
    val pick_title: String,
    val seo_description: String,
    val seo_title: String
)

data class Sports(
    val all_description: String,
    val all_title: String,
    val pick_description: String,
    val pick_title: String,
    val seo_description: String,
    val seo_title: String
)

data class DigitalEventsText(
    val seo_description: String,
    val seo_title: String
)
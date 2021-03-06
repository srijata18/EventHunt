package com.example.eventhunt.dashboard.dataModel

data class FeaturedModel(
    val _id: String,
    val action_button_text: String,
    val applicable_filters: List<String>,
    val category_id: CategoryId,
    val city: String,
    val communication_strategy: String,
    val event_state: String,
    val favStats: FavStats,
    val group_id: GroupId,
    val horizontal_cover_image: String,
    val is_rsvp: Boolean,
    val min_price: Int,
    val min_show_start_time: Int,
    val model: String,
    val name: String,
    val popularity_score: Double,
    val price_display_string: String,
    val purchase_visibility: String,
    val slug: String,
    val tags: List<Tag>,
    val type: String,
    val venue_date_string: String,
    val venue_geolocation: VenueGeolocation,
    val venue_id: String,
    val venue_name: String,
    val vertical_cover_image: String
)


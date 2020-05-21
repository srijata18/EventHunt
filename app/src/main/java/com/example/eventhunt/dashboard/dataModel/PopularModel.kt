package com.example.eventhunt.dashboard.dataModel

data class PopularModel(
    val popular: List<Popular>
)

data class Popular(
    val _id: String?,
    val action_button_text: String?,
    val applicable_filters: List<String>?,
    val category_id: CategoryId?,
    val city: String?,
    val communication_strategy: String?,
    val event_state: String?,
    val favStats: FavStats?,
    val group_id: GroupId?,
    val horizontal_cover_image: String?,
    val is_rsvp: Boolean?,
    val min_price: Int?,
    val min_show_start_time: Int?,
    val model: String?,
    val name: String?,
    val popularity_score: Float?,
    val price_display_string: String?,
    val purchase_visibility: String?,
    val slug: String?,
    val tags: List<Tag>?,
    val type: String?,
    val venue_date_string: String?,
    val venue_geolocation: VenueGeolocation?,
    val venue_id: String?,
    val venue_name: String?,
    val vertical_cover_image: String?
)

data class Tag(
    val _id: String,
    val carousel_position: Int,
    val is_carousel: Boolean,
    val is_featured: Boolean,
    val is_pick: Boolean,
    val is_primary_interest: Boolean,
    val priority: Int,
    val tag_id: String
)
data class CategoryId(
    val _id: String,
    val display_details: DisplayDetails,
    val icon_img: String,
    val name: String
)

data class DisplayDetails(
    val colour: String,
    val seo_description: String,
    val seo_title: String
)

data class FavStats(
    val actualCount: Int,
    val prettyCount: Int,
    val target_id: String
)
data class GroupId(
    val _id: String,
    val display_details: DisplayDetails,
    val icon_img: String,
    val name: String
)

data class VenueGeolocation(
    val latitude: Int,
    val longitude: Int
)
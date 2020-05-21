package com.example.eventhunt.dashboard.dataModel

data class DatesModel(
    val banners: List<Banner>?,
    val dates: Dates?
)

data class Dates(
    val next_weekend: String?="",
    val next_weekend_date_string: String?="",
    val today: String?="",
    val today_date_string: String?="",
    val tomorrow: String?="",
    val tomorrow_date_string: String?="",
    val weekend: String?="",
    val weekend_date_string: String?=""
)

data class Banner(
    val _id: String?="",
    val category_id: CategoryIdDate?,
    val description: String?="",
    val display_details: DisplayDetailsDate?,
    val group_id: GroupIdDate?,
    val is_internal: Boolean?=false,
    val map_link: String?,
    val name: String?="",
    val priority: Int?=0,
    val type: String?="",
    val vertical_cover_image: String?=""
)

data class CategoryIdDate(
    val _id: String?="",
    val name: String?="",
    val slug: String?=""
)
data class DisplayDetailsDate(
    val link_slug: String?="",
    val link_type: String?=""
)

data class GroupIdDate(
    val _id: String?="",
    val name: String?="",
    val slug: String?=""
)
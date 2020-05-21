package com.example.eventhunt.dashboard.dataModel

data class EventDataModel(val filters: Filters?,
                          val groups: List<String>?,
                          val tags: List<String>?,
                          val sorters: SortersModel?,
                          val list: Lists?,
                          val popular: List<Popular>?,
                          val featured: List<FeaturedModel>?,
                          val picks: Picks?
)


data class Show(
    val display: String?,
    val key: String?
)

data class Filters(
    val DigitalEvents: DigitalEvents?,
    val Events: EventsFilter?,
    val Food: FoodFilter?,
    val Sports: SportsFilter?,
    val Theatre: TheatreFilter?,
    val Travel: TravelFilter?,
    val Workshops: WorkshopsFilter?
)
data class EventsFilter(
    val show: List<Show>?
)

data class DigitalEvents(
    val show: List<Show>?
)


data class FoodFilter(
    val show: List<Show>?
)
data class SportsFilter(
    val show: List<Show>?
)
data class TravelFilter(
    val show: List<Show>?
)
data class TheatreFilter(
    val show: List<Show>?
)
data class WorkshopsFilter(
    val show: List<Show>?
)
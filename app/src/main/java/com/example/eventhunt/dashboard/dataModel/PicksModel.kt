package com.example.eventhunt.dashboard.dataModel

data class PicksModel(
    val picks: Picks
)

data class Picks(
//    val masterList: MasterList,
    val categorywiseList: CategorywiseListOfPicks,
    val groupwiseList: GroupwiseListOfPicks
)

data class CategorywiseListOfPicks(
    val Comedy: List<String>,
    val Events: List<String>,
    val Food: List<String>,
    val Games: List<String>,
    val HealthandFitness: List<String>,
    val Music: List<String>,
    val OnlineCourse: List<String>,
    val Other: List<String>,
    val Screening: List<String>,
    val Workshops: List<String>
)

data class GroupwiseListOfPicks(
    val DigitalEvents: List<String>,
    val Events: List<String>,
    val Workshops: List<String>
)
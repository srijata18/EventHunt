package com.example.eventhunt.dashboard.dataModel
import kotlin.collections.List

data class Lists(
//    val masterList : MasterList,
    val categorywiseList: CategorywiseList,
    val groupwiseList: GroupwiseList
)

data class MasterList(val map : List<MasterType>)

data class MasterType(val data : Popular)

data class CategorywiseList(
    val Adventure: List<String>?,
    val AlternativeAndExperimental: List<String>?,
    val Art: List<String>?,
    val ArtsAndTheatre: List<String>,
    val Camping: List<String>,
    val Comedy: List<String>,
    val Conference: List<String>,
    val Cycling: List<String>,
    val Experiences: List<String>,
    val Games: List<String>,
    val GuidedWalks: List<String>,
    val Health: List<String>,
    val HealthAndFitness: List<String>,
    val Kids: List<String>,
    val Learn: List<String>,
    val Music: List<String>,
    val OnlineCourse: List<String>,
    val OpenMic: List<String>,
    val Storytelling: List<String>,
    val Talks: List<String>,
    val Theatre: List<String>,
    val Tour: List<String>,
    val Trek: List<String>,
    val Volunteer: List<String>,
    val Workshops: List<String>,
    val Yoga: List<String>
)

data class GroupwiseList(
    val DigitalEvents: List<String>,
    val Events: List<String>,
    val Food: List<String>,
    val Sports: List<String>,
    val Theatre: List<String>,
    val Travel: List<String>,
    val Workshops: List<String>
)
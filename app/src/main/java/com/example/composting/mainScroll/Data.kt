package com.example.composting.mainScroll


abstract class Data {
    abstract var viewType: Int
}

data class CompostCard (
    override var viewType: Int,
    var totalCompostEntries: Int,
    var turnDays: Int,
    var compostHealth: Int
) : Data()

data class AnalyticsCard (
    override var viewType: Int,
    var textData: String = ""
) : Data()

data class GameCard (
    override var viewType: Int,
    var coins: Int,
    var trophies: Int
) : Data()


data class UserCard (
    override var viewType: Int,
    var email:String,
    var totalCompostEntries: Int,
    var turnDays: Int,
    var turnDate: String,
    var compostHealth: Int,
    var coins: Int,
    var trophies: Int,
    var milestones: Int,
    var milestoneProgress: Int,
    var coinMultiplier: Double,
    var trophyMultiplier: Double,
    var milestoneMultiplier:Double,
    var carbonTotal: Int,
    var nitrogenTotal:Int,
    var liveTotal:Int
    ) : Data ()
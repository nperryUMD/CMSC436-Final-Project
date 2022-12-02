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

data class CalendarCard (
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
    var totalCompostEntries: Int,
    var turnDays: Int,
    var compostHealth: Int,
    var coins: Int,
    var trophies: Int
    ) : Data ()
package com.example.composting.mainScroll


class Datasource {
    fun load() : ArrayList<Data>{
        val dataList = ArrayList<Data>()
        dataList.add(CompostCard(RecyclerViewAdapter.VIEW_TYPE_ONE, 1, 2, 3))
        dataList.add(CalendarCard(RecyclerViewAdapter.VIEW_TYPE_THREE, "2. Hi! I am in View 2"))
        dataList.add(GameCard(RecyclerViewAdapter.VIEW_TYPE_FOUR, 0,0))
        dataList.add(AnalyticsCard(RecyclerViewAdapter.VIEW_TYPE_TWO, "4. Hi! I am in View 4"))
        return dataList
    }


}
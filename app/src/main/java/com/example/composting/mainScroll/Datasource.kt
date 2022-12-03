package com.example.composting.mainScroll


class Datasource {
    fun load() : ArrayList<Data>{
        val dataList = ArrayList<Data>()
        dataList.add(CompostCard(RecyclerViewAdapter.VIEW_COMPOST_BIN, 1, 2, 3))
        dataList.add(GameCard(RecyclerViewAdapter.VIEW_GAME_BIN, 0,0))
        dataList.add(AnalyticsCard(RecyclerViewAdapter.VIEW_ANALYTICS_BIN, "4. Hi! I am in View 4"))
        return dataList
    }


}
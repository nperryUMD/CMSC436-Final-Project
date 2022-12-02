package com.example.composting.detailScreens.classes

import com.example.composting.detailScreens.classes.Category.Companion.CATEGORY_VEG
import com.example.composting.detailScreens.classes.Category.Companion.CATEGORY_FOOD
import com.example.composting.detailScreens.classes.Category.Companion.CATEGORY_LIVE

class Datasource {
    fun load() : ArrayList<CompostItems>{
        val dataList = ArrayList<CompostItems>()
        dataList.add(CompostItems("VEG", CATEGORY_VEG,1))
        dataList.add(CompostItems("VEG", CATEGORY_VEG, 2))
        dataList.add(CompostItems("VEG", CATEGORY_VEG, 3))
        dataList.add(CompostItems("VEG", CATEGORY_VEG, 4))
        dataList.add(CompostItems("VEG", CATEGORY_VEG,1))
        dataList.add(CompostItems("VEG", CATEGORY_VEG, 2))
        dataList.add(CompostItems("VEG", CATEGORY_VEG, 3))
        dataList.add(CompostItems("VEG", CATEGORY_VEG, 4))
        dataList.add(CompostItems("VEG", CATEGORY_VEG,1))
        dataList.add(CompostItems("VEG", CATEGORY_VEG, 2))
        dataList.add(CompostItems("VEG", CATEGORY_VEG, 3))
        dataList.add(CompostItems("VEG", CATEGORY_VEG, 4))

        dataList.add(CompostItems("FOOD", CATEGORY_FOOD,1))
        dataList.add(CompostItems("FOOD", CATEGORY_FOOD, 1))
        dataList.add(CompostItems("FOOD", CATEGORY_FOOD, 1))
        dataList.add(CompostItems("FOOD", CATEGORY_FOOD, 1))
        dataList.add(CompostItems("FOOD", CATEGORY_FOOD,1))
        dataList.add(CompostItems("FOOD", CATEGORY_FOOD, 1))
        dataList.add(CompostItems("FOOD", CATEGORY_FOOD, 1))
        dataList.add(CompostItems("FOOD", CATEGORY_FOOD, 1))

        dataList.add(CompostItems("LIVE", CATEGORY_LIVE,1))
        dataList.add(CompostItems("LIVE", CATEGORY_LIVE, 1))
        dataList.add(CompostItems("LIVE", CATEGORY_LIVE, 1))
        dataList.add(CompostItems("LIVE", CATEGORY_LIVE, 1))
        dataList.add(CompostItems("LIVE", CATEGORY_LIVE,1))
        dataList.add(CompostItems("LIVE", CATEGORY_LIVE, 1))
        dataList.add(CompostItems("LIVE", CATEGORY_LIVE, 1))
        dataList.add(CompostItems("LIVE", CATEGORY_LIVE, 1))
        dataList.add(CompostItems("LIVE", CATEGORY_LIVE,1))
        dataList.add(CompostItems("LIVE", CATEGORY_LIVE, 1))
        dataList.add(CompostItems("LIVE", CATEGORY_LIVE, 1))
        dataList.add(CompostItems("LIVE", CATEGORY_LIVE, 1))

        return dataList
    }
}
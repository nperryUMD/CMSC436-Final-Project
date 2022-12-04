package com.example.composting.detailScreens.classes

import com.example.composting.detailScreens.classes.Category.Companion.CATEGORY_VEG
import com.example.composting.detailScreens.classes.Category.Companion.CATEGORY_FOOD
import com.example.composting.detailScreens.classes.Category.Companion.CATEGORY_LIVE

class Datasource {
    fun load() : ArrayList<CompostItems>{
        val dataList = ArrayList<CompostItems>()
        dataList.add(CompostItems("Wood Chips", CATEGORY_VEG,-1))
        dataList.add(CompostItems("Leaves", CATEGORY_VEG, -2))
        dataList.add(CompostItems("Grass Clippings", CATEGORY_VEG, -3))
        dataList.add(CompostItems("Teabags", CATEGORY_VEG, -4))
        dataList.add(CompostItems("Coffee Grinds", CATEGORY_VEG,-1))
        dataList.add(CompostItems("Egg Shells", CATEGORY_VEG, -2))
        dataList.add(CompostItems("Tissues", CATEGORY_VEG, 3))
        dataList.add(CompostItems("Sawdust", CATEGORY_VEG, -1))
        dataList.add(CompostItems("Fruit Rinds", CATEGORY_VEG,-1))
        dataList.add(CompostItems("Cut Flowers", CATEGORY_VEG, -2))
        dataList.add(CompostItems("Peat Moss", CATEGORY_VEG, 3))
        dataList.add(CompostItems("Cotton Swabs", CATEGORY_VEG, -4))

        dataList.add(CompostItems("Cheese", CATEGORY_FOOD,3))
        dataList.add(CompostItems("Bread", CATEGORY_FOOD, 2))
        dataList.add(CompostItems("Rice", CATEGORY_FOOD, 1))
        dataList.add(CompostItems("Chicken", CATEGORY_FOOD, 2))
        dataList.add(CompostItems("Spinach", CATEGORY_FOOD,1))
        dataList.add(CompostItems("Milk", CATEGORY_FOOD, 2))
        dataList.add(CompostItems("Lentils", CATEGORY_FOOD, 1))
        dataList.add(CompostItems("Nuts", CATEGORY_FOOD, -3))

        dataList.add(CompostItems("Grasshopper", CATEGORY_LIVE,0))
        dataList.add(CompostItems("Cricket", CATEGORY_LIVE, 0))
        dataList.add(CompostItems("Shellfish", CATEGORY_LIVE, 0))
        dataList.add(CompostItems("Maggot", CATEGORY_LIVE, 0))
        dataList.add(CompostItems("Mite", CATEGORY_LIVE,0))
        dataList.add(CompostItems("Beetle", CATEGORY_LIVE, 0))
        dataList.add(CompostItems("Ant", CATEGORY_LIVE, 0))
        dataList.add(CompostItems("Worm", CATEGORY_LIVE, 0))
        dataList.add(CompostItems("Fly", CATEGORY_LIVE,0))
        dataList.add(CompostItems("Centipede", CATEGORY_LIVE, 0))
        dataList.add(CompostItems("Snail", CATEGORY_LIVE, 0))
        dataList.add(CompostItems("Slug", CATEGORY_LIVE, 0))

        return dataList
    }
}
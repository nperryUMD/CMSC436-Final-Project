package com.example.composting.detailScreens.classes

import com.example.composting.detailScreens.classes.Category.Companion.CATEGORY_VEG
import com.example.composting.detailScreens.classes.Category.Companion.CATEGORY_FOOD
import com.example.composting.detailScreens.classes.Category.Companion.CATEGORY_LIVE

class Datasource {
    fun load() : ArrayList<CompostItems>{
        val dataList = ArrayList<CompostItems>()
        dataList.add(CompostItems("Spinach", CATEGORY_VEG,-1))
        dataList.add(CompostItems("Lettuce", CATEGORY_VEG, -2))
        dataList.add(CompostItems("Kale", CATEGORY_VEG, -3))
        dataList.add(CompostItems("Arugula", CATEGORY_VEG, -4))
        dataList.add(CompostItems("Broccoli", CATEGORY_VEG,-1))
        dataList.add(CompostItems("Cabbage", CATEGORY_VEG, -2))
        dataList.add(CompostItems("Cauliflower", CATEGORY_VEG, 3))
        dataList.add(CompostItems("Carrot", CATEGORY_VEG, -1))
        dataList.add(CompostItems("Brussel Sprout", CATEGORY_VEG,-1))
        dataList.add(CompostItems("Cabbage", CATEGORY_VEG, -2))
        dataList.add(CompostItems("Bell Pepper", CATEGORY_VEG, 3))
        dataList.add(CompostItems("Tomato", CATEGORY_VEG, 4))

        dataList.add(CompostItems("Cheese", CATEGORY_FOOD,3))
        dataList.add(CompostItems("Egg", CATEGORY_FOOD, 2))
        dataList.add(CompostItems("Rice", CATEGORY_FOOD, 1))
        dataList.add(CompostItems("Chicken", CATEGORY_FOOD, 2))
        dataList.add(CompostItems("Potatoe", CATEGORY_FOOD,1))
        dataList.add(CompostItems("Milk", CATEGORY_FOOD, 2))
        dataList.add(CompostItems("Lentil", CATEGORY_FOOD, 1))
        dataList.add(CompostItems("Nut", CATEGORY_FOOD, -3))

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
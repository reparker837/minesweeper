package com.ait.minesweeper

data class Field(var type: Int = 0, var minesAround: Int = 0,
                 var isFlagged: Boolean = false, var wasClicked: Boolean = false)


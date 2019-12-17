package com.ait.minesweeper.model

import com.ait.minesweeper.Field
import java.util.*

object MineSweeperModel {
    public var bomb1 = Field()
    public var bomb2 = Field()
    public var bomb3 = Field()
    //game area
    val fieldMatrix: Array<Array<Field>> = arrayOf(
        arrayOf(Field(), Field(), Field(), Field(), Field()),
        arrayOf(Field(), Field(), Field(), Field(), Field()),
        arrayOf(Field(), Field(), Field(), Field(), Field()),
        arrayOf(Field(), Field(), Field(), Field(), Field()),
        arrayOf(Field(), Field(), Field(), Field(), Field())
    )

    fun resetModel(){
        for (i in 0..4) {
            for (j in 0..4) {
                fieldMatrix[i][j] = Field()
            }
        }
        setBombs()
    }

    fun getFieldContent(x: Int, y: Int) = fieldMatrix[x][y]

    fun setBombs(){

        var a = (0..4).random()
        var b = (0..4).random()

        fieldMatrix[a][b].type = -1
        bomb1 = fieldMatrix[a][b]

        var c = (0..4).random()
        var d = (0..4).random()

        while(a == c && b == d){
            c = (0..4).random()
            d = (0..4).random()
        }
        fieldMatrix[c][d].type = -1
        bomb2 = fieldMatrix[c][d]

        var e = (0..4).random()
        var f = (0..4).random()
        while((a == e && b == f) || (c == e && d == f)){
            c = (0..4).random()
            d = (0..4).random()
        }
        fieldMatrix[e][f].type = -1
        bomb3 = fieldMatrix[e][f]

        for (i in 0..4) {
            for (j in 0..4){
                var counter = 0

                if(i != 0){
                    if (fieldMatrix[i-1][j].type == -1)
                        counter = counter + 1
                }
                if(j != 4){
                    if (fieldMatrix[i][j+1].type == -1)
                        counter = counter + 1
                }
                if(j != 0){
                    if (fieldMatrix[i][j-1].type == -1)
                        counter = counter + 1
                }
                if(i != 4){
                    if (fieldMatrix[i+1][j].type == -1)
                        counter = counter + 1
                }
                if(i != 4 && j != 4){
                    if (fieldMatrix[i+1][j+1].type == -1)
                        counter = counter + 1
                }
                if(i != 4 && j != 0){
                    if (fieldMatrix[i+1][j-1].type == -1)
                        counter = counter + 1
                }
                if(i != 0 && j != 4){
                    if (fieldMatrix[i-1][j+1].type == -1)
                        counter = counter + 1
                }
                if(i != 0 && j != 0){
                    if (fieldMatrix[i-1][j-1].type == -1)
                        counter = counter + 1
                }

                fieldMatrix[i][j].minesAround = counter
            }
        }

        /*fieldMatrix[0][1].minesAround = 1
        fieldMatrix[0][2].minesAround = 0
        fieldMatrix[0][3].minesAround = 0
        fieldMatrix[0][4].minesAround = 0
        fieldMatrix[1][0].minesAround = 1
        fieldMatrix[1][1].minesAround = 1
        fieldMatrix[1][2].minesAround = 1
        fieldMatrix[1][3].minesAround = 1
        fieldMatrix[1][4].minesAround = 1
        fieldMatrix[2][0].minesAround = 0
        fieldMatrix[2][1].minesAround = 0
        fieldMatrix[2][2].minesAround = 1
        fieldMatrix[2][4].minesAround = 1
        fieldMatrix[3][0].minesAround = 0
        fieldMatrix[3][1].minesAround = 0
        fieldMatrix[3][2].minesAround = 1
        fieldMatrix[3][3].minesAround = 2
        fieldMatrix[3][4].minesAround = 2
        fieldMatrix[4][0].minesAround = 0
        fieldMatrix[4][1].minesAround = 0
        fieldMatrix[4][2].minesAround = 0
        fieldMatrix[4][3].minesAround = 1*/
    }

}
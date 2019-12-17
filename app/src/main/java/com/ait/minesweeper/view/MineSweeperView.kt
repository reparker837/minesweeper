package com.ait.minesweeper.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import com.ait.minesweeper.MainActivity
import com.ait.minesweeper.R
import com.ait.minesweeper.model.MineSweeperModel

class MineSweeperView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    var paintBackground : Paint = Paint()
    var paintLine : Paint = Paint()
    var paintText: Paint = Paint()


    init {
        paintBackground.color = Color.BLACK
        paintBackground.style = Paint.Style.FILL

        paintLine.color = Color.RED
        paintLine.style = Paint.Style.STROKE
        paintLine.strokeWidth = 7f

        paintText.color = Color.WHITE

        MineSweeperModel.setBombs()
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        paintText.textSize = height / 8f
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas?.drawRect(0f, 0f, width.toFloat(), height.toFloat(), paintBackground)

        drawBoard(canvas)

        drawContents(canvas)
    }

    private fun drawBoard(canvas: Canvas?) {
        // border
        canvas?.drawRect(0f, 0f, width.toFloat(), height.toFloat(), paintLine)
        // four horizontal lines
        canvas?.drawLine(
            0f, (height / 5).toFloat(), width.toFloat(), (height / 5).toFloat(),
            paintLine
        )
        canvas?.drawLine(
            0f, (2 * height / 5).toFloat(), width.toFloat(),
            (2 * height / 5).toFloat(), paintLine
        )
        canvas?.drawLine(
            0f, (3 * height / 5).toFloat(), width.toFloat(),
            (3 * height / 5).toFloat(), paintLine
        )
        canvas?.drawLine(
            0f, (4 * height / 5).toFloat(), width.toFloat(),
            (4 * height / 5).toFloat(), paintLine
        )
        // four vertical lines
        canvas?.drawLine(
            (width / 5).toFloat(), 0f, (width / 5).toFloat(), height.toFloat(),
            paintLine
        )
        canvas?.drawLine(
            (2 * width / 5).toFloat(), 0f, (2 * width / 5).toFloat(), height.toFloat(),
            paintLine
        )
        canvas?.drawLine(
            (3 * width / 5).toFloat(), 0f, (3 * width / 5).toFloat(), height.toFloat(),
            paintLine
        )
        canvas?.drawLine(
            (4 * width / 5).toFloat(), 0f, (4 * width / 5).toFloat(), height.toFloat(),
            paintLine
        )
    }

    var flagEmoji = String(Character.toChars(0x1F6A9))
    var bombEmoji = String(Character.toChars(0x1F4A3))

    private fun drawContents(canvas: Canvas?){
        for (i in 0..4) {
            for (j in 0..4) {
                if (MineSweeperModel.fieldMatrix[i][j].type == -1
                    && MineSweeperModel.fieldMatrix[i][j].wasClicked == true
                    && MineSweeperModel.fieldMatrix[i][j].isFlagged == false) {
                    //val centerX = (i * width / 5 + width / 10).toFloat()
                    //val centerY = (j * height / 5 + height / 10).toFloat()
                    //val radius = height / 10 - 2
                    canvas?.drawText( //not being used-
                        "${bombEmoji}",
                        i*(width/5f),
                        (j+1)*(height/5f - 20),
                        paintText
                    )
                    //canvas?.drawCircle(centerX, centerY, radius.toFloat(), paintLine)
                } else if (MineSweeperModel.fieldMatrix[i][j].wasClicked
                    && MineSweeperModel.fieldMatrix[i][j].isFlagged == false) {
                    canvas?.drawText(
                        " ${MineSweeperModel.fieldMatrix[i][j].minesAround}",
                        (i*(width/5f)+20),
                        ((j+1)*(height/5f) - 70),
                    paintText
                    )
                } else if(MineSweeperModel.fieldMatrix[i][j].isFlagged == true){
                    canvas?.drawText(
                        "${flagEmoji}",
                        (i*(width/5f)+20),
                        ((j+1)*(height/5f) - 70),
                        paintText
                    )

                }
            }
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {

        if (event?.action  == MotionEvent.ACTION_DOWN) {
            val tX: Int = event.x.toInt() / (width / 5)
            val tY: Int = event.y.toInt() / (height / 5)

            if ((context as MainActivity).isToggleOn()) {
                MineSweeperModel.fieldMatrix[tX][tY].wasClicked = true
                if (MineSweeperModel.fieldMatrix[tX][tY].type == -1) {
                    endGame(false)
                } else {
                    MineSweeperModel.fieldMatrix[tX][tY].wasClicked = true
                }
            } else{
                MineSweeperModel.fieldMatrix[tX][tY].isFlagged = true
                if(MineSweeperModel.bomb1.isFlagged == true
                    && MineSweeperModel.bomb2.isFlagged == true
                    && MineSweeperModel.bomb3.isFlagged == true){
                    endGame(true)
                } else if(MineSweeperModel.fieldMatrix[tX][tY].type == 0) {
                    endGame(false)
                }
            }

            invalidate()
        }

        return super.onTouchEvent(event)
    }

    fun endGame(win : Boolean){
        if(win){
            (context as MainActivity).showText(
                "WINNER!!!"
            )
        } else{
            (context as MainActivity).showText(
                "GAME OVER :("
            )
            //make more obvious
        }

        MineSweeperModel.resetModel()
        invalidate()
    }

}
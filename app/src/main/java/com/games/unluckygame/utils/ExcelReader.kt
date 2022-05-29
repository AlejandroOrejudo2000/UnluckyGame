package com.games.unluckygame.utils

import android.content.ContentResolver
import android.net.Uri
import android.util.Log
import androidx.fragment.app.FragmentActivity
import com.games.unluckygame.database.GameDataBase
import com.games.unluckygame.entity.*
import org.apache.poi.ss.usermodel.*
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.io.*
import java.lang.NullPointerException
import java.lang.StringBuilder

object ExcelReader {

    private const val TAG = "EXCELREADER"
    private const val ROWSIZE_GAME = 6
    private const val ROWSIZE_EVENT = 4
    private const val ROWSIZE_PENALTY = 3

    private val sb = StringBuilder()

    fun readExcelFile(inputStream: InputStream) : String{
        sb.clear()
        val workbook = XSSFWorkbook(inputStream)
        val formulaEvaluator = workbook.creationHelper.createFormulaEvaluator()
        val numberofsheets = workbook.numberOfSheets
        for(i in 0 until numberofsheets)
        {
            readExcelSheet(workbook.getSheetAt(i), formulaEvaluator)
        }
        return sb.toString()
    }

    private fun readExcelSheet(sheet: Sheet, formulaEvaluator: FormulaEvaluator){
        val rows = sheet.physicalNumberOfRows
        for(i in 0 until rows) {
            val row = sheet.getRow(i)
            when(row.count()) {
                ROWSIZE_GAME -> readExcelRowAsGame(row, formulaEvaluator)
                ROWSIZE_EVENT -> readExcelRowAsEvent(row, formulaEvaluator)
                ROWSIZE_PENALTY -> readExcelRowAsPenalty(row, formulaEvaluator)
            }
        }
    }

    private fun readExcelRowAsGame(row: Row, formulaEvaluator: FormulaEvaluator){
        val name = getCellAsString(row.getCell(0), formulaEvaluator)
        val diff = getCellAsString(row.getCell(1), formulaEvaluator)
        val type = getCellAsString(row.getCell(2), formulaEvaluator)
        val reward = getCellAsString(row.getCell(3), formulaEvaluator)
        val mat = getCellAsString(row.getCell(4), formulaEvaluator)
        val desc = getCellAsString(row.getCell(5), formulaEvaluator)
        if(!name.isNullOrBlank() && !diff.isNullOrBlank() && !type.isNullOrBlank() &&
            !reward.isNullOrBlank() && !mat.isNullOrBlank() && !desc.isNullOrBlank()){
            sb.append("g:$name:$diff:$type:$reward:$mat:$desc;")
        }
    }

    private fun readExcelRowAsEvent(row: Row, formulaEvaluator: FormulaEvaluator){
        val name = getCellAsString(row.getCell(0), formulaEvaluator)
        val desc = getCellAsString(row.getCell(1), formulaEvaluator)
        val type = getCellAsString(row.getCell(2), formulaEvaluator)
        val scope = getCellAsString(row.getCell(3), formulaEvaluator)
        if(!name.isNullOrBlank() && !type.isNullOrBlank() &&
            !scope.isNullOrBlank() && !desc.isNullOrBlank()){
            sb.append("e:$name:$type:$scope:$desc;")
        }
    }

    private fun readExcelRowAsPenalty(row: Row, formulaEvaluator: FormulaEvaluator){
        val name = getCellAsString(row.getCell(0), formulaEvaluator)
        val diff = getCellAsString(row.getCell(1), formulaEvaluator)
        val desc = getCellAsString(row.getCell(2), formulaEvaluator)
        if(!name.isNullOrBlank() && !diff.isNullOrBlank() && !desc.isNullOrBlank()){
            sb.append("p:$name:$diff:$desc;")
        }
    }

    private fun getCellAsString(cell : Cell, formulaEvaluator: FormulaEvaluator) : String?{
        var value : String? = null
        try{
            val cellValue = formulaEvaluator.evaluate(cell)
            value = cellValue.stringValue
        }catch (e : NullPointerException){
            Log.e(TAG, "NullPointerException. ${e.message}")
        }
        return value
    }

    private fun getCellAsNumber(cell : Cell, formulaEvaluator: FormulaEvaluator) : Int?{
        var value : Int? = null
        try{
            val cellValue = formulaEvaluator.evaluate(cell)
            value = cellValue.numberValue as Int
        }catch (e : NullPointerException){
            Log.e(TAG, "NullPointerException. ${e.message}")
        }
        return value
    }
}
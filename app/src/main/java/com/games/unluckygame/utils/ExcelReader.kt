package com.games.unluckygame.utils

import android.content.ContentResolver
import android.net.Uri
import android.util.Log
import androidx.fragment.app.FragmentActivity
import org.apache.poi.ss.usermodel.FormulaEvaluator
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.io.*
import java.lang.NullPointerException
import java.lang.StringBuilder

object ExcelReader {

    private const val TAG = "EXCELREADER"

    fun readExcelFile(filepath: String){
        Log.d(TAG, "Reading excel file")
        val inputFile = File(filepath)
        try {

            val inputStream : InputStream = FileInputStream(inputFile)

            val workbook = XSSFWorkbook(inputStream)
            val sheet = workbook.getSheetAt(0)
            val rows = sheet.physicalNumberOfRows
            val formulaEvaluator = workbook.creationHelper.createFormulaEvaluator()
            val sb = StringBuilder()

            for(i in 0..rows){
                val row = sheet.getRow(i)
                val cols = row.count()
                for(j in 0..cols){
                    val value = getCellAsString(row, j, formulaEvaluator)
                    val cellInfo = "r:" + i + "c:" + j +"v:" + value
                    sb.append(cellInfo)
                }
                sb.append(" : ")
            }
            Log.d(TAG, sb.toString())
        }
        catch (e : FileNotFoundException){
            Log.e(TAG, "FileNotFoundException. ${e.message}")
        }
        catch (e : IOException){
            Log.e(TAG, "IOException. ${e.message}")
        }
    }

    fun readFile(uri: Uri, contentResolver: ContentResolver?){
        contentResolver?.let {
            val inputStream = it.openInputStream(uri)
            val workbook = XSSFWorkbook(inputStream)
            val sheet = workbook.getSheetAt(0)
            println("NAME: ${sheet.sheetName}")
            val rows = sheet.physicalNumberOfRows
            println(rows)
            val formulaEvaluator = workbook.creationHelper.createFormulaEvaluator()
            val sb = StringBuilder()
            for(i in 0 until rows){
                val row = sheet.getRow(i)
                val cols = row.count()
                for(j in 0 until cols){
                    val value = getCellAsString(row, j, formulaEvaluator)
                    val cellInfo = "r-$i,c-$j,-v:$value"
                    sb.append(cellInfo)
                }
                sb.append(" : ")
            }
            println(sb.toString())
        }
    }

    private fun getCellAsString(row: Row, c: Int, formulaEvaluator: FormulaEvaluator) : String{
        var value : String = ""
        try{
            val cell = row.getCell(c)
            val cellValue = formulaEvaluator.evaluate(cell)
            value = cellValue.stringValue
        }catch (e : NullPointerException){
            Log.e(TAG, "NullPointerException. ${e.message}")
        }
        return value
    }

    fun parseStringBuilder(sb : StringBuilder){
        val rows = sb.toString().split(":")
    }

}
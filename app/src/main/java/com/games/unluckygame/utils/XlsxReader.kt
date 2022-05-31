package com.games.unluckygame.utils

import org.apache.poi.ss.usermodel.FormulaEvaluator
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.ss.usermodel.Sheet
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.io.InputStream
import java.lang.StringBuilder

object XlsxReader {

    fun readExcelFile(inputStream: InputStream) : String{
        val sb = StringBuilder()
        val workbook = XSSFWorkbook(inputStream)
        val formulaEvaluator = workbook.creationHelper.createFormulaEvaluator()
        val numberOfSheets = workbook.numberOfSheets
        for(i in 0 until numberOfSheets)
        {
            val sheet = workbook.getSheetAt(i)
            if(sheet != null)
                sb.append(readExcelSheet(workbook.getSheetAt(i), formulaEvaluator))
        }
        return sb.toString()
    }

    private fun readExcelSheet(sheet: Sheet, formulaEvaluator: FormulaEvaluator) : StringBuilder {
        val sb = StringBuilder()
        for(i in 0 until sheet.physicalNumberOfRows) {
            val row = sheet.getRow(i)
            if(row != null)
                sb.append("${readExcelRow(row, formulaEvaluator)};")
        }
        return sb
    }

    private fun readExcelRow(row: Row, formulaEvaluator: FormulaEvaluator): StringBuilder {
        val sb = StringBuilder()

        for (i in 0 until row.count()) {
            val cellValue = row.getCell(i).stringCellValue
            if(!cellValue.isNullOrBlank()){
                if(!sb.isNullOrBlank())
                    sb.append(":")
                sb.append(cellValue)
            }
        }
        return sb
    }
}
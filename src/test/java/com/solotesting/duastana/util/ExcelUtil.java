package com.solotesting.duastana.util;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.solotesting.duastana.MainTesting;
import com.solotesting.duastana.constants.NameConstants;
import com.solotesting.duastana.pages.LoginPage;
import com.solotesting.duastana.pages.MainPage;
import com.solotesting.duastana.util.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.aeonbits.owner.ConfigFactory;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import static com.solotesting.duastana.constants.NameConstants.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtil {
    private Workbook workbook;
    private Sheet worksheet;
    FileInputStream inStream;
    FileOutputStream outStream;
    public int excelRow;
    public int excelCol;
    private Cell cell;
    private MainTesting testCases = new MainTesting();

    public int getExcelCol() {
        return excelCol;
    }

    public void setExcelCol(int excelCol) {
        this.excelCol = excelCol;
    }

    public int getExcelRow() {
        return excelRow;
    }

    public void setExcelRow(int excelRow) {
        this.excelRow = excelRow;
    }
    public void incExcelRow() {
        this.excelRow++;
    }

    public void setExcelFile(String excelFilePath) throws IOException {
        inStream = new FileInputStream(excelFilePath);
        workbook = WorkbookFactory.create(inStream);

    }

    public String getCellData(int rowNumber,int columnNumber){
        worksheet = workbook.getSheetAt(0);
        if (cell==null) return null;
        else
        cell =worksheet.getRow(rowNumber).getCell(columnNumber);
        if (cell==null) {
            return "NULL";
        }else
        return cell.getStringCellValue();
    }

    public int getRowCountInSheet(){
        worksheet = workbook.getSheetAt(0);
        int rowsCount = worksheet.getPhysicalNumberOfRows();
        return rowsCount;
    }

    public void setCellValue(int rowNum,int columnNum,String cellValue,String excelFilePath) throws Exception {
        inStream = new FileInputStream(excelFilePath);
        workbook = WorkbookFactory.create(inStream);
        worksheet = workbook.getSheetAt(0);
        excelRow = rowNum;
        excelCol = columnNum;

        cell = worksheet.getRow(rowNum).getCell(columnNum);

        if(cell==null) {

        } else
        cell.setCellValue(cellValue);

        outStream = new FileOutputStream(excelFilePath);
        workbook.write(outStream);

        outStream.close();
        workbook.close();
        inStream.close();


    }

    public void callTestMethod(String testName) {
        if(testName=="TC001") {
            testCases.logIn();
        }
        if(testName=="TC002") {
            testCases.logInWithId();
        }
        if(testName=="TC003") {
            testCases.testProfilePage();
        }
        if(testName=="TC004") {
            testCases.testCurTable();
        }
        if(testName=="TC005") {
            testCases.testTeacherTab();
        }
        if(testName=="TC006") {
            testCases.testTranscriptTab();
        }
        if(testName=="TC007") {
            testCases.testDiseaseTab();
        }
        if(testName=="TC008") {
            testCases.testCertificateTab();
        }
        if(testName=="TC009") {
            testCases.testLogout();
        }
        if(testName=="TC010") {
            testCases.testLogoutTab();
        }
        if(testName=="TC011") {
            testCases.testSwitcher();
        }

    }

}

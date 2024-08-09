import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable
import org.apache.poi.ss.usermodel.*
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.io.FileOutputStream
import java.io.File
import java.io.FileInputStream

// Define the path to the Excel file
String filePath = "C:/Users/DELL/OneDrive/Desktop/mail/data_sheet.xlsx" // Replace with your file path

// Open or create the Excel workbook and sheet
File excelFile = new File(filePath)
Workbook workbook
Sheet sheet

if (excelFile.exists() && excelFile.length() > 0) {
    // If the file exists and is not empty, open the workbook
    FileInputStream fileInputStream = new FileInputStream(excelFile)
    workbook = WorkbookFactory.create(fileInputStream)
    sheet = workbook.getSheetAt(0) // Get the first sheet, change index if necessary
    fileInputStream.close()
} else {
    // If the file doesn't exist or is empty, create a new workbook and sheet
    workbook = new XSSFWorkbook()
    sheet = workbook.createSheet("Sheet1")
}

// Get the last row number and create a new row
int lastRowNum = sheet.getLastRowNum()
Row newRow = sheet.createRow(lastRowNum + 1)

// Write the global variable value to the first cell of the new row
Cell cell = newRow.createCell(0)
cell.setCellValue(GlobalVariable.username) // Replace with your global variable

// Save changes to the Excel file
FileOutputStream fileOut = new FileOutputStream(filePath)
workbook.write(fileOut)
fileOut.close()
workbook.close()

println("Global variable value saved to Excel successfully!")

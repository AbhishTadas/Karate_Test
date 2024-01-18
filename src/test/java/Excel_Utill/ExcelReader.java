package Excel_Utill;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.io.File;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {
    @SuppressWarnings("unchecked")
    public static String sJson;
    public static String getAllColumnVslues(String sSheetName){
        Map<String,String> addValues = null;
        ArrayList headerValues = null;
        String path = null;
        try {
            addValues = new HashMap<String,String>();
            path = System.getProperty("user.dir")+"\\TestData\\testData.xlsx";
            File file = new File(path);
            FileInputStream fis = new FileInputStream(file);
            XSSFWorkbook workbook = new XSSFWorkbook(fis);
            XSSFSheet sheet = workbook.getSheet(sSheetName);
            headerValues = new ArrayList();
            XSSFRow row = sheet.getRow(0);
            for (int head = 0; head < row.getLastCellNum(); head++) {
                String headValue = row.getCell(head).getStringCellValue();
                headerValues.add(headValue);
            }

            for (int head = 0; head < row.getLastCellNum(); head++) {
                int icell = row.getCell(head).getColumnIndex();
                int rowsCount = sheet.getLastRowNum();

                for (int row1 = 1; row1 <= rowsCount; row1++) {
                    String cellName = sheet.getRow(row1).getCell(icell).getStringCellValue();
                    if (!cellName.isEmpty()) {
                        //if (cellName.equals(sTestCaseID)) {
                            for (int head1 = 2; head1 < row.getLastCellNum(); head1++) {
                                String headValue1 = row.getCell(head1).getStringCellValue();
                                if (headValue1.equals(headerValues.get(head1))) {
                                    String head2 = headerValues.get(head1).toString();
                                    sJson = sheet.getRow(row1).getCell(head1).getStringCellValue();
                                    //System.out.println(sJson);
                                    //addValues.put(head2, sJson);
                                }
                            }
                        //}
                    }
                }
            }
        }catch(Exception e) {
            System.out.println(e.getMessage());
        }
        return sJson;
    }

}

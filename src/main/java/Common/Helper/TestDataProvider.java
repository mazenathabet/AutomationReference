package Common.Helper;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

public class TestDataProvider {

    /**
     * We need to use the Commons-io and jackson-databind dependency from mvn repo so that we can read data the data from json files
     * and jackson-databind dependency to read the json string and write it into HashMap
     * steps :
     * 1 - parse our json file to json string using ( commons-io )
     * 2 - convert the json string to hashmap ( jackson-databind)
     * 3 - HashMap will be converted to data provider, so it can be used in the testcase
     */
    public static List<HashMap<String, String>> getDataFromJsonFile(String jsonFilePath) throws IOException {
        // convert json file content to json string
        String jsonContent = FileUtils.readFileToString(new File(jsonFilePath), StandardCharsets.UTF_8);
        ObjectMapper mapper = new ObjectMapper();
        List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<>() {
        });
        return data;
    }


    public static Object[][] getDataFromExcelFile(String filePath) throws IOException {
        File srcFile = new File(Properties.getProperty(filePath));
        FileInputStream fis = new FileInputStream(srcFile);
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        XSSFSheet sheet = wb.getSheetAt(0);

        int TotalNumberOfRows = (sheet.getLastRowNum() + 1);
        int TotalNumberOfCols = 2;

        String[][] arrayExcelData = new String[TotalNumberOfRows][TotalNumberOfCols];
        for (int i = 0; i < TotalNumberOfRows; i++) {
            for (int j = 0; j < TotalNumberOfCols; j++) {
                XSSFRow row = sheet.getRow(i);
                arrayExcelData[i][j] = row.getCell(j).toString();
            }
        }
        wb.close();
        return arrayExcelData;
    }
}

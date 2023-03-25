package Common;

import Common.Helper.Properties;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import static Common.Helper.TestDataProvider.getDataFromJsonFile;

public class DataProviderClass {


    @Test(dataProvider = "getData")
    public void simpleDataProviderTest(String fname, String lname, String company, String gender, String country) {
        System.out.println("fname = " + fname + ", lname = " + lname + ", company = " + company + ", gender = " + gender + ", country = " + country);
    }

    @DataProvider
    public Object[][] getData() {
        return new Object[][]{
                {"Mazen", "Ahmed", "Company", "Male", "Egypt"},
                {"Sarah", "John", "Company1", "Female", "USA"},
                {"Osama", "Omar", "Company2", "Male", "Brazil"}
        };
    }


    @Test(dataProvider = "getJsonData")
    public void JsonFileDataProviderTest(HashMap<String, String> input) {
        System.out.println("first name = " + input.get("first name") + ", last name = " + input.get("last name") + ", Company = " + input.get("Company") + ", gender = " + input.get("Gender") + ", country = " + input.get("Country"));
    }

    @DataProvider
    public Object[][] getJsonData() throws IOException {
        List<HashMap<String, String>> data = getDataFromJsonFile(System.getProperty("user.dir") + Properties.getProperty("DataProviderJsonFilePath"));
        return new Object[][]{
                {data.get(0)},
                {data.get(1)},
                {data.get(2)},
                {data.get(3)}
        };
    }
}

package Com.ShoppersStack_GenericUtility;

public interface FrameWorkConstants {

	JavaUtility javaUtility = new JavaUtility();

	static final String propertyFilePath = "./src/test/resources/TestData/shoppersStackData.properties";

	static final String excelFilePath = "./src/test/resources/TestData/shoppersStackData.xlsx";

	static final String screenShotPath = "./screenshot/" + javaUtility.getLocalDateAndTime() + ".png";

	static final String reportsPath = "./reports/" + javaUtility.getLocalDateAndTime() + ".html";

	static final String chromeKey = "webdriver.chrome.driver";

	static final String chromeValue = "./src/main/resources/driver/chromedriver.exe";

}

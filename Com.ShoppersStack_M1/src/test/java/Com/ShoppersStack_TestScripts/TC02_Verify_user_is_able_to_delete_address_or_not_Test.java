package Com.ShoppersStack_TestScripts;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.annotations.Test;

import Com.ShoppersStack_GenericUtility.BaseTest;
import Com.ShoppersStack_POM.MyAddresses_Page;
import Com.ShoppersStack_POM.MyProfile_Page;

public class TC02_Verify_user_is_able_to_delete_address_or_not_Test extends BaseTest {

	@Test
	public void deleteAddress() throws EncryptedDocumentException, IOException, InterruptedException {

		Thread.sleep(2000);
		homePage.getAccountSettingsBtn().click();
		homePage.getMyProfileBtn().click();

		MyProfile_Page myProfilePage = new MyProfile_Page(driver);
		myProfilePage.getMyAddressesBtn().click();

		MyAddresses_Page myAddressesPage = new MyAddresses_Page(driver);
		Thread.sleep(2000);
		myAddressesPage.getDeleteBtn().click();
		myAddressesPage.getYesBtn().click();

		Thread.sleep(2000);
		driver.switchTo().alert().accept();

		Thread.sleep(2000);
		webDriverUtility.webPageScreenShot(driver);

	}

}

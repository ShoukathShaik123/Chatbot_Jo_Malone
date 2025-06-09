package Package2;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProviderClass {
	@DataProvider(name="TestData")
	public Object[][] testData(){
		return new Object[][] {
			{"418253287","jayneeey@gmail.com","DE56 2BF"},
			{"421331363","lo_belal88@yahoo.com","LN2 1SH"},
			{"237128096","gloria.mma@gmail.com","RH163bh"}
		};
	}
	@Test(dataProvider="TestData")
	public void runTest(String orderID,String email,String postCode) {
		JoMaloneExtent1 jm1=new JoMaloneExtent1();
		try {
			jm1.setpReport();
			jm1.setUp();
			jm1.nexusChat();
			jm1.WlcmMsgVerification();
			jm1.haveOrdNum();
			jm1.credentials(orderID,email,postCode);
			jm1.expFeedCon();
			jm1.tearDownReport();
//			jm1.closeBrowser();
		}finally {
			jm1.closeBrowser();
		}
	}
}

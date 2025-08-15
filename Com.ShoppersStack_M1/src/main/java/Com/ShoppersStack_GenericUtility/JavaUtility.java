package Com.ShoppersStack_GenericUtility;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Random;

public class JavaUtility {

	public int generateRandomNumber() {

		Random ran = new Random();
		int num = ran.nextInt(1000);
		return num;

	}

	public String getLocalDate() {

		String date = LocalDate.now().toString().replace("-", "");
		return date;

	}

	public String getLocalDateAndTime() {

		String time = LocalDateTime.now().toString().replace("-", "").replace(":", "").replace(".", "");
		return time;

	}

}

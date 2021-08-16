package Actions;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.*;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Helpers Methods
 * 
 * @author Toka.Ashraf
 *
 */

public class Helpers {

	Logger log = LogManager.getLogger("HelpersMethods");

	

	// function to generate a random string of length n
	public String getAlphaNumericString(int n) {

		String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" + "abcdefghijklmnopqrstuvxyz";

		// create StringBuffer size of AlphaNumericString
		StringBuilder sb = new StringBuilder(n);

		for (int i = 0; i < n; i++) {

			int index = (int) (AlphaNumericString.length() * Math.random());

			sb.append(AlphaNumericString.charAt(index));
		}

		return sb.toString();
	}

	public String[] splitString(String text, String splitter)

	{
		
		log.info("split String using : " + splitter +" For Text : "+ text);
		String[] words = text.split(splitter);
		for (String gen : words) {
			log.info("get each part after splitting : " + gen);
			System.out.println("each part of the list after split :"+gen);
		}
		String Arrfinal = Arrays.toString(words);
		log.info("get array of strings including all parts after splitting");
		return words;

	}

	

}

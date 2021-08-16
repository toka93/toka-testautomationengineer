package configuration;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConfigProperties {

	Logger log= LogManager.getLogger("config.properties file");

	private Properties props;
	String path = System.getProperty("user.dir");

	//String path = System.getProperty("user.dir");

	private final String propertyFilePath = path + File.separator +  "config.properties";

	public ConfigProperties() {
		BufferedReader reader;
		StringBuffer buf = new StringBuffer();
		try {
			reader = new BufferedReader(new FileReader(propertyFilePath));
			// reader = new BufferedReader(new InputStreamReader(is));

			props = new Properties();
			try {
				props.load(reader);
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("Configuration.properties not found at " + propertyFilePath);
		}
	}
	// read from resources 

/*	public ConfigProperties() {
		
			
		String resourceName = "config.properties"; 
	
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		log.info("read properties file as a class resource");
		props = new Properties();
		try (InputStream resourceStream = loader.getResourceAsStream(resourceName)) {
			props.load(resourceStream);
 log.info("load resource file");
			resourceStream.close();

		} catch (IOException e) {
			log.fatal("file not found !!");

			e.printStackTrace();
		}

	}*/

	public String getAppuser() {
		String username = props.getProperty("username");
		log.info("username value in config file is : "+username );
		if (username != null)
			return username;
		else
			log.fatal("username property does not exist in config.properties file");

			throw new RuntimeException("user name not specified in the Configuration.properties file.");
	}

	public String getApplicationUrl() {
		String url = props.getProperty("AppURL");
		log.info("url value in config file is : "+url );
		if (url != null)
			return url;
		else
			log.fatal("url property does not exist in config.properties file");

			throw new RuntimeException("url not specified in the Configuration.properties file.");
	}
	//GetDockerURL
	
	

	public String getTestDataFile() {
		
		String dataFile = props.getProperty("TestDataFile");
		log.info("dataFile   json or excel is the same value in config file is : "+dataFile );

		if (dataFile != null)
			return dataFile;
		else
			log.fatal("Data file property does not exist in config.properties file");

			throw new RuntimeException("dataFile not specified in the Configuration.properties file.");
	}

	public String getuserPass() {
		String pass = props.getProperty("password");
		log.info("pass  in config file is : "+pass );

		if (pass != null)
			return pass;
		else
			log.fatal("App pass property does not exist in config.properties file");

			throw new RuntimeException("pass not specified in the Configuration.properties file.");
	}

	/*
	 * public String getDBurl() { String DBurl = props.getProperty("URLORHost");
	 * log.info("DBurl in config file is : "+DBurl );
	 * 
	 * if (DBurl != null) return DBurl; else
	 * log.fatal("DB url property does not exist in config.properties file");
	 * 
	 * throw new
	 * RuntimeException("DB url not specified in the Configuration.properties file."
	 * ); }
	 * 
	 * public String getDBuser() { String DBuser = props.getProperty("DBuser");
	 * log.info("DBuser in config file is : "+DBuser );
	 * 
	 * if (DBuser != null) return DBuser; else
	 * log.fatal("DB user property does not exist in config.properties file");
	 * 
	 * throw new
	 * RuntimeException("DB url not specified in the Configuration.properties file."
	 * ); }
	 * 
	 * public String getDBpassword() { String DBpass =
	 * props.getProperty("DBpassword");
	 * log.info("DBpass in config file is : "+DBpass );
	 * 
	 * if (DBpass != null) return DBpass; else
	 * log.fatal("DB pass property does not exist in config.properties file");
	 * 
	 * throw new
	 * RuntimeException("DB password not specified in the Configuration.properties file."
	 * ); }
	 * 
	 * public String getDBSchema() { String Schema = props.getProperty("Schema");
	 * log.info("DB  Schema in config file is : "+Schema );
	 * 
	 * if (Schema != null) return Schema; else
	 * log.fatal("DB schema property does not exist in config.properties file");
	 * throw new
	 * RuntimeException("DB schema not specified in the Configuration.properties file."
	 * ); }
	 */
//ProjectName
	public String getProjectName() {
		String project = props.getProperty("ProjectName");
		if (project != null)
			return project;
		else
			throw new RuntimeException("project name not specified in the Configuration.properties file.");
	}
	
//ExtentReport
	public String getExtentReport() {
		String ExtentReport = props.getProperty("ExtentReport");
		log.info("ExtentReport  name in config file is : "+ExtentReport );
		if (ExtentReport != null)
			return ExtentReport;
		else
			log.fatal("Extent Report property does not exist in config.properties file");
			throw new RuntimeException("ExtentReport not specified in the Configuration.properties file.");
		
	}
	public String getBrowser() {
		String browser = props.getProperty("Browser");
		log.info("Browser value in config file is : "+browser );
		if (browser != null)
			return browser;
		else
			log.fatal("browser property does not exist in config.properties file");

			throw new RuntimeException("user name not specified in the Configuration.properties file.");
	}
	
	
	
	
	
	
	public String GetChromeVersion() {
		String version = props.getProperty("Version");
		log.info("Version value in config file is : "+ version);
		if (version != null)
			return version;
		else
			log.fatal("version property does not exist in config.properties file");

			throw new RuntimeException("version not specified in the Configuration.properties file.");
	}
	
	public String GetType() {
		String type = props.getProperty("Type");
		log.info("Type value in config file is : "+type );
		if (type != null)
			return type;
		else
			log.fatal("type property does not exist in config.properties file");

			throw new RuntimeException("type not specified in the Configuration.properties file.");
	}
	
	
	public String GetHeadlessState() {
		String state = props.getProperty("Headless");
		log.info("Headless State in config file is : "+state );
		if (state != null)
			return state;
		else
			log.fatal("Headless property does not exist in config.properties file");

			throw new RuntimeException("Headless not specified in the Configuration.properties file.");
	}
	
	
	
}

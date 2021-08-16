package Actions;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitJS {
 
    public static WebDriver jsWaitDriver;
    public static WebDriverWait jsWait;
    public static JavascriptExecutor jsExec;
 
    //Get the driver 
    public static void setDriver (WebDriver driver) {
        jsWaitDriver = driver;
        jsWait = new WebDriverWait(jsWaitDriver, 10);
        jsExec = (JavascriptExecutor) jsWaitDriver;
    }
 
    public void ajaxComplete(WebDriver driver) {
    	
    	
    	jsWaitDriver = driver;
        jsWait = new WebDriverWait(jsWaitDriver, 10);
        jsExec = (JavascriptExecutor) jsWaitDriver;
    	
    	
    	
    	
        jsExec.executeScript("var callback = arguments[arguments.length - 1];"
            + "var xhr = new XMLHttpRequest();" + "xhr.open('GET', '/Ajax_call', true);"
            + "xhr.onreadystatechange = function() {" + "  if (xhr.readyState == 4) {"
            + "    callback(xhr.responseText);" + "  }" + "};" + "xhr.send();");
    }
 
    public void waitForJQueryLoad(WebDriver driver) {
        
    	jsWaitDriver = driver;
        jsWait = new WebDriverWait(jsWaitDriver, 10);
        jsExec = (JavascriptExecutor) jsWaitDriver;
    	
    	try {
        
        	ExpectedCondition<Boolean> jQueryLoad = xdriver -> ((Long) ((JavascriptExecutor) this.jsWaitDriver)
                .executeScript("return jQuery.active") == 0);
 
            boolean jqueryReady = (Boolean) jsExec.executeScript("return jQuery.active==0");
 
            if (!jqueryReady) {
                jsWait.until(jQueryLoad);
            }
        } catch (WebDriverException ignored) {
        }
    }
 
    public void waitForAngularLoad(WebDriver driver) {
    	jsWaitDriver = driver;
        jsWait = new WebDriverWait(jsWaitDriver, 10);
        jsExec = (JavascriptExecutor) jsWaitDriver;
    	
        String angularReadyScript = "return angular.element(document).injector().get('$http').pendingRequests.length === 0";
        angularLoads(angularReadyScript,driver);
    }
 
    public void waitUntilJSReady(WebDriver driver) {
    	
    	
    	 jsWaitDriver = driver;
         jsWait = new WebDriverWait(jsWaitDriver, 10);
         jsExec = (JavascriptExecutor) jsWaitDriver;
    	
        try {
            ExpectedCondition<Boolean> jsLoad = xdriver -> ((JavascriptExecutor) this.jsWaitDriver)
                .executeScript("return document.readyState").toString().equals("complete");
 
            boolean jsReady = jsExec.executeScript("return document.readyState").toString().equals("complete");
 
            if (!jsReady) {
                jsWait.until(jsLoad);
            }
        } catch (WebDriverException ignored) {
        }
    }
 
    public void waitUntilJQueryReady(WebDriver driver) {

   	 jsWaitDriver = driver;
        jsWait = new WebDriverWait(jsWaitDriver, 10);
        jsExec = (JavascriptExecutor) jsWaitDriver;
   	
    	
        Boolean jQueryDefined = (Boolean) jsExec.executeScript("return typeof jQuery != 'undefined'");
        if (jQueryDefined) {
            poll(20);
 
            waitForJQueryLoad(driver);
 
            poll(20);
        }
    }
 
    
    public void waitUntilAngularReady(WebDriver driver) {
    	

   	 jsWaitDriver = driver;
        jsWait = new WebDriverWait(jsWaitDriver, 10);
        jsExec = (JavascriptExecutor) jsWaitDriver;
   	
    	
        try {
            Boolean angularUnDefined = (Boolean) jsExec.executeScript("return window.angular === undefined");
            if (!angularUnDefined) {
                Boolean angularInjectorUnDefined = (Boolean) jsExec.executeScript("return angular.element(document).injector() === undefined");
                if (!angularInjectorUnDefined) {
                    poll(20);
 
                    waitForAngularLoad(driver);
 
                    poll(20);
                }
            }
        } catch (WebDriverException ignored) {
        }
    }
 
    public void waitUntilAngular5Ready(WebDriver driver) {
    	

   	 jsWaitDriver = driver;
        jsWait = new WebDriverWait(jsWaitDriver, 10);
        jsExec = (JavascriptExecutor) jsWaitDriver;
   	
        try {
            Object angular5Check = jsExec.executeScript("return getAllAngularRootElements()[0].attributes['ng-version']");
            if (angular5Check != null) {
                Boolean angularPageLoaded = (Boolean) jsExec.executeScript("return window.getAllAngularTestabilities().findIndex(x=>!x.isStable()) === -1");
                if (!angularPageLoaded) {
                    poll(20);
 
                    waitForAngular5Load(driver);
 
                    poll(20);
                }
            }
        } catch (WebDriverException ignored) {
        }
    }
 
    public void waitForAngular5Load(WebDriver driver) {
    	

   	 jsWaitDriver = driver;
        jsWait = new WebDriverWait(jsWaitDriver, 10);
        jsExec = (JavascriptExecutor) jsWaitDriver;
   	
        String angularReadyScript = "return window.getAllAngularTestabilities().findIndex(x=>!x.isStable()) === -1";
        angularLoads(angularReadyScript,driver);
    }
 
    public void angularLoads(String angularReadyScript , WebDriver driver) {
    	

   	 jsWaitDriver = driver;
        jsWait = new WebDriverWait(jsWaitDriver, 10);
        jsExec = (JavascriptExecutor) jsWaitDriver;
   	
    	
        try {
            ExpectedCondition<Boolean> angularLoad = xdriver -> Boolean.valueOf(((JavascriptExecutor) driver)
                .executeScript(angularReadyScript).toString());
 
            boolean angularReady = Boolean.valueOf(jsExec.executeScript(angularReadyScript).toString());
 
            if (!angularReady) {
                jsWait.until(angularLoad);
            }
        } catch (WebDriverException ignored) {
        }
    }
 
    public void waitAllRequest(WebDriver driver) {
        waitUntilJSReady(driver);
        ajaxComplete(driver);
        waitUntilJQueryReady(driver);
        waitUntilAngularReady(driver);
        waitUntilAngular5Ready(driver);
    }
 
    /**
     * Method to make sure a specific element has loaded on the page
     *
     * @param by
     * @param expected
     */
    public void waitForElementAreComplete(By by, int expected) {
        ExpectedCondition<Boolean> angularLoad = driver -> {
            int loadingElements = this.jsWaitDriver.findElements(by).size();
            return loadingElements >= expected;
        };
        jsWait.until(angularLoad);
    }
 
    /**
     * Waits for the elements animation to be completed
     * @param css
     */
    public void waitForAnimationToComplete(String css) {
        ExpectedCondition<Boolean> angularLoad = driver -> {
            int loadingElements = this.jsWaitDriver.findElements(By.cssSelector(css)).size();
            return loadingElements == 0;
        };
        jsWait.until(angularLoad);
    }
 
    public void poll(long milis) {
        try {
            Thread.sleep(milis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

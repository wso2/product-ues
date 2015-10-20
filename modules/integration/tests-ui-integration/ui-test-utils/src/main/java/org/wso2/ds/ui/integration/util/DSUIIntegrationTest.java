/*
*Copyright (c) 2015​, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
*
*WSO2 Inc. licenses this file to you under the Apache License,
*Version 2.0 (the "License"); you may not use this file except
*in compliance with the License.
*You may obtain a copy of the License at
*
*http://www.apache.org/licenses/LICENSE-2.0
*
*Unless required by applicable law or agreed to in writing,
*software distributed under the License is distributed on an
*"AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
*KIND, either express or implied.  See the License for the
*specific language governing permissions and limitations
*under the License.
*/

package org.wso2.ds.ui.integration.util;

import ds.integration.tests.common.domain.DSIntegrationTest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.wso2.carbon.automation.engine.context.TestUserMode;
import org.wso2.carbon.automation.extensions.selenium.BrowserManager;

import javax.xml.xpath.XPathExpressionException;
import java.net.MalformedURLException;

public abstract class DSUIIntegrationTest extends DSIntegrationTest {

    protected static final String DASHBOARD_REGISTRY_BASE_PATH = "/_system/config/ues/dashboards/";
    private static final Log LOG = LogFactory.getLog(DSUIIntegrationTest.class);
    private static final String DS_SUFFIX = "/portal/login-controller?destination=%2Fportal%2F";
    private static final String DS_HOME_SUFFIX = "/portal/";
    private static final String ADMIN_CONSOLE_SUFFIX = "/carbon/admin/index.jsp";
    protected String resourcePath;
    private DSWebDriver driver = null;
    private WebDriverWait wait = null;

    public DSUIIntegrationTest() {
        super();
    }

    public DSUIIntegrationTest(TestUserMode userMode) {
        super(userMode);

    }

    /**
     * To login to Dashboard server
     *
     * @param driver   WebDriver instance
     * @param url      base url of the server
     * @param userName user name
     * @param pwd      password
     * @throws javax.xml.xpath.XPathExpressionException,InterruptedException
     */
    public static void login(DSWebDriver driver, String url, String userName, String pwd)
            throws InterruptedException, XPathExpressionException {
        String fullUrl = "";
        fullUrl = url + DS_SUFFIX;
        driver.get(fullUrl);
        driver.findElement(By.name("username")).clear();
        driver.findElement(By.name("username")).sendKeys(userName);
        driver.findElement(By.name("password")).clear();
        driver.findElement(By.name("password")).sendKeys(pwd);
        driver.findElement(By.cssSelector(".ues-signin")).click();
    }

    /**
     * To logout from Dashboard server
     *
     * @param driver   WebDriver instance
     * @param url      base url of the server
     * @param userName user name
     * @throws javax.xml.xpath.XPathExpressionException
     */
    public static void logout(DSWebDriver driver, String url, String userName) throws
            XPathExpressionException {
        String fullUrl = "";
        fullUrl = url + DS_HOME_SUFFIX;
        driver.get(fullUrl);
        driver.findElement(By.cssSelector(".dropdown-toggle")).click();
        driver.findElement(By.cssSelector(".dropdown-menu > li > a")).click();
    }

    /**
     * To login to admin console DashBoard server
     *
     * @param driver   WebDriver instance
     * @param url      base url of the server
     * @param userName user name
     * @param pwd      password
     * @throws javax.xml.xpath.XPathExpressionException
     */
    public static void loginToAdminConsole(DSWebDriver driver, String url, String userName, String pwd) throws
            XPathExpressionException {
        driver.get(url + ADMIN_CONSOLE_SUFFIX);
        driver.findElement(By.id("txtUserName")).clear();
        driver.findElement(By.id("txtUserName")).sendKeys(userName);
        driver.findElement(By.id("txtPassword")).clear();
        driver.findElement(By.id("txtPassword")).sendKeys(pwd);
        driver.findElement(By.cssSelector("input.button")).click();
    }

    /**
     * To logout from admin console dashboard server
     *
     * @param driver WebDriver instance
     * @param url    base url of the server
     */
    public static void logoutFromAdminConsole(DSWebDriver driver, String url) {
        driver.get(url + ADMIN_CONSOLE_SUFFIX);
        driver.findElement(By.cssSelector(".right > a")).click();
    }

    /**
     * This method returns the web driver instance
     *
     * @return DSWEbDriver - the driver instance of DSWebDriver
     */
    public DSWebDriver getDriver() throws MalformedURLException, XPathExpressionException {
        if (driver == null) {
            driver = new DSWebDriver(BrowserManager.getWebDriver(), getMaxWaitTime());
        }
        return driver;
    }

    /**
     * This method returns the web driver instance
     *
     * @return DSWEbDriver - the driver instance of DSWebDriver
     */
    public DSWebDriver getDriver(FirefoxProfile profile) throws MalformedURLException, XPathExpressionException {
        if (driver == null) {
            driver = new DSWebDriver(BrowserManager.getWebDriver(), getMaxWaitTime(), profile);
        }
        return driver;
    }

    /**
     * This method returns the we driver wait instance
     *
     * @return DSWEbDriverWait - the webDriverWait instance of DSWebDriverWait
     */
    public WebDriverWait getWebDriverWait() throws MalformedURLException, XPathExpressionException {
        if (wait == null) {
            wait = new WebDriverWait(getDriver(), getMaxWaitTime());
        }
        return wait;
    }

}
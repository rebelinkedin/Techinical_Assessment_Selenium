### Description
This is a Web App test automation exercise created using the Selenium WebDriver with Java.

### Automated Tests
1. Go to https://www.amazon.com
2. Search for "hats for men" 
3. Add first hat to Cart with quantity 2
4. Open cart and assert total price and quantity are correct
5. Search for "hats for women"
6. Add first hat to Cart with quantity 1
7. Open cart and assert total price and quantity are correct
8. Change the quantity for item selected at step 3 from 2 to 1 item in Cart
9. Assert total price and quantity are changed correctly

### Prerequisites Software/Tools

* Git: [ http://git-scm.com/downloads ]

* Maven: [ http://maven.apache.org/ ]

* Java Development Kit 8: [https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html]

* IntelliJ IDE: [ https://www.jetbrains.com/idea/ ] (Only needed if you want to run the test in IDE)

* ChromeDriver: [ http://chromedriver.chromium.org/downloads]
  * Download ChromeDriver according to your Chrome version
  * Select the ChromeDriver for your Operating System
  * Specify in **FILE_PATH** where your ChromeDriver is placed in **SetUp.java**
     * for instance, I am using Windows and my chromedriver.exe path is C:\, so the FILE_PATH is **"C:\\chromedriver.exe"**
     * If I am using Mac and my chromedriver path is in Applications, the FILE_PATH will be like: **"/Applications/chromedriver"**

### Getting Started 
**Run tests with Maven on Windows**
1. Open the Git bash and clone the project using below command on the target directory: git clone [https://github.com/rebelinkedin/Nuvolar.git]
2. Open the windows command prompt **'cmd'** and navigate to the project directory
3. Run the below command to start the tests
   1. **mvn clean install** or **mvn clean test**
4. See the test report
   1. Access **target > surefire-reports** to see the test report in *emailable-report.html*
   1. Access **target > surefire-reports/Test Suite** to see the test report in *QaAutomationExercise.html* and *QaAutomationExercise.xml*
   
**Run tests in IntelliJ**
1. Open the Git bash and clone the project using below command on the target directory: git clone [https://github.com/rebelinkedin/Nuvolar.git]
2. Open the IntelliJ and open the project from the project directory
3. In order to see the test report
   * Open IntelliJ, select **Run > Edit Configurations**
   * Select **TestNG.xml** file and go to **Listeners** tab
   * Add the following reporters:
     * EmailableReporter
     * FailedReporter 
   * Click **Apply** and **OK**
4. Select **testing.xml** file and right click > Run **'TestNG.xml'**      
   * Access **test-output** folder to see the test report in *emailable-report.html*

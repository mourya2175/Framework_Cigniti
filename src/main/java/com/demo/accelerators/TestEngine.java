package com.demo.accelerators;       

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.demo.objectRepository.CommonObjects;
import com.demo.support.ConfiguratorSupport;
import com.demo.support.HtmlReportSupport;
import com.demo.support.MyListener;
import com.demo.support.ReportStampSupport;
import com.demo.utilities.SendMail;
import com.demo.utilities.Zip;


public class TestEngine extends SendMail {

	ObjectFactory objectFactory =new ObjectFactory();
	public static Logger logger = Logger.getLogger(TestEngine.class.getName());

	public static ConfiguratorSupport configProps = new ConfiguratorSupport(
			"config.properties");

	public static ConfiguratorSupport counterProp = new ConfiguratorSupport(
			configProps.getProperty("counterPath"));


	public String currentSuite = "";
	public String method = "";
	//public static String timeStamp = ReportStampSupport.timeStamp().replace(" ", "_").replace(":", "_").replace(".", "_");
	public boolean flag = false;
	public WebDriver webDriver = null;
	public EventFiringWebDriver driver=null;
	public DesiredCapabilities capabilities;
	public  int stepNum = 0;
	public  int PassNum =0;
	public  int FailNum =0;
	public String testName = "";
	public String testCaseExecutionTime = "";
	public StringBuffer x=new StringBuffer();
	public String finalTime = "";
	public boolean isSuiteRunning=false;
	public static Map<String, String> testDescription = new LinkedHashMap<String, String>();
	public static Map<String, String> testResults = new LinkedHashMap<String, String>();
	public static Map<Integer, Object[]> data = new LinkedHashMap<Integer, Object[]>();
	private static int h=1;
	public String url;
	//static ExcelReader xlsrdr = new ExcelReader(configProps.getProperty("TestData"),configProps.getProperty("sheetName0"));
	/*
	 * public static Screen s; public static String url =
	 * "jdbc:mysql://172.16.6.121/"; public static String dbName = "test";
	 * public static String userName = "root"; public static Connection conn =
	 * null; public static Statement stmt = null; public static
	 * PreparedStatement pstmt = null; public static ResultSet rs = null;
	 */
	public int countcompare = 0;

	public static String browser = null;
	static int len = 0;
	static int i = 0;
	public static ITestContext itc;
	public static String groupNames =null;
	public CommonObjects cObjs;

	public int failCount=0;
	public int passCount=0;

	/**
	 * Initializing browser requirements, Test Results file path and Database
	 * requirements from the configuration file
	 * 
	 * @Date 19/02/2013
	 * @Revision History
	 * 
	 */

	@BeforeSuite(alwaysRun=true)
	public void suiteFirst(ITestContext ctx) throws Throwable{
		itc=ctx;

		TestEngine.cleanUP();
		ReportStampSupport.calculateSuiteStartTime();
		

	}
	
	@BeforeTest(alwaysRun=true)
	public void first(ITestContext ctx) throws Throwable{

		itc=ctx;

	}


	@Parameters({"browser","view"})
	@BeforeClass(alwaysRun=true)
	public void first(ITestContext ctx, String browser,String view) throws Throwable{
		itc=ctx;
		if(view.equalsIgnoreCase("web")){
			cObjs=objectFactory.getObject("WEB");
		}
		else if(view.equalsIgnoreCase("mobile")){
			cObjs=objectFactory.getObject("MOBILE");
		}

	}


	@Parameters({"browser"})
	@AfterTest(alwaysRun=true)
	public void first1(ITestContext ctx, String browser) throws Throwable{
		itc=ctx;			

		HtmlReportSupport.createHtmlSummaryReport(browser, url);
		ReportStampSupport.calculateSuiteExecutionTime();
		closeSummaryReport(browser);
	}

	@Parameters({"browser"})
	@AfterSuite(alwaysRun = true)
	public void tearDownFirefox(ITestContext ctx, String browser) throws Throwable {
		if(configProps.getProperty("ReportsMail").equalsIgnoreCase("true")){
			if(configProps.getProperty("ExcelReports").equalsIgnoreCase("true")){
				WriteExcelfile();			
				SendMail.attachReportsToEmail("Results/HTML/SummaryResults.xls");
			}
			else{
				Zip.zipFolder("Results\\HTML", "Results\\Automation.zip");
				SendMail.attachReportsToEmail("Results\\Automation.zip");
			}
		}
	 if(configProps.getProperty("FinalSingleReport").equalsIgnoreCase("true"))
		createSummaryFinalReport();
	}

	public static void cleanUP() throws IOException {

		FileUtils.deleteDirectory(new File("Results/HTML"));		
		if(new File("Results/Automation.zip").exists()){
			FileUtils.deleteDirectory(new File("Results/"));
		}
		new File("Results/HTML/Screenshots").mkdirs();
		HtmlReportSupport.copyLogos();

	}


	@Parameters({"browser"})
	@BeforeMethod(alwaysRun = true)
	public void reportHeader(Method method, ITestContext ctx, String browser) throws Throwable {
		itc = ctx;

		if(browser.equalsIgnoreCase("firefox"))
		{
			webDriver = new FirefoxDriver();
			i=i+1;
		}
		else if(browser.equalsIgnoreCase("ie"))
		{
			File file = new File("Drivers/IEDriverServer.exe");
			System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
			webDriver= new InternetExplorerDriver();
			i=i+1;
		}
		else if(browser.equalsIgnoreCase("chrome"))
		{
			File file = new File("Drivers/chromedriver.exe");
			System.setProperty("webdriver.chrome.driver",  file.getAbsolutePath());				
			webDriver=new ChromeDriver();
			i=i+1;

		}

		driver = new EventFiringWebDriver(webDriver);
		url=configProps.getProperty("url");
		System.out.println("url opened :"+url);
		MyListener myListener = new MyListener();
		driver.register(myListener);

		try {

			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

			if(browser.equalsIgnoreCase("Firefox") || browser.equalsIgnoreCase("IE") || browser.equalsIgnoreCase("Chrome"))
			{
				driver.manage().window().maximize();
			}

			currentSuit = ctx.getCurrentXmlTest().getSuite().getName();
		} catch (Exception e1) {
			System.out.println(e1);
		}

		calculateTestCaseStartTime();

		flag = false;

		tc_name = method.getName().toString() + "-" + browser;
		String[] ts_Name = this.getClass().getName().toString().split("\\.");
		packageName = ts_Name[0] + "." + ts_Name[1] + "."+ ts_Name[2];


		testHeader(tc_name);

		stepNum = 0;
		PassNum = 0;
		FailNum = 0;
		testName = method.getName();
		String[] tmp=testName.split("_");
		String desc = testName.replaceAll("_", " ")+" Script";
		testDescription.put(testName+ "-" + browser, desc);

	}


	@AfterMethod(alwaysRun = true)
	public void tearDown()
	{
		calculateTestCaseExecutionTime();
		closeDetailedReport(browser);
		System.out.println("browser :"+strTestName);
		String[] test=strTestName.split("-");
		int stringlength=test.length;
		String currentBrwoser=test[1];           
		if(FailNum!=0)
		{
			testResults.put(tc_name, "FAIL");
			System.out.println("current Browser:"+currentBrwoser);
			this.failCount++;
			Assert.fail();
		}

		else if(PassNum!=0)
		{
			testResults.put(tc_name, "PASS");
			this.passCount++;
		}

		try{
			driver.quit();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	@Parameters({"browser"})
	@AfterClass(alwaysRun=true)
	public void close(String browser){

	}


	public void calculateTestCaseStartTime(){			
		iStartTime = System.currentTimeMillis();
	}


	/***
	 * 		This method is supposed to be used in the @AfterMethod to calculate the total test case execution time 
	 * to show in Reports by taking the start time from the calculateTestCaseStartTime method.
	 */
	public void calculateTestCaseExecutionTime(){	
		iEndTime = System.currentTimeMillis();
		iExecutionTime=(iEndTime-iStartTime);
		TimeUnit.MILLISECONDS.toSeconds(iExecutionTime);
		HtmlReportSupport.executionTime.put(tc_name,String.valueOf(TimeUnit.MILLISECONDS.toSeconds(iExecutionTime)));
	}


	public void onSuccess(String strStepName, String strStepDes) {


		//String fileName= strTestName;
		strTestName=strTestName.replaceAll("_", " ");	
		File file = new File("Results/HTML/" + strTestName+"_Results"
				+ ".html");// "SummaryReport.html"
		Writer writer = null;
		stepNum = stepNum + 1;

		try {
			//testdescrption.put(TestTitleDetails.x.toString(), TestEngine.testDescription.get(TestTitleDetails.x));
			if (!map.get(packageName + ":" + tc_name).equals("FAIL")) {
				map.put(packageName + ":" + tc_name, "PASS");
				//map.put(TestTitleDetails.x.toString(), TestEngine.testDescription.get(TestTitleDetails.x.toString()));
			}
			writer = new FileWriter(file, true);
			writer.write("<tr class='content2' >");
			writer.write("<td>" + stepNum + "</td> ");
			writer.write("<td class='justified'>" + strStepName + "</td>");
			writer.write("<td class='justified'>" + strStepDes + "</td> ");
			if(configProps.getProperty("ExcelReports").equalsIgnoreCase("true")){
				writer.write("<td>PASS</td>");	
			}
			else
				writer.write("<td class='Pass' align='center'><img  src='./Screenshots/passed.ico' width='18' height='18'/></td> ");
			PassNum  =PassNum + 1;
			endStepTime = System.currentTimeMillis();
			stepExecutionTime=TimeUnit.MILLISECONDS.toSeconds(endStepTime-startStepTime);
			ExecutionTime=String.valueOf(stepExecutionTime);
			if(stepExecutionTime<1){
				ExecutionTime="<1";
			}

			writer.write("<td><small>" +  ExecutionTime +" Sec"+  "</small></td> ");
			writer.write("</tr> ");
			writer.close();

		}catch (Exception e) {
			e.printStackTrace();
		}

	}
	public void onWarning(String strStepName, String strStepDes) {
		Writer writer = null;
		try {
			//String fileName= strTestName;
			strTestName=strTestName.replaceAll("_", " ");	

			File file = new File("Results/HTML/" + strTestName+"_Results"
					+ ".html");// "SummaryReport.html"

			writer = new FileWriter(file, true);
			stepNum = stepNum + 1;

			writer.write("<tr class='content2' >");
			writer.write("<td>" + stepNum + "</td> ");
			writer.write("<td class='justified'>" + strStepName + "</td>");
			writer.write("<td class='justified'>" + strStepDes + "</td> ");
			FailNum = FailNum + 1;


			writer.write("<td class='Fail'  align='center'><a  href='"+"./Screenshots"+"/"
					+ strStepDes.replaceAll("[^\\w]", "_")
					+ ".jpeg'"+" alt= Screenshot  width= 15 height=15 style='text-decoration:none;'><img src='./Screenshots/warning.ico' width='18' height='18'/></a></td>");

			endStepTime = System.currentTimeMillis();
			stepExecutionTime=TimeUnit.MILLISECONDS.toSeconds(endStepTime-startStepTime);
			ExecutionTime=String.valueOf(stepExecutionTime);
			if(stepExecutionTime<1){
				ExecutionTime="<1";
			}

			writer.write("<td><small>" +  ExecutionTime +" Sec"+  "</small></td> ");
			writer.write("</tr> ");
			writer.close();

		} catch (Exception e) {

		}

	}


	/*
	 * 
	 * 
	 */
	public void onFailure(String strStepName, String strStepDes, String stepExecTime) {
		Writer writer = null;
		try {
			//String fileName= strTestName;
			strTestName=strTestName.replaceAll("_", " ");			
			File file = new File("Results/HTML/" + strTestName+"_Results"
					+ ".html");// "SummaryReport.html"

			writer = new FileWriter(file, true);
			stepNum = stepNum + 1;

			writer.write("<tr class='content2' >");
			writer.write("<td>" + stepNum + "</td> ");
			writer.write("<td class='justified'>" + strStepName + "</td>");
			writer.write("<td class='justified'>" + strStepDes + "</td> ");
			FailNum = FailNum + 1;

			if(configProps.getProperty("ExcelReports").equalsIgnoreCase("true")){
				writer.write("<td>FAIL</td>");	
			}
			else
				writer.write("<td class='Fail' align='center'><a  href='"+"./Screenshots"+"/"
						+ strStepDes.replaceAll("[^\\w]", "_")
						+ stepExecTime +".jpeg'"+" alt= Screenshot  width= 15 height=15 style='text-decoration:none;'><img  src='./Screenshots/failed.ico' width='18' height='18'/></a></td>");

			endStepTime = System.currentTimeMillis();
			stepExecutionTime=TimeUnit.MILLISECONDS.toSeconds(endStepTime-startStepTime);
			ExecutionTime=String.valueOf(stepExecutionTime);
			if(stepExecutionTime<1){
				ExecutionTime="<1";
			}

			writer.write("<td><small>" +  ExecutionTime +" Sec"+  "</small></td> ");
			writer.write("</tr> ");
			writer.close();
			if (!map.get(packageName + ":" + tc_name).equals("PASS")) {
				map.put(packageName + ":" + tc_name+":", "FAIL");
				//map.put(TestTitleDetails.x.toString(), TestEngine.testDescription.get(TestTitleDetails.x.toString()));
			}
		} catch (Exception e) {

		}

	}

	public void closeDetailedReport(String browser) {

		//String fileName= strTestName;
		strTestName=strTestName.replaceAll("_", " ");	
		File file = new File("Results/HTML/" + strTestName+"_Results"
				+ ".html");// "SummaryReport.html"
		Writer writer = null;

		try {
			writer = new FileWriter(file, true);
			writer.write("</table>");
			writer.write("<table id='footer'>");
			writer.write("<colgroup>");
			writer.write("<col style='width: 25%' />");
			writer.write("<col style='width: 25%' />");
			writer.write("<col style='width: 25%' />");
			writer.write("<col style='width: 25%' />");
			writer.write("</colgroup>");
			writer.write("<tfoot>");
			writer.write("<tr class='heading'> ");
			writer.write("<th colspan='4'>Execution Time In Seconds (Includes Report Creation Time) : "
					+ executionTime.get(tc_name)+ "&nbsp;</th> ");
			writer.write("</tr> ");
			writer.write("<tr class='content'>");
			writer.write("<td class='pass'>&nbsp;Steps Passed&nbsp;:</td>");
			writer.write("<td class='pass'> " + PassNum
					+ "</td>");
			writer.write("<td class='fail'>&nbsp;Steps Failed&nbsp;: </td>");
			writer.write("<td class='fail'>" + FailNum
					+ "</td>");
			writer.write("</tr>");
			writer.close();			

		} catch (Exception e) {

		}
	}

	public void closeSummaryReport(String browser) {	
		File file = new File("Results/HTML/" + "SummaryResults_"+browser+".html");// "SummaryReport.html"
		Writer writer = null;
		try {
			writer = new FileWriter(file, true);

			writer.write("<table id='footer'>");
			writer.write("<colgroup>");
			writer.write("<col style='width: 25%' />");
			writer.write("<col style='width: 25%' />");
			writer.write("<col style='width: 25%' />");
			writer.write("<col style='width: 25%' /> ");
			writer.write("</colgroup> ");
			writer.write("<tfoot>");
			writer.write("<tr class='heading'>");
			writer.write("<th colspan='4'>Total Duration  In Minutes (Including Report Creation) : "
					+ (iSuiteExecutionTime) + "</th>");
			writer.write("</tr>");
			writer.write("<tr class='content'>");
			writer.write("<td class='pass'>&nbsp;Tests Passed&nbsp;:</td>");
			writer.write("<td class='pass'> " + passCount
					+ "</td> ");
			writer.write("<td class='fail'>&nbsp;Tests Failed&nbsp;:</td>");
			writer.write("<td class='fail'> " + failCount
					+ "</td> ");
			writer.write("</tr>");
			writer.write("</tfoot>");
			writer.write("</table> ");

			writer.close();
		} catch (Exception e) {

		}
	}

	public void WriteExcelfile() throws IOException {

		HSSFWorkbook workbook = new HSSFWorkbook();

		File dir=new File("Results/HTML");
		int sheetno = 0;
		int c = 0;
		File[] directoryListing = dir.listFiles();
		if (directoryListing != null) {
			for (File child : directoryListing) {
				if(child.getName().endsWith("html"))
				{
					System.out.println(child.getName());

					System.out.println("Entire File Path:"+child.getAbsolutePath());  
					sheetno++;
					Document html = Jsoup.parse(FileUtils.readFileToString(new File(child.getAbsolutePath())));

					Elements elements = html.body().getElementById("main").getElementsByTag("tr");


					setTrElements(elements.get(0), new Object[elements.get(0).getElementsByTag("th").size() + 1]);                                  

					for (Element Elemen : elements) {

						//setTrElements(Elemen, new Object[Elemen.getElementsByTag("th").size() + 1]);// check this place to remove extra line from excel
						setElements(Elemen,	new Object[Elemen.getElementsByTag("td").size() ]);
					}

					HSSFSheet sheet = workbook.createSheet(child.getName().replace(".html", ""));

					Set<Integer> keyset = data.keySet();



					int rownum = 0;
					for (Integer key : keyset) {
						Row row = sheet.createRow(rownum++);
						Object[] objArr = data.get(key);
						int cellnum = 0;
						for (Object obj : objArr) {
							Cell cell = row.createCell(cellnum++);
							if (obj instanceof Date)
								cell.setCellValue((Date) obj);
							else if (obj instanceof Boolean)
								cell.setCellValue((Boolean) obj);
							else if (obj instanceof String)
								cell.setCellValue((String) obj);
							else if (obj instanceof Double)
								cell.setCellValue((Double) obj);

							sheet.autoSizeColumn(cellnum);
						}
					}

					try {
						FileOutputStream out = new FileOutputStream(new File("Results/HTML/SummaryResults.xls"));

						workbook.write(out);
						out.close();
						System.out.println("Excel written successfully..");

					} catch (FileNotFoundException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
					for(int i = 0; i<rownum;i++)
					{
						Object key = data.keySet().iterator().next();
						data.remove(key);
					}
				}
				else
				{
				}

			}
		} 
		else {
		}


	}


	private void setTrElements(Element es, Object[] m) {

		int y = 0;
		for (Element k : es.getElementsByTag("th")) {
			m[y++] = Jsoup.parse(k.childNodes().get(0).toString()).text();
		}

		data.put(h, m);
		h++;
	}

	private void setElements(Element es, Object[] m) {
		int y = 0;
		for (Element k : es.getElementsByTag("td")) {
			m[y++] = Jsoup.parse(k.childNodes().get(0).toString()).text();
		}

		data.put(h, m);
		h++;
	}

	//////////////////////////////////////////////////////////////////////////
	synchronized public static void createSummaryFinalReport() throws Exception {

		File file = new File("Results/HTML/" + "SummaryResults.htm");// "SummaryReport.htm"
		Writer writer=new FileWriter(file, true);
		Map<String, String> treeMap = new TreeMap<String, String>(map);
		Map<String, String> map=new LinkedHashMap<String, String> (treeMap);

		Iterator<Entry<String, String>> iterator = map.entrySet().iterator();
		System.out.println("MAP SIZE Total:"+map.size());
		System.out.println("MAP :"+map);
		while (iterator.hasNext()) {
			@SuppressWarnings("rawtypes")
			Map.Entry mapEntry = (Map.Entry) iterator.next();
			key = mapEntry.getKey().toString().split(":");
			String value = (String) mapEntry.getValue();
			String time=executionTime.get(key[1]);
			if(time !=null && !(time.equals("0"))){  
				if(key[1].contains("Firefox")){
					Firefoxmap.put(key[1], value);
				}
				else if(key[1].contains("Chrome")){
					Chromemap.put(key[1], value);
				}
				else if(key[1].contains("IE")){
					IEmap.put(key[1], value);
				}
				else if(key[1].contains("Android")){
					Androidmap.put(key[1], value);
				}
				else if(key[1].contains("Iphone")){
					Iphonemap.put(key[1], value);
				}
			}

		}

		System.out.println("Firefox MAP SIZE Total:"+Firefoxmap.size());
		System.out.println("Firefox MAP :"+Firefoxmap);

		createSummaryFinalreportHeader();


		// need to call createSummaryBody from here
		if(Firefoxmap.size()>0){
			createSummaryFinalreportBody("Firefox");
					

		}
		if(Chromemap.size()>0){
			createSummaryFinalreportBody("Chrome");
			


		}
		if(IEmap.size()>0){
			createSummaryFinalreportBody("IE");


		}
			/*if(Androidmap.size()>0){
createSummaryFinalreportBody("Android",webUrl,"Web");
createSummaryFinalreportBody("Android",mobileUrl,"Mobile");
createSummaryFinalreportBody("Android",agentUrl,"Agent");


}
if(Iphonemap.size()>0){
createSummaryFinalreportBody("Iphone",webUrl,"Web");
createSummaryFinalreportBody("Iphone",mobileUrl,"Mobile");
createSummaryFinalreportBody("Iphone",agentUrl,"Agent");


}*/
	}


	public static void createSummaryFinalreportHeader() throws IOException{
		File file = new File("Results/HTML/" + "SummaryResults.html");// "SummaryReport.htm"
		Writer writer = null;

		writer = new FileWriter(file);
		try {						
			writer.write("<!DOCTYPE html>");
			writer.write("<html> ");
			writer.write("<head> ");
			writer.write("<meta charset='UTF-8'> ");
			writer.write("<title>Automation Execution Results Summary</title>");

			writer.write("<style type='text/css'>");
			writer.write("body {");
			writer.write("background-color: #FFFFFF; ");
			writer.write("font-family: Verdana, Geneva, sans-serif; ");
			writer.write("text-align: center; ");
			writer.write("} ");

			writer.write("small { ");
			writer.write("font-size: 0.7em; ");
			writer.write("} ");

			writer.write("table { ");
			writer.write("box-shadow: 9px 9px 10px 4px #BDBDBD;");
			writer.write("border: 0px solid #4D7C7B;");
			writer.write("border-collapse: collapse; ");
			writer.write("border-spacing: 0px; ");
			writer.write("width: 1000px; ");
			writer.write("margin-left: auto; ");
			writer.write("margin-right: auto; ");
			writer.write("} ");

			writer.write("tr.heading { ");
			writer.write("background-color: #041944;");
			writer.write("color: #FFFFFF; ");
			writer.write("font-size: 0.7em; ");
			writer.write("font-weight: bold; ");
			writer.write("background:-o-linear-gradient(bottom, #999999 5%, #000000 100%);	background:-webkit-gradient( linear, left top, left bottom, color-stop(0.05, #999999), color-stop(1, #000000) );");
			writer.write("background:-moz-linear-gradient( center top, #999999 5%, #000000 100% );");
			writer.write("filter:progid:DXImageTransform.Microsoft.gradient(startColorstr=#999999, endColorstr=#000000);	background: -o-linear-gradient(top,#999999,000000);");
			writer.write("} ");

			writer.write("tr.subheading { ");
			writer.write("background-color: #6A90B6;");
			writer.write("color: #000000; ");
			writer.write("font-weight: bold; ");
			writer.write("font-size: 0.7em; ");
			writer.write("text-align: justify; ");
			writer.write("} ");

			writer.write("tr.section { ");
			writer.write("background-color: #A4A4A4; ");
			writer.write("color: #333300; ");
			writer.write("cursor: pointer; ");
			writer.write("font-weight: bold;");
			writer.write("font-size: 0.8em; ");
			writer.write("text-align: justify;");
			writer.write("background:-o-linear-gradient(bottom, #56aaff 5%, #e5e5e5 100%);	background:-webkit-gradient( linear, left top, left bottom, color-stop(0.05, #56aaff), color-stop(1, #e5e5e5) );");
			writer.write("background:-moz-linear-gradient( center top, #56aaff 5%, #e5e5e5 100% );");
			writer.write("filter:progid:DXImageTransform.Microsoft.gradient(startColorstr=#56aaff, endColorstr=#e5e5e5);	background: -o-linear-gradient(top,#56aaff,e5e5e5);");

			writer.write("} ");

			writer.write("tr.subsection { ");
			writer.write("cursor: pointer; ");
			writer.write("} ");

			writer.write("tr.content { ");
			writer.write("background-color: #FFFFFF; ");
			writer.write("color: #000000; ");
			writer.write("font-size: 0.7em; ");
			writer.write("display: table-row; ");
			writer.write("} ");

			writer.write("tr.content2 { ");
			writer.write("background-color:#;E1E1E1");
			writer.write("border: 1px solid #4D7C7B;");
			writer.write("color: #000000; ");
			writer.write("font-size: 0.7em; ");
			writer.write("display: table-row; ");
			writer.write("} ");

			writer.write("td, th { ");
			writer.write("padding: 5px; ");
			writer.write("border: 1px solid #4D7C7B; ");
			writer.write("text-align: inherit\0/; ");
			writer.write("} ");

			writer.write("th.Logos { ");
			writer.write("padding: 5px; ");
			writer.write("border: 0px solid #4D7C7B; ");
			writer.write("text-align: inherit /;");
			writer.write("} ");

			writer.write("td.justified { ");
			writer.write("text-align: justify; ");
			writer.write("} ");

			writer.write("td.pass {");
			writer.write("font-weight: bold; ");
			writer.write("color: green; ");
			writer.write("} ");

			writer.write("td.fail { ");
			writer.write("font-weight: bold; ");
			writer.write("color: red; ");
			writer.write("} ");

			writer.write("td.done, td.screenshot { ");
			writer.write("font-weight: bold; ");
			writer.write("color: black; ");
			writer.write("} ");

			writer.write("td.debug { ");
			writer.write("font-weight: bold; ");
			writer.write("color: blue; ");
			writer.write("} ");

			writer.write("td.warning { ");
			writer.write("font-weight: bold; ");
			writer.write("color: orange; ");
			writer.write("} ");
			writer.write("</style> ");

			writer.write("<script> ");
			writer.write("function toggleMenu(objID) { ");
			writer.write(" if (!document.getElementById) return;");
			writer.write(" var ob = document.getElementById(objID).style; ");
			writer.write("if(ob.display === 'none') { ");
			writer.write(" try { ");
			writer.write(" ob.display='table-row-group';");
			writer.write("} catch(ex) { ");
			writer.write("	 ob.display='block'; ");
			writer.write("} ");
			writer.write("} ");
			writer.write("else { ");
			writer.write(" ob.display='none'; ");
			writer.write("} ");
			writer.write("} ");
			writer.write("function toggleSubMenu(objId) { ");
			writer.write("for(i=1; i<10000; i++) { ");
			writer.write("var ob = document.getElementById(objId.concat(i)); ");
			writer.write("if(ob === null) { ");
			writer.write("break; ");
			writer.write("} ");
			writer.write("if(ob.style.display === 'none') { ");
			writer.write("try { ");
			writer.write(" ob.style.display='table-row'; ");
			writer.write("} catch(ex) { ");
			writer.write("ob.style.display='block'; ");
			writer.write("} ");
			writer.write(" } ");
			writer.write("else { ");
			writer.write("ob.style.display='none'; ");
			writer.write("} ");
			writer.write(" } ");
			writer.write("} ");
			writer.write("</script> ");
			writer.write("</head> ");
			writer.write("<body> ");
			writer.write("</br>");

		} catch (Exception e) {

		}

		finally {
			writer.flush();
			writer.close();
		}
	}




	public static void createSummaryFinalreportBody(String browser) throws IOException{
		File file = new File("Results/HTML/" + "SummaryResults.html");// "SummaryReport.htm"
		Writer writer = null;

		writer = new FileWriter(file, true);

		try {						

			writer.write("<br> ");
			writer.write("<br> ");
			writer.write("<br> ");
			writer.write("<table id='Logos'>");
			writer.write("<colgroup>");
			writer.write("<col style='width: 25%' />");
			writer.write("<col style='width: 25%' />");
			writer.write("<col style='width: 25%' />");
			writer.write("<col style='width: 25%' />");
			writer.write("</colgroup> ");
			writer.write("<thead> ");

			writer.write("<tr class='content'>");
			writer.write("<th class ='Logos' colspan='2' >");
			writer.write("<img align ='left' src= ./Screenshots/logo.png></img>");
			writer.write("</th>");
			writer.write("<th class = 'Logos' colspan='2' > ");
			writer.write("<img align ='right' src= ./Screenshots/cigniti.png></img>");
			writer.write("</th> ");
			writer.write("</tr> ");

			writer.write("</thead> ");
			writer.write("</table> ");

			writer.write("<table id='header'> ");
			writer.write("<colgroup> ");
			writer.write("<col style='width: 25%' /> ");
			writer.write("<col style='width: 25%' /> ");
			writer.write("<col style='width: 25%' /> ");
			writer.write(" <col style='width: 25%' /> ");
			writer.write("</colgroup> ");

			writer.write("<thead> ");

			writer.write("<tr class='heading'> ");
			writer.write("<th colspan='4' style='font-family:Copperplate Gothic Bold; font-size:1.4em;'> ");
			writer.write("Automation Execution Result Summary ");
			writer.write("</th> ");
			writer.write("</tr> ");
			writer.write("<tr class='subheading'> ");
			writer.write("<th>&nbsp;Date&nbsp;&&nbsp;Time&nbsp;:&nbsp;" + ""
					+ "</th> ");
			// writer.write("<th>&nbsp;:&nbsp;08-Apr-2013&nbsp;06:24:21&nbsp;PM</th> ");
			writer.write("<th> &nbsp;" + ReportStampSupport.dateTime()
					+ "&nbsp;</th> ");
			writer.write("<th>&nbsp;View&nbsp;:&nbsp;</th> ");
			writer.write("<th>Web,Mobile </th> ");
			writer.write("</tr> ");

			writer.write("<tr class='subheading'> ");
			writer.write("<th>&nbsp;Host Name&nbsp;:</th> ");
			writer.write("<th>" + InetAddress.getLocalHost().getHostName()
					+ "</th> ");
			writer.write("<th>&nbsp;Browser&nbsp;:</th> ");
			writer.write("<th>" + browser + "</th> ");
			writer.write("</tr> ");
			writer.write("</thead> ");
			writer.write("</table> ");
			writer.write("<table id='main'> ");
			writer.write("<colgroup> ");
			writer.write("<col style='width: 5%' /> ");
			writer.write("<col style='width: 35%' /> ");
			writer.write("<col style='width: 42%' /> ");
			writer.write("<col style='width: 10%' /> ");
			writer.write("<col style='width: 8%' /> ");
			writer.write("</colgroup> ");
			writer.write("<thead> ");
			writer.write("<tr class='heading'> ");
			writer.write("<th>S.NO</th> ");
			writer.write("<th>Test Case</th> ");
			writer.write("<th>Description</th> ");
			writer.write("<th>Time</th> ");
			writer.write("<th>Status</th> ");
			writer.write("</tr> ");
			writer.write("</thead> ");

			Iterator<Entry<String, String>> iterator = null;
			long SuiteTime=0;

			if(browser.equalsIgnoreCase("Firefox")){
				iterator = Firefoxmap.entrySet().iterator();
				System.out.println("Map Before Creating summary Report: "+Firefoxmap);
			}
			else if(browser.equalsIgnoreCase("Chrome")){
				iterator = Chromemap.entrySet().iterator();
				System.out.println("Map Before Creating summary Report: "+Chromemap);
			}	
			else if(browser.equalsIgnoreCase("IE")){
				iterator = IEmap.entrySet().iterator();
				System.out.println("Map Before Creating summary Report: "+IEmap);
			}
			else if(browser.equalsIgnoreCase("Android")){
				iterator = Androidmap.entrySet().iterator();
				System.out.println("Map Before Creating summary Report: "+Androidmap);
			}	
			else if(browser.equalsIgnoreCase("Iphone")){
				iterator = Iphonemap.entrySet().iterator();
				System.out.println("Map Before Creating summary Report: "+Iphonemap);
			}
			int serialNo = 1;
			writer.write("<tbody> ");

			while (iterator.hasNext()) {
				@SuppressWarnings("rawtypes")
				Map.Entry mapEntry = (Map.Entry) iterator.next();
				String  key = mapEntry.getKey().toString();
				String value = (String) mapEntry.getValue();
				String time=executionTime.get(key);				
				if (key.contains(browser)) {											
					writer.write("<tr class='content2' > ");
					writer.write("<td class='justified'>" + serialNo + "</td>");
					serialNo = serialNo + 1;				
					if (value.equals("PASS")) {					
						writer.write("<td class='justified'><a href='" + key.replaceAll("_", " ")
								+ "_Results" + ".html'"
								+ "' target='about_blank'>"
								+ key.substring(0, key.indexOf("-"))
								+ "</a></td>");
					} else {
						writer.write("<td class='justified'><a href='" + key.replaceAll("_", " ")
								+ "_Results" + ".html'"
								+ " target='about_blank'>"
								+ key.substring(0, key.indexOf("-"))
								+ "</a></td>");
					}
					writer.write("<td class='justified'>"
							+ TestEngine.testDescription.get(key) + "</td>");                  

					writer.write("<td>" + executionTime.get(key)
							+ " Seconds</td>");
					SuiteTime+=Integer.parseInt(executionTime.get(key));
					if (TestEngine.testResults.get(key).equals("PASS"))
						writer.write("<td class='pass'>Passed</td> ");
					else
						writer.write("<td class='fail'>Failed</td> ");
					writer.write("</tr>");
				}

			}
			writer.write("</tbody> ");

			writer.flush();
			writer.close();
			String STime = String.format("%dMin %dSec", 
					TimeUnit.SECONDS.toMinutes(SuiteTime),
					SuiteTime - TimeUnit.MINUTES.toSeconds(TimeUnit.SECONDS.toMinutes(SuiteTime))) ;
			closeSummaryFinalReport(browser,STime);

		} catch (Exception e) {

		}

		finally {
			try{
				writer.flush();
				writer.close();

			}
			catch(Exception e){}
		}

	}


	public static void closeSummaryFinalReport(String browser,String STime) {
		int passCounter =0,failCounter =0,WebPass=0,WebFail=0,MobilePass=0,MobileFail=0,AgentPass=0,AgentFail=0;	
		Iterator<Entry<String, String>> iterator = null;
		if(browser.equalsIgnoreCase("Firefox")){			
			iterator = Firefoxmap.entrySet().iterator();
			while (iterator.hasNext()) {
				@SuppressWarnings("rawtypes")
				Map.Entry mapEntry = (Map.Entry) iterator.next();
				String  key = mapEntry.getKey().toString();
				String value = (String) mapEntry.getValue();
				String time=executionTime.get(key);				
				
				if (key.contains("Mobile")) {							

					if (TestEngine.testResults.get(key).equals("PASS")){					
						MobilePass+=1;
					} else if (TestEngine.testResults.get(key).equals("FAIL")) {
						MobileFail+=1;
					}

				}
				else {
					if (TestEngine.testResults.get(key).equals("PASS")){					
						WebPass+=1;
					} else if (TestEngine.testResults.get(key).equals("FAIL")) {
						WebFail+=1;
					}
				}
			}

		}
		if(browser.equalsIgnoreCase("Chrome")){			
			iterator = Chromemap.entrySet().iterator();
			while (iterator.hasNext()) {
				@SuppressWarnings("rawtypes")
				Map.Entry mapEntry = (Map.Entry) iterator.next();
				String  key = mapEntry.getKey().toString();
				String value = (String) mapEntry.getValue();
				String time=executionTime.get(key);				
				
				if (key.contains("Mobile")) {								

					if (TestEngine.testResults.get(key).equals("PASS")){					
						MobilePass+=1;
					} else if (TestEngine.testResults.get(key).equals("FAIL")) {
						MobileFail+=1;
					}

				}
				else  {	

					if (TestEngine.testResults.get(key).equals("PASS")){					
						WebPass+=1;
					} else if (TestEngine.testResults.get(key).equals("FAIL")) {
						WebFail+=1;
					}

				}	
			}

		}
		if(browser.equalsIgnoreCase("IE")){			
			iterator = IEmap.entrySet().iterator();
			while (iterator.hasNext()) {
				@SuppressWarnings("rawtypes")
				Map.Entry mapEntry = (Map.Entry) iterator.next();
				String  key = mapEntry.getKey().toString();
				String value = (String) mapEntry.getValue();
				String time=executionTime.get(key);				
				
				if (key.contains("Mobile")) {								

					if (TestEngine.testResults.get(key).equals("PASS")){					
						MobilePass+=1;
					} else if (TestEngine.testResults.get(key).equals("FAIL")) {
						MobileFail+=1;
					}

				}
				else  {	

					if (TestEngine.testResults.get(key).equals("PASS")){					
						WebPass+=1;
					} else if (TestEngine.testResults.get(key).equals("FAIL")) {
						WebFail+=1;
					}

				}
			}

		}
		passCounter=WebPass+MobilePass;
		failCounter=WebFail+MobileFail;
		File file = new File("Results/HTML/" + "SummaryResults.html");// "SummaryReport.htm"
		Writer writer = null;
		try {
			writer = new FileWriter(file, true);

			writer.write("<table id='footer'>");
			writer.write("<colgroup>");
			writer.write("<col style='width: 25%' />");
			writer.write("<col style='width: 25%' />");
			writer.write("<col style='width: 25%' />");
			writer.write("<col style='width: 25%' /> ");
			writer.write("</colgroup> ");
			writer.write("<tfoot>");
			writer.write("<tr class='heading'>");
			writer.write("<th colspan='4'>Total Duration  In Minutes : "
					+ STime + "</th>");
			writer.write("</tr>");
			writer.write("<tr class='content'>");
			writer.write("<td class='pass'>&nbsp;Total Tests Passed&nbsp;:</td>");
			writer.write("<td class='pass'> " + passCounter
					+ "</td> ");
			writer.write("<td class='fail'>&nbsp;Total Tests Failed&nbsp;:</td>");
			writer.write("<td class='fail'> " + failCounter
					+ "</td> ");
			writer.write("</tr>");
			writer.write("<tr class='content'>");
			writer.write("<td class='pass'>&nbsp;Tests Passed in WEB&nbsp;:</td>");
			writer.write("<td class='pass'> " + WebPass
					+ "</td> ");
			writer.write("<td class='fail'>&nbsp;Tests Failed in WEB&nbsp;:</td>");
			writer.write("<td class='fail'> " + WebFail
					+ "</td> ");
			writer.write("</tr>");
			writer.write("<tr class='content'>");
			writer.write("<td class='pass'>&nbsp;Tests Passed in MOBILE&nbsp;:</td>");
			writer.write("<td class='pass'> " + MobilePass
					+ "</td> ");
			writer.write("<td class='fail'>&nbsp;Tests Failed in MOBILE&nbsp;:</td>");
			writer.write("<td class='fail'> " + MobileFail
					+ "</td> ");
			writer.write("</tr>");			
			writer.write("</tfoot>");
			writer.write("</table> ");

			writer.flush();
			writer.close();
		} catch (Exception e) {

		}
	}

}

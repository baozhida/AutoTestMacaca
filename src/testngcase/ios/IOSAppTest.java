package testngcase.ios;

//import static org.testng.AssertJUnit.assertTrue;
//import static org.testng.AssertJUnit.fail;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.annotations.AfterMethod;

import com.alibaba.fastjson.JSONObject;

import macaca.client.MacacaClient;
import macaca.client.commands.Element;



public class IOSAppTest {
    
	private String udid,proxyport,port;
	boolean isFail;
	int initcount = 0;

    private MacacaClient driver = new MacacaClient();
    
    public MacacaClient initDriver() throws Exception {
    	initcount = initcount +1;
    	System.out.println("-----设备"+udid+"---第"+initcount+"次初始化-------------");
    	String platform = "IOS";

        JSONObject porps = new JSONObject();
        porps.put("platformName", platform);
        porps.put("app", "./app/TuNiuApp.app");
        //0: 启动并安装 app。1 (默认): 卸载并重装 app。 2: 仅重装 app。3: 在测试结束后保持 app 状态。
        porps.put("reuse", 3);
        porps.put("udid", udid);
        porps.put("proxyPort", Integer.parseInt(proxyport));
        JSONObject desiredCapabilities = new JSONObject();
        desiredCapabilities.put("desiredCapabilities", porps);
        desiredCapabilities.put("host", "127.0.0.1");
        desiredCapabilities.put("port", Integer.parseInt(port)); 
        
        if(port.equals("3457")){
        	driver.sleep(2000);
        }
        return driver.initDriver(desiredCapabilities); 
    }

    @BeforeTest
    @Parameters({ "port", "proxyport", "udid" })  
    public void getpara(String port, String proxyport, String udid) {
          this.port = port;
          this.proxyport = proxyport;
          this.udid = udid;
      }
    @BeforeClass
    public void setUp() throws Exception {
    	
    	initDriver();
    }
    
    @BeforeMethod
    public void beforecase() throws Exception {
    	//重置isFail,判读是否执行 aftercase方法
    	isFail = true;
    }

    @Test
    public void case_1进入旅游() {
        // set screenshot save path
        //String courseFile = directory.getCanonicalPath();
        System.out.println("------------1-点击旅游图片，进入旅游频道------------------");
        try {
			driver.sleep(5000);
			Element  el = driver.elementByXPath("//XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[1]"
	        		+ "/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]"
	        		+ "/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]"
	        		+ "/XCUIElementTypeCollectionView[1]/XCUIElementTypeCell[3]/XCUIElementTypeOther[1]"
	        		+ "/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeImage[1]");
	        System.out.println("------------旅游控件存在------------------");

	        el.click();
	        //判断到达新页面
	        assertTrueReWrite("assertTrue:元素未找到！",driver.sleep(5000).isElementExist("xpath", "//XCUIElementTypeStaticText[@name='旅游']/following-sibling::XCUIElementTypeOther"));
	        //screenShot();
	        System.out.println("------------控件存在------------------");

	        //返回首页
	        driver.elementByXPath("//XCUIElementTypeButton[@name='tab channel topbar back']").click();        
	        driver.sleep(2000);
	        isFail = false;
		} catch (Exception e) {
			failReWrite(e.toString()+e.getLocalizedMessage());
		}

    }
    
    
    @Test
    public void case_2进入跟团游() {

        System.out.println("------------2-点击跟团游图片------------------");
        try {
			Element  el = driver.elementByXPath("//XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[1]"
	        		+ "/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]"
	        		+ "/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]"
	        		+ "/XCUIElementTypeCollectionView[1]/XCUIElementTypeCell[3]/XCUIElementTypeOther[1]"
	        		+ "/XCUIElementTypeOther[1]/XCUIElementTypeOther[2]/XCUIElementTypeImage[1]");
	        System.out.println("------------跟团游控件存在------------------");

	        el.click();
	        //判断到达新页面
	        assertTrueReWrite("assertTrue:元素未找到！",driver.sleep(5000).isElementExist("xpath", "//XCUIElementTypeStaticText[@name='跟团游']/following-sibling::XCUIElementTypeOther"));
	        System.out.println("------------控件存在------------------");

	        //返回首页
	        driver.elementByXPath("//XCUIElementTypeButton[@name='tab channel topbar back']").click();        
	        driver.sleep(2000);
	        isFail = false;
		} catch (Exception e) {
			failReWrite(e.toString());
		}
    }
     
    @Test
    public void case_3进入自助游() {

        System.out.println("------------3-点击自助游图片------------------");
        
        try {
			Element  el = driver.elementByXPath("//XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[1]"
	        		+ "/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]"
	        		+ "/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]"
	        		+ "/XCUIElementTypeCollectionView[1]/XCUIElementTypeCell[3]/XCUIElementTypeOther[1]"
	        		+ "/XCUIElementTypeOther[1]/XCUIElementTypeOther[3]/XCUIElementTypeImage[1]");
	        System.out.println("------------控件存在------------------");

	        el.click();
	        //判断到达新页面
	        assertTrueReWrite("assertTrue:元素未找到！",driver.sleep(5000).isElementExist("xpath", "//XCUIElementTypeStaticText[@name='自助游']/following-sibling::XCUIElementTypeOther"));
	        System.out.println("------------控件存在------------------");

	        //返回首页
	        driver.elementByXPath("//XCUIElementTypeButton[@name='tab channel topbar back']").click();        
	        driver.sleep(2000);
	        isFail = false;
		} catch (Exception e) {
			failReWrite(e.toString());
		}
    }

    @Test
    public void case_4进入邮轮() {

        System.out.println("------------4-点击邮轮图片------------------");
        
        try {
			Element  el = driver.elementByXPath("//XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[1]"
	        		+ "/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]"
	        		+ "/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]"
	        		+ "/XCUIElementTypeCollectionView[1]/XCUIElementTypeCell[3]/XCUIElementTypeOther[1]"
	        		+ "/XCUIElementTypeOther[1]/XCUIElementTypeOther[4]/XCUIElementTypeImage[1]");
	        System.out.println("------------控件存在------------------");

	        el.click();
	        //判断到达新页面
	        assertTrueReWrite("assertTrue:元素未找到！",driver.sleep(5000).isElementExist("xpath", "//XCUIElementTypeStaticText[@name='全部航线']"));
	        System.out.println("------------控件存在------------------");

	        //返回首页
	        driver.elementByXPath("//XCUIElementTypeButton[@name='base topbar icon nav back']").click();        
	        driver.sleep(2000);
	        isFail = false;
		} catch (Exception e) {
			failReWrite(e.toString());
		}

    }
    
    @Test
    public void case_5进入自驾游() {

        System.out.println("------------5-点击自驾游图片------------------");
        
        try {
        	Element  el = driver.elementByXPath("//XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[1]"
	        		+ "/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]"
	        		+ "/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]"
	        		+ "/XCUIElementTypeCollectionView[1]/XCUIElementTypeCell[3]/XCUIElementTypeOther[1]"
	        		+ "/XCUIElementTypeOther[1]/XCUIElementTypeOther[5]/XCUIElementTypeImage[1]");
	        System.out.println("------------控件存在------------------");
	        el.click();
	        //判断到达新页面
	        assertTrueReWrite("assertTrue:元素未找到！",driver.sleep(5000).isElementExist("xpath", "//XCUIElementTypeStaticText[@name='自驾游']/following-sibling::XCUIElementTypeOther"));
	        System.out.println("------------控件存在------------------");

	        //返回首页
	        driver.elementByXPath("//XCUIElementTypeButton[@name='tab channel topbar back']").click();        
	        driver.sleep(2000);
	        isFail = false;
		} catch (Exception e) {
			failReWrite(e.toString());
		}

    }
    
    @Test
    public void case_6进入出境游() {

        System.out.println("------------6-点击出境游图片------------------");
        
        try {
			Element  el = driver.elementByXPath("//XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[1]"
	        		+ "/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]"
	        		+ "/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]"
	        		+ "/XCUIElementTypeCollectionView[1]/XCUIElementTypeCell[5]/XCUIElementTypeOther[1]"
	        		+ "/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeImage[1]");
	        System.out.println("------------控件存在------------------");
	        el.click();
	        //判断到达新页面
	        assertTrueReWrite("assertTrue:元素未找到！",driver.sleep(5000).isElementExist("xpath", "//XCUIElementTypeStaticText[@name='出境游']/following-sibling::XCUIElementTypeOther"));
	        System.out.println("------------控件存在------------------");

	        //返回首页
	        driver.elementByXPath("//XCUIElementTypeButton[@name='tab channel topbar back']").click();        
	        driver.sleep(2000);
	        isFail = false;
		} catch (Exception e) {
			failReWrite(e.toString());
		}
    }
    
    @Test
    public void case_7进入国内游() throws Exception {

        System.out.println("------------7-点击国内游图片------------------");
        
        try {
			Element  el = driver.elementByXPath("//XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[1]"
	        		+ "/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]"
	        		+ "/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]"
	        		+ "/XCUIElementTypeCollectionView[1]/XCUIElementTypeCell[5]/XCUIElementTypeOther[1]"
	        		+ "/XCUIElementTypeOther[1]/XCUIElementTypeOther[2]/XCUIElementTypeImage[1]");
	        System.out.println("------------控件存在------------------");
	        el.click();
	        //判断到达新页面
	        assertTrueReWrite("assertTrue:元素未找到！",driver.sleep(5000).isElementExist("xpath", "//XCUIElementTypeStaticText[@name='国内游']/following-sibling::XCUIElementTypeOther"));
	        System.out.println("------------控件存在------------------");

	        //返回首页
	        driver.elementByXPath("//XCUIElementTypeButton[@name='tab channel topbar back']").click();        
	        driver.sleep(2000);
	        isFail = false;
		} catch (Exception e) {
			failReWrite(e.toString());
		}
    }
    
    @Test
    public void case_8进入周边游() throws Exception {

        System.out.println("------------8-点击周边游图片------------------");
        
        try {
			Element  el = driver.elementByXPath("//XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[1]"
	        		+ "/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]"
	        		+ "/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]"
	        		+ "/XCUIElementTypeCollectionView[1]/XCUIElementTypeCell[5]/XCUIElementTypeOther[1]"
	        		+ "/XCUIElementTypeOther[1]/XCUIElementTypeOther[3]/XCUIElementTypeImage[1]");
	        System.out.println("------------控件存在------------------");
	        el.click();
	        //判断到达新页面
	        assertTrueReWrite("assertTrue:元素未找到！",driver.sleep(5000).isElementExist("xpath", "//XCUIElementTypeStaticText[@name='周边游']/following-sibling::XCUIElementTypeOther"));
	        System.out.println("------------控件存在------------------");

	        //返回首页
	        driver.elementByXPath("//XCUIElementTypeButton[@name='tab channel topbar back']").click();        
	        driver.sleep(2000);
	        isFail = false;
		} catch (Exception e) {
			failReWrite(e.toString());
		}
    }
    
    @Test
    public void case_9进入机票频道() {

        System.out.println("------------9-点击机票图片------------------");
        
        try {
			Element  el = driver.elementByXPath("//XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[1]"
	        		+ "/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]"
	        		+ "/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]"
	        		+ "/XCUIElementTypeCollectionView[1]/XCUIElementTypeCell[7]/XCUIElementTypeOther[1]"
	        		+ "/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeImage[1]");
	        System.out.println("------------控件存在------------------");
	        el.click();
	        //判断到达新页面
	        assertTrueReWrite("assertTrue:元素未找到！",driver.sleep(5000).isElementExist("xpath", "//XCUIElementTypeStaticText[@name='机票']"));
	        assertTrueReWrite("assertTrue:元素未找到！",driver.isElementExist("xpath", "//XCUIElementTypeButton[@name='查询']"));
	        System.out.println("------------控件存在------------------");

	        //返回首页
	        driver.elementByXPath("//XCUIElementTypeButton[@name='airplane ticket home back butt']").click();        
	        driver.sleep(2000);
	        isFail = false;
		} catch (Exception e) {
			failReWrite(e.toString());
		}
    }
    
    @Test
    public void case_10进入国际机票频道() {

        System.out.println("------------10-点击国际机票图片------------------");
        
        try {
			Element  el = driver.elementByXPath("//XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[1]"
	        		+ "/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]"
	        		+ "/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]"
	        		+ "/XCUIElementTypeCollectionView[1]/XCUIElementTypeCell[7]/XCUIElementTypeOther[1]"
	        		+ "/XCUIElementTypeOther[1]/XCUIElementTypeOther[2]/XCUIElementTypeImage[1]");
	        System.out.println("------------控件存在------------------");
	        el.click();
	        //判断到达新页面
	        assertTrueReWrite("assertTrue:元素未找到！",driver.sleep(5000).isElementExist("xpath", "//XCUIElementTypeStaticText[@name='机票']"));
	        assertTrueReWrite("assertTrue:元素未找到！",driver.isElementExist("xpath", "//XCUIElementTypeButton[@name='查询']"));
	        System.out.println("------------控件存在------------------");

	        //返回首页
	        driver.elementByXPath("//XCUIElementTypeButton[@name='airplane ticket home back butt']").click();        
	        driver.sleep(2000);
	        isFail = false;
		} catch (Exception e) {
			failReWrite(e.toString());
		}
    }
    
    @Test
    public void case_11进入火车票频道() {

        System.out.println("------------11-点击火车票图片------------------");
        
        try {
			Element  el = driver.elementByXPath("//XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[1]"
	        		+ "/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]"
	        		+ "/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]"
	        		+ "/XCUIElementTypeCollectionView[1]/XCUIElementTypeCell[7]/XCUIElementTypeOther[1]"
	        		+ "/XCUIElementTypeOther[1]/XCUIElementTypeOther[3]/XCUIElementTypeImage[1]");
	        System.out.println("------------控件存在------------------");
	        el.click();
	        //判断到达新页面
	        assertTrueReWrite("assertTrue:元素未找到！",driver.sleep(5000).isElementExist("xpath", "//XCUIElementTypeStaticText[@name='火车票']"));
	        assertTrueReWrite("assertTrue:元素未找到！",driver.isElementExist("xpath", "//XCUIElementTypeButton[@name='查询']"));
	        System.out.println("------------控件存在------------------");

	        //返回首页
	        driver.elementByXPath("//XCUIElementTypeButton[@name='train home return']").click();        
	        driver.sleep(2000);
	        isFail = false;
		} catch (Exception e) {
			failReWrite(e.toString());
		}
    }
    
    @Test
    public void case_12进入汽车票频道() {

        System.out.println("------------12-点击汽车票图片------------------");
        
        try {
			Element  el = driver.elementByXPath("//XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[1]"
	        		+ "/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]"
	        		+ "/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]"
	        		+ "/XCUIElementTypeCollectionView[1]/XCUIElementTypeCell[7]/XCUIElementTypeOther[1]"
	        		+ "/XCUIElementTypeOther[1]/XCUIElementTypeOther[4]/XCUIElementTypeImage[1]");
	        System.out.println("------------控件存在------------------");
	        el.click();
	        //判断到达新页面
	        assertTrueReWrite("assertTrue:元素未找到！",driver.sleep(5000).isElementExist("xpath", "//XCUIElementTypeStaticText[@name='汽车票']"));
	        assertTrueReWrite("assertTrue:元素未找到！",driver.isElementExist("xpath", "//XCUIElementTypeButton[@name='查询']"));
	        System.out.println("------------控件存在------------------");

	        //返回首页
	        driver.elementByXPath("//XCUIElementTypeButton[@name='bus ticket home topLeft button']").click();        
	        driver.sleep(2000);
	        isFail = false;
		} catch (Exception e) {
			failReWrite(e.toString());
		}
    }
    
    @Test
    public void case_13进入租车频道() {

        System.out.println("------------13-点击租车图片------------------");
        
        try {
			Element  el = driver.elementByXPath("//XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[1]"
	        		+ "/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]"
	        		+ "/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]"
	        		+ "/XCUIElementTypeCollectionView[1]/XCUIElementTypeCell[7]/XCUIElementTypeOther[1]"
	        		+ "/XCUIElementTypeOther[1]/XCUIElementTypeOther[5]/XCUIElementTypeImage[1]");
	        System.out.println("------------控件存在------------------");
	        el.click();
	        //判断到达新页面
	        assertTrueReWrite("assertTrue:元素未找到！",driver.sleep(5000).isElementExist("xpath", "//XCUIElementTypeStaticText[@name='出行用车']"));
	        assertTrueReWrite("assertTrue:元素未找到！",driver.isElementExist("xpath", "//XCUIElementTypeStaticText[@name='国内用车']"));
	        System.out.println("------------控件存在------------------");

	        //返回首页
	        driver.elementByXPath("//XCUIElementTypeButton[@name='p back green']").click();        
	        driver.sleep(2000);
	        isFail = false;
		} catch (Exception e) {
			failReWrite(e.toString());
		}
    }
    
    @Test
    public void case_14进入酒店频道() {

        System.out.println("------------14-点击酒店图片------------------");
        
        try {
			Element  el = driver.elementByXPath("//XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[1]"
	        		+ "/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]"
	        		+ "/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]"
	        		+ "/XCUIElementTypeCollectionView[1]/XCUIElementTypeCell[9]/XCUIElementTypeOther[1]"
	        		+ "/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeImage[1]");
	        System.out.println("------------控件存在------------------");
	        el.click();
	        //判断到达新页面
	        assertTrueReWrite("assertTrue:元素未找到！",driver.sleep(5000).isElementExist("xpath", "//XCUIElementTypeStaticText[@name='酒店']"));
	        assertTrueReWrite("assertTrue:元素未找到！",driver.isElementExist("xpath", "//XCUIElementTypeButton[@name='查询']"));
	        System.out.println("------------控件存在------------------");

	        //返回首页
	        driver.elementByXPath("//XCUIElementTypeButton[@name='hotel detail back']").click();        
	        driver.sleep(2000);
	        isFail = false;
		} catch (Exception e) {
			failReWrite(e.toString());
		}
    }
    
    @Test
    public void case_15进入国际酒店频道() {

        System.out.println("------------15-点击国际酒店图片------------------");
        
        try {
			Element  el = driver.elementByXPath("//XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[1]"
	        		+ "/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]"
	        		+ "/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]"
	        		+ "/XCUIElementTypeCollectionView[1]/XCUIElementTypeCell[9]/XCUIElementTypeOther[1]"
	        		+ "/XCUIElementTypeOther[1]/XCUIElementTypeOther[2]/XCUIElementTypeImage[1]");
	        System.out.println("------------控件存在------------------");
	        el.click();
	        //判断到达新页面
	        assertTrueReWrite("assertTrue:元素未找到！",driver.sleep(5000).isElementExist("xpath", "//XCUIElementTypeStaticText[@name='酒店']"));
	        assertTrueReWrite("assertTrue:元素未找到！",driver.isElementExist("xpath", "//XCUIElementTypeButton[@name='查询']"));
	        System.out.println("------------控件存在------------------");

	        //返回首页
	        driver.elementByXPath("//XCUIElementTypeButton[@name='hotel detail back']").click();        
	        driver.sleep(2000);
	        isFail = false;
		} catch (Exception e) {
			failReWrite(e.toString());
		}
    }
       
    @Test
    public void case_16进入当地玩乐频道() {

        System.out.println("------------16-点击当地玩乐图片------------------");

        try {
			Element  el = driver.elementByXPath("//XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[1]"
	        		+ "/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]"
	        		+ "/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]"
	        		+ "/XCUIElementTypeCollectionView[1]/XCUIElementTypeCell[9]/XCUIElementTypeOther[1]"
	        		+ "/XCUIElementTypeOther[1]/XCUIElementTypeOther[3]/XCUIElementTypeImage[1]");
	        System.out.println("------------控件存在------------------");
	        el.click();
	        //判断到达新页面
	        assertTrueReWrite("assertTrue:元素未找到！",driver.sleep(5000).isElementExist("xpath", "//XCUIElementTypeStaticText[@name='当地玩乐']"));
	        System.out.println("------------控件存在------------------");

	        //返回首页
	        driver.elementByXPath("//XCUIElementTypeButton[@name='p back green']").click();        
	        driver.sleep(2000);
	        isFail = false;
		} catch (Exception e) {
			failReWrite(e.toString());
		}
    }
    
    @Test
    public void case_17进入签证频道() {

        System.out.println("------------17-点击签证图片------------------");
        
        try {
			Element  el = driver.elementByXPath("//XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[1]"
	        		+ "/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]"
	        		+ "/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]"
	        		+ "/XCUIElementTypeCollectionView[1]/XCUIElementTypeCell[11]/XCUIElementTypeOther[1]"
	        		+ "/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeImage[1]");
	        System.out.println("------------控件存在------------------");
	        el.click();
	        //判断到达新页面
	        assertTrueReWrite("assertTrue:元素未找到！",driver.sleep(5000).isElementExist("xpath", "//XCUIElementTypeStaticText[@name='签证']"));
	        System.out.println("------------控件存在------------------");

	        //返回首页
	        driver.elementByXPath("//XCUIElementTypeButton[@name='p back green']").click();        
	        driver.sleep(2000);
	        isFail = false;
		} catch (Exception e) {
			failReWrite(e.toString());
		}
    }
    
    @Test
    public void case_18进入门票频道() {

        System.out.println("------------18-点击门票图片------------------");
        
        try {
			Element  el = driver.elementByXPath("//XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[1]"
	        		+ "/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]"
	        		+ "/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]"
	        		+ "/XCUIElementTypeCollectionView[1]/XCUIElementTypeCell[11]/XCUIElementTypeOther[1]"
	        		+ "/XCUIElementTypeOther[1]/XCUIElementTypeOther[2]/XCUIElementTypeImage[1]");
	        System.out.println("------------控件存在------------------");
	        el.click();
	        //判断到达新页面
	        driver.sleep(5000);
	        if(driver.isElementExist("xpath", "//XCUIElementTypeStaticTex[@name='当前定位城市是南京']") == true){
    			driver.elementByXPath("//XCUIElementTypeButton[@name='确定']").click();
    			driver.sleep(2000);
    		}
	        assertTrueReWrite("assertTrue:元素未找到！",driver.isElementExist("xpath", "//XCUIElementTypeStaticText[@name='景点门票']"));
	        System.out.println("------------控件存在------------------");

	        //返回首页
	        driver.elementByXPath("//XCUIElementTypeButton[@name='p back green']").click();        
	        driver.sleep(2000);
	        isFail = false;
		} catch (Exception e) {
			failReWrite(e.toString());
		}
    }
    
    @Test
    public void case_19进入定制频道() {

        System.out.println("------------19-点击定制图片------------------");
        
        try {
			Element  el = driver.elementByXPath("//XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[1]"
	        		+ "/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]"
	        		+ "/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]"
	        		+ "/XCUIElementTypeCollectionView[1]/XCUIElementTypeCell[11]/XCUIElementTypeOther[1]"
	        		+ "/XCUIElementTypeOther[1]/XCUIElementTypeOther[3]/XCUIElementTypeImage[1]");
	        System.out.println("------------控件存在------------------");
	        el.click();
	        //判断到达新页面
	        assertTrueReWrite("assertTrue:元素未找到！",driver.sleep(5000).isElementExist("xpath", "//XCUIElementTypeStaticText[@name='定制·包团']"));
	        assertTrueReWrite("assertTrue:元素未找到！",driver.sleep(5000).isElementExist("xpath", "//XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[1]"
	        		+ "/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]"
	        		+ "/XCUIElementTypeOther[2]/XCUIElementTypeOther[2]/XCUIElementTypeOther[1]"
	        		+ "/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeLink[1]"));

	        System.out.println("------------控件存在------------------");

	        //返回首页
	        driver.elementByXPath("//XCUIElementTypeButton[@name='p back green']").click();        
	        driver.sleep(2000);
	        isFail = false;
		} catch (Exception e) {
			failReWrite(e.toString());
		}
    }
     
    @Test
    public void case_20进入攻略频道() {

        System.out.println("------------20-点击攻略图片------------------");
        
        try {
			Element  el = driver.elementByXPath("//XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[1]"
	        		+ "/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]"
	        		+ "/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]"
	        		+ "/XCUIElementTypeCollectionView[1]/XCUIElementTypeCell[13]/XCUIElementTypeOther[1]"
	        		+ "/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeImage[1]");
	        System.out.println("------------控件存在------------------");
	        el.click();
	        //判断到达新页面
	        assertTrueReWrite("assertTrue:元素未找到！",driver.sleep(5000).isElementExist("xpath", "//XCUIElementTypeStaticText[@name='攻略首页']"));

	        System.out.println("------------控件存在------------------");

	        //返回首页
	        driver.tap(21, 42);
	        driver.sleep(2000);
	        isFail = false;
		} catch (Exception e) {
			failReWrite(e.toString());
		}
    }
      
    @Test
    public void case_21进入旅拍频道() {

        System.out.println("------------21-点击旅拍图片------------------");
        
        try {
			Element  el = driver.elementByXPath("//XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[1]"
	        		+ "/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]"
	        		+ "/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]"
	        		+ "/XCUIElementTypeCollectionView[1]/XCUIElementTypeCell[13]/XCUIElementTypeOther[1]"
	        		+ "/XCUIElementTypeOther[1]/XCUIElementTypeOther[2]/XCUIElementTypeImage[1]");
	        System.out.println("------------控件存在------------------");
	        el.click();
	        //判断到达新页面
	        assertTrueReWrite("assertTrue:元素未找到！",driver.sleep(5000).isElementExist("xpath", "//XCUIElementTypeStaticText[@name='途牛全球旅拍']"));

	        System.out.println("------------控件存在------------------");

	        //返回首页
	        driver.elementByXPath("//XCUIElementTypeButton[@name='p back green']").click();        
	        driver.sleep(2000);
	        isFail = false;
		} catch (Exception e) {
			failReWrite(e.toString());
		}
    }
    
    @Test
    public void case_22进入金融频道() {

        System.out.println("------------21-点击金融图片------------------");
        
        try {
			Element  el = driver.elementByXPath("//XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[1]"
	        		+ "/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]"
	        		+ "/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]"
	        		+ "/XCUIElementTypeCollectionView[1]/XCUIElementTypeCell[13]/XCUIElementTypeOther[1]"
	        		+ "/XCUIElementTypeOther[1]/XCUIElementTypeOther[3]/XCUIElementTypeImage[1]");
	        System.out.println("------------控件存在------------------");
	        el.click();
	        //判断到达新页面
	        assertTrueReWrite("assertTrue:元素未找到！",driver.sleep(5000).isElementExist("xpath", "//XCUIElementTypeStaticText[@name='途牛金服']"));

	        System.out.println("------------控件存在------------------");

	        //返回首页
	        driver.elementByXPath("//XCUIElementTypeButton[@name='p back green']").click();        
	        driver.sleep(2000);
	        isFail = false;
		} catch (Exception e) {
			failReWrite(e.toString());
		}
    }
    
    @AfterMethod
    public void aftercase() throws Exception {
    	System.out.println("------------进入aftercase方法-----------------");
    	if(isFail==true){
    		//如果用例执行出错，检查是否在首页，如果不再首页，点击页面右上角的点，返回
    		for(int i=1;i<=5;i++){
	    		if(driver.isElementExist("xpath", "//XCUIElementTypeButton[@name='首页']") == true){
	    			return;
	    		}else{
	    			driver.tap(21, 42);
					driver.sleep(2000);
	    		}
    		}
    		//如果5次循环都不能到首页，重新初始化driver
    		initDriver();
    		//return;
    	}
    }
    
    @AfterClass
    public void tearDown() throws Exception {
        driver.quit();
    }
    
    public void failReWrite(String message) {
		String imgurl = screenShot();
		if (message == null) {
            throw new AssertionError();
        }
        throw new AssertionError(message+" Check:"+" ltltlta target=_blank href=file://"+imgurl+"gtgtgtScreenShotltltlt/agtgtgt"+" ");
	}
	
	
	public void assertTrueReWrite(String message, boolean condition) {
        if (!condition) {
        	failReWrite(message);
        }
    }
	
    
    public String getDateTime(){  
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");  
        return df.format(new Date());  
    }
    
    
    public String screenShot(){
    	
    	File directory = new File("");
    	String courseFile;
	    try {
	    	courseFile = directory.getCanonicalPath();
	    	String imgname = courseFile+"/report/img/img"+getDateTime()+".png";
	    	System.out.println("-------imgname----------"+imgname);
	    	driver.saveScreenshot(imgname);
	    	return imgname;
		} catch (IOException e) {
			System.out.println("获取路径失败，报错信息："+e.getMessage());
			return "";
		} catch (Exception e1) {
			System.out.println("截图失败，报错信息："+e1.getMessage());
			return "";
		}
	}
}

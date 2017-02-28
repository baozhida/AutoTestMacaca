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
        porps.put("app", "./app/xxx.app");
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
    public void case_001xxx() {
        // set screenshot save path
        //String courseFile = directory.getCanonicalPath();
        try {
			driver.sleep(5000);
			Element  el = driver.elementByXPath("//*[@name='xxx']");
	        System.out.println("------------旅游控件存在------------------");

	        el.click();
	        //判断到达新页面
	        assertTrueReWrite("assertTrue:元素未找到！",driver.sleep(5000).isElementExist("xpath", "//*[@name='xxx']"));
	        //screenShot();
	        System.out.println("------------控件存在------------------");

	        //返回首页
	        driver.elementByXPath("//*[@name='tab back']").click();        
	        driver.sleep(2000);
	        isFail = false;
		} catch (Exception e) {
			failReWrite(e.toString()+e.getLocalizedMessage());
		}

    }
    
    
    @AfterMethod
    public void aftercase() throws Exception {
    	System.out.println("------------进入aftercase方法-----------------");
    	if(isFail==true){
    		//如果用例执行出错，检查是否在首页，如果不再首页，点击页面右上角的点，返回
    		for(int i=1;i<=5;i++){
	    		if(driver.isElementExist("xpath", "//*[@name='首页']") == true){
	    			return;
	    		}else{
	    			driver.tap(21, 42);
					driver.sleep(2000);
	    		}
    		}
    		//如果5次循环都不能到首页，重新初始化driver
    		initDriver();
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

package macaca.testngcase.android;

//import static org.testng.AssertJUnit.assertTrue;
//import static org.testng.AssertJUnit.fail;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSONObject;

import macaca.testngcase.android.elementlocation.HomePageElement;
import macaca.testngcase.macaca.MacacaServer;
import macaca.testngcase.macaca.MyMacacaClient;

public class AndroidAppTest {

	private String udid,proxyport,port;
	boolean isFail;
	int initcount = 0;

    private MyMacacaClient driver = new MyMacacaClient();

    public MyMacacaClient initDriver() throws Exception {
        initcount = initcount +1;
        System.out.println("-----------第"+initcount+"次初始化-------------");

        String platform = "Android";
        JSONObject porps = new JSONObject();
        porps.put("platformName", platform);
        //porps.put("app", "path_to_app_pkg");
        porps.put("package", "com.tuniu.app.ui");
        porps.put("activity", "com.tuniu.app.ui.activity.LaunchActivity");
        //0: 启动并安装 app。
        // 1 (默认): 卸载并重装 app。
        // 2: 仅重装 app。
        // 3:直接使用已经安装的app，在测试结束后保持 app状态。
        porps.put("reuse", 3);
        porps.put("udid", udid);
        //porps.put("autoAcceptAlerts", true);
        porps.put("host", "127.0.0.1");
        porps.put("port", Integer.parseInt(port));
        JSONObject desiredCapabilities = new JSONObject();
        desiredCapabilities.put("desiredCapabilities", porps);
        return (MyMacacaClient) driver.initDriver(desiredCapabilities);
    }

    @BeforeTest
    @Parameters({ "port", "udid" })
    public void getpara(String port, String udid) {
        this.port = port;
        this.udid = udid;
    }

    @BeforeClass
    public void setUp() throws Exception {
        // 判断端口是否已经启动node进程。如果没有，启动macaca server
        if(!MacacaServer.isPortRunning(port)){
            MacacaServer.runMacacaServer(port);
        }
        initDriver();
    }
    
    @BeforeMethod
    public void beforecase() throws Exception {
    	//重置isFail,判读是否执行 aftercase方法
    	isFail = true;
    }

	@Test
	public void case001_APP首页_正常进入旅游频道() {

		//String courseFile = directory.getCanonicalPath();
		try {
			driver.waitForElement(HomePageElement.LVYOU_ICON).click();
			//判断到达新页面
			assertTrueReWrite("LVYOU_CHANNEL_MARK:"+ HomePageElement.LVYOU_CHANNEL_MARK+"元素未找到！",
					driver.waitForElement(HomePageElement.LVYOU_CHANNEL_MARK) != null);

			//返回首页
			driver.back();
			driver.sleep(2000);
			isFail = false;
		} catch (Exception e) {
			failReWrite(e.toString()+e.getLocalizedMessage());
		}

	}

    @AfterMethod
    public void aftercase() throws Exception {
    	System.out.println("----进入aftercase方法----");
    	if(isFail==true){
    		goHomePage();
    	}
    }
    
    @AfterClass
    public void tearDown() throws Exception {
        driver.quit();
    }

    public void goHomePage() throws Exception {
        //如果用例执行出错，检查是否在首页
        for(int i=1;i<=5;i++) {
            if (driver.isElementExist(HomePageElement.HOME)) {
                break;
            } else {
                System.out.println("----第"+i+"次back操作----");
                driver.back();
            }
        }
        driver.getElement(HomePageElement.HOME).click();
        driver.sleep(2000);

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
	    	System.out.println("----imgname----"+imgname);
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

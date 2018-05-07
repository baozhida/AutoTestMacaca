/**
 * Copyright (C) 2006-2018 Tuniu All rights reserved
 */
package macaca.testngcase.macaca;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONObject;

import macaca.client.MacacaClient;
import macaca.client.commands.Element;

/**
 * 继承自 MacacaClient 新增一些定制方法，比如 id::test 类型当做入参，之类查询元素
 * 这种方式比较讨巧，不一定是所有都需要的，所以不提交到官方的项目中
 * Date: 2018-04-25
 *
 * @author baozhida
 */
public class MyMacacaClient  extends MacacaClient{

    /**
     * time interval between finding an element ,valid for waitForElement() function
     * paired with waitElementTimeout, if waitElementTimeout = 1000 & waitElementTimeInterval = 200,it means we will find given element per 200ms,until 1000ms passed,
     * which means we will find for 5 times
     */
    // 默认是1000ms
    public int waitElementTimeout = 10000;
    // 默认是200ms
    public int waitElementTimeInterval = 1000;

    public int WindowSizeX;
    public int WindowSizeY;

    public MacacaClient initDriver(JSONObject jsonObject) throws Exception {
        super.initDriver(jsonObject);
        WindowSizeX = (int) super.getWindowSize().get("width");
        WindowSizeY = (int) super.getWindowSize().get("height");
        return this;
    }

    public int getWaitElementTimeout() {
        return waitElementTimeout;
    }

    public void setWaitElementTimeout(int waitElementTimeout) {
        this.waitElementTimeout = waitElementTimeout;
    }

    public int getWaitElementTimeInterval() {
        return waitElementTimeInterval;
    }

    public void setWaitElementTimeInterval(int waitElementTimeInterval) {
        this.waitElementTimeInterval = waitElementTimeInterval;
    }


    /**
     * <p>
     * Search for an element on the page, starting from the document root.<br>
     * Support: Android iOS Web(WebView)
     *
     * @param wayValue  The loaction attribute of element,contains the way to find an element,
     *                   contains 4:ID,CSS,XPATH,selector
     *                   for example   [id::fl_auto_play_ad]  [::]间隔开，查找元素的方式在前
     * @return true-exist ; false-do not exist
     * @throws Exception
     */
    public boolean isElementExist(String wayValue) throws Exception {
        try {
            element = getElement(wayValue);
            return element != null;
        } catch (Exception e) {
            return false;
        }

    }

    /**
     * <p>
     * Search for an element on the page, starting from the document root.<br>
     * Support: Android iOS Web(WebView)
     *
     * @param wayValue  The loaction attribute of element,contains the way to find an element,
     *                   contains 4:ID,CSS,XPATH,selector
     *                   for example   [id::fl_auto_play_ad]  [::]间隔开，查找元素的方式在前
     * @return true-exist ; false-do not exist
     * @throws Exception
     */
    public boolean isElementExist(String wayValue, int index) throws Exception {
        try {
            element = getElement(wayValue, index);
            return element != null;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * <p>
     * Search for an element on the page, starting from the document root.<br>
     * Support: Android iOS Web(WebView)
     *
     * @param wayValue  The loaction attribute of element,contains the way to find an element,
     *                   contains 4:ID,CSS,XPATH,selector
     *                   for example   [id::fl_auto_play_ad]  [::]间隔开，查找元素的方式在前
     * @return true-exist ; false-do not exist
     * @throws Exception
     */
//    public boolean isElementsExist(String wayValue) throws Exception {
//        try {
//            return getElement(wayValue) != null;
//        } catch (Exception e) {
//            return false;
//        }
//
//    }

    /**
     * <p>
     * Search for an element on the page, starting from the document root.<br>
     * Support: Android iOS Web(WebView)
     *
     * @param wayValue  The loaction attribute of element,contains the way to find an element,
     *                   contains 4:ID,CSS,XPATH,selector
     *                   for example   [id::fl_auto_play_ad]  [::]间隔开，查找元素的方式在前
     * @return return the element to find if it exists,if it does not exist ,return null
     * @throws Exception
     */
    public Element getElement(String wayValue) throws Exception {
        String[] tmp = wayValue.split("::");
        switch (tmp[0]) {
            case "id":
                return elementById(tmp[1]);
            case "selector":
                return elementByCss(tmp[1]);
            case "name":
                return elementByName(tmp[1]);
            case "xpath":
                return elementByXPath(tmp[1]);
            default:
                return null;
        }
    }

    /**
     * <p>
     * Search for an element on the page, starting from the document root.<br>
     * Support: Android iOS Web(WebView)
     *
     * @param wayValue  The loaction attribute of element,contains the way to find an element,
     *                   contains 4:ID,CSS,XPATH,selector
     *                   for example   [id::fl_auto_play_ad]  [::]间隔开，查找元素的方式在前
     * @param index     index for target element
     * @return return the element to find if it exists,if it does not exist ,return null
     * @throws Exception
     */
    public Element getElement(String wayValue, int index) throws Exception {
        String[] tmp = wayValue.split("::");
        List<Element> elements = new ArrayList<Element>();
        switch (tmp[0]) {
            case "id":
                elements = elementsById(tmp[1]);
                break;
            case "selector":
                elements = elementsByCss(tmp[1]);
                break;
            case "name":
                elements = elementsByName(tmp[1]);
                break;
            case "xpath":
                elements = elementsByXPath(tmp[1]);
                break;
            default:
                elements = null;
                break;
        }

        if (elements != null && elements.size() > (index - 1)) {
            element = elements.get(index);
        } else {
            System.out.println("can't find the element:" + tmp[1] + "[" + index + "]");
            return null;
        }
        return element;
    }


    /**
     * <p>
     * Search for an element on the page, starting from the document root.<br>
     * Support: Android iOS Web(WebView)
     *
     * @param wayValue  The loaction attribute of element,contains the way to find an element,
     *                   contains 4:ID,CSS,XPATH,selector
     *                   for example   [id::fl_auto_play_ad]  [::]间隔开，查找元素的方式在前
     * @return return the all elements to find if it exists,if it does not exist ,return null
     * @throws Exception
     */
    public List<Element> getElements(String wayValue) throws Exception {
        String[] tmp = wayValue.split("::");
        switch (tmp[0]) {
            case "id":
                return elementsById(tmp[1]);
            case "selector":
                return elementsByCss(tmp[1]);
            case "name":
                return elementsByName(tmp[1]);
            case "xpath":
                return elementsByXPath(tmp[1]);
            default:
                return null;
        }
    }


    /**
     * <p>
     * find target element,if it doesn't exist,keep finding during given time
     * (property:waitElementTimeout)<br>
     * Support: Android iOS Web(WebView)
     * @param wayValue  The loaction attribute of element,contains the way to find an element,
     *                   contains 4:ID,CSS,XPATH,selector
     *                   for example   [id::fl_auto_play_ad]  [::]间隔开，查找元素的方式在前
     * @return return the element to find if it exists,if it does not exist ,return null
     * @throws Exception
     */
    public Element waitForElement(String wayValue) throws Exception {
        String[] tmp = wayValue.split("::");
        int count = 0;
        int timeLeft = waitElementTimeout;
        boolean satisfied = false;
        while (timeLeft > 0) {
            boolean elementExist = false;
            System.out.println(String.format("attempt to search the element for %d times", ++count));
            elementExist = isElementExist(wayValue);
            if (!elementExist) {
                // not find element ,keep searching
                this.sleep(waitElementTimeInterval);
                timeLeft -= waitElementTimeInterval;
            } else {
                // finded , break
                satisfied = true;
                break;
            }
        }
        if (!satisfied) {
            System.out.println("can't find the element:" + tmp[1]);
            return null;
        }
        return element;
    }

    /**
     * <p>
     * find target element,if it doesn't exist,keep finding during given time
     * (property:waitElementTimeout)<br>
     * Support: Android iOS Web(WebView)
     *
     * @param wayValue  The loaction attribute of element,contains the way to find an element,
     *                   contains 4:ID,CSS,XPATH,selector
     *                   for example   [id::fl_auto_play_ad]  [::]间隔开，查找元素的方式在前
     * @param index     index for target element
     * @return return the element to find if it exists,if it does not exist ,return null
     * @throws Exception
     */
    public Element waitForElement(String wayValue, int index) throws Exception {
        String[] tmp = wayValue.split("::");
        int count = 0;
        int timeLeft = waitElementTimeout;
        boolean satisfied = false;
        while (timeLeft > 0) {
            boolean elementExist = false;
            System.out.println(String.format("attempt to search the element for %d times", count++));
            elementExist = isElementExist(wayValue, index);
            if (!elementExist) {
                // not find element ,keep searching
                this.sleep(waitElementTimeInterval);
                timeLeft -= waitElementTimeInterval;
            } else {
                // finded , break
                satisfied = true;
                getElement(wayValue, index);
                break;
            }
        }
        if (!satisfied) {
            System.out.println("can't find the element:" + tmp[1]);
            return null;
        }
        return element;
    }

    /**
     * <p>
     * find target element,if it doesn't exist,keep finding during given time
     * (property:waitElementTimeout)<br>
     * Support: Android iOS Web(WebView)
     * @param wayValue  The loaction attribute of element,contains the way to find an element,
     *                   contains 4:ID,CSS,XPATH,selector
     *                   for example   [id::fl_auto_play_ad]  [::]间隔开，查找元素的方式在前
     * @return return the all element to find if it exists,if it does not exist ,return null
     * @throws Exception
     */
    public List<Element> waitForElements(String wayValue) throws Exception {
        String[] tmp = wayValue.split("::");
        int count = 0;
        int timeLeft = waitElementTimeout;
        boolean satisfied = false;
        List<Element> elements = new ArrayList<Element>();;
        while (timeLeft > 0) {
            boolean elementExist = false;
            System.out.println(String.format("attempt to search the element for %d times", count++));
            elementExist = isElementExist(wayValue);
            if (!elementExist) {
                // not find element ,keep searching
                this.sleep(waitElementTimeInterval);
                timeLeft -= waitElementTimeInterval;
            } else {
                // finded , break
                satisfied = true;
                elements = getElements(wayValue);
                break;
            }
        }
        if (!satisfied) {
            System.out.println("can't find the element:" + tmp[1]);
            return null;
        }
        return elements;
    }


    /**
     * <p>
     * get count of elements when there exist multiple elements<br>
     * Support: Android iOS Web(WebView)
     *
     * @param wayValue  The loaction attribute of element,contains the way to find an element,
     *                   contains 4:ID,CSS,XPATH,selector
     *                   for example   [id::fl_auto_play_ad]  [::]间隔开，查找元素的方式在前
     * @return count of target element
     * @throws Exception
     */
    public int countOfElements(String wayValue) throws Exception {

        List<Element> elements = getElements(wayValue);
        if (elements != null) {
            return elements.size();
        }

        return 0;
    }

    //像素坐标比较，不能超过手机屏幕大小
    public Boolean checkPointLocation(double x, double y){
        if(x > WindowSizeX || x < 0){
            return false;
        }
        if(y > WindowSizeY || y < 0){
            return false;
        }
        return true;
    }

    /**
     * drag
     * Support: Android iOS
     *
     * @param fromX    drag start x-coordinate
     * @param fromY    drag start y-coordinate
     * @param toX      drag end x-coordinate
     * @param toY      drag end y-coordinate
     * @param duration drag duration (valid for iOS and Android,time-unit:s)
     * @throws Exception
     */
    public void drag(double fromX, double fromY, double toX, double toY, double duration) throws Exception {
        //像素坐标比较，不能超过手机屏幕大小
        if(!checkPointLocation(fromX, fromY)){
            throw new RuntimeException("坐标点("+fromX+","+fromY+")越界，请检查，正确的范围是[(0,0),("+WindowSizeX+","+WindowSizeY+")]");
        };
        if(!checkPointLocation(toX, toY)){
            throw new RuntimeException("坐标点("+toX+","+toY+")越界，请检查，正确的范围是[(0,0),("+WindowSizeX+","+WindowSizeY+")]");
        };
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("fromX", fromX);
        jsonObject.put("fromY", fromY);
        jsonObject.put("toX", toX);
        jsonObject.put("toY", toY);
        jsonObject.put("duration", duration);
        this.touch("drag", jsonObject);
    }

    /**
     * drag
     * Support: Android iOS
     *
     * @param fromX    drag start x-Percentage  ex:1000px  80% is 800px
     * @param fromY    drag start y-Percentage
     * @param toX      drag end x-Percentage
     * @param toY      drag end y-Percentage
     * @param duration drag duration (valid for iOS and Android,time-unit:s)
     * @throws Exception
     */
    public void swipeByPointPercentage(int fromX, int fromY, int toX, int toY, double duration) throws Exception {
        double fX = WindowSizeX * fromX / 100;
        double fY = WindowSizeY * fromY / 100;
        double tX = WindowSizeX * toX / 100;
        double tY = WindowSizeY * toY / 100;


        if(!checkPointLocation(fX, fY)){
            throw new RuntimeException("坐标点("+fX+","+fY+")越界，请检查，正确的范围是[(0,0),("+WindowSizeX+","+WindowSizeY+")]");
        };
        if(!checkPointLocation(tX, tY)){
            throw new RuntimeException("坐标点("+tX+","+tY+")越界，请检查，正确的范围是[(0,0),("+WindowSizeX+","+WindowSizeY+")]");
        };

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("fromX", fX);
        jsonObject.put("fromY", fY);
        jsonObject.put("toX", tX);
        jsonObject.put("toY", tY);
        jsonObject.put("duration", duration);

        // action  "tap" "doubleTap" "press" "pinch" "drag"
        super.touch("drag", jsonObject);
    }

    /**
     * 指定的控件元素，滑动到指定的位置
     * Support: Android iOS
     *
     * @param e    Element to drag 使用的时候直接传控件即可
     * @param toX      drag end x-coordinate
     * @param toY      drag end y-coordinate
     * @param duration drag duration (valid for iOS and Android,time-unit:s)
     * @throws Exception
     */
    public void drag(Element e, double toX, double toY, double duration) throws Exception {
        //像素坐标比较，不能超过手机屏幕大小
        if(!checkPointLocation(toX, toY)){
            throw new RuntimeException("坐标点("+toX+","+toY+")越界，请检查，正确的范围是[(0,0),("+WindowSizeX+","+WindowSizeY+")]");
        };
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("fromX", e.getCenterX());
        jsonObject.put("fromY", e.getCenterY());
        jsonObject.put("toX", toX);
        jsonObject.put("toY", toY);
        jsonObject.put("duration", duration);
        this.touch("drag", jsonObject);
    }

    /**
     * 指定的控件元素，上下左右滑动
     * Support: Android iOS
     *
     * @param e    Element to drag 使用的时候直接传控件即可
     * @param direction      拖拽元素的方向
     * @param length      拖拽的长度 px像素
     * @param duration drag duration (valid for iOS and Android,time-unit:s)
     * @throws Exception
     */
    public void drag(Element e, String direction,int length, double duration) throws Exception {
        double fromX = e.getCenterX();
        double fromY = e.getCenterY();

        double toX = 0, toY =0;

        if (direction.equals("UP")){
            if (checkPointLocation(fromX, fromY - length)){
                toX = fromX;
                toY = fromY - length;
            } else {
                toX = fromX;
                toY = 0;
            }
        }else if (direction.equals("DOWN")){
            if (checkPointLocation(fromX, fromY + length)){
                toX = fromX;
                toY = fromY - length;
            } else {
                toX = fromX;
                toY = WindowSizeY;
            }
        } else if (direction.equals("LEFT")){
            if (checkPointLocation(fromX - length, fromY)){
                toX = fromX;
                toY = fromY - length;
            } else {
                toX = fromX;
                toY = 0;
            }
        }else if (direction.equals("RIGHT")){
            if (checkPointLocation(fromX + length, fromY)){
                toX = fromX;
                toY = fromY - length;
            } else {
                toX = fromX;
                toY = WindowSizeX;
            }
        }
        // 实际传递的是元素的控件ID
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("fromX", fromX);
        jsonObject.put("fromY", fromY);
        jsonObject.put("toX", toX);
        jsonObject.put("toY", toY);
        jsonObject.put("duration", duration);
        this.touch("drag", jsonObject);
    }


    /**
     * 滑动屏幕查找元素（默认往上滑屏，最多滑动10次）
     *
     * @param wayValue  The loaction attribute of element,contains the way to find an element,
     *                   contains 4:ID,CSS,XPATH,selector
     *                   for example   [id::fl_auto_play_ad]  [::]间隔开，查找元素的方式在前
     * @return return the element to find if it exists,if it does not exist ,return null
     * @throws Exception
     */
    public Element getElementBySwipe(String wayValue) throws Exception{
        int i = 0;
        element = null;
        while (i < 10) {
            if (this.isElementExist(wayValue) == true) {
                System.out.println("屏幕滑动" + i + "次后找到元素。");
                break;
            } else {
                // 没有找到元素就向上滑屏
                System.out.println("第" + i + "次向上滑动查找元素。");
                swipeByPointPercentage(50, 80, 50, 30, 2);
            }
            i++;

        }
        if(element != null){
            return element;
        }else{
            throw new RuntimeException("控件未找到。");
        }

    }


    /**
     * 滑动屏幕查找元素,方向往上或者往下由入参控制，最多滑动次数由入参控制
     *
     * @param wayValue  The loaction attribute of element,contains the way to find an element,
     *                   contains 4:ID,CSS,XPATH,selector
     *                   for example   [id::fl_auto_play_ad]  [::]间隔开，查找元素的方式在前
     * @param times  swipe times
     * @param direction  swipe direction , default true swipeup or false is swipedown
     * @return return the element to find if it exists,if it does not exist ,return null
     * @throws Exception
     */
    public Element getElementBySwipe(String wayValue, int times, boolean direction) throws Exception{
        int i = 0;
        element = null;
        while (i < times) {
            element = this.getElement(wayValue);
            if (element != null) {
                System.out.println("屏幕滑动" + i + "次后找到元素。");
                break;
            } else if (direction) {
                // 向上滑屏
                System.out.println("第" + i + "次向上滑动查找元素。");
                swipeByPointPercentage(50, 80, 50, 20, 2);
            } else {
                // 向下滑屏
                System.out.println("第" + i + "次向下滑动查找元素。");
                swipeByPointPercentage(50, 20, 50, 80, 2);
            }
            i++;

        }
        if(element != null){
            return element;
        }else{
            throw new RuntimeException("控件未找到。");
        }

    }


    /*
	 * 获取当前界面的activity,并且与预期的activity进行比较
	 */
    public Boolean verifyActivity(String activity) throws Exception {
        String currentActivity = MacacaServer.currentActivity();
        if (activity.equals(currentActivity)) {
            return true;
        } else {
            return false;
        }

    }

}

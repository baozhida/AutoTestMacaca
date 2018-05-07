/**
 * Copyright (C) 2006-2018 Tuniu All rights reserved
 */
package macaca.testngcase.macaca;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Macaca 服务起停进程
 * Date: 2018-04-27
 *
 * @author baozhida
 */
public class MacacaServer {

    public static void runMacacaServer(String port) throws InterruptedException {
        String MacacaCmd = "cmd /K start macaca server --verbose --port "+port;
        //System.out.println(appiumCmd);
        runCommand(MacacaCmd);
        System.out.println("start macaca server at port "+port);
        Thread.sleep(20000);
    }

    private static void runCommand(String command){
        try {
            Runtime.getRuntime().exec(command);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void killTaskByName(String taskname){
        String Command = "taskkill /F /im " + taskname;
        System.out.println("kill " + taskname + " task ...");
        runCommand(Command);
    }

    public static void killTaskByPid(String pid) {
        String Command = "taskkill /F /pid " + pid;
        System.out.println("kill " + pid + " task ...");
        runCommand(Command);
    }

    public static boolean isPortRunning(String port){
        try {
            Process process = Runtime.getRuntime().exec("cmd /c netstat -ano | findstr "+port);
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String lineString = "" ;
            boolean flag = false;
            while ((lineString = reader.readLine()) != null) {
                flag = regexFind(lineString, port);
                if(flag){
                    System.out.println("端口 "+port+" 已经占用 ...");
                    return flag;
                }
            }
        } catch (IOException e) {

        }
        return false;
    }

    public static String getProcessPid(String port) {
        try {
            Process process = Runtime.getRuntime().exec("cmd /c netstat -ano | findstr " + port);
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            return reader.readLine().split("\\s+")[5];
        } catch (IOException e) {
            return null;
        }
    }

    public static boolean regexFind(String lineString,String port){
        String regex=":(\\d+)\\s+\\d+";
        Matcher matcher = Pattern.compile(regex).matcher(lineString);
        while(matcher.find()){
            if(port.trim().equals(matcher.group(1).trim())){
                return true;
            }
        }
        return false;
    }


    //获取当前运行页面的ACTIVITY
    public static String currentActivity(){
        try {
            Process process = Runtime.getRuntime().exec("cmd /c adb shell dumpsys activity top | findstr ACTIVITY");
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            return reader.readLine().split("\\s+")[2];
        } catch (IOException e) {
            return null;
        }
    }













    public static void main(String[] args) throws InterruptedException {
//        isPortRunning("4567");
//        killTaskByName("node.exe");
//        runMacacaServer("5656");
//        Thread.sleep(5000);
//        String pid = getProcessPid("5656");
//        Thread.sleep(5000);
//        killTaskByPid(pid);
        System.out.println(currentActivity());
    }

}
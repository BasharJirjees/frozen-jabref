/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.simplestresstester;

/**
 * @author Bashar Jirjees
 */

import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.lang.Runnable;
import java.util.Collections;
import java.util.ArrayList;

public class Threads {
    
final int thread_count = 32;
private int highPerformance_check = 0;
    protected Threads(){
        storeWebsites();
    }
    
    protected Threads(int num){
     highPerformance_check = num;
     threadRunnable threadHighPerformance = new threadRunnable();
     threadHighPerformance.run();
    }
    
    
    private void storeWebsites(){
        
        final ArrayList<String> websites = new ArrayList<>();
        
        websites.add("https://amazon.com");
        websites.add("https://hp.com");
        websites.add("https://alienware.com");
        websites.add("https://phonearena.com");
        websites.add("https://lenovo.com");
        websites.add("https://microsoft.com");
        websites.add("https://msi.com");
        websites.add("https://gsmarena.com");
        websites.add("https://asus.com");
        websites.add("https://aliexpress.com");
        websites.add("https://acer.com");
        websites.add("https://intel.com");
        websites.add("https://huawei.com");
        websites.add("https://samsung.com");
        websites.add("https://lg.com");
        websites.add("https://alibaba.com");
        store_Processing_Threads(websites);
    }
            
    private void store_Processing_Threads(ArrayList <String> websiteList ){
        
        
        String links = null;
        ExecutorService executor = Executors.newFixedThreadPool(thread_count);
        
        for(String urls: websiteList){
           
            links = urls;
            Runnable runThread = new threadRunnable(links);
            executor.execute(runThread);
         
        } 
        
        executor.shutdown();
        
    }
    
    private class threadRunnable implements Runnable{
        
        private String urlRunnable = null;
        private final int HighPerRunnable = 300;
        private String  Content = null;
        private threadRunnable(String link){
            urlRunnable = link;    
        }
        
        private threadRunnable(){}
        
        @Override
        public void run(){
            if(highPerformance_check == 0){
            try{
                URL websiteLink = new URL(urlRunnable);
                URLConnection LinkConnect = websiteLink.openConnection();
                LinkConnect.setRequestProperty("GET",urlRunnable);
                LinkConnect.setConnectTimeout(15000);
                LinkConnect.connect();
               
                
            }catch(Exception e){
                System.out.println("Link is broken and doesn't load. Please check url data. " + e.getMessage());
            }
            } else{
                ExecutorService executor = Executors.newFixedThreadPool(thread_count);
                Runnable runThread = new threadRunnable(String.valueOf(HighPerRunnable));
                executor.execute(runThread);
                initiateHighPerformanceTest();
                executor.shutdown();    
            }
            
        }
        
        
        private void initiateHighPerformanceTest(){
         int counter  = 0;
         
         for(int i = 1; i <= HighPerRunnable ; i+=0.001);  
            
         
            
        }
    }
    
}

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
import java.util.ArrayList;


public class Threads extends data_Generator {
    
final int thread_count = 8;
private int succesful_links = 0;
private final ArrayList<Long> processes = new ArrayList<>();
private final ArrayList<String> websites = new ArrayList<>();

/*
* Thread class constructor that initiates the test.
*/
    protected Threads(){
     
     storeWebsites();
    }
     
 
    
 /*
 * storeWebsites methods that saves the websites used to check the network
 * connection availibilty and performance, and store them in list.
 */      
    private void storeWebsites(){
            
        websites.add("https://amazon.com");
        websites.add("https://hp.com");
        websites.add("https://phonearena.com");
        websites.add("https://lenovo.com");
        websites.add("https://microsoft.com");
        websites.add("https://msi.com");
        websites.add("https://gsmarena.com");
        websites.add("https://asus.com");
        websites.add("https://aliexpress.com");
        websites.add("https://alienware.com");
        websites.add("https://acer.com");
        websites.add("https://intel.com");
        websites.add("https://huawei.com");
        websites.add("https://samsung.com");
        websites.add("https://lg.com");
        websites.add("https://alibaba.com");
        websites.add("https://nvidia.com");
        succesful_links = websites.size();
        store_Processing_Threads(websites);
    }
     
    /*
    * store_Processing_Threads that takes the list of websites as a paramater
    * then starts connecting to each websites using al threads in the CPU and 
    * in conjuction tests the arithmatic performance of all threads using.
    */
    private void store_Processing_Threads(ArrayList <String> websiteList ){
        
        ExecutorService executor = Executors.newFixedThreadPool(thread_count);
        String links = null;
        
        for(String urls: websiteList){
           
            links = urls;
            Runnable runThread = new threadRunnable(links);
            executor.execute(runThread);
                        
        } 
        
        executor.shutdownNow();
    }
    
    /*
    * threadRunnale class that implements the Runnable interface to use the run
    * method continuously by each CPU thread.
    */
    private class threadRunnable implements Runnable{
        
        private String urlRunnable = null;
        private String  Content = null;
        private threadRunnable(String link){
            urlRunnable = link;    
        }
        
        private threadRunnable(){}
       
    /*
    * run method that consists of all the necessary tests neded to be performed 
    * by the CPU threads.
    */
        
        @Override
        public void run(){
           
            try{
                URL websiteLink = new URL(urlRunnable);
                URLConnection LinkConnect = websiteLink.openConnection();
                LinkConnect.setRequestProperty("GET",urlRunnable);
                LinkConnect.setConnectTimeout(15000);
                LinkConnect.connect();
               
                
            }catch(Exception e){
                System.out.println("Link is broken and doesn't load. Please check url data. " + e.getMessage());
                --succesful_links;
            }
            
            
             initiateHighPerformanceTest();
        }
        
    /*
    * initiateHighPerformanceTest method conatins the high perfomance CPU stress  
    * test.
    */    
        private void initiateHighPerformanceTest(){
         long operation_counter = 0;
         long  time_limit =  300000 + System.currentTimeMillis();
         for(double i = 0; System.currentTimeMillis() < time_limit; i += 0.00001)
            ++operation_counter;  
            
         processes.add(operation_counter);
         if(processes.size() >= thread_count){
             displaySummary();
             System.exit(0);
         }
         
        }
        
       
    }
    
    
    /*
    * displaySummary method outputs the total number of operations by the high
    * performance CPU stress test and the total succesful links to given
    * websites.
    */
    @Override
    void displaySummary(){
        long total = 0;
        for(int i = 0; i < processes.size(); ++i)
            total = total + processes.get(i);
        
   
        System.out.println("Total CPU operations under stress test: " + total);
        System.out.printf("Numer of succeful links: %x / %x ", succesful_links, websites.size());
        processes.clear();
        
        }
    
}

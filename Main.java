/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.simplestresstester;

/**
 *
 * @author Bashar Jirjees
 */

import java.util.Scanner;

public class Main {

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        // TODO code application logic here
        
        final int time_limit = 200000;
        Scanner choiceInput  = new Scanner(System.in);
        Threads linking = null;
        System.out.println("Select how would your hardware test level:");
        System.out.println("Press 1 for light CPU test");
        System.out.println("Press 2 for intense CPU test");
        String choice = choiceInput.next();
        
        
        switch(choice){
        
        case "1": for(int i = 1; i < 20; ++i) linking = new Threads();
        case "2": while(System.currentTimeMillis() < time_limit) linking = new Threads(1);
        default: System.exit(0);
        }
    }
    
}

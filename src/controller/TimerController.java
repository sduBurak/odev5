/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;


import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JLabel;

public class TimerController{
    private static int counter = 0;
    JLabel sureSayac;
    Timer timer;
   
    public TimerController(){                  
        timer = new Timer();
       
    }  
    public void timerBasla(final JLabel sureSayac1){
        this.sureSayac = sureSayac1;        
        timer.scheduleAtFixedRate(new TimerTask(){
            @Override
            public void run() {                
                sureSayac.setText(Integer.toString(counter));
                counter++;                                    
            }
        }, new Date(), 1000);  //1 sn    
    }
    
    public void duraklat() {
        this.timer.cancel();
    }
    public void devamEt() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask(){
            @Override
            public void run() {                
                sureSayac.setText(Integer.toString(counter));
                counter++;                                    
            }
        }, new Date(), 1000); 
    }    
}

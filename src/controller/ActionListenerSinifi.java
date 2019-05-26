/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Model.OyunModel;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

/**
 *
 * @author uluzm
 */
public class ActionListenerSinifi extends JFrame {
    JToggleButton btn;
    private OyunModel oyunModel;
    private JPanel jpanelContainer;
    public JToggleButton[] buttonList;
    public int sayac = 0;
    
    private int basarisizPuanDegeri = 0;
    private int basariliPuanDegeri = 0;
    JLabel puanLabel;
    TimerController timerController;
     public ActionListenerSinifi(OyunModel oyunModel,JPanel jpanelContainer,
            final JLabel puanLabel,final TimerController timerController){
         
         this.timerController = timerController;
         this.puanLabel = puanLabel;
         
           this.oyunModel=oyunModel;
           this.jpanelContainer = jpanelContainer;
        
           final int kutuAdedi = this.oyunModel.getAdet();
           int[] intListe = new int[kutuAdedi];
            buttonList = new JToggleButton[kutuAdedi];
           
           final JToggleButton[] degerTut = new JToggleButton[kutuAdedi];
         
           LayoutManager layout = new GridLayout();//FlowLayout
           if("FlowLayout".equals(this.oyunModel.getSelectedLayouth()))layout = new FlowLayout();
           this.jpanelContainer.setLayout(layout);
         
            SayiUret sayUret = new SayiUret();
            intListe = sayUret.sayiUretListe(kutuAdedi);
        
        
        Random random = new Random();
        //Bu kisimda dizinin elemanlarini rastgele degistirme islemi yapiliyor.
        for (int i=0; i<intListe.length; i++) {
                    int randomPosition = random.nextInt(intListe.length);
                    int temp = intListe[i];
                    intListe[i] = intListe[randomPosition];
                    intListe[randomPosition] = temp;
        } 
        
        for(int i=1;i<=kutuAdedi;i++){
            btn = new JToggleButton(Integer.toString(intListe[i-1]));
            btn.setActionCommand(Integer.toString(intListe[i-1]));
            buttonList[i-1]=btn; 
            
            btn.setPreferredSize(new Dimension(100, 100));
             this.jpanelContainer.add(btn);
             this.jpanelContainer.revalidate();
             
             ItemListener itemListener = new ItemListener() { 

                    public void itemStateChanged(ItemEvent itemEvent) 
                    { 
                        JToggleButton button = (JToggleButton) itemEvent.getSource();
                        //Tiklanilan buton hangi sayiyi tutorsa ona erisiliyor
                        String tiklanilanSayi = button.getActionCommand();
                        int state = itemEvent.getStateChange(); 

                        if (state == ItemEvent.SELECTED) {                    

                            button.setText(tiklanilanSayi);                            
                            if(sayac % 2 == 0){
                                button.setEnabled(false);
                                degerTut[sayac]= button;
                                sayac = sayac + 1;                                
                            }
                            else{                                
                                if(kontrolEt(tiklanilanSayi,degerTut)){                                  
                                    button.setEnabled(false);
                                    degerTut[sayac]= button;
                                    sayac = sayac + 1;
                                    basariliPuanDegeri = basariliPuanDegeri + 50;
                                }else{   
                                     basarisizPuanDegeri = basarisizPuanDegeri + 20;
                                    button.setSelected(false);  
                                    button.setEnabled(true);
                                    sayac = sayac - 1;
                                    degerTut[sayac].setSelected(false); 
                                     degerTut[sayac].setEnabled(true);
                                    degerTut[sayac]= null;                                    
                                }
                            }
                        } 
                        else { 
                             button.setText("");
                        } 
                        if(kontrolEtOyunSon(degerTut)){
                             //JOptionPane.showMessageDialog(null, "Oyun Bitti");
                            
                             int sonucPuan = (basariliPuanDegeri - basarisizPuanDegeri)*kutuAdedi;
                             System.out.println("Sonuc Puan : " + sonucPuan);
                             puanLabel.setText(Integer.toString(sonucPuan));                            
                             timerController.duraklat();
                            
                        }
                    }                
                };
            btn.addItemListener(itemListener);

            pack();
        }
     }    
      public boolean kontrolEt(String secilenSayi,JToggleButton secilenDizi[]){
            boolean kontrol = false;
        for (JToggleButton btn2 : secilenDizi) {
            if (btn2 != null){
                String command = btn2.getActionCommand();
                if (command.equals(secilenSayi)) {
                    kontrol = true;
                }
            }
        }
            return kontrol;
    }
     public boolean kontrolEtOyunSon(JToggleButton secilenDizi[]){
       boolean isOyunSon = true;
       for (JToggleButton btn1 : secilenDizi) {
            if (btn1 == null){
                isOyunSon = false;
            }
       }
       return isOyunSon;
     }
     
     public void butonTemizle(){
         for (JToggleButton btn : buttonList) {
             if(btn.isEnabled()){
                 btn.setText("");
             }            
         }     
     }
}

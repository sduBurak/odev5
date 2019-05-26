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
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

/**
 *
 * @author uluzm
 */
public class OyunController {
    private OyunModel oyunModel;
    private JPanel jpanelContainer;
      ActionListenerSinifi actionListenerSinifi;
    public OyunController(OyunModel oyunModel,JPanel jpanelContainer){
        this.oyunModel=oyunModel;
        this.jpanelContainer = jpanelContainer;
     }
    
    public void modelVeriDoldur(int adet,String selectedLayouth){
        oyunModel.setAdet(adet);
        oyunModel.setSelectedLayouth(selectedLayouth);
    }
    public int sayac = 0;
    public void buttonUret(TimerController timerController,JLabel puanLabel){
       
         actionListenerSinifi = new ActionListenerSinifi(this.oyunModel,this.jpanelContainer,puanLabel,timerController);       
    }
    public void butonTemizle(){
       actionListenerSinifi.butonTemizle();
    }
    
}

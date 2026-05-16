/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.accountingmanagement;

import javax.swing.*;
import java.awt.Color;

/**
 *
 * @author nagihan_imamoğlu
 */
public class DarkLight<T extends JComponent> {

    public void DarkMode(T component) {
        component.setBackground(Color.GRAY);
        component.setForeground(Color.WHITE);
    }

    public void LightMode(T component) {
        component.setBackground(Color.WHITE);
        component.setForeground(Color.GRAY);
    }
}

class Main {

    public static void main(String[] args) {

    }
}

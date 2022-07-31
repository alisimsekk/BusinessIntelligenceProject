package com.alisimsek.javabootcamp.finalproject.helper;

import java.awt.*;

import javax.swing.*;

public class Helper {
	public static int screenCenterPoint(String axis, Dimension size){
        int point;
        switch (axis) {
            case "x":
                point = (Toolkit.getDefaultToolkit().getScreenSize().width - size.width) / 2;
                break;
            case "y":
                point = (Toolkit.getDefaultToolkit().getScreenSize().height - size.height) / 2;
                break;
            default:
                point = 0;
        }
        return point;
    }
	
	public static boolean isFieldEmpty(JTextField field){
        return field.getText().trim().isEmpty();
    }
	
	public static void showMsg(String str){
        optionPaneTR();
        String title =  "Mesaj";
        JOptionPane.showMessageDialog(null,str,title,JOptionPane.INFORMATION_MESSAGE);
    }

	public static void optionPaneTR(){
        UIManager.put("OptionPane.okButtonText", "Tamam");
    }
	
}

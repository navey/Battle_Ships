package com.company;

import javax.swing.*;

public class RepeatedNumberException extends Exception {

    public RepeatedNumberException(){
        JOptionPane.showMessageDialog(null, "You hit this spot already!", "Battle Ships", JOptionPane.INFORMATION_MESSAGE);
    }
}

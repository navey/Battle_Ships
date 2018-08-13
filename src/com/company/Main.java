package com.company;

import javax.swing.JOptionPane;

public class Main {

    public static void main(String[] args) {

        String input;
        int save;
        int load;
        Board user;
        Board comp;
        ShipBoard shipUser;
        ShipBoard shipComp;

        load = JOptionPane.showConfirmDialog(null, "Welcome to Battle Ships!\n Would you like to load the previously saved data?", "Battle Ships", JOptionPane.YES_NO_OPTION);

        if (load == 0) {
            shipUser = new ShipBoard("loadUser");
            shipComp = new ShipBoard("loadComp");


            user = new Board("loadUser", shipUser.getBoard());
            comp = new Board("loadComp", shipUser.getBoard());
        }
        else{
            shipUser = new ShipBoard();
            shipComp = new ShipBoard();

            user = new Board(shipUser.getBoard());
            comp = new Board(shipComp.getBoard());
        }

        input = JOptionPane.showInputDialog(null,
                "Enter a row position \n" + user.display());

        while(true){

            try {
                user.shot(input);
                user.checkGame("user");

                JOptionPane.showMessageDialog(null, "It is the Computer's turn", "Battle Ships", JOptionPane.INFORMATION_MESSAGE);

                comp.compShot();
                comp.checkGame("comp");


                input = JOptionPane.showInputDialog(null,
                        "Enter a position \n" + user.display());
            }
            catch(NullPointerException e){
                save = JOptionPane.showConfirmDialog(null, "Would you like to save?", "Battle Ships", JOptionPane.YES_NO_OPTION);
                if (save == 0){
                    user.save(0);
                    comp.save(1);
                    shipUser.save(0);
                    shipComp.save(1);
                    JOptionPane.showMessageDialog(null, "Save Successful", "Battle Ships", JOptionPane.INFORMATION_MESSAGE);
                    System.exit(0);
                }
                else if (save == 1){
                    System.exit(0);
                }
            }
        }
    }
}

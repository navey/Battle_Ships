package com.company;

import java.io.*;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;

public class ShipBoard{

    private String[][] board = {{"01","02","03","04","05","06","07","08","09","10",},
                                {"11","12","13","14","15","16","17","18","19","20",},
                                {"21","22","23","24","25","26","27","28","29","30",},
                                {"31","32","33","34","35","36","37","38","39","40",},
                                {"41","42","43","44","45","46","47","48","49","50",},
                                {"51","52","53","54","55","56","57","58","59","60",},
                                {"61","62","63","64","65","66","67","68","69","70",},
                                {"71","72","73","74","75","76","77","78","79","80",}};

    public ShipBoard(){
        generateSubmarine();
        generateAircraftCarrier();
        generatePatrolBoat();
        generateDestroyer();
        generateBattleShip();
    }

    /**
     * Generates previously saved shipboard for both user and computer.
     * @param a determines whether the shipboard is the user's or computer's
     */
    public ShipBoard(String a){
        int row = 0;
        int col = 0;

        File file;

        if(a.equals("loadUser")){
            file = new File("/Users/Naveen/Desktop/JavaAppSaveLogs/BattleShipSaveLog/UserShipBoard.txt");
        }
        else{
            file = new File("/Users/Naveen/Desktop/JavaAppSaveLogs/BattleShipSaveLog/CompShipBoard.txt");
        }

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String st = br.readLine();

            for(int i = 0; i < st.length() - 2 ; i = i + 3) {
                board[row][col] = st.substring(i, i + 2);
                if (col / 9 == 1) {
                    row = row + 1;
                    col = 0;
                }
                else {
                    col = col + 1;
                }
            }
        }
        catch(FileNotFoundException e){
            System.out.println("error");
        }
        catch(IOException e){
            System.out.println("error");
        }

    }

    /**
     * Used to generate the submarine that is 3 spots in length
     */
    public void generateSubmarine(){
        int row = (int) (Math.random() * 6 + 1);
        int col = (int) (Math.random() * 8 + 1);

        board[row][col] = "XX";
        int num = (int) (Math.random() * 2 + 1);
        if(num % 2 == 0){
            board[row][col+1] = "XX";
            board[row][col-1] = "XX";
        }
        else{
            board[row+1][col] = "XX";
            board[row-1][col] = "XX";
        }
    }

    /**
     * Used to generate an Aircraft Carrier which is 5 spots in length
     */
    public void generateAircraftCarrier(){
        int row = (int) (Math.random() * 4 + 2);
        int col = (int) (Math.random() * 6 + 2);

        board[row][col] = "XX";
        int num = (int) (Math.random() * 2 + 1);
        if(num % 2 == 0){
            board[row][col+1] = "XX";
            board[row][col+2] = "XX";
            board[row][col-1] = "XX";
            board[row][col-2] = "XX";
        }
        else{
            board[row+1][col] = "XX";
            board[row+2][col] = "XX";
            board[row-1][col] = "XX";
            board[row-2][col] = "XX";
        }
    }

    /**
     * Used to generate a Destroyer that is 4 spots in length
     */
    public void generateDestroyer(){
        int row = (int) (Math.random() * 6 + 1);
        int col = (int) (Math.random() * 8 + 1);

        board[row][col] = "XX";
        int num = (int) (Math.random() * 2 + 1);
        if(num % 2 == 0){
            board[row][col+1] = "XX";
            board[row][col-1] = "XX";
        }
        else{
            board[row+1][col] = "XX";
            board[row-1][col] = "XX";
        }
    }

    /**
     * Used to generate a Patrol Boat which is 2 spots in length
     */
    public void generatePatrolBoat(){
        int row = (int) (Math.random() * 6 + 1);
        int col = (int) (Math.random() * 8 + 1);

        board[row][col] = "XX";
        int num = (int) (Math.random() * 2 + 1);
        if(num % 2 == 0){
            board[row][col+1] = "XX";
        }
        else{
            board[row+1][col] = "XX";
        }
    }

    /**
     * Used to generate a BattleShip which is 4 spots in length
     */
    public void generateBattleShip(){
        int row = (int) (Math.random() * 4 + 2);
        int col = (int) (Math.random() * 6 + 2);

        board[row][col] = "XX";
        int num = (int) (Math.random() * 2 + 1);
        if(num % 2 == 0){
            board[row][col+1] = "XX";
            board[row][col+2] = "XX";
            board[row][col-1] = "XX";
        }
        else{
            board[row+1][col] = "XX";
            board[row+2][col] = "XX";
            board[row-1][col] = "XX";
        }
    }

    public void save(int select){
        String fileName;

        if (select == 0){
            fileName = "UserShipBoard";
        }
        else{
            fileName = "CompShipBoard";
        }

        FileSystem fs = FileSystems.getDefault();
        Path file = fs.getPath("/Users/Naveen/Desktop/JavaAppSaveLogs/BattleShipSaveLog/" + fileName + ".txt");

        OutputStream output ;
        OutputStream initializer;
        BufferedWriter writer;

        try{
            initializer = new BufferedOutputStream(Files.newOutputStream(file, CREATE));

            Files.delete(file);

            output = new BufferedOutputStream(Files.newOutputStream(file, CREATE, APPEND));
            writer = new BufferedWriter(new OutputStreamWriter(output));


            for(int i = 0; i < 8; i++){
                for(int j = 0; j < 10; j++){
                    String data = board[i][j];
                    writer.write(data, 0, data.length());
                    writer.write(",");
                }
            }
            writer.close();

        }

        catch(IOException e){
            System.out.println("Error");
        }
    }


    public String[][] getBoard(){
        return board;
    }
}


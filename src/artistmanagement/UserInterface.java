/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artistmanagement;

import java.sql.*;


import java.util.Scanner;

public class UserInterface {

    static boolean Loop = true;
    static Scanner sc = new Scanner(System.in);

    public static int readNumber() {

        int num = 0;
        try {
            num = sc.nextInt();
         
        } catch (Exception e) {
            sc.nextLine();
            System.err.println("Wrong input!");
            System.out.print("Try again: ");

        }
        sc.nextLine();
        return num;

    }

    public static String readText() {
     String txt = sc.nextLine();
        while (!isName(txt)) {
                    System.err.println("Invalid input! Enter name: ");
                    txt = sc.nextLine();
        }

        return txt;

    }

    public static boolean isName(String name) {
        String alfabet = "ABCDEFJHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuwxyz ";
        for (int i = 0; i < name.length(); i++) {
            boolean isWord = false;
            for (int j = 0; j < alfabet.length(); j++) {
                if (name.charAt(i) == alfabet.charAt(j)) {
                    isWord = true;
                }
            }
            if (!isWord) {
                return false;
            }
        }
        return true;
    }

    public int mainMenu() {
        System.out.println("\n************Main Menu*************");
        System.out.println("\n");
        System.out.println("Enter your Choice");
        System.out.println("1. Add Artist");
        System.out.println("2. Update Artist");
        System.out.println("3. Find Artist");
        System.out.println("4. Delet Artist");
        System.out.println("5. Show All Artist");
        System.out.println("6. Exit");
        return readNumber();
    }

    public static int updateArtist() {
        System.out.println("\n************Update Menu*************");
        System.out.println("Enter your Choice");
        System.out.println("1. Update artists first name");
        System.out.println("2. Update artists last name");
        System.out.println("3. Update artists age ");
        System.out.println("4. Main Menu ");
        System.out.println("5. Exit");

        return readNumber();
    }

    public static int findArtist() {
        System.out.println("\n************Search Menu*************");
        System.out.println("Enter your Choice");
        System.out.println("1. Find artist by id");
        System.out.println("2. Find artist by first name");
        System.out.println("3. Main Menu ");
        System.out.println("4. Exit");
        return readNumber();
    }

    public void switch_ChoiceMainMenu(int ch) throws SQLException, Exception {
        dbConnection db = new dbConnection("demo");
        int x = 0;
        int id = 0;
        switch (ch) {

            case 1: // add artist
                System.out.println("Enter first name of Artist: ");
                String fname = readText();
                System.out.println("Enter last name of Artist: ");
                String lname = readText();
                System.out.println("Enter age of Artist: ");
                int age = readNumber();
                db.addArtist(fname, lname, age);
                break;

            case 2:
                while (x != 4) {
                    x = updateArtist();
                    // process the use choice 
                    subUpdate(x);
                }
                break;

            case 3:

                while (x != 3) {

                    x = findArtist();
                    // process the use choice
                    subFind(x);
                }
                break;
            case 4: // delete artist by id
                System.out.println("Enter artis id to delete : ");
                id = readNumber();
                db.deleteArtistbyId(id);
                break;
            case 5:
                db.showAllArtist();
                break;

            case 6:
                System.exit(0);
                break;

            default:
                System.out.println("Try a Number between 1 to 6 ");
                //mainMenu();
                break;
        }
    }

    public void subUpdate(int ch) throws SQLException, Exception {
        dbConnection db = new dbConnection("demo");
        int id = 0;
        String fname = null;
        String lname = null;
        int age = 0;
        switch (ch) {

            case 1:
                // Update Artist's first name
                System.out.println("Enter first name of Artist: ");
                fname = readText();
                System.out.println("Enter id of Artist: ");
                id = readNumber();
                db.updateFirstName(fname, id);

                break;
            case 2:
                // Update Artist's last name
                System.out.println("Enter last name of Artist: ");
                lname = readText();
                System.out.println("Enter id of Artist: ");
                id = readNumber();
                db.updateLastName(lname, id);

                break;
            case 3:
                // Update Artist's age
                System.out.println("Enter first name of Artist: ");
                fname = sc.nextLine();
                System.out.println("Enter new age of Artist: ");
                age = sc.nextInt();

                db.updateAge(age, fname);
                break;
            case 4:
                break;
            case 5:
                System.exit(0);
                break;

            default:
                System.out.println("No such choice! ****TRY AGAIN*** ");

        }

    }

    public void subFind(int ch) throws SQLException, Exception {
        dbConnection db = new dbConnection("demo");
        int id = 0;
        String fname = null;

        switch (ch) {
            case 1: // Find artist by id
                System.out.println("Enter artis id: ");
                id = readNumber();
                db.showArtistById(id);
                break;
            case 2: // Find artist by name
                System.out.println("Enter artis's first name: ");
                fname = readText();
                db.showArtistByFirstName(fname);
                break;
            case 3: //main menu
                break;
            case 4: // exit
                System.exit(0);
                break;
            default:
                System.out.println("No such choice! ****TRY AGAIN*** ");

        }
    }
     
}

package artistmanagement;

import java.sql.SQLException;

public class main {

    public static void main(String[] args) throws SQLException, Exception {
        dbConnection db = new dbConnection("demo");
        UserInterface us = new UserInterface();
        int ch = us.mainMenu();
        while (ch != 6) {
            us.switch_ChoiceMainMenu(ch);
            ch = us.mainMenu();
        }

    }

}

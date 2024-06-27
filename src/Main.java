import util.UI;

public class Main {
    public static void main(String[] args) {
        String action = "main_menu";

        boolean run = true;
        while (run) {
            switch (action) {
                case "main_menu":
                    action = UI.mainMenu();
                    break;
                case "create_owner":
                    action = UI.createNewOwner();
                    break;
                case "choose_owner":
                    action = UI.chooseOwner();
                    break;
                case "owner_menu":
                    action = UI.ownerMenu();
                    break;
                case "exit":
                    run = false;
                    break;
                default:
                    System.out.println("Something went wrong");
                    break;
            }
        }
    }
}
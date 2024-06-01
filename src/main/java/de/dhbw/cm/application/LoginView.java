package de.dhbw.cm.application;

import de.dhbw.cm.domain.User;
import de.dhbw.cm.domain.UserManager;

public class LoginView {
    UserManager userManager;
    ConsoleWriter cw;
    ConsoleReader cr;

    public LoginView(ConsoleWriter consoleWriter, ConsoleReader consoleReader) {
        this.userManager = new UserManager();
        this.cw = consoleWriter;
        this.cr = consoleReader;
    }

    public void show() {
        cw.clearConsole();
        showLogo();
        cw.write(String.valueOf(userManager.getUsers()));
        cw.write(System.getProperty("user.dir"));
        cw.write(AnsiCodes.YELLOW,
                "Choose an option:\n" +
                        "\t1. Login\n" +
                        "\t2. Create New Account\n" +
                        "\t3. Exit\n");
        validateInput();
    }

    void showLogo() {
        cw.write(AnsiCodes.CYAN, "" +
                "███╗   ██╗ ██████╗ ██████╗ ███████╗███████╗      ███╗   ███╗ █████╗ ███╗   ██╗ █████╗  ██████╗ ███████╗██████╗ \n" +
                "████╗  ██║██╔═══██╗██╔══██╗██╔════╝██╔════╝      ████╗ ████║██╔══██╗████╗  ██║██╔══██╗██╔════╝ ██╔════╝██╔══██╗\n" +
                "██╔██╗ ██║██║   ██║██║  ██║█████╗  ███████╗█████╗██╔████╔██║███████║██╔██╗ ██║███████║██║  ███╗█████╗  ██████╔╝\n" +
                "██║╚██╗██║██║   ██║██║  ██║██╔══╝  ╚════██║╚════╝██║╚██╔╝██║██╔══██║██║╚██╗██║██╔══██║██║   ██║██╔══╝  ██╔══██╗\n" +
                "██║ ╚████║╚██████╔╝██████╔╝███████╗███████║      ██║ ╚═╝ ██║██║  ██║██║ ╚████║██║  ██║╚██████╔╝███████╗██║  ██║\n" +
                "╚═╝  ╚═══╝ ╚═════╝ ╚═════╝ ╚══════╝╚══════╝      ╚═╝     ╚═╝╚═╝  ╚═╝╚═╝  ╚═══╝╚═╝  ╚═╝ ╚═════╝ ╚══════╝╚═╝  ╚═╝\n");
    }

    void validateInput() {
        String choice = cr.readLine();
        if (choice.equals("1")) {
            login();
        } else if (choice.equals("2")) {
            createAccount();
        } else if (choice.equals("3")) {
            cw.write(AnsiCodes.YELLOW, "\nExiting...");
            System.exit(1);
        } else {
            cw.write(AnsiCodes.RED, "\nInvalid choice. Please try again.\n");
            validateInput();
        }
    }

    void login() {
        cw.clearConsole();
        cw.write(AnsiCodes.CYAN, "Login\n");
        performLogin();
    }

    private void performLogin() {
        cw.writeInLine(AnsiCodes.YELLOW, "Enter username (or 'b' to go back): ");
        String username = cr.readLine();
        if (username.equalsIgnoreCase("b")) {
            show();
        }
        cw.writeInLine(AnsiCodes.YELLOW, "Enter password: ");
        String password = cr.readLine();

        User user = userManager.loginUser(username, password);
        if (user != null) {
            cw.write(AnsiCodes.GREEN, "\nLogin successful!\n");
            new OverviewView(user, username, cw, cr).show(); //open overview page
            return;
        } else {
            cw.write(AnsiCodes.RED, "\nInvalid username or password. Please try again.\n");
            performLogin();
        }
    }

    void createAccount() {
        cw.clearConsole();
        cw.write(AnsiCodes.CYAN, "Create New Account\n");
        performCreateAccount();
    }

    private void performCreateAccount() {
        cw.writeInLine(AnsiCodes.YELLOW, "Enter new username (or 'b' to go back): ");
        String username = cr.readLine();
        if (username.equalsIgnoreCase("b")) {
            show();
        }
        cw.writeInLine(AnsiCodes.YELLOW, "Enter new password: ");
        String password = cr.readLine();

        if (userManager.createUser(username, password)) {
            cw.write(AnsiCodes.GREEN, "\nAccount created successfully!\n");
            show();
        } else {
            cw.write(AnsiCodes.RED, "\nUsername already exists. Please try a different username.\n");
            performCreateAccount();
        }
    }
}

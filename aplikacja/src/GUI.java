import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * The main class which represents graphical user interface
 */
public class GUI{
    Frame frame;
    MenuBar menu;
    Menu fileMenu, optionsMenu, helpMenu;
    Panel centralPanel;
    Button[] buttons = new Button[10];
    static String enteredUsername;
    static String enteredPassword;
    Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();
    int screenWidth = (int)(screenDimension.width * 0.7);
    int screenHeight = (int)(screenDimension.height * 0.7);
    Image icon = Toolkit.getDefaultToolkit().getImage(".//resources//icon.JPG");
    User user = new User();
    User loggedUser = new User();
    User userToEdit = new User();
    int numberOfAttempts = 0;
    int SCREEN_RES = 0;

    /**
     * The basic Constructor which creates basic frame of Main menu.
     */
    public GUI() {
        frame = new Frame("Patient Service System - Orthodontic office");
        frame.addWindowListener(
                new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        System.exit( 0 );
                    }
                }
        );
        menu = new MenuBar();
        centralPanel = new Panel();

        fileMenu = new Menu("File");
        optionsMenu = new Menu("Options");
        helpMenu = new Menu("Help");
    }

    /**
     * The method which creates Buttons in main frame for Orthodontist
     */
    private void createButtonsOrthodontist(){
        buttons[0] = new Button("My visits");
        buttons[0].setBackground(Color.getHSBColor(178.8f,102.2f,85.1f));
        buttons[1] = new Button("My mailbox");
        buttons[1].setBackground(Color.getHSBColor(178.8f,102.2f,85.1f));
        buttons[2] = new Button("My profile");
        buttons[2].setBackground(Color.getHSBColor(178.8f,102.2f,85.1f));
        buttons[3] = new Button("Log out");
        buttons[3].setBackground(Color.getHSBColor(178.8f,102.2f,85.1f));
        buttons[4] = new Button("Exit the program");
        buttons[4].setBackground(Color.getHSBColor(178.8f,102.2f,85.1f));

        buttons[0].addActionListener(e -> {
            frame.removeAll();
            myVisitsOrthodontist();
        });

        buttons[1].addActionListener(e -> {
            frame.removeAll();
            myMailboxOrthodontist();
        });

        buttons[2].addActionListener(e -> {
            frame.removeAll();
            myProfileOrthodontist();
        });

        buttons[3].addActionListener(e -> {
            frame.dispose();
            loginScreen();
        });
        buttons[4].addActionListener(e -> {
            frame.dispose();
            System.exit(0);
        });
    }

    /**
     * The method which creates Buttons in main frame for Patient
     */
    private void createButtonsPatient(){
        buttons[0] = new Button("My visits");
        buttons[0].setBackground(Color.getHSBColor(137.4f,101.1f,85.1f));
        buttons[1] = new Button("My mailbox");
        buttons[1].setBackground(Color.getHSBColor(137.4f,101.1f,85.1f));
        buttons[2] = new Button("My profile");
        buttons[2].setBackground(Color.getHSBColor(137.4f,101.1f,85.1f));
        buttons[3] = new Button("Log out");
        buttons[3].setBackground(Color.getHSBColor(137.4f,101.1f,85.1f));
        buttons[4] = new Button("Exit the program");
        buttons[4].setBackground(Color.getHSBColor(137.4f,101.1f,85.1f));

        buttons[0].addActionListener(e -> {
            frame.removeAll();
            myVisitsPatient();
        });

        buttons[1].addActionListener(e -> {
            frame.removeAll();
            myMailboxPatient();
        });

        buttons[2].addActionListener(e -> {
            frame.removeAll();
            myProfilePatient();
        });

        buttons[3].addActionListener(e -> {
            frame.dispose();
            loginScreen();
        });
        buttons[4].addActionListener(e -> {
            frame.dispose();
            System.exit(0);
        });
    }

    /**
     * The method which creates Buttons in main frame for Developer
     */
    private void createButtonsDeveloper(){
        buttons[0] = new Button("Find user in database");
        buttons[0].setBackground(Color.lightGray);
        buttons[1] = new Button("Edit users");
        buttons[1].setBackground(Color.lightGray);
        buttons[2] = new Button("Add user");
        buttons[2].setBackground(Color.lightGray);
        buttons[3] = new Button("Delete user");
        buttons[3].setBackground(Color.lightGray);
        buttons[4] = new Button("My profile");
        buttons[4].setBackground(Color.lightGray);
        buttons[5] = new Button("Log out");
        buttons[5].setBackground(Color.lightGray);
        buttons[6] = new Button("Exit the program");
        buttons[6].setBackground(Color.lightGray);

        buttons[0].addActionListener(e -> {
            frame.removeAll();
            findUser();
        });

        buttons[1].addActionListener(e -> {
            frame.removeAll();
            editUsers();
        });

        buttons[2].addActionListener(e -> {
            frame.removeAll();
            addUser();
        });

        buttons[3].addActionListener(e -> {
            frame.removeAll();
            deleteUser();
        });

        buttons[4].addActionListener(e -> {
            frame.removeAll();
            myProfileDeveloper();
        });

        buttons[5].addActionListener(e -> {
            frame.dispose();
            loginScreen();
        });
        buttons[6].addActionListener(e -> {
            frame.dispose();
            System.exit(0);
        });
    }

    /**
     * The method which configures Menu Bars in main frame
     */
    public void configureMenuBars(){
        fileMenu = new Menu("File");
        helpMenu = new Menu("Help");
        optionsMenu = new Menu("Options");

        menu.add(fileMenu);
        menu.add(optionsMenu);
        menu.add(helpMenu);

        MenuItem loadFileItem = new MenuItem("Load file");
        MenuItem exitMenuItem = new MenuItem("Exit");
        MenuItem resetTheGraphicalInterface = new MenuItem("Reset the graphical user interface (Log out from the app)");
        MenuItem openSettingsItem = new MenuItem("Open settings");
        MenuItem yourAccountItem = new MenuItem("Your account");
        MenuItem helpItem = new MenuItem("Help");
        MenuItem gettingStartedItem = new MenuItem("Getting started");
        MenuItem checkForUpdatesItem = new MenuItem("Check for updates");
        MenuItem aboutItem = new MenuItem("About");

        loadFileItem.addActionListener(e -> {
            System.out.println("Button in progress...."); // TODO, maybe a method which can read png files
        });
        exitMenuItem.addActionListener(e -> {
            frame.dispose();
            frame.setVisible(false);
            System.exit(0);
        });

        fileMenu.add(loadFileItem);
        fileMenu.add(exitMenuItem);

        resetTheGraphicalInterface.addActionListener(e -> {
            frame.dispose();
            frame.setLayout(null);
            loginScreen();
        });
        openSettingsItem.addActionListener(e -> {
            Frame settingsFrame = new Frame("Settings");
            settingsFrame.setIconImage(icon);
            settingsFrame.setVisible(true);
            Label message = new Label("Resize the window using these options:", Label.CENTER);
            Button normalButton = new Button("Standard (70% screen size)");
            normalButton.addActionListener(e1 -> {
                    frame.setSize(screenWidth,screenHeight);
                    
                    frame.setLocationRelativeTo(null);
                    SCREEN_RES = 0;
                    frame.setLayout(new BoxLayout(frame, BoxLayout.Y_AXIS));
            });
            Button greaterButton = new Button("Greater (90% screen size)");
            greaterButton.addActionListener(e1 -> {
                frame.setSize((int)(screenWidth*1.3),(int)(screenHeight*1.3));
                frame.setLocationRelativeTo(null);
                SCREEN_RES = 1;
                frame.setLayout(new BoxLayout(frame, BoxLayout.Y_AXIS));
            });
            Button fullscreenButton = new Button("Fullscreen (100% screen size)");
            fullscreenButton.addActionListener(e1 -> {
                frame.setSize(screenDimension.width, screenDimension.height);
                frame.setLocationRelativeTo(null);
                SCREEN_RES = 3;
                frame.setLayout(new BoxLayout(frame, BoxLayout.Y_AXIS));
            });
            settingsFrame.add(message);
            settingsFrame.add(normalButton);
            settingsFrame.add(greaterButton);
            settingsFrame.add(fullscreenButton);
            settingsFrame.setLayout(new BoxLayout(settingsFrame, BoxLayout.Y_AXIS));
            Point location = frame.getLocation();
            location.x += frame.getWidth()/3;
            location.y += frame.getHeight()/5;
            settingsFrame.setLocation(location);
            settingsFrame.setSize(frame.getWidth()/3,frame.getHeight()/3);
            settingsFrame.setVisible(true);
            settingsFrame.addWindowListener(new WindowAdapter(){
                @Override
                public void windowClosing(WindowEvent e) {
                    super.windowClosing(e);
                    settingsFrame.setVisible(false);
                    settingsFrame.dispose();
                    if (user == null)
                        System.exit(0);
                }

            });
        });

        optionsMenu.add(resetTheGraphicalInterface);
        optionsMenu.add(openSettingsItem);

        yourAccountItem.addActionListener(e -> {
            if(loggedUser.getUserPermissionsLevel()==0) {
                frame.removeAll();
                myProfilePatient();
            }
            else if(loggedUser.getUserPermissionsLevel()==1){
                frame.removeAll();
                myProfileOrthodontist();
            }
            else if(loggedUser.getUserPermissionsLevel()==2){
                frame.removeAll();
                myProfileDeveloper();
            }
        });
        helpItem.addActionListener(e -> {
            Frame helpFrame = new Frame("Help");
            helpFrame.setIconImage(icon);
            helpFrame.setVisible(true);
            Label message = new Label("For assistance with the application, please contact the administrator", Label.CENTER);
            helpFrame.add(message);
            helpFrame.setLayout(new BoxLayout(helpFrame, BoxLayout.Y_AXIS));
            Point location = frame.getLocation();
            location.x += frame.getWidth()/3;
            location.y += frame.getHeight()/5;
            helpFrame.setLocation(location);
            helpFrame.setSize(frame.getWidth()/3,frame.getHeight()/6);
            helpFrame.setVisible(true);
            helpFrame.addWindowListener(new WindowAdapter(){
                @Override
                public void windowClosing(WindowEvent e) {
                    super.windowClosing(e);
                    helpFrame.setVisible(false);
                    helpFrame.dispose();
                    if (user == null)
                        System.exit(0);
                }

            });
        });
        gettingStartedItem.addActionListener(e -> {
            Frame gettingStartedFrame = new Frame("Getting Started");
            gettingStartedFrame.setIconImage(icon);
            gettingStartedFrame.setVisible(true);
            Label message = new Label("For more instructions please read README.md file", Label.CENTER);
            Label message2 = new Label("!!!This file is located in SystemObslugiPacjenta-GabinetOrtodontyczny directory", Label.CENTER);
            gettingStartedFrame.add(message);
            gettingStartedFrame.add(message2);
            Point location = frame.getLocation();
            location.x += frame.getWidth()/3;
            location.y += frame.getHeight()/5;
            gettingStartedFrame.setLocation(location);
            gettingStartedFrame.setSize(frame.getWidth()/3,frame.getHeight()/5);
            gettingStartedFrame.setVisible(true);
            gettingStartedFrame.setLayout(new BoxLayout(gettingStartedFrame, BoxLayout.Y_AXIS));
            gettingStartedFrame.addWindowListener(new WindowAdapter(){
                @Override
                public void windowClosing(WindowEvent e) {
                    super.windowClosing(e);
                    gettingStartedFrame.setVisible(false);
                    gettingStartedFrame.dispose();
                    if (user == null)
                        System.exit(0);
                }

            });
        });
        checkForUpdatesItem.addActionListener(e -> {
            Frame updateFrame = new Frame("Check for updates");
            updateFrame.setIconImage(icon);
            updateFrame.setVisible(true);
            Label message = new Label("You have the latest version installed, current version: 1.00000023", Label.CENTER);
            updateFrame.add(message);
            updateFrame.setLayout(new BoxLayout(updateFrame, BoxLayout.Y_AXIS));
            updateFrame.setResizable(false);
            Point location = frame.getLocation();
            location.x += frame.getWidth()/3;
            location.y += frame.getHeight()/5;
            updateFrame.setLocation(location);
            updateFrame.setSize(frame.getWidth()/3,frame.getHeight()/6);
            updateFrame.setVisible(true);
            updateFrame.addWindowListener(new WindowAdapter(){
                @Override
                public void windowClosing(WindowEvent e) {
                    super.windowClosing(e);
                    updateFrame.setVisible(false);
                    updateFrame.dispose();
                    if (user == null)
                        System.exit(0);
                }

            });
        });
        aboutItem.addActionListener(e -> {
            Frame aboutFrame = new Frame("About Patient Service System - Orthodontic office");
            aboutFrame.setIconImage(icon);
            Panel aboutPanel = new Panel();
            Label message = new Label("Build #OP-4214.2421323, built on January 10, 2022", Label.CENTER);
            Label message2 = new Label("Runtime version: 1.00312313123 amd64", Label.CENTER);
            Label message3 = new Label("Powered by open-source software", Label.CENTER);
            Label message4 = new Label("Copyright © 2022-2042 Orthodontic office systems s.r.o.", Label.CENTER);

            aboutPanel.add(message);
            aboutPanel.add(message2);
            aboutPanel.add(message3);
            aboutPanel.add(message4);
            aboutFrame.setResizable(false);
            Point location = frame.getLocation();
            location.x += frame.getWidth()/3;
            location.y += frame.getHeight()/5;
            aboutFrame.setLocation(location);
            aboutFrame.setSize(frame.getWidth()/3,frame.getHeight()/4);
            aboutPanel.setVisible(true);
            aboutFrame.setVisible(true);
            aboutFrame.add(aboutPanel);
            aboutFrame.setLayout(new BoxLayout(aboutFrame, BoxLayout.Y_AXIS));
            aboutFrame.addWindowListener(new WindowAdapter(){
                @Override
                public void windowClosing(WindowEvent e) {
                    super.windowClosing(e);
                    aboutFrame.setVisible(false);
                    aboutFrame.dispose();
                    if (user == null)
                        System.exit(0);
                }

            });
        });
        helpMenu.add(yourAccountItem);
        helpMenu.add(helpItem);
        helpMenu.add(gettingStartedItem);
        helpMenu.add(checkForUpdatesItem);
        helpMenu.add(aboutItem);
    }

    /**
     * The method which adds Panels in main frame
     */
    private void addPanels(){
        frame.add(centralPanel);
        frame.setVisible(true);
    }

    /**
     * The method which is responsible for adding exit button for developer to GUI
     */
    public void addExitButtonDeveloper(){
        numberOfAttempts = 0;
        Panel editUserPanel = new Panel();
        Button exitButton = new Button("Return to main menu");
        exitButton.setBackground(Color.yellow);
        editUserPanel.add(exitButton);
        exitButton.addActionListener(e -> {
            frame.setLayout(null);
            runGUIDeveloper();
            frame.setVisible(true);
        });
        frame.add(editUserPanel);
    }

    /**
     * The method which is responsible for adding exit button for orthodontist to GUI
     */
    public void addExitButtonOrthodontist(){
        numberOfAttempts = 0;
        Panel editUserPanel = new Panel();
        Button exitButton = new Button("Return to main menu");
        exitButton.setBackground(Color.yellow);
        editUserPanel.add(exitButton);
        exitButton.addActionListener(e -> {
            frame.setLayout(null);
            runGUIOrthodontist();
            frame.setVisible(true);
        });
        frame.add(editUserPanel);
    }

    /**
     * The method which is responsible for adding exit button for patient to GUI
     */
    public void addExitButtonPatient(){
        numberOfAttempts = 0;
        Panel editUserPanel = new Panel();
        Button exitButton = new Button("Return to main menu");
        exitButton.setBackground(Color.yellow);
        editUserPanel.add(exitButton);
        exitButton.addActionListener(e -> {
            frame.setLayout(null);
            runGUIPatient();
            frame.setVisible(true);
        });
        frame.add(editUserPanel);
    }

    /**
     * The method which is responsible for finding the user by e-mail address
     */
    public void findUser(){
        enteredUsername = "null";
        Connection connection = DataBaseHandlingClass.StartConnectionWithDB();
        User user2 = DataBaseHandlingClass.LogInUser(connection, "admin", "admin");
        assert user2 != null;
        List<User> list = DataBaseHandlingClass.SearchForAllUsers(connection, user2);

        Panel welcomePanel = new Panel();
        Panel findPanelMessage = new Panel();
        Panel findUserPanel = new Panel(new GridBagLayout());

        Label usernameLabel = new Label("Username: ");
        Label userStatement = new Label("Please enter the username to see user's credentials");

        TextField userField = new TextField(20);

        Button loginButton = new Button("Find user");
        ActionListener action = e -> {
            numberOfAttempts++;
            try {
                enteredUsername = userField.getText();

                boolean userFound = false;
                assert list != null;
                for(User userToFind: list){
                    if(userToFind.getUserLogin().equals(enteredUsername)){
                        userFound = true;
                        userToEdit = userToFind;
                    }
                }
                if(userFound && userToEdit.getUserPermissionsLevel()==0){
                    List<Visit> visitsToView = DataBaseHandlingClass.SearchForVisitsOfPatient(connection, userToEdit);
                    if(visitsToView!=null) {
                        StringBuilder messageToView = new StringBuilder("You have found user: " + userToEdit.getUserName() + " " + userToEdit.getUserSurname() + ", with DB id: " + userToEdit.getUserId()
                                + ", this user has: " + visitsToView.size() + " visits, which took place on: ");
                        for (int i = 0; i < visitsToView.size(); i++) {
                            messageToView.append(visitsToView.get(i).getVisitDate());
                            if(i==visitsToView.size()-1)
                                messageToView.append(".");
                            else
                                messageToView.append(", ");
                        }
                        userStatement.setText(messageToView.toString());
                    }
                    else {
                        String messageToView = "You have found user: " + userToEdit.getUserName() + " " + userToEdit.getUserSurname() + ", with DB id: " + userToEdit.getUserId() + ", this user has 0 visits";
                        userStatement.setText(messageToView);
                    }
                }
                else if(userFound && userToEdit.getUserPermissionsLevel()!=0){
                    String messageToView = "You have found user: " + userToEdit.getUserName() + " " + userToEdit.getUserSurname() + ", with DB id: " + userToEdit.getUserId();
                    userStatement.setText(messageToView);
                }
                else {
                    userStatement.setText("There is no such user in the database (Attempt: " + numberOfAttempts + ")");
                }
            }
            catch(Exception exception){
                System.out.println("Exception caught!" + exception.getMessage());
                userStatement.setText("There is no such user in the database (Attempt: " + numberOfAttempts + ")");
            }
        };

        userField.addActionListener(action);
        loginButton.addActionListener(action);

        findUserPanel.add(usernameLabel);
        findUserPanel.add(userField);
        findUserPanel.setLayout(new BoxLayout(findUserPanel, BoxLayout.Y_AXIS));
        findUserPanel.add(loginButton);

        findPanelMessage.setLayout(new BoxLayout(findPanelMessage, BoxLayout.Y_AXIS));
        findPanelMessage.add(userStatement);

        frame.removeAll();
        frame.add(findUserPanel, BorderLayout.NORTH);
        frame.add(findPanelMessage, BorderLayout.CENTER);
        frame.add(welcomePanel, BorderLayout.SOUTH);
        addExitButtonDeveloper();
        frame.setLayout(new BoxLayout(frame, BoxLayout.Y_AXIS));
        frame.setVisible(true);
    }

    /**
     * The method which is responsible for adding new user to the database
     */
    public void addUser(){
        frame.removeAll();

        Panel editUserPanel = new Panel();
        Label info = new Label("When adding a user, remember to fill in all data, and prohibit creating duplicates, otherwise adding will fail");
        editUserPanel.add(info);
        Label username = new Label("Username: ");
        TextField usernameTextField = new TextField();
        Label password = new Label("Password: ");
        TextField passwordTextField = new TextField();
        Label name = new Label("Name: ");
        TextField nameTextField = new TextField();
        Label surname = new Label("Surname: ");
        TextField surnameTextField = new TextField();
        Label mobileNumber = new Label("Mobile number: ");
        TextField mobileNumberTextField = new TextField();
        Label mail = new Label("E-mail: ");
        TextField mailTextField = new TextField();
        Label level = new Label("Permission level (0 - Patient, 1 - Orthodontist, 2 - Developer): ");
        TextField levelTextField = new TextField();
        Label optionalOrthodontist = new Label("OPTIONAL [select only when adding a patient] Patient's Orthodontist: ");
        Choice orthodontistToSelect = new Choice();

        Connection connection = DataBaseHandlingClass.StartConnectionWithDB();
        List<User> listOfOrthodontist = DataBaseHandlingClass.SearchForAllOrthodontists(connection, loggedUser);
        if (listOfOrthodontist != null)
            for (User userToAdd : listOfOrthodontist) {
                orthodontistToSelect.add(userToAdd.getUserLogin());
            }

        editUserPanel.add(username);
        editUserPanel.add(usernameTextField);
        editUserPanel.add(password);
        editUserPanel.add(passwordTextField);
        editUserPanel.add(name);
        editUserPanel.add(nameTextField);
        editUserPanel.add(surname);
        editUserPanel.add(surnameTextField);
        editUserPanel.add(mobileNumber);
        editUserPanel.add(mobileNumberTextField);
        editUserPanel.add(mail);
        editUserPanel.add(mailTextField);
        editUserPanel.add(level);
        editUserPanel.add(levelTextField);
        editUserPanel.add(optionalOrthodontist);
        editUserPanel.add(orthodontistToSelect);

        editUserPanel.setLayout(new BoxLayout(editUserPanel, BoxLayout.Y_AXIS));

        Button editUserButton = new Button("Add new user to database");
        Label prompt = new Label("Please before pressing the button, check the correctness of the data");
        editUserPanel.add(prompt);
        editUserPanel.add(editUserButton);

        editUserButton.addActionListener(e -> {

            try {
                User orthodontistSelected = new User();
                if(listOfOrthodontist != null){
                    for(User userToFind: listOfOrthodontist){
                        if(userToFind.getUserLogin().equals(orthodontistToSelect.getSelectedItem())){
                            orthodontistSelected = userToFind;
                        }
                    }
                }

                if( Integer.parseInt(levelTextField.getText())>3 || Integer.parseInt(levelTextField.getText())<0 )
                    throw new Exception();

                if (!levelTextField.getText().isEmpty() && !mailTextField.getText().isEmpty() && !mobileNumberTextField.getText().isEmpty() && !surnameTextField.getText().isEmpty() &&
                        !nameTextField.getText().isEmpty() && !passwordTextField.getText().isEmpty() && !usernameTextField.getText().isEmpty()) {

                    User admin = DataBaseHandlingClass.LogInUser(connection, "admin", "admin");
                    assert admin != null;
                    List<User> list = DataBaseHandlingClass.SearchForAllUsers(connection, admin);
                    assert list != null;
                    for(User userToCheck: list) {
                        if(usernameTextField.getText().equals(userToCheck.getUserName()))
                            throw new Exception();
                    }

                    User userToAdd = new User();
                    userToAdd.setUserName(nameTextField.getText());
                    userToAdd.setUserSurname(surnameTextField.getText());
                    userToAdd.setUserLogin(usernameTextField.getText());
                    userToAdd.setUserPassword(passwordTextField.getText());
                    userToAdd.setUserEmail(mailTextField.getText());
                    userToAdd.setUserPermissionsLevel(Integer.parseInt(levelTextField.getText()));
                    userToAdd.setUserTelephoneNumber(mobileNumberTextField.getText());


                    if (userToEdit.getUserPermissionsLevel() == 0) {
                        DataBaseHandlingClass.AddNewPatientToDB(connection, admin, userToAdd, orthodontistSelected);
                    } else if (userToEdit.getUserPermissionsLevel() == 1) {
                        try {
                            DataBaseHandlingClass.AddNewOrthodontistToDB(connection, admin, userToAdd);
                        } catch (Exception exception) {
                            System.out.println("Exception caught!");
                        }
                    } else if (userToEdit.getUserPermissionsLevel() == 2) {
                        try {
                            if (userToAdd.getUserLogin().equals("admin"))
                                throw new Exception();
                            DataBaseHandlingClass.AddNewAdministratorToDB(connection, admin, userToAdd);
                        } catch (Exception exception) {
                            System.out.println("Exception caught!");
                        }
                    }
                    prompt.setText("The user: " + userToAdd.getUserLogin() + ", has been successfully added to the database");
                } else
                    prompt.setText("There was an error adding the user, please check the entered data and try again...");

            }
            catch (Exception exception){
                prompt.setText("A problem has arisen while adding a new user to database");
                System.out.println("A problem has arisen while adding a new user to database " + exception.getMessage());
            }
            finally{
                try {
                    assert connection != null;
                    connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        frame.add(editUserPanel, BorderLayout.CENTER);
        addExitButtonDeveloper();
        frame.setLayout(new BoxLayout(frame, BoxLayout.Y_AXIS));
        frame.setVisible(true);
    }

    /**
     * The method which is responsible for adding user from the database
     */
    public void deleteUser(){
        enteredUsername = "null";
        Connection connection = DataBaseHandlingClass.StartConnectionWithDB();
        List<User> list = DataBaseHandlingClass.SearchForAllUsers(connection, loggedUser);

        Panel welcomePanel = new Panel();
        Panel findPanelMessage = new Panel();
        Panel findUserPanel = new Panel(new GridBagLayout());

        Label usernameLabel = new Label("List of users available in the database: ");
        usernameLabel.setMaximumSize(new Dimension(frame.getWidth(), frame.getHeight()/12));
        Label userStatement = new Label("Please select the username from the list, to see user's credentials", Label.CENTER);
        userStatement.setMaximumSize(new Dimension(frame.getWidth(), frame.getHeight()/12));

        //TextField userField = new TextField(20);
        Choice userToSelect = new Choice();
        userToSelect.setMaximumSize(new Dimension(frame.getWidth(), frame.getHeight()/12));
        if (list != null)
            for (User userToAdd : list) {
                if (!userToAdd.getUserLogin().equals(loggedUser.getUserLogin()))
                    userToSelect.add(userToAdd.getUserLogin());
            }

        Button downloadInfoButton = new Button("Confirm your selection");
        downloadInfoButton.setSize(new Dimension(frame.getWidth(), frame.getHeight()/7));

        downloadInfoButton.addActionListener(e -> {
            boolean userFound = false;
            if (list != null)
                for (User userToFind : list) {
                    if (userToFind.getUserLogin().equals(userToSelect.getSelectedItem())) {
                        userToEdit = userToFind;
                        userFound = true;
                    }
                }

            if(userFound){
                userStatement.setText("You have selected: " + userToEdit.getUserName() + " " + userToEdit.getUserSurname() + ", with username: " + userToEdit.getUserName());
            }
            else {
                userStatement.setText("ERROR, Please select a user from the list first!");
            }
        });

        findUserPanel.add(usernameLabel);
        findUserPanel.add(userToSelect);
        Label gapLabel = new Label();
        gapLabel.setPreferredSize(new Dimension(frame.getWidth(), frame.getHeight()/8));
        findUserPanel.add(gapLabel);
        findUserPanel.setLayout(new BoxLayout(findUserPanel, BoxLayout.Y_AXIS));
        findUserPanel.add(downloadInfoButton);

        findPanelMessage.setLayout(new BoxLayout(findPanelMessage, BoxLayout.Y_AXIS));
        findPanelMessage.add(userStatement);

        Panel deletePanel = new Panel();
        Label deleteInfo = new Label("Are you sure you want to permanently delete this user? If yes press the button below", Label.CENTER);
        deleteInfo.setMaximumSize(new Dimension(frame.getWidth(), frame.getHeight()/12));
        Button deleteButton = new Button("Delete user");
        deleteButton.setSize(new Dimension(frame.getWidth(), frame.getHeight()/7));
        deleteButton.addActionListener(e -> {
            try {
                if (userToEdit.getUserLogin().equals("admin") || userToEdit.getUserLogin().equals("adminOrthodontist"))
                    throw new Exception("Attempting to add a user who is already in the database");

                if (userToEdit.getUserPermissionsLevel() == 0) {
                    DataBaseHandlingClass.RemovePatientFromDB(connection, loggedUser, userToEdit);
                    deleteInfo.setText("Deletion of patient: " + userToEdit.getUserLogin() + ", was successful");
                    userToEdit = null;
                } else if (userToEdit.getUserPermissionsLevel() == 1) {
                    User adminOrthodontist = DataBaseHandlingClass.LogInUser(connection, "adminOrthodontist", "admin");
                    deleteInfo.setText("Deletion of orthodontist: " + userToEdit.getUserLogin() + ", was successful");
                    DataBaseHandlingClass.RemoveOrthodontistFromDB(connection, loggedUser, userToEdit, adminOrthodontist);
                    userToEdit = null;
                } else if (userToEdit.getUserPermissionsLevel() == 2) {
                    try {
                        if (userToEdit.getUserLogin().equals(loggedUser.getUserLogin()))
                            throw new Exception();
                        DataBaseHandlingClass.RemoveAdministratorFromDB(connection, loggedUser, userToEdit);
                        deleteInfo.setText("Deletion of developer: " + userToEdit.getUserLogin() + ", was successful");
                        userToEdit = null;
                    } catch (Exception exception) {
                        System.out.println("Exception caught! a problem has arisen while deleting user from the database");
                    }
                }
            }
            catch(Exception exception){
                System.out.println("Exception caught! " + exception.getMessage());
            }
            finally{
                try {
                    assert connection != null;
                    connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }

        });
        deletePanel.add(deleteInfo);
        deletePanel.add(deleteButton);
        deletePanel.setLayout(new BoxLayout(deletePanel, BoxLayout.Y_AXIS));


        frame.removeAll();
        frame.add(findUserPanel, BorderLayout.NORTH);
        frame.add(findPanelMessage, BorderLayout.CENTER);
        frame.add(welcomePanel, BorderLayout.SOUTH);
        frame.add(deletePanel);
        addExitButtonDeveloper();
        frame.setLayout(new BoxLayout(frame, BoxLayout.Y_AXIS));
        frame.setVisible(true);
    }

    /**
     * The method which is responsible for editing the user's credentials
     */
    public void editUsers() {
        frame.removeAll();
        userToEdit = null;
        enteredUsername = "null";

        Connection connection = DataBaseHandlingClass.StartConnectionWithDB();
        User user = DataBaseHandlingClass.LogInUser(connection, "admin", "admin");
        assert user != null;
        List<User> list = DataBaseHandlingClass.SearchForAllUsers(connection, user);

        Panel welcomePanel = new Panel();
        Panel findPanelMessage = new Panel();
        Panel findUserPanel = new Panel(new GridBagLayout());
        Label usernameLabel = new Label("List of users available in the database: ");

        Choice userToSelect = new Choice();
        if (list != null)
            for (User userToAdd : list) {
                if (!userToAdd.getUserLogin().equals(loggedUser.getUserLogin()))
                    userToSelect.add(userToAdd.getUserLogin());
            }
        Button loginButton = new Button("Download data");
        Panel editUserPanel = new Panel();
        Label info = new Label("Please first select the user from the database, then click \"download data\" button to see the credentials of selected user");
        editUserPanel.add(info);
        Label username = new Label("New username: ");
        TextField usernameTextField = new TextField("null");
        Label password = new Label("New password: ");
        TextField passwordTextField = new TextField("null");
        Label name = new Label("New user's name: ");
        TextField nameTextField = new TextField("null");
        Label surname = new Label("New user's surname: ");
        TextField surnameTextField = new TextField("null");
        Label mobileNumber = new Label("New user's mobile number: ");
        TextField mobileNumberTextField = new TextField("null");
        Label mail = new Label("New user's mail: ");
        TextField mailTextField = new TextField("null");
        Label address = new Label("New user's address: ");
        TextField addressTextField = new TextField("null");

        ActionListener action = e -> {
            enteredUsername = userToSelect.getSelectedItem();
            try {
                assert list != null;
                for(User userToFind: list){
                    if(userToFind.getUserLogin().equals(enteredUsername)){
                        userToEdit = userToFind;
                        usernameTextField.setText(userToEdit.getUserLogin());
                        passwordTextField.setText(userToEdit.getUserPassword());
                        nameTextField.setText(userToEdit.getUserName());
                        surnameTextField.setText(userToEdit.getUserSurname());
                        mobileNumberTextField.setText(userToEdit.getUserTelephoneNumber());
                        mailTextField.setText(userToEdit.getUserEmail());
                        addressTextField.setText(userToEdit.getUserAddress());
                    }
                }
            }
            catch(Exception exception){
                System.out.println("Exception caught!" + exception.getMessage());
            }
        };


        loginButton.addActionListener(action);
        findUserPanel.add(usernameLabel);
        findUserPanel.add(userToSelect);
        findUserPanel.setLayout(new BoxLayout(findUserPanel, BoxLayout.Y_AXIS));
        findPanelMessage.setLayout(new BoxLayout(findPanelMessage, BoxLayout.Y_AXIS));
        editUserPanel.add(username);
        editUserPanel.add(usernameTextField);
        editUserPanel.add(password);
        editUserPanel.add(passwordTextField);
        editUserPanel.add(name);
        editUserPanel.add(nameTextField);
        editUserPanel.add(surname);
        editUserPanel.add(surnameTextField);
        editUserPanel.add(mobileNumber);
        editUserPanel.add(mobileNumberTextField);
        editUserPanel.add(mail);
        editUserPanel.add(mailTextField);
        editUserPanel.add(address);
        editUserPanel.add(addressTextField);
        editUserPanel.setLayout(new BoxLayout(editUserPanel, BoxLayout.Y_AXIS));
        Button editUserButton = new Button("Edit user");
        editUserPanel.add(loginButton);
        Label prompt = new Label("", Label.CENTER);
        editUserPanel.add(editUserButton);
        editUserPanel.add(prompt);

        editUserButton.addActionListener(e -> {
            try {
                if (userToEdit != null) {
                    User userToAdd = new User();
                    userToAdd.setUserName(nameTextField.getText());
                    userToAdd.setUserSurname(surnameTextField.getText());
                    userToAdd.setUserLogin(usernameTextField.getText());
                    userToAdd.setUserPassword(passwordTextField.getText());
                    userToAdd.setUserEmail(mailTextField.getText());
                    userToAdd.setUserTelephoneNumber(mobileNumberTextField.getText());
                    userToAdd.setUserId(userToEdit.getUserId());
                    userToAdd.setUserAddress(addressTextField.getText());
                    DataBaseHandlingClass.EditUserInfoInDB(connection, userToEdit, userToAdd);
                    prompt.setText("The edition was a success");
                } else
                    prompt.setText("There is no such user in the database (Editing is forbidden)");
            }
            catch(Exception exception){
                System.out.println("Problem has arisen during editing profile");
            }
            finally {
                try {
                    assert connection != null;
                    connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        frame.add(findUserPanel, BorderLayout.NORTH);
        frame.add(findPanelMessage, BorderLayout.CENTER);
        frame.add(welcomePanel, BorderLayout.SOUTH);
        frame.add(editUserPanel, BorderLayout.SOUTH);
        addExitButtonDeveloper();
        frame.setLayout(new BoxLayout(frame, BoxLayout.Y_AXIS));
        frame.setVisible(true);
    }

    /**
     * The method which is responsible for patient's mailbox
     */
    public void myMailboxPatient(){
        if(loggedUser.getUserPermissionsLevel()==0) {
            Dimension maxDimension = new Dimension(frame.getWidth()/20,frame.getHeight()/20);
            Dimension maxDimensionWidth = new Dimension(frame.getWidth(), frame.getHeight()/15);
            Label infoList = new Label("Please select a user from the list in order to send an e-mail: ", Label.CENTER);
            infoList.setMaximumSize(maxDimensionWidth);
            Choice selectionList = new Choice();
            selectionList.setMaximumSize(maxDimensionWidth);
            Connection connection = DataBaseHandlingClass.StartConnectionWithDB();
            User orthodontistOfPatient = DataBaseHandlingClass.SearchForOrthodontistOfPatient(connection, loggedUser);
            if (orthodontistOfPatient != null) {
                selectionList.add(orthodontistOfPatient.getUserName() + " " + orthodontistOfPatient.getUserSurname() + ", +48 " + orthodontistOfPatient.getUserTelephoneNumber());
                selectionList.add("Dev (Please contact us only if you have any problems with the application)");
            }
            else{
                selectionList.add("Dev (Please contact us only if you have any problems with the application)");
            }

            frame.add(infoList);
            frame.add(selectionList);

            TextField textFieldTopic = new TextField();
            Label textFieldTopicLabel = new Label("e-mail topic:");
            textFieldTopic.setMaximumSize(maxDimensionWidth);
            textFieldTopicLabel.setMaximumSize(maxDimensionWidth);
            frame.add(textFieldTopicLabel);
            frame.add(textFieldTopic);

            TextField textField = new TextField();
            Dimension textFieldDimension = new Dimension(frame.getWidth(), frame.getHeight());
            Label textFieldLabel = new Label("e-mail subject:");
            textFieldLabel.setMaximumSize(maxDimensionWidth);
            textField.setPreferredSize(textFieldDimension);
            frame.add(textFieldLabel);
            frame.add(textField);

            Label sendInfo = new Label("Please check before submitting if a valid user is selected, and the subject and text fields are not empty", Label.CENTER);
            Button sendButton = new Button("Send");
            sendInfo.setMaximumSize(maxDimensionWidth);
            sendButton.setMaximumSize(maxDimension);
            frame.add(sendInfo);
            frame.add(sendButton);

            sendButton.addActionListener(e -> {

                if(textField.getText().isEmpty() || textFieldTopic.getText().isEmpty())
                {
                    sendInfo.setText("The subject and text fields are empty, please fill them in and try again");
                }
                else {
                    // TODO email sending algorithm
                    sendInfo.setText("The message has been sent successfully!");
                }
            });

        }
        else
        {
            System.err.println("There were problems logging in the correct user");
        }
        addExitButtonPatient();
        frame.setLayout(new BoxLayout(frame, BoxLayout.Y_AXIS));
        frame.setVisible(true);
    }

    /**
     * The method which is responsible for orthodontist's mailbox
     */
    public void myMailboxOrthodontist(){
        if(loggedUser.getUserPermissionsLevel()==1) {
            Dimension maxDimension = new Dimension(frame.getWidth()/20,frame.getHeight()/20);
            Dimension maxDimensionWidth = new Dimension(frame.getWidth(), frame.getHeight()/15);
            Label infoList = new Label("Please select a user from the list in order to send an e-mail: ", Label.CENTER);
            infoList.setMaximumSize(maxDimensionWidth);
            Choice selectionList = new Choice();
            selectionList.setMaximumSize(maxDimensionWidth);
            Connection connection = DataBaseHandlingClass.StartConnectionWithDB();
            List<User> listOfPatients = DataBaseHandlingClass.SearchForPatientsOfOrthodontist(connection, loggedUser);
            if (listOfPatients != null) {
                for (User patient : listOfPatients)
                    selectionList.add(patient.getUserName() + " " + patient.getUserSurname() + ", +48 " + patient.getUserTelephoneNumber());
                selectionList.add("Dev (Please contact us only if you have any problems with the application)");
            }
            else{
                selectionList.add("Dev (Please contact us only if you have any problems with the application)");
            }

            frame.add(infoList);
            frame.add(selectionList);

            TextField textFieldTopic = new TextField();
            Label textFieldTopicLabel = new Label("e-mail topic:");
            textFieldTopic.setMaximumSize(maxDimensionWidth);
            textFieldTopicLabel.setMaximumSize(maxDimensionWidth);
            frame.add(textFieldTopicLabel);
            frame.add(textFieldTopic);

            TextField textField = new TextField();
            Dimension textFieldDimension = new Dimension(frame.getWidth(), frame.getHeight());
            Label textFieldLabel = new Label("e-mail subject:");
            textFieldLabel.setMaximumSize(maxDimensionWidth);
            textField.setPreferredSize(textFieldDimension);
            frame.add(textFieldLabel);
            frame.add(textField);

            Label sendInfo = new Label("Please check before submitting if a valid user is selected, and the subject and text fields are not empty", Label.CENTER);
            Button sendButton = new Button("Send");
            sendInfo.setMaximumSize(maxDimensionWidth);
            sendButton.setMaximumSize(maxDimension);
            frame.add(sendInfo);
            frame.add(sendButton);

            sendButton.addActionListener(e -> {

                if(textField.getText().isEmpty() || textFieldTopic.getText().isEmpty())
                {
                    sendInfo.setText("The subject and text fields are empty, please fill them in and try again");
                }
                else {
                    // TODO email sending algorithm
                    sendInfo.setText("The message has been sent successfully!");
                }
            });

        }
        else
        {
            System.err.println("There were problems logging in the correct user");
        }
        addExitButtonOrthodontist();
        frame.setLayout(new BoxLayout(frame, BoxLayout.Y_AXIS));
        frame.setVisible(true);
    }

    /**
     * The method which is responsible for viewing and editing developer's credentials
     */
    public void myProfileDeveloper(){
        frame.removeAll();

        Panel editUserPanel = new Panel();
        Label info = new Label("If u wish to edit your credentials, please change them and enter the button below");
        editUserPanel.add(info);
        Label address = new Label("Your address: ");
        TextField addressTextField = new TextField(loggedUser.getUserAddress());
        Label password = new Label("Your password: ");
        TextField passwordTextField = new TextField(loggedUser.getUserPassword());
        Label name = new Label("Your name: ");
        TextField nameTextField = new TextField(loggedUser.getUserName());
        Label surname = new Label("Your surname: ");
        TextField surnameTextField = new TextField(loggedUser.getUserSurname());
        Label mobileNumber = new Label("Your mobile number: ");
        TextField mobileNumberTextField = new TextField(loggedUser.getUserTelephoneNumber());
        Label mail = new Label("Your mail: ");
        TextField mailTextField = new TextField(loggedUser.getUserEmail());

        editUserPanel.add(password);
        editUserPanel.add(passwordTextField);
        editUserPanel.add(address);
        editUserPanel.add(addressTextField);
        editUserPanel.add(name);
        editUserPanel.add(nameTextField);
        editUserPanel.add(surname);
        editUserPanel.add(surnameTextField);
        editUserPanel.add(mobileNumber);
        editUserPanel.add(mobileNumberTextField);
        editUserPanel.add(mail);
        editUserPanel.add(mailTextField);

        editUserPanel.setLayout(new BoxLayout(editUserPanel, BoxLayout.Y_AXIS));

        Button editUserButton = new Button("Edit my data");
        Label prompt = new Label("Please before pressing the button, check the correctness of the data");
        editUserPanel.add(prompt);
        editUserPanel.add(editUserButton);

        editUserButton.addActionListener(e -> {
            Connection connection = DataBaseHandlingClass.StartConnectionWithDB();
            try {
                loggedUser = DataBaseHandlingClass.LogInUser(connection, loggedUser.getUserLogin(), loggedUser.getUserPassword());
                if (loggedUser != null) {
                    User userToAdd = new User(loggedUser);
                    userToAdd.setUserName(nameTextField.getText());
                    userToAdd.setUserPassword(passwordTextField.getText());
                    userToAdd.setUserSurname(surnameTextField.getText());
                    userToAdd.setUserAddress(addressTextField.getText());
                    userToAdd.setUserEmail(mailTextField.getText());
                    userToAdd.setUserTelephoneNumber(mobileNumberTextField.getText());
                    userToAdd.setUserId(loggedUser.getUserId());
                    DataBaseHandlingClass.EditUserInfoInDB(connection, loggedUser, userToAdd);
                    loggedUser = userToAdd;
                    prompt.setText("The edition was a success");
                } else
                    prompt.setText("There is no such user in the database (Editing is forbidden)");
            }
            catch(Exception exception){
                System.out.println("Problem has arisen during editing profile");
            }
            finally {
                try {
                    assert connection != null;
                    connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        frame.add(editUserPanel, BorderLayout.CENTER);
        addExitButtonDeveloper();
        frame.setLayout(new BoxLayout(frame, BoxLayout.Y_AXIS));
        frame.setVisible(true);
    }

    /**
     * The method which is responsible for viewing and editing patient's credentials
     */
    public void myProfilePatient(){
        frame.removeAll();

        Panel editUserPanel = new Panel();
        Label info = new Label("If u wish to edit your credentials, please change them and enter the button below");
        editUserPanel.add(info);
        Label address = new Label("Your address: ");
        TextField addressTextField = new TextField(loggedUser.getUserAddress());
        Label password = new Label("Your password: ");
        TextField passwordTextField = new TextField(loggedUser.getUserPassword());
        Label name = new Label("Your name: ");
        TextField nameTextField = new TextField(loggedUser.getUserName());
        Label surname = new Label("Your surname: ");
        TextField surnameTextField = new TextField(loggedUser.getUserSurname());
        Label mobileNumber = new Label("Your mobile number: ");
        TextField mobileNumberTextField = new TextField(loggedUser.getUserTelephoneNumber());
        Label mail = new Label("Your mail: ");
        TextField mailTextField = new TextField(loggedUser.getUserEmail());

        editUserPanel.add(password);
        editUserPanel.add(passwordTextField);
        editUserPanel.add(address);
        editUserPanel.add(addressTextField);
        editUserPanel.add(name);
        editUserPanel.add(nameTextField);
        editUserPanel.add(surname);
        editUserPanel.add(surnameTextField);
        editUserPanel.add(mobileNumber);
        editUserPanel.add(mobileNumberTextField);
        editUserPanel.add(mail);
        editUserPanel.add(mailTextField);

        editUserPanel.setLayout(new BoxLayout(editUserPanel, BoxLayout.Y_AXIS));

        Button editUserButton = new Button("Edit my data");
        Label prompt = new Label("Please before pressing the button, check the correctness of the data");
        editUserPanel.add(editUserButton);
        editUserPanel.add(prompt);

        editUserButton.addActionListener(e -> {
            Connection connection = DataBaseHandlingClass.StartConnectionWithDB();
            try {
                loggedUser = DataBaseHandlingClass.LogInUser(connection, loggedUser.getUserLogin(), loggedUser.getUserPassword());
                if (loggedUser != null) {
                    User userToAdd = new User(loggedUser);
                    userToAdd.setUserName(nameTextField.getText());
                    userToAdd.setUserPassword(passwordTextField.getText());
                    userToAdd.setUserSurname(surnameTextField.getText());
                    userToAdd.setUserAddress(addressTextField.getText());
                    userToAdd.setUserEmail(mailTextField.getText());
                    userToAdd.setUserTelephoneNumber(mobileNumberTextField.getText());
                    userToAdd.setUserId(loggedUser.getUserId());
                    DataBaseHandlingClass.EditUserInfoInDB(connection, loggedUser, userToAdd);
                    loggedUser = userToAdd;
                    prompt.setText("The edition was a success");
                } else
                    prompt.setText("There is no such user in the database (Editing is forbidden)");
            }
            catch(Exception exception){
                System.out.println("Problem has arisen during editing profile");
            }
            finally {
                try {
                    assert connection != null;
                    connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        frame.add(editUserPanel, BorderLayout.CENTER);
        addExitButtonPatient();
        frame.setLayout(new BoxLayout(frame, BoxLayout.Y_AXIS));
        frame.setVisible(true);
    }

    /**
     * The method which is responsible for viewing and editing orthodontist's credentials
     */
    public void myProfileOrthodontist(){
        frame.removeAll();
        Connection connection = DataBaseHandlingClass.StartConnectionWithDB();
        Panel editUserPanel = new Panel();
        Label info = new Label("If u wish to edit your credentials, please change them and enter the button below");
        editUserPanel.add(info);
        Label address = new Label("Your address: ");
        TextField addressTextField = new TextField(loggedUser.getUserAddress());
        Label password = new Label("Your password: ");
        TextField passwordTextField = new TextField(loggedUser.getUserPassword());
        Label name = new Label("Your name: ");
        TextField nameTextField = new TextField(loggedUser.getUserName());
        Label surname = new Label("Your surname: ");
        TextField surnameTextField = new TextField(loggedUser.getUserSurname());
        Label mobileNumber = new Label("Your mobile number: ");
        TextField mobileNumberTextField = new TextField(loggedUser.getUserTelephoneNumber());
        Label mail = new Label("Your mail: ");
        TextField mailTextField = new TextField(loggedUser.getUserEmail());

        editUserPanel.add(password);
        editUserPanel.add(passwordTextField);
        editUserPanel.add(address);
        editUserPanel.add(addressTextField);
        editUserPanel.add(name);
        editUserPanel.add(nameTextField);
        editUserPanel.add(surname);
        editUserPanel.add(surnameTextField);
        editUserPanel.add(mobileNumber);
        editUserPanel.add(mobileNumberTextField);
        editUserPanel.add(mail);
        editUserPanel.add(mailTextField);

        editUserPanel.setLayout(new BoxLayout(editUserPanel, BoxLayout.Y_AXIS));

        Button editUserButton = new Button("Edit my data");
        Label prompt = new Label("Please before pressing the button, check the correctness of the data");
        editUserPanel.add(editUserButton);
        editUserPanel.add(prompt);

        editUserButton.addActionListener(e -> {
            try {
                loggedUser = DataBaseHandlingClass.LogInUser(connection, loggedUser.getUserLogin(), loggedUser.getUserPassword());
                if (loggedUser != null) {
                    User userToAdd = new User(loggedUser);
                    userToAdd.setUserName(nameTextField.getText());
                    userToAdd.setUserPassword(passwordTextField.getText());
                    userToAdd.setUserSurname(surnameTextField.getText());
                    userToAdd.setUserAddress(addressTextField.getText());
                    userToAdd.setUserEmail(mailTextField.getText());
                    userToAdd.setUserTelephoneNumber(mobileNumberTextField.getText());
                    userToAdd.setUserId(loggedUser.getUserId());
                    DataBaseHandlingClass.EditUserInfoInDB(connection, loggedUser, userToAdd);
                    loggedUser = userToAdd;
                    prompt.setText("The edition was a success");
                } else
                    prompt.setText("There is no such user in the database (Editing is forbidden)");
            }
            catch(Exception exception){
                System.out.println("Problem has arisen during editing profile");
            }
            finally {
                try {
                    assert connection != null;
                    connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        frame.add(editUserPanel, BorderLayout.CENTER);
        addExitButtonOrthodontist();
        frame.setLayout(new BoxLayout(frame, BoxLayout.Y_AXIS));
        frame.setVisible(true);
    }

    /**
     * The method which is responsible for viewing patient's visits
     */
    public void myVisitsPatient(){
        if(loggedUser.getUserPermissionsLevel()==0) {
            Dimension maxDimension = new Dimension(frame.getWidth()/5,frame.getHeight()/20);
            Dimension maxDimensionWidth = new Dimension(frame.getWidth(), frame.getHeight()/15);
            Choice visitSelection = new Choice();
            visitSelection.setMaximumSize(maxDimensionWidth);
            Connection connection = DataBaseHandlingClass.StartConnectionWithDB();
            List<Visit> listOfVisits = DataBaseHandlingClass.SearchForVisitsOfPatient(connection, loggedUser);
            if(listOfVisits != null) {
                for (Visit visit : listOfVisits)
                    visitSelection.add("Date of the visit:  " + visit.getVisitDate());
            }
            frame.add(visitSelection);
            Panel visitInfoPanel = new Panel();
            visitInfoPanel.setPreferredSize(new Dimension(frame.getWidth(),frame.getHeight()));
            frame.add(visitInfoPanel);
            Button buttonShowDetails = new Button("Show details of the visit");
            buttonShowDetails.setMaximumSize(maxDimension);
            Button generatePDF = new Button("Generate PDF file of the visit");
            generatePDF.setMaximumSize(maxDimension);
            frame.add(buttonShowDetails);
            frame.add(generatePDF);

            generatePDF.addActionListener(e -> { // TODO

            });

            buttonShowDetails.addActionListener(e -> {

            });

        }
        else {
            System.err.println("There were problems logging in the correct user");
        }

        addExitButtonPatient();
        frame.setLayout(new BoxLayout(frame, BoxLayout.Y_AXIS));
        frame.setVisible(true);
    }

    /**
     * The method is responsible for viewing and editing orthodontist's visits
     */
    public void myVisitsOrthodontist(){
        Dimension maxDimensionWidth = new Dimension(frame.getWidth(), frame.getHeight()/5);
        Button createVisit = new Button("Create a new visit");
        Button cancelVisit = new Button("Cancel a visit");
        Button editVisit = new Button("Edit your visits");
        createVisit.setMinimumSize(maxDimensionWidth);
        cancelVisit.setMinimumSize(maxDimensionWidth);
        editVisit.setMinimumSize(maxDimensionWidth);
        Panel panelVisits = new Panel();
        Panel panelVisits2 = new Panel();
        panelVisits2.setPreferredSize(new Dimension(frame.getWidth(), frame.getHeight()));
        panelVisits.setPreferredSize(new Dimension(frame.getWidth(), frame.getHeight()));
        frame.add(panelVisits);
        frame.add(createVisit);
        frame.add(cancelVisit);
        frame.add(editVisit);
        frame.add(panelVisits2);

        createVisit.addActionListener(e -> { // TODO

        });

        cancelVisit.addActionListener(e -> {

        });

        editVisit.addActionListener(e -> {

        });

        addExitButtonOrthodontist();
        frame.setLayout(new BoxLayout(frame, BoxLayout.Y_AXIS));
        frame.setVisible(true);
    }

    /**
     * The method which is responsible for login screen
     */
    public void loginScreen() {
        enteredUsername = "null";
        enteredPassword = "null";
        Frame loginFrame = new Frame("Login Screen");

        Panel welcomePanel = new Panel();
        Panel loginPanelMessage = new Panel();
        Panel loginPanel = new Panel(new GridBagLayout());

        Label labelUsername = new Label("Username: ");
        Label labelPassword = new Label("Password: ");
        Label loginStatement = new Label("Please enter your credentials", Label.CENTER);
        Label welcomeLabel = new Label("Patient Service System - Orthodontic office");

        TextField usernameField = new TextField(20);
        TextField passwordField = new TextField(20);

        Button loginButton = new Button("Login");

        ActionListener action = e -> {
            Connection connection = DataBaseHandlingClass.StartConnectionWithDB();
            try {
                enteredUsername = usernameField.getText();
                enteredPassword = passwordField.getText();
                numberOfAttempts++;
                User user = DataBaseHandlingClass.LogInUser(connection, enteredUsername, enteredPassword);
                assert user != null;
                User admin = DataBaseHandlingClass.LogInUser(connection,"admin", "admin");
                assert admin != null;
                List<User> list = DataBaseHandlingClass.SearchForAllUsers(connection, admin);

                assert list != null;
                for(User userToFind: list) {
                    if (userToFind.getUserLogin().equals(enteredUsername)){
                        loggedUser.setUserId(userToFind.getUserId());
                        loggedUser.setUserPassword(userToFind.getUserPassword());
                        loggedUser.setUserLogin(userToFind.getUserLogin());
                        loggedUser.setUserEmail(userToFind.getUserEmail());
                        loggedUser.setUserName(userToFind.getUserName());
                        loggedUser.setUserSurname(userToFind.getUserSurname());
                        loggedUser.setUserTelephoneNumber(userToFind.getUserTelephoneNumber());
                        loggedUser.setUserPermissionsLevel(userToFind.getUserPermissionsLevel());
                        loggedUser.setUserAddress(userToFind.getUserAddress());
                    }
                }

                if (user.getUserPermissionsLevel()==0){
                    numberOfAttempts = 0;
                    loginFrame.setVisible(false);
                    loginFrame.dispose();
                    runGUIPatient();
                }
                else if(user.getUserPermissionsLevel()==1){
                    numberOfAttempts = 0;
                    loginFrame.setVisible(false);
                    loginFrame.dispose();
                    runGUIOrthodontist();
                }
                else if(user.getUserPermissionsLevel()==2){
                    numberOfAttempts = 0;
                    loginFrame.setVisible(false);
                    loginFrame.dispose();
                    runGUIDeveloper();
                }
            }
            catch(Exception exception){
                loginStatement.setText("Your input is wrong (Attempt: " + numberOfAttempts + ")");
            }
            finally {
                try {
                    assert connection != null;
                    connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }

        };

        welcomePanel.add(welcomeLabel);

        passwordField.addActionListener(action);
        usernameField.addActionListener(action);
        loginButton.addActionListener(action);

        loginPanel.add(labelUsername);
        loginPanel.add(usernameField);
        loginPanel.add(labelPassword);
        loginPanel.add(passwordField);
        loginPanel.setLayout(new BoxLayout(loginPanel, BoxLayout.Y_AXIS));
        loginPanel.add(loginButton);

        loginPanelMessage.add(loginStatement, BorderLayout.NORTH);
        loginPanelMessage.setVisible(true);

        loginFrame.setIconImage(icon);
        loginFrame.add(loginPanel, BorderLayout.CENTER);
        loginFrame.add(loginPanelMessage, BorderLayout.SOUTH);
        loginFrame.add(welcomePanel,BorderLayout.NORTH);
        loginFrame.setSize((int)(screenWidth/1.7),(screenHeight/3));
        loginFrame.setLocationRelativeTo(null);
        loginFrame.setVisible(true);
        loginFrame.setResizable(false);
        loginFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                loginFrame.setVisible(false);
                loginFrame.dispose();
                if (user == null)
                    System.exit(0);
            }

        });
    }

    /**
     * The method which is responsible for running main frame of GUI for Orthodontist
     */
    public void runGUIOrthodontist() {
        frame.removeAll();
        createButtonsOrthodontist();
        addPanels();
        frame.setIconImage(icon);
        switch (SCREEN_RES) {
            case 0:
                frame.setSize(screenWidth,screenHeight);
                break;
            case 1:
                frame.setSize((int)(screenWidth*1.3),(int)(screenHeight*1.3));
                break;
            case 2:
                frame.setSize(screenDimension.width, screenDimension.height);
                break;
        }
        frame.setMenuBar(menu);
        frame.setResizable(false);
        centralPanel.removeAll();
        centralPanel.setLayout(new BoxLayout(centralPanel, BoxLayout.Y_AXIS));
        for(int i=0;i<5;i++)
            centralPanel.add(buttons[i]);
        frame.setLocationRelativeTo(null);
    }

    /**
     * The method which is responsible for running main frame of GUI for Patient
     */
    public void runGUIPatient() {
        frame.removeAll();
        createButtonsPatient();
        addPanels();
        frame.setIconImage(icon);
        switch (SCREEN_RES) {
            case 0:
                frame.setSize(screenWidth,screenHeight);
                break;
            case 1:
                frame.setSize((int)(screenWidth*1.3),(int)(screenHeight*1.3));
                break;
            case 2:
                frame.setSize(screenDimension.width, screenDimension.height);
                break;
        }
        frame.setMenuBar(menu);
        frame.setResizable(false);
        centralPanel.removeAll();
        centralPanel.setLayout(new BoxLayout(centralPanel, BoxLayout.Y_AXIS));
        for(int i=0;i<5;i++)
            centralPanel.add(buttons[i]);
        frame.setLocationRelativeTo(null);
    }

    /**
     * The method which is responsible for running main frame of GUI for Developer
     */
    public void runGUIDeveloper() {
        frame.removeAll();
        createButtonsDeveloper();
        addPanels();
        frame.setIconImage(icon);
        switch (SCREEN_RES) {
            case 0:
                frame.setSize(screenWidth,screenHeight); // default size
                break;
            case 1:
                frame.setSize((int)(screenWidth*1.3),(int)(screenHeight*1.3));
                break;
            case 2:
                frame.setSize(screenDimension.width, screenDimension.height);
                break;
        }
        frame.setMenuBar(menu);
        frame.setResizable(false);
        centralPanel.removeAll();
        centralPanel.setLayout(new BoxLayout(centralPanel, BoxLayout.Y_AXIS));
        for(int i=0;i<7;i++)
            centralPanel.add(buttons[i]);
        frame.setLocationRelativeTo(null);
    }
}

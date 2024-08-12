

package newassigment1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;
import java.util.ArrayList;

class SDG13Goal01 {
    private String target;
    private String description;

    public SDG13Goal01(String target, String description) {
        this.target = target;
        this.description = description;
    }

    public String getTarget() {
        return target;
    }

    public String getDescription() {
        return description;
    }
}

class User02 {
    private String username;
    private String password;
    private boolean isAdmin;

    public User02(String username, String password, boolean isAdmin) {
        this.username = username;
        this.password = password;
        this.isAdmin = isAdmin;
    }

    public String getUsername() {
        return username;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public boolean validatePassword(String password) {
        return this.password.equals(password);
    }
}

public class SDG13App {
    private ArrayList<SDG13Goal01> goals;
    private ArrayList<User02> users;
    private User02 currentUser;

    private JFrame frame;
    private CardLayout cardLayout;
    private JPanel mainPanel;

    private DefaultListModel<String> queryModel;
    private DefaultListModel<String> tempQueryModel;

    private static final String[] BUTTON_TEXTS = {
            "Login", "Register", "New Username:", "New Password:", "Are you an admin?",
            "Quick Search", "Policy", "The News", "Global Warming", "Education Resource", "Quiz", "Logout", "Manage Queries",
            "Search", "Cancel", "Add", "Update", "Delete", "Back"
    };

    public SDG13App() {
        goals = new ArrayList<>();
        users = new ArrayList<>();
        queryModel = new DefaultListModel<>();
        tempQueryModel = new DefaultListModel<>();

        goals.add(new SDG13Goal01("Strengthen resilience to climate hazards",
                "Improve resilience and adaptive capacity to climate-related disasters."));
        goals.add(new SDG13Goal01("Integrate climate measures into policies",
                "Integrate measures into national policies, strategies, and planning."));
        goals.add(new SDG13Goal01("Improve education on climate change",
                "Enhance education and awareness on climate change mitigation and adaptation."));
        goals.add(new SDG13Goal01("Mobilize $100 billion annually",
                "Mobilize funds to address the needs of developing countries for climate actions."));
        goals.add(new SDG13Goal01("Promote effective climate change planning",
                "Raise capacity for effective planning and management in least developed countries."));

        users.add(new User02("admin", "admin", true));

        String[] initialQueries = {
                "When was SDG 13.3 established?",
                "What is included in SDG 13.3?",
                "What is the impact of policies established after SDG 13.3?",
                "Why did the UN establish SDG 13.3 as part of the sustainable development goals?",
                "What was the public reaction to the establishment of SDG 13.3?",
                "What are the benefits of establishing SDG 13.3?",
                "What are the potential risks of establishing SDG 13.3?",
                "What obstacles have been encountered during the development of SDG 13.3?"
        };
        for (String query : initialQueries) {
            queryModel.addElement(query);
            tempQueryModel.addElement(query);
        }

        frame = new JFrame("SDG 13 Climate Action Application");
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        setupLoginPanel();
        setupRegisterPanel();

        frame.add(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setVisible(true);
    }

    private void setupLoginPanel() {
        BackgroundPanel01 loginPanel = new BackgroundPanel01("D:/STUDIES/OOP/main background.jpg");
        loginPanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel usernameLabel = new JLabel("Username:");
        JTextField usernameField = new JTextField();
        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField();
        JButton loginButton = new JButton(BUTTON_TEXTS[0]);
        JButton registerButton = new JButton(BUTTON_TEXTS[1]);
        Font font = new Font("Arial", Font.BOLD, 20);
        usernameLabel.setForeground(Color.WHITE);
        usernameLabel.setFont(font);
        passwordLabel.setForeground(Color.WHITE);
        passwordLabel.setFont(font);
        usernameField.setFont(font);
        passwordField.setFont(font);
        loginButton.setFont(font);
        registerButton.setFont(font);

        gbc.gridx = 0;
        gbc.gridy = 0;
        loginPanel.add(usernameLabel, gbc);
        gbc.gridx = 1;
        loginPanel.add(usernameField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        loginPanel.add(passwordLabel, gbc);
        gbc.gridx = 1;
        loginPanel.add(passwordField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        loginPanel.add(loginButton, gbc);
        gbc.gridx = 1;
        loginPanel.add(registerButton, gbc);

        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            currentUser = getUserByUsername(username);
            if (currentUser != null && currentUser.validatePassword(password)) {
                JOptionPane.showMessageDialog(frame, "Login successful.");
                setupMainPanel();
                cardLayout.show(mainPanel, "main");
            } else {
                JOptionPane.showMessageDialog(frame, "Login failed. Invalid username or password.");
            }
        });

        registerButton.addActionListener(e -> cardLayout.show(mainPanel, "register"));

        mainPanel.add(loginPanel, "login");
    }

    private void setupRegisterPanel() {
        BackgroundPanel01 registerPanel = new BackgroundPanel01("D:/STUDIES/OOP/main background.jpg");
        registerPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel usernameLabel = new JLabel(BUTTON_TEXTS[2]);
        JTextField usernameField = new JTextField();
        JLabel passwordLabel = new JLabel(BUTTON_TEXTS[3]);
        JPasswordField passwordField = new JPasswordField();
        JLabel adminLabel = new JLabel(BUTTON_TEXTS[4]);
        JCheckBox adminCheckBox = new JCheckBox();
        JLabel adminKeyLabel = new JLabel("Admin Key:");
        JPasswordField adminKeyField = new JPasswordField();
        adminKeyField.setEnabled(false);
        JButton registerButton = new JButton(BUTTON_TEXTS[1]);
        JButton backButton = new JButton(BUTTON_TEXTS[18]);

        Font font = new Font("Arial", Font.BOLD, 20);
        usernameLabel.setForeground(Color.WHITE);
        usernameLabel.setFont(font);
        passwordLabel.setForeground(Color.WHITE);
        passwordLabel.setFont(font);
        adminLabel.setForeground(Color.WHITE);
        adminLabel.setFont(font);
        adminKeyLabel.setForeground(Color.WHITE);
        adminKeyLabel.setFont(font);
        usernameField.setFont(font);
        passwordField.setFont(font);
        adminCheckBox.setFont(font);
        adminKeyField.setFont(font);
        registerButton.setFont(font);
        backButton.setFont(font);

        gbc.gridx = 0;
        gbc.gridy = 0;
        registerPanel.add(usernameLabel, gbc);
        gbc.gridx = 1;
        registerPanel.add(usernameField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        registerPanel.add(passwordLabel, gbc);
        gbc.gridx = 1;
        registerPanel.add(passwordField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        registerPanel.add(adminLabel, gbc);
        gbc.gridx = 1;
        registerPanel.add(adminCheckBox, gbc);
        gbc.gridx = 0;
        gbc.gridy = 3;
        registerPanel.add(adminKeyLabel, gbc);
        gbc.gridx = 1;
        registerPanel.add(adminKeyField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 4;
        registerPanel.add(registerButton, gbc);
        gbc.gridx = 1;
        registerPanel.add(backButton, gbc);

        adminCheckBox.addActionListener(e -> adminKeyField.setEnabled(adminCheckBox.isSelected()));

        registerButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            boolean isAdmin = adminCheckBox.isSelected();
            String adminKey = new String(adminKeyField.getPassword());

            if (isAdmin && !adminKey.equals("123456789")) {
                JOptionPane.showMessageDialog(frame, "Invalid admin key. Registration failed.");
                return;
            }

            if (getUserByUsername(username) == null) {
                addUser(username, password, isAdmin);
                JOptionPane.showMessageDialog(frame, "Registration successful. You can now log in.");
                cardLayout.show(mainPanel, "login");
            } else {
                JOptionPane.showMessageDialog(frame, "Username already exists. Registration failed.");
            }
        });

        backButton.addActionListener(e -> cardLayout.show(mainPanel, "login"));

        mainPanel.add(registerPanel, "register");
    }

    private void addUser(String username, String password, boolean isAdmin) {
        users.add(new User02(username, password, isAdmin));
    }

    private void setupMainPanel() {
        BackgroundPanel01 mainMenuPanel = new BackgroundPanel01("D:/STUDIES/OOP/main background.jpg");
        mainMenuPanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JButton quickSearchButton = new JButton(BUTTON_TEXTS[5]);
        JButton policyButton = new JButton(BUTTON_TEXTS[6]);
        JButton thenewsButton = new JButton(BUTTON_TEXTS[7]);
        JButton globlewarmingButton = new JButton(BUTTON_TEXTS[8]);
        JButton educationResourceButton = new JButton(BUTTON_TEXTS[9]);
        JButton Quiz = new JButton(BUTTON_TEXTS[10]);
        JButton logoutButton = new JButton(BUTTON_TEXTS[11]);
        JButton manageQueriesButton = new JButton(BUTTON_TEXTS[12]);

        Font font = new Font("Arial", Font.BOLD, 20);
        JButton[] buttons = {quickSearchButton, policyButton, thenewsButton, globlewarmingButton, educationResourceButton, Quiz, logoutButton, manageQueriesButton};
        for (JButton button : buttons) {
            button.setFont(font);
            button.setBackground(Color.LIGHT_GRAY);
        }

        gbc.gridx = 0;
        gbc.gridy = 0;
        mainMenuPanel.add(quickSearchButton, gbc);
        gbc.gridx = 1;
        mainMenuPanel.add(policyButton, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        mainMenuPanel.add(thenewsButton, gbc);
        gbc.gridx = 1;
        mainMenuPanel.add(globlewarmingButton, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        mainMenuPanel.add(educationResourceButton, gbc);
        gbc.gridx = 1;
        mainMenuPanel.add(Quiz, gbc);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        mainMenuPanel.add(logoutButton, gbc);

        if (currentUser != null && currentUser.isAdmin()) {
            gbc.gridy = 4;
            mainMenuPanel.add(manageQueriesButton, gbc);
        }

        ActionListener mainPanelListener = e -> {
            JButton source = (JButton) e.getSource();
            switch (source.getText()) {
                case "Quick Search":
                    cardLayout.show(mainPanel, "quickSearch");
                    break;
                case "Policy":
                    showPolicyDialog();
                    break;
                case "The News":
                    try {
                        Desktop.getDesktop().browse(new URI("https://www.wri.org/sdgs/sdg-13"));
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    break;
                case "Global Warming":
                    showInformationDialog();
                    break;
                case "Education Resource":
                    try {
                        Desktop.getDesktop().browse(new URI("https://lixuye67.wixsite.com/the-education-sour-2"));
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    break;
                case "Quiz":
                    try {
                        Desktop.getDesktop().browse(new URI("https://lixuye67.wixsite.com/sdg13"));
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    break;
                case "Logout":
                    currentUser = null;
                    cardLayout.show(mainPanel, "login");
                    break;
                case "Manage Queries":
                    if (currentUser != null && currentUser.isAdmin()) {
                        cardLayout.show(mainPanel, "adminCRUD");
                    } else {
                        JOptionPane.showMessageDialog(frame, "You do not have permission to access this feature.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    break;
            }
        };

        for (JButton button : buttons) {
            button.addActionListener(mainPanelListener);
        }

        mainPanel.add(mainMenuPanel, "main");
        setupQuickSearchPanel();
        setupAdminCRUDPanel();
    }

    private void setupQuickSearchPanel() {
        BackgroundPanel01 quickSearchPanel = new BackgroundPanel01("D:/STUDIES/OOP/main background.jpg");
        quickSearchPanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JTextField searchField = new JTextField(20);
        searchField.setEditable(true);
        JButton searchButton = new JButton(BUTTON_TEXTS[13]);
        JButton cancelButton = new JButton(BUTTON_TEXTS[14]);

        JList<String> prefilledList = new JList<>(queryModel);
        prefilledList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        prefilledList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                searchField.setText(prefilledList.getSelectedValue());
            }
        });

        Font font = new Font("Arial", Font.BOLD, 20);
        searchField.setFont(font);
        searchButton.setFont(font);
        cancelButton.setFont(font);
        prefilledList.setFont(font);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        quickSearchPanel.add(searchField, gbc);
        gbc.gridx = 2;
        gbc.gridwidth = 1;
        quickSearchPanel.add(searchButton, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 3;
        quickSearchPanel.add(new JScrollPane(prefilledList), gbc);
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        quickSearchPanel.add(cancelButton, gbc);

        searchButton.addActionListener(e -> {
            String query = searchField.getText();
            if (!query.isEmpty()) {
                try {
                    Desktop.getDesktop().browse(new URI("https://www.google.com/search?q=" + query.replace(" ", "+")));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } else {
                JOptionPane.showMessageDialog(frame, "Please enter a query or select a query from the list.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        cancelButton.addActionListener(e -> cardLayout.show(mainPanel, "main"));

        mainPanel.add(quickSearchPanel, "quickSearch");
    }

    private void setupAdminCRUDPanel() {
        BackgroundPanel01 adminCRUDPanel = new BackgroundPanel01("D:/STUDIES/OOP/main background.jpg");
        adminCRUDPanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel queryLabel = new JLabel("Query:");
        JTextField queryField = new JTextField(20);
        JButton addButton = new JButton(BUTTON_TEXTS[15]);
        JButton updateButton = new JButton(BUTTON_TEXTS[16]);
        JButton deleteButton = new JButton(BUTTON_TEXTS[17]);
        JButton backButton = new JButton(BUTTON_TEXTS[18]);

        JList<String> prefilledList = new JList<>(tempQueryModel);
        prefilledList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        prefilledList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                queryField.setText(prefilledList.getSelectedValue());
            }
        });
        Font font = new Font("Arial", Font.BOLD, 20);
        queryLabel.setFont(font);
        queryField.setFont(font);
        addButton.setFont(font);
        updateButton.setFont(font);
        deleteButton.setFont(font);
        backButton.setFont(font);
        prefilledList.setFont(font);

        gbc.gridx = 0;
        gbc.gridy = 0;
        adminCRUDPanel.add(queryLabel, gbc);
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        adminCRUDPanel.add(queryField, gbc);
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 1;
        adminCRUDPanel.add(addButton, gbc);
        gbc.gridx = 1;
        adminCRUDPanel.add(updateButton, gbc);
        gbc.gridx = 2;
        adminCRUDPanel.add(deleteButton, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 3;
        adminCRUDPanel.add(new JScrollPane(prefilledList), gbc);
        gbc.gridy = 3;
        adminCRUDPanel.add(backButton, gbc);

        ActionListener crudPanelListener = e -> {
            JButton source = (JButton) e.getSource();
            String query = queryField.getText();
            int selectedIndex = prefilledList.getSelectedIndex();
            switch (source.getText()) {
                case "Add":
                    if (!query.isEmpty() && !tempQueryModel.contains(query)) {
                        tempQueryModel.addElement(query);
                        queryField.setText("");
                    } else {
                        JOptionPane.showMessageDialog(frame, "Query is empty or already exists.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    break;
                case "Update":
                    if (!query.isEmpty() && selectedIndex != -1) {
                        tempQueryModel.setElementAt(query, selectedIndex);
                        queryModel.clear();
                        for (int i = 0; i < tempQueryModel.size(); i++) {
                            queryModel.addElement(tempQueryModel.get(i));
                        }
                    } else {
                        JOptionPane.showMessageDialog(frame, "Query is empty or no query selected.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    break;
                case "Delete":
                    if (selectedIndex != -1) {
                        tempQueryModel.remove(selectedIndex);
                    } else {
                        JOptionPane.showMessageDialog(frame, "No query selected.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    break;
                case "Back":
                    cardLayout.show(mainPanel, "main");
                    break;
            }
        };

        addButton.addActionListener(crudPanelListener);
        updateButton.addActionListener(crudPanelListener);
        deleteButton.addActionListener(crudPanelListener);
        backButton.addActionListener(crudPanelListener);

        mainPanel.add(adminCRUDPanel, "adminCRUD");
    }

    private void showPolicyDialog() {
        String[] messages = {
                "<html>1. Climate Education and Awareness<br>" +
                        "National Climate Change Education Strategies: Countries like the Philippines<br>and Brazil have integrated climate change education into their national curricula<br>to enhance students' understanding of climate impacts and solutions.<br><br>" +
                        "Public Awareness Campaigns and Training: Governments and NGOs conduct<br>public campaigns and training programs to raise awareness about climate change.<br>These include community workshops, media outreach, and online courses.</html>",
                "<html>2. Capacity Building and Technical Assistance<br>" +
                        "Global Environment Facility (GEF) Projects: GEF funds projects aimed at<br>strengthening the technical and managerial capacities of national and local<br>governments to better address climate change. These projects often include<br>training government officials, improving data collection, and enhancing disaster<br>response mechanisms.<br><br>" +
                        "Capacity-Building Initiative for Transparency (CBIT): Managed by the GEF,<br>CBIT helps developing countries meet the transparency requirements of the<br>Paris Agreement by enhancing their capacities for effective climate action<br>implementation.</html>",
                "<html>3. Early Warning Systems and Disaster Risk Management<br>" +
                        "Climate Risk and Early Warning Systems (CREWS): CREWS provides financial<br>and technical support to developing countries to establish and strengthen early<br>warning systems for climate-related hazards, aiming to reduce the vulnerability of<br>populations through timely and effective warnings.<br><br>" +
                        "Community-Based Disaster Risk Management (CBDRM): Many countries<br>implement CBDRM projects to strengthen local communities' capacity to respond<br>to climate-related disasters. These projects include disaster preparedness drills,<br>community risk assessments, and emergency planning.</html>",
                "<html>4. Policy Integration and National Plans<br>" +
                        "National Adaptation Plans (NAPs): Countries develop and implement NAPs to<br>identify medium- and long-term climate adaptation needs and strategies.<br>For example, Bangladesh and Vietnam have NAPs that address sectors such as<br>agriculture, fisheries, water management, and public health.<br><br>" +
                        "Nationally Determined Contributions (NDCs): Under the Paris Agreement,<br>countries submit NDCs outlining their commitments to mitigate climate change<br>and adapt to its impacts. For instance, India and South Africa's NDCs include<br>large-scale renewable energy projects and agricultural adaptation measures.</html>",
                "<html>5. International Cooperation and Funding Mechanisms<br>" +
                        "Green Climate Fund (GCF): The GCF supports developing countries in<br>implementing climate change measures by providing grants and loans. Projects<br>funded by the GCF include climate-smart agriculture in Kenya and coastal<br>adaptation in Fiji.<br><br>" +
                        "Paris Agreement Framework: Through the Paris Agreement, countries<br>collaborate to share technology and knowledge, promoting global climate action.<br>The United Nations Framework Convention on Climate Change (UNFCCC) offers<br>various technical and financial support mechanisms to assist countries in their<br>climate efforts.</html>"
        };

        String[] options = {"Next", "Cancel"};

        int i = 0;
        do {
            int result = JOptionPane.showOptionDialog(frame, messages[i], "Policy",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                    null, options, options[0]);
            if (result != 0) {
                break;
            }
            i++;
        } while (i < messages.length);
    }

    private void showInformationDialog() {
        String[] messages = {
                "<html>1. Sea Level Rise<br>Climate warming leads to the melting of glaciers and<br>polar ice caps, as well as the thermal expansion of seawater,<br>causing sea levels to rise.<br><br>This can inundate low-lying coastal areas, threatening the<br>habitats of millions of people, particularly those living on<br>small islands and in coastal regions.</html>",
                "<html>2. Increase in Extreme Weather Events<br>Climate warming increases the frequency and intensity<br>of extreme weather events such as heatwaves, heavy<br>rainfall, hurricanes, and droughts.<br><br>These extreme events cause significant property damage,<br>loss of life, and disruptions to agriculture, infrastructure,<br>and economic activities.</html>",
                "<html>3. Damage to Ecosystems and Biodiversity<br>Climate warming alters habitats, putting many species at<br>risk of extinction.<br><br>For example, coral bleaching is caused by rising sea<br>temperatures, severely affecting marine biodiversity.<br>Terrestrial species may also be forced to migrate or<br>face extinction due to changing habitats.</html>"
        };

        String[] options = {"Next", "Cancel"};

        int i = 0;
        do {
            int result = JOptionPane.showOptionDialog(frame, messages[i], "Information",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                    null, options, options[0]);
            if (result != 0) {
                break;
            }
            i++;
        } while (i < messages.length);
    }

    private User02 getUserByUsername(String username) {
        for (User02 user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        new SDG13App();
    }
}

class BackgroundPanel01 extends JPanel {
    private Image backgroundImage;

    public BackgroundPanel01(String fileName) {
        try {
            backgroundImage = new ImageIcon(fileName).getImage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}

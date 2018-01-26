package sclab.cnu.quan;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;

import java.awt.FlowLayout;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class ScrapingGUI {

	private JFrame frame;
	private JTextField textGathering;
	private JTextField textAchieved;
	private JTextField textInReview;
	private JTextField textApproved;
	private JTextField textInProduct;
	private JTextField textExpired;

	String DEST_FOLDER;
	ArrayList<Integer> DEST_SIZE = new ArrayList<>();
	ArrayList<Integer> DEST_TYPE = new ArrayList<>();
	public Long SLEEP;

	int atLest_OneSelect = 0;
	Ahttp_sample2 obj_Ahttp_sample2 = new Ahttp_sample2();

	private JTextField textNotApproved;
	private JTextField textOnShelves;
	private JTextField textANY;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ScrapingGUI window = new ScrapingGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ScrapingGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		String currentDirectory;
		File file = new File("");
		JButton btnSave = new JButton("Save folder");
		JButton btnScraping = new JButton("Scrape");

		JSpinner spinner = new JSpinner();
		JSpinner spinner_1 = new JSpinner();
		JSpinner spinner_2 = new JSpinner();
		JSpinner spinner_3 = new JSpinner();

		currentDirectory = file.getAbsolutePath();
		System.out.println("Current working directory : " + currentDirectory);
		StringBuffer sb2 = new StringBuffer(currentDirectory);
		for (int i = 0, d = 0; i < currentDirectory.length(); i++) {

			if (currentDirectory.charAt(i) == '\\') {
				// destinationalFolder.(i, ' ');
				d++;
				sb2.insert(i + d, '\\');

			}

		}
		currentDirectory = sb2.toString();
		DEST_FOLDER = currentDirectory;
		System.out.println("Folder Default : " + DEST_FOLDER);

		frame = new JFrame("Scraping from Lego Site");
		frame.setResizable(false);
		frame.getContentPane().setBackground(new Color(153, 255, 255));
		frame.getContentPane().setForeground(new Color(51, 102, 51));
		frame.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(12, 13, 441, 21);
		frame.getContentPane().add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

		JLabel lblNewLabel_1 = new JLabel("Message:");
		panel.add(lblNewLabel_1);

		JLabel lblRunning = new JLabel("");
		lblRunning.setFont(new Font("Tahoma", Font.PLAIN, 13));
		panel.add(lblRunning);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_1.setForeground(new Color(0, 255, 153));
		panel_1.setBounds(12, 42, 290, 318);
		frame.getContentPane().add(panel_1);

		JRadioButton rdbtnGathering = new JRadioButton("Gathering");
		rdbtnGathering.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if (arg0.getStateChange() == ItemEvent.SELECTED) {
					textGathering.setEditable(true);
					lblRunning.setText("");
					atLest_OneSelect++;
					if (atLest_OneSelect > 0) {
						btnScraping.setEnabled(true);
					}

				} else if (arg0.getStateChange() == ItemEvent.DESELECTED) {
					textGathering.setEditable(false);
					lblRunning.setText("");
					atLest_OneSelect--;
					if (atLest_OneSelect == 0) {
						btnScraping.setEnabled(false);
					}
				}
			}
		});

		JRadioButton rdbtnAchieved = new JRadioButton("Achieved");
		rdbtnAchieved.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					textAchieved.setEditable(true);
					lblRunning.setText("");
					atLest_OneSelect++;
					if (atLest_OneSelect > 0) {
						btnScraping.setEnabled(true);
					}
				} else if (e.getStateChange() == ItemEvent.DESELECTED) {
					textAchieved.setEditable(false);
					lblRunning.setText("");
					atLest_OneSelect--;
					if (atLest_OneSelect == 0) {
						btnScraping.setEnabled(false);
					}

				}
			}
		});

		JRadioButton rdbtnInReview = new JRadioButton("In Review");
		rdbtnInReview.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					textInReview.setEditable(true);
					lblRunning.setText("");
					atLest_OneSelect++;
					if (atLest_OneSelect > 0) {
						btnScraping.setEnabled(true);
					}
				} else if (e.getStateChange() == ItemEvent.DESELECTED) {
					textInReview.setEditable(false);
					lblRunning.setText("");
					atLest_OneSelect--;
					if (atLest_OneSelect == 0) {
						btnScraping.setEnabled(false);
					}
				}
			}
		});

		JRadioButton rdbtnApproved = new JRadioButton("Approved");
		rdbtnApproved.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					textApproved.setEditable(true);
					lblRunning.setText("");
					atLest_OneSelect++;
					if (atLest_OneSelect > 0) {
						btnScraping.setEnabled(true);
					}
				} else if (e.getStateChange() == ItemEvent.DESELECTED) {
					textApproved.setEditable(false);
					lblRunning.setText("");
					atLest_OneSelect--;
					if (atLest_OneSelect == 0) {
						btnScraping.setEnabled(false);
					}
				}
			}
		});

		JRadioButton rdbtnInProduct = new JRadioButton("In Product");
		rdbtnInProduct.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					textInProduct.setEditable(true);
					lblRunning.setText("");
					atLest_OneSelect++;
					if (atLest_OneSelect > 0) {
						btnScraping.setEnabled(true);
					}
				} else if (e.getStateChange() == ItemEvent.DESELECTED) {
					textInProduct.setEditable(false);
					lblRunning.setText("");
					atLest_OneSelect--;
					if (atLest_OneSelect == 0) {
						btnScraping.setEnabled(false);
					}
				}
			}
		});

		JRadioButton rdbtnExpired = new JRadioButton("Expired");
		rdbtnExpired.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					textExpired.setEditable(true);
					atLest_OneSelect++;
					if (atLest_OneSelect > 0) {
						btnScraping.setEnabled(true);
					}
				} else if (e.getStateChange() == ItemEvent.DESELECTED) {
					textExpired.setEditable(false);
					atLest_OneSelect--;
					if (atLest_OneSelect == 0) {
						btnScraping.setEnabled(false);
					}
				}
			}
		});

		JLabel lblSize = new JLabel("size");

		JRadioButton rdbtnNotapproved = new JRadioButton("NOTApproved");
		rdbtnNotapproved.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					textNotApproved.setEditable(true);
					atLest_OneSelect++;
					if (atLest_OneSelect > 0) {
						btnScraping.setEnabled(true);
					}
				} else if (e.getStateChange() == ItemEvent.DESELECTED) {
					textNotApproved.setEditable(false);
					atLest_OneSelect--;
					if (atLest_OneSelect == 0) {
						btnScraping.setEnabled(false);
					}
				}
			}
		});

		JRadioButton rdbtnOnShelves = new JRadioButton("On Shelves");
		rdbtnOnShelves.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					textOnShelves.setEditable(true);
					atLest_OneSelect++;
					if (atLest_OneSelect > 0) {
						btnScraping.setEnabled(true);
					}
				} else if (e.getStateChange() == ItemEvent.DESELECTED) {
					textOnShelves.setEditable(false);
					atLest_OneSelect--;
					if (atLest_OneSelect == 0) {
						btnScraping.setEnabled(false);
					}
				}
			}
		});

		JRadioButton rdbtnAny = new JRadioButton("ANY");
		rdbtnAny.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					textANY.setEditable(true);
					atLest_OneSelect++;
					if (atLest_OneSelect > 0) {
						btnScraping.setEnabled(true);
					}
				} else if (e.getStateChange() == ItemEvent.DESELECTED) {
					textANY.setEditable(false);
					atLest_OneSelect--;
					if (atLest_OneSelect == 0) {
						btnScraping.setEnabled(false);
					}
				}
			}
		});

		JLabel lblNewLabel = new JLabel("Type project");

		textGathering = new JTextField();
		textGathering.setEditable(false);
		textGathering.setText("21");
		textGathering.setHorizontalAlignment(SwingConstants.RIGHT);
		textGathering.setColumns(10);

		textAchieved = new JTextField();
		textAchieved.setEditable(false);
		textAchieved.setHorizontalAlignment(SwingConstants.RIGHT);
		textAchieved.setText("21");
		textAchieved.setColumns(10);

		textInReview = new JTextField();
		textInReview.setEditable(false);
		textInReview.setText("21");
		textInReview.setHorizontalAlignment(SwingConstants.RIGHT);
		textInReview.setColumns(10);

		textApproved = new JTextField();
		textApproved.setEditable(false);
		textApproved.setText("21");
		textApproved.setHorizontalAlignment(SwingConstants.RIGHT);
		textApproved.setColumns(10);

		textInProduct = new JTextField();
		textInProduct.setEditable(false);
		textInProduct.setText("21");
		textInProduct.setHorizontalAlignment(SwingConstants.RIGHT);
		textInProduct.setColumns(10);

		textExpired = new JTextField();
		textExpired.setEditable(false);
		textExpired.setText("21");
		textExpired.setHorizontalAlignment(SwingConstants.RIGHT);
		textExpired.setColumns(10);

		textNotApproved = new JTextField();
		textNotApproved.setText("21");
		textNotApproved.setHorizontalAlignment(SwingConstants.RIGHT);
		textNotApproved.setEditable(false);
		textNotApproved.setColumns(10);

		textOnShelves = new JTextField();
		textOnShelves.setText("21");
		textOnShelves.setHorizontalAlignment(SwingConstants.RIGHT);
		textOnShelves.setEditable(false);
		textOnShelves.setColumns(10);

		textANY = new JTextField();
		textANY.setText("21");
		textANY.setHorizontalAlignment(SwingConstants.RIGHT);
		textANY.setEditable(false);
		textANY.setColumns(10);
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup().addGap(32)
						.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_panel_1.createSequentialGroup().addComponent(rdbtnInProduct)
										.addPreferredGap(ComponentPlacement.RELATED, 39, Short.MAX_VALUE).addComponent(
												textInProduct, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel_1.createSequentialGroup().addComponent(rdbtnApproved)
										.addPreferredGap(ComponentPlacement.RELATED, 43, Short.MAX_VALUE).addComponent(
												textApproved, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel_1.createSequentialGroup().addComponent(rdbtnInReview)
										.addPreferredGap(ComponentPlacement.RELATED, 41, Short.MAX_VALUE).addComponent(
												textInReview, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel_1.createSequentialGroup().addComponent(rdbtnAchieved)
										.addPreferredGap(ComponentPlacement.RELATED, 47, Short.MAX_VALUE).addComponent(
												textAchieved, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel_1.createSequentialGroup().addComponent(rdbtnGathering)
										.addPreferredGap(ComponentPlacement.RELATED, 43, Short.MAX_VALUE).addComponent(
												textGathering, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel_1.createSequentialGroup().addComponent(lblNewLabel)
										.addPreferredGap(ComponentPlacement.RELATED, 148, Short.MAX_VALUE)
										.addComponent(lblSize))
								.addGroup(gl_panel_1.createSequentialGroup()
										.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
												.addComponent(rdbtnExpired)
												.addComponent(rdbtnAny, GroupLayout.PREFERRED_SIZE, 71,
														GroupLayout.PREFERRED_SIZE)
												.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING, false)
														.addComponent(rdbtnOnShelves, Alignment.LEADING,
																GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
														.addComponent(rdbtnNotapproved, Alignment.LEADING)))
										.addPreferredGap(ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
										.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING, false)
												.addComponent(textNotApproved, GroupLayout.DEFAULT_SIZE, 116,
														Short.MAX_VALUE)
												.addComponent(textANY).addComponent(textExpired)
												.addComponent(textOnShelves))))
						.addContainerGap()));
		gl_panel_1.setVerticalGroup(gl_panel_1.createParallelGroup(Alignment.LEADING).addGroup(gl_panel_1
				.createSequentialGroup()
				.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE).addComponent(lblNewLabel)
						.addComponent(lblSize))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE).addComponent(rdbtnGathering).addComponent(
						textGathering, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
						GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE).addComponent(rdbtnAchieved).addComponent(
						textAchieved, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE).addComponent(rdbtnInReview).addComponent(
						textInReview, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE).addComponent(rdbtnApproved).addComponent(
						textApproved, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE).addComponent(rdbtnNotapproved)
						.addComponent(textNotApproved, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE).addComponent(rdbtnInProduct).addComponent(
						textInProduct, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
						GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE).addComponent(rdbtnOnShelves).addComponent(
						textOnShelves, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
						GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE).addComponent(rdbtnExpired).addComponent(
						textExpired, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE).addComponent(rdbtnAny).addComponent(
						textANY, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addContainerGap(22, Short.MAX_VALUE)));
		gl_panel_1.setHonorsVisibility(false);
		gl_panel_1.setAutoCreateContainerGaps(true);
		panel_1.setLayout(gl_panel_1);

		// JButton btnSave = new JButton("Save folder");
		// JButton btnScraping = new JButton("Scrape");

		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser chooser = new JFileChooser();
				chooser.setCurrentDirectory(new java.io.File("."));
				String choosertitle = "Destinational folder";
				chooser.setDialogTitle(choosertitle);
				chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				chooser.setAcceptAllFileFilterUsed(false);
				//
				System.out.println("getCurrentDirectory(): " + chooser.getCurrentDirectory());
				String Default_destinationalFolder = chooser.getCurrentDirectory().toString();
				StringBuffer sb2 = new StringBuffer(Default_destinationalFolder);
				if (chooser.showSaveDialog(btnSave) == JFileChooser.APPROVE_OPTION) {

					System.out.println("getSelectedFile(): " + chooser.getSelectedFile());
					String destinationalFolder = chooser.getSelectedFile().toString();
					StringBuffer sb = new StringBuffer(destinationalFolder);
					lblRunning.setText(" Output store into " + destinationalFolder);

					for (int i = 0, d = 0; i < destinationalFolder.length(); i++) {

						if (destinationalFolder.charAt(i) == '\\') {
							// destinationalFolder.(i, ' ');
							d++;
							sb.insert(i + d, '\\');

						}

					}
					destinationalFolder = sb.toString();
					DEST_FOLDER = destinationalFolder;
					System.out.println("Destination Folder : " + DEST_FOLDER);

				} else {
					System.out.println("No Selection -> we chose DEFAULT");
					lblRunning.setText(" Output store into " + Default_destinationalFolder);

					for (int i = 0, d = 0; i < Default_destinationalFolder.length(); i++) {

						if (Default_destinationalFolder.charAt(i) == '\\') {
							// destinationalFolder.(i, ' ');
							d++;
							sb2.insert(i + d, '\\');

						}

					}
					Default_destinationalFolder = sb2.toString();
					DEST_FOLDER = Default_destinationalFolder;
					System.out.println("Destination Folder Default : " + DEST_FOLDER);
				}
			}
		});
		btnSave.setBounds(314, 286, 139, 30);
		frame.getContentPane().add(btnSave);

		btnScraping.setEnabled(false);
		btnScraping.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DEST_SIZE.clear();
				DEST_TYPE.clear();

				SLEEP = Long.parseLong(spinner.getValue().toString()) * 24 * 60 * 60
						+ Long.parseLong(spinner_1.getValue().toString()) * 60 * 60
						+ Long.parseLong(spinner_2.getValue().toString()) * 60
						+ Long.parseLong(spinner_3.getValue().toString());
				System.out.println("Delay time: " + SLEEP);

				obj_Ahttp_sample2.setSlep(SLEEP);
				obj_Ahttp_sample2.setDEST_FOLDER(DEST_FOLDER);

				String text_Gathering = textGathering.getText();
				System.out.println("text_Gathering : " + text_Gathering);
				DEST_SIZE.add(Integer.parseInt(text_Gathering));

				String text_Achieved = textAchieved.getText();
				System.out.println("text_Achieved : " + text_Achieved);
				DEST_SIZE.add(Integer.parseInt(text_Achieved));

				String text_InReview = textInReview.getText();
				System.out.println("text_InReview : " + text_InReview);
				DEST_SIZE.add(Integer.parseInt(text_InReview));

				String text_Approved = textApproved.getText();
				System.out.println("text_Approved : " + text_Approved);
				DEST_SIZE.add(Integer.parseInt(text_Approved));

				String text_NOTApproved = textNotApproved.getText();
				System.out.println("text_NOTApproved : " + text_NOTApproved);
				DEST_SIZE.add(Integer.parseInt(text_NOTApproved));

				String text_InProduct = textInProduct.getText();
				System.out.println("text_InProduct : " + text_InProduct);
				DEST_SIZE.add(Integer.parseInt(text_InProduct));

				String text_OnShelves = textOnShelves.getText();
				System.out.println("text_OnShelves : " + text_OnShelves);
				DEST_SIZE.add(Integer.parseInt(text_OnShelves));

				String text_Expired = textExpired.getText();
				System.out.println("text_Expired : " + text_Expired);
				DEST_SIZE.add(Integer.parseInt(text_Expired));

				String text_ANY = textANY.getText();
				System.out.println("text_ANY : " + text_ANY);
				DEST_SIZE.add(Integer.parseInt(text_ANY));

				if (rdbtnGathering.isSelected()) {
					DEST_TYPE.add(0);
				}
				if (rdbtnAchieved.isSelected()) {
					DEST_TYPE.add(1);
				}
				if (rdbtnInReview.isSelected()) {
					DEST_TYPE.add(2);
				}
				if (rdbtnApproved.isSelected()) {
					DEST_TYPE.add(3);
				}
				if (rdbtnNotapproved.isSelected()) {
					DEST_TYPE.add(4);
				}
				if (rdbtnInProduct.isSelected()) {
					DEST_TYPE.add(5);
				}
				if (rdbtnOnShelves.isSelected()) {
					DEST_TYPE.add(6);
				}
				if (rdbtnExpired.isSelected()) {
					DEST_TYPE.add(7);
				}
				if (rdbtnAny.isSelected()) {
					DEST_TYPE.add(8);
				}

				// btnScraping.setText("Running");
				lblRunning.setText("Scraper is running on Lego site");
				lblRunning.setEnabled(true);

				try {
					lblRunning.setText(" RUNNING");
					obj_Ahttp_sample2.Scraping(DEST_TYPE, DEST_SIZE);
					lblRunning.setText(" SCRAPED SUCESSFULLY");

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					lblRunning.setText("Scraper meet exception");
					lblRunning.setEnabled(true);
				}

			}
		});
		btnScraping.setBounds(314, 329, 139, 30);
		if (rdbtnGathering.isSelected()) {
			btnScraping.setEnabled(false);
		}
		frame.getContentPane().add(btnScraping);

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(314, 47, 139, 135);
		frame.getContentPane().add(panel_2);

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setIcon(new ImageIcon(ScrapingGUI.class.getResource("/sclab/cnu/quan/lego.png")));
		panel_2.add(lblNewLabel_2);

		JPanel panel_3 = new JPanel();
		panel_3.setBounds(12, 379, 441, 47);
		frame.getContentPane().add(panel_3);
		panel_3.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		JLabel lblNewLabel_3 = new JLabel("Period Time:");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.LEFT);
		panel_3.add(lblNewLabel_3);

		JLabel lblDay = new JLabel("Days");
		lblDay.setHorizontalAlignment(SwingConstants.LEFT);
		panel_3.add(lblDay);

		// JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		panel_3.add(spinner);

		JLabel lblHours = new JLabel("Hours");
		lblHours.setHorizontalAlignment(SwingConstants.LEFT);
		panel_3.add(lblHours);

		// JSpinner spinner_1 = new JSpinner();
		spinner_1.setModel(new SpinnerNumberModel(0, 0, 23, 1));
		panel_3.add(spinner_1);

		JLabel lblMinutes = new JLabel("Minutes");
		lblMinutes.setHorizontalAlignment(SwingConstants.LEFT);
		panel_3.add(lblMinutes);

		// JSpinner spinner_2 = new JSpinner();
		spinner_2.setModel(new SpinnerNumberModel(0, 0, 59, 1));
		panel_3.add(spinner_2);

		JLabel lblSeconds = new JLabel("Seconds");
		lblSeconds.setHorizontalAlignment(SwingConstants.LEFT);
		panel_3.add(lblSeconds);

		// JSpinner spinner_3 = new JSpinner();
		spinner_3.setModel(new SpinnerNumberModel(0, 0, 59, 1));
		panel_3.add(spinner_3);
		frame.setBounds(100, 100, 471, 474);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
}

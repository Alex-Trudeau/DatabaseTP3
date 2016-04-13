import java.awt.BorderLayout;
import java.sql.*;
import java.awt.EventQueue;
import javax.swing.JOptionPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class Pharmaco_UL extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String username = "";
	String password = "";
	int attempt = 0;
	private JPanel contentPane;
	Connection con;

	


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Pharmaco_UL frame = new Pharmaco_UL();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Pharmaco_UL() {
		login();
		

		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 539, 494);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblEtude = new JLabel("\u00C9tudes");
		lblEtude.setBounds(10, 11, 46, 14);
		contentPane.add(lblEtude);
		
		JScrollPane scrollPaneEtudes = new JScrollPane();
		scrollPaneEtudes.setBounds(10, 120, 339, -87);
		contentPane.add(scrollPaneEtudes);
		
		final JTextArea textAreaGene = new JTextArea();
		textAreaGene.setBounds(337, 149, 113, 22);
		contentPane.add(textAreaGene);
		
		final JTextArea textAreaNoVariant = new JTextArea();
		textAreaNoVariant.setBounds(215, 149, 60, 22);
		contentPane.add(textAreaNoVariant);
		
		JTextArea textAreaEtudes = new JTextArea();
		textAreaEtudes.setText("");
		textAreaEtudes.setBounds(10, 36, 339, 82);
		contentPane.add(textAreaEtudes);
		
				try {
						Statement stmt = con.createStatement();
						ResultSet rs = stmt.executeQuery("Select NO_ETUDE, TITRE_ET from TP_ETUDE");
							boolean more = rs.next();

							while (more) {
								String value = rs.getString(1);
								String value2 = rs.getString(2);
				
								textAreaEtudes.append(value + "             " + value2 + "\n");
								more = rs.next();
							}
							
				} catch(Exception e) {
					textAreaEtudes.append("Aucune �tude");
				}
		
		JButton btnReherche = new JButton("Rechercher");
		btnReherche.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String NomDrogue = JOptionPane.showInputDialog(contentPane, "Entrez le nom de la drogue");
				String Gene = JOptionPane.showInputDialog(contentPane, "Entrez le g�ne");
				String NoVariant = JOptionPane.showInputDialog(contentPane, "Entrez le num�ro de variant");
				
			}
		});
		btnReherche.setBounds(382, 37, 89, 23);
		contentPane.add(btnReherche);
		
		
		
		JLabel lblNoEtude = new JLabel("No \u00C9tude");
		lblNoEtude.setBounds(10, 129, 46, 14);
		contentPane.add(lblNoEtude);
		
		JLabel lblNoDrogue = new JLabel("No Drogue");
		lblNoDrogue.setBounds(10, 154, 63, 14);
		contentPane.add(lblNoDrogue);
		
		JLabel lblNomDrogue = new JLabel("Nom Drogue");
		lblNomDrogue.setBounds(10, 182, 63, 14);
		contentPane.add(lblNomDrogue);
		
		final JTextArea textAreaNoEtude = new JTextArea();
		textAreaNoEtude.setBounds(79, 124, 63, 22);
		contentPane.add(textAreaNoEtude);

			
		
		
		final JTextArea textAreaNoDrogue = new JTextArea();
		textAreaNoDrogue.setBounds(79, 149, 63, 22);
		contentPane.add(textAreaNoDrogue);
		
		final JTextArea textAreaNomDrogue = new JTextArea();
		textAreaNomDrogue.setBounds(79, 177, 63, 22);
		contentPane.add(textAreaNomDrogue);
		
		final JButton btnDrugBank = new JButton("Aller sur DrugBank");
		btnDrugBank.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String url = "http://www.google.ca";
					
					java.awt.Desktop.getDesktop().browse(java.net.URI.create(url));
				}	catch (java.io.IOException e1) {
					JOptionPane.showInputDialog(contentPane, e1.getMessage());
				}
			}
		});
		btnDrugBank.setBounds(152, 178, 131, 23);
		contentPane.add(btnDrugBank);
		btnDrugBank.setEnabled(false);
		
		final JButton btnGO = new JButton("GO");
		btnGO.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String NoEtude = textAreaNoEtude.getText();
				
				try {
					Statement stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery("Select NO_DROGUE, NO_VAR_GEN, GENE_VAR, NOM_DROGUE from TP_ETUDE where NO_ETUDE ="+NoEtude);
						boolean more = rs.next();
						if (more == false) {
							System.exit(0);
						}
		
						while (more) {
							String NoDrogue = rs.getString(1);
							String NoVariant = rs.getString(2);
							String Gene = rs.getString(3);
							String NomDrogue = rs.getString(4);
							textAreaNoDrogue.setText(NoDrogue);
							textAreaNoVariant.setText(NoVariant);
							textAreaGene.setText(Gene);
							textAreaNomDrogue.setText(NomDrogue);
							btnDrugBank.setEnabled(true);

							more = rs.next();
						}
						
			} catch(Exception e) {
				JOptionPane.showMessageDialog(contentPane, "Num�ro d'�tude invalide");
			}
				
			}
		});
		btnGO.setBounds(152, 125, 89, 23);
		contentPane.add(btnGO);
		btnGO.setEnabled(false);

		
		final JButton btnNouveauPatient = new JButton("Nouveau Patient");
		btnNouveauPatient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String NoPatient = JOptionPane.showInputDialog(contentPane, "Entrez le num�ro do patient");
				String Province = JOptionPane.showInputDialog(contentPane, "Entrez la province du patient");
				String IndiceEffMeta = JOptionPane.showInputDialog(contentPane, "Entrez l'indice d'�fficacit� m�tabolique");
			}
		});
		btnNouveauPatient.setBounds(29, 228, 113, 23);
		contentPane.add(btnNouveauPatient);
		btnNouveauPatient.setEnabled(false);
		
		
		final JButton btnSupprimerPatient = new JButton("Supprimer Patient");
		btnSupprimerPatient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String NoPatient = JOptionPane.showInputDialog(contentPane, "Entrez le num�ro do patient � supprimer");
			}
		});
		btnSupprimerPatient.setBounds(187, 228, 131, 23);
		contentPane.add(btnSupprimerPatient);
		btnSupprimerPatient.setEnabled(false);
		
		final JButton btnModifierPatient = new JButton("Modifier Patient");
		btnModifierPatient.setBounds(358, 228, 131, 23);
		contentPane.add(btnModifierPatient);
		btnModifierPatient.setEnabled(false);
		
		JLabel lblNoVariant = new JLabel("No Variant");
		lblNoVariant.setBounds(162, 154, 60, 14);
		contentPane.add(lblNoVariant);
		
		JButton btnAnnuler = new JButton("Annuler");
		btnAnnuler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textAreaNoEtude.setText("");
				textAreaNoVariant.setText("");
				textAreaNoDrogue.setText("");
				textAreaNomDrogue.setText("");
				textAreaGene.setText("");
				
			}
		});
		btnAnnuler.setBounds(382, 71, 89, 23);
		contentPane.add(btnAnnuler);
		
		
		
		JLabel lblGene = new JLabel("G\u00E8ne");
		lblGene.setBounds(303, 154, 46, 14);
		contentPane.add(lblGene);
		
		
		
		
		
		JButton btnIndiceEff = new JButton("Indice d'\u00E9fficacit\u00E9 m\u00E9tabolique");
		btnIndiceEff.setBounds(293, 178, 196, 23);
		contentPane.add(btnIndiceEff);
		btnIndiceEff.setEnabled(false);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 375, 451, -87);
		contentPane.add(scrollPane);
		
		JTextArea textAreaPatient = new JTextArea();
		textAreaPatient.setBounds(20, 299, 436, 69);
		contentPane.add(textAreaPatient);
		
		JLabel lblPatient = new JLabel("Patients      Province       Indice d'\u00E9fficacit\u00E9 m\u00E9tabolique");
		lblPatient.setBounds(20, 276, 282, 14);
		contentPane.add(lblPatient);
		
		JButton btnQuitter = new JButton("Quitter");
		btnQuitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);	
			}
		});
		btnQuitter.setBounds(400, 386, 89, 23);
		contentPane.add(btnQuitter);
		
		textAreaNoEtude.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
				if (textAreaNoEtude.getText().equals("")) {
					btnNouveauPatient.setEnabled(false);
					btnSupprimerPatient.setEnabled(false);
					btnModifierPatient.setEnabled(false);
					btnGO.setEnabled(true);
					} else {
						btnNouveauPatient.setEnabled(true);
						btnSupprimerPatient.setEnabled(true);
						btnModifierPatient.setEnabled(true);
						btnGO.setEnabled(true);
					}
			  }
			  public void removeUpdate(DocumentEvent e) {
				  if (textAreaNoEtude.getText().equals("")) {
						btnNouveauPatient.setEnabled(false);
						btnSupprimerPatient.setEnabled(false);
						btnModifierPatient.setEnabled(false);
						btnGO.setEnabled(true);
						} else {
							btnNouveauPatient.setEnabled(true);
							btnSupprimerPatient.setEnabled(true);
							btnModifierPatient.setEnabled(true);
							btnGO.setEnabled(true);
						}
			  }
			  public void insertUpdate(DocumentEvent e) {
				  if (textAreaNoEtude.getText().equals("")) {
						btnNouveauPatient.setEnabled(false);
						btnSupprimerPatient.setEnabled(false);
						btnModifierPatient.setEnabled(false);
						btnGO.setEnabled(true);
						} else {
							btnNouveauPatient.setEnabled(true);
							btnSupprimerPatient.setEnabled(true);
							btnModifierPatient.setEnabled(true);
							btnGO.setEnabled(true);
						}
			  }
		

	});
		
}
	
	public void login() {
		try {
			if (attempt < 2) {
				attempt++;
				username = JOptionPane.showInputDialog("Quel est le nom de l'utilisateur?");
			    password = JOptionPane.showInputDialog("Quel est le mot de passe?");
			    
			    
			    //1-initialisation de la connexion.
				Class.forName("oracle.jdbc.driver.OracleDriver");
				con = DriverManager.getConnection ("jdbc:oracle:thin:@ift-oracle2k3.fsg.ulaval.ca:1521:ora11g", username, password);
			} else {
				System.exit(0);
			}
		} catch (Throwable e) {
			String message = e.getMessage();
			if (e.getMessage().indexOf("invalid username/password")>0) {
				message = "La combinaison mot de passe/usager est invalide";
		    }
		      else
		      //Pour toutes les autres erreur qu'on ne g�re pas, on affiche le message anglais
		      	{
		    	  message = e.getMessage();		      
		  		}
			JOptionPane.showMessageDialog(null, message);
			login();
		}
	}
	
	
	
}

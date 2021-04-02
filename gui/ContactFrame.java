package gui;

import controller.ContactController;
import model.Contact;
import model.Persoana;

import javax.swing.*;
import java.util.List;

public class ContactFrame extends JFrame {
    private JList list1;
    private JPanel mainpanel;
    private JTextField textField1;
    private JButton adaugabutton;
    private DefaultListModel<Contact> model;
    private Persoana persoana;

   public ContactFrame(Persoana persoana) {
        model = new DefaultListModel<>();
        list1.setModel(model);
        this.persoana = persoana;

        afisContacte();
        adaugabutton.addActionListener(ev -> adaugaContact());



        setContentPane(mainpanel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

    }


    private void afisContacte() {
        List<Contact> contacte = ContactController.getInstance().findByPersoana(persoana.getId());
        model.clear();
        contacte.forEach(model::addElement);
    }

    private void adaugaContact() {
        String telefon = textField1.getText();
        Contact contact = new Contact(0, telefon, persoana.getId());

        boolean rez = ContactController.getInstance().create(contact);

        if (rez==true) {
            afisContacte();
        }
        else {
            JOptionPane.showMessageDialog(null,"d");
        }

        textField1.setText("");
    }
}



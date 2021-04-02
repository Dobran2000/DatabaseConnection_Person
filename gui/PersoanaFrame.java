package gui;

import controller.PersoanaController;
import model.Persoana;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class PersoanaFrame extends JFrame {
    private JList list1;
    private JPanel mainPanel;
    private JTextField textField1;
    private JButton adaugaButton;

    private DefaultListModel<Persoana> model;

    public PersoanaFrame() {
        model = new DefaultListModel<>();
        list1.setModel(model);
        afisPersoane();
        adaugaButton.addActionListener(ev -> adaugaPersoana());
        setContentPane(mainPanel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        list1.addMouseListener(new MouseAdapter() {
            /**
             * {@inheritDoc}
             *
             * @param e
             */
            @Override
            public void mouseClicked(MouseEvent e) {
                onMouseClickedForList(e);
            }
        });
    }


    private void afisPersoane() {
        List<Persoana> persoane = PersoanaController.getInstance().findALll();
        model.clear();
        persoane.forEach(p -> model.addElement(p));

    }

    private void adaugaPersoana() {
        String nume = textField1.getText();
        Persoana p = new Persoana(0, nume);
        boolean rez = PersoanaController.getInstance().create(p);

        if (rez) {
            afisPersoane();
        } else {
            JOptionPane.showMessageDialog(null, "Numele exista deja");
            textField1.setText("");
        }
    }


    private void onMouseClickedForList(MouseEvent e) {
        boolean itemSelected = list1.getSelectedValue() != null;

        if(itemSelected &&
           e.getButton()==MouseEvent.BUTTON1
                && e.getClickCount()==1
        )
        {
            Persoana selected=(Persoana) list1.getSelectedValue();
            new ContactFrame(selected);
        }

        if(itemSelected &&
                e.getButton()==MouseEvent.BUTTON1
                && e.getClickCount()==2
        )
        {
            Persoana selected=(Persoana) list1.getSelectedValue();
            boolean rez= PersoanaController.getInstance().delete(selected.getId());

        if(rez)
        {
            afisPersoane();
        }

        }



    }
}
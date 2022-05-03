package com.company.view;

import com.company.Controller;
import com.company.db.DataBaseController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class actionListeners {
    public static void menuActionListeners(MainFrame frame, MenuPanel menuPanel) {
        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object source = e.getSource();
                if (source == menuPanel.list) {
                    ListPanel listPanel = new ListPanel();
                    listPanelActionListeners(frame, listPanel, menuPanel);
                    ResultSet table = DataBaseController.employeesListFromDB();
                    int size = DataBaseController.employeesSizeDB();
                    listPanel.position.setText("Pracownik: 1/" + size);
                    try {
                        table.next();
                        listPanel.pesel.setText(table.getString("pesel"));
                        listPanel.name.setText(table.getString("first_name"));
                        listPanel.lastName.setText(table.getString("last_name"));
                        listPanel.job.setText(table.getString("job"));
                        listPanel.team.setText(table.getString("team"));
                        listPanel.salary.setText(table.getString("salary"));
                        listPanel.phone.setText(table.getString("phone_nr"));
                        if (table.getString("job").equals("Handlowiec")) {
                            listPanel.lBonus.setVisible(false);
                            listPanel.lCard.setVisible(false);
                            listPanel.lLimit.setVisible(true);
                            listPanel.lProvision.setVisible(true);
                        } else if (table.getString("job").equals("Dyrektor")) {
                            listPanel.lBonus.setVisible(true);
                            listPanel.lCard.setVisible(true);
                            listPanel.lLimit.setVisible(false);
                            listPanel.lProvision.setVisible(false);
                        } else {
                            listPanel.lBonus.setVisible(false);
                            listPanel.lCard.setVisible(false);
                            listPanel.lLimit.setVisible(false);
                            listPanel.lProvision.setVisible(false);
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                    frame.changeView(listPanel);
                } else if (source == menuPanel.addEmployee) {
                    AddPanel addPanel = new AddPanel();
                    frame.changeView(addPanel);
                    addPanelActionListeners(frame, addPanel, menuPanel);
                } else if (source == menuPanel.exit) {

                }
            }
        };
        menuPanel.list.addActionListener(actionListener);
        menuPanel.addEmployee.addActionListener(actionListener);
        menuPanel.exit.addActionListener(actionListener);
    }

    public static void listPanelActionListeners(MainFrame frame, ListPanel listPanel, MenuPanel menuPanel) {
        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object source = e.getSource();
                if (source == listPanel.returnBtn) {
                    frame.changeView(menuPanel);
                } else if (source == listPanel.nextBtn) {

                } else if (source == listPanel.prevBtn) {

                }

            }
        };
        listPanel.returnBtn.addActionListener(actionListener);
    }

    public static void addPanelActionListeners(MainFrame frame, AddPanel addPanel, MenuPanel menuPanel) {
        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object source = e.getSource();
                if (source == addPanel.returnBtn) {
                    frame.changeView(menuPanel);
                }
            }
        };
        addPanel.returnBtn.addActionListener(actionListener);
    }

    public static void choiceActionListener(AddPanel addPanel, JComboBox choice, JButton addBtn) {
        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object source = e.getSource();
                Object chosen = choice.getSelectedItem();
                if (source == choice) {
                    if (chosen.equals("Dyrektor")) {
                        addPanel.job.setText("Dyrektor");
                        addPanel.job.setEnabled(false);
                        addPanel.lProvision.setVisible(false);
                        addPanel.provision.setVisible(false);
                        addPanel.lLimit.setVisible(false);
                        addPanel.limit.setVisible(false);
                        addPanel.bonus.setVisible(true);
                        addPanel.lBonus.setVisible(true);
                        addPanel.card.setVisible(true);
                        addPanel.lCard.setVisible(true);
                    } else if (chosen.equals("Handlowiec")) {
                        addPanel.job.setText("Handlowiec");
                        addPanel.job.setEnabled(false);
                        addPanel.lProvision.setVisible(true);
                        addPanel.provision.setVisible(true);
                        addPanel.lLimit.setVisible(true);
                        addPanel.limit.setVisible(true);
                        addPanel.bonus.setVisible(false);
                        addPanel.lBonus.setVisible(false);
                        addPanel.card.setVisible(false);
                        addPanel.lCard.setVisible(false);
                    } else {
                        addPanel.job.setText("");
                        addPanel.job.setEnabled(true);
                        addPanel.lProvision.setVisible(false);
                        addPanel.provision.setVisible(false);
                        addPanel.lLimit.setVisible(false);
                        addPanel.limit.setVisible(false);
                        addPanel.bonus.setVisible(false);
                        addPanel.lBonus.setVisible(false);
                        addPanel.card.setVisible(false);
                        addPanel.lCard.setVisible(false);
                    }
                } else if (source == addBtn) {
                    String pesel = addPanel.pesel.getText();
                    String name = addPanel.name.getText();
                    String lname = addPanel.lastName.getText();
                    String job = addPanel.job.getText();
                    String team = addPanel.team.getText();
                    String salary = addPanel.salary.getText();
                    String phone = addPanel.phone.getText();
                    String bonus = addPanel.bonus.getText();
                    String card = addPanel.card.getText();
                    String provision = addPanel.provision.getText();
                    String limit = addPanel.limit.getText();

                    chosen = choice.getSelectedItem();
                    if (chosen.equals(AddPanel.choices[0])) {

                        if (Controller.addEmployeeWithValidation(pesel, name, lname, job, team, salary, phone, bonus, card)) {
                            addPanel.status.setForeground(Color.green);
                            addPanel.status.setText("Dodano pracownika!");
                            addPanel.status.setVisible(true);
                        } else {
                            addPanel.status.setForeground(Color.red);
                            addPanel.status.setText("Nie udało się dodać pracownika(Podano złe dane)!");
                            addPanel.status.setVisible(true);
                        }
                    } else if (chosen.equals(AddPanel.choices[1])) {
                        if (Controller.addEmployeeWithValidation(pesel, name, lname, job, salary, team, phone, provision, limit)) {
                            addPanel.status.setForeground(Color.green);
                            addPanel.status.setText("Dodano pracownika!");
                            addPanel.status.setVisible(true);
                        } else {
                            addPanel.status.setForeground(Color.red);
                            addPanel.status.setText("Nie udało się dodać pracownika(Podano złe dane)!");
                            addPanel.status.setVisible(true);
                        }
                    } else {
                        if (Controller.addEmployeeWithValidation(pesel, name, lname, job, team, salary, phone)) {
                            addPanel.status.setForeground(Color.green);
                            addPanel.status.setText("Dodano pracownika!");
                            addPanel.status.setVisible(true);
                            addPanel.job.setText("");
                        } else {
                            addPanel.status.setForeground(Color.red);
                            addPanel.status.setText("Nie udało się dodać pracownika(Podano złe dane)!");
                            addPanel.status.setVisible(true);

                        }
                    }
                    addPanel.pesel.setText("");
                    addPanel.name.setText("");
                    addPanel.lastName.setText("");
                    addPanel.team.setText("");
                    addPanel.salary.setText("");
                    addPanel.phone.setText("");
                    addPanel.phone.setText("");
                    addPanel.phone.setText("");
                }
            }
        };
        choice.addActionListener(actionListener);
        addBtn.addActionListener(actionListener);
    }
}

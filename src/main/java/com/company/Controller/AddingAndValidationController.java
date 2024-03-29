package com.company.Controller;

import com.company.DB.DataBaseController;
import com.company.Model.Employee;
import com.company.Model.EmployeesList;
import com.company.Model.Manager;
import com.company.Model.Tradesman;
import com.company.View.AddPanel;

import java.math.BigDecimal;
import java.util.Scanner;

public final class AddingAndValidationController {
    static Scanner in = new Scanner(System.in);

    private static boolean peselCheck(char[] p) {

        if (p.length == 11) {
            if ((p[0] + 3 * p[1] + 7 * p[2] + 9 * p[3] + p[4] + 3 * p[5] + 7 * p[6] + 9 * p[7] + p[8] + 3 * p[9] + p[10]) % 10 == 0) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    private static boolean isValueValid(String val) {

        if (!val.equals("")) {
            try {
                BigDecimal newVal = new BigDecimal(val);
            } catch (NumberFormatException e) {
                return false;
            }
            return true;
        } else {
            return false;
        }
    }

    private static boolean isValueValid(String val, int len) {

        if (!val.matches("^[0-9]*$") || val.length() == 0 || val.length() > len) {
            return false;
        }
        return true;
    }

    private static boolean isValueValid(String val, boolean onlyLetters, int len) {

        if (onlyLetters) {
            if (!val.matches("[a-zA-Z- ]+") || val.length() == 0 || val.length() > len) {
                return false;
            }
        } else {
            if (val.length() == 0 || val.length() > len) {
                return false;
            }
        }
        return true;
    }

    public static boolean employeeValidation(String pesel, String name, String lName, String job, String team,
                                             String salary, String phone, boolean update) {
        char[] p = pesel.toCharArray();

        if (team.equals("")) {
            return false;
        }
        int t = Integer.parseInt(team);

        if (!peselCheck(p)) {
            System.out.println("Bledny pesel: ");
            return false;
        }
        if (!isValueValid(name, true, Employee.MAX_NAME_LEN)) {
            return false;
        }
        if (!isValueValid(lName, true, Employee.MAX_LNAME_LEN)) {
            return false;
        }
        if (!isValueValid(job, true, Employee.MAX_JOB_LEN)) {
            return false;
        }
        if (t <= 0) {
            return false;
        }
        if (!isValueValid(salary)) {
            return false;
        }
        if (!isValueValid(phone, false, Employee.MAX_PHONE_LEN)) {
            return false;
        }

        BigDecimal s = new BigDecimal(salary);

        if (update == true) {
            int i = ListController.isEmployeeExists(pesel);
            if (i >= 0) {
                Employee e = EmployeesList.getListOfEmployees().get(i);
                e.updateEmployee(pesel, name, lName, job, t, s, phone);
                if (DataBaseController.updateEmployeeInDB(e, pesel)) {
                    return true;
                } else {
                    return false;
                }
            }
            return false;
        }

        Employee e = new Employee(pesel, name, lName, job, Integer.parseInt(team), new BigDecimal(salary), phone);
        if (DataBaseController.addToDB(e)) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean employeeValidation(String pesel, String name, String lName, String job,
                                             String team, String salary, String phone, String bonusOrProv,
                                             String cardOrlimit, boolean update) {
        char[] p = pesel.toCharArray();

        if (team.equals("")) {
            return false;
        }
        int t = Integer.parseInt(team);

        if (!peselCheck(p)) {
            System.out.println("Bledny pesel: ");
            return false;
        }
        if (!isValueValid(name, true, Employee.MAX_NAME_LEN)) {
            return false;
        }

        if (!isValueValid(lName, true, Employee.MAX_LNAME_LEN)) {
            return false;
        }
        if (!isValueValid(job, false, Employee.MAX_JOB_LEN)) {
            return false;
        }
        if (t <= 0) {
            return false;
        }
        if (!isValueValid(salary)) {
            return false;
        }
        if (!isValueValid(phone, false, Employee.MAX_PHONE_LEN)) {
            return false;
        }
        BigDecimal s = new BigDecimal(salary);

        if (job.equals(AddPanel.choices[0])) {
            if (!isValueValid(bonusOrProv)) {
                return false;
            }
            if (!isValueValid(cardOrlimit, Manager.MAX_CARD_LEN)) {
                return false;
            }

            if (update == true) {
                int i = ListController.isEmployeeExists(pesel);

                if (i >= 0) {
                    Manager m = (Manager) EmployeesList.getListOfEmployees().get(i);

                    if (DataBaseController.updateEmployeeInDB(m, pesel)) {
                        System.out.println(m.getPesel() + " " + pesel);
                        return true;
                    } else {
                        return false;
                    }
                }
                return false;
            }

            Manager m = new Manager(pesel, name, lName, job, t, s, phone, new BigDecimal(bonusOrProv), cardOrlimit);

            if (DataBaseController.addToDB(m)) {
                return true;
            } else {
                return false;
            }
        } else {
            if (!isValueValid(bonusOrProv)) {
                return false;
            }
            if (!isValueValid(cardOrlimit)) {
                return false;
            }

            if (update == true) {
                int i = ListController.isEmployeeExists(pesel);
                if (i > 0) {
                    Tradesman tm = (Tradesman) EmployeesList.getListOfEmployees().get(i);
                    tm.updateEmployee(pesel, name, lName, job, t, s, phone, new BigDecimal(bonusOrProv), new BigDecimal(cardOrlimit));
                    if (DataBaseController.updateEmployeeInDB(tm, pesel)) {
                        return true;
                    } else {
                        return false;
                    }
                }
                return false;
            }

            Tradesman tm = new Tradesman(pesel, name, lName, job, t, s, phone, new BigDecimal(bonusOrProv), new BigDecimal(cardOrlimit));

            if (DataBaseController.addToDB(tm)) {
                return true;
            } else {
                return false;
            }
        }
    }
}

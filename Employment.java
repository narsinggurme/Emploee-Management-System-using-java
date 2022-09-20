//                     Project:"Employee Management System"

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
// import java.io.BufferedOutputStream;
import java.io.Serializable;
// import java.net.SocketPermission;
import java.util.ArrayList;
import java.util.Scanner;

@SuppressWarnings("")
class Employee implements Serializable {

    int id;
    String name;
    float salary;
    long contact_number;
    String email_id;

    public Employee(int id, String name, float salary, long contact_number, String email_id) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.contact_number = contact_number;
        this.email_id = email_id;
    }

    public String toString() {
        return "\nEmployee Details :" + "\n ID: " + this.id + "\n Name: " + this.name + "\nSalary: " + this.salary
                + "\nContact No.:" + this.contact_number + "\nEmail-id: " + this.email_id;
    }

}

public class Employment {
    static void display(ArrayList<Employee> al) {
        System.out.println("\n          -------------Employee List------------\n");
        System.out.println(String.format("%-10s %-15s %-10s %-20s %-10s", "ID", "Name", "Salary", "Contact_No.", "Email_ID"));
        for (Employee e : al) {
            System.out.println(
                    String.format("%-5s %-20s %-10s %-15s %-10s", e.id, e.name, e.salary, e.contact_number, e.email_id));
        }// System.out.printf("%-20s %-20s %20s %n","InterestRate","Monthly Payment","Total Payment\n");               
        // System.out.printf("%-20f %-20.2f %-20.2f\n",r,monthlyPayment,totalPayment);
    }

    @SuppressWarnings("unchecked")
    public static void main(String args[]) {
        int id;
        String name;
        float salary;
        long contact_number;
        String email_id;

        Scanner sc = new Scanner(System.in);
        ArrayList<Employee> al = new ArrayList<Employee>();

        File f = null;
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {

            f = new File("Employment Management  System/database.sql");
            if (f.exists()) {
                fis = new FileInputStream(f);
                ois = new ObjectInputStream(fis);
                al = (ArrayList<Employee>) ois.readObject();
            }
        } catch (Exception exp) {
            System.out.println(exp);
        }

        do {
            System.out.println("\n******* Welcome to the Employee Management System *******\n");
            System.out.println("1). Add Employee to the Database\n" +
                    "2). Search for Employee\n" +
                    "3). Edit Employee Details\n" +
                    "4). Delete Employee Details\n" +
                    "5). Displya All Employee\n" +
                    "6). EXIT\n");
            System.out.println("Enter Your Choice: ");
            int ch = sc.nextInt();

            switch (ch) {
                case 1:
                    System.out.println("\nEnter the Following details to ADD List: \n");
                    System.out.println("Enter ID:  ");
                    id = sc.nextInt();
                    System.out.println("Enter Name : ");
                    name = sc.next();
                    System.out.println("Enter salary : ");
                    salary = sc.nextFloat();
                    System.out.println("Enter Contact no. :  ");
                    contact_number = sc.nextLong();
                    System.out.println("Enter Email_id :  ");
                    email_id = sc.next();
                    al.add(new Employee(id, name, salary, contact_number, email_id));
                    display(al);
                    break;

                case 2:
                    System.out.println("Enter the Employee Id to Search :  ");
                    id = sc.nextInt();
                    int i = 0;
                    for (Employee e : al) {
                        if (id == e.id) {
                            System.out.println(e + "\n");
                            i++;
                        }
                    }
                    if (i == 0) {
                        System.out.println("\nEmployee Details are not found, Please enter a valid ID!!");
                    }
                    break;

                case 3:
                    System.out.println("\nEnter the Employee ID to EDIT the Details");
                    id = sc.nextInt();
                    int j = 0;
                    for (Employee e : al) {
                        if (id == e.id) {
                            j++;
                            do {
                                int ch1 = 0;
                                System.out.println("\nEDIT Employee Details: \n" +
                                        "1). Employee ID\n" +
                                        "2). Name\n" +
                                        "3). Salary\n" +
                                        "4). contact No.\n" +
                                        "5). Email id\n" +
                                        "6). GO Back\n");
                                System.out.println("Enteer your Choice: ");
                                ch1 = sc.nextInt();
                                switch (ch1) {
                                    case 1:
                                        System.out.println("\nEnter Your Employee ID: ");
                                        e.id = sc.nextInt();
                                        System.out.println(e + "\n");
                                        break;

                                    case 2:
                                        System.out.println("Enter your Employee Name: ");
                                        sc.nextInt();
                                        System.out.println(e + "\n");
                                        break;

                                    case 3:
                                        System.out.println("Enter your Employee Salary: ");
                                        e.salary = sc.nextFloat();
                                        System.out.println(e + "\n");
                                        break;

                                    case 4:
                                        System.out.println("Enter new Employee Contact No. : ");
                                        e.contact_number = sc.nextLong();
                                        System.out.println(e + "\n");
                                        break;

                                    case 5:
                                        System.out.println("Enter new Employee Email_ID:  ");
                                        e.email_id = sc.next();
                                        System.out.println(e + "\n");
                                        break;

                                    case 6:
                                        j++;
                                        break;

                                    default:
                                        System.out.println("\nEnter a correct choice from the list");
                                        break;
                                }

                            } while (j == 1);
                        }
                    }
                    if (j == 0) {
                        System.out.println("\nEmployee Details are not available, Please enter a valid ID!!");
                    }

                    break;

                case 4:
                    System.out.println("\nEnter Employee ID to to delete from the database : ");
                    id = sc.nextInt();
                    int k = 0;
                    try {
                        for (Employee e : al) {
                            if (id == e.id) {
                                al.remove(e);
                                display(al);
                                k++;
                            }
                        }
                        if (k == 0) {
                            System.out.println("\nEmployee Details Are Not Available, Please Enter a valid ID!!");
                        }
                    } catch (Exception ex) {
                        System.out.println(ex);
                    }
                    break;

                case 5:
                    try {
                        al = (ArrayList<Employee>) ois.readObject();

                    } catch (ClassNotFoundException e2) {

                        System.out.println(e2);
                    } catch (Exception e2) {

                        System.out.println(e2);
                    }
                    display(al);
                    break;
                case 6:
                    try {
                        fos = new FileOutputStream(f);
                        oos = new ObjectOutputStream(fos);
                        oos.writeObject(al);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }

                    catch (Exception e2) {
                        e2.printStackTrace();
                    } finally {
                        try {
                            fis.close();
                            ois.close();
                            fos.close();
                            oos.close();
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                    }
                    System.out.println("\nYou have Choosen Exit !! Saving Files and Closing the Tool.");
                    sc.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("\nEnter a correct choice From the List");
                    break;
            }
        } while (true);
    }

}
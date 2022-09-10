package org.example.interfaces;

import org.example.base.User;

import java.util.HashMap;

public interface iStaff {
    String registerNewCustomer(HashMap<String , User> customerList, HashMap<String , User> staffList,  String staffEmail, String password,  String name, String email, String mobileNumber, String accountNumber );

    String registerStaff(HashMap<String , User> staff, String name, String email, String mobileNumber);



    boolean verifyAccountNumber(String accountNumber);
}

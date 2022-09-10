package services;

import org.example.base.User;
import org.example.services.CustomerImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class CustomerImplTest {

    CustomerImpl customerService;
    @BeforeEach
    void setUp() {
        this.customerService = new CustomerImpl();
    }

    @org.junit.jupiter.api.Test
    void queryStaffList_Actual_Customer() {
        //Given
        HashMap<String , User> customerList = new HashMap<>();
        // User testStaff  = new User("test" , "test@gmail.com" , "09055589964");
        User testCustomer = new User( "Akorede" , "akorede@gmail.com", "12345" , "09055589964" , "12345");
        customerList.put("akorede@gmail.com" , testCustomer);

        //when
        var actual = customerService.queryCustomerList(customerList , "akorede@gmail.com");

        assertEquals(testCustomer ,actual);
    }

    @org.junit.jupiter.api.Test
    void queryStaffList_Null_Customer() {
        //Given
        HashMap<String , User> customerList = new HashMap<>();
        // User testStaff  = new User("test" , "test@gmail.com" , "09055589964");
        User testCustomer = new User( "Akorede" , "hameedkorede@gmail.com", "12345" , "09055589964" , "12345");
        customerList.put("hameedkorede@gmail.com" , testCustomer);

        //when
        var actual = customerService.queryCustomerList(customerList , "akorede@gmail.com");

        assertNull(actual);
    }

    /**Withdraw successfull
    insufficient Balance
    Unauthorized
    Only Customers Can Withdraw
     */
    @Test
    void withdraw_Customer_notFound() {
        //Given
        HashMap<String, User> customerList = new HashMap<>();
        User testCustomer = new User( "Akorede" ,  "12345678912" ,  "akorede@gmail.com" , "12345" , "09055589964" );

        //When
        var expected = "Only Customers Can Withdraw";
        var actual = customerService.withdraw(customerList , "akorede@gmail.com" , 700);
        assertEquals(expected , actual);

    }

    @Test
    void withdraw_Customer_Unauthorized() {
        //Given
        HashMap<String, User> customerList = new HashMap<>();
        User testCustomer = new User( "Akorede" ,  "12345678912" ,  "akorede@gmail.com" , "12345" , "09055589964" );
        customerList.put("akorede@gmail.com" , testCustomer);
        //When
        var expected = "Unauthorized";
        var actual = customerService.withdraw(customerList , "akorede@gmail.com" , 700);
        assertEquals(expected , actual);


    }

    @Test
    void withdraw_Customer_Insufficient_Balance() {
        //Given
        HashMap<String, User> customerList = new HashMap<>();
        User testCustomer = new User( "Akorede" ,  "12345678912" ,  "akorede@gmail.com" , "12345" , "09055589964" );
        customerList.put("akorede@gmail.com" , testCustomer);
        testCustomer.setLoggedIn(true);
        //When
        var expected = "insufficient Balance";
        var actual = customerService.withdraw(customerList , "akorede@gmail.com" , 700);
        assertEquals(expected , actual);


    }
    @Test
    void withdraw_Customer_Successfull() {
        //Given
        HashMap<String, User> customerList = new HashMap<>();
        User testCustomer = new User( "Akorede" ,  "12345678912" ,  "akorede@gmail.com" , "12345" , "09055589964" );
        customerList.put("akorede@gmail.com" , testCustomer);
        testCustomer.setLoggedIn(true);
        testCustomer.setAccountBalance(750);

        //When
        var expected = "Withdraw successfull";
        var actual = customerService.withdraw(customerList , "akorede@gmail.com" , 700);
        assertEquals(expected , actual);

    }

    @Test

    void withdraw_Customer_notFoundForDeposit() {
        //Given
        HashMap<String, User> customerList = new HashMap<>();
        User testCustomer = new User("Akorede", "12345678912", "akorede@gmail.com", "12345", "09055589964");

        //When
        var expected = "Only Customers Can deposit";
        var actual = customerService.deposit(customerList, "akorede@gmail.com", 700);
        assertEquals(expected, actual);
    }

    @Test
    void withdraw_Customer_UnauthorizedDeposit() {
        //Given
        HashMap<String, User> customerList = new HashMap<>();
        User testCustomer = new User( "Akorede" ,  "12345678912" ,  "akorede@gmail.com" , "12345" , "09055589964" );
        customerList.put("akorede@gmail.com" , testCustomer);
        //When
        var expected = "Unauthorized";
        var actual = customerService.deposit(customerList , "akorede@gmail.com" , 700);
        assertEquals(expected , actual);


    }

    @Test
    void withdraw_Customer_Deposit_Successfull() {
        //Given
        HashMap<String, User> customerList = new HashMap<>();
        User testCustomer = new User( "Akorede" ,  "12345678912" ,  "akoredegmail.com" , "12345" , "09055589964" );
        customerList.put("akorede@gmail.com" , testCustomer);
        testCustomer.setLoggedIn(true);
        //When
        var expected = "deposit successfull";
        var actual = customerService.deposit(customerList , "akorede@gmail.com" , 700);
        assertEquals(expected , actual);

    }

    /**Sender Not Found
    unauthorized
    Receiver Not Found
    Insufficient Funds
     */
    @Test
    void transfer_Sender_Not_Found() {
        HashMap<String, User> customerList = new HashMap<>();
        User testCustomerSender = new User( "Akorede" ,  "12345678912" ,  "akorede@gmail.com" , "12345" , "09055589964" );
        User testCustomerReceiver = new User( "charles" ,  "12345678912" ,  "hameedkorede@gmail.com" , "12345" , "09055589964" );
        // customerList.put("akorede@gmail.com" , testCustomerSender);

        //when
        var expected = "Sender Not Found";
        var actual = customerService.transfer(customerList, "akorede@gmail.com" , "hameedkorede@gmail.com" , 700);
        assertEquals(expected , actual);
    }

    @Test
    void transfer_Unauthenticated() {
        HashMap<String, User> customerList = new HashMap<>();
        User testCustomerSender = new User( "Akorede" ,  "12345678912" ,  "akorede@gmail.com" , "12345" , "09055589964" );
        User testCustomerReceiver = new User( "charles" ,  "12345678912" ,  "hameedkorede@gmail.com" , "12345" , "09055589964" );
        customerList.put("akorede@gmail.com" , testCustomerSender);

        //when
        var expected = "unauthorized";
        var actual = customerService.transfer(customerList, "akorede@gmail.com" , "hameedkorede@gmail.com" , 700);
        assertEquals(expected , actual);
    }
    @Test
    void transfer_Receiver_Not_Found() {
        HashMap<String, User> customerList = new HashMap<>();
        User testCustomerSender = new User( "Akorede" ,  "12345678912" ,  "akorede@gmail.com" , "12345" , "09055589964" );
        User testCustomerReceiver = new User( "charles" ,  "12345678912" ,  "hameedkorede@gmail.com" , "12345" , "09055589964" );
        customerList.put("akorede@gmail.com" , testCustomerSender);
        testCustomerSender.setLoggedIn(true);

        //when
        var expected = "Receiver Not Found";
        var actual = customerService.transfer(customerList, "akorede@gmail.com" , "hameedkorede@gmail.com" , 700);
        assertEquals(expected , actual);
    }

    @Test
    void transfer_Insufficient_Fund() {
        HashMap<String, User> customerList = new HashMap<>();
        User testCustomerSender = new User( "Akorede" ,  "12345678912" ,  "akorede@gmail.com" , "12345" , "09055589964" );
        User testCustomerReceiver = new User( "charles" ,  "12345678912" ,  "hameedkorede@gmail.com", "12345" , "09055589964" );
        customerList.put("akorede@gmail.com" , testCustomerSender);
        customerList.put("hameedkorede@gmail.com" , testCustomerReceiver);
        testCustomerSender.setLoggedIn(true);

        //when
        var expected = "Insufficient Funds";
        var actual = customerService.transfer(customerList, "akorede@gmail.com" , "hameedkorede@gmail.com" , 700);
        assertEquals(expected , actual);
    }

    @Test
    void transfer_Successfull() {
        HashMap<String, User> customerList = new HashMap<>();
        User testCustomerSender = new User( "Akorede" ,  "12345678912" ,  "akorede@gmail.com" , "12345" , "09055589964" );
        User testCustomerReceiver = new User( "charles" ,  "12345678912" ,  "hameedkorede@gmail.com", "12345" , "09055589964" );
        customerList.put("akorede@gmail.com" , testCustomerSender);
        customerList.put("hameedkorede@gmail.com" , testCustomerReceiver);
        testCustomerSender.setLoggedIn(true);
        testCustomerSender.setAccountBalance(1000);

        //when
        var expected = "successfull";
        var actual = customerService.transfer(customerList, "akorede@gmail.com" , "hameedkorede@gmail.com" , 700);
        assertEquals(expected , actual);
    }


    //Incorrect Email
    //User Not Found
    //incorrect password
    //successfull
    @Test
    void login_incorrect_email() {
        HashMap<String, User> customerList = new HashMap<>();
        User testCustomerSender = new User( "Akorede" ,  "12345678912" ,  "akorede@gmail.com" , "12345" , "09055589964" );
        customerList.put("akorede@gmail.com" , testCustomerSender);

        //when
        var expected = "Incorrect Email";
        var actual = customerService.login(customerList , "akorede@gmail.com" , "12345");
        assertEquals(expected , actual);
    }

    @Test
    void login_User_Not_Found() {
        HashMap<String, User> customerList = new HashMap<>();
        User testCustomerSender = new User( "Akorede" ,  "12345678912" ,  "akorede@gmail.com" , "12345" , "09055589964" );
        // customerList.put("akorede@gmail.com" , testCustomerSender);

        //when
        var expected = "User Not Found";
        var actual = customerService.login(customerList , "akorede@gmail.com" , "12345");
        assertEquals(expected , actual);
    }

    @Test
    void login_Incorrect_Password() {
        HashMap<String, User> customerList = new HashMap<>();
        User testCustomerSender = new User( "Akorede" ,  "12345678912" ,  "akorede@gmail.com" , "12345" , "09055589964" );
        customerList.put("akorede@gmail.com" , testCustomerSender);

        //when
        var expected = "incorrect password";
        var actual = customerService.login(customerList , "akorede@gmail.com" , "145");
        assertEquals(expected , actual);
    }

    @Test
    void login_Successfull() {
        HashMap<String, User> customerList = new HashMap<>();
        User testCustomerSender = new User( "Akorede" ,  "12345678912" ,  "akorede@gmail.com" , "12345" , "09055589964" );
        customerList.put("akorede@gmail.com" , testCustomerSender);

        //when
        var expected = "successfull";
        var actual = customerService.login(customerList , "akorede@gmail.com" , "12345");
        assertEquals(expected , actual);
    }
}

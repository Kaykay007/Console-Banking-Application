package services;

import org.example.base.User;
import org.example.enumerations.Role;
import org.example.models.Bank;
import org.example.services.StaffImpl;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class StaffImplTest {

    StaffImpl staffTest;
    Bank bank;
    /** User testCustomer;
     * User testStaff;
     */

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        this.staffTest = new StaffImpl();
        this.bank = new Bank();
        //this.testCustomer = new User()
    }

    @org.junit.jupiter.api.Test
    void registerNewCustomer_Staff_Not_Found() {
        /**Given
         *
         */
        HashMap<String , User> staffList = new HashMap<>();
        HashMap<String , User> customerList = new HashMap<>();
        //when
        var expected = "staff not found";
        var actual = staffTest.registerNewCustomer(customerList , staffList , "hameedkorede@gmail.com" , "Akorede" , "akorede@gmail.com", "12345" , "09055589964" , "12345");
        assertEquals(expected , actual);
    }

    @org.junit.jupiter.api.Test
    void registerNewCustomer_Staff_Role_Not_Authorized() {
        //Given
        HashMap<String , User> staffList = new HashMap<>();
        HashMap<String , User> customerList = new HashMap<>();
        User testStaff  = new User("test" , "test@gmail.com" , "09055589964");
        staffList.put("test@gmail.com" , testStaff);
        //when
        var expected = "Unauthorized Operation";
        var actual = staffTest.registerNewCustomer(customerList , staffList , "test@gmail.com" , "Akorede" , "akorede@gmail.com", "12345" , "09055589964" , "12345");
        assertEquals(expected , actual);
    }

    @org.junit.jupiter.api.Test
    void registerNewCustomer_Invalid_Email() {
        //Given
        HashMap<String , User> staffList = new HashMap<>();
        HashMap<String , User> customerList = new HashMap<>();
        User testStaff  = new User("test" , "test@gmail.com" , "09055589964");
        testStaff.setRole(Role.STAFF);
        staffList.put("test@gmail.com" , testStaff);
        //when
        var expected = "invalid email";
        var actual = staffTest.registerNewCustomer(customerList , staffList , "test@gmail.com" , "Akorede" , "akorede@gmail.com", "12345" , "09055589964" , "12345");
        assertEquals(expected , actual);
    }


    @org.junit.jupiter.api.Test
    void registerNewCustomer_Invalid_Account_Number() {
        //Given
        HashMap<String , User> staffList = new HashMap<>();
        HashMap<String , User> customerList = new HashMap<>();
        User testStaff  = new User("test" , "test@gmail.com" , "09055589964");
        testStaff.setRole(Role.STAFF);
        staffList.put("test@gmail.com" , testStaff);
        //when
        var expected = "invalid account number";
        var actual = staffTest.registerNewCustomer(customerList , staffList , "test@gmail.com" , "Akorede" , "akorede@gmail.com", "12345" , "09055589964" , "12345");
        assertEquals(expected , actual);
    }

    @org.junit.jupiter.api.Test
    void registerNewCustomer_User_Already_Exists() {
        //Given
        HashMap<String , User> staffList = new HashMap<>();
        HashMap<String , User> customerList = new HashMap<>();
        User testStaff  = new User("test" , "test@gmail.com" , "09055589964");
        User testCustomer = new User( "Akorede" , "akorede@gmail.com", "12345" , "09055589964" , "12345");
        customerList.put("akorede@gmail.com" , testCustomer);
        testStaff.setRole(Role.STAFF);
        staffList.put("test@gmail.com" , testStaff);
        //when
        var expected = "User Already Exists";
        var actual = staffTest.registerNewCustomer(customerList , staffList , "test@gmail.com" , "Akorede" , "akorede@gmail.com", "12345" , "09055589964" , "12345876547");
        assertEquals(expected , actual);
    }

    @org.junit.jupiter.api.Test
    void registerNewCustomer_User_Registered_Successfully() {
        //Given
        HashMap<String , User> staffList = new HashMap<>();
        HashMap<String , User> customerList = new HashMap<>();
        User testStaff  = new User("test" , "test@gmail.com" , "09055589964");
        //User testCustomer = new User( "Akorede" , "akorede@gmail.com", "12345" , "09055589964" , "12345");
        //customerList.put("akorede@gmail.com" , testCustomer);
        testStaff.setRole(Role.STAFF);
        staffList.put("test@gmail.com" , testStaff);
        //when
        var expected = "successfully Registered";
        var actual = staffTest.registerNewCustomer(customerList , staffList , "test@gmail.com" , "Akorede" , "akorede@gmail.com", "12345" , "09055589964" , "12345876547");
        assertEquals(expected , actual);
    }

    @org.junit.jupiter.api.Test
    void registerStaff_invalid_Email() {
        //Given
        HashMap<String , User> staffList = new HashMap<>();
        //when
        var expected = "invalid email";
        var actual = staffTest.registerStaff(staffList, "Akorede" , "hameedkorede@gmail.com" , "09055589964");
        assertEquals(expected , actual);
    }

    @org.junit.jupiter.api.Test
    void registerStaff_User_Already_eXISTS() {
        //Given
        HashMap<String , User> staffList = new HashMap<>();
        staffList.put("hameedkorede@gmail.com" , new User("Akorede" , "12345678910" , "hameedkorede@gmail.com" ,  "Fbiswa", "09055589964"));
        //when
        var expected = "user Already Exits";
        var actual = staffTest.registerStaff(staffList, "Akorede" , "hameedkorede@gmail.com", "09055589964");
        assertEquals(expected , actual);
    }

    @org.junit.jupiter.api.Test
    void registerStaff_Successful() {
        //Given
        HashMap<String , User> staffList = new HashMap<>();
        staffList.put("hameedkorede@gmail.com" , new User("Akorede" , "12345678910" , "hameedkorede@gmail.com" ,  "Fbiswa", "09055589964"));
        //when
        var expected = "successfully Registered";
        var actual = staffTest.registerStaff(staffList, "Akorede" , "nas@gmail.com" , "09055589964");
        assertEquals(expected , actual);
    }

    @org.junit.jupiter.api.Test
    void verifyEmail_true() {
        //when
        var actual = StaffImpl.
                verifyEmail("akorede@gmail.com");
        assertTrue(actual);
    }
    @org.junit.jupiter.api.Test
    void verifyEmail_false() {
        //when
        var actual = StaffImpl.
                verifyEmail("akorede@gmail.com");
        assertFalse(actual);
    }

    @org.junit.jupiter.api.Test
    void verifyAccountNumber_Correct_Format() {
        //when
        var actual = staffTest.verifyAccountNumber("12345678908");
        assertTrue(actual);
    }

    @org.junit.jupiter.api.Test
    void verifyAccountNumber_Wrong_Format() {
        //when
        var actual = staffTest.verifyAccountNumber("12345");
        assertFalse(actual);
    }


    @org.junit.jupiter.api.Test
    void queryStaffList_Actual_Customer() {
        //Given
        HashMap<String , User> customerList = new HashMap<>();
        // User testStaff  = new User("test" , "test@gmail.com" , "09055589964");
        User testCustomer = new User( "Akorede" , "akorede@gmail.com", "12345" , "09055589964" , "12345");
        customerList.put("akorede@gmail.com" , testCustomer);

        //when
        var actual = staffTest.queryStaffList(customerList , "akorede@gmail.com");

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
        var actual = staffTest.queryStaffList(customerList , "akorede@gmail.com");

        assertNull(actual);
    }



}

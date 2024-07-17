package com.example.vhsrental;

import com.example.vhsrental.controller.RentalControllerTest;
import com.example.vhsrental.controller.UserControllerTest;
import com.example.vhsrental.controller.VHSControllerTest;
import com.example.vhsrental.service.RentalServiceImplTest;
import com.example.vhsrental.service.UserServiceImplTest;
import com.example.vhsrental.service.VHSServiceImplTest;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({
        RentalControllerTest.class,
        UserControllerTest.class,
        VHSControllerTest.class,
        RentalServiceImplTest.class,
        UserServiceImplTest.class,
        VHSServiceImplTest.class
})
public class AllTestsSuite {
}

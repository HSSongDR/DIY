import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class SCHTest {


    private static ByteArrayOutputStream out = new ByteArrayOutputStream();
    private static ByteArrayOutputStream err = new ByteArrayOutputStream();
    private static PrintStream originalOut = System.out;
    private static PrintStream originalErr = System.err;

    private static DB employeeDB;

    @BeforeAll
    public static void setStreams() {
        System.setOut(new PrintStream(out));
        System.setErr(new PrintStream(err));
    }

    @AfterEach
    public void restoreInitialStreams() {
        System.out.close();
    }

    @BeforeAll
    public static void initTest(){
        EmployeeInfo e1 = new EmployeeInfo("15123099","VXIHXOTH JHOP","CL3","010-3112-2609","19771211","ADV");
        EmployeeInfo e2 = new EmployeeInfo("17112609","FB NTAWR","CL4","010-5645-6122","19861203","PRO");
        EmployeeInfo e3 = new EmployeeInfo("18115040","TTETHU HBO","CL3","010-4581-2050","20080718","ADV");
        EmployeeInfo e4 = new EmployeeInfo("88114052","NQ LVARW","CL4","010-4528-3059","19911021","PRO");
        EmployeeInfo e5 = new EmployeeInfo("19129568","SRERLALH HMEF","CL2","010-3091-9521","19640910","PRO");
        EmployeeInfo e6 = new EmployeeInfo("17111236","VSID TVO","CL1","010-3669-1077","20120718","PRO");
        EmployeeInfo e7 = new EmployeeInfo("18117906","TWU QSOLT","CL4","010-6672-7186","20030413","PRO");
        EmployeeInfo e8 = new EmployeeInfo("08123556","WN XV","CL1","010-7986-5047","20100614","PRO");
        EmployeeInfo e9 = new EmployeeInfo("02117175","SBILHUT LDEXRI","CL4","010-2814-1699","19950704","ADV");
        EmployeeInfo e10 = new EmployeeInfo("03113260","HH LTUPF","CL2","010-5798-5383","19791018","PRO");
        EmployeeInfo e11 = new EmployeeInfo("14130827","RPO JK","CL4","010-3231-1698","20090201","ADV");
        EmployeeInfo e12 = new EmployeeInfo("01122329","DN WD","CL4","010-7174-5680","20071117","PRO");
        EmployeeInfo e13 = new EmployeeInfo("08108827","RTAH VNUP","CL4","010-9031-2726","19780417","ADV");
        EmployeeInfo e14 = new EmployeeInfo("85125741","FBAH RTIJ","CL1","010-8900-1478","19780228","ADV");
        EmployeeInfo e15 = new EmployeeInfo("08117441","BMU MPOSXU","CL3","010-2703-3153","20010215","ADV");
        EmployeeInfo e16 = new EmployeeInfo("10127115","KBU MHU","CL3","010-3284-4054","19660814","ADV");
        EmployeeInfo e17 = new EmployeeInfo("12117017","LFIS JJIVL","CL1","010-7914-4067","20120812","PRO");
        EmployeeInfo e18 = new EmployeeInfo("11125777","TKOQKIS HC","CL1","010-6734-2289","19991001","PRO");
        EmployeeInfo e19 = new EmployeeInfo("11109136","QKAHCEX LTODDO","CL4","010-2627-8566","19640130","PRO");
        EmployeeInfo e20 = new EmployeeInfo("05101762","VCUHLE HMU","CL4","010-3988-9289","20030819","PRO");

        employeeDB = new DB();
        employeeDB.ADD(e1);
        employeeDB.ADD(e2);
        employeeDB.ADD(e3);
        employeeDB.ADD(e4);
        employeeDB.ADD(e5);
        employeeDB.ADD(e6);
        employeeDB.ADD(e7);
        employeeDB.ADD(e8);
        employeeDB.ADD(e9);
        employeeDB.ADD(e10);
        employeeDB.ADD(e11);
        employeeDB.ADD(e12);
        employeeDB.ADD(e13);
        employeeDB.ADD(e14);
        employeeDB.ADD(e15);
        employeeDB.ADD(e16);
        employeeDB.ADD(e17);
        employeeDB.ADD(e18);
        employeeDB.ADD(e19);
        employeeDB.ADD(e20);
    }

    @Test
    void SCH_test1(){
        employeeDB.SCH("-p","","","employeeNum","17112609");
        assertEquals("FB NTAWR,CL4,010-5645-6122,19861203,PRO", out.toString());
        
    }
    @Test
    void SCH_test2(){
        employeeDB.SCH("","","","birthday","19810630");
        assertEquals("2", out.toString());
    }

    @Test
    void SCH_test3(){
        employeeDB.SCH("","","","employeeNum","21040123");
        assertEquals("NONE", out.toString());
    }

}

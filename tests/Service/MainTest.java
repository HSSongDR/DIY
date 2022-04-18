package Service;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;

import org.junit.jupiter.api.BeforeAll;



class MainTest {



    @BeforeAll
    static void init() {

        DB.employeeHM = new HashMap<>();
        DB.searchHM = new HashMap<>();

        //m.employeeHM.put("18115040", new EmployeeInfo("18115040","TTETHU HBO","CL3","010-4581-2050","20080718","ADV"));
        DB.ADD("ADD, , , ,15123099,VXIHXOTH JHOP,CL3,010-3112-2609,19771211,ADV".split(","));
        DB.ADD("ADD, , , ,17112609,FB NTAWR,CL4,010-5645-6122,19861203,PRO".split(","));
        DB.ADD("ADD, , , ,18115040,TTETHU HBO,CL3,010-4581-2050,20080718,ADV".split(","));
        DB.ADD("ADD, , , ,88114052,NQ LVARW,CL4,010-4528-3059,19911021,PRO".split(","));
        DB.ADD("ADD, , , ,19129568,SRERLALH HMEF,CL2,010-3091-9521,19640910,PRO".split(","));
        DB.ADD("ADD, , , ,17111236,VSID TVO,CL1,010-3669-1077,20120718,PRO".split(","));
        DB.ADD("ADD, , , ,18117906,TWU QSOLT,CL4,010-6672-7186,20030413,PRO".split(","));
        DB.ADD("ADD, , , ,08123556,WN XV,CL1,010-7986-5047,20100614,PRO".split(","));
        DB.ADD("ADD, , , ,02117175,SBILHUT LDEXRI,CL4,010-2814-1699,19950704,ADV".split(","));
        DB.ADD("ADD, , , ,03113260,HH LTUPF,CL2,010-5798-5383,19791018,PRO".split(","));
        DB.ADD("ADD, , , ,14130827,RPO JK,CL4,010-3231-1698,20090201,ADV".split(","));
        DB.ADD("ADD, , , ,01122329,DN WD,CL4,010-7174-5680,20071117,PRO".split(","));
        DB.ADD("ADD, , , ,08108827,RTAH VNUP,CL4,010-9031-2726,19780417,ADV".split(","));
        DB.ADD("ADD, , , ,85125741,FBAH RTIJ,CL1,010-8900-1478,19780228,ADV".split(","));
        DB.ADD("ADD, , , ,08117441,BMU MPOSXU,CL3,010-2703-3153,20010215,ADV".split(","));
        DB.ADD("ADD, , , ,10127115,KBU MHU,CL3,010-3284-4054,19660814,ADV".split(","));
        DB.ADD("ADD, , , ,12117017,LFIS JJIVL,CL1,010-7914-4067,20120812,PRO".split(","));
        DB.ADD("ADD, , , ,11125777,TKOQKIS HC,CL1,010-6734-2289,19991001,PRO".split(","));
        DB.ADD("ADD, , , ,11109136,QKAHCEX LTODDO,CL4,010-2627-8566,19640130,PRO".split(","));
        DB.ADD("ADD, , , ,05101762,VCUHLE HMU,CL4,010-3988-9289,20030819,PRO".split(","));
    }



    @Test
    void AddTest() {

        String tmp = "ADD, , , ,18115040,TTETHU HBO,CL3,010-4581-2050,20080718,ADV";
        String [] temp = tmp.split(",");

        assertEquals("20", DB.employeeHM.size()+"");

    }

    @Test
    void DelTest() {


        String t1 = "DEL, , , ,employeeNum,18115040";
        String t2 = "DEL,-p, , ,employeeNum,18115040";
        String t3 = "DEL,-p, , ,employeeNum,18115040";

        assertEquals("DEL,1", DB.DEL(t1.split(",")).toString().trim());
        assertEquals("DEL,NONE", DB.DEL(t2.split(",")).toString().trim());

    }


    @Test
    void ModTest() {


        String t1 = "MOD,-p, , ,name,FB NTAWR,birthday,20050520";
        String t2 = "MOD,-p, , ,name,FB NTAWR,cl,CL3";
        String t3 = "MOD,-p, , ,employeeNum,08123556,birthday,20110706";
        String t4 = "MOD,-p, , ,employeeNum,18115040,birthday,12345678";



        assertEquals("MOD,17112609,FB NTAWR,CL4,010-5645-6122,19861203,PRO", DB.MOD(t1.split(",")).toString().trim());
        //Main.employeeHM.get("17112609").setBirthday("20050520");
        assertEquals("MOD,17112609,FB NTAWR,CL4,010-5645-6122,20050520,PRO", DB.MOD(t2.split(",")).toString().trim());
        assertEquals("MOD,08123556,WN XV,CL1,010-7986-5047,20100614,PRO", DB.MOD(t3.split(",")).toString().trim());
        assertEquals("MOD,18115040,TTETHU HBO,CL3,010-4581-2050,20080718,ADV", DB.MOD(t4.split(",")).toString().trim());
        assertEquals("19", DB.employeeHM.size()+"");

    }


    @Test
    void SchTest() {

        String t1 = "SCH,-p,-d, ,birthday,04";
        String t2 = "SCH, , , ,employeeNum,79110836";

        assertEquals("SCH,02117175,SBILHUT LDEXRI,CL4,010-2814-1699,19950704,ADV", DB.SCH(t1.split(",")).toString().trim());
        assertEquals("SCH,NONE", DB.SCH(t2.split(",")).toString().trim());


    }

}

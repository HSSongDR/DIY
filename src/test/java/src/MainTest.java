package src;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controller.EmployeeController;
import service.EmployeeAddServiceImpl;
import service.EmployeeDelServiceImpl;
import service.EmployeeModServiceImpl;
import service.EmployeeSchServiceImpl;
import service.EmployeeService;


class MainTest {
	
	
	
	@BeforeEach
	void init() {
		
		
		EmployeeService DB = new EmployeeAddServiceImpl();
		
		EmployeeService.employeeHM = new HashMap<>();
		EmployeeService.searchHM = new HashMap<>();
		
		//m.employeeHM.put("18115040", new EmployeeInfo("18115040","TTETHU HBO","CL3","010-4581-2050","20080718","ADV"));
		DB.run("ADD, , , ,15123099,VXIHXOTH JHOP,CL3,010-3112-2609,19771211,ADV".split(","));
		DB.run("ADD, , , ,17112609,FB NTAWR,CL4,010-5645-6122,19861203,PRO".split(","));
		DB.run("ADD, , , ,18115040,TTETHU HBO,CL3,010-4581-2050,20080718,ADV".split(","));
		DB.run("ADD, , , ,88114052,NQ LVARW,CL4,010-4528-3059,19911021,PRO".split(","));
		DB.run("ADD, , , ,19129568,SRERLALH HMEF,CL2,010-3091-9521,19640910,PRO".split(","));
		DB.run("ADD, , , ,17111236,VSID TVO,CL1,010-3669-1077,20120718,PRO".split(","));
		DB.run("ADD, , , ,18117906,TWU QSOLT,CL4,010-6672-7186,20030413,PRO".split(","));
		DB.run("ADD, , , ,08123556,WN XV,CL1,010-7986-5047,20100614,PRO".split(","));
		DB.run("ADD, , , ,02117175,SBILHUT LDEXRI,CL4,010-2814-1699,19950704,ADV".split(","));
		DB.run("ADD, , , ,03113260,HH LTUPF,CL2,010-5798-5383,19791018,PRO".split(","));
		DB.run("ADD, , , ,14130827,RPO JK,CL4,010-3231-1698,20090201,ADV".split(","));
		DB.run("ADD, , , ,01122329,DN WD,CL4,010-7174-5680,20071117,PRO".split(","));
		DB.run("ADD, , , ,08108827,RTAH VNUP,CL4,010-9031-2726,19780417,ADV".split(","));
		DB.run("ADD, , , ,85125741,FBAH RTIJ,CL1,010-8900-1478,19780228,ADV".split(","));
		DB.run("ADD, , , ,08117441,BMU MPOSXU,CL3,010-2703-3153,20010215,ADV".split(","));
		DB.run("ADD, , , ,10127115,KBU MHU,CL3,010-3284-4054,19660814,ADV".split(","));
		DB.run("ADD, , , ,12117017,LFIS JJIVL,CL1,010-7914-4067,20120812,PRO".split(","));
		DB.run("ADD, , , ,11125777,TKOQKIS HC,CL1,010-6734-2289,19991001,PRO".split(","));
		DB.run("ADD, , , ,11109136,QKAHCEX LTODDO,CL4,010-2627-8566,19640130,PRO".split(","));
		DB.run("ADD, , , ,05101762,VCUHLE HMU,CL4,010-3988-9289,20030819,PRO".split(","));
		
		DB.run("ADD, , , ,01345678,VSID TVO,CL1,010-3669-1077,20120718,PRO".split(","));
		DB.run("ADD, , , ,05222222,VSID TVO,CL1,010-3669-1077,20120718,PRO".split(","));
		DB.run("ADD, , , ,19333333,VSID TVO,CL1,010-3669-1077,20120718,PRO".split(","));
		DB.run("ADD, , , ,20444444,VSID TVO,CL1,010-3669-1077,20120718,PRO".split(","));
		DB.run("ADD, , , ,96555555,VSID TVO,CL1,010-3669-1077,20120718,PRO".split(","));
		DB.run("ADD, , , ,99666666,VSID TVO,CL1,010-3669-1077,20120718,PRO".split(","));
		
		DB.run("ADD, , , ,99000000,N DY,CL1,010-3669-1077,20120718,PRO".split(","));
		DB.run("ADD, , , ,99999999,N DY,CL1,010-3669-1077,20120718,PRO".split(","));
		DB.run("ADD, , , ,99000001,N DY,CL1,010-3669-1077,20120718,PRO".split(","));
		DB.run("ADD, , , ,00000000,N DY,CL1,010-3669-1077,20120718,PRO".split(","));
		DB.run("ADD, , , ,00000001,N DY,CL1,010-3669-1077,20120718,PRO".split(","));
		
	}
	
	
	
	@Test
	void AddTest() {
		

		String tmp = "ADD, , , ,18115040,TTETHU HBO,CL3,010-4581-2050,20080718,ADV";
		
		assertEquals("31", EmployeeService.employeeHM.size()+"");
		 
	}
	
	
	@Test
	void SchManyItemTest() {
		
		EmployeeService DB = new EmployeeAddServiceImpl();
		EmployeeService SCH = new EmployeeSchServiceImpl();
		EmployeeService MOD = new EmployeeModServiceImpl();
		EmployeeService DEL = new EmployeeDelServiceImpl();	
		                     
		for(int i=10000000;i<10005000;i++) {
			// 5000개 INSERT
			String str = "ADD, , , ,"+i+",TEMP TEMP,CL1,010-3669-1077,99990101,PRO";
			DB.run(str.split(","));
		}
		
		String tmp =  "SCH, , , ,name,TEMP TEMP";	
		assertEquals("SCH,5000",  SCH.run(tmp.split(",")).toString().trim());
		
		int birth = 20120718;
		int cnt = 1 ;
		for(int i=10002500;i<10005000;i++) {
			// 5000개 INSERT
			String str = "MOD, , , ,employeeNum,"+i+",birthday,"+birth+cnt;
			MOD.run(str.split(","));
			cnt++;
		}
		
		tmp = "SCH, , , ,birthday,99990101";	
		
		assertEquals("SCH,2500",  SCH.run(tmp.split(",")).toString().trim());
		assertEquals("SCH,2500",  SCH.run(tmp.split(",")).toString().trim());
		
	
		for(int i=10002501;i<10005000;i++) {
			// 5000개 INSERT
			String str = "DEL, , , ,employeeNum,"+i;
			DEL.run(str.split(","));
			cnt++;
		}
		
		
		tmp =  "SCH, , , ,name,TEMP TEMP";
		
		assertEquals("SCH,2501",  SCH.run(tmp.split(",")).toString().trim());
		 
	}
	
	@Test
	void DelTest() {
		
		EmployeeService DB = new EmployeeDelServiceImpl();
		
		String t1 = "DEL, , , ,employeeNum,18115040";
		String t2 = "DEL,-p, , ,employeeNum,18115040";
		String t3 = "DEL,-p, , ,employeeNum,18115040";
		
		String t4 = "DEL,-p, , ,name,VSID TVO";
		
		//assertEquals("DEL,1", DB.run(t1.split(",")).toString().trim());
		//assertEquals("DEL,NONE", DB.run(t2.split(",")).toString().trim());
		assertEquals("DEL,96555555,VSID TVO,CL1,010-3669-1077,20120718,PRO\n"
				+ "DEL,99666666,VSID TVO,CL1,010-3669-1077,20120718,PRO\n"
				+ "DEL,01345678,VSID TVO,CL1,010-3669-1077,20120718,PRO\n"
				+ "DEL,05222222,VSID TVO,CL1,010-3669-1077,20120718,PRO\n"
				+ "DEL,17111236,VSID TVO,CL1,010-3669-1077,20120718,PRO", DB.run(t4.split(",")).toString().trim());
		
	}
	
	@Test
	void DelDupEmpNoTest() {
		
		EmployeeService DB = new EmployeeDelServiceImpl();
		
		String t1 = "DEL,-p, , ,name,N DY";
		
		assertEquals("DEL,99000000,N DY,CL1,010-3669-1077,20120718,PRO\n"
				+ "DEL,99000001,N DY,CL1,010-3669-1077,20120718,PRO\n"
				+ "DEL,99999999,N DY,CL1,010-3669-1077,20120718,PRO\n"
				+ "DEL,00000000,N DY,CL1,010-3669-1077,20120718,PRO\n"
				+ "DEL,00000001,N DY,CL1,010-3669-1077,20120718,PRO", DB.run(t1.split(",")).toString().trim());
		
		assertEquals("26", EmployeeService.employeeHM.size()+"");
	}
	
	
	@Test
	void ModTest() {
		
		EmployeeService DB = new EmployeeModServiceImpl();
		
		String t1 = "MOD,-p, , ,name,FB NTAWR,birthday,20050520";
		String t2 = "MOD,-p, , ,name,FB NTAWR,cl,CL3";
		String t3 = "MOD,-p, , ,employeeNum,08123556,birthday,20110706";
		String t4 = "MOD,-p, , ,employeeNum,18115040,birthday,12345678";
		
		
		
		assertEquals("MOD,17112609,FB NTAWR,CL4,010-5645-6122,19861203,PRO", DB.run(t1.split(",")).toString().trim());
		//Main.employeeHM.get("17112609").setBirthday("20050520");
		assertEquals("MOD,17112609,FB NTAWR,CL4,010-5645-6122,20050520,PRO", DB.run(t2.split(",")).toString().trim());
		assertEquals("MOD,08123556,WN XV,CL1,010-7986-5047,20100614,PRO", DB.run(t3.split(",")).toString().trim());
		assertEquals("MOD,18115040,TTETHU HBO,CL3,010-4581-2050,20080718,ADV", DB.run(t4.split(",")).toString().trim());
		assertEquals("31", EmployeeService.employeeHM.size()+"");
		
	}
	
	
	@Test
	void SchTest() {
			
		EmployeeService DB = new EmployeeSchServiceImpl();
		
		String t1 = "SCH,-p,-d, ,birthday,04";
		String t2 = "SCH, , , ,employeeNum,79110836";
		
		assertEquals("SCH,02117175,SBILHUT LDEXRI,CL4,010-2814-1699,19950704,ADV", DB.run(t1.split(",")).toString().trim());
		assertEquals("SCH,NONE", DB.run(t2.split(",")).toString().trim());
		
		
	}
	
	@Test
	void SchOpt2Test() {
		
		EmployeeService ADD = new EmployeeAddServiceImpl();
		EmployeeService SCH = new EmployeeSchServiceImpl();
		EmployeeService MOD = new EmployeeModServiceImpl();
		EmployeeService DEL = new EmployeeDelServiceImpl();	
		                     
		
		String str = "ADD, , , ,80000000,TEMP TEMP2,CL1,010-3669-1077,99990101,PRO";
		ADD.run(str.split(","));
		
		str = "ADD, , , ,80000001,TEMP TEMP2,CL1,010-3669-1077,99990101,PRO";
		ADD.run(str.split(","));
		
		str = "ADD, , , ,80000002,TEMP TEMP2,CL1,010-3669-1077,99990101,PRO";
		ADD.run(str.split(","));
		
		str = "ADD, , , ,80000003,TEMP3 TEMP2,CL1,010-3669-1077,99990123,PRO";
		ADD.run(str.split(","));
		
		str = "ADD, , , ,80000004,TEMP1 TEMP2,CL1,010-3669-1077,99991201,PRO";
		ADD.run(str.split(","));
		
		
		String tmp =  "SCH, , , ,name,TEMP TEMP2";	
		assertEquals("SCH,3",  SCH.run(tmp.split(",")).toString().trim());
		
		tmp =  "SCH, ,-f, ,name,TEMP";	
		assertEquals("SCH,3",  SCH.run(tmp.split(",")).toString().trim());


		tmp =  "SCH, ,-l, ,name,TEMP2";	
		assertEquals("SCH,5",  SCH.run(tmp.split(",")).toString().trim());
		
		tmp = "MOD, ,-f, ,name,TEMP,birthday,12121201";
		MOD.run(tmp.split(","));
		
		tmp =  "SCH, , , ,birthday,12121201";	
		assertEquals("SCH,3",  SCH.run(tmp.split(",")).toString().trim());
		
		tmp =  "SCH, ,-d, ,birthday,01";	
		assertEquals("SCH,6",  SCH.run(tmp.split(",")).toString().trim());
		 
	}
	
	@Test
	void performanceTest() {
		EmployeeService ADD = new EmployeeAddServiceImpl();
		EmployeeService SCH = new EmployeeSchServiceImpl();
		EmployeeService MOD = new EmployeeModServiceImpl();
		EmployeeService DEL = new EmployeeDelServiceImpl();	
		
		for(int i=0;i<100000;i++) {
			// 5000개 INSERT
			String str = "ADD, , , ,"+(i+10000000)+",A B,CL1,010-3669-1077,99990101,PRO";
			ADD.run(str.split(","));
		}
		
		String tmp = "MOD, , , ,certi,PRO,certi,ADV";
		assertEquals("MOD,100023",  MOD.run(tmp.split(",")).toString().trim());
		
		tmp =  "SCH, , , ,certi,ADV";	
		assertEquals("SCH,100031",  SCH.run(tmp.split(",")).toString().trim());
	
	}
	
}

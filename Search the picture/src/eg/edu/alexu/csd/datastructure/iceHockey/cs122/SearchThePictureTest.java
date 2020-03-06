package eg.edu.alexu.csd.datastructure.iceHockey.cs122;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Point;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

class SearchThePictureTest {

	@Test
	void test() {
		PlayersFinder test = new PlayersFinder();
		
		//test 1 
		
		
		String[] photo = {"33JUBU33",
							"3U3O4433",
							"O33P44NB",
							"PO3NSDP3",
							"VNDSD333",
							"OINFD33X"}; 
		Point[] assertInputs = new Point[3];
		assertInputs[0]=new Point(4,5);
		assertInputs[1]=new Point(13,9);
		assertInputs[2]=new Point(14,2);
		assertEquals(Arrays.toString(test.findPlayers(photo,3,16)),Arrays.toString(assertInputs));
		
		
		//test 2
		
		
		photo =new String[] {"44444H44S4",
				"K444K4L444",
				"4LJ44T44XH",
				"444O4VIF44",
				"44C4D4U444",
				"4V4Y4KB4M4",
				"G4W4HP4O4W",
				"4444ZDQ4S4",
				"4BR4Y4A444",
				"4G4V4T4444"};
		assertInputs = new Point[6];
		assertInputs[0]=new Point(3,8);
		assertInputs[1]=new Point(4,16);
		assertInputs[2]=new Point(5,4);
		assertInputs[3]=new Point(16,3);
		assertInputs[4]=new Point(16,17);
		assertInputs[5]=new Point(17,9);
		assertEquals(Arrays.toString(test.findPlayers(photo,4,16)),Arrays.toString(assertInputs));
		
		//test 3 ;;;
		
		
		photo = new String[] {"11111",
				"1AAA1",
				"1A1A1",
				"1AAA1",
				"11111"};
		assertInputs = new Point[2];
		assertInputs[0]=new Point(5,5);
		assertInputs[1]=new Point(5,5);
		assertEquals(Arrays.toString(test.findPlayers(photo,1,3)),Arrays.toString(assertInputs));
	
		//null photo tests;;;
	
		photo = new String[] {};
		assertEquals(test.findPlayers(photo,1,3),null);
		assertEquals(test.findPlayers(null,1,3),null);
	}

}

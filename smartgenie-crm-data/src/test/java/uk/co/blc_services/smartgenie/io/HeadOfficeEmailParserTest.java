/*
 * Copyright BLC IT Services Ltd 2015
 */
package uk.co.blc_services.smartgenie.io;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.math.BigDecimal;

import org.joda.time.LocalDate;
import org.junit.Test;

import uk.co.blc_services.smartgenie.domain.Job;
import uk.co.blc_services.smartgenie.domain.JobStatus;
import uk.co.blc_services.smartgenie.domain.JobType;

/**
 * @author dave.clarke@blc-services.co.uk
 *
 */
public class HeadOfficeEmailParserTest {
	
	private static HeadOfficeEmailParser instance = new HeadOfficeEmailParser();
	
	private static String testMail1 = "\n" + 
			"Hi Adam ,\n" + 
			"\n" + 
			"New  NA-Insurance job  REV-03458-  Complectus Ltd \n" + 
			"\n" + 
			"Client details\n" + 
			"\n" + 
			"Name: Mr P Cook \n" + 
			"\n" + 
			"Address:  Ladywood\n" + 
			"Langley Road\n" + 
			"Chipperfield WD4 9JS\n" + 
			"\n" + 
			"Email Address:  bob@bob.com\n" + 
			"\n" + 
			"Tel No.:   01923 262 120    \n" + 
			"\n" + 
			"Vehicle Make & Model: Range Rover Sport \n" + 
			"\n" + 
			"Vehicle Reg: FY11 KFP \n" + 
			"\n" + 
			"Damage:  rear bumper (o/s of number plate)\n Small scuff\n\n" + 
			"\n" + 
			"Price: £105.00\n" + 
			"\n" + 
			"Adam  , please contact customer within 4 hrs of receiving job details!\n" + 
			" \n" + 
			"Kind regards\n" + 
			"   \n" + 
			"Sue Barrie \n" + 
			" \n" + 
			"NATIONAL ACCOUNT";
	

	@Test
	public void test() {
		Job expected = new Job();
		expected.setDateReceived(LocalDate.now());
		expected.setStatus(JobStatus.NEW);
		expected.setName("Mr P Cook");
		expected.setAddress("Ladywood\n" + 
			"Langley Road\n" + 
			"Chipperfield");
		expected.setPostcode("WD4 9JS");
		expected.setEmailAddress("bob@bob.com");
		expected.setTelNo("01923 262 120");
		expected.setVehicleMakeAndModel("Range Rover Sport");
		expected.setVehicleReg("FY11 KFP");
		expected.setDamageDescription("rear bumper (o/s of number plate)\nSmall scuff");
		expected.setPrice(new BigDecimal("105.00"));
		expected.setType(JobType.NATIONAL_ACCOUNT);
		expected.setOrderId("REV-03458-  Complectus Ltd ");
		
		Job actual = instance.parseEmail(testMail1);
		assertEquals(expected.toString(), actual.toString());
		assertEquals(expected, actual);
	}
	
	public void testParseOrderNo(){
		
		assertEquals("REV-03458-C5K4Y1 CRM:0002549", instance.parseOrderNo("FW: New job Complectus Ltd - REV-03458-C5K4Y1 CRM:0002549"));
		assertEquals("REV-03458-C5K4Y1", instance.parseOrderNo("REV-03458-C5K4Y1"));
		assertEquals("REV-03458-C5K4Y1 some other text", instance.parseOrderNo("REV-03458-C5K4Y1 some other text"));
		assertEquals("REV-03458-C5K4Y1", instance.parseOrderNo("Order = REV-03458-C5K4Y1"));
		assertNull(instance.parseOrderNo("FW: New job Complectus Ltd -"));
		assertNull(instance.parseOrderNo("REV-0348-5K4Y1"));
	}

}

/*
 * Copyright BLC IT Services Ltd 2015
 */
package uk.co.blc_services.smartgenie.io;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.joda.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import uk.co.blc_services.smartgenie.domain.Job;
import uk.co.blc_services.smartgenie.domain.JobStatus;
import uk.co.blc_services.smartgenie.domain.JobType;

import com.google.common.io.CharStreams;

/**
 * Turns the text sent head office as into a job instance.
 * 
 * 
 * @author dave.clarke@blc-services.co.uk
 *
 */
public class HeadOfficeEmailParser {

	private static final Logger LOG = LoggerFactory.getLogger(HeadOfficeEmailParser.class.getName());

	private static final String POSTCODE_REGEX = "(GIR 0AA)|((([A-Z-[QVX]][0-9][0-9]?)|(([A-Z-[QVX]][A-Z-[IJZ]][0-9][0-9]?)|(([A-Z-[QVX]][0-9][A-HJKPSTUW])|([A-Z-[QVX]][A-Z-[IJZ]][0-9][ABEHMNPRVWXY])))) [0-9][A-Z-[CIKMOV]]{2})";
	private static final String ORDER_ID_REGEX = ".*?(?<orderId>REV\\-(?:[0-9]){5}(?:\\-(?:\\w){6})*.*)$";
	private static final Pattern ORDER_ID_PATTERN = Pattern.compile(ORDER_ID_REGEX);
	private StringBuilder buffer = new StringBuilder();
	private Field parsing = null;

	/**
	 * we are going to assume consistent order of the data in the email.
	 * 
	 * @param email
	 * @return job with as many fields populated as possible
	 * @throws IOException
	 */
	public Job parseEmail(InputStream email) throws IOException {
		List<String> lines = CharStreams
				.readLines(new InputStreamReader(email));

		Job parsed = new Job();
		parsed.setType(JobType.NATIONAL_ACCOUNT);
		parsed.setDateReceived(LocalDate.now());
		parsed.setStatus(JobStatus.NEW);

		for (String line : lines) {
			// see if it contains a field identifier
			boolean foundField = false;
			LOG.debug("Processing '{}'", line);
			for (Field field : Field.values()) {
				if (!field.ident.equalsIgnoreCase("")
						&& line.contains(field.ident)) {
					LOG.debug("Line matched '{}'", field);
					foundField = true;
					String value = line.substring(
							line.lastIndexOf(field.ident)
									+ field.ident.length()).trim();
					// new field found
					setMutilineValue(parsed);
					parsing = field;
					// new multiline
					if (field.isMultiLine()) {
						buffer = new StringBuilder(value);
					} else {
						switch (field) {
						case NAME:
							parsed.setName(value);
							break;
						case EMAIL_ADDRESS:
							parsed.setEmailAddress(value);
							break;
						case TEL_NO:
							parsed.setTelNo(value);
							break;
						case MAKE_AND_MODEL:
							parsed.setVehicleMakeAndModel(value);
							break;
						case REG:
							parsed.setVehicleReg(value);
							break;
						case PRICE:
							parsed.setPrice(parsePrice(value));
							break;
						default:
							break;
						}
					}
					break;
				}// end if found
			}// end for each known field
			if (foundField == false) {
				if (parsing != null && parsing.isMultiLine()) {
					buffer.append("\n" + line.trim());
				} else {
					// this is either gibberish or a line with an order number
					// in
					String orderId = parseOrderNo(line);
					if(orderId != null){
						//looks like it contains an order number
						parsed.setOrderId(orderId);
					} else {
					LOG.info("Skipping '{}' as we can't figure out what it is.",
							line);
					}
				}

			}
		}

		return parsed;
	}
	
	/**
	 * Returns the order no or null if one couldn't be found.
	 * @param line
	 * @return
	 */
	String parseOrderNo(String line){
		Matcher matcher = ORDER_ID_PATTERN.matcher(line);
		if(matcher.matches()){
			//looks like it contains an order number
			return (matcher.group("orderId"));
		} else {
			return null;
		}
	}
	
	BigDecimal parsePrice(String line){
		try {
			String rawPrice = line.replaceAll("[^\\d\\.]", "");
			return new BigDecimal(rawPrice);
		} catch (NumberFormatException e) {
			LOG.error("Failed to parse price from '"+line+"' platform encoding ="+Charset.defaultCharset().name(), e );
		}
		return null;
	}
	
	private void setMutilineValue(Job instance) {
		if (parsing != null && parsing.isMultiLine() && buffer.length() > 0) {
			switch (parsing) {
			case ADDRESS:
				processAddress(instance, buffer.toString());
				break;
			case DAMAGE_DESC:
				instance.setDamageDescription(buffer.toString().trim());
				break;
			default:
				break;
			}
		}
	}

	/**
	 * attempt to split into postcode and address or store in address field if
	 * postcode not found.
	 */
	public void processAddress(Job instance, String address) {

		boolean foundPostcode = false;
		String[] addressWords = address.split(" ");
		if (addressWords != null && addressWords.length > 1) {
			String potentialPostcode = new String(addressWords[addressWords.length - 2]
					+ " " + addressWords[addressWords.length - 1]).trim();
			if (potentialPostcode.matches(POSTCODE_REGEX)) {
				instance.setPostcode(potentialPostcode);
				instance.setAddress(address.replace(potentialPostcode, "").trim());
				foundPostcode = true;
			}
		}
		if (!foundPostcode){
			instance.setAddress(address);
		}

	}

	public Job parseEmail(String email) {
		try {
			return parseEmail(new ByteArrayInputStream(email.getBytes()));
		} catch (IOException e) {
			LOG.error("Failed to parse email from '"+email+"'", e );
		}
		return null;
	}

	private static enum Field {
		ORDER_ID(""), NAME("Name:"), EMAIL_ADDRESS("Email Address:"), TEL_NO(
				"Tel No.:"), MAKE_AND_MODEL("Vehicle Make & Model:"), REG(
				"Vehicle Reg:"), PRICE("Price:"),

		// Multiline
		ADDRESS("Address:", true), DAMAGE_DESC("Damage:", true);

		private String ident;
		private boolean multiLine;

		Field(String ident) {
			this(ident, false);
		}

		Field(String ident, boolean multiLine) {
			this.ident = ident;
			this.multiLine = multiLine;
		}

		public boolean isMultiLine() {
			return this.multiLine;
		}

	}

}

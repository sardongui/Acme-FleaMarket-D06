package acme.components;

import java.text.ParseException;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.format.Formatter;

import acme.datatypes.Address;
import acme.framework.helpers.MessageHelper;
import acme.framework.helpers.StringHelper;

public class AddressFormatter implements Formatter<Address>{

	@Override
	public String print(Address object, Locale locale) {
		assert object != null;
		assert locale != null;

		String result = null;
		String streetName, streetNumber, cityName, zipCode, countryName = null;
			
		
		streetName=String.format("%s, ", object.getStreetName());
		streetNumber=String.format("%s, ", object.getStreetNumber());
		cityName=String.format("%s ", object.getCityName());
		zipCode=String.format("%s, ", object.getZipCode());
		countryName=String.format("%s", object.getCountryName());
		result =String.format("%s%s%s%s%s", streetName, streetNumber, cityName, zipCode, countryName);
	

		return result;

	}

	@Override
	public Address parse(String text, Locale locale) throws ParseException {
		assert !StringHelper.isBlank(text);
		assert locale != null;

		Address result;
		String streetNameRegex, streetNumberRegex, zipCodeRegex, cityNameRegex, countryNameRegex;
		String addressRegex;
		Pattern pattern;
		Matcher matcher;
		String errorMessage;
		String streetNameText, streetNumberText, zipCodeText, cityNameText, countryNameText;

		streetNameRegex = "\\s+\\.*\\D+\\-*";  //letras separadas por espacios y guiones
		streetNumberRegex = "\\.*\\w+\\-*";  //dígitos, letras, guiones y barras oblicuas
		zipCodeRegex = "\\.*\\w+\\-*";  //dígitos, letras, guiones y barras oblicuas
		cityNameRegex = "\\s*\\D+\\.*\\-*";  //letras separadas por espacios y guiones
		countryNameRegex = "\\s*\\D+\\-*";  //letras separadas por espacios y guiones

		addressRegex = String.format(
			"^\\s*(<SN>%1$s),\\s*(?<NB>%2$s),\\s*(?<CN>%3$s)\\s*(?<ZC>%4$s),\\s*(?<CT>%5$s)$", 
			streetNameRegex, streetNumberRegex, cityNameRegex, zipCodeRegex, countryNameRegex);

		pattern = Pattern.compile(addressRegex, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
		matcher = pattern.matcher(text);

		if (!matcher.find()) {
			errorMessage = MessageHelper.getMessage("default.error.conversion", null, "Invalid value", locale);
			throw new ParseException(errorMessage, 0);
		} else {
			streetNameText = matcher.group("SN");
			streetNumberText = matcher.group("NB");
			zipCodeText = matcher.group("ZC");
			cityNameText =matcher.group("CN");
			countryNameText =matcher.group("CT");

			result = new Address();
			result.setStreetName(streetNameText);
			result.setStreetNumber(streetNumberText);
			result.setZipCode(zipCodeText);
			result.setCityName(cityNameText);
			result.setCountryName(countryNameText);
		}

		return result;
	}

}

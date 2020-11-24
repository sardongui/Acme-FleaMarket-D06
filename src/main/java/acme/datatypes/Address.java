package acme.datatypes;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import acme.framework.datatypes.DomainDatatype;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class Address extends DomainDatatype{
	
	private static final long	serialVersionUID	= 1L;

	//Atributes------------------------------------------

	@NotBlank
	private String streetNumber;
	
	@NotBlank
	private String streetName;
	
	@NotBlank
	private String zipCode;
	
	@NotBlank
	private String cityName;
	
	@NotBlank
	private String countryName;
	
	//Object interface----------------------------------
	
	@Override
	public String toString() {
		StringBuilder result;
		
		result = new StringBuilder();
		//if(comienza con streetName pues asi, sino de la otra forma)
		result.append(this.streetName);
		result.append(",");
		result.append(this.streetNumber);
		result.append(",");
		result.append(this.cityName);
		result.append(this.zipCode);
		result.append(",");
		result.append(this.countryName);
		
		return result.toString();
		
	}

}

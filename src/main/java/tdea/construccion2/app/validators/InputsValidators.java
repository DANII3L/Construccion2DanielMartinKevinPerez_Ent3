package tdea.construccion2.app.validators;

import java.sql.Date;
import java.time.LocalDate;
import org.springframework.stereotype.Component;

@Component
public abstract class InputsValidators {
	public void stringValidator(String string, String element) throws Exception {
		if (string == null || string.isEmpty())
			throw new RuntimeException(element + " no es un valor valido.");
	}

	public int integerValidator(String number, String element) throws Exception {
		stringValidator(number, element);
		try {
			Integer num = Integer.parseInt(number);
			if (num <= 0)
				throw new RuntimeException(element + " es igual a 0 o menor.");

			return num;
		} catch (Exception e) {
			throw new RuntimeException(element + " no es un valor valido. " + e.getMessage());
		}
	}

	public long longValidator(String number, String element) throws Exception {
		stringValidator(number, element);
		try {
			Long num = Long.parseLong(number);
			if (num <= 0)
				throw new RuntimeException(element + " es igual a 0 o menor.");
			
			return num;
		} catch (Exception e) {
			throw new RuntimeException(element + " no es un valor valido. " + e.getMessage());
		}
	}

	public double doubleValidator(String number, String element) throws Exception {
		stringValidator(number, element);
		try {
			return Double.parseDouble(number);
		} catch (Exception e) {
			throw new RuntimeException(element + " no es un valor valido.");
		}
	}

	public boolean booleanValidator(String bool, String element) throws Exception {
		stringValidator(bool, element);
		try {
			return Boolean.parseBoolean(bool);
		} catch (Exception e) {
			throw new RuntimeException(element + " no es un valor valido.");
		}
	}

	public Date dateValidator(String date, String element) throws Exception {
		stringValidator(date, element);
		try {
			return Date.valueOf(LocalDate.parse(date));
		} catch (Exception e) {
			throw new RuntimeException(element + " no es un valor valido.");
		}
	}
}

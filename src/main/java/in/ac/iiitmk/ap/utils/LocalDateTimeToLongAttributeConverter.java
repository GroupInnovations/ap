package in.ac.iiitmk.ap.utils;

import java.time.*;

import javax.persistence.*;

@Converter (autoApply = true)
public class LocalDateTimeToLongAttributeConverter implements AttributeConverter<LocalDateTime, Long> {
	
	@Override
	public Long convertToDatabaseColumn (LocalDateTime localDateTime) {
		return localDateTime.atZone(ZoneId.systemDefault()).toEpochSecond();
	}
	
	@Override
	public LocalDateTime convertToEntityAttribute (Long epoch) {
		return Instant.ofEpochMilli(epoch).atZone(ZoneId.systemDefault()).toLocalDateTime();
	}
}

package converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class AttendanceStatusConverter implements AttributeConverter<AttendanceStatus, Integer> {

    @Override
    public Integer convertToDatabaseColumn(AttendanceStatus status) {
        if (status == null) {
            return null;
        }
        return switch (status) {
            case PRESENT -> 0;
            case ABSENT -> 1;
            case EXCUSED -> 2;
        };
    }

    @Override
    public AttendanceStatus convertToEntityAttribute(Integer dbData) {
        if (dbData == null) {
            return null;
        }
        return switch (dbData) {
            case 0 -> AttendanceStatus.PRESENT;
            case 1 -> AttendanceStatus.ABSENT;
            case 2 -> AttendanceStatus.EXCUSED;
            default -> null;
        };
    }
}

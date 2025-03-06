package converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class RankConverter implements AttributeConverter<String, Integer> {

    @Override
    public Integer convertToDatabaseColumn(String rank) {
        if (rank == null) {
            return null;
        }
        return switch (rank.toLowerCase()) {
            case "novice" -> 0;
            case "intermediate" -> 1;
            case "advanced" -> 2;
            default -> -1;
        };
    }

    @Override
    public String convertToEntityAttribute(Integer dbData) {
        if (dbData == null) {
            return null;
        }
        return switch (dbData) {
            case 0 -> "Novice";
            case 1 -> "Intermediate";
            case 2 -> "Advanced";
            default -> "Unknown";
        };
    }
}

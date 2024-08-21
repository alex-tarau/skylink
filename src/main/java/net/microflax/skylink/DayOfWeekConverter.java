package net.microflax.skylink;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.util.AbstractCollection;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.stream.Collectors;

@Converter
@Component
public class DayOfWeekConverter implements AttributeConverter<EnumSet<DayOfWeek>, String> {
    @Override
    public String convertToDatabaseColumn(EnumSet<DayOfWeek> value) {
        return value == null ? null : value.stream().map(Enum::name).collect(Collectors.
                joining("|", "|", "|"));
    }

    @Override
    public EnumSet<DayOfWeek> convertToEntityAttribute(String dbData) {
        return dbData == null ? null :
                Arrays.stream(DayOfWeek.values()).filter(d -> dbData.contains('|' + d.name() + '|'))
                        .collect(() -> EnumSet.noneOf(DayOfWeek.class), AbstractCollection::add,
                                AbstractCollection::addAll);
    }

}

package it.discovery.persistence.converter;

import it.discovery.persistence.model.BookState;
import jakarta.persistence.AttributeConverter;

import java.util.Arrays;

public class BookStateConverter implements AttributeConverter<BookState, Character> {
    @Override
    public Character convertToDatabaseColumn(BookState bookState) {
        if (bookState == null) {
            return null;
        }
        return bookState.name().charAt(0);
    }

    @Override
    public BookState convertToEntityAttribute(Character ch) {
        if (ch == null) {
            return null;
        }
        return Arrays.stream(BookState.values()).filter(state -> state.name().charAt(0) == ch)
                .findFirst().orElseThrow();
    }
}

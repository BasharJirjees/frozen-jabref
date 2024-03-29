package org.jabref.gui.fieldeditors;

import java.time.DateTimeException;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAccessor;

import javax.swing.undo.UndoManager;

import javafx.util.StringConverter;

import org.jabref.gui.autocompleter.SuggestionProvider;
import org.jabref.logic.integrity.FieldCheckers;
import org.jabref.model.entry.Date;
import org.jabref.model.entry.field.Field;
import org.jabref.model.strings.StringUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DateEditorViewModel extends AbstractEditorViewModel {

    private static final Logger LOGGER = LoggerFactory.getLogger(DateEditorViewModel.class);
    private final DateTimeFormatter dateFormatter;

    public DateEditorViewModel(Field field, SuggestionProvider<?> suggestionProvider, DateTimeFormatter dateFormatter, FieldCheckers fieldCheckers, UndoManager undoManager) {
        super(field, suggestionProvider, fieldCheckers, undoManager);
        this.dateFormatter = dateFormatter;
    }

    public StringConverter<TemporalAccessor> getDateToStringConverter() {
        return new StringConverter<>() {
            @Override
            public String toString(TemporalAccessor date) {
                if (date != null) {
                    try {
                        return dateFormatter.format(date);
                    } catch (DateTimeException ex) {
                        LOGGER.error("Could not format date", ex);
                        return "";
                    }
                } else {
                    return "";
                }
            }

            @Override
            public TemporalAccessor fromString(String string) {
                if (StringUtil.isNotBlank(string)) {
                    try {
                        return dateFormatter.parse(string);
                    } catch (DateTimeParseException exception) {
                        // We accept all kinds of dates (not just in the format specified)
                        return Date.parse(string).map(Date::toTemporalAccessor).orElse(null);
                    }
                } else {
                    return null;
                }
            }
        };
    }
}

package io.ootb.insefrontend.core;

import java.io.IOException;

import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;

public final class LocalDateTimeJSerializer extends JodaJSerializerBase<LocalDateTime> {
	private static final long serialVersionUID = 3572991996412107255L;

	static final DateTimeFormatter format = ISODateTimeFormat.dateTime();

	public LocalDateTimeJSerializer() {
		super(LocalDateTime.class);
	}

	@Override
	public void serialize(LocalDateTime dt, JsonGenerator jgen, SerializerProvider provider) throws IOException {
		jgen.writeString(format.print(dt));
	}

	@Override
	public JsonNode getSchema(SerializerProvider provider, java.lang.reflect.Type typeHint) {
		return createSchemaNode(
				!provider.isEnabled(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS) ? "array" : "string", true);
	}
}
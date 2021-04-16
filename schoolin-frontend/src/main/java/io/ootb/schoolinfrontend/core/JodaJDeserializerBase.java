package io.ootb.schoolinfrontend.core;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;

/**
 * Created by Diwakar Kuruba on 14/04/2021.
 */
abstract class JodaJDeserializerBase<T> extends StdScalarDeserializer<T> {
	private static final long serialVersionUID = -5809747621227179151L;

	protected JodaJDeserializerBase(Class<?> cls) {
		super(cls);
	}

	protected JodaJDeserializerBase(JodaJDeserializerBase<?> src) {
		super(src);
	}

	public Object deserializeWithType(JsonParser p, DeserializationContext ctxt, TypeDeserializer typeDeserializer)
			throws IOException {
		return typeDeserializer.deserializeTypedFromAny(p, ctxt);
	}
}

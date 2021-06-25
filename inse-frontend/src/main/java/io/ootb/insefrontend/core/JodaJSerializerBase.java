package io.ootb.insefrontend.core;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

/**
 * Created by Diwakar Kuruba on 14/04/2021.
 */
abstract class JodaJSerializerBase<T> extends StdSerializer<T> {
	private static final long serialVersionUID = 1L;

	protected JodaJSerializerBase(Class<T> cls) {
		super(cls);
	}

	@SuppressWarnings("deprecation")
	public void serializeWithType(T value, JsonGenerator jgen, SerializerProvider provider, TypeSerializer typeSer)
			throws IOException {
		typeSer.writeTypePrefixForScalar(value, jgen);
		this.serialize(value, jgen, provider);
		typeSer.writeTypeSuffixForScalar(value, jgen);
	}
}

package org.rest.rapa.formatter.json;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;
import org.rest.rapa.formatter.FormatHandler;
import org.rest.rapa.formatter.JAXB.XMLHandler;
import org.rest.rapa.resource.Resource;

public class JSonHandler implements FormatHandler {

	public Resource deserialize(String content, Class<?> resourceType)
			throws JSONException {
		if (content == null || content.trim().length() == 0) {
			return null;
		}
		JSONObject jsonContent = new JSONObject(content);
		String xmlContent = XML.toString(jsonContent);
		return new XMLHandler().deserialize(xmlContent, resourceType);
	}

	public String serialize(Resource resource) throws JSONException {
		if (resource == null) {
			return null;
		}
		String encodedXml = new XMLHandler().serialize(resource);
		JSONObject json = XML.toJSONObject(encodedXml.trim());
		return json.toString();
	}

	public String getExtension() {
		return "json";
	}

	public String getContentType() {
		return "text/json";
	}
}

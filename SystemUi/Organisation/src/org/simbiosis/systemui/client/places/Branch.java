package org.simbiosis.systemui.client.places;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class Branch extends Place {
	String token;

	public Branch(String token) {
		super();
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public static class Tokenizer implements PlaceTokenizer<Branch> {

		@Override
		public Branch getPlace(String token) {
			return new Branch(token);
		}

		@Override
		public String getToken(Branch place) {
			return place.getToken();
		}

	}
}

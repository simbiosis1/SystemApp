package org.simbiosis.systemui.client.places;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class Role extends Place {
	String token;

	public Role(String token) {
		super();
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public static class Tokenizer implements PlaceTokenizer<Role> {

		@Override
		public Role getPlace(String token) {
			return new Role(token);
		}

		@Override
		public String getToken(Role place) {
			return place.getToken();
		}

	}
}

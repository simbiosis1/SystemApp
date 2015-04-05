package org.simbiosis.systemui.client.places;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class User extends Place {
	String token;

	public User(String token) {
		super();
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public static class Tokenizer implements PlaceTokenizer<User> {

		@Override
		public User getPlace(String token) {
			return new User(token);
		}

		@Override
		public String getToken(User place) {
			return place.getToken();
		}

	}
}

package org.simbiosis.systemui.client.places;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class SubBranch extends Place {
	String token;

	public SubBranch(String token) {
		super();
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public static class Tokenizer implements PlaceTokenizer<SubBranch> {

		@Override
		public SubBranch getPlace(String token) {
			return new SubBranch(token);
		}

		@Override
		public String getToken(SubBranch place) {
			return place.getToken();
		}

	}
}

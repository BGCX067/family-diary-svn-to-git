package  com.lebsh.diary.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class MainPlace extends Place {
private String mainName;
	
	public MainPlace(String token)
	{
		this.mainName = token;
	}

	public String getMainName()
	{
		return mainName;
	}

	public static class Tokenizer implements PlaceTokenizer<MainPlace>
	{
		@Override
		public String getToken(MainPlace place)
		{
			return place.getMainName();
		}

		@Override
		public MainPlace getPlace(String token)
		{
			return new MainPlace(token);
		}
	}
}

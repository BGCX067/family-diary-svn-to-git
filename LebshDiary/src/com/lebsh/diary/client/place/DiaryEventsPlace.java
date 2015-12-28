package  com.lebsh.diary.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class DiaryEventsPlace extends Place {
private String mainName;
	
	public DiaryEventsPlace(String token)
	{
		this.mainName = token;
	}

	public String getMainName()
	{
		return mainName;
	}

	public static class Tokenizer implements PlaceTokenizer<DiaryEventsPlace>
	{
		@Override
		public String getToken(DiaryEventsPlace place)
		{
			return place.getMainName();
		}

		@Override
		public DiaryEventsPlace getPlace(String token)
		{
			return new DiaryEventsPlace(token);
		}
	}
}

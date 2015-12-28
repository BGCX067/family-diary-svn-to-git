package  com.lebsh.diary.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.lebsh.diary.shared.DiaryEventDTO;

public class DiaryEventEditPlace extends Place {
private String mainName;
private DiaryEventDTO eventToEdit;
	
	public DiaryEventEditPlace(String token)
	{
		this.mainName = token;
	}

	public String getMainName()
	{
		return mainName;
	}
	
	public void setEventToEdit(DiaryEventDTO eventToEdit) {
		this.eventToEdit = eventToEdit;
	}
	
	public DiaryEventDTO getEventToEdit() {
		return eventToEdit;
	}

	public static class Tokenizer implements PlaceTokenizer<DiaryEventEditPlace>
	{
		@Override
		public String getToken(DiaryEventEditPlace place)
		{
			return place.getMainName();
		}

		@Override
		public DiaryEventEditPlace getPlace(String token)
		{
			return new DiaryEventEditPlace(token);
		}
	}
}

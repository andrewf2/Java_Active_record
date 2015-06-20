package sandbox;

public class AreaController {

	public static void main(String[] args) {
		Area area = new Area("America", "North America", 0);
		Person person = new Person();
		area.joinHandler.where(person, area.id);
		area.find(area.id);
		area.save();
		
		
		
	}

}

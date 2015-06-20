package sandbox;

class Area extends ActiveRecord{

	 String name;
	 String region;

	public Area(String name, String region, int id){
		this.id = id;
		this.name = name;
		this.region = region;
	}
	

}

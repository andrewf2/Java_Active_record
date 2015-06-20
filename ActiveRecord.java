package sandbox;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public abstract class ActiveRecord {
	int id;
	String table = this.getClass().getSimpleName().toLowerCase();
	Join joinHandler = new Join();
	private String[] results;
	Map<String,String> javaToSQLMap = javaToSQL();
	  Map<String, String> javaToSQL(){
		Map<String, String> javaToSQLMap = new HashMap<String, String>(); 
		javaToSQLMap.put("String", "varchar");
	    javaToSQLMap.put("int", "Integer");
	    javaToSQLMap.put("Integer", "Integer");
	    javaToSQLMap.put("Boolean", "Boolean");
	    javaToSQLMap.put("char", "CHARACTER");
	    return javaToSQLMap;
	}
	
	public void all(){
		ArrayList<Object> query = new ArrayList<>();
		System.out.println("SELECT * FROM " + table + "; ");
	}
	public void find(int id){
		Object query = new Object();
		System.out.println("SELECT * FROM " + table +" WHERE id = " + id + "; ");
		
	}
	
	public class Where{
		String field;
		public Where(String field){
			this.field = field;
			
		}
		public Where(){
			this.field = null;
			
		}
		public void isEqualTo(Object params){
			Object var = params;
			System.out.println("SELECT * FROM "+ table + " WHERE " + this.field + "= " + var + "; ");
			
		}
	}
	class Join{
		public void where(Object obj, int id){
			String tableName = obj.getClass().getSimpleName().toLowerCase();
			System.out.println("SELECT * FROM "+ table + " INNER JOIN " + tableName +" ON " + table + ".id="+ tableName +".id; ");
			
		}
	
	}
	
	public void delete(int id){
		System.out.println("DELETE FROM "+ table +" WHERE "+ table +".id ="+id+"; ");
		
	}
	
	private void newTable (Class klass){
		
		String tableName = klass.getSimpleName().toLowerCase();
		Field[] fields = klass.getDeclaredFields();
		Map <String, String> attrs = new HashMap<String, String>();
	
         
        for (Field field : fields) {
            attrs.put(field.getName(), field.getType().getSimpleName());
        }
                           
            System.out.println("CREATE TABLE " + tableName + "( " + tableName + "_id int, " + attrs + ");"); 
        
        }
	
	public void save(){
		Object obj = this;
		Field[] fields = this.getClass().getDeclaredFields();
		ArrayList<String> results = new ArrayList<String>();
		for(int i = 0; i <= fields.length-1; i++){
			
			 try {
				results.add(fields[i].getName() + fields[i].get(obj).toString() +" "+ this.javaToSQLMap.get(fields[i].getType().getSimpleName()) + "(250)" );
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("UPDATE "+ table +" SET "+ results.get(0) + " " + results.get(1) + "TO "  );
		
	}
		
		
}
	
	
	
	
	


/**
 * 
 */
package ArmyAnt.IO;
import java.io.IOException;
import org.json.*;
/**
 * @author Jason
 *
 */
public class JsonReader {

	/**
	 * @throws IOException 
	 * @throws JSONException 
	 * 
	 */
	public boolean load(String jsonContent) {
		// TODO Auto-generated constructor stub
		try {
			array = null;
			obj = null;
			array = new JSONArray(new String(jsonContent));
		} catch (JSONException e) {
			// e.printStackTrace();
			try {
				obj = new JSONObject(new String(jsonContent));
			} catch (JSONException e1) {
				// e.printStackTrace();
				return false;
			}
		}
		return array != null || obj != null;
	}
	
	public boolean isArray(){
		return array != null;
	}
	
	public boolean isObject(){
		return obj != null;
	}
	
	public JSONArray getArray(){
		return array;
	}
	
	public JSONObject getObject(){
		return obj;
	}

	private JSONArray array = null;
	private JSONObject obj = null;
}

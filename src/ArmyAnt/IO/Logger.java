/**
 * 
 */
package ArmyAnt.IO;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author Jason-Zhao-Jie
 *
 */


public class Logger{
	public Logger(){
	}
	public Logger(String filepath){
		this.filepath = filepath;
	}
	
	public boolean openLog(){
		if(stream != null)
			return false;
		File file = new File(filepath);
		try {
			if(!file.exists() && !file.createNewFile())
				return false;
			stream = new FileOutputStream(file);
		} catch (IOException e) {
			// e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean closeLog(){
		try {
			if(stream == null)
				return true;
			stream.close();
			stream = null;
			return true;
		} catch (IOException e) {
			// e.printStackTrace();
			return false;
		}
	}
	
	public boolean logprint(String value){
		value= "["+ df.format(new java.util.Date()) +"]	" + value;
		System.out.println(value);
		value += "\r\n";
		try {
			stream.write(value.getBytes());
		} catch (IOException e) {
			// e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public void print(String value){
		value= "["+ df.format(new java.util.Date()) +"]	" + value;
		System.out.println(value);
	}
	
	public boolean log(String value){
		value = "["+ df.format(new java.util.Date()) +"]	" + value + "\r\n";
		try {
			stream.write(value.getBytes());
		} catch (IOException e) {
			// e.printStackTrace();
			return false;
		}
		return true;
	}
	
	private String filepath = "log.txt";
	private FileOutputStream stream = null;
	private static java.text.SimpleDateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
}

package ArmyAnt.IO;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class FileOperator {
	
	public FileOperator(){
	}
	
	public static boolean createDirIfNotExist(String path){
		File file = new File(path);
		if(!file.exists())
			return file.mkdir();
		return true;
	}
	
	public static boolean removePath(String path){
		File file = new File(path);
		if(file.isDirectory()){
			String[] children = file.list();
			for(int i=0; i<children.length; ++i){
				if(!removePath(path + "/" + children[i]))
					return false;
			}
		}
		return file.delete();	
	}
	
	public static boolean isPathExist(String path){
		return new File(path).exists();
	}
	
	public static boolean isPathDirectory(String path){
		return new File(path).isDirectory();
	}
	
	public static boolean movePath(String dest, String src){
		return new File(src).renameTo(new File(dest));
	}

	public static boolean copyFile(String dest, String src) throws IOException {
		try{
			return null != Files.copy(new File(src).toPath(), new File(dest).toPath());
		}catch(IOException e){
			return false;
		}
	}
}

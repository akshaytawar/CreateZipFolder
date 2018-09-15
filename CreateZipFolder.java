import java.io.*; 
import java.util.ArrayList; 
import java.util.List; 
import java.util.zip.ZipEntry; 
import java.util.zip.ZipOutputStream;

public class GenerateDirZip {

private static List<String> fileList=new ArrayList<String>();

/**
 * This method compress the folder to Zip folder
 * @param dir
 */
public static void generatingDirZip(File dir) {

	try {
		addFile(dir);
		File zipfile=new File(dir.toString()+"\\tmp.zip");
		
		//create ZipOutputStream to write to  the zipfile
		FileOutputStream fout=new FileOutputStream(zipfile);
		ZipOutputStream	zos=new ZipOutputStream(fout);
		
	for(String temp:fileList){
		
			System.out.println("Name : "+temp);
			
			//add a new Zip Entry to the ZipOutPutStream
			ZipEntry ze=new ZipEntry(temp.substring(dir.getAbsolutePath().length()+1, temp.length()));
			zos.putNextEntry(ze);
			
			int i;
			
			//read the file and write to the ZipOutPutStream
			FileInputStream fin=new FileInputStream(temp);
			while((i=fin.read())!=-1){
				zos.write(i);
			}
			
		    //close the zip entry to write to to zip file
			zos.closeEntry();
			fin.close();
			
	}	
	  zos.close();
	  fout.close();
	
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
	

/**
 * This method add the files present in dir to the List
 * @param dir
 */
public static void addFile(File dir) {
	
	File [] files=dir.listFiles();
	for(File temp:files){
		
		if(temp.isFile()){
			fileList.add(temp.getAbsolutePath());
			System.out.println("Temp : "+temp);
			
		}			
	}
	
}

public static void main(String[] args) {
	// TODO Auto-generated method stub

	File dir=new File("dir or folder path");
	GenerateDirZip.generatingDirZip(dir);
}
}
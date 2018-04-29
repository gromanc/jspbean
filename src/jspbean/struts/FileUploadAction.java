package jspbean.struts;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

@Action(value="upload", results = {
  @Result(name="input", location = "/fileupload.jsp"),
  @Result(type="redirect", location = "/fileupload.jsp")
})
public class FileUploadAction extends ActionSupport {

  private List<File> fileUpload = new ArrayList<File>();
 	private List<String> fileUploadContentType = new ArrayList<String>();
 	private List<String> fileUploadFileName = new ArrayList<String>();

  private File filename_1;
  private File filename_2;

  private File photo;
  private String name;
  private String fileName;

  public String getFileName() {
    return fileName;
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public File getPhoto() {
    return photo;
  }

  public void setPhoto(File photo) {
    this.photo = photo;
  }

  public File getFilename_1() {
    return filename_1;
  }

  public void setFilename_1(File filename_1) {
    this.filename_1 = filename_1;
  }

  public File getFilename_2() {
    return filename_2;
  }

  public void setFilename_2(File filename_2) {
    this.filename_2 = filename_2;
  }

  public List<File> getFileUpload() {
 		return fileUpload;
 	}

 	public void setFileUpload(List<File> fileUpload) {
 		this.fileUpload = fileUpload;
 	}

 	public List<String> getFileUploadContentType() {
 		return fileUploadContentType;
 	}

 	public void setFileUploadContentType(List<String> fileUploadContentType) {
 		this.fileUploadContentType = fileUploadContentType;
 	}

 	public List<String> getFileUploadFileName() {
 		return fileUploadFileName;
 	}

 	public void setFileUploadFileName(List<String> fileUploadFileName) {
 		this.fileUploadFileName = fileUploadFileName;
 	}

 	public String upload() throws Exception{
    Files.move(photo.toPath(),Paths.get("C:\\tmp\\"+name), StandardCopyOption.REPLACE_EXISTING);

 	    for (File file: fileUpload) {
 	        System.out.println("File :" + file);
 	    }

 	    for (String fileName: fileUploadFileName) {
 	        System.out.println("Filename : " + fileName);
 	    }

 	    for (String fileContentType: fileUploadContentType) {
 	        System.out.println("File type : " + fileContentType);
 	    }

 	    return SUCCESS;

 	}

  public String execute() {
 		return SUCCESS;
 	}

 	public String display() {
 		return NONE;
 	}
}

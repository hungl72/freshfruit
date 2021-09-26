package project.util;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import project.constant.GlobalConstant;

public class FileUtil {
	
	//create method to rename file
	public static String renameFile(String fileName) {
		if(!fileName.equals(GlobalConstant.EMPTY)) {
			StringBuilder sb = new StringBuilder();
			sb.append(FilenameUtils.getBaseName(fileName)).
			append("-").
			append(System.nanoTime()).
			append(".").
			append(FilenameUtils.getExtension(fileName));
			return sb.toString();
		}
		return GlobalConstant.EMPTY;
	}
	
	// create method to upload file
	public static String upload(MultipartFile file, HttpServletRequest request) {
		
		if(!file.getOriginalFilename().equals(GlobalConstant.EMPTY)) {
			String dirPath = request.getServletContext().getRealPath("") +
					"WEB-INF" + File.separator + "resources" + File.separator + GlobalConstant.DIR_UPLOAD;
			File saveDir = new File(dirPath);
			if(!saveDir.exists()) {
				saveDir.mkdir();
			}
			String fileName = renameFile(file.getOriginalFilename());
			String filePaths = dirPath + File.separator + fileName;
			try {
				file.transferTo(new File(filePaths));
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
			return fileName;
		}
		return GlobalConstant.EMPTY;
	}
	
	//create method delete file
	public static boolean delFile(String filename, HttpServletRequest request) {
		if(!"".equals(filename)) {
			String filePart = request.getServletContext().getRealPath("") +
			GlobalConstant.DIR_UPLOAD + File.separator  + filename;
			File file = new File(filePart);
			file.delete();
		}
		return false;
	}
	
	
}

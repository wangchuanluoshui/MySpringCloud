package com.hyn.spring.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.hyn.spring.utils.ICodes;
import com.hyn.spring.utils.IResult;
import com.hyn.spring.utils.IResultUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 
 * @Title: FileController.java
 * @Package com.summit.homs.controller.sys
 * @Description: TODO
 * @author hyn  
 * @date 2018年12月3日 下午8:06:02
 * @version V1.0  
 */
@Controller
@RequestMapping("file")
@Api(value = "sys", tags = "文件传输模块")
public class FileController {

	@Value("${localhost.uploadfile.url}")
	String uploadFilePath;

	@Value("${file.path}")
	String filePath;

	@ApiOperation(value = "下载文件")
	@RequestMapping(value = "/", method = RequestMethod.GET)
	@ResponseBody
	public void downFile(
			@ApiParam(value = "文件路径", required = true) @RequestParam(name = "filePath", required = true) String filePath,
			HttpServletResponse response) {
		File file = null;
		FileInputStream inputStream = null;
		ServletOutputStream out = null;
		try {
			file = new File(filePath);

			String fileName = file.getName();
			// 视频，图像可以在线查看
//			if (!(fileName.endsWith(".jpeg") || fileName.endsWith(".jpg") || fileName.endsWith(".png")
//					|| fileName.endsWith(".mp3") || fileName.endsWith(".mp4"))) {
//				response.setContentType("multipart/form-data");
//				response.setHeader("Content-Disposition",
//						"attachment;fileName=" + java.net.URLEncoder.encode(file.getName(), "UTF-8"));
//			}
			response.setHeader("Content-Disposition","attachment;fileName=" + java.net.URLEncoder.encode(file.getName(), "UTF-8"));
			inputStream = new FileInputStream(file);
			// 通过response获取ServletOutputStream对象(out)
			out = response.getOutputStream();
			int bt = 0;
			byte[] buffer = new byte[1024];
			while ((bt = inputStream.read(buffer)) != -1) {
				out.write(buffer, 0, bt);
			}
			out.flush();

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

	@ApiOperation(value = "上传文件")
	@RequestMapping(value = "/", method = RequestMethod.POST)
	@ResponseBody
	public IResult<String> uploadFile(
			@ApiParam(value = "文件", required = true) @RequestParam("file") MultipartFile file) {
		String resultCode = "";
		String filePath = null;
		if (!file.isEmpty()) {
			try {
				filePath = saveFile(file);
				resultCode = ICodes.CODE_0000;
			} catch (Exception e) {
				resultCode = ICodes.CODE_9989;
				e.printStackTrace();
			}
		} else {
			resultCode = ICodes.CODE_9988;
		}

		return IResultUtil.responseMsg(resultCode, filePath);

	}

	@ApiOperation(value = "批量上传文件")
	@RequestMapping(value = "/batchfile/", method = RequestMethod.POST)
	@ResponseBody
	public IResult<List<String>> uploadBatchFile(HttpServletRequest request, HttpEntity<String> formEntity) {
		String resultCode = "";
		List<String> filePathList = new ArrayList<>();

		// 遍历多个文件
		List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("files");
		try {
			if (files != null && files.size() > 0) {
				for (MultipartFile mFile : files) {
					// 保存文件
					filePathList.add(saveFile(mFile));
				}
				resultCode = ICodes.CODE_0000;
			} else {
				resultCode = ICodes.CODE_9988;
			}
		} catch (IllegalStateException e) {
			resultCode = ICodes.CODE_9989;
			e.printStackTrace();
		} catch (IOException e) {
			resultCode = ICodes.CODE_9989;
			e.printStackTrace();
		}
		return IResultUtil.responseMsg(resultCode, filePathList);
	}

	public String saveFile(MultipartFile mFile) throws IOException {
		String fileName = mFile.getOriginalFilename();
		// 生成时间序列
		String timeDir = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
		String dirPath = filePath + timeDir + "/";
		File dirFile = new File(dirPath);
		if (!dirFile.isDirectory()) {
			dirFile.mkdirs();
		}
		File newFile = new File(dirPath + fileName);
		if (!newFile.isFile() && !newFile.exists()) {
			newFile.createNewFile();
		} else {
			newFile.delete();
			newFile.createNewFile();
		}
		mFile.transferTo(newFile);
		String filePath = uploadFilePath + dirPath + fileName;

		return filePath;
	}

}

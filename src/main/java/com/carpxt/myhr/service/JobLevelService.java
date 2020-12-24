package com.carpxt.myhr.service;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import com.carpxt.myhr.controller.config.UploadDataListener;
import com.carpxt.myhr.mapper.JobLevelMapper;
import com.carpxt.myhr.model.JobLevel;
import com.carpxt.myhr.model.Position;
import com.carpxt.myhr.model.RespResult;
import com.carpxt.myhr.model.from.DeletePosFrom;
import com.carpxt.myhr.model.from.Jobdata;
import com.carpxt.myhr.utils.FileUtil;
import com.sun.deploy.net.HttpResponse;
import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import static com.carpxt.myhr.utils.FileUtil.encryptFile;
import static com.carpxt.myhr.utils.FileUtil.readFile;

/**
 * @Author: tianjie
 * @Date: 2020/12/17 14:19
 * @Description: TODO
 * @Version: 1.0
 */
@Service
@Transactional
public class JobLevelService {

    @Autowired
    JobLevelMapper jobLevelMapper;

    public List<JobLevel> getJobLevelList() {
        return jobLevelMapper.getJobLevelList();
    }

    public int addJobLevelList(JobLevel jobLevel) {
        return jobLevelMapper.insertSelective(jobLevel);
    }

    public int deleteJobLevel(Integer id) {
        return jobLevelMapper.deleteByPrimaryKey(id);
    }

    public int updateJobLevel(JobLevel jobLevel) {
        return jobLevelMapper.updateByPrimaryKeySelective(jobLevel);
    }

    public int deleteJobLevelByIds(DeletePosFrom deletePosFrom) {
        return jobLevelMapper.deleteJobLevelByIds(deletePosFrom);
    }

    public void downloadJob(HttpServletResponse response) throws IOException {
        List<JobLevel> jobLevelList = jobLevelMapper.getJobLevelList();
        ClassPathResource classPathResource = new ClassPathResource("templates/exportStaTemplate.xlsx");
        InputStream inputStream = classPathResource.getInputStream();
        response.setContentType("application/vnd.ms-excel");

        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyExcel没有关系
        String fileName = URLEncoder.encode("测试", "UTF-8").replaceAll("\\+", "%20");
        response.setHeader("Content-Disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
        EasyExcel.write(response.getOutputStream(), JobLevel.class).withTemplate(inputStream).sheet("模板").doFill(jobLevelList);

    }


    public void importJob(MultipartFile file) throws IOException {
        EasyExcel.read(file.getInputStream(), Jobdata.class, new UploadDataListener(jobLevelMapper)).sheet().doRead();
    }

    public void encryptedFile(MultipartFile file) throws Exception {




    }
}

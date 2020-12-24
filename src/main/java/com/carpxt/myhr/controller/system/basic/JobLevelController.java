package com.carpxt.myhr.controller.system.basic;

import com.carpxt.myhr.model.JobLevel;
import com.carpxt.myhr.model.RespResult;
import com.carpxt.myhr.model.from.DeletePosFrom;
import com.carpxt.myhr.service.JobLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @Author: tianjie
 * @Date: 2020/12/17 13:53
 * @Description: 职称管理
 * @Version: 1.0
 */

@RestController
@RequestMapping("/system/basic/jobLevel")
public class JobLevelController {


    @Autowired
    JobLevelService joblevelService;

    @GetMapping("/getJobLevelList")
    public List<JobLevel> getJobLevelList() {
        return joblevelService.getJobLevelList();
    }

    @PostMapping("/addJobLevel")
    public RespResult addJobLevelList(@RequestBody JobLevel jobLevel) {
        if (joblevelService.addJobLevelList(jobLevel) == 1) {
            return RespResult.ok("新增成功");
        }
        return RespResult.error("新增失败");
    }

    @GetMapping("/deleteJobLevel/{id}")
    public RespResult deleteJobLevel(@PathVariable("id") Integer id) {
        if (joblevelService.deleteJobLevel(id) == 1) {
            return RespResult.ok("删除成功");
        }
        return RespResult.error("删除失败");

    }


    @PostMapping("/updateJobLevel")
    public RespResult updateJobLevel(@RequestBody JobLevel jobLevel) {
        if (joblevelService.updateJobLevel(jobLevel) == 1) {
            return RespResult.ok("操作成功");
        }
        return RespResult.error("操作失败");

    }


    /**
     * <p>批量删除</p>
     *
     * @param: [id]
     * @date: 2020/12/16 19:02
     * @return: com.carpxt.myhr.model.RespResult
     */
    @PostMapping("/deleteJobLevelByIds")
    public RespResult deleteJobLevelByIds(@RequestBody DeletePosFrom deletePosFrom) {
        if (joblevelService.deleteJobLevelByIds(deletePosFrom) == deletePosFrom.getIds().size()) {
            return RespResult.ok("删除成功");
        }
        return RespResult.error("删除失败");
    }


    @GetMapping("/downloadJob")
    public void downloadJob(HttpServletResponse response) {
        try {
            joblevelService.downloadJob(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @PostMapping("/importJob")
    public void importJob(@RequestParam("file") MultipartFile file) throws IOException {
            joblevelService.importJob(file);
    }



    @PostMapping("/encryptedFile")
    public void encryptedFile(@RequestParam("file") MultipartFile file) throws Exception {
        joblevelService.encryptedFile(file);
    }


}

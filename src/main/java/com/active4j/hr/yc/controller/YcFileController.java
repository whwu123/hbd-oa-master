package com.active4j.hr.yc.controller;

import com.active4j.hr.base.controller.BaseController;
import com.active4j.hr.common.constant.GlobalConstant;
import com.active4j.hr.core.annotation.Log;
import com.active4j.hr.core.model.AjaxJson;
import com.active4j.hr.core.model.LogType;
import com.active4j.hr.core.query.QueryUtils;
import com.active4j.hr.core.shiro.ShiroUtils;
import com.active4j.hr.core.util.DateUtils;
import com.active4j.hr.core.util.FileUtils;
import com.active4j.hr.core.util.ResponseUtil;
import com.active4j.hr.core.util.StringUtil;
import com.active4j.hr.core.web.tag.model.DataGrid;
import com.active4j.hr.system.model.ActiveUser;
import com.active4j.hr.yc.entity.YcUploadAttachmentEntity;
import com.active4j.hr.yc.service.YcUploadAttachmentService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

@Controller
@Slf4j
@RequestMapping("/ycFileController")
public class YcFileController extends BaseController {

    @Autowired
    private YcUploadAttachmentService ycUploadAttachmentService;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(Model model) {
        return "yc/file/filelist";
    }

    /**
     * ????????????
     * @param request
     * @param response
     * @param dataGrid
     */
    @RequestMapping("/datagrid")
    public void datagrid(YcUploadAttachmentEntity ycUploadAttachmentEntity, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        //??????????????????
        QueryWrapper<YcUploadAttachmentEntity> queryWrapper = QueryUtils.installQueryWrapper(ycUploadAttachmentEntity, request.getParameterMap(), dataGrid);
        //????????????
        IPage<YcUploadAttachmentEntity> lstResult = ycUploadAttachmentService.page(new Page<YcUploadAttachmentEntity>(dataGrid.getPage(), dataGrid.getRows()), queryWrapper);

        //????????????????????????????????????
        ResponseUtil.writeJson(response, dataGrid, lstResult);
    }

    @RequestMapping(value = "/addfile", method = RequestMethod.GET)
    public String addfile(Model model) {
        return "yc/file/fileadd";
    }


    /**
     *
     * @description
     *  	????????????
     * @return AjaxJson
     * @author guyp
     * @time 2020???4???16??? ??????9:44:14
     */
    @RequestMapping("uploadFiles")
    @ResponseBody
    public AjaxJson uploadFiles(String db, MultipartHttpServletRequest request, HttpServletResponse response){
        AjaxJson j = new AjaxJson();
        try{
            Map<String, MultipartFile> fileMap = request.getFileMap();

            for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {

                MultipartFile mf = entity.getValue();// ????????????????????????
                String fileName = mf.getOriginalFilename();// ???????????????
                String extend = FileUtils.getExtend(fileName);// ?????????????????????

                if(StringUtils.equals(db, GlobalConstant.FILE_UPLOADER_SAVE_FILE)) {
                    //??????????????????

                    String strYYYYMMDD = DateUtils.getYYYYMMDDStr();

                    String realPath = request.getSession().getServletContext().getRealPath("/") + "/upload/" + strYYYYMMDD + "/";// ???????????????????????????
                    String path = "upload/" + strYYYYMMDD + "/";
                    File file = new File(realPath);
                    if (!file.exists()) {
                        file.mkdirs();// ???????????????
                    }

                    String noextfilename = DateUtils.getDataString(DateUtils.SDF_YYYYMMDDHHMMSS) + StringUtil.random(8);//?????????????????????
                    String myfilename= noextfilename+"."+extend;//?????????????????????

                    String savePath = realPath + myfilename;// ?????????????????????
                    path = path + myfilename;
                    File savefile = new File(savePath);

                    // ?????????????????????????????????
                    FileCopyUtils.copy(mf.getBytes(), savefile);

                    //??????????????????
                    YcUploadAttachmentEntity attach = new YcUploadAttachmentEntity();
                    attach.setExtendName(extend);
                    attach.setName(fileName);
                    attach.setSaveName(myfilename);
                    attach.setPath(savePath);
                    attach.setUploaderDate(DateUtils.getDate());
                    attach.setUploaderName(ShiroUtils.getSessionUserName());
                    attach.setType(GlobalConstant.FILE_UPLOADER_TYPE_OTHER);
                    //??????
                    ycUploadAttachmentService.save(attach);

                    //???????????????
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("filePath", attach.getId());
                    j.setAttributes(map);

                }else if(StringUtils.equals(db, GlobalConstant.FILE_UPLOADER_SAVE_DB)) {
                    //??????????????????
                    YcUploadAttachmentEntity attach = new YcUploadAttachmentEntity();
                    attach.setContent(mf.getBytes());
                    attach.setName(fileName);
                    attach.setExtendName(extend);

                    String noextfilename = DateUtils.getDataString(DateUtils.SDF_YYYYMMDDHHMMSS) + StringUtil.random(10);//?????????????????????
                    String myfilename= noextfilename+"."+extend;//?????????????????????

                    attach.setSaveName(myfilename);
                    attach.setPath(GlobalConstant.CONFIG_FILE_SAVE_DB_URL + myfilename);
                    attach.setUploaderDate(DateUtils.getDate());
                    attach.setUploaderName(ShiroUtils.getSessionUserName());
                    attach.setType(GlobalConstant.FILE_UPLOADER_TYPE_OTHER);
                    ycUploadAttachmentService.save(attach);

                    //???????????????
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("filePath", attach.getId());
                    j.setAttributes(map);
                }

                break;
            }

        }catch(Exception e){
            j.setSuccess(false);
            j.setMsg("??????????????????");
            log.error("??????????????????????????????????????????", e.getMessage());
        }

        return j;
    }

    /**
     *
     * @description
     *  	????????????
     * @return void
     * @author guyp
     * @time 2020???4???15??? ??????5:22:50
     */
    @RequestMapping("download")
    public void download(String id, HttpServletRequest request, HttpServletResponse response) throws IOException {

        try {
            YcUploadAttachmentEntity tsAttachment = ycUploadAttachmentService.getById(id);

            if(null == tsAttachment) {
                return;
            }
            //??????????????????
            if(StringUtils.equals(GlobalConstant.FILE_UPLOADER_SAVE_FILE, tsAttachment.getType())) {

                //??????????????????
                String downLoadPath = tsAttachment.getPath();
                String fileName = tsAttachment.getName();
                //??????????????????
                long fileLength = new File(downLoadPath).length();
                //????????????????????????
                response.setContentType("application/octet-stream");

                response.setHeader("Content-disposition", "attachment; filename=" + new String(fileName.getBytes(), "ISO8859-1"));
                response.setHeader("Content-Length", String.valueOf(fileLength));

                //???????????????
                BufferedInputStream bis = new BufferedInputStream(new FileInputStream(downLoadPath));
                //?????????
                BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());

                byte[] buff = new byte[2048];
                int bytesRead;
                while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                    bos.write(buff, 0, bytesRead);
                }
                //?????????
                bis.close();
                bos.close();
            }
            //?????????????????????
            else if(StringUtils.equals(GlobalConstant.FILE_UPLOADER_SAVE_DB, tsAttachment.getType())){
                byte[] data = tsAttachment.getContent();
                String fileName = tsAttachment.getName();
                response.reset();
                response.setHeader("Content-Disposition", "attachment; filename=\"" +new String(fileName.getBytes(), "iso8859-1") + "\"");
                response.addHeader("Content-Length", "" + data.length);
                response.setContentType("application/octet-stream;charset=UTF-8");
                OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
                outputStream.write(data);
                outputStream.flush();
                outputStream.close();
            }
        } catch (Exception e) {
            log.error("????????????????????????????????????{}", e.getMessage());
            e.printStackTrace();
        }
    }


    @RequestMapping("/del")
    @ResponseBody
    @Log(type = LogType.del, name = "??????????????????", memo = "??????????????????")
    public AjaxJson del(String id, HttpServletRequest req) {
        AjaxJson j = new AjaxJson();
        try {

            if(StringUtils.isEmpty(id)) {
                j.setSuccess(false);
                j.setMsg("????????????????????????????????????");
                return j;
            }
            ActiveUser user = ShiroUtils.getSessionUser();
            ycUploadAttachmentService.removeById(id);
            log.info("?????????" + user.getUserName() + "?????????id??????" + id + "???????????????");
        }catch(Exception e) {
            log.error("??????????????????????????????????????????{}", e.getMessage());
            j.setSuccess(false);
            j.setMsg("????????????????????????");
            e.printStackTrace();
        }

        return j;
    }
}

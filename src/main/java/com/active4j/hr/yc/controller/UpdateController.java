package com.active4j.hr.yc.controller;

import com.active4j.hr.base.controller.BaseController;
import com.active4j.hr.core.annotation.Log;
import com.active4j.hr.core.model.AjaxJson;
import com.active4j.hr.core.model.LogType;
import com.active4j.hr.core.query.QueryUtils;
import com.active4j.hr.core.util.ResponseUtil;
import com.active4j.hr.core.web.tag.model.DataGrid;
import com.active4j.hr.system.entity.SysDeptEntity;
import com.active4j.hr.system.entity.SysRoleEntity;
import com.active4j.hr.system.entity.SysUserEntity;
import com.active4j.hr.yc.entity.YcPaymentRecord;
import com.active4j.hr.yc.entity.YcStudentEntity;
import com.active4j.hr.yc.entity.YcUpdateLog;
import com.active4j.hr.yc.entity.YcUpdateStulog;
import com.active4j.hr.yc.service.YcUpdateLogService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author wuchunhui home
 * @date 2021/8/29
 * apinote
 */
@Controller
@Slf4j
@RequestMapping("/updateController")
public class UpdateController extends BaseController {

    @Autowired
    private YcUpdateLogService ycUpdateLogService;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(Model model) {
        return "yc/update/list";
    }

    @RequestMapping(value = "/index2", method = RequestMethod.GET)
    public String index2(Model model) {
        return "yc/update/list2";
    }

    /**
     *
     * @description
     *  	??????????????????
     * @return void
     * @author ?????????
     * @time 2020???1???25??? ??????9:46:12
     */
    @RequestMapping("/datagrid")
    public void datagrid(YcUpdateLog ycUpdateLog, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        ycUpdateLog.setState("1");

        //??????????????????
        QueryWrapper<YcUpdateLog> queryWrapper = QueryUtils.installQueryWrapper(ycUpdateLog, request.getParameterMap(), dataGrid);

        //????????????
        IPage<YcUpdateLog> lstResult = ycUpdateLogService.page(new Page<YcUpdateLog>(dataGrid.getPage(), dataGrid.getRows()), queryWrapper);

        //????????????
        ResponseUtil.writeJson(response, dataGrid, lstResult);

    }

    @RequestMapping("/datagrid2")
    public void datagrid2(YcUpdateLog ycUpdateLog, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        ycUpdateLog.setState("2");

        //??????????????????
        QueryWrapper<YcUpdateLog> queryWrapper = QueryUtils.installQueryWrapper(ycUpdateLog, request.getParameterMap(), dataGrid);

        //????????????
        IPage<YcUpdateLog> lstResult = ycUpdateLogService.page(new Page<YcUpdateLog>(dataGrid.getPage(), dataGrid.getRows()), queryWrapper);

        //????????????
        ResponseUtil.writeJson(response, dataGrid, lstResult);

    }
/**
 *
 * @description
 *  	?????????????????????
 * @return ModelAndView
 * @author ?????????
 * @time 2020???1???28??? ??????3:48:35
 */
    @RequestMapping("/addUpdate")
    public ModelAndView addUpdate(YcUpdateLog ycUpdateLog, HttpServletRequest req) {
        ModelAndView view = new ModelAndView("yc/update/updatelog");
        if(StringUtils.isEmpty(ycUpdateLog.getId())) {
            //??????
            ycUpdateLog = new YcUpdateLog();
            view.addObject("ycUpdateLog", ycUpdateLog);
        }else {
            //??????
            ycUpdateLog = ycUpdateLogService.getById(ycUpdateLog.getId());
            view.addObject("ycUpdateLog", ycUpdateLog);
        }


        return view;
    }

    @RequestMapping(value = "/auditingStu", method = RequestMethod.POST)
    @ResponseBody
    @Log(type = LogType.update, name = "?????????????????????", memo = "?????????????????????")
    public AjaxJson auditingStu(String id, HttpServletRequest req) {
        YcUpdateLog ycUpdateLog = new YcUpdateLog();
        AjaxJson j = new AjaxJson();
        try {
            ycUpdateLog = ycUpdateLogService.getById(id);
            if(ycUpdateLog!=null){
                String card = ycUpdateLog.getStudentCard();

                QueryWrapper<YcUpdateLog> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("student_card",card);
                List<YcUpdateLog> list = ycUpdateLogService.list(queryWrapper);
                for(int i=0; i<list.size();i++){
                    YcUpdateLog ycUpdateLog1 = list.get(i);
                    ycUpdateLog1.setState("2");
                    ycUpdateLogService.saveOrUpdate(ycUpdateLog1);
                }
            }
            j.setSuccess(true);
            j.setMsg("???????????????"+ycUpdateLog.getStudentName()+"???????????????");
            return j;

        }catch(Exception e) {
            log.error("????????????????????????:" + e.getMessage());
            j.setSuccess(false);
            j.setMsg("????????????????????????");
            e.printStackTrace();
        }
        return j;
    }


    @RequestMapping(value = "/saveUpdatelog", method = RequestMethod.POST)
    @ResponseBody
    @Log(type = LogType.update, name = "?????????????????????", memo = "?????????????????????")
    public AjaxJson saveUpdatelog(YcUpdateLog ycUpdateLog, HttpServletRequest req) {
        AjaxJson j = new AjaxJson();
        try {
            if(ycUpdateLog.getId()==null || ycUpdateLog.getId().isEmpty()){
                ycUpdateLog.setState("1");
            }

            ycUpdateLog.setMemoOne(ycUpdateLog.getMemoOne().trim());
            ycUpdateLog.setMemoTwo(ycUpdateLog.getMemoTwo().trim());
            ycUpdateLogService.saveOrUpdate(ycUpdateLog);
            j.setSuccess(true);
            j.setMsg("???????????????"+ycUpdateLog.getStudentName()+"???????????????");
            return j;

        }catch(Exception e) {
            log.error("????????????????????????:" + e.getMessage());
            j.setSuccess(false);
            j.setMsg("????????????????????????");
            e.printStackTrace();
        }
        return j;
    }


    @RequestMapping(value = "/dele", method = RequestMethod.POST)
    @ResponseBody
    @Log(type = LogType.update, name = "?????????????????????", memo = "?????????????????????")
    public AjaxJson dele(String id, HttpServletRequest req) {
        AjaxJson j = new AjaxJson();
        try {
            YcUpdateLog ycUpdateLog = ycUpdateLogService.getById(id);
            ycUpdateLog.setState("-1");
            ycUpdateLogService.saveOrUpdate(ycUpdateLog);
            j.setSuccess(true);
            j.setMsg("???????????????"+ycUpdateLog.getStudentName()+"???????????????");
            return j;

        }catch(Exception e) {
            log.error("????????????????????????:" + e.getMessage());
            j.setSuccess(false);
            j.setMsg("????????????????????????");
            e.printStackTrace();
        }
        return j;
    }
}

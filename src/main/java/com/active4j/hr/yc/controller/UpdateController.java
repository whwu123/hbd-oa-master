package com.active4j.hr.yc.controller;

import com.active4j.hr.base.controller.BaseController;
import com.active4j.hr.core.annotation.Log;
import com.active4j.hr.core.model.AjaxJson;
import com.active4j.hr.core.model.LogType;
import com.active4j.hr.core.query.QueryUtils;
import com.active4j.hr.core.util.ResponseUtil;
import com.active4j.hr.core.web.tag.model.DataGrid;
import com.active4j.hr.yc.entity.YcPaymentRecord;
import com.active4j.hr.yc.entity.YcStudentEntity;
import com.active4j.hr.yc.entity.YcUpdateLog;
import com.active4j.hr.yc.entity.YcUpdateStulog;
import com.active4j.hr.yc.service.YcUpdateLogService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
     *  	表格数据显示
     * @return void
     * @author 麻木神
     * @time 2020年1月25日 下午9:46:12
     */
    @RequestMapping("/datagrid")
    public void datagrid(YcUpdateLog ycUpdateLog, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        ycUpdateLog.setState("1");

        //拼接查询条件
        QueryWrapper<YcUpdateLog> queryWrapper = QueryUtils.installQueryWrapper(ycUpdateLog, request.getParameterMap(), dataGrid);

        //执行查询
        IPage<YcUpdateLog> lstResult = ycUpdateLogService.page(new Page<YcUpdateLog>(dataGrid.getPage(), dataGrid.getRows()), queryWrapper);

        //输出结果
        ResponseUtil.writeJson(response, dataGrid, lstResult);

    }

    @RequestMapping("/datagrid2")
    public void datagrid2(YcUpdateLog ycUpdateLog, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        ycUpdateLog.setState("2");

        //拼接查询条件
        QueryWrapper<YcUpdateLog> queryWrapper = QueryUtils.installQueryWrapper(ycUpdateLog, request.getParameterMap(), dataGrid);

        //执行查询
        IPage<YcUpdateLog> lstResult = ycUpdateLogService.page(new Page<YcUpdateLog>(dataGrid.getPage(), dataGrid.getRows()), queryWrapper);

        //输出结果
        ResponseUtil.writeJson(response, dataGrid, lstResult);

    }


    @RequestMapping(value = "/auditingStu", method = RequestMethod.POST)
    @ResponseBody
    @Log(type = LogType.update, name = "审核了学生信息", memo = "审核了学生信息")
    public AjaxJson auditingStu(String id, HttpServletRequest req) {
        YcUpdateLog ycUpdateLog = new YcUpdateLog();
        AjaxJson j = new AjaxJson();
        try {
            ycUpdateLog = ycUpdateLogService.getById(id);
            ycUpdateLog.setState("2");
            ycUpdateLogService.saveOrUpdate(ycUpdateLog);

            j.setSuccess(true);
            j.setMsg("审核学生《"+ycUpdateLog.getStudentName()+"》信息成功");
            return j;

        }catch(Exception e) {
            log.error("审核学生信息错误:" + e.getMessage());
            j.setSuccess(false);
            j.setMsg("审核学生信息错误");
            e.printStackTrace();
        }
        return j;
    }
}

package com.active4j.hr.yc.controller;

import com.active4j.hr.base.controller.BaseController;
import com.active4j.hr.core.annotation.Log;
import com.active4j.hr.core.beanutil.MyBeanUtils;
import com.active4j.hr.core.model.AjaxJson;
import com.active4j.hr.core.model.LogType;
import com.active4j.hr.core.query.QueryUtils;
import com.active4j.hr.core.util.ListUtils;
import com.active4j.hr.core.util.ResponseUtil;
import com.active4j.hr.core.web.tag.model.DataGrid;
import com.active4j.hr.system.entity.SysDicValueEntity;
import com.active4j.hr.system.model.KeyValueModel;
import com.active4j.hr.system.util.SystemUtils;
import com.active4j.hr.yc.entity.*;
import com.active4j.hr.yc.service.*;
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
import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/insuranceController")
public class YcInsuranceCompanyController extends BaseController {

    @Autowired
    private YcInsuranceCompanyService ycInsuranceCompanyService;

    @Autowired
    private YcInsurancePersonService ycInsurancePersonService;

    @Autowired
    private YcAreaService ycAreaService;

    @Autowired
    private YcSchoolInsuredService ycSchoolService;

    @RequestMapping(value = "/area/list", method = RequestMethod.GET)
    public String alist(Model model) {
        return "yc/insurance/area/list";
    }

    @RequestMapping(value = "/school/insured/list", method = RequestMethod.GET)
    public String slist(Model model) {
        //给所属区县准备数据
        QueryWrapper<YcAreaEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("AREA_STATE",0);
        List<YcAreaEntity> list = ycAreaService.list(queryWrapper);
        String str = ListUtils.listToReplaceStr(list, "areaName", "id");
        model.addAttribute("areaReplace", str);

        //给供应商电话准备数据
        QueryWrapper<YcInsurancePersonEntity> queryWrapper2 = new QueryWrapper<>();
        queryWrapper2.eq("PERSON_STATE",0);
        List<YcInsurancePersonEntity> list2 = ycInsurancePersonService.list(queryWrapper2);
        String str2 = ListUtils.listToReplaceStr(list2, "personPhone", "id");
        model.addAttribute("insurancePhoneReplace", str2);
        //给供应商姓名准备数据
        String str3 = ListUtils.listToReplaceStr(list2, "personName", "id");
        model.addAttribute("insuranceNameReplace", str3);

        //给供应商名称准备数据
        QueryWrapper<YcInsuranceCompanyEntity> queryWrapper3 = new QueryWrapper<>();
        queryWrapper3.eq("COMPANY_STATE",0);
        List<YcInsuranceCompanyEntity> list3 = ycInsuranceCompanyService.list(queryWrapper3);
        String str4 = ListUtils.listToReplaceStr(list3, "companyName", "id");
        model.addAttribute("companyReplace", str4);



        return "yc/insurance/school/insured/list";
    }

    @RequestMapping(value = "/company/list", method = RequestMethod.GET)
    public String clist(Model model) {
        return "yc/insurance/company/list";
    }

    @RequestMapping(value = "/person/list", method = RequestMethod.GET)
    public String plist(Model model) {
        // 给保险公司查询条件中的下拉框准备数据
        QueryWrapper<YcInsuranceCompanyEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("COMPANY_STATE",0);
        List<YcInsuranceCompanyEntity> list = ycInsuranceCompanyService.list(queryWrapper);
        String str = ListUtils.listToReplaceStr(list, "companyName", "id");
        model.addAttribute("companyReplace", str);
        return "yc/insurance/person/list";
    }

    /**
     *
     * @description
     *  	表格数据显示
     * @return void
     * @author 麻木神
     * @time 2020年1月25日 下午9:46:12
     */
    @RequestMapping("/company/datagrid")
    public void datagrid(YcInsuranceCompanyEntity ycInsuranceCompanyEntity, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        //拼接查询条件
        QueryWrapper<YcInsuranceCompanyEntity> queryWrapper = QueryUtils.installQueryWrapper(ycInsuranceCompanyEntity, request.getParameterMap(), dataGrid);
        //执行查询
        IPage<YcInsuranceCompanyEntity> lstResult = ycInsuranceCompanyService.page(new Page<YcInsuranceCompanyEntity>(dataGrid.getPage(), dataGrid.getRows()), queryWrapper);
        //输出结果
        ResponseUtil.writeJson(response, dataGrid, lstResult);
    }

    @RequestMapping("/person/datagrid")
    public void datagrid2(YcInsurancePersonEntity ycInsurancePersonEntity, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        //拼接查询条件
        QueryWrapper<YcInsurancePersonEntity> queryWrapper = QueryUtils.installQueryWrapper(ycInsurancePersonEntity, request.getParameterMap(), dataGrid);
        //执行查询
        IPage<YcInsurancePersonEntity> lstResult = ycInsurancePersonService.page(new Page<YcInsurancePersonEntity>(dataGrid.getPage(), dataGrid.getRows()), queryWrapper);
        //输出结果
        ResponseUtil.writeJson(response, dataGrid, lstResult);
    }

    @RequestMapping("/area/datagrid")
    public void datagrid3(YcAreaEntity ycAreaEntity, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        //拼接查询条件
        QueryWrapper<YcAreaEntity> queryWrapper = QueryUtils.installQueryWrapper(ycAreaEntity, request.getParameterMap(), dataGrid);
        //执行查询
        IPage<YcAreaEntity> lstResult = ycAreaService.page(new Page<YcAreaEntity>(dataGrid.getPage(), dataGrid.getRows()), queryWrapper);
        //输出结果
        ResponseUtil.writeJson(response, dataGrid, lstResult);
    }

    @RequestMapping("/school/insured/datagrid")
    public void datagrid4(YcSchoolInsuredEntity ycSchoolEntity, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        //拼接查询条件
        QueryWrapper<YcSchoolInsuredEntity> queryWrapper = QueryUtils.installQueryWrapper(ycSchoolEntity, request.getParameterMap(), dataGrid);
        //执行查询
        IPage<YcSchoolInsuredEntity> lstResult = ycSchoolService.page(new Page<YcSchoolInsuredEntity>(dataGrid.getPage(), dataGrid.getRows()), queryWrapper);
        //输出结果
        ResponseUtil.writeJson(response, dataGrid, lstResult);
    }

    /**
     *
     * @description 新增或编辑跳转
     * @return ModelAndView
     */
    @RequestMapping("/company/addorupdate")
    public ModelAndView addorupdate(YcInsuranceCompanyEntity ycInsuranceCompanyEntity, HttpServletRequest req) {
        ModelAndView view = new ModelAndView("yc/insurance/company/company");

        if(StringUtils.isEmpty(ycInsuranceCompanyEntity.getId())) {
            //新增
            ycInsuranceCompanyEntity = new YcInsuranceCompanyEntity();
            view.addObject("ycInsuranceCompanyEntity", ycInsuranceCompanyEntity);
        }else {
            //编辑
            ycInsuranceCompanyEntity = ycInsuranceCompanyService.getById(ycInsuranceCompanyEntity.getId());
            view.addObject("ycInsuranceCompanyEntity", ycInsuranceCompanyEntity);
        }
        return view;
    }

    @RequestMapping("/person/addorupdate")
    public ModelAndView paddorupdate(YcInsurancePersonEntity ycInsurancePersonEntity, HttpServletRequest req) {
        ModelAndView view = new ModelAndView("yc/insurance/person/person");

        if(StringUtils.isEmpty(ycInsurancePersonEntity.getId())) {
            //新增
            ycInsurancePersonEntity = new YcInsurancePersonEntity();
            view.addObject("ycInsurancePersonEntity", ycInsurancePersonEntity);
        }else {
            //编辑
            ycInsurancePersonEntity = ycInsurancePersonService.getById(ycInsurancePersonEntity.getId());
            view.addObject("ycInsurancePersonEntity", ycInsurancePersonEntity);
        }
        //取得所有的保险公司
        QueryWrapper<YcInsuranceCompanyEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("COMPANY_STATE",0);
        List<YcInsuranceCompanyEntity> companyEntityList =  ycInsuranceCompanyService.list(queryWrapper);
        view.addObject("companyEntityList",companyEntityList);
        return view;
    }

    @RequestMapping("/area/addorupdate")
    public ModelAndView areaaddorupdate(YcAreaEntity ycAreaEntity, HttpServletRequest req) {
        ModelAndView view = new ModelAndView("yc/insurance/area/area");

        if(StringUtils.isEmpty(ycAreaEntity.getId())) {
            //新增
            ycAreaEntity = new YcAreaEntity();
        }else {
            //编辑
            ycAreaEntity = ycAreaService.getById(ycAreaEntity.getId());
        }
        view.addObject("ycAreaEntity", ycAreaEntity);
        return view;
    }

    @RequestMapping("/school/insured/addorupdate")
    public ModelAndView schooladdorupdate(YcSchoolInsuredEntity ycSchoolEntity, HttpServletRequest req) {
        ModelAndView view = new ModelAndView("yc/insurance/school/insured/school");
        //获取学历类型的数据字典
        List<SysDicValueEntity> xueliList = SystemUtils.getDictionaryLst("xueli_type");
        view.addObject("xueliList", xueliList);

        //获取保险种类类型的数据字典
        List<SysDicValueEntity> insuranceList = SystemUtils.getDictionaryLst("insurance_type");
        view.addObject("insuranceList", insuranceList);

        //获取年级的数据字典
        List<SysDicValueEntity> gradList = SystemUtils.getDictionaryLst("school_nianji");
        view.addObject("gradList", gradList);

        QueryWrapper<YcAreaEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("AREA_STATE",0);
        List<YcAreaEntity> areaList = ycAreaService.list(queryWrapper);
        view.addObject("areaList", areaList);

        //菜单的树形结构
        QueryWrapper<YcInsuranceCompanyEntity> queryWrapper2 = new QueryWrapper<>();
        queryWrapper2.eq("COMPANY_STATE",0);
        List<KeyValueModel> lstTrees = new ArrayList<KeyValueModel>();
        List<YcInsuranceCompanyEntity> lstParents = ycInsuranceCompanyService.list(queryWrapper2);
        if(null != lstParents && lstParents.size() > 0) {
            for(YcInsuranceCompanyEntity f : lstParents) {
                KeyValueModel keyValue = new KeyValueModel();
                keyValue.setKey(f.getCompanyName());
                keyValue.setValue(f.getId());
                lstTrees.add(keyValue);

                QueryWrapper<YcInsurancePersonEntity> queryWrapper3 = new QueryWrapper<>();
                queryWrapper3.eq("COMPANY_ID",f.getId());
                List<YcInsurancePersonEntity> lstChildren = ycInsurancePersonService.list(queryWrapper3);
                getYcTreeKeyValue(lstChildren, lstTrees, "　　");
            }
        }
        view.addObject("lstTrees", lstTrees);
        if(StringUtils.isEmpty(ycSchoolEntity.getId())) {
            //新增
            ycSchoolEntity = new YcSchoolInsuredEntity();
        }else {
            //编辑
            ycSchoolEntity = ycSchoolService.getById(ycSchoolEntity.getId());
        }
        view.addObject("ycSchoolEntity", ycSchoolEntity);
        return view;
    }
    private void getYcTreeKeyValue(List<YcInsurancePersonEntity> lst, List<KeyValueModel> lstTrees, String tag) {
        if(null != lst && lst.size() > 0) {
            for(YcInsurancePersonEntity f : lst) {
                KeyValueModel keyValue = new KeyValueModel();
                keyValue.setKey(tag + f.getPersonName());
                keyValue.setValue(f.getId());
                lstTrees.add(keyValue);
                QueryWrapper<YcInsurancePersonEntity> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("COMPANY_ID",f.getId());
                List<YcInsurancePersonEntity> lstChildren = ycInsurancePersonService.list(queryWrapper);
                getYcTreeKeyValue(lstChildren, lstTrees, "　　");
            }
        }
    }
    /**
     * @return AjaxJson
     */
    @RequestMapping("/school/insured/save")
    @ResponseBody
    @Log(type = LogType.save, name = "保存学校信息", memo = "新增或编辑保存了学校信息")
    public AjaxJson schoolSave(YcSchoolInsuredEntity ycSchoolEntity, HttpServletRequest request) {
        AjaxJson j = new AjaxJson();
        try {
            String InsurancePersonId = ycSchoolEntity.getInsurancePersonId();
            QueryWrapper<YcInsurancePersonEntity> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("ID",InsurancePersonId);
            if (StringUtils.isNotEmpty(ycSchoolEntity.getId())) {
                List<YcInsurancePersonEntity> listPerson = ycInsurancePersonService.list(queryWrapper);
                if(listPerson.size()>0){
                    ycSchoolEntity.setInsuranceCompanyId(listPerson.get(0).getCompanyId());
                }
                //编辑保存
                YcSchoolInsuredEntity tmp = ycSchoolService.getById(ycSchoolEntity.getId());
                MyBeanUtils.copyBeanNotNull2Bean(ycSchoolEntity, tmp);
                ycSchoolService.saveOrUpdate(tmp);
            }else {
                //新增保存
                List<YcInsurancePersonEntity> listPerson = ycInsurancePersonService.list(queryWrapper);
                if(listPerson.size()>0){
                    ycSchoolEntity.setInsuranceCompanyId(listPerson.get(0).getCompanyId());
                }
                ycSchoolService.save(ycSchoolEntity);
            }
        }catch(Exception e) {
            log.error("保存学校信息报错，错误信息:" + e.getMessage());
            j.setSuccess(false);
            j.setMsg("保存学校错误");
            e.printStackTrace();
        }
        return j;
    }

    /**
     * @return AjaxJson
     */
    @RequestMapping("/area/save")
    @ResponseBody
    @Log(type = LogType.save, name = "保存区县信息", memo = "新增或编辑保存了区县信息")
    public AjaxJson areaSave(YcAreaEntity ycAreaEntity, HttpServletRequest request) {
        AjaxJson j = new AjaxJson();
        try {
            if (StringUtils.isNotEmpty(ycAreaEntity.getId())) {
                //编辑保存
                YcAreaEntity tmp = ycAreaService.getById(ycAreaEntity.getId());
                MyBeanUtils.copyBeanNotNull2Bean(ycAreaEntity, tmp);
                ycAreaService.saveOrUpdate(tmp);
            }else {
                //新增保存
                ycAreaService.save(ycAreaEntity);
            }
        }catch(Exception e) {
            log.error("保存区县信息报错，错误信息:" + e.getMessage());
            j.setSuccess(false);
            j.setMsg("保存区县错误");
            e.printStackTrace();
        }
        return j;
    }

    /**
     * @return AjaxJson
     */
    @RequestMapping("/company/save")
    @ResponseBody
    @Log(type = LogType.save, name = "保存保险公司信息", memo = "新增或编辑保存了保险公司信息")
    public AjaxJson companySave(YcInsuranceCompanyEntity ycInsuranceCompanyEntity, HttpServletRequest request) {
        AjaxJson j = new AjaxJson();
        try {
            if (StringUtils.isNotEmpty(ycInsuranceCompanyEntity.getId())) {
                //编辑保存
                YcInsuranceCompanyEntity tmp = ycInsuranceCompanyService.getById(ycInsuranceCompanyEntity.getId());
                MyBeanUtils.copyBeanNotNull2Bean(ycInsuranceCompanyEntity, tmp);
                ycInsuranceCompanyService.saveOrUpdate(tmp);
            }else {
                //新增保存
                ycInsuranceCompanyService.save(ycInsuranceCompanyEntity);
            }
        }catch(Exception e) {
            log.error("保存保险公司信息报错，错误信息:" + e.getMessage());
            j.setSuccess(false);
            j.setMsg("保存保险公司错误");
            e.printStackTrace();
        }
        return j;
    }

    /**
     * @return AjaxJson
     */
    @RequestMapping("/person/save")
    @ResponseBody
    @Log(type = LogType.save, name = "保存保险人员公司信息", memo = "新增或编辑保存了保险人员公司信息")
    public AjaxJson personSave(YcInsurancePersonEntity ycInsurancePersonEntity, HttpServletRequest request) {
        AjaxJson j = new AjaxJson();
        try {

            if(StringUtils.isEmpty(ycInsurancePersonEntity.getCompanyId())) {
                j.setSuccess(false);
                j.setMsg("所属保险公司为空");
                return j;
            }
            if(StringUtils.isEmpty(ycInsurancePersonEntity.getPersonName())) {
                j.setSuccess(false);
                j.setMsg("姓名为空");
                return j;
            }

            if(StringUtils.isEmpty(ycInsurancePersonEntity.getPersonPhone())) {
                j.setSuccess(false);
                j.setMsg("人员电话为空");
                return j;
            }




            if (StringUtils.isNotEmpty(ycInsurancePersonEntity.getId())) {
                //编辑保存
                YcInsurancePersonEntity tmp = ycInsurancePersonService.getById(ycInsurancePersonEntity.getId());
                MyBeanUtils.copyBeanNotNull2Bean(ycInsurancePersonEntity, tmp);
                ycInsurancePersonService.saveOrUpdate(tmp);

                //修改学校的承保公司信息
                String insuranceCompanyId = ycInsurancePersonEntity.getCompanyId();
                String insurancePersonId = ycInsurancePersonEntity.getId();
                QueryWrapper<YcSchoolInsuredEntity> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("INSURANCE_PERSON_ID",insurancePersonId);
                List<YcSchoolInsuredEntity> ycSchoolEntities = ycSchoolService.list(queryWrapper);
                for(YcSchoolInsuredEntity ycSchoolEntity :ycSchoolEntities){
                        ycSchoolEntity.setInsuranceCompanyId(insuranceCompanyId);
                        ycSchoolService.saveOrUpdate(ycSchoolEntity);
                }
            }else {
                ycInsurancePersonService.save(ycInsurancePersonEntity);
            }
        }catch(Exception e) {
            log.error("保存保险人员公司信息报错，错误信息:" + e.getMessage());
            j.setSuccess(false);
            j.setMsg("保存保险人员公司错误");
            e.printStackTrace();
        }

        return j;
    }

    /**
     * @params
     * @return AjaxJson
     */
    @RequestMapping("/company/del")
    @ResponseBody
    @Log(type = LogType.del, name = "删除保险公司信息", memo = "删除了保险公司信息")
    public AjaxJson cdel(String id, HttpServletRequest req) {
        AjaxJson j = new AjaxJson();
        try {
            ycInsuranceCompanyService.removeById(id);
        }catch(Exception e) {
            log.error("删除保险公司信息报错，错误信息：{}", e.getMessage());
            j.setSuccess(false);
            j.setMsg("删除保险公司信息错误");
            e.printStackTrace();
        }
        return j;
    }

    /**
     * @params
     * @return AjaxJson
     */
    @RequestMapping("/person/del")
    @ResponseBody
    @Log(type = LogType.del, name = "删除保险公司人员信息", memo = "删除了保险公司人员信息")
    public AjaxJson pdel(String id, HttpServletRequest req) {
        AjaxJson j = new AjaxJson();
        try {
            ycInsurancePersonService.removeById(id);
        }catch(Exception e) {
            log.error("删除保险公司人员信息报错，错误信息：{}", e.getMessage());
            j.setSuccess(false);
            j.setMsg("删除保险公司人员信息错误");
            e.printStackTrace();
        }
        return j;
    }

    /**
     * @params
     * @return AjaxJson
     */
    @RequestMapping("/area/del")
    @ResponseBody
    @Log(type = LogType.del, name = "删除区县信息", memo = "删除了区县信息")
    public AjaxJson adel(String id, HttpServletRequest req) {
        AjaxJson j = new AjaxJson();
        try {
            YcAreaEntity areaEntity = ycAreaService.getById(id);
            areaEntity.setAreaState(99);
            ycAreaService.saveOrUpdate(areaEntity);
        }catch(Exception e) {
            log.error("删除区县信息报错，错误信息：{}", e.getMessage());
            j.setSuccess(false);
            j.setMsg("删除区县信息错误");
            e.printStackTrace();
        }
        return j;
    }

    /**
     * @params
     * @return AjaxJson
     */
    @RequestMapping("/school/insured/del")
    @ResponseBody
    @Log(type = LogType.del, name = "删除学校信息", memo = "删除了学校信息")
    public AjaxJson schooldel(String id, HttpServletRequest req) {
        AjaxJson j = new AjaxJson();
        try {
            YcSchoolInsuredEntity ycSchoolEntity = ycSchoolService.getById(id);
            ycSchoolEntity.setSchoolState(99);
            ycSchoolService.saveOrUpdate(ycSchoolEntity);
        }catch(Exception e) {
            log.error("删除学校信息报错，错误信息：{}", e.getMessage());
            j.setSuccess(false);
            j.setMsg("删除学校信息错误");
            e.printStackTrace();
        }
        return j;
    }
}

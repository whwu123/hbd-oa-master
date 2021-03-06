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
    private  YcSchoolService ycSchoolService;

    @Autowired
    private YcSchoolInsuredService ycSchoolInsuredService;

    @RequestMapping(value = "/area/list", method = RequestMethod.GET)
    public String alist(Model model) {
        return "yc/insurance/area/list";
    }
    @RequestMapping(value = "/school/list", method = RequestMethod.GET)
    public String silist(Model model) {
        //???????????????????????????
        QueryWrapper<YcAreaEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("AREA_STATE",0);
        List<YcAreaEntity> list = ycAreaService.list(queryWrapper);
        String str = ListUtils.listToReplaceStr(list, "areaName", "id");
        model.addAttribute("areaReplace", str);
        return "yc/insurance/school/list";
    }

    @RequestMapping(value = "/school/grade/list", method = RequestMethod.GET)
    public String gradelist(Model model,String schoolId) {
        if(schoolId!=null){
            //????????????ID??????????????????
            YcSchoolEntity ycSchoolEntity = ycSchoolService.getById(schoolId);
            String str = ycSchoolEntity.getName()+"_"+ycSchoolEntity.getId();
            model.addAttribute("schoolStr",str);

        }
        model.addAttribute("schoolId",schoolId);
        return "yc/insurance/school/grade/list";
    }

    @RequestMapping(value = "/school/class/list", method = RequestMethod.GET)
    public String classlist(Model model,String gradeId) {
        if(gradeId!=null){
            //????????????ID????????????
            YcSchoolEntity ycSchoolGradeEntity = ycSchoolService.getById(gradeId);
            String gradeStr = ycSchoolGradeEntity.getName()+"_"+ycSchoolGradeEntity.getId();
            System.out.println("gradeStr==="+gradeStr);
            model.addAttribute("gradeStr",gradeStr);

           /* //????????????????????????ID??????????????????
            QueryWrapper<YcSchoolEntity> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("PARENT_ID",ycSchoolGradeEntity.getParentId());
            List<YcSchoolEntity> ycSchoolEntityList = ycSchoolService.list(queryWrapper);
            YcSchoolEntity ycSchoolEntity = ycSchoolEntityList.get(0);

            String schoolStr = ycSchoolEntity.getName()+"_"+ycSchoolEntity.getId();
            System.out.println("schoolStr==="+schoolStr);*/

        }
        System.out.println("gradeId==="+gradeId);
        model.addAttribute("gradeId",gradeId);
        return "yc/insurance/school/schoolclass/list";
    }

    @RequestMapping(value = "/school/insured/list", method = RequestMethod.GET)
    public String slist(Model model) {
        //???????????????????????????
        QueryWrapper<YcAreaEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("AREA_STATE",0);
        List<YcAreaEntity> list = ycAreaService.list(queryWrapper);
        String str = ListUtils.listToReplaceStr(list, "areaName", "id");
        model.addAttribute("areaReplace", str);

        //??????????????????????????????
        QueryWrapper<YcInsurancePersonEntity> queryWrapper2 = new QueryWrapper<>();
        queryWrapper2.eq("PERSON_STATE",0);
        List<YcInsurancePersonEntity> list2 = ycInsurancePersonService.list(queryWrapper2);
        String str2 = ListUtils.listToReplaceStr(list2, "personPhone", "id");
        model.addAttribute("insurancePhoneReplace", str2);
        //??????????????????????????????
        String str3 = ListUtils.listToReplaceStr(list2, "personName", "id");
        model.addAttribute("insuranceNameReplace", str3);

        //??????????????????????????????
        QueryWrapper<YcInsuranceCompanyEntity> queryWrapper3 = new QueryWrapper<>();
        queryWrapper3.eq("COMPANY_STATE",0);
        List<YcInsuranceCompanyEntity> list3 = ycInsuranceCompanyService.list(queryWrapper3);
        String str4 = ListUtils.listToReplaceStr(list3, "companyName", "id");
        model.addAttribute("companyReplace", str4);


        //?????????????????????
        QueryWrapper<YcSchoolEntity> queryWrapper4 = new QueryWrapper<>();
        queryWrapper4.eq("STATE",0);
        queryWrapper4.eq("LEVEL",2);
        List<YcSchoolEntity> list4 = ycSchoolService.list(queryWrapper4);
        String schoolReplace = ListUtils.listToReplaceStr(list4, "name", "id");

        model.addAttribute("schoolReplace", schoolReplace);
        return "yc/insurance/school/insured/list";
    }

    @RequestMapping(value = "/company/list", method = RequestMethod.GET)
    public String clist(Model model) {
        return "yc/insurance/company/list";
    }

    @RequestMapping(value = "/person/list", method = RequestMethod.GET)
    public String plist(Model model) {
        // ??????????????????????????????????????????????????????
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
     *  	??????????????????
     * @return void
     * @author ?????????
     * @time 2020???1???25??? ??????9:46:12
     */
    @RequestMapping("/school/datagrid")
    public void datagridsi(YcSchoolEntity ycSchoolEntity, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        ycSchoolEntity.setLevel(2);
        //??????????????????
        QueryWrapper<YcSchoolEntity> queryWrapper = QueryUtils.installQueryWrapper(ycSchoolEntity, request.getParameterMap(), dataGrid);
        //????????????
        IPage<YcSchoolEntity> lstResult = ycSchoolService.page(new Page<YcSchoolEntity>(dataGrid.getPage(), dataGrid.getRows()), queryWrapper);
        //????????????
        ResponseUtil.writeJson(response, dataGrid, lstResult);
    }

    @RequestMapping("/company/datagrid")
    public void datagrid(YcInsuranceCompanyEntity ycInsuranceCompanyEntity, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        //??????????????????
        QueryWrapper<YcInsuranceCompanyEntity> queryWrapper = QueryUtils.installQueryWrapper(ycInsuranceCompanyEntity, request.getParameterMap(), dataGrid);
        //????????????
        IPage<YcInsuranceCompanyEntity> lstResult = ycInsuranceCompanyService.page(new Page<YcInsuranceCompanyEntity>(dataGrid.getPage(), dataGrid.getRows()), queryWrapper);
        //????????????
        ResponseUtil.writeJson(response, dataGrid, lstResult);
    }

    @RequestMapping("/person/datagrid")
    public void datagrid2(YcInsurancePersonEntity ycInsurancePersonEntity, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        //??????????????????
        QueryWrapper<YcInsurancePersonEntity> queryWrapper = QueryUtils.installQueryWrapper(ycInsurancePersonEntity, request.getParameterMap(), dataGrid);
        //????????????
        IPage<YcInsurancePersonEntity> lstResult = ycInsurancePersonService.page(new Page<YcInsurancePersonEntity>(dataGrid.getPage(), dataGrid.getRows()), queryWrapper);
        //????????????
        ResponseUtil.writeJson(response, dataGrid, lstResult);
    }

    @RequestMapping("/area/datagrid")
    public void datagrid3(YcAreaEntity ycAreaEntity, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        //??????????????????
        QueryWrapper<YcAreaEntity> queryWrapper = QueryUtils.installQueryWrapper(ycAreaEntity, request.getParameterMap(), dataGrid);
        //????????????
        IPage<YcAreaEntity> lstResult = ycAreaService.page(new Page<YcAreaEntity>(dataGrid.getPage(), dataGrid.getRows()), queryWrapper);
        //????????????
        ResponseUtil.writeJson(response, dataGrid, lstResult);
    }

    @RequestMapping("/school/insured/datagrid")
    public void datagrid4(YcSchoolInsuredEntity ycSchoolEntity, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        //??????????????????
        QueryWrapper<YcSchoolInsuredEntity> queryWrapper = QueryUtils.installQueryWrapper(ycSchoolEntity, request.getParameterMap(), dataGrid);
        //????????????
        IPage<YcSchoolInsuredEntity> lstResult = ycSchoolInsuredService.page(new Page<YcSchoolInsuredEntity>(dataGrid.getPage(), dataGrid.getRows()), queryWrapper);
        //????????????
        ResponseUtil.writeJson(response, dataGrid, lstResult);
    }

    @RequestMapping("/school/grade/datagrid")
    public void datagrid5(YcSchoolEntity ycSchoolEntity, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        ycSchoolEntity.setLevel(3);
        //??????????????????
        QueryWrapper<YcSchoolEntity> queryWrapper = QueryUtils.installQueryWrapper(ycSchoolEntity, request.getParameterMap(), dataGrid);
        //????????????
        IPage<YcSchoolEntity> lstResult = ycSchoolService.page(new Page<YcSchoolEntity>(dataGrid.getPage(), dataGrid.getRows()), queryWrapper);
        //????????????
        ResponseUtil.writeJson(response, dataGrid, lstResult);
    }

    @RequestMapping("/school/class/datagrid")
    public void datagrid6(YcSchoolEntity ycSchoolEntity, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        ycSchoolEntity.setLevel(4);
        //??????????????????
        QueryWrapper<YcSchoolEntity> queryWrapper = QueryUtils.installQueryWrapper(ycSchoolEntity, request.getParameterMap(), dataGrid);
        //????????????
        IPage<YcSchoolEntity> lstResult = ycSchoolService.page(new Page<YcSchoolEntity>(dataGrid.getPage(), dataGrid.getRows()), queryWrapper);
        //????????????
        ResponseUtil.writeJson(response, dataGrid, lstResult);
    }

    /**
     *
     * @description ?????????????????????
     * @return ModelAndView
     */
    @RequestMapping("/company/addorupdate")
    public ModelAndView addorupdate(YcInsuranceCompanyEntity ycInsuranceCompanyEntity, HttpServletRequest req) {
        ModelAndView view = new ModelAndView("yc/insurance/company/company");

        if(StringUtils.isEmpty(ycInsuranceCompanyEntity.getId())) {
            //??????
            ycInsuranceCompanyEntity = new YcInsuranceCompanyEntity();
            view.addObject("ycInsuranceCompanyEntity", ycInsuranceCompanyEntity);
        }else {
            //??????
            ycInsuranceCompanyEntity = ycInsuranceCompanyService.getById(ycInsuranceCompanyEntity.getId());
            view.addObject("ycInsuranceCompanyEntity", ycInsuranceCompanyEntity);
        }
        return view;
    }

    @RequestMapping("/school/grade/addorupdate")
    public ModelAndView schoolgradeaddorupdate(YcSchoolEntity ycSchoolEntity, HttpServletRequest req) {
        ModelAndView view = new ModelAndView("yc/insurance/school/grade/grade");
        String parentId = "";
        if(ycSchoolEntity.getParentId()!=null && !ycSchoolEntity.getParentId().isEmpty()){
            parentId = ycSchoolEntity.getParentId();
        }
        if(StringUtils.isEmpty(ycSchoolEntity.getId())) {
            //??????
            ycSchoolEntity = new YcSchoolEntity();
            ycSchoolEntity.setParentId(parentId);
            view.addObject("ycSchoolEntity", ycSchoolEntity);
        }else {
            //??????
            ycSchoolEntity = ycSchoolService.getById(ycSchoolEntity.getId());
            view.addObject("ycSchoolEntity", ycSchoolEntity);
        }
        return view;
    }

    @RequestMapping("/school/class/addorupdate")
    public ModelAndView schoolclassaddorupdate(YcSchoolEntity ycSchoolEntity, HttpServletRequest req) {
        ModelAndView view = new ModelAndView("yc/insurance/school/schoolclass/schoolclass");
        String parentId = "";
        if(ycSchoolEntity.getParentId()!=null && !ycSchoolEntity.getParentId().isEmpty()){
            parentId = ycSchoolEntity.getParentId();
        }
        if(StringUtils.isEmpty(ycSchoolEntity.getId())) {
            //??????
            ycSchoolEntity = new YcSchoolEntity();
            ycSchoolEntity.setParentId(parentId);
            view.addObject("ycSchoolEntity", ycSchoolEntity);
        }else {
            //??????
            ycSchoolEntity = ycSchoolService.getById(ycSchoolEntity.getId());
            view.addObject("ycSchoolEntity", ycSchoolEntity);
        }
        return view;
    }

    @RequestMapping("/person/addorupdate")
    public ModelAndView paddorupdate(YcInsurancePersonEntity ycInsurancePersonEntity, HttpServletRequest req) {
        ModelAndView view = new ModelAndView("yc/insurance/person/person");

        if(StringUtils.isEmpty(ycInsurancePersonEntity.getId())) {
            //??????
            ycInsurancePersonEntity = new YcInsurancePersonEntity();
            view.addObject("ycInsurancePersonEntity", ycInsurancePersonEntity);
        }else {
            //??????
            ycInsurancePersonEntity = ycInsurancePersonService.getById(ycInsurancePersonEntity.getId());
            view.addObject("ycInsurancePersonEntity", ycInsurancePersonEntity);
        }
        //???????????????????????????
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
            //??????
            ycAreaEntity = new YcAreaEntity();
        }else {
            //??????
            ycAreaEntity = ycAreaService.getById(ycAreaEntity.getId());
        }
        view.addObject("ycAreaEntity", ycAreaEntity);
        return view;
    }

    @RequestMapping("/school/addorupdate")
    public ModelAndView schooladdorupdate(YcSchoolEntity ycSchoolEntity, HttpServletRequest req) {
        ModelAndView view = new ModelAndView("yc/insurance/school/school");
        //?????????????????????????????????
        QueryWrapper<YcAreaEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("AREA_STATE",0);
        List<YcAreaEntity> areaList = ycAreaService.list(queryWrapper);
        view.addObject("areaList", areaList);
        List<SysDicValueEntity> xueliList = SystemUtils.getDictionaryLst("xueli_type");
        view.addObject("xueliList", xueliList);

        if(StringUtils.isEmpty(ycSchoolEntity.getId())) {
            //??????
            ycSchoolEntity = new YcSchoolEntity();
        }else {
            //??????
            ycSchoolEntity = ycSchoolService.getById(ycSchoolEntity.getId());
        }
        view.addObject("ycSchoolEntity", ycSchoolEntity);
        return view;
    }

    @RequestMapping("/school/insured/addorupdate")
    public ModelAndView schoolinsuredaddorupdate(YcSchoolInsuredEntity ycSchoolInsuredEntity, HttpServletRequest req) {
        ModelAndView view = new ModelAndView("yc/insurance/school/insured/school");
        //?????????????????????????????????
        List<SysDicValueEntity> xueliList = SystemUtils.getDictionaryLst("xueli_type");
        view.addObject("xueliList", xueliList);

        //???????????????????????????????????????
        List<SysDicValueEntity> insuranceList = SystemUtils.getDictionaryLst("insurance_type");
        view.addObject("insuranceList", insuranceList);

        //???????????????????????????
        List<SysDicValueEntity> gradList = SystemUtils.getDictionaryLst("school_grade");
        view.addObject("gradList", gradList);

        QueryWrapper<YcAreaEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("AREA_STATE",0);
        List<YcAreaEntity> areaList = ycAreaService.list(queryWrapper);
        view.addObject("areaList", areaList);

        //?????????????????????
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
                getYcTreeKeyValue(lstChildren, lstTrees, "??????");
            }
        }
        view.addObject("lstTrees", lstTrees);

        if(StringUtils.isEmpty(ycSchoolInsuredEntity.getId())) {
            //??????
            ycSchoolInsuredEntity = new YcSchoolInsuredEntity();
        }else {
            //??????
            ycSchoolInsuredEntity = ycSchoolInsuredService.getById(ycSchoolInsuredEntity.getId());
            //????????????ID??????????????????
            String schoolId = ycSchoolInsuredEntity.getSchoolId();
            YcSchoolEntity ycSchoolEntity = ycSchoolService.getById(schoolId);
            view.addObject("schoolName", ycSchoolEntity.getName());
        }
        view.addObject("ycSchoolInsuredEntity", ycSchoolInsuredEntity);
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
                getYcTreeKeyValue(lstChildren, lstTrees, "??????");
            }
        }
    }
    /**
     * @return AjaxJson
     */
    @RequestMapping("/school/insured/save")
    @ResponseBody
    @Log(type = LogType.save, name = "???????????????????????????", memo = "?????????????????????????????????????????????")
    public AjaxJson schoolInsuredSave(YcSchoolInsuredEntity ycSchoolInsuredEntity, HttpServletRequest request) {
        AjaxJson j = new AjaxJson();
        String schoolId = ycSchoolInsuredEntity.getSchoolId();
        YcSchoolEntity ycSchoolEntity = ycSchoolService.getById(schoolId);
        if(ycSchoolInsuredEntity != null ){
            ycSchoolInsuredEntity.setSchoolAreaId(ycSchoolEntity.getParentId());
        }
        try {
            String InsurancePersonId = ycSchoolInsuredEntity.getInsurancePersonId();
            QueryWrapper<YcInsurancePersonEntity> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("ID",InsurancePersonId);
            if (StringUtils.isNotEmpty(ycSchoolInsuredEntity.getId())) {
                List<YcInsurancePersonEntity> listPerson = ycInsurancePersonService.list(queryWrapper);
                if(listPerson.size()>0){
                    ycSchoolInsuredEntity.setInsuranceCompanyId(listPerson.get(0).getCompanyId());
                }
                //????????????
                YcSchoolInsuredEntity tmp = ycSchoolInsuredService.getById(ycSchoolInsuredEntity.getId());
                MyBeanUtils.copyBeanNotNull2Bean(ycSchoolInsuredEntity, tmp);
                ycSchoolInsuredService.saveOrUpdate(tmp);
            }else {
                //????????????
                List<YcInsurancePersonEntity> listPerson = ycInsurancePersonService.list(queryWrapper);
                if(listPerson.size()>0){
                    ycSchoolInsuredEntity.setInsuranceCompanyId(listPerson.get(0).getCompanyId());
                }
                ycSchoolInsuredService.save(ycSchoolInsuredEntity);
            }
        }catch(Exception e) {
            log.error("????????????????????????????????????????????????:" + e.getMessage());
            j.setSuccess(false);
            j.setMsg("???????????????????????????");
            e.printStackTrace();
        }
        return j;
    }

    @RequestMapping("/school/grade/save")
    @ResponseBody
    @Log(type = LogType.save, name = "????????????????????????", memo = "??????????????????????????????????????????")
    public AjaxJson schoogradelSave(YcSchoolEntity ycSchoolEntity, HttpServletRequest request) {
        AjaxJson j = new AjaxJson();
        ycSchoolEntity.setLevel(3);
        try {
            if (StringUtils.isNotEmpty(ycSchoolEntity.getId())) {
                //????????????
                YcSchoolEntity tmp = ycSchoolService.getById(ycSchoolEntity.getId());
                MyBeanUtils.copyBeanNotNull2Bean(ycSchoolEntity, tmp);
                ycSchoolService.saveOrUpdate(tmp);
            }else {
                //????????????
                ycSchoolService.save(ycSchoolEntity);
            }
        }catch(Exception e) {
            log.error("?????????????????????????????????????????????:" + e.getMessage());
            j.setSuccess(false);
            j.setMsg("????????????????????????");
            e.printStackTrace();
        }
        return j;
    }

    @RequestMapping("/school/class/save")
    @ResponseBody
    @Log(type = LogType.save, name = "????????????????????????", memo = "??????????????????????????????????????????")
    public AjaxJson schoolclasslSave(YcSchoolEntity ycSchoolEntity, HttpServletRequest request) {
        AjaxJson j = new AjaxJson();
        ycSchoolEntity.setLevel(4);
        try {
            if (StringUtils.isNotEmpty(ycSchoolEntity.getId())) {
                //????????????
                YcSchoolEntity tmp = ycSchoolService.getById(ycSchoolEntity.getId());
                MyBeanUtils.copyBeanNotNull2Bean(ycSchoolEntity, tmp);
                ycSchoolService.saveOrUpdate(tmp);
            }else {
                //????????????
                ycSchoolService.save(ycSchoolEntity);
            }
        }catch(Exception e) {
            log.error("?????????????????????????????????????????????:" + e.getMessage());
            j.setSuccess(false);
            j.setMsg("????????????????????????");
            e.printStackTrace();
        }
        return j;
    }

    @RequestMapping("/school/save")
    @ResponseBody
    @Log(type = LogType.save, name = "??????????????????", memo = "??????????????????????????????????????????")
    public AjaxJson schoolSave(YcSchoolEntity ycSchoolEntity, HttpServletRequest request) {
        AjaxJson j = new AjaxJson();
        ycSchoolEntity.setLevel(2);
        try {
            if (StringUtils.isNotEmpty(ycSchoolEntity.getId())) {
                //????????????
                YcSchoolEntity tmp = ycSchoolService.getById(ycSchoolEntity.getId());
                MyBeanUtils.copyBeanNotNull2Bean(ycSchoolEntity, tmp);
                ycSchoolService.saveOrUpdate(tmp);
            }else {
                //????????????
                ycSchoolService.save(ycSchoolEntity);
            }
        }catch(Exception e) {
            log.error("???????????????????????????????????????:" + e.getMessage());
            j.setSuccess(false);
            j.setMsg("??????????????????");
            e.printStackTrace();
        }
        return j;
    }

    /**
     * @return AjaxJson
     */
    @RequestMapping("/area/save")
    @ResponseBody
    @Log(type = LogType.save, name = "??????????????????", memo = "????????????????????????????????????")
    public AjaxJson areaSave(YcAreaEntity ycAreaEntity, HttpServletRequest request) {
        AjaxJson j = new AjaxJson();
        try {
            if (StringUtils.isNotEmpty(ycAreaEntity.getId())) {
                //????????????
                YcAreaEntity tmp = ycAreaService.getById(ycAreaEntity.getId());
                MyBeanUtils.copyBeanNotNull2Bean(ycAreaEntity, tmp);
                ycAreaService.saveOrUpdate(tmp);
            }else {
                //????????????
                ycAreaService.save(ycAreaEntity);
            }
        }catch(Exception e) {
            log.error("???????????????????????????????????????:" + e.getMessage());
            j.setSuccess(false);
            j.setMsg("??????????????????");
            e.printStackTrace();
        }
        return j;
    }

    /**
     * @return AjaxJson
     */
    @RequestMapping("/company/save")
    @ResponseBody
    @Log(type = LogType.save, name = "????????????????????????", memo = "??????????????????????????????????????????")
    public AjaxJson companySave(YcInsuranceCompanyEntity ycInsuranceCompanyEntity, HttpServletRequest request) {
        AjaxJson j = new AjaxJson();
        try {
            if (StringUtils.isNotEmpty(ycInsuranceCompanyEntity.getId())) {
                //????????????
                YcInsuranceCompanyEntity tmp = ycInsuranceCompanyService.getById(ycInsuranceCompanyEntity.getId());
                MyBeanUtils.copyBeanNotNull2Bean(ycInsuranceCompanyEntity, tmp);
                ycInsuranceCompanyService.saveOrUpdate(tmp);
            }else {
                //????????????
                ycInsuranceCompanyService.save(ycInsuranceCompanyEntity);
            }
        }catch(Exception e) {
            log.error("?????????????????????????????????????????????:" + e.getMessage());
            j.setSuccess(false);
            j.setMsg("????????????????????????");
            e.printStackTrace();
        }
        return j;
    }

    /**
     * @return AjaxJson
     */
    @RequestMapping("/person/save")
    @ResponseBody
    @Log(type = LogType.save, name = "??????????????????????????????", memo = "????????????????????????????????????????????????")
    public AjaxJson personSave(YcInsurancePersonEntity ycInsurancePersonEntity, HttpServletRequest request) {
        AjaxJson j = new AjaxJson();
        try {

            if(StringUtils.isEmpty(ycInsurancePersonEntity.getCompanyId())) {
                j.setSuccess(false);
                j.setMsg("????????????????????????");
                return j;
            }
            if(StringUtils.isEmpty(ycInsurancePersonEntity.getPersonName())) {
                j.setSuccess(false);
                j.setMsg("????????????");
                return j;
            }

            if(StringUtils.isEmpty(ycInsurancePersonEntity.getPersonPhone())) {
                j.setSuccess(false);
                j.setMsg("??????????????????");
                return j;
            }




            if (StringUtils.isNotEmpty(ycInsurancePersonEntity.getId())) {
                //????????????
                YcInsurancePersonEntity tmp = ycInsurancePersonService.getById(ycInsurancePersonEntity.getId());
                MyBeanUtils.copyBeanNotNull2Bean(ycInsurancePersonEntity, tmp);
                ycInsurancePersonService.saveOrUpdate(tmp);

                //?????????????????????????????????
                String insuranceCompanyId = ycInsurancePersonEntity.getCompanyId();
                String insurancePersonId = ycInsurancePersonEntity.getId();
                QueryWrapper<YcSchoolInsuredEntity> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("INSURANCE_PERSON_ID",insurancePersonId);
                List<YcSchoolInsuredEntity> ycSchoolEntities = ycSchoolInsuredService.list(queryWrapper);
                for(YcSchoolInsuredEntity ycSchoolEntity :ycSchoolEntities){
                        ycSchoolEntity.setInsuranceCompanyId(insuranceCompanyId);
                    ycSchoolInsuredService.saveOrUpdate(ycSchoolEntity);
                }
            }else {
                ycInsurancePersonService.save(ycInsurancePersonEntity);
            }
        }catch(Exception e) {
            log.error("???????????????????????????????????????????????????:" + e.getMessage());
            j.setSuccess(false);
            j.setMsg("??????????????????????????????");
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
    @Log(type = LogType.del, name = "????????????????????????", memo = "???????????????????????????")
    public AjaxJson cdel(String id, HttpServletRequest req) {
        AjaxJson j = new AjaxJson();
        try {
            ycInsuranceCompanyService.removeById(id);
        }catch(Exception e) {
            log.error("????????????????????????????????????????????????{}", e.getMessage());
            j.setSuccess(false);
            j.setMsg("??????????????????????????????");
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
    @Log(type = LogType.del, name = "??????????????????????????????", memo = "?????????????????????????????????")
    public AjaxJson pdel(String id, HttpServletRequest req) {
        AjaxJson j = new AjaxJson();
        try {
            ycInsurancePersonService.removeById(id);
        }catch(Exception e) {
            log.error("??????????????????????????????????????????????????????{}", e.getMessage());
            j.setSuccess(false);
            j.setMsg("????????????????????????????????????");
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
    @Log(type = LogType.del, name = "??????????????????", memo = "?????????????????????")
    public AjaxJson adel(String id, HttpServletRequest req) {
        AjaxJson j = new AjaxJson();
        try {
            YcAreaEntity areaEntity = ycAreaService.getById(id);
            areaEntity.setAreaState(99);
            ycAreaService.saveOrUpdate(areaEntity);
        }catch(Exception e) {
            log.error("??????????????????????????????????????????{}", e.getMessage());
            j.setSuccess(false);
            j.setMsg("????????????????????????");
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
    @Log(type = LogType.del, name = "???????????????????????????", memo = "??????????????????????????????")
    public AjaxJson schoolInsuredDel(String id, HttpServletRequest req) {
        AjaxJson j = new AjaxJson();
        try {
            YcSchoolInsuredEntity ycSchoolInsuredEntity = ycSchoolInsuredService.getById(id);
            ycSchoolInsuredEntity.setSchoolState(99);
            ycSchoolInsuredService.saveOrUpdate(ycSchoolInsuredEntity);
        }catch(Exception e) {
            log.error("???????????????????????????????????????????????????{}", e.getMessage());
            j.setSuccess(false);
            j.setMsg("?????????????????????????????????");
            e.printStackTrace();
        }
        return j;
    }

    /**
     * @params
     * @return AjaxJson
     */
    @RequestMapping("/school/del")
    @ResponseBody
    @Log(type = LogType.del, name = "??????????????????", memo = "?????????????????????")
    public AjaxJson schooldel(String id, HttpServletRequest req) {
        AjaxJson j = new AjaxJson();
        try {
            YcSchoolEntity ycSchoolEntity = ycSchoolService.getById(id);
            ycSchoolEntity.setState(99);
            ycSchoolService.saveOrUpdate(ycSchoolEntity);
        }catch(Exception e) {
            log.error("??????????????????????????????????????????{}", e.getMessage());
            j.setSuccess(false);
            j.setMsg("????????????????????????");
            e.printStackTrace();
        }
        return j;
    }
}

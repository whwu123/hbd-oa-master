package com.active4j.hr.yc.hbd;

import com.active4j.hr.base.controller.BaseController;
import com.active4j.hr.core.model.AjaxJson;
import com.active4j.hr.yc.entity.YcAreaEntity;
import com.active4j.hr.yc.entity.YcSchoolEntity;
import com.active4j.hr.yc.service.YcAreaService;
import com.active4j.hr.yc.service.YcSchoolService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@Slf4j
@RequestMapping("/webhbd")
public class IndexController extends BaseController {
    @Autowired
    private YcAreaService ycAreaService;
    @Autowired
    private YcSchoolService ycSchoolService;

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home(Model model) {
        return "yc/hbd/ychome";
    }

    @RequestMapping(value = "/diqu", method = RequestMethod.GET)
    public String diqu(Model model) {
        return "yc/hbd/diqu";
    }


    @RequestMapping(value = "/tiaokuan", method = RequestMethod.GET)
    public String tiaokuan(Model model) {
        return "yc/hbd/tiaokuan";
    }

    @RequestMapping(value = "/tebie", method = RequestMethod.GET)
    public String tebie(Model model) {
        return "yc/hbd/tebie";
    }

    @RequestMapping(value = "/checkPayMoney", method = RequestMethod.GET)
    public String checkPayMoney(Model model,String studentCard) {
        if(studentCard!=null && !studentCard.isEmpty()){

            model.addAttribute("studentCard",studentCard);
        }
        //没有学生信息就
        //查询业务区县列表
        QueryWrapper<YcAreaEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("AREA_STATE",0);
        List<YcAreaEntity> areaList = ycAreaService.list(queryWrapper);
        model.addAttribute("areaList",areaList);
        return "yc/hbd/studentInformation";
    }

    @RequestMapping(value = "/selectData", method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson selectData( String id) {
        AjaxJson j = new AjaxJson();
        if(id!=null && !id.isEmpty()){
            QueryWrapper<YcSchoolEntity> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("PARENT_ID",id);
            List<YcSchoolEntity> schoolList = ycSchoolService.list(queryWrapper);
            j.setObj(schoolList);
        }
        return j;
    }
}

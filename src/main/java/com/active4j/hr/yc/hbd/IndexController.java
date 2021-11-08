package com.active4j.hr.yc.hbd;

import com.active4j.hr.base.controller.BaseController;
import com.active4j.hr.core.model.AjaxJson;
import com.active4j.hr.yc.entity.YcAreaEntity;
import com.active4j.hr.yc.entity.YcSchoolEntity;
import com.active4j.hr.yc.entity.YcStudentInformationEntity;
import com.active4j.hr.yc.service.YcAreaService;
import com.active4j.hr.yc.service.YcSchoolService;
import com.active4j.hr.yc.service.YcStudentInformationService;
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
    @Autowired
    private YcStudentInformationService ycStudentInformationService;

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
        //查询业务区县列表
        QueryWrapper<YcAreaEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("AREA_STATE",0);
        List<YcAreaEntity> areaList = ycAreaService.list(queryWrapper);
        model.addAttribute("areaList",areaList);
        if(studentCard!=null && !studentCard.isEmpty()){
            QueryWrapper<YcStudentInformationEntity> queryWrapper2 = new QueryWrapper<>();
            queryWrapper2.eq("STUDENT_CARD",studentCard);
            List<YcStudentInformationEntity> informationList = ycStudentInformationService.list(queryWrapper2);

            if(informationList.size()>0){
                YcStudentInformationEntity ycStudentInformationEntity =informationList.get(0);
                model.addAttribute("information",ycStudentInformationEntity);
                //根据区域ID查询该区域下的所有学校
                QueryWrapper<YcSchoolEntity> queryWrapper3 = new QueryWrapper<>();
                queryWrapper3.eq("PARENT_ID",ycStudentInformationEntity.getAreaId());
                List<YcSchoolEntity> ycSchoolList = ycSchoolService.list(queryWrapper3);
                model.addAttribute("ycSchoolList",ycSchoolList);

                QueryWrapper<YcSchoolEntity> queryWrapper4 = new QueryWrapper<>();
                queryWrapper4.eq("PARENT_ID",ycStudentInformationEntity.getSchoolId());
                List<YcSchoolEntity> ycSchoolList2 = ycSchoolService.list(queryWrapper4);
                model.addAttribute("ycSchoolList2",ycSchoolList2);

                QueryWrapper<YcSchoolEntity> queryWrapper5 = new QueryWrapper<>();
                queryWrapper5.eq("PARENT_ID",ycStudentInformationEntity.getNianjiId());
                List<YcSchoolEntity> ycSchoolList3 = ycSchoolService.list(queryWrapper5);
                model.addAttribute("ycSchoolList3",ycSchoolList3);



            }else{
                YcStudentInformationEntity ycStudentInformationEntity = new YcStudentInformationEntity();
                ycStudentInformationEntity.setStudentCard(studentCard);
                model.addAttribute("information",ycStudentInformationEntity);
            }
        }
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

    @RequestMapping(value = "/student/save", method = RequestMethod.POST)
    public String studentSave(Model model, YcStudentInformationEntity informationEntity) {
        if(informationEntity!=null){
            ycStudentInformationService.saveOrUpdate(informationEntity);
        }
        return "yc/hbd/list";
    }
}

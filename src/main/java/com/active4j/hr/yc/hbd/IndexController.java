package com.active4j.hr.yc.hbd;

import com.active4j.hr.base.controller.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@Slf4j
@RequestMapping("/hbd")
public class IndexController extends BaseController {

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home(Model model) {
        return "yc/hbd/ychome";
    }

    @RequestMapping(value = "/diqu", method = RequestMethod.GET)
    public String diqu(Model model) {
        return "yc/hbd/diqu";
    }
}

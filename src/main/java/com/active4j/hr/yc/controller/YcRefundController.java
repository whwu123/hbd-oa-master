package com.active4j.hr.yc.controller;


import com.active4j.hr.base.controller.BaseController;
import com.active4j.hr.core.annotation.Log;
import com.active4j.hr.core.beanutil.MyBeanUtils;
import com.active4j.hr.core.model.AjaxJson;
import com.active4j.hr.core.model.LogType;
import com.active4j.hr.core.query.QueryUtils;
import com.active4j.hr.core.shiro.ShiroUtils;
import com.active4j.hr.core.util.ListUtils;
import com.active4j.hr.core.util.ResponseUtil;
import com.active4j.hr.core.web.tag.model.DataGrid;
import com.active4j.hr.system.entity.SysDeptEntity;
import com.active4j.hr.system.entity.SysRoleEntity;
import com.active4j.hr.system.entity.SysUserEntity;
import com.active4j.hr.system.model.ActiveUser;
import com.active4j.hr.system.model.SysUserModel;
import com.active4j.hr.system.service.SysDeptService;
import com.active4j.hr.system.service.SysUserService;
import com.active4j.hr.yc.entity.*;
import com.active4j.hr.yc.service.YcPaymentRecordService;
import com.active4j.hr.yc.service.YcRefunService;
import com.active4j.hr.yc.service.YcStudentService;
import com.active4j.hr.yc.service.YcUpdateStulogService;
import com.active4j.hr.yc.util.ExcelUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/ycRefunController")
public class YcRefundController extends BaseController {

	@Autowired
	private YcRefunService ycRefunService;


	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(Model model) {
		return "yc/refund/index";
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
	public void datagrid(YcRefundEntity ycRefundEntity, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		if(ycRefundEntity.getState() == 0){
			ycRefundEntity.setState(1);
		}
		//拼接查询条件
		QueryWrapper<YcRefundEntity> queryWrapper = QueryUtils.installQueryWrapper(ycRefundEntity, request.getParameterMap(), dataGrid);

		//执行查询
		IPage<YcRefundEntity> lstResult = ycRefunService.page(new Page<YcRefundEntity>(dataGrid.getPage(), dataGrid.getRows()), queryWrapper);

		//输出结果
		ResponseUtil.writeJson(response, dataGrid, lstResult);

	}

	/**
	 *
	 * @description
	 *  	新增或编辑跳转
	 * @return ModelAndView
	 * @author 麻木神
	 * @time 2020年1月28日 下午3:48:35
	 */
	@RequestMapping("/addorupdate")
	public ModelAndView addorupdate(YcRefundEntity ycRefundEntity, HttpServletRequest req) {
		ModelAndView view = new ModelAndView("yc/refund/refund");

		if(StringUtils.isEmpty(ycRefundEntity.getId())) {
			//新增
			ycRefundEntity = new YcRefundEntity();
			view.addObject("refund", ycRefundEntity);
		}else {
			//编辑
			ycRefundEntity = ycRefunService.getById(ycRefundEntity.getId());
			view.addObject("refund", ycRefundEntity);
		}
		return view;
	}


	/**
	 *
	 * @param req
	 * @return
	 */
	@RequestMapping("/saveUpdate")
	@ResponseBody
	@Log(type = LogType.save, name = "保存退费登记信息", memo = "新增或编辑保存了退费登记信息")
	public AjaxJson saveUser(HttpServletRequest req, YcRefundEntity ycRefundEntity) {
		AjaxJson j = new AjaxJson();
		try {
			ycRefundEntity.setState(1);
			if(StringUtils.isEmpty(ycRefundEntity.getId())) {
				if(StringUtils.isEmpty(ycRefundEntity.getStudentName())) {
					j.setSuccess(false);
					j.setMsg("学生姓名不能为空");
					return j;
				}

				if(StringUtils.isEmpty(ycRefundEntity.getPayNumber())) {
					j.setSuccess(false);
					j.setMsg("支付流水单号不能为空");
					return j;
				}


				if(StringUtils.isEmpty(ycRefundEntity.getStudentCard())) {
					j.setSuccess(false);
					j.setMsg("学生身份证不能为空");
					return j;
				}
				//状态
				ycRefunService.save(ycRefundEntity);
			}else {
				//编辑保存
				YcRefundEntity tmp = ycRefunService.getById(ycRefundEntity.getId());
				MyBeanUtils.copyBeanNotNull2Bean(ycRefundEntity, tmp);
				ycRefunService.saveOrUpdate(tmp);
			}
		}catch(Exception e) {
			log.error("保存退费登记信息报错，错误信息:" + e.getMessage());
			j.setSuccess(false);
			j.setMsg("保存退费登记错误");
			e.printStackTrace();
		}
		return j;
	}

	/**
	 *
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/auditingStu", method = RequestMethod.POST)
	@ResponseBody
	@Log(type = LogType.update, name = "审核退费登记信息", memo = "审核或编辑保存了退费登记信息")
	public AjaxJson auditingStu(HttpServletRequest req, String id) {
		AjaxJson j = new AjaxJson();
		try {
			if(!StringUtils.isEmpty(id)) {
				//状态
				YcRefundEntity  ycRefundEntity = ycRefunService.getById(id);
				if(ycRefundEntity!=null){
					ycRefundEntity.setState(2);
				}
				ycRefunService.saveOrUpdate(ycRefundEntity);
				j.setMsg("审核退费记录《"+ycRefundEntity.getStudentName()+"》信息成功");
			}

		}catch(Exception e) {
			log.error("审核退费登记信息报错，错误信息:" + e.getMessage());
			j.setSuccess(false);
			j.setMsg("审核退费登记错误");
			e.printStackTrace();
		}
		return j;
	}


	/**
	 * 导出报表
	 * @return
	 */
	@RequestMapping(value = "/export")
	@ResponseBody
	public void export(Integer state ,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) throws Exception {

		//获取数据
		//拼接查询条件
		QueryWrapper<YcRefundEntity> queryWrapper = new QueryWrapper<>();
		if(state == null){
			state = 1;
		}
		queryWrapper.eq("yc_state",state);
		queryWrapper.orderByDesc("CREATE_DATE");
		//执行查询
		List<YcRefundEntity> list = ycRefunService.list(queryWrapper);

		List<YcStudentModel> listNew = new ArrayList<>();

		//excel标题
		String[] title = {"支付流水号","学生姓名","学生身份证","家长联系电话","购买金额","退款理由"};
		//excel文件名
		String fileName = "学生退款记录表"+System.currentTimeMillis()+".xls";
		//sheet名
		String sheetName = "学生退款记录表";
		String[][] content = new String[list.size()+1][title.length];
		for (int i = 0; i < list.size(); i++) {
			YcRefundEntity obj = list.get(i);
			content[i][0] = obj.getPayNumber();
			content[i][1] = obj.getStudentName();
			content[i][2] = obj.getStudentCard();
			content[i][3] = obj.getTbrPhone();
			content[i][4] = obj.getPayMoney();
			content[i][5] = obj.getContent();

		}
		//创建HSSFWorkbook
		HSSFWorkbook wb = ExcelUtil.getHSSFWorkbook(sheetName, title, content, null);
		//响应到客户端
		try {
			this.setResponseHeader(response, fileName);
			OutputStream os = response.getOutputStream();
			wb.write(os);
			os.flush();
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//发送响应流方法
	public void setResponseHeader(HttpServletResponse response, String fileName) {
		try {
			try {
				fileName = new String(fileName.getBytes(),"ISO8859-1");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			response.setContentType("application/octet-stream;charset=ISO8859-1");
			response.setHeader("Content-Disposition", "attachment;filename="+ fileName);
			response.addHeader("Pargam", "no-cache");
			response.addHeader("Cache-Control", "no-cache");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 *
	 * @description
	 *  	删除账户
	 * @params
	 * @return AjaxJson
	 * @author guyp
	 * @time 2020年2月8日 下午4:25:02
	 */
	@RequestMapping("/del")
	@ResponseBody
	@Log(type = LogType.del, name = "删除退费用户", memo = "删除了退费用户信息")
	public AjaxJson del(String id, HttpServletRequest req) {
		AjaxJson j = new AjaxJson();
		try {
			if(StringUtils.isEmpty(id)) {
				j.setSuccess(false);
				j.setMsg("请选择需要删除的用户");
				return j;
			}

			ActiveUser user = ShiroUtils.getSessionUser();
			if(StringUtils.equals(user.getId(), id)) {
				j.setSuccess(false);
				j.setMsg("不允许删除自身账户");
				return j;
			}
			//删除用户
			YcRefundEntity ycRefundEntity = ycRefunService.getById(id);
			if(ycRefundEntity!=null){
				ycRefundEntity.setState(-1);
			}
			ycRefunService.saveOrUpdate(ycRefundEntity);
			log.info("用户：" + user.getUserName() + "删除了id为：" + id + "的退费用户");
		}catch(Exception e) {
			log.error("删除退费用户报错，错误信息：{}", e.getMessage());
			j.setSuccess(false);
			j.setMsg("删除退费用户错误");
			e.printStackTrace();
		}
		return j;
	}
}

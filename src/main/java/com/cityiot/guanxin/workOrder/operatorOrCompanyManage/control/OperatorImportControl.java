package com.cityiot.guanxin.workOrder.operatorOrCompanyManage.control;

import com.cityiot.guanxin.common.utils.ImportExcelUtil;
import com.cityiot.guanxin.user.entity.Accountinfo;
import com.cityiot.guanxin.user.service.AccountinfoService;
import com.cityiot.guanxin.workOrder.operatorOrCompanyManage.entity.OperatorOrCompanyManage;
import com.cityiot.guanxin.workOrder.operatorOrCompanyManage.service.OperatorOrCompanyManageService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import swallow.framework.web.ApiResult;
import swallow.framework.web.BaseApiResult;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 运维人员/公司管理excel批量导入API
 *
 * @author Guoyingzhao
 */
@RestController
@Api(tags = "运维人员/公司管理excel批量导入API")
@RequestMapping("/api/OperatorImport")
public class OperatorImportControl {

    private static final Logger log = LoggerFactory.getLogger(OperatorImportControl.class);
    @Autowired
    private AccountinfoService accountinfoService;
    @Autowired
    private OperatorOrCompanyManageService service;

    /**
     * 运维人员批量导入
     *
     * @param file
     * @return
     */
    @RequiresPermissions(value={"004007005","SUPERADMIN"}, logical= Logical.OR)
    @ApiOperation(value = "运维人员批量导入",consumes="multipart/form-data")
   	@ApiImplicitParams({
   		@ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) 
   		})
    @RequestMapping(value = "/excel")
    @Transactional
    public BaseApiResult OperatorImportExcel(@RequestParam(value = "file") MultipartFile file) {
        log.info("----------start----------OperatorImportControl");
        if (file == null) {
            return BaseApiResult.failResult(500, "请重新上传文件！");
        }
        boolean flag = true;
        String msg = "";
        try {
            // 读取内容
            InputStream in = file.getInputStream();
            Map<String, Object> objMap = new ImportExcelUtil().getMapByExcel(in, file.getOriginalFilename(), false);

            @SuppressWarnings("unchecked")
            List<ArrayList<Object>> objList = (List<ArrayList<Object>>) objMap.get("list");
            if (objList != null && objList.size() > 1) {
                for (int i = 1; i < objList.size(); i++) {
                    List<Object> list = objList.get(i);
                    if (list.size() == 0 ||
                        StringUtils.isEmpty(list.get(0).toString()) ||
                        StringUtils.isEmpty(list.get(1).toString()) ||
                        StringUtils.isEmpty(list.get(2).toString()) ||
                        StringUtils.isEmpty(list.get(3).toString()) ||
                        StringUtils.isEmpty(list.get(4).toString()) ||
                        StringUtils.isEmpty(list.get(5).toString()) ||
                        StringUtils.isEmpty(list.get(6).toString()) ||
                        StringUtils.isEmpty(list.get(7).toString()) ) {
                        msg = StringUtils.isEmpty(msg) ? "第" + i : msg + "、" + i;
                        continue;
                    }
                    OperatorOrCompanyManage operatorManage = new OperatorOrCompanyManage();
                    operatorManage.setName(list.get(0).toString());//姓名/公司
                    operatorManage.setAccount(list.get(1).toString());//账户
                    operatorManage.setGender(Integer.parseInt(list.get(2).toString()));//性别
                    operatorManage.setPhone(list.get(3).toString());//电话号码
                    operatorManage.setStatus(Integer.parseInt(list.get(4).toString()));//在岗状态
                    operatorManage.setDisable(Integer.parseInt(list.get(5).toString()));//禁用状态
                    operatorManage.setOperatorType(Integer.parseInt(list.get(6).toString()));//类型
                    operatorManage.setRemark(list.get(7).toString());//备注
                    OperatorOrCompanyManage operatorNew = service.insertItem(operatorManage);
                    if (operatorNew != null) {
                        Accountinfo accountinfo = new Accountinfo();
                        accountinfo.setAccount(operatorNew.getAccount());
                        accountinfo.setPassword(operatorNew.getPassword());
                        accountinfo.setDid(operatorNew.getId().intValue());
                        accountinfo.setType(2);
                        int disable = operatorNew.getDisable() == 2 ? 1 : 0;
                        accountinfo.setDisable(disable);
                        accountinfo.setPhone(operatorNew.getPhone());
                        accountinfoService.insertItem(accountinfo);
                        operatorNew.setAccountid(accountinfo.getId());
                        service.updateItem(operatorNew);
                    }else {
                        msg = StringUtils.isEmpty(msg) ? "第" + i : msg + "、" + i;
                        continue;
                    }
                }
            } else {
                flag = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return BaseApiResult.failResult(500, "导入失败！" + e.getMessage());
        }
        if (!flag) {
            return BaseApiResult.failResult(500, "导入失败！");
        }
        msg = StringUtils.isEmpty(msg) ? "" : msg + "条数据添加失败！";
        log.info("----------end----------importDeviceInfo");
        return ApiResult.success("导入成功！" + msg);

    }

}

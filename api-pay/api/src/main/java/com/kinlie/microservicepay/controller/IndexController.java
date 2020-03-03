package com.kinlie.microservicepay.controller;

import com.kinlie.microservicepay.controller.base.BaseController;
import com.kinlie.microservicepay.core.ResultCode;
import com.kinlie.microservicepay.core.StatusCode;
import lombok.Data;
import org.springframework.web.bind.annotation.*;

/**
* @Author wwy
* @Description 测试类
* @Date 9:42 2019/12/31
* @Param  
* @return 
**/
@RestController
public class IndexController extends BaseController {

    @RequestMapping(value = { "/", "/api/1.0/index" } , method = RequestMethod.POST)
    public ResultCode index(){
        String index = "Hello,51kinlie.com";
        return new ResultCode(StatusCode.SUCCESS,index);
    }

    @RequestMapping(value = { "/test", "/api/1.0/test" }, method = RequestMethod.POST)
    public ResultCode index1(@RequestParam("userName") String userName){
        String index = "Hello,"+userName+"51kinlie.com";
        return new ResultCode(StatusCode.SUCCESS,index);
    }

}


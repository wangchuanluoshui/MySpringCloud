package com.hyn.cloud.ribbon.controler;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import com.hyn.cloud.ribbon.service.RibbonService;



@RestController
@RequestMapping("/ribbon")
public class RibbonControler {

    
    @Autowired
    RibbonService iRibbonService;
	
    @RequestMapping(value = "/name",method=RequestMethod.GET)
    @ResponseBody
    public String ribbon(@RequestParam String name){
       return iRibbonService.sendFeignServer(name);
    }
    
    @RequestMapping(value = "/name2",method=RequestMethod.GET)
    @ResponseBody
    public String ribbon2(@RequestParam String name){
    	String respResult="I am ribbon,My name2 is "+name;
        return respResult;
    }
    
   
}

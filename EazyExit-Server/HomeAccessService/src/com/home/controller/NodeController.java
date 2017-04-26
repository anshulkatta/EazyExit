package com.home.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.helper.Util;
import com.home.service.NodeService;
import com.user.bean.Node;

@Controller
@RequestMapping("/node")
public class NodeController {
	
	@Autowired
	NodeService nodeService;
	
	@RequestMapping(value = "/discover", method = RequestMethod.GET)
	public ModelAndView getDiscoveredNodes(ModelAndView mav) {
		mav.setViewName("DiscoveredNodes");
		mav.addObject("nodesList",new ArrayList<Node>(nodeService.discoverNodes().values()));
		return mav;
	}
	
	@RequestMapping(value = "/on", method = RequestMethod.GET)
    public ResponseEntity<Boolean> listAllNodes() {
        boolean flag = nodeService.broadCastMessage(Util.MESSAGE_ON);
        if(flag){
            return new ResponseEntity<Boolean>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<Boolean>(flag, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/off", method = RequestMethod.GET)
    public ResponseEntity<Boolean> turnoffNode() {
    	Boolean flag = nodeService.broadCastMessage(Util.MESSAGE_OFF);
        if(flag){
            return new ResponseEntity<Boolean>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<Boolean>(flag, HttpStatus.OK);
    }

}

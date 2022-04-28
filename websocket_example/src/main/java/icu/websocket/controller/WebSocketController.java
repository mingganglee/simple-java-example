package icu.websocket.controller;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import icu.websocket.domain.SocketMessage;
import icu.websocket.service.WebSocketService;

@Controller
@RequestMapping(value = "/web/socket")
public class WebSocketController {
    
    @GetMapping("/{cid}")
    public ModelAndView socket(@PathVariable String cid) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("cid", cid);
        return mav;
    }

    @ResponseBody
    @GetMapping("/send/{cid}")
    public String pushToWeb(@PathVariable String cid,@RequestParam("message") String message) {
        try {
            SocketMessage socketMessage = new SocketMessage(message, new Date());
            WebSocketService.sendInfo(socketMessage, cid);
        } catch (Exception e) {
            //TODO: handle exception
            e.printStackTrace();
            return String.format("%s # %s", cid, e.getMessage());
        }
        return cid;
    }
}

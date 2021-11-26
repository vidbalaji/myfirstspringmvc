package com.vblearning.processget.controller;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jutils.jprocesses.model.ProcessInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.vblearning.processget.service.ProcessService;

@Controller
@PropertySource("classpath:app.properties")
public class MainController {

	@Autowired
	public ProcessService userProcessService;

	@Autowired
	public ProcessService systemProcessService;

	public ProcessService mProcessService;

	public ProcessService getmProcessService() {
		return mProcessService;
	}

	@RequestMapping(value = "/welcome", method = RequestMethod.POST)
	public String handleProcess(ModelMap model, @RequestParam MultiValueMap<String, String> params) {
		System.out.println(params);
		List<ProcessInfo> pList = new ArrayList<ProcessInfo>();
		List<String> uid = params.get("uid");
		List<String> sType = params.get("sType");
		List<String> sOrderType = params.get("sOrderType");
		int number = Integer.parseInt(uid.get(0));
		String sTypeStr = sType.get(0);
		String sTypeOrderStr = sOrderType.get(0);
		if (params.containsKey("submitsystem")) {
			setmProcessService(systemProcessService);
		} else {
			setmProcessService(userProcessService);
			refreshScheduleList();
		}

		pList = getmProcessService().getProcessesListFor(number * 10, sTypeStr + ":" + sTypeOrderStr);

		model.addAttribute("processlist", pList);
		if (params.containsKey("submitsystem")) {
			model.put("isFromUser", "0");
		} else {
			model.put("isFromUser", "1");
		}
		return "getprocess";
	}

	@ResponseBody
	@RequestMapping(value = "/getprocess", method = RequestMethod.GET, params = { "kill_pid" })
	public String killPid(@RequestParam(value = "kill_pid") String pid) {
		boolean success = getmProcessService().killProcess(pid);
		String successMsg = "";
		if (success) {
			successMsg = "Killed Sucessfully";
			getmProcessService().getUserProcessList();
		} else {
			successMsg = "Killed UnSucessfully";
		}
		System.out.println(success);
		return successMsg;
	}

	@RequestMapping(value = "/getprocess", method = RequestMethod.GET, params = { "next" })
	public String nextPage(ModelMap model) {
		int number = 0;
		List<ProcessInfo> pList = new ArrayList<ProcessInfo>();

		number = getmProcessService().getNumberOfProcesses();
		pList = getmProcessService().getProcessesListFor(number);
		if (getmProcessService().equals(userProcessService)) {
			model.put("isFromUser", "1");
		} else {
			model.put("isFromUser", "0");
		}
		model.addAttribute("processlist", pList);

		return "getprocess";
	}

	@RequestMapping(value = "/getprocess", method = RequestMethod.GET, params = { "prev" })
	public String previosPage(ModelMap model) {
		int number = 0;
		List<ProcessInfo> pList = new ArrayList<ProcessInfo>();
		if (getmProcessService().getIndex() > 1) {
			getmProcessService().setIndex(getmProcessService().getIndex() - 2);
		}
		number = getmProcessService().getNumberOfProcesses();
		pList = getmProcessService().getProcessesListFor(number);
		if (getmProcessService().equals(userProcessService)) {
			model.put("isFromUser", "1");
		} else {
			model.put("isFromUser", "0");
		}
		model.addAttribute("processlist", pList);

		return "getprocess";
	}

	@RequestMapping(value = "/getprocess", method = RequestMethod.GET, params = { "refresh" })
	public ModelAndView refreshPage() {
		ModelAndView model = new ModelAndView("getprocess");
		if (getmProcessService().equals(userProcessService)) {
			model.addObject("isFromUser", "1");
		} else {
			model.addObject("isFromUser", "0");
		}
		refreshScheduleList();
		// must match the jsp page name which is being requested.
		model.addObject("schedulemsg", "Scheduled Successfully");
		return model;

	}

	private void refreshScheduleList() {
		// TODO Auto-generated method stub
		String path = getmProcessService().getExtFile();
		String text = "";
		System.out.println(path);
		try {
			FileWriter fw = new FileWriter(path, false);
			fw.write(text);
			fw.close();
			System.out.println(path);
		} catch (IOException e) {
		}
	}

	@RequestMapping(value = "/")
	public String sayHello() {
		return "welcome";
	}

	@RequestMapping(value = "/getprocess", method = RequestMethod.GET, params = { "schedule" })
	public String schedulePid(@RequestParam(value = "schedule") String name) {
		String path = getmProcessService().getExtFile();
		String text = name + "\n";
		boolean success = true;
		System.out.println(path);
		try {
			FileWriter fw = new FileWriter(path, true);
			fw.write(text);
			fw.close();
			System.out.println(path);
		} catch (IOException e) {
			success = false;
		}
		if (success) {
		} else {
		}
		System.out.println(success);
		return null;
	}

	public void setmProcessService(ProcessService mProcessService) {
		this.mProcessService = mProcessService;
	}
}

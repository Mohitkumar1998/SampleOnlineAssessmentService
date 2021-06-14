package com.wipro.onlineAssessmentSystem.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.wipro.onlineAssessmentSystem.entity.*;
import com.wipro.onlineAssessmentSystem.repository.testdataRepository;
import com.wipro.onlineAssessmentSystem.repository.userRepository;
import com.wipro.onlineAssessmentSystem.repository.userdataRepository;

@Controller
@RequestMapping("/")
public class loginController {
	@Autowired
	private userRepository userRepo;
	
	@Autowired
	private userdataRepository userdataRepo;
	
	@Autowired
	private testdataRepository testdataRepo;
	
	Date date = new Date();
	SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	
	@RequestMapping("/login")
	public ModelAndView login() {
		return new ModelAndView("login");
	}
	
	@RequestMapping("/invalidUserPage")
	public ModelAndView invalidUser() {
		return new ModelAndView("invalidUserPage");
	}
	
	@RequestMapping("/passwordErrorPage")
	public ModelAndView passwordError() {
		return new ModelAndView("passwordErrorPage");
	}
	
	@RequestMapping("/register")
	public ModelAndView register() {
		return new ModelAndView("register");
	}
	
	@PostMapping("/registeration/do")
	public ModelAndView registerNewUser(@RequestParam("email") String email, @RequestParam("firstName") String firstname, @RequestParam("lastName") String lastname, @RequestParam("password") String password) throws IOException {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		password=encoder.encode(password);
		user newUser = new user(email,password,"USER");
		userdata newUserData = new userdata(email, firstname, lastname, "USER");
		userRepo.save(newUser);
		userdataRepo.save(newUserData);
		ModelAndView registration = new ModelAndView("registerConfirmation");
		registration.addObject("firstname", firstname);
		registration.addObject("lastname", lastname);
		return registration;
	}
	
	@RequestMapping("/userDashboard")
	public ModelAndView user() {
		return new ModelAndView("userDashboard");
	}
	
	@RequestMapping("/user/springAssessment")
	public ModelAndView userSpringAssessment() {
		return new ModelAndView("springAssessment");
	}
	
	@PostMapping("/user/springAssessment/test")
	public ModelAndView springAssessmentTest(@RequestParam("Question1") String answer1, @RequestParam("Question2") String answer2, @RequestParam("Question3") String answer3, @RequestParam("Question4") String answer4, @RequestParam("Question5" ) String answer5) {
		int score = 0;
		if(answer1.equals("J2EE App Development Framework")) {
			score++;
		}
		if(answer2.equals("Inversion Of Control")) {
			score++;
		}
		if(answer3.equals("Aspect Oriented Programming")) {
			score++;
		}
		if(answer4.equals("Application Context")) {
			score++;
		}
		if(answer5.equals("Dispatcher Servlet")) {
			score++;
		}
		score*=10;
		ModelAndView evaluationPage = new ModelAndView("evaluationPage");
		String message = "Congratulations !!! You have passed !!!";
		String result ="Passed";
		if(score<30) {
			message = "Sorry !!! Better Luck Next Time !!!";
			result="Failed";
		}
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		testdata thisTestData = new testdata(auth.getName(), formatter.format(date).toString(), "Spring-L1", score, 50, result);
		testdataRepo.save(thisTestData);
		evaluationPage.addObject("msg", message);
		return evaluationPage;
	}
	
	@RequestMapping("/user/hibernateAssessment")
	public ModelAndView userHibernateAssessment() {
		return new ModelAndView("hibernateAssessment");
	}
	
	@PostMapping("/user/hibernateAssessment/test")
	public ModelAndView hibernateAssessmentTest(@RequestParam("Question1") String answer1, @RequestParam("Question2") String answer2, @RequestParam("Question3") String answer3, @RequestParam("Question4") String answer4, @RequestParam("Question5" ) String answer5) {
		int score = 0;
		if(answer1.equals("Object Relational Mapping")) {
			score++;
		}
		if(answer2.equals("uni-directional & bi-directional")) {
			score++;
		}
		if(answer3.equals("configuration file")) {
			score++;
		}
		if(answer4.equals("Hibernate Query Language")) {
			score++;
		}
		if(answer5.equals("isolation levels")) {
			score++;
		}
		score*=10;
		ModelAndView evaluationPage = new ModelAndView("evaluationPage");
		String message = "Congratulations !!! You have passed !!!";
		String result="Passed";
		if(score<30) {
			message = "Sorry !!! Better Luck Next Time !!!";
			result="Failed";
		}
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		testdata thisTestData = new testdata(auth.getName(), formatter.format(date).toString(), "Spring-L1", score, 50, result);
		testdataRepo.save(thisTestData);
		evaluationPage.addObject("msg", message);
		return evaluationPage;
	}

	@RequestMapping("/adminDashboard")
	public ModelAndView admin() {
		return new ModelAndView("adminDashboard");
	}
	
	@RequestMapping("/admin/allCandidates")
	public ModelAndView adminViewAllCandidates() {
		ModelAndView allCandidates =  new ModelAndView("viewCandidates");
		List<userdata> allCandidatesList = userdataRepo.findAll();
		System.out.println(allCandidatesList);
		allCandidates.addObject("allCandidateList", allCandidatesList);
		return allCandidates;
	}
	
	@RequestMapping("/admin/allTests")
	public ModelAndView adminViewAllTests() {
		ModelAndView allTests =  new ModelAndView("viewTests");
		List<testdata> allTestsList = testdataRepo.findAll();
		System.out.println(allTestsList);
		allTests.addObject("allTestsList", allTestsList);
		return allTests;
	}
}

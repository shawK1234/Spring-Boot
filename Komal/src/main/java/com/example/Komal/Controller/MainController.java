package com.example.Komal.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.Komal.Model.Employee;
import com.example.Komal.Repo.EmployeeRepo;

@Controller
public class MainController {
	
	int flag=0;
	List<Employee> thelist;
	
	@Autowired
	EmployeeRepo rep;
	
	@RequestMapping("/home")
	public String home() {
		System.out.println("Here");
		return "home.jsp";
	}
	@RequestMapping("/")
	public String Blank() {
		System.out.println("Here2blank");
		return "home.jsp";
	}
		
	@RequestMapping("/add")
	public ModelAndView add(String fname,String lname,ModelAndView mv) 
	{
		if(fname==null)
		{
			mv.setViewName("add.jsp");
			return mv;
		}
		if(check(fname+" "+lname))
		{
			System.out.println("Person exits already");
			
		}
		else
		{
			Employee emp=new Employee();
			emp.setEmpid(rep.findallfromEmployee().size()+10);
			emp.setName(fname+" "+lname);
			rep.save(emp);
			System.out.println("Details Added");
		}
		mv.setViewName("add.jsp");
		return mv;
	}
	
	@RequestMapping("/delete")
	public String delete(String fname,String lname)
	{
		if(fname==null)
			return "delete.jsp";
		
		if(check(fname+" "+lname))
		{
			List<Employee> lst=rep.findByName(fname+" "+lname);
			Employee emp=new Employee();
			emp.setEmpid(lst.get(0).getEmpid());
			emp.setName(fname+" "+lname);
			rep.delete(emp);
			System.out.println("Delete successfull");
			
		}
		else
			{
				System.out.println("You cannot delete a data which isn't there in DB");
			}
		return "delete.jsp";
	
	}
	
	@RequestMapping("/search")
	public ModelAndView SearchAndView(String fname,String lname,ModelAndView mv)
	{
		if(fname==null)
		{
			mv.setViewName("search.jsp");
			return mv;
		}
		String name=fname+" "+lname;
		if(check(name))
		{
			List<Employee> lst=rep.findByName(fname+" "+lname);
			System.out.println(lst.get(0).toString());
		}
		else
			System.out.println("Name not found in DB");
		mv.setViewName("search.jsp");
		return mv;
	}
	
	@RequestMapping("/update")
	public ModelAndView update(String fname,String lname,ModelAndView mv)
	{
		flag=0;
		if(fname==null)
		{
			mv.setViewName("update.jsp");
			return mv;
		}
		String name=fname+" "+lname;
		if(check(name))
		{
			List<Employee> lst=rep.findByName(fname+" "+lname);
			System.out.println("Old Details--> "+lst.get(0).toString());
			flag=1;
			thelist=lst;
			mv.setViewName("update2.jsp");
			return mv;
		}
		else
			System.out.println("Name not found in DB");
		mv.setViewName("update.jsp");
		return mv;
	}
	
	@RequestMapping("/updation")
	public ModelAndView update2(String fname,String lname,ModelAndView mv)
	{ if(flag==0)
		{
			System.out.println("First visit/update page ");
			mv.setViewName("update.jsp");
			return mv;
		}
	if(fname==null)
	{
		mv.setViewName("update.jsp");
		return mv;
	}
	if(flag==1)
	{
		Employee emp=new Employee();
		emp.setEmpid(thelist.get(0).getEmpid());
		emp.setName(fname+" "+lname);
		System.out.println("new Details--> "+emp.toString());
		rep.save(emp);
		System.out.println("Details updated successfully");
		
	}
		
	
	mv.setViewName("update.jsp");
	return mv;
	}
	
	
	public boolean check(String name)
	{
		List list=rep.findByName(name);
		if(list.size()==0)
			return false;
		else
			return true;
		
	}

}

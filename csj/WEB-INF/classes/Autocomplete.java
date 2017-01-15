// Import required java libraries
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

import saxParser.*;

// Extend HttpServlet class
public class Autocomplete extends HttpServlet {
 
  public void doGet(HttpServletRequest request,
                    HttpServletResponse response)
            throws ServletException, IOException
{
  	System.out.println("Autocomplete>>");
  	String action = request.getParameter("action");
    	String searchId = request.getParameter("searchId");
    	PrintWriter out = response.getWriter();
	try
	{
		StringBuffer sb = new StringBuffer();
		boolean namesAdded = false;
		if (action.equals("complete"))
		{
			if (!searchId.equals("")){ 
				AjaxUtility a=new AjaxUtility();
				sb=a.readdata(searchId);
				if(sb!=null || !sb.equals("")){
					namesAdded=true;
				}
				if (namesAdded){
					System.out.println("Autocomplete>>here1");
					//response.setContentType("text/xml; charset=UTF-8");
					//response.setContentType("UTF-8");
					//out.write("<?xml version=\"1.0\">");
					//out.write("<products>" + sb.toString() + "</products >");
					//response.getWriter().write("<products>" + sb.toString() + "</products >");
					response.setContentType("text/xml");
					response.setHeader("Cache-Control", "no-cache");
					response.getWriter().write("" + sb.toString() + "");
				}
			}
		}
	}catch(Exception e){
		System.out.println("Autocomplete<<Exception");
	}
	System.out.println("Autocomplete<<");
}


  public void doPost(HttpServletRequest request,
                    HttpServletResponse response)
            throws ServletException, IOException
{
  	System.out.println("Autocomplete>>");
  	String action = request.getParameter("action");
    	String searchId = request.getParameter("searchId");
	try
	{
		StringBuffer sb = new StringBuffer();
		boolean namesAdded = false;
		if (action.equals("complete"))
		{
			if (!searchId.equals("")){ 
				AjaxUtility a=new AjaxUtility();
				sb=a.readdata(searchId);
				if(sb!=null || !sb.equals("")){
					namesAdded=true;
				}
				if (namesAdded){
					System.out.println("Autocomplete>>here");
					response.setContentType("text/xml");
					response.getWriter().write("<products>" + sb.toString() + "</products >");
				}
			}
		}
	}catch(Exception e){
		System.out.println("Autocomplete<<Exception");
	}
	System.out.println("Autocomplete<<");
}

}


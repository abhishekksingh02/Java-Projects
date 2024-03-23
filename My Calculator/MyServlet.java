package myPackage;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MyServlet
 */
@WebServlet("/MyServlet")
public class MyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String[] jokes = {
	        "Why do Java developers wear glasses? Because they don't C#!",
	        "I told my computer I needed a break, and now it won't stop sending me Kit Kat bars.",
	        "Why don't programmers like nature? It has too many bugs.",
	        "How many programmers does it take to change a light bulb? None, it's a hardware problem.",
	        // Add more jokes here
	    };

    /**
     * Default constructor. 
     */
    public MyServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String str1 = request.getParameter("num1");
		String str2 = request.getParameter("num2");
		String str3 = request.getParameter("bt1");
		
		//Type Casting
		int a = Integer.parseInt(str1);
		int b = Integer.parseInt(str2);
		
//		int sum = a + b;
		int ans;
		if(str3.equals("1")) {
			ans = a+b;
		}
		else if(str3.equals("2"))
		{
			ans = a-b;
		}
		else if(str3.equals("3"))
		{
			ans = a*b;
		}
		else
		{
			ans = a/b;
		}
		
		int randomIndex = (int) (Math.random() * jokes.length);
		String randomJoke = jokes[randomIndex];
			
//		response.getWriter().append("Answer = " + ans);
		response.sendRedirect("NewFile.jsp?ans=" + ans +"&joke=" + randomJoke);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

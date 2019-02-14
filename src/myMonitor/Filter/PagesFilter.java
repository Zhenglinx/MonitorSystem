package myMonitor.Filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class PagesFilter
 */
@WebFilter("/PagesFilter")
public class PagesFilter implements Filter {

    /**
     * Default constructor. 
     */
    public PagesFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		HttpServletRequest requ=(HttpServletRequest)request;
		HttpServletResponse resp=(HttpServletResponse)response;
		HttpSession session = requ.getSession(true);
		
		String requestPath=requ.getServletPath();
		//System.out.println(requestPath);
		
		if(requestPath.endsWith("/login.jsp")) {
			chain.doFilter(request, response);//��¼ҳ�棬����
			return;
		}
		if(requestPath.endsWith("/SignIn")) {
			chain.doFilter(request, response);//��¼servlet������
		}
		if(session.getAttribute("user")!=null) {
			System.out.println(session.getAttribute("user").toString());
			chain.doFilter(request, response);//�û��ѵ�½������
			return;
		}else {
			resp.sendRedirect("/myMonitor/login.jsp");
			System.out.println("�ض�����");
			//request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
		
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}

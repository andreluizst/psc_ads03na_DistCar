package seguranca;

import gui.managedBeans.LoginBean;
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class LoginAutenticadoFilter
 */
@WebFilter("*.xhtml")
public class LoginAutenticadoFilter implements Filter {

	private static final String[] arquivosEscape = { "javax.faces.resource",
			"index.xhtml", "novo-usuario.xhtml"};

	public void destroy() {
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here

		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession sessao = req.getSession();
		
		// Retirar a linha abaixo,  para começar a usar o filtro 
		//chain.doFilter(request, response);
		
		
		if (verificaPaginaInicial(request)) {
			chain.doFilter(request, response);
		} else {

			if (sessao == null || sessao.getAttribute("loginBean") == null || 
					((LoginBean)sessao.getAttribute("loginBean")).getUsuarioLogado() == null) {
				
				RequestDispatcher dis = request
						.getRequestDispatcher("/index.xhtml");
				dis.forward(request, response);
			} else {
				
				// pass the request along the filter chain
				chain.doFilter(request, response);

			}
		}
		
	}

	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

	protected boolean verificaPaginaInicial(ServletRequest request) {

		String resource = ((HttpServletRequest) request).getRequestURI();

		for (String esc : arquivosEscape) {
			if (resource.contains(esc)) {
				return true;
			}
		}
		System.out.println(resource);
		return false;
	}

}

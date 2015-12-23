package loginFilter;

import beans.UserLoginBean;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Kiker
 */
public class LoginFilter implements Filter
{

    String loginBeanName;
    String redirectAdress;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException
    {
        loginBeanName = filterConfig.getInitParameter("beanName");
        redirectAdress = filterConfig.getInitParameter("redirectAddress");
        if ((loginBeanName == null) || (redirectAdress == null))
        {
            throw new ServletException("LoginHttpFilter: beanName or redirectAddress initParam is not specified.");
        }
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException
    {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        String url = httpRequest.getRequestURI();
        if (!url.contains(redirectAdress))
        {
            checkAndRedirect(httpRequest, httpResponse);
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy()
    {
    }

    private void checkAndRedirect(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        UserLoginBean userAuth = (UserLoginBean) request.getSession().getAttribute(loginBeanName);
        if (userAuth == null || !userAuth.isSignedIn())
        {
            response.sendRedirect(redirectAdress);
        }
    }

}

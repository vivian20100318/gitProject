/**
 * @(#) WebInitializer.java Created on 2010-5-13
 * 
 * Copyright (c) 2010 Aspire. All Rights Reserved
 */
package com.hjkj.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import com.hjkj.job.InitQuartzJob;

/**
 * The class <code>WebInitializer</code>
 * 
 * @author Link Wang
 * @version 1.0
 */
public class WebInitializer extends HttpServlet {

    /**
     * The serial version UID
     */
    private static final long serialVersionUID = -7064390563063878073L;

    /**
     * The servlet context path
     */
    private static String contextPath = null;
    
    private static String realPath = null;

    @Override
    public void init() throws ServletException {
        super.init();
        contextPath = getServletContext().getContextPath();
        realPath = getServletContext().getRealPath("/");
        InitQuartzJob initQuartzJob = new InitQuartzJob();
        initQuartzJob.initJob();
    }

    /**
     * Gets the context path
     * @return Returns the context path
     */
    public static String getContextPath() {
        return contextPath;
    }
    
    public static String getRealPath() {
        //return contextPath;
    	return realPath;
    }
    
    
}

package com.cyq.product.filter;

import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import com.cyq.product.model.CommonResult;
import com.cyq.product.model.RoleVo;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * 用户过滤器
 */
@Component
public class UserFilter implements Filter {

    private static final String USER_LOGIN_URL_SUB = "login";
    private static final String USER_REGISTRY_URL_SUB = "registry";
    private static final String SYS_ROLE_URL = "sys-role";
    private static final String SYS_USER_URL = "sys-user";
    private static final String SYS_PRODDUCT_URL = "sys-product";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String requestURI = request.getRequestURI();
        // 注册和登陆页面没有登陆直接放行
        if (requestURI == null) {
            response.setContentType("application/json; charset=UTF-8");
            PrintWriter writer = response.getWriter();
            CommonResult result = CommonResult.failed("请求URI为空");
            JSON json = JSONUtil.parse(result);
            writer.write(json.toString());
            writer.close();
            return;
        }
        if (requestURI.contains(USER_LOGIN_URL_SUB) || requestURI.contains(USER_REGISTRY_URL_SUB)) {
            filterChain.doFilter(request, response);
        } else if (requestURI.contains(SYS_USER_URL) || requestURI.contains(SYS_ROLE_URL)) {
            HttpSession session = request.getSession();
            String userCode = (String)session.getAttribute("userCode");
            if (userCode == null) {
                response.setContentType("application/json; charset=UTF-8");
                PrintWriter writer = response.getWriter();
                CommonResult result = CommonResult.failed("用户没有登陆");
                JSON json = JSONUtil.parse(result);
                writer.write(json.toString());
                writer.close();
                return;
            }
            List<RoleVo> roles = (List<RoleVo>)session.getAttribute("userRoles");
            for (RoleVo roleVo : roles) {
                Integer roleId = roleVo.getId();
                if(roleId == 1) {
                    filterChain.doFilter(request, response);
                } else {
                    response.setContentType("application/json; charset=UTF-8");
                    PrintWriter writer = response.getWriter();
                    CommonResult result = CommonResult.failed("用户没有权限");
                    JSON json = JSONUtil.parse(result);
                    writer.write(json.toString());
                    writer.close();
                    return;
                }
            }
        } else if(requestURI.contains(SYS_PRODDUCT_URL)) {
            HttpSession session = request.getSession();
            String userCode = (String)session.getAttribute("userCode");
            if (userCode == null) {
                response.setContentType("application/json; charset=UTF-8");
                PrintWriter writer = response.getWriter();
                CommonResult result = CommonResult.failed("用户没有登陆");
                JSON json = JSONUtil.parse(result);
                writer.write(json.toString());
                writer.close();
                return;
            }
            List<RoleVo> roles = (List<RoleVo>)session.getAttribute("userRoles");
            for (RoleVo roleVo : roles) {
                Integer roleId = roleVo.getId();
                if(roleId == 1 || roleId == 2) {
                    filterChain.doFilter(request, response);
                } else {
                    response.setContentType("application/json; charset=UTF-8");
                    PrintWriter writer = response.getWriter();
                    CommonResult result = CommonResult.failed("用户没有权限");
                    JSON json = JSONUtil.parse(result);
                    writer.write(json.toString());
                    writer.close();
                    return;
                }
            }
            filterChain.doFilter(request, response);
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}

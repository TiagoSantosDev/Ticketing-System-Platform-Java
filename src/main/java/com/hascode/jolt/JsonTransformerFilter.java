package com.hascode.jolt;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import com.bazaarvoice.jolt.Chainr;
import com.bazaarvoice.jolt.JsonUtils;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

	
public class JsonTransformerFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("json transformer servlet filter initialized");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        List<Object> specs = JsonUtils.classpathToList("/spec.json");
        System.out.println("specs loaded:");
        specs.forEach(System.out::println);
        Chainr chainr = Chainr.fromSpec(specs);

        CustomResponseWrapper res = new CustomResponseWrapper((HttpServletResponse) response);
        chain.doFilter(request, res);

        String inputJSON = res.getContent();
        Object transformedOutput = chainr.transform(JsonUtils.jsonToObject(inputJSON));
        System.out.printf("transformed json: %s\n", transformedOutput);

        PrintWriter writer = response.getWriter();
        writer.write(transformedOutput.toString());
        writer.flush();
    }

    @Override
    public void destroy() {
    }

}

package com.extractor;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface RequestExtractor<T> {
    T extract(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException;
}

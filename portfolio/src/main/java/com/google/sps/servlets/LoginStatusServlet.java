// Copyright 2019 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.google.sps.servlets;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gson.Gson;
import java.util.*;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** Servlet that returns some example content. TODO: modify this file to handle comments data */
@WebServlet("/loginstatus")
public class LoginStatusServlet extends HttpServlet {

  // abstract class
  private final class LoginStatus {
      String email;
      String redirectUrl;

    public LoginStatus(String email, String redirectUrl) {
    this.email = email;
    this.redirectUrl = redirectUrl;
  }

  }
  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    response.setContentType("application/json");
    // {"email": .., "url": ...}

    UserService userService = UserServiceFactory.getUserService();
    if (userService.isUserLoggedIn()) {
      String userEmail = userService.getCurrentUser().getEmail();
      String urlToRedirectToAfterUserLogsOut = "/";
      String logoutUrl = userService.createLogoutURL(urlToRedirectToAfterUserLogsOut);
      // make instance of login class and set email and url

      LoginStatus lgs = new LoginStatus(userEmail, logoutUrl);
      Gson gson = new Gson();
      String json = gson.toJson(lgs); 
      response.getWriter().println(json);
      //response.setContentType("application/json"); 
      //response.getWriter().println("<p>Hello " + userEmail + "!</p>");
      //response.getWriter().println("<p>Logout <a href=\"" + logoutUrl + "\">here</a>.</p>");
    } else {
      
      
      String urlToRedirectToAfterUserLogsIn = "/";
      String loginUrl = userService.createLoginURL(urlToRedirectToAfterUserLogsIn);
      LoginStatus lgs = new LoginStatus("", loginUrl);
      Gson gson = new Gson();
      String json = gson.toJson(lgs); 
      response.getWriter().println(json);
      
      //response.sendRedirect("/loginstatus");
      //response.getWriter().println("<p>Hello stranger.</p>");
      //response.getWriter().println("<p>Login <a href=\"" + loginUrl + "\">here</a>.</p>");
    }

  }


}

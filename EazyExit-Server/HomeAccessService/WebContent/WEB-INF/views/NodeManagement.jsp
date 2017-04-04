<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>  
    <title>Node Management </title>  
    <button type="button" ng-click="ctrl.edit(u.id)" class="btn btn-success custom-width">Discover Nodes</button>
    
                              
    <style>
      .username.ng-valid {
          background-color: lightgreen;
      }
      .username.ng-dirty.ng-invalid-required {
          background-color: red;
      }
      .username.ng-dirty.ng-invalid-minlength {
          background-color: yellow;
      }

      .email.ng-valid {
          background-color: lightgreen;
      }
      .email.ng-dirty.ng-invalid-required {
          background-color: red;boot
      }
      .email.ng-dirty.ng-invalid-email {
          background-color: yellow;
      }

    </style>
     <link rel="stylesheet" href="<c:url value='/static/css/bootstrap.min.css'/>" ></link>
     <link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
  </head>
  <body ng-app="myApp" class="ng-cloak">
      <div class="generic-container" ng-controller="NodeController as ctrl">
         
          <div class="panel panel-default">
                <!-- Default panel contents -->
              <div class="panel-heading"><span class="lead">List of Nodes </span></div>
              <div class="tablecontainer">
                  <table class="table table-hover">
                      <thead>
                          <tr>
                              <th>ID.</th>
                              <th>Name</th>
                              <th>Status</th>
                              <th width="20%"></th>
                          </tr>
                      </thead>
                      <tbody>
                          <tr ng-repeat="n in ctrl.nodes">
                              <td><span ng-bind="n.id"></span></td>
                              <td><span ng-bind="n.nodename"></span></td>                             
                              <td><span ng-bind="n.status"></span></td>
                          </tr>
                      </tbody>
                  </table>
              </div>
          </div>
          
          <tbody>
                          <tr ng-repeat="n in ctrl.nodes">
                              <td><span ng-bind="n.id"></span></td>
                              </tr>
          </tbody>
      </div>
      
      <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.4/angular.js"></script>
      <script src="<c:url value='/static/js/app.js' />"></script>
      <script src="<c:url value='/static/js/service/node_service.js' />"></script>
      <script src="<c:url value='/static/js/controller/node_controller.js' />"></script>
  </body>
</html>
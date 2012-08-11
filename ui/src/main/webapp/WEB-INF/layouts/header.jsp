<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%><%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<div id="nav_top">
  <div id="app_logo_area" >
   <img src="<c:url value="/resources/images/ime_logo.gif"/>" alt="" width="53px" height="36px" style="background-color: #002288;" /> 
  </div>
  <div id="app_area" >
     <fmt:message key="app_name" />
   </div>
   <div id="app_main_area"> <fmt:message key="app_description" /></div>
   
   <div id="other_area">

  </div>
</div>



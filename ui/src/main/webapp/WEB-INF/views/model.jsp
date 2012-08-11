<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>



<form:form modelAttribute="model">
<fieldset><legend>Model description:</legend>
  <table style="width:400px;">
     <tr><th style="text-align:right">Id<th><td>${model.id}</td></tr>
     <tr><th style="text-align:right">Name<th><td><form:input path="name"/></td></tr>
     <tr><th style="text-align:right">Version<th><td><form:input path="version"/></td></tr>
     <tr><th style="text-align:right">Description<th><td><form:input path="description"/></td></tr>
     <tr><th style="text-align:right">Creator<th><td>${model.auditable.createdBy.login}</td></tr>
     <tr><th style="text-align:right">Created<th><td>${model.auditable.creationDate}</td></tr>
    </table>
</fieldset>
<input type="submit"  value="Save" /> <input type="submit" name="_cancel" value="Cancel" />
</form:form>

<c:url var="cancelUrl" value="/model/cancel" />



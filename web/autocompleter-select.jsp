<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=windows-1251" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<head>
  <link href="<s:url value="/css/template_styles.css"/>" type="text/css" rel="stylesheet">
  <sj:head jqueryui="true" />
  <title>Autocompleter Select</title>
 </head>
<body>

<h2>Autocompleter - Select Box</h2>

<p class="text">
	The first autocompleter handle a List from Action as Select Box. The second can handle a Select Box with JSON Result
	as autocompleter.
</p>
<strong>Result Div :</strong>

<div id="formResult" class="result ui-widget-content ui-corner-all">Submit form bellow.</div>
<strong>Topics Div :</strong>

<div id="topics" class="result ui-widget-content ui-corner-all"></div>
<br/>
<s:url var="jsoncustomers" action="jsoncustomers"/>
<s:form id="formAutocompleteSelect" action="echo" theme="simple">
  <fieldset>
    <legend>Select Box as Autocompleter</legend>
    <div class="type-select">
      <label for="echo">Echo: </label>
      <%--<sj:select href="%{jsoncustomers}" name="echo" list="%{customersList}" listKey="id" listValue="name" emptyOption="true" autocomplete="true" headerKey="-1" headerValue=""/>--%>
    </div>
  </fieldset>
  <fieldset>
 		<legend>Autocompleter as Select Box with JSON Result</legend>
    <div class="type-select">
  		<label for="echo">Echo: </label>
      <sj:autocompleter
          id="customers"
          name="echo"
          list="%{customersList}"
          listValue="name"
          listKey="id"
          forceValidOption="false"
          selectBox="true"
          selectBoxIcon="true"
          onChangeTopics="autocompleteChange"
          onFocusTopics="autocompleteFocus"
          onSelectTopics="autocompleteSelect"

          />
    </div>
  </fieldset>
  <fieldset>
    <legend>Autocompleter </legend>
    <div class="type-select">
      <label for="echo">Echo: </label>
      <sj:autocompleter
          id="customersList"
          name="echo"
          href="%{jsoncustomers}"
          list="customers"
          listValue="name"
          listKey="id"
          delay="50"
          loadMinimumCount="2"
          forceValidOption="false"
          />
     </div>
  </fieldset>
  <sj:submit
      targets="formResult"
      value="AJAX Submit"
      indicator="indicator2"
      button="true"
      onClickTopics="clickTopic"
      />
  <img id="indicator2" src="images/indicator.gif" alt="Loading..." style="display:none"/>
</s:form>
<script type="text/javascript">
  $(document).ready(function() {
    $.subscribe('autocompleteChange', function(event, data) {
      var ui = event.originalEvent.ui;
      if(ui.item) {
        var message = ui.item.value;
        if(ui.item.key) {
          message = '( '+ ui.item.key +' ) '+message;
        }
        $('#topics').html('<b>'+message+'</b>');
      }
    });

    $.subscribe('autocompleteFocus', function(event, data) {
      var ui = event.originalEvent.ui;
      var message = ui.item.value;
      if(ui.item.key) {
        message = '( '+ ui.item.key +' ) '+message;
      }
      $('#topics').html('<u>'+message+'</u>');
    });

    $.subscribe('autocompleteSelect', function(event, data) {
      var ui = event.originalEvent.ui;
      var message = ui.item.value;
      if(ui.item.key) {
        message = '( '+ ui.item.key +' ) '+message;
      }
      $('#topics').html('<i>'+message+'</i>');
    });
    $(".s2j-combobox-input.ui-autocomplete-input").keyup(function(e){
      $("#customers").val($(".s2j-combobox-input.ui-autocomplete-input").val())
    });
//    $(".s2j-combobox-input.ui-autocomplete-input").attr('type', 'text');
  });
</script>


</body>
</html>
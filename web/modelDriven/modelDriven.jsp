<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<html>
<head>
	<title>Struts2 Showcase - Model Driven Example</title>
	<sj:head debug="true" compressed="false"/>
  <script src="https://cdn.jsdelivr.net/lodash/4.16.2/lodash.min.js"></script>
  <script>
    $.fn.serializeObject = function() {
      var o = {};
      var a = this.serializeArray();
      $.each(a, function() {
        if (o[this.name] !== undefined) {
          if (!o[this.name].push) {
            o[this.name] = [o[this.name]];
          }
          o[this.name].push(this.value || '');
        } else {
          o[this.name] = this.value || '';
        }
      });
      return o;
    };
    /**
     * Form logic
     * Handler to submit a form with jquery.form.js plugin
     * */
    $.subscribeHandler($.struts2_jquery.handler.form, function(event, data) {
      var s2j = $.struts2_jquery,
          elem = $(event.target),
          o = {},
          params = {},
          indi,
          always;

      // need to also make use of original attributes registered with the container (such as onCompleteTopics)
      if (data) {
        $.extend(o, data);
      }
      if (event.data) {
        $.extend(o, event.data);
      }

      s2j.lasttopic = o.actionTopic;
      indi = o.indicatorid;
      always = o.onalw;

      if (o.href && o.href !== '#') {
        params.url = o.href;
        if (o.hrefparameter) {
          params.url = params.url + '?' + o.hrefparameter;
        }
      }
      if (o.clearform) {
        params.clearForm = true;
      }
      if (o.iframe) {
        params.iframe = true;
      }
      if (o.resetform) {
        params.resetForm = true;
      }
      if (o.replaceTarget) {
        params.replaceTarget = true;
      }
      if (o.timeout) {
        params.timeout = parseInt(o.timeout, 10);
      }
      if (o.datatype) {
        params.dataType = o.datatype;
      }
      else {
        params.dataType = null;
      }
      if ($(event.target).attr('contenttype')) {
        params.contentType = $(event.target).attr('contenttype');
      }

      params.target = '';
      if (o.targets) {
        $.each(o.targets.split(','), function(i, target) {
          elem = $(s2j.escId(target));
          if (params.target === '') {
            params.target = s2j.escId(target);
          }
          else {
            params.target = params.target + ',#' + s2j.escId(target);
          }
        });
      }


      params.beforeSubmit = function(formData, form, formoptions) {

        var orginal = {};
        orginal.formData = formData;
        orginal.form = form;
        orginal.options = formoptions;
        orginal.options.submit = true;

        s2j.publishTopic(elem, always, orginal);

        if (o.onbef) {
          $.each(o.onbef.split(','), function(i, topic) {
            elem.publish(topic, elem, orginal);
          });
        }

        if (o.validate) {
          orginal.options.submit = s2j.validateForm(form, o);
          orginal.formvalidate = orginal.options.submit;
          if (o.onaftervalidation) {
            $.each(o.onaftervalidation.split(','), function(i, topic) {
              elem.publish(topic, elem, orginal);
            });
          }
        }
        if (orginal.options.submit) {
          s2j.showIndicator(indi);
          if(!o.datatype || o.datatype !== "json") {
            if (o.loadingtext && o.loadingtext !== "false") {
              $.each(o.targets.split(','), function(i, target) {
                $(s2j.escId(target)).html(o.loadingtext);
              });
            }
            else if (s2j.defaults.loadingText !== null) {
              $.each(o.targets.split(','), function(i, target) {
                $(s2j.escId(target)).html(s2j.defaults.loadingText);
              });
            }
          }
        }
        return orginal.options.submit;
      };

      params.success = s2j.pubSuc(elem, always, o.onsuc, indi, 'form', o);
      params.complete = s2j.pubCom(elem, always, o.oncom, o.targets, indi, o);
      params.error = s2j.pubErr(elem, always, o.onerr, o.errortext, 'html');

      $.each(o.formids.split(','), function(i, fid) {
        s2j.log('submit form : ' + fid);
        if (params.contentType === "application/json") {
          var $form = $(s2j.escId(fid));
          params.url = $form.attr('action');
          var obj = $form.serializeObject();
          var json = {};
          $.each(obj, function (key, value){
            if (/^__checkbox_/.test(key))
              key = key.substring(11);
            _.set(json, key, value);
          });
          params.data = JSON.stringify(json);
          console.log(params.data);

          params.dataType="json";
          params.type="post";
          $.ajax(params.url, params);
        } else
          $(s2j.escId(fid)).ajaxSubmit(params);
        });

      return false;
    });

  </script>
</head>

<body>

<div class="page-header">
	<h1>Model Driven Example</h1>
</div>

<div class="container-fluid">
	<div class="row-fluid">
		<div class="span12">

      <s:form id="modelDrivenForm" action="modelDrivenResult" method="POST" namespace="/modelDriven" theme="xhtml">
        <tr><td>
        <label for="modelDrivenForm_name">Gangster Name: </label>
        <s:textfield id="modelDrivenForm_name"
						label="Gangster Name"
						name="name" />
        <s:fielderror fieldName="name" />
        </td></tr>
				<s:select
            list="#{5:'5',10:'10',15:'15'}"
						label="Gangster Age"
						name="age"/>
				<s:checkbox
						label="Gangster Busted Before"
						name="bustedBefore" fieldValue="Yes" >&nbsp;
        </s:checkbox>
				<s:textarea
						cols="30"
						rows="5"
						label="Gangster Description"
						name="description"/>


        <%--<s:combobox label="Select Question" headerKey="-1" headerValue="--- Select ---"  name="question" list="questionList" listKey="id" listValue="name" />--%>
        <%--<s:select multiple="true" label="Select Question" headerKey="-1" headerValue="--- Select ---"  name="questionIds" list="questionMap" listKey="value.id" listValue="value.name" />--%>

      <tr><td>Select Question:</td></tr>
        <s:iterator value="{'Question1','Question2','Question3'}" status="status" >
          <s:textfield label="Name" name="questionList[%{#status.index}].name"  id="name" value="%{top}"/>
        </s:iterator>

        <s:textfield
    						label="Par1"
    						name="par1"/>

				<sj:submit cssClass="btn btn-primary" targets="div1" onBeforeTopics="beforeForm" value="SJ Submit"  contentType="application/json" />

        <br/>
        <tr><td>
        <button type="button" class="btn btn-primary" onclick="doSubmit()">Button</button>
        </td></tr>
        <script>
          function doSubmit(){
              $.ajax({
                type: 'GET',
                'url': '<s:url namespace="/modelDriven" action="modelDrivenResult"/>',
                data: {par1: $("input[name=par1]").val()},
                dataType: 'html',
                success: function(response){
                  $("#div1").html(response);
                },
                timeout: 10000,
                error: function(xhr, status, err){
                }
              });
          }
        </script>
      </s:form>
		</div>
    <div id="div1"></div>
    <br>
     <s:if test="#context['struts.actionMapping'].name=='modelDriven'">
              do some stuff
     </s:if>

	</div>
</div>
</body>
</html>


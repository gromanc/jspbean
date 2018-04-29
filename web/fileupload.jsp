<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>File Upload</title>
  <sj:head />
</head>

<body>
    <h1>File Upload Form</h1>
    <hr/>
    <fieldset>
        <legend>Upload File</legend>
        <form NAME="form1" action="upload!upload" method="post" ENCTYPE="multipart/form-data">
            <label for="filename_1">File: </label>
            <input id="filename_1" type="file" name="filename_1" size="50"/><br/>
            <label for="filename_2">File: </label>
            <input id="filename_2" type="file" name="filename_2" size="50"/><br/>
            <br/>
            <sj:submit value="Upload"/>
          <br/>
          <input type="reset"  value="Clear" />
          <script type="text/javascript">
            function clearFileElement(fileId){
                document.getElementById(fileId).value = '';
            }
          </script>
          <br/>
          <s:file label="FIle 1" name="fileUpload" size="40" /><br/>
          <s:file label="FIle 2" name="fileUpload" size="40" />

        </form>
    </fieldset>
<fieldset><legend>Upload Blob</legend>
  <button type="button" onclick="upload()">Upload</button>
  <script>
      function upload() {
        var file = document.getElementById('filename_1').files[0];
        var fileName = file.name;
        var blob = new Blob([file]);
        // create a form with a couple of values
        var form = new FormData();
        form.append("name", file.name);
        form.append("photo", blob);

        // send via XHR - look ma, no headers being set!
        var xhr = new XMLHttpRequest();
        xhr.onload = function () {
          console.log("Upload complete.");
        };
        xhr.open("post", "upload!upload", true);
        xhr.send(form);
      }
  </script>
</fieldset>
</body>
</html>
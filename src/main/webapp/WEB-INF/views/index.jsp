<html>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.10.2.min.js"></script>
<body>
<h2>Geosynkronisering</h2>
<table width="600" border="0">
<tr>
<td><input type="button" value="GetLastIndex" name="radiogroup" onClick="window.location.href='http://localhost:8081/geosync_client/getLastIndex?datasetId='+$('#datasetId').val()"></td>
<td><input type="button" value="OrderChangelog" name="radiogroup" onClick="window.location.href='http://localhost:8081/geosync_client/orderChangelog?datasetId='+$('#datasetId').val()+',startIndex='+$('#startIndex').val()+',count='+$('#count').val()+',ResultType='+$('#ResultType').val()+',outputFormat='+$('#outputFormat').val()"></td>
<td><input type="button" value="ListStoredChangelogs" name="radiogroup" onClick="window.location.href='http://localhost:8081/geosync_client/ListStoredChangelogs?changelogId='+$('#changelogId').val()"></td>
<td><input type="button" value="GetChangelogStatus" name="radiogroup" onClick="window.location.href='http://localhost:8081/geosync_client/getChangelogStatus?changelogId='+$('#changelogId').val()"></td>
<td><input type="button" value="CancelChangelog" name="radiogroup" onClick="window.location.href='http://localhost:8081/geosync_client/cancelChangelog?changelogId='+$('#changelogId').val()"></td>
<td><input type="button" value="GetChangelog" name="radiogroup" onClick="window.location.href='http://localhost:8081/geosync_client/getChangelog?changelogId='+$('#changelogId').val()"></td>
</tr>
<tr>
<td>datasetId: <select name="datasetId" id="datasetId">
			<option value="3"
				selected
				>AR5
		</select> </td>
</tr>
<tr>
<td colspan="2">changelogId: <input type="text" name="changelogId" id="changelogId" size=20 value="${changelogId}"></td>
</tr>
<tr>
<td colspan="2">startIndex: <input type="text" name="startIndex" id="startIndex" size=20 value="${startIndex}"></td>
</tr>
<tr>
<td colspan="2">count: <input type="text" name="count" id="count" size=20 value="${count}"></td>
</tr>
<tr>
<td colspan="2">ResultType: <input type="text" name="ResultType" id="ResultType" size=20 value="${ResultType}"></td>
</tr>
<tr>
<td colspan="2">outputFormat: <input type="text" name="outputFormat" id="outputFormat" size=20 value="${outputFormat}"></td>
</tr>
<tr>
<td>&nbsp;</td>
<tr>
<td>Output:</td>
<td colspan="6">${lastIndex}</td>
</tr>
</table>
</body>
</html>

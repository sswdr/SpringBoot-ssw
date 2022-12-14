<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>test model student</title>
</head>
<body>
Hello ${name}!
<#assign endTime=(.now?long?c)?string?substring(0,10)>
<#assign startTime=(((.now?long)-365*24*60*60*1000)?long?c)?string?substring(0,10)>
startTime=${startTime}
endTime=${endTime}
</body>
</html>
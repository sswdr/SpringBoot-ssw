package com.ssw.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

/**
 * @author ssw
 * @date 2023/12/12 14:29
 */
@Api(tags = "TestHttps模块")
@RestController
public class TestHttpsController {

    @Autowired
    @Qualifier("noSSLAuthRestTemplate")
    public RestTemplate restTemplate;

    //    $session = New-Object Microsoft.PowerShell.Commands.WebRequestSession
    //    $session.UserAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36 Edg/120.0.0.0"
    //            $session.Cookies.Add((New-Object System.Net.Cookie("Idea-81a02315", "5a51ff54-fd85-41da-97bf-0c8d19d927a1", "/", "localhost")))
    //    Invoke-WebRequest -UseBasicParsing -Uri "http://localhost:8888/url" `
    //            -Method "POST" `
    //            -WebSession $session `
    //            -Headers @{
    //        "Accept"="*/*"
    //        "Accept-Encoding"="gzip, deflate, br"
    //        "Accept-Language"="zh-CN,zh;q=0.9,en;q=0.8,en-GB;q=0.7,en-US;q=0.6"
    //        "Origin"="http://localhost:8888"
    //        "Referer"="http://localhost:8888/doc.html"
    //        "Request-Origion"="Knife4j"
    //        "Sec-Fetch-Dest"="empty"
    //        "Sec-Fetch-Mode"="cors"
    //        "Sec-Fetch-Site"="same-origin"
    //        "knife4j-gateway-code"="ROOT"
    //        "sec-ch-ua"="`"Not_A Brand`";v=`"8`", `"Chromium`";v=`"120`", `"Microsoft Edge`";v=`"120`""
    //        "sec-ch-ua-mobile"="?0"
    //        "sec-ch-ua-platform"="`"Windows`""
    //    } `
    //            -ContentType "application/json" `
    //            -Body "https://fanyi.baidu.com/?aldtype=16047#zh/en/%E8%AE%A4%E8%AF%81"
    @ApiImplicitParam(name = "url", value = "url", required = true)
    @ApiOperation(value = "请求url方法")
    @PostMapping("/url")
    public ResponseEntity<String> getBody(@RequestBody String url) {
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, null, String.class);
        return ResponseEntity.ok(Objects.requireNonNull(responseEntity.getBody()));
    }
}
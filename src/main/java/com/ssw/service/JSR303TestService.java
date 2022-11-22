package com.ssw.service;

import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

@Service
public class JSR303TestService {

    public void method01(BindingResult result01) {
        ObjectError objectError = new ObjectError("addErrorName", "addErrorMsg");
        result01.addError(objectError);
    }
}

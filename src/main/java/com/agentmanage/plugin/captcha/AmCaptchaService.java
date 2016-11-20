package com.agentmanage.plugin.captcha;

import com.octo.captcha.service.captchastore.CaptchaStore;
import com.octo.captcha.service.image.DefaultManageableImageCaptchaService;

public class AmCaptchaService extends DefaultManageableImageCaptchaService{

    public CaptchaStore getStore(){
        return store;
    }
}

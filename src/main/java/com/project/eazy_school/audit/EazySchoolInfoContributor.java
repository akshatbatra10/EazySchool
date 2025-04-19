package com.project.eazy_school.audit;

import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class EazySchoolInfoContributor implements InfoContributor {


    @Override
    public void contribute(Info.Builder builder) {
        Map<String, String> eazyMap = new HashMap<>();
        eazyMap.put("App Name", "Eazy School");
        eazyMap.put("App Version", "1.0.0");
        eazyMap.put("App Description", "Eazy School web application for students and administrators");
        eazyMap.put("Contact Email", "eazyschool@gmail.com");
        eazyMap.put("Contact Mobile", "8989898984");
        builder.withDetail("EazySchool-Info", eazyMap);
    }
}

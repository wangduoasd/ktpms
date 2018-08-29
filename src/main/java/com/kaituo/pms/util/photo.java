package com.kaituo.pms.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public class photo {
    public boolean photos(MultipartFile photo)

    {
        if (!photo.isEmpty()) {
            String filename = photo.getOriginalFilename();
            File file = new File("F:\\workelplice\\jifenxitong\\WebContent\\img" + "\\" + filename);
            try {
                photo.transferTo(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
            String photo1 = "img" + "/" + filename;
        }
        return true;
    }
}

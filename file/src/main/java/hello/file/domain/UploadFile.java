package hello.file.domain;

import lombok.Data;

/**
 * Created by Hunseong on 2022/04/05
 */
@Data
public class UploadFile {

    private String uploadFileName;
    private String storeFileName;

    public UploadFile(String uploadFileName, String storeFileName) {
        this.uploadFileName = uploadFileName;
        this.storeFileName = storeFileName;
    }
}

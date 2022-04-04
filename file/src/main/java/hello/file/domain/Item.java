package hello.file.domain;

import lombok.Data;

import java.util.List;

/**
 * Created by Hunseong on 2022/04/05
 */
@Data
public class Item {

    private Long id;
    private String itemName;
    private UploadFile attachFile;
    private List<UploadFile> imageFiles;
}

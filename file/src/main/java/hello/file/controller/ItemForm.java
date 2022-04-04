package hello.file.controller;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created by Hunseong on 2022/04/05
 */
@Data
public class ItemForm {

    private String itemName;
    private MultipartFile attachFile;
    private List<MultipartFile> imageFiles;
}

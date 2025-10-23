package model;
import com.google.gson.annotations.Expose;
import lombok.Data;

import java.util.List;

@Data
public class ErrorMessageModel {
    @Expose
    private List<String> error;
}
package model;

import com.google.gson.annotations.Expose;
import lombok.Data;

import java.time.LocalDate;

@Data
public class EmotionModel {
    @Expose(serialize = false)
    private String id;

    @Expose
    private String deviceId;

    @Expose
    private LocalDate date;

    @Expose
    private String emotion;
}

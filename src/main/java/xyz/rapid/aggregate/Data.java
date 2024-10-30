package xyz.rapid.aggregate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Data {
    private String id;
    private String img;
    private String url;
    private String title;
    private String brief;
    private String category;
    private int indes;
}

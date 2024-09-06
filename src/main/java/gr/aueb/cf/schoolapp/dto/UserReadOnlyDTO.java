package gr.aueb.cf.schoolapp.dto;

public class UserReadOnlyDTO extends BaseUserDTO{
    private Integer id;
    public UserReadOnlyDTO() {}

    public UserReadOnlyDTO(String username, String password, Integer id) {
        super(username, password);
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}

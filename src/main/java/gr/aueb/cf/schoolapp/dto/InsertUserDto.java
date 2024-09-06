package gr.aueb.cf.schoolapp.dto;

public class InsertUserDto extends BaseUserDTO{
    private  String corfimPassword;

    public InsertUserDto() {}

    public InsertUserDto(String username, String password, String corfimPassword) {
        super(username, password);
        this.corfimPassword = corfimPassword;
    }

    public String getCorfimPassword() {
        return corfimPassword;
    }

    public void setCorfimPassword(String corfimPassword) {
        this.corfimPassword = corfimPassword;
    }
}

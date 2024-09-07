package gr.aueb.cf.schoolapp.dto;

public class InsertUserDto extends BaseUserDTO{
    private  String corfimPassword;

    public InsertUserDto() {}

    public InsertUserDto(String username, String password, String confirmedPassword) {
        super(username, password, confirmedPassword);
    }
}

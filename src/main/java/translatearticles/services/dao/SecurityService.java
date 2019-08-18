package translatearticles.services.dao;

public interface SecurityService {

    String findLoggedInUsername();

    void autoLogin(String username, String password);

}

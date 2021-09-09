
package Users;

import entity.Users;
import facade.UsersFacadeLocal;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;


@Named(value = "usersManagedBean")
@SessionScoped

public class UsersManagedBean implements Serializable{
    
    private List<Users> _usersList;
    private String user;
    private String password;
    private int idprivilege;    
    private String message = "";
    
    
  @Inject
  UsersFacadeLocal usersFacadeLocal;
    
    @PostConstruct
    private void init(){
        _usersList = usersFacadeLocal.findAll();
    }
    
public UsersManagedBean(){
}


    public List<Users> getUsersList() {
        return _usersList;
    }

    public void setUsersList(List<Users> _usersList) {
        this._usersList = _usersList;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getIdprivilege() {
        return idprivilege;
    }

    public void setIdprivilege(int idprivilege) {
        this.idprivilege = idprivilege;
    }

    

    public String login(){
    
        for(Users logg : _usersList){
            if (user !=null && user.equals(logg .getUsername())){
                if(pass!=null && password.equals(logg.getPassword())){
                    idprivilege = logg.getIdPrivilege();
                }
            }
        }
        
          if (idprivilege==1){
              return "user";
              }else if(idprivilege==2){
             return "admin";
           }else{
            return "";
           }
    }
    
    public String register(){ 
    Users users= new Users(user, password, idprivilege);
    users.setId(idprivilege);
    users.setUsername(user);
    users.setPassword(password);
    usersFacadeLocal.create(users);
    init();
    return "index";


}
    
}
                






   

/*


import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;

public class Utente {
    //simpleStringProperty è come una string ma con piu funzionalita per essere modificata
    private SimpleStringProperty name, password;
    private SimpleBooleanProperty isAdmin;
    public Utente(String name, String password, Boolean isAdmin){
        this.name = new SimpleStringProperty(name);
        this.password= new SimpleStringProperty(password);
        this.isAdmin= new SimpleBooleanProperty(isAdmin);;
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name = new SimpleStringProperty(name);
    }

    public String getPassword() {
        return password.get();
    }

    public SimpleStringProperty passwordProperty() {
        return password;
    }

    public void setPassword(String password) {
        this.password.set(password);
    }


    public Boolean getIsAdmin() {
        return isAdmin.get();
    }


    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin.set(isAdmin);
    }
    public SimpleBooleanProperty isAdminProperty(){
        return isAdmin;
    }
    public String toString(){
        return this.getName()+this.getPassword()+this.getIsAdmin();
    }
}
*/


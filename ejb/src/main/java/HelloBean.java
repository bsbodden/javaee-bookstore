import javax.ejb.Stateless;

@Stateless
public class HelloBean implements HelloLocal {
    public String sayHello() {
        return "Hello";
    }
}

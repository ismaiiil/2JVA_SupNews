package Core.dao;
import Core.vo.Credential;
import java.util.List;

public interface CredentialDao {

    public void insert(Credential credential);

    public List<Credential> select();
}

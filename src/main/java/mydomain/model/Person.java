package mydomain.model;

import javax.persistence.*;

@Entity
public class Person
{
    @Id
    Long id;
    
    @Version
    long version;

    String name;

    public Person(long version, long id, String name)
    {
        this.version = version;
        this.id = id;
        this.name = name;
    }

    public String getName()
    {
        return name;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public long getVersion() {
        return version;
    }
    
    public void setVersion(long version) {
        this.version = version;
    }

    public Long getId()
    {
        return id;
    }
}

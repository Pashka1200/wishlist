package table;

import javax.persistence.*;

/**
 * Created by pavlo on 14.07.15.
 */

@Entity
@Table(name="user")
@NamedQueries({
        @NamedQuery(name = "findOneUserByFacebookId",
        query = "SELECT u " +
                "FROM User u WHERE u.facebookId = :facebook_id")
})
public class User {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name="fb_id", unique = true)
    private long facebookId;

    @Column(name="date_of_birth")
    private String date_of_birth;


    public long getId() {
        return id;
    }

    public long getFacebookId() {
        return facebookId;
    }

    public String getDate_of_birth() {
        return date_of_birth;
    }


    public void setId(long id) {
        this.id = id;
    }

    public void setFacebookId(long facebookId) {
        this.facebookId = facebookId;
    }

    public void setDate_of_birth(String date_of_birth) {
        this.date_of_birth = date_of_birth;
    }
}

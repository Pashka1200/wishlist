package table;

import javax.persistence.*;

/**
 * Created by pavlo on 14.07.15.
 */

@Entity
@Table(name="user")
@NamedQueries({
        @NamedQuery(name = "findUserByFacebookId",
                query = "SELECT u.id " +
                        "FROM User u WHERE u.facebookId = :facebook_id"),
        @NamedQuery(name = "findOneUserByFacebookId",
        query = "SELECT u " +
                "FROM User u WHERE u.facebookId = :facebook_id")
})
public class User {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name="fb_id", unique = true)
    private int facebookId;

    @OneToOne
    @PrimaryKeyJoinColumn
    private User user;

    public int getId() {
        return id;
    }

    public int getFacebookId() {
        return facebookId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFacebookId(int facebookId) {
        this.facebookId = facebookId;
    }





//    @Column(name="vk_id", unique = true)
//    private int vkontakteId;
//
//    public void setVkontakteId(int vkontakteId) {
//            this.vkontakteId = vkontakteId;
//    }
//
//    public int getVkontakteId() {
//        return vkontakteId;
//    }
}

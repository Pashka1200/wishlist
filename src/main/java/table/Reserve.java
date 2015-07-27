package table;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by pavlo on 14.07.15.
 */
@Entity
@Table(name="reserve")
@NamedQueries({
        @NamedQuery(name = "findReservesUserByFacebookId",
                query = "SELECT r " +
                        "FROM Reserve r WHERE r.client.id = :userId"),
        @NamedQuery(name = "findReservesByItemId",
                query = "SELECT r " +
                        "FROM Reserve r WHERE r.item.id = :itemId")
})

public class Reserve implements Serializable{

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne(fetch = FetchType.EAGER )
    @JoinColumn(name="client_id", nullable = false)
    private User client;

    @Column(name = "buyer_id")
    private Integer buyer_id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="item_id", nullable = false)
    private Item item;

     @Column(name = "is_buy")
    private int is_buy;


    public int getId() {
        return id;
    }

    public User getClient() {
        return client;
    }

    public Integer getBuyer_id() {
        return buyer_id;
    }

    public Item getItem() {
        return item;
    }

    public int getIs_buy() {
        return is_buy;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setClient(User client) {
        this.client = client;
    }

    public void setBuyer_id(Integer buyer_id) {
        this.buyer_id = buyer_id;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public void setIs_buy(int is_buy) {
        this.is_buy = is_buy;
    }


}

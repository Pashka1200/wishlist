package table;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by pavlo on 14.07.15.
 */
@Entity
@Table(name="reserve")
@NamedQueries({
        //get items list which received or not
        @NamedQuery(name = "findReservesUserByClientFacebookId",
                query = "SELECT r " +
                        "FROM Reserve r WHERE r.client.id = :userId AND r.is_buy = :status"),

        //get items for present to someone
        @NamedQuery(name = "findReservesUserByBuyerFacebookId",
                query = "SELECT r " +
                        "FROM Reserve r WHERE r.buyer_id = :userId AND r.is_buy = :status"),

        //for manipulation with some item
        @NamedQuery(name = "findReservesByItemId",
                query = "SELECT r " +
                        "FROM Reserve r WHERE r.item.id = :itemId")
})

public class Reserve implements Serializable{

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne(fetch = FetchType.EAGER )
    @JoinColumn(name="client_id", nullable = false)
    private User client;

    @Column(name = "buyer_id")
    private Long buyer_id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="item_id", nullable = false)
    private Item item;

     @Column(name = "is_buy")
    private int is_buy;


    public long getId() {
        return id;
    }

    public User getClient() {
        return client;
    }

    public Long getBuyer_id() {
        return buyer_id;
    }

    public Item getItem() {
        return item;
    }

    public int getIs_buy() {
        return is_buy;
    }


    public void setId(long id) {
        this.id = id;
    }

    public void setClient(User client) {
        this.client = client;
    }

    public void setBuyer_id(Long buyer_id) {
        this.buyer_id = buyer_id;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public void setIs_buy(int is_buy) {
        this.is_buy = is_buy;
    }


}
